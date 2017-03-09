This project was built using IntelliJ IDEA :

IntelliJ IDEA 2016.3.5
Build #IC-163.13906.18, built on March 6, 2017
JRE: 1.8.0_112-release-408-b6 x86
JVM: OpenJDK Server VM by JetBrains s.r.o

All inputs are considered to be read as JSON as per specification. As seen in the sample txt files, let each line be some hand. If that hand is invalid for whatever reason, based on the function of the application one is trying to run or if any cards are repeating, the program will skip that line. Each line is considered a hand.

In order to execute the program, navigate to poker/artifacts/poker_jar and run the jar file as follows:
	1. Determine the rank of a 5 Card Hand
		java -jar poker.jar determine.txt determine

		Given a list of card hands, return the corresponding ranking for each card. If a hand is invalid, an error is printed out to console to notify of the error.

	2. Determine the best 5 Card Hand
		java -jar poker.jar besthand.txt compare

		Given a list of card hands, returns a list of given valid card hands ranked best to worst

	3. Determine the best hand made from a set of cards
		java -jar poker.jar besthand.txt besthand

		Given a list of card hands, returns a list of the best card hand that can be made with a valid input.

	For above, the input file can be named whatever, as long as path/name is specified correctly from the command line.
	