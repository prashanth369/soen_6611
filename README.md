![alt text](https://users.encs.concordia.ca/~hamza/logoENCS.jpg)

This Repository holds all Extended Eclipse Plugins and Sample Applications Source Code we used during the executions of the required Project part of Concordia SOEN 6611 Graduate Course

JDeodorantMetricsExtension
==========================
This is an eclipse Plugin based on the JDeodorant eclipse Plugin https://github.com/tsantalis/JDeodorant 
In this Plugin we are using the capabilities of JDeorodant to calucuate different Types of Metrics including QMOOD Metrics
It also calucates Quality Attributes like 
-Reusalbility
-Understandibility
-Flexibility

SampleProject 
==========================
We use this 5 classes  Project source code to validate eclipse plugins and tools we used and developed during the execution of our project

data-collection
==========================
This folder has the results of running the eclipse metric tool and other tools in different formats (XLS, CSV and XML)
1. Internal Metrics
	* graphs (this folder shows some essential external metric graphs) 
1. External Metrics including 
	* Number of Bugs per Version
	* Test Coverage per version
	* Number of commits
	* Number of files changed
	* graphs (this folder shows some essential internal metric graphs) 

1. Test Project Internal Metrics

How to Run
===========
1. Download this Github repository using eclipse http://www.eclipse.org/ or GitHub desktop https://desktop.github.com/
1. Import the Project as Eclipse Project (Environment details listed below)
	* eclipse Version: Oxygen.2 Release (4.7.2) Build id: 20171218-0600 
	* Java version 1.8.0_144
1. Run the JDeodorantMetricsExtension as an Eclipse Application
1. Import the SampleProject as a Java Project on the new eclipse window which will open once you execute step 3
1. Click on the Test Project and use the Metrics Menu to calcualte the Metrics

Have fun!
