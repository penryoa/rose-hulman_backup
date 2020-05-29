import os
import sys
import os.path
import json
import urllib.request
import requests

import middle_layer
import druid_backend


MOST_RECENT_FILE = 'most_recent'

def compare_csv_by_filename(filename):
    result = filename.split('-')
    result[2] = result[2].split('.')[0]
    result = [int(s) for s in result]
    result = (result[2], result[0], result[1])
    return result

def make_uri(filename):
    return ("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/"
        + "master/csse_covid_19_data/csse_covid_19_daily_reports/"
        + filename)

def make_spec(uris):
    with open('ingestion_spec.json') as f:
        spec = json.load(f)
    spec['spec']['ioConfig']['inputSource']['uris'] = uris
    return json.dumps(spec)


def get_most_recent():
    os.chdir(os.path.expanduser('~'))
    os.chdir('covid-crew')
    with open(MOST_RECENT_FILE) as f:
        most_recent = f.read()
    os.chdir('COVID-19')
    os.system('git pull')
    loc = os.path.join('csse_covid_19_data','csse_covid_19_daily_reports')
    os.chdir(loc)
    all_updates = filter(lambda f: 'csv' in f, os.listdir('.'))
    newest_files = [f for f in all_updates
        if compare_csv_by_filename(f) > compare_csv_by_filename(most_recent)]
    for _ in range(3):
        os.chdir('..')
    return newest_files

def main():
    filenames = get_most_recent()
    uris = [make_uri(filename) for filename in filenames]
    spec = make_spec(uris)
    if middle_layer.is_available():
        middle_layer.log_update('import_corona_data', [spec], druid=True)
        succeeded = True
    else:
        succeeded = druid_backend.upload(spec)
    if succeeded:
        newest_filename = max(filenames, key=compare_csv_by_filename)
        with open(MOST_RECENT_FILE, 'w') as f:
            f.write(newest_filename)
    else:
        print('O_O Zoinks!')

if __name__ == '__main__':
    main()
