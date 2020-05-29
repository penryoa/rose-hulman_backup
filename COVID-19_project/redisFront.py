import redis
from pydruid.db import connect
from pymongo import MongoClient
import druid_backend
from queue import Queue
import threading
import requests
#from sshtunnel import SSHTunnelForwarder


failures = {key: False for key in ('rConn', 'dConn', 'mConn')}
try:
    rConn = redis.Redis('covidcrew-4.csse.rose-hulman.edu', password='no')
except redis.exceptions.ConnectionError:
    failures['rConn'] = True

dConn = connect(host='covidcrew-3.csse.rose-hulman.edu',port=8088,path='/druid/v2/sql/',scheme='http')
mConn = MongoClient("mongodb://covidcrew-1.csse.rose-hulman.edu:27017/user_cases",
    username='csse', password='pass')
#mConn = MongoClient("covidcrew-1.csse.rose-hulman.edu:27017")
dcurs = dConn.cursor()
#m_host = "covidcrew-1.csse.rose-hulman.edu"
#m_db = "user_cases"
#m_user = "csse"
#m_pass = "pass"
#server = SSHTunnelForwarder(m_host,
	#ssh_username = m_user,
	#ssh_pass = m_pass,
	#remote_bind_address = ('127.0.0.1',27017))
#server.start()
#client = MongoClient('127.0.0.1',server.local_bind_port)
mdb = mConn['user_cases']

#5 minutes
CACHE_LIVE_TIME=300

class BGDaemon(threading.Thread):
    """Used to run background jobs."""
    def __init__(self):
        super().__init__(daemon=True)
        self.tq = Queue()
        self.start()

    def run(self):
        while True:
            job = self.tq.get()
            job()
            self.tq.task_done()

    def schedule_job(self, job):
        """Pass in a background job as a callable with no args."""
        self.tq.put(job)

bgd = BGDaemon()

#Here's an example of how to make a query fault-tolerant.
#If we want to do this for all DB accesses, we might need
#a more clever design.
def todays_deaths(country):
    if failures['rConn']:
        #Warning: This could fail if Druid is down too.
        return druid_backend.todays_deaths(country)
    key = country+'_tdeaths'
    def cache_update():
        try:
            result = druid_backend.todays_deaths(country)
        except requests.exceptions.ConnectionError:
            failures['dConn'] = True
            return None
        rConn.set(key, result)
        rConn.expire(key, CACHE_LIVE_TIME)
        return result
    try:
        cache_attempt = rConn.get(key)
    except redis.exceptions.ConnectionError:
        failures['rConn'] = True
        #retry now that we know redis is down
        return todays_deaths(country)
    if cache_attempt is None:
        return cache_update()
    else:
        #Refresh the key since we used it recently
        rConn.expire(key, CACHE_LIVE_TIME)
        #Asynchronously update cache to ensure consistency
        bgd.schedule_job(cache_update)
        return int(cache_attempt)


if __name__ == '__main__':
    print("FROM REDIS:")
    rConn.set('hello','world') 
    print(rConn.get('hello'))
    print("Testing cache")
    for _ in range(10):
        input('Pause to kill DBs if you want')
        print(todays_deaths('US'))
    print()

    #print("FROM DRUID:")
    #dcurs.execute("""
    #        SELECT *
    #        FROM covid
    #        LIMIT 5
    #""")
    #for row in dcurs:
    #        print (row)
    #### close druid connection
    #print()

    print("FROM MONGO:")
    c = mdb.hospitals.find().limit(5)
    for row in c:
            print(row)
    print(mdb.collection_names())
    mConn.close()
    #server.stop()
    print()
