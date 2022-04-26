# Airplane-Seat-Reservation-System

A command line argument reads the name of a file that holds information from a prior program run. 
If the file does not exist, the program creates one. 
The user interacts with the program to make a reservation, and the user input is read from standard input (System.in). 

The program now displays a MAIN MENU with following options: 
Add Passenger, Add Group, Cancel Reservations, Print Seating Availiability Chart, Print Manifest, Quit 

Select one of the following main menu options: Add [P]assenger, Add [G]roup, [C]ancel Reservations, Print Seating [A]vailability Chart, Print [M]anifest, [Q]uit

The user may enter one of the letters highlighted with a pair of the bracket to choose an option.
The main menu will be displayed after each option is done.

**Add [P]assenger**
When the user wants to add a passenger, the user is prompted:

Name: John Smith

Service Class: First

Seat Preference: W

Either the seat number of the passenger is printed, or an error message stating that the passenger cannot be seated.

**Add [G]roup**
When the user wants to add a group, the user is prompted: 

Group Name: cssjsu

Names: Venkathan Subramanian,Karl Schulz

Service Class: Economy

Either the seat numbers of the passengers are printed, or an error message stating that the passengers cannot be seated.

**[C]ancel Reservations**
The user is asked if the cancellation is for an [I]ndividual or a [G]roup. 
When the user wants to cancel an individual reservation, the user is prompted:

Cancel [I]ndividual or [G]roup? I

Names: John Smith

When the user wants to cancel a group reservation, the user is prompted: 

Group Name: cssjsu

A cancellation of a group reservation will cancel the reservations of all group members. 

**Print Seating [A]vailability Chart.**
The user is asked the desired service class. 
The program displays the seat availability chart of the service class only.

**Print [M]anifest**
The user is the desired service class. 
The program displays the manifest of the requested service class only.

**[Q]uit**
When the user quits, the reservations are saved in the text file whose name was given on the command line when the program starts. 
