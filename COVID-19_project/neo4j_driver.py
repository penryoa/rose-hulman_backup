from neo4j import GraphDatabase
URI = "bolt://covidcrew-4.csse.rose-hulman.edu:7687"
#TODO fix formatting
class Neo4JHospitals:
    def __init__(self):
        self.driver = GraphDatabase.driver(
            URI, auth=("neo4j", "TheDruids"), encrypted=False)
        self.session = self.driver.session()

    def insert_hospital(
            self,
            name,
            country, state, city,
            total_beds, available,
            lat, long):
        query = \
"""CREATE (n:Hospital {
    name: $name,
    country: $country,
    state: $state,
    city: $city,
    total_beds: $total_beds,
    available: $available,
    loc: point({
        latitude: $lat,
        longitude: $long
    })
})"""
        result = self.session.run(
            query,
            name=name,
            country=country, state=state, city=city,
            total_beds=total_beds, available=available,
            lat=lat, long=long)
        return len(list(result))

    def refer_patient(from_id, to_id):
        #begin transaction
        query = \
"""MATCH (n1:Hospital)-[r:Referral]->(n2:Hospital)
WHERE n1.id = $from_id AND n2.id = $to_id
RETURN id(r)"""
        if result_count == 0:
            query = \
"""MATCH (n1:Hospital), (n2:Hospital)
WHERE n1.id = $from_id AND n2.id = $to_id
CREATE (a)-[r:Referral {count: 0}]->(b)"""
        query =\
"""MATCH ()-[r:Referral]->()
WHERE id(r) = $id
SET r.count=r.count+1"""

#test code
if __name__ == '__main__':
    conn = Neo4JHospitals()
    conn.insert_hospital(
        'Test Hospital',
        'US', 'IN', 'Terre Haute',
        100, 50,
        39.481980, -87.327500)
    for result in conn.session.run("MATCH (n)\nRETURN n"):
        print(result)
