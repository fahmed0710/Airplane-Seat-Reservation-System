/*
 * A tester class to test the Seats class
 * 
 * @author Fariha Ahmed
 * @version 1.0
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReservationSystem {
	/*
	 * A main method to test out the various methods of the Seats class
	 * 
	 * @param args a command-line argument representing the filename
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Seats seats = new Seats();
		
		//Read the name of a file that holds information from a prior program run
		String fName = args[0];
		File file = new File(fName);
		
		if(file.createNewFile()) {
			
		}
		else {
			 BufferedReader br = new BufferedReader(new FileReader(file));
		     ArrayList<String> fileContent = new ArrayList<String>();
		     String line = br.readLine();
		        
		     while(line != null) {
		    	 fileContent.add(line);
		       	 line = br.readLine();
		     }
		     br.close();
		     
		     for(int i = 0; i < fileContent.size(); i+= 2) {
		    	 String name = fileContent.get(i);
		    	 String[] otherInfo = fileContent.get(i + 1).split("[,:-]");
		    	 
		    	 //Individual passenger info
		    	 if(otherInfo.length == 3) {
		    		 String serviceClass = otherInfo[0].toLowerCase();
		    		 String seatPreference = otherInfo[1].toLowerCase();
		    		 String seatNum = otherInfo[2];
		    		 
		    		 Passenger p = new Passenger(name, serviceClass, seatPreference);
		    		 p.setSeat(seatNum);
		    		 
		    		 seats.seatPassengerBySeatNumber(p, serviceClass, seatNum);
		    	 }
		    	 //Group passenger info
		    	 else {
		    		 String serviceClass = otherInfo[0].toLowerCase();
		    		 String[] names = new String[otherInfo.length];
		    		 String[] seatNums = new String[otherInfo.length];
		    		 
		    		 for(int j = 1; j < otherInfo.length; j+=2) {
		    			 String n = otherInfo[j];
		    			 names[j - 1] = n;
		    			 
		    			 String seatNum = otherInfo[j + 1];
		    			 seatNums[j - 1] = seatNum;
		    			 
		    			 Passenger p = new Passenger(n, name);
		    			 p.setSeat(seatNum);
		    			
		    			 seats.seatPassengerBySeatNumber(p, serviceClass, seatNum);
		    		 } 
		    		 Group g = new Group(name, names, serviceClass);
		    		 for(int k = 0; k < g.getPassengers().size(); k++) {
		    			 Passenger p = g.getPassengers().get(k);
		    			 p.setSeat(seatNums[k]);
		    		 }
		    		 seats.addGroup(g);
		    	 }
		     }
		}
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nSelect one of the following main menu options: ");
		System.out.println("Add [P]assenger, Add [G]roup, [C]ancel Reservations, "
						 + "Print Seating [A]vailability Chart, Print [M]anifest, [Q]uit");
		
		while(sc.hasNextLine()) {
			String option = sc.nextLine().toLowerCase();
			
			//Add Passenger - make a singular reservation
			if(option.equals("p")){
				System.out.print("\nName: ");
				String name = sc.nextLine();
				
				System.out.print("\nService Class: ");
				String serviceClass = sc.nextLine().toLowerCase();
				
				System.out.print("\nSeat Preference: ");
				String seatPreference = sc.nextLine().toLowerCase();
				
				//Create new Passenger
				Passenger p = new Passenger(name, serviceClass, seatPreference);
				
				//Put Passenger in Seat chart
				if(seats.seatPassenger(p) == true)
					System.out.println("\nSUCCESS: Seat " + p.getSeat() + " booked for " + p.getName() + ".");
				else 
					System.out.println("\nREQUEST FAILED: There is no seat available with the given preferences.");
				
				System.out.println("\nSelect one of the following main menu options: ");
				System.out.println("Add [P]assenger, Add [G]roup, [C]ancel Reservations, "
								 + "Print Seating [A]vailability Chart, Print [M]anifest, [Q]uit");	
			}
			
			//Add Group - make a group reservation
			else if(option.equals("g")) {
				System.out.print("\nGroup Name: ");
				String groupName = sc.nextLine();
				
				System.out.print("\nNames: ");
				String[] names = sc.nextLine().split(",");
				
				int size = names.length;
				
				System.out.print("\nService Class: ");
				String serviceClass = sc.nextLine().toLowerCase();
				
				//Create new group
				Group g = new Group(groupName, names, serviceClass);
				
				//Put Group in Seat chart
				if(seats.seatGroup(g) == true) {
					System.out.println("\nSUCCESS: Seats booked for " + g.getName() + ".");
					ArrayList<Passenger> passengers = g.getPassengers();
					for(int i = 0; i < passengers.size(); i++) {
						Passenger p = passengers.get(i);
						System.out.println(p.getName() + ": " + p.getSeat());
					}
				}
				else 
					System.out.println("\nREQUEST FAILED: There are not enough seats available to accomodate group with the given preferences.");
					
				System.out.println("\nSelect one of the following main menu options: ");
				System.out.println("Add [P]assenger, Add [G]roup, [C]ancel Reservations, "
								 + "Print Seating [A]vailability Chart, Print [M]anifest, [Q]uit");
			}
			
			//Cancel Reservations
			else if(option.equals("c")) {
				System.out.print("\n[I]ndividual or [G]roup? ");
				String cMode = sc.next().toLowerCase();
				
				//Individual - cancel individual reservation
				if(cMode.equals("i")) {
					sc.nextLine();
					System.out.print("\nName: ");
					String name = sc.nextLine().toLowerCase();
					
					seats.cancelIndividual(name);
					
					System.out.println("\nReservation cancelled.");
					
					System.out.println("\nSelect one of the following main menu options: ");
					System.out.println("Add [P]assenger, Add [G]roup, [C]ancel Reservations, "
									 + "Print Seating [A]vailability Chart, Print [M]anifest, [Q]uit");
				}
				
				//Group - cancel group reservation
				else if(cMode.equals("g")) {
					sc.nextLine();
					System.out.print("\nGroup Name: ");
					String name = sc.nextLine().toLowerCase();
					
					seats.cancelGroup(name);
					
					System.out.println("\nReservation cancelled.");

					System.out.println("\nSelect one of the following main menu options: ");
					System.out.println("Add [P]assenger, Add [G]roup, [C]ancel Reservations, "
									 + "Print Seating [A]vailability Chart, Print [M]anifest, [Q]uit");
				}
			}
			
			//Print Seating Availability Class
			else if(option.equals("a")) {
				System.out.print("\nService Class: ");
				String aMode = sc.next().toLowerCase();
				
				System.out.println(seats.availabilityChart(aMode));
				System.out.println("\nSelect one of the following main menu options: ");
				System.out.println("Add [P]assenger, Add [G]roup, [C]ancel Reservations, "
								 + "Print Seating [A]vailability Chart, Print [M]anifest, [Q]uit");
			}
			
			//Print Manifest
			else if(option.equals("m")) {
				System.out.print("\nService Class: ");
				String aMode = sc.next().toLowerCase();
				
				System.out.println(seats.manifest(aMode));
				System.out.println("\nSelect one of the following main menu options: ");
				System.out.println("Add [P]assenger, Add [G]roup, [C]ancel Reservations, "
								 + "Print Seating [A]vailability Chart, Print [M]anifest, [Q]uit");
			}
			
			//Quit 
			else if(option.equals("q")) {
				FileWriter output = new FileWriter(file);
				
				//Writes the information of individual passengers
				for(int i = 0; i < seats.getPassengers().size(); i++) {
					Passenger p = seats.getPassengers().get(i);
					if(i == 0)
						output.write(p.getName());
					else
						output.write("\n" + p.getName());
					output.write("\n" + p.getServiceClass().toUpperCase() + "-" + p.getSeatPreference().toUpperCase() + "-" + p.getSeat());
				}
				
				//Writes the information of group passengers
				for(int i = 0; i < seats.getGroups().size(); i++) {
					Group g = seats.getGroups().get(i);
					output.write("\n" + g.getName());
					String s = "\n" + g.getServiceClass().toUpperCase() + ":";
					for(int j = 0; j < g.getPassengers().size(); j++) {
						Passenger p = g.getPassengers().get(j);
						if(j == g.getPassengers().size() -1)
							s += p.getName() + "-" + p.getSeat();
						else 
							s += p.getName() + "-" + p.getSeat() + ",";
					}
					output.write(s);
				}
				output.close();
				System.out.println("Goodbye!");
				System.exit(0);
			}
		}
	}
}
