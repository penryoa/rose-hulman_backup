from pymongo import MongoClient

client = MongoClient("mongodb://covidcrew-1.csse.rose-hulman.edu:27017/user_cases",
    username='csse', password='pass')
db = client['user_cases']

def get_conn():
    return db