from mongo_to_middle import *
import datetime

def main():
    active = True
    while active:
        print('1 to enter a case. 2 to view user cases. 3 for the best hospitals. 4 to update a case. q to quit.')
        choice = input().strip()
        if choice == '1':
            enter_case()
        if choice == '2':
            print_user_cases()
        if choice == '3':
            get_best_hospitals()
        if choice == '4':
            update_case()
        if choice == 'q':
            print('Bye!')
            return True

def yes_or_no():
    choice=""
    while choice not in ('y','n'):
        choice = input().strip().lower()
    return {
        'y': True,
        'n': False,
    }[choice]

def get_location():
    print('Enter country')
    country = input()
    print('Enter state or province? y/n')
    if yes_or_no():
        state = input('State or province name: ')
    else:
        state = None
    print('Enter city? y/n')
    if yes_or_no():
        city = input('City name: ')
    else:
        city = None
    return Location(country, state, city)

def get_date():
    print('Use current date? y/n')
    if yes_or_no():
        return datetime.date.today()
    else:
        try:
            print('Enter year.')
            year = int(input().strip())
            print('Enter the month as an integer from 1 to 12')
            month = int(input().strip())
            print('Enter the day.')
            day = int(input().strip())
            return datetime.date(year, month, day)
        except ValueError:
            print('Please reenter a valid date.')
            return get_date()

def enter_case():
    print('Enter case status.',
        'a: Active case',
        'd: Dead',
        'r: Recovered :)')
    choice = None
    while choice not in ('a','d','r'):
        choice = input().strip().lower()
    status = {
        'a': 'active',
        'r': 'recovered',
        'd': 'dead',
    }[choice]
    print('Enter case location? y/n')
    if yes_or_no():
        location = get_location()
    else:
        location = None
    print('Enter hospital? y/n')
    if yes_or_no():
        print('Hospital name?')
        hosp_name = input()
        hosp_loc = None
        if location is not None:
            print('Use same location as patient? y/n')
            if yes_or_no():
                hosp_loc = location
        if hosp_loc is None:
            hosp_loc = get_location()
    else:
        hosp_name = None
        hosp_loc = None
    print('Do you know when the case was discovered? y/n')
    if yes_or_no():
        print('Enter when the case was discovered')
        date_start = get_date()
    else:
        date_start = None
    if status in ('recovered', 'dead'):
        if status == 'recovered':
            print('When did you find out the patient recovered?')
        else:
            print('When did you find out the patient died?')
        date_end = get_date()
    else:
        date_end = None
    add_case(status, loc=location, hosp_loc=hosp_loc,
                           hosp_name=hosp_name, date_start=date_start,
                           date_end=date_end)
    print('Case added successfully.')

def print_user_cases():
    # print('All the cases entered are as follows:')
    # print_all_cases()
    print('1 to view the first 10 cases. 2 to view first 10 active cases. 3 to return to the main menu.')
    choice=""
    while choice not in ('1','2','3'):
        choice = input().strip()
    if choice == '1':
        print_cases()
    if choice == '2':
        print_active_cases()
    if choice == '3':
        return
    
# Cannot update location or start date since these form the unique id for the case
def update_case():
    print ("Enter case location:")
    location = get_location()
    print("Enter discovery date:")
    date_start = get_date()
    print("Update case status? y/n")
    if yes_or_no():
        print('Enter case status.',
        'a: Active case',
        'd: Dead',
        'r: Recovered :)')
        choice = None
        while choice not in ('a','d','r'):
            choice = input().strip().lower()
        status = {
            'a': 'active',
            'r': 'recovered',
            'd': 'dead',
        }[choice]
    else:
        status = None
    print("Update hospital? y/n")
    if yes_or_no():
        print('Hospital name?')
        hosp_name = input()
        hosp_loc = None
        if location is not None:
            print('Use same location as patient? y/n')
            if yes_or_no():
                hosp_loc = location
        if hosp_loc is None:
            hosp_loc = get_location()
    else:
        hosp_name = None
        hosp_loc = None
    if status in ('recovered', 'dead'):
        if status == 'recovered':
            print('Update recovery date of patient? y/n')
        else:
            print('Update date of death of patient? y/n')
        if yes_or_no():
            date_end = get_date()
    else:
        date_end = None
    update_case( loc=location, date_start=date_start, status=status, hosp_loc=hosp_loc,
                 hosp_name=hosp_name,date_end=date_end)
    print('Case updated successfully.')
    
if __name__=="__main__":
    main()
