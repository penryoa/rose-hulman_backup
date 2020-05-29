import json
import mongo_connection

db = mongo_connection.get_conn()

def insert_case_json(case):
    case = json.loads(case.decode('utf-8'))
    db.user_cases.insert_one(case)

def add_hospital(hosp):
    hosp = json.loads(hosp.decode('utf-8'))
    db.hospitals.insert_one(hosp)

def print_cases():
    c = db.user_cases.find().limit(10)
    for d in c:
        print('-----------------')
        print(d)
    print('-----------------')

def print_active_cases():
    c = db.user_cases.find({"status":"active"}).limit(10)
    for d in c:
        print('-----------------')
        print(d)
    print('-----------------')

def print_test():
    print('You can print from mongo_backend')
    
def update_case_json(case):
    case = json.loads(case.decode('utf-8'))
    db.user_cases.update_one(case)
