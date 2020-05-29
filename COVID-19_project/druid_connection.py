from pydruid.db import connect

conn = connect(host='covidcrew-3.csse.rose-hulman.edu',port=8082,
    path='/druid/v2/sql/',scheme='http')

def get_conn():
    return conn