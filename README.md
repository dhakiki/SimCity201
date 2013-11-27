##SimCity Project Repository

# TEAM POWER RANGERS
![alt text](http://in10words.files.wordpress.com/2013/08/mmpr_rangers.jpg "Team Power Ranger")


###Team Information
  + Doreen Hakimi
  + Linda Char
  + Kimberly Santiago
  + Drew Appleman
  + Tiffany Tran
  + Brian Nguyen 
  
###Anything missing or implemented incorrectly?
	+ Everything is implemented
	+ Semaphores are added
	+ Everything should work! Sometimes a waiter will get stuck getting an order, but I've only seen that when I tested many customers/waiters.


###How To Compile and Run
	+ Download files and open onto eclipse, then press 'run'!
	+ NOTE 1: In order to add customers properly, MUST be entered in the following way:
	+ 		Name, Money, FoodChoice, Stay/Leave 
	+		(Stay/Leave will determine whether they stay or leave if restaurant runs out of choice, or they can't afford anything on the menu, as well as whether they'll Stay or Leave if the restaurant is full)
	+			Is disregarded in the commit for Issue #2 (Scenario 1), just enter it anyway.
	+		FoodChoice is either "Steak", "Chicken", "Pizza", "Salad"
	+		EXAMPLE: Doreen, 23.55, Steak, Leave
	+ NOTE 2: Initializing kitchen threshold values/current inventory values/market values MUST be set at first.
	+		Can increase/decrease as you like, once happy with numbers hit 'SET' so cook can check if restaurant is ready for open
	

	For our v1 SimCity deliverable, each of our agents and roles work independently of eachother and this is evidenced by our working Unit Tests. 
	However, with the addition of the PersonAgent to unify all of our roles and agents together, some complications occurred during integration. 
	Our separate working roles and agents were unable to operate well in the whole city. 
	Approaching v2, Team 38 is well aware of the improvements that are needed in the Person Agent's scheduler in order to have a cohesive and correct v2.  
	As of now, our tests are correct and are representative of working agents. 
	In the SimCityView, Time only goes up to 7am, however time is correctly assigned to each person and they receive their jobs.  
	This is through a parsedReadIn configuration file which assigns the jobs to each person as well as other characteristics. The names are updated in the panel as well.  
	
	Doreen Hakimi:
	+City View Gui and Panel
	+File I/O
	+Card Layout
	+ Design+Implementation BankManager
	+ Design+Implementation Bank Loan Officer
	+ Shared Data of Restaurant (rotating wheel)
	
	Kimberly:
	+Design+Implementation Market Inventory Boy
	+Design+Implementation Market Customer
	+Contributed to other Market Agents 
	+Collaborated on PersonAgent
	+Started Shared Data on Restaurants
	+Fixed Market/Cashier Interactions
	+Shared Data of Restaurant (rotating wheel)
	+Unit Test Market Inventory Boy
	+Unit Test Market Customer

	
	Linda:
	+Design+Implementation MarketCashier 
	+Design+Implementation Delivery Truck
	+Updated JUnit corrections and errors
	+Design+Implementation MarketManager
	+Shared Data in Restaurant (rotating wheel)
	+Unit Test MarketCashier 
	+Unit Test Delivery Truck
	+Updated JUnit corrections and errors
	+Unit Tested MarketManager

	Brian:
	+Design+Implementation Bus Agent
	+Design+Implementation BusStop Agent
	+Design+Implementation Car Agent
	+Worked on the Transportation Scheduler of the PersonAgent
	+Helped Unit Testing of LandLord
	+Unit Testing of BusStopAgent
	+Unit Testing of Car Agent
	+Unit Testing of Bus Agent
	+Shared Data of Restaurant (rotating wheel)
	+Help Unit Test Person
	
	Tiffany:
	+Design+Implementation Landlord 
	+Design+Implementation Repairman 
	+Unit Testing of Landlord
	+Unit Testing of Repairman 
	+Shared Data of Restaurant
	
	
	Drew:
	+Design+Implementation BankTeller
	+Design+Implementation LoanOfficer
	+Design+Implementation BankRobber
	+Design+Implementation BankCustomer
	+Unit Tested BankTeller
	+Unit Tested BankRobber
	+Unit Tested BankCustomer
	+Worked on Scheduler of the PersonAgent
	+Help Unit Test Person
	
	
	
	
	
	
	
###Resources
  + [Restaurant v1](http://www-scf.usc.edu/~csci201/readings/restaurant-v1.html)
  + [Agent Roadmap](http://www-scf.usc.edu/~csci201/readings/agent-roadmap.html)
