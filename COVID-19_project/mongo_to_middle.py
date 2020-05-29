import datetime
import json
import middle_layer as m
import mongo_connection

db = mongo_connection.get_conn()

class Location:
    def __init__(self, country, state=None, city=None):
        self.country = country
        self.state = state
        #assume countries without states exist
        self.city = city
    def to_dict(self):
        result = {}
        result['country'] = self.country
        if self.state is not None:
            result['state'] = self.state
        if self.city is not None:
            result['city'] = self.city
        return result
    def __str__(self):
        return ", ".join(name in (self.city, self.state, self.country)
            if name is not None))

def insert_hospital(name, country, state, city, beds, available, lati, longi):
    hospital = {}
    hospital['location'] = {
        'coords': {
            'type': 'Point',
            'coordinates': [
                longi,
                lati,
            ]
        },
        'country': country,
        'state': state,
        'city': city,
    }
    hospital['name'] = name
    hospital['beds'] = beds
    hospital['available'] = available
    # put this in MongoDB
    print('Putting into database ...')
    # db.user_cases.insert_one(case)
    hospital = json.dumps(hospital) # dictionary type to json type
    m.log_update('add_hospital',[hospital],druid=False)
    print('Insert successful!')
    # db.hospitals.insert_one(hospital) # TODO use middle layer

def date_to_dict(date):
    try:
        return {
            'year': date.year,
            'month': date.month,
            'day': date.day,
        }
    except AttributeError:
        # the date is NoneType
        return None


def add_case(status, loc=None, hosp_loc=None, hosp_name=None,
        date_start=None, date_end=None):
    status = status.lower()
    if status not in ('active', 'recovered', 'dead'):
        raise ValueError('bad case status')
    try:
        if date_start > date_end:
            raise ValueError('bad case timing')
        if date_start > datetime.date.today() or date_end > datetime.date.today():
            raise ValueError('bad case timing')
    except TypeError:
        # date_start is None or date_end is None
        pass

    if hosp_loc is not None or hosp_name is not None:
        if hosp_loc is None or hosp_name is None:
            raise ValueError('hospital needs name and location')
    case = {}
    case['status'] = status
    if loc is not None:
        case['location'] = loc.to_dict()
    if hosp_name is not None:
        case['hospital'] = {}
        case['hospital']['name'] = hosp_name.upper()
        case['hospital']['location'] = hosp_loc.to_dict()
    # date_start = date_to_dict(date_start)
    if date_start is not None:
        case['date_start'] = date_to_dict(date_start)
    # date_end = date_to_dict(date_end)
    if date_end is not None:
        case['date_end'] = date_to_dict(date_end)
    #put this in MongoDB
    print('Putting into database ...')
    # db.user_cases.insert_one(case)
    case = json.dumps(case) # dictionary type to json type
    m.log_update('add_user_case',[case],druid=False)
    print('Insert successful!')
    
def update_case(loc, date_start, status = None, hosp_loc=None,
                 hosp_name=None, date_end=None):
    if status is not None:
        status = status.lower()
    if status not in ('active', 'recovered', 'dead'):
        raise ValueError('bad case status')
    try:
        if date_start > date_end:
            raise ValueError('bad case timing')
        if date_start > datetime.date.today() or date_end > datetime.date.today():
            raise ValueError('bad case timing')
    except TypeError:
        # date_start is None or date_end is None
        pass
    if hosp_loc is not None or hosp_name is not None:
        if hosp_loc is None or hosp_name is None:
            raise ValueError('hospital needs name and location')
    case = {}
    if status is not None:
        case['status'] = status
    if loc is not None:
        case['location'] = loc.to_dict()
    if hosp_name is not None:
        case['hospital'] = {}
        case['hospital']['name'] = hosp_name.upper()
        case['hospital']['location'] = hosp_loc.to_dict()
    # date_start = date_to_dict(date_start)
    if date_start is not None:
        case['date_start'] = date_to_dict(date_start)
    # date_end = date_to_dict(date_end)
    if date_end is not None:
        case['date_end'] = date_to_dict(date_end)
    #put this in MongoDB
    print('Putting into database ...')
    # db.user_cases.insert_one(case)
    case = json.dumps(case) # dictionary type to json type
    m.log_update('update_user_case',[case],druid=False)
    print('Case updated!')

def print_cases():
    # IDEA: throw in keyword for [{status: <active,death,recovered>}]
    print('printing...')
    # m.log_update('print_cases',[],druid=False)
    c = db.user_cases.find()
    for d in c:
        print('-----------------')
        print_case(d)
    print('-----------------')
    print('done printing')

def print_case(d):
    case = d['status']
    if 'location' in d:
        case += ' in ' + str(d)
    if 'hospital' in d:
        case += ' at ' + d['hospital']['name']

def print_active_cases():
    print('printing...')
    m.log_update('print_active_cases',[],druid=False)
    print('done printing')
    # c = db.user_cases.find() # TODO log to m
    # for d in c:
    #     print('-----------------')
    #     print(d)
    # print('-----------------')

def print_test(): # TODO remove this from everything
    m.log_update('print_test',[],druid=False)

def get_best_hospitals():
    hospitals = db.user_cases.find({"hospital":{"$exists":True}, "hospital.location.city":{"$exists":True}})
    best_hospitals = list(db.user_cases.find({"hospital":{"$exists":True}, "hospital.location.city":{"$exists":True}}).limit(3)) # start a list of the first three hospitals; aribtrarily picked three hospitals

    # initialize the "best" hospitals aka the first three hospitals
    severities = [0,0,0]
    for h in best_hospitals:
        # print(h['hospital']['name'])
        name = h['hospital']['name']
        city = h['hospital']['location']['city']
        # print(name)
        d = db.user_cases.find({"hospital.name":name,"hospital.location.city":city,"status":"dead"}).count()
        r = db.user_cases.find({"hospital.name":name,"hospital.location.city":city,"status":"recovered"}).count()
        if (r+d)>0:
            severities[i] = r/(r+d)
        i+=1
	
    # then try to have the actual hospitals replace those if they're better
    for h in hospitals:
        name = h['hospital']['name']
        city = h['hospital']['location']['city']
        d = db.user_cases.find({"hospital.name":name,"hospital.location.city":city,"status":"dead"}).count()
        r = db.user_cases.find({"hospital.name":name,"hospital.location.city":city,"status":"recovered"}).count()
        if (r+d) > 0:
            severity = r/(r+d)
            for i in range(3):
                b_name = best_hospitals[i]['hospital']['name']
                print("my name:", name, "   being compared to ", b_name)
                if name != b_name and severity > severities[i]:
                    print("totally not the same name")
                    best_hospitals[i] = h
                    severities[i] = severity
                    break

    print("The best three hospitals are:")

    # best_hospitals.sort()
    # severities.sort()

    for i in range(3):
        b = best_hospitals[i]
        name = b['hospital']['name']
        city = b['hospital']['location']['city']
        print("Name:",name,"      City:",city,"     Recovery percentage:",severities[i])
    # return best_hospitals # TODO sort this eventually
