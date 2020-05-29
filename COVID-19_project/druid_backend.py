import urllib.error
import urllib.request
import requests.exceptions
import druid_connection

conn = druid_connection.get_conn()

def upload(spec):
    url = 'http://covidcrew-1.csse.rose-hulman.edu:8081/druid/indexer/v1/task'
    spec_raw = spec.encode('utf-8')
    request = urllib.request.Request(url, data=spec_raw)
    request.add_header('Content-Type', 'application/json; charset=utf-8')
    try:
        with urllib.request.urlopen(request) as response:
            print(response.status)
            return response.status == 200
    except urllib.error.HTTPError as e:
        print(e.reason)
        print(e.headers)
        print(e.read())
        raise requests.exceptions.ConnectionError()
    except urllib.error.URLError as e:
        print(e)
        raise requests.exceptions.ConnectionError()

def todays_deaths(country):
    curs = conn.execute(
        """SELECT TIME_FLOOR("__time", 'P1D')
        AS "__time_time_floor", SUM(Deaths) AS total_deaths
        FROM covid
        WHERE "Country_Region" = %(country)s
        GROUP BY 1, Country_Region
        ORDER BY "__time_time_floor" DESC
        LIMIT 2""", 
        parameters={'country': country})

    curs = list(curs)
    today = curs[0]
    yesterday = curs[1]
    return int(today.total_deaths - yesterday.total_deaths)


#testing
if __name__ == '__main__':
    print(todays_deaths('US'))
