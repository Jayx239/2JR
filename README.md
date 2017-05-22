# 2JR
An 802.15.4 Low Energy Implementation

Project Setup:
•	In order to access the project you must have a github account, you can get a free account at https://github.com/join?source=header-home 
•	2JR was developed on Intellij IDEA and it is highly recommended that this IDE be used for project setup. The community edition of Intellij IDEA can be downloaded from https://www.jetbrains.com/idea/#chooseYourEdition 
•	In order to clone the directory you must have git installed, you can use the Git Bash for this. Git bash can be downloaded from https://git-scm.com/downloads 
•	2JR relies on Java jdk 1.8. This can be found at http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html 

1.	Clone the repository to a directory of your choosing using the url: https://github.com/Jayx239/2JR.git
a.	The command line command to do this is: 
	git clone https://github.com/Jayx239/2JR.git

	2.	Open Intellij IDEA and open the project from the directory you cloned it in

3.	Upon completion you will see the project files on the left side of the screen. At this point the project library dependencies must be added.
	a.	Open project module settings
	b.	Select SDK
	c.	Select import directories and choose “lib” folder found in the project root directory
	d.	Click ok on the prompt, then apply in the module settings folder

	e.	Open the project settings tab on the left side under project settings
		i.	Project sdk = 1.8
		ii.	Project Language level = 8
		iii.	Create a new folder named “out” in the project root directory
		iv.	Set Project Compiler Output to the “out” folder
	
	f.	Exit project settings by clicking ok
	g.	Right click on src folder and set it as “Sources Root”
	h.	Optionally set the lib directory as “Resources Root”

4.	At this point the project should be completely configured. To test:
	a.	Right click on src
	b.	Click ‘Run All Tests’
		* Note, some tests will most likely fail as they depend on the xbee device configurations. These were hard coded in some and need to be updated for your specific device.
