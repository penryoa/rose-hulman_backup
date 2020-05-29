import csv
import random
import covid_backend as conn

reader = csv.DictReader(open('data/hospital_data.csv', encoding='utf-8-sig'))

for line in reader:
    if line['BEDS'] is None or int(line['BEDS']) == -999:
        line['BEDS'] = 100
    else:
        line['BEDS'] = int(line['BEDS'])
    available = random.randint(0, line['BEDS'])
    line['LATITUDE'] = float(line['LATITUDE'])
    line['LONGITUDE'] = float(line['LONGITUDE'])
    conn.insert_hospital(
        line['NAME'],
        'USA', line['STATE'], line['CITY'],
        line['BEDS'], available,
        line['LATITUDE'], line['LONGITUDE'])
    print(line['NAME'],'inserted')
