# schoolBackup
## Homework Assignments
The files starting with bookApp*.py are homework projects where we had to design and implement a book app using a given NoSQL database (REDIS, MongoDB, and Neo4j). They consist of a front end and back end, though for the REDIS assignment, that was not separated out and I was just calling the functions. To use them, have the respective database servers running in the background and run *python3 <bookAppFront<db>.py* and it should be good from there!
  
## Class Project
The finalized code for the class project is the folder titled COVID-19 Project. The goal is to report cases of COVID-19, edit cases, find hospitals, and recommend hospitals with better recovery-to-death ratios. 
It is ran with: three Druid servers (one master, one data, one query server), one REDIS server, one MongoDB server. Assuming all servers are going, you can run *python3 appFront.py* to get the ball rolling. 
This isn't the best app written by any means, but it has the basics mostly working. It was done during the COVID outbreak and my team of 3 had very little to no experience with the tools. There was a large learning curve involved as it was an indepent learning project.
