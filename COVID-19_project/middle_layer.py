import sys
from traceback import format_exception

import redis
from pydruid.db import connect
import pymongo
from pymongo import MongoClient
import druid_backend
import threading
import requests
import druid_adapters
import mongo_backend as mon

CMDS_FROM_IDS = {
    'test': print,
    'import_corona_data': druid_adapters.redis_to_druid_import,
    'add_user_case': mon.insert_case_json,
    'add_hospital': mon.add_hospital,
    'print_cases': mon.print_cases,
    'print_active_cases': mon.print_active_cases,
    'print_test': mon.print_test,
    'update_user_case': mon.update_case_json
}

rConn = None
try:
    rConn = redis.Redis('covidcrew-4.csse.rose-hulman.edu', password='no')
except redis.exceptions.ConnectionError:
    _redis_up = False
else:
    _redis_up = True

def is_available():
    return _redis_up

#log entry: $OP_ID-data-$DATA_ID
#Probably best for OP_ID not to contain dashes

def log_update(op, data, druid=True):
    if druid:
        target = 'druid-log'
    else:
        target = 'mongo-log'

    pl = rConn.pipeline()

    #just keep trying to log the update until it works
    while True:
        try:
            #generate new log entry key
            data_id = 'data-' + str(rConn.incrby('id_ctr', 1))
            log_entry_key = op + '-' + data_id

            pl.watch(log_entry_key)
            pl.watch(data_id)
            #add to REDIS log
            pl.multi()
            pl.rpush(target, log_entry_key)
            for arg in data:
                pl.rpush(data_id, arg)
            pl.execute()
            return
        except redis.WatchError:
            pass

class LogReader(threading.Thread):
    """Processes commands added to the transaction logs in REDIS.
    Make sure there is only 1 log reader for each DB in existence
    at any given time."""
    def __init__(self, rConn, log_name, errType):
        super().__init__()
        self.rConn = rConn
        self.log_name = log_name
        self.errType = errType

    def run(self):
        while True:
            next_op = self.rConn.lrange(self.log_name, 0, 0)
            if len(next_op) == 0:
                continue
            else:
                next_op = next_op[0].decode('utf-8')
            try:
                op_id, data_id = next_op.split('-data-')
                data_id = 'data-' + data_id
                cmd = CMDS_FROM_IDS[op_id]
                args = self.rConn.lrange(data_id, 0, -1)

                cmd(*args)
            except self.errType as db_error:
                continue
            except:
                exc_type, exc_value, trace = sys.exc_info()
                with open('middle_layer.log', 'a') as errlog:
                    err = format_exception(exc_type, exc_value, trace)
                    print(err, file=errlog)
                #fall through to lpop and delete. if the command is bad,
                #AND it isn't a DB problem, ignore it.

            self.rConn.lpop(self.log_name)
            self.rConn.delete(data_id)

def run_log_readers():
    d_reader = LogReader(rConn, 'druid-log', requests.exceptions.ConnectionError)
    #TODO instantiate MongoDB LogReader
    m_reader = LogReader(rConn, 'mongo-log', pymongo.errors.PyMongoError)
    d_reader.start()
    #run mongo log in the foreground so we don't make unnecessary threads
    m_reader.run()
    #wait forever
    d_reader.join()

if __name__ == '__main__':
    run_log_readers()
