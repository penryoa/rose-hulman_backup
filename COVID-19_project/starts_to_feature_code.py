"""
I'm not sure where all of these would go, 
but I wanted to get some logic written 
and ready to copy and paste.


Our list of features:
1. Display current cases
2. Calculate ratio of dead cases to recovered in certain areas; ignore active cases. this measures severity.
When logged in (if we want to add this level of permission)
  ---> 3. Add a case
  ---> 4. Edit a case 
5. Recommend hospitals
6.  Send people to hospitals (transfer w/ number of beds)
7.  Best hospitals in terms of low death rates? Or least severity?
8.  Recommend areas to stay away from bc there are many active cases
9.  Total case graph
"""
from pygments.lexers import graph
import pandas
from msilib import Feature




"""
1. Display current cases

TODO Logic is in mongo_backend, though it doesn't print to console. Fix it there.
"""




"""
2. Calculate severity
Args: Country name as a string

TODO put this functionality in correct locations and error check it

We could improve this more as follows:
If druid is True, loc is a string formatted as
      "Country" = <country name> and "State" = <state name>
           ... and so on; it's ready to add to the druid query. "" if empty.

If druid is False, loc is in json format as
     {"country":<country name>,"state":<state name>}
           ... and so on; it's ready to add to the mongo query. {} if empty.
"""
def get_severity(country,druid=False):
    if druid:
        # get d = number of deaths
        curs = druid_conn.execute(
        """SELECT SUM(Deaths)/(SUM(Deaths)+SUM(Recovered)) AS severity
        FROM covid
        GROUP BY Country_Region, __time
        WHERE "Country_Region" = %(country)s""", 
        parameters={'country': country})
        
        return curs.severity
    else:
        # d_curs = db.user_cases.find({"location.country":country},{"status":"dead"}).aggregate([{$group {total_deaths: {$sum: 1}}}])
        # d = d_curs.total_deaths
        # r_curs = db.user_cases.find({"location.country":country},{"status":"recovered"},{total_recovered: {$sum: 1}})
        # r = r_curs.total_recovered
        d = db.user_cases.find({"location.country":country},{"status":"dead"}).count()
        r = db.user_cases.find({"location.country":country},{"status":"recovered"}).count()
        return r/(r+d)




"""
3. Add a case

DONE :)   Logic is in mongo_backend and mongo_to_middle
"""

def search_case(
        loc=None, hosp=None, hosp_loc=None,
        start_lower=None, start_upper=None):
    """3.5: Search for a case so users can edit it"""
    query = {}
    text_query = ""
    if loc is not None:
        text_query += " ".join('"{}"'.format(t) for t in
            (loc.country, loc.state, loc.city) if t is not None)
    if hosp is not None:
        text_query += '"{}"'.format(hosp)
    if text_query != "":
        query['$text'] = {'$search': text_query}
    yield from db.find(query)


"""
4. Edit a case
Args: not positive, but we need the old fields to find it and the new status.
 ---> could do tuple list in format ['field name','field value']
 ---> could be a json fragment in the format we need
 ---> could be each individual field (this is what I've done before)

TODO think about args, put this func where it needs to go, tweak as necessary
""" 
def update_case(new_status):
    db.user_cases.update_one({"status":"active"},{"$set": {"status":new_status}},False,False) # TODO make this specify the inputs we need
    return




"""
5. Recommend hospitals
Args: state/province name

TODO right now it just recommends based on severity. consider other factors maybe?
"""
def recommend_local_hospitals(state):
    hospitals = db.hospitals.find({"location.state":state})
    best_hospitals = db.hospitals.find({"location.state":state}).limit(3) # start a list of the first three hospitals; aribtrarily picked three hospitals
    # TODO create a new field in each of best_hospitals that holds the severity factor

    for h in hospitals:
        d = db.user_cases.find({"location.state":state, "hospital.name":h.name},{"status":"dead"}).count()
        r = db.user_cases.find({"location.state":state, "hospital.name":h.name},{"status":"recovered"}).count()
        severity = r/(r+d) # so this is the severity for a certain hospital
        # pseudocode: 
        # for (i=0;i<3;i++):
        #   if severity > best_hospitals[i].severity:
        #       best_hospital[i] = h
        #       break
    return best_hospitals.sort()





"""
6. Send people to hospitals
Args: from_hosp and to_hosp are the string names of the hospitals where num_patients will transfer from from_hosp to to_hosp

TODO error check this and put it where it needs to go. should be ok though!
"""
def transfer_patients(from_hosp, to_hosp, num_patients):
    # 1. check that old_hosp has that many patients and new_hosp can accept that many
    num_in_from_hosp = db.hospitals.find({"name":from_hosp},{"beds": 1, "available": 1, "_id": 0})
    num_in_to_hosp = db.hospitals.find({"name":to_hosp},{"beds": 1, "available": 1, "_id": 0})
    
    from_hosp_new_available = num_in_from_hosp.available + num_patients # transfering num_patients from here increases availability
    to_hosp_new_available = num_in_to_hosp.available - num_patients # transfering num_patients to here decreases availability

    if num_in_from_hosp.beds < from_hosp_new_available or to_hosp_new_available < 0:
        print("There are insufficient patients in the previous hospital or insufficient beds in the next hospital. Check your arguments.")
        return

    # 2. update previous hospital's availability
    db.hospitals.update_one({"name":from_hosp},{"$set": {"available":from_hosp_new_available}},False,False)

    # 3. set new's num to old num + num_patients
    db.hospitals.update_one({"name":to_hosp},{"$set": {"available":to_hosp_new_available}},False,False)

    return






"""
7. Best hospitals in terms of severity

TODO finish this out and put it where it needs to go.
"""
def get_best_hospitals():
    hospitals = db.hospitals.find({},{_id:0})
    best_hospitals = db.hospitals.find({}).limit(3) # start a list of the first three hospitals; aribtrarily picked three hospitals

    # initialize something to hold best_hospitals severities
    severities = {0,0,0}
    for (i=0;i<3;i++):
        d = db.user_cases.find({"location.country":h.location.country, "hospital.name":h.name},{"status":"dead"}).count()
        r = db.user_cases.find({"location.country":h.location.country, "hospital.name":h.name},{"status":"recovered"}).count()
        severities[i] = r/(r+d) 
    
    # then try to have the actual hospitals replace those if they're better
    for h in hospitals:
        d = db.user_cases.find({"location.country":h.location.country, "hospital.name":h.name},{"status":"dead"}).count()
        r = db.user_cases.find({"location.country":h.location.country, "hospital.name":h.name},{"status":"recovered"}).count()
        severity = r/(r+d) # so this is the severity for a certain hospital
        for (i=0;i<3;i++):
          if severity > best_hospitals[i].severity:
              best_hospital[i] = h
              severities[i] = severity
              break
    return best_hospitals # TODO sort this eventually







"""
8. Areas to avoid
 ---> Avoid areas with high levels of active cases. 

TODO the entire feature
"""
def get_worst_areas():
    # for each country (or state or whatever) in mongo, get the severity constant by that area.
    # return the worst three or five
    
    
    
    
    
    
"""
9. Total cases graph
 --> Grab historical cases by location from Druid and make graph with pandas
 
 TODO implement Feature
 """
 
 def graph_cases(country,druid=TRUE):
      curs = druid_conn.execute(
        """SELECT Deaths AS deaths, Timestamp AS time
        FROM covid
        WHERE "Country_Region" = %(country)s""", 
        parameters={'country': country})
        
        d = curs.deaths
        t = curs.time
        
        # Construct graph
        df = pd.DataFrame()
        df['Deaths'] = d
        df['Date'] = t
        df.plot.line(x='Date',y='Deaths')
     
