/*
 * A class that represents the seats on an airplane
 * 
 * @author Fariha Ahmed
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Seats {
	private Passenger[][] economySeats, firstSeats;
	private ArrayList<Passenger> passengers;
	private ArrayList<Group> groups;
	
	/*
	 * Constructs the empty seats of an airplane
	 */
	public Seats() {
		this.economySeats = new Passenger[20][6];
		this.firstSeats  = new Passenger[2][4];
		this.passengers = new ArrayList<Passenger>();
		this.groups = new ArrayList<Group>();
	}
	
	/*
	 * Seats passenger in their preferred spot
	 * 
	 * @param p the passenger
	 * @return a boolean based on whether the passenger is able to sit in their preferred seat
	 */
	public boolean seatPassenger(Passenger p) {
		boolean seated = false;
		

		//Seat passenger in first class
		if(p.getServiceClass().equals("first")) {
			String sp = p.getSeatPreference();
			
			outerloop:
			for(int row = 0; row < firstSeats.length; row++) {
				for(int col = 0; col < firstSeats[0].length; col++) {
					//Seat passenger at window
					if(sp.equals("w")) {
						if((col == 0 || col == 3) && firstSeats[row][col] == null) {
							this.firstSeats[row][col] = p;
							seated = true;
							String seatNum = "" + (row + 1) + getChar(col);
							p.setSeat(seatNum);
							this.passengers.add(p);
							break outerloop;
						}
					}
					
					//Seat passenger at aisle
					if(sp.equals("a")) {
						if((col == 1 || col == 2) && firstSeats[row][col] == null) {
							this.firstSeats[row][col] = p;
							seated = true;
							String seatNum = "" + (row + 1) + getChar(col);
							p.setSeat(seatNum);
							this.passengers.add(p);
							break outerloop;
						}
					}
				}
			}	
		}
		
		//Seat passenger in economy class
		else if(p.getServiceClass().equals("economy")) {
			String sp = p.getSeatPreference();
			
			outerloop:
			for(int row = 0; row < economySeats.length; row++) {
				for(int col = 0; col < economySeats[0].length; col++) {
					//Seat passenger at window
					if(sp.equals("w")) {
						if((col == 0 || col == 5) && economySeats[row][col] == null) {
							this.economySeats[row][col] = p;
							seated = true;
							String seatNum = "" + (row + 1) + getChar(col);
							p.setSeat(seatNum);
							this.passengers.add(p);
							break outerloop;
						}
					}
					
					//Seat passenger at center
					if(sp.equals("c")) {
						if((col == 1 || col == 4) && economySeats[row][col] == null) {
							this.economySeats[row][col] = p;
							seated = true;
							String seatNum = "" + (row + 1) + getChar(col);
							p.setSeat(seatNum);
							this.passengers.add(p);
							break outerloop;
						}
					}
					
					//Seat passenger at aisle
					if(sp.equals("a")) {
						if((col == 2 || col == 3) && economySeats[row][col] == null) {
							this.economySeats[row][col] = p;
							seated = true;
							String seatNum = "" + (row + 1) + getChar(col);
							p.setSeat(seatNum);
							this.passengers.add(p);
							break outerloop;
						}
					}
				}
			}	
		}
		return seated;
	}
	
	/*
	 * Seats passenger in a group in their preferred service class
	 * 
	 * @param g the group
	 * @return a boolean based on whether the entire group is able to be seated
	 */
	public boolean seatGroup(Group g) {
		boolean seated = false;
		ArrayList<Passenger> groupPassengers = g.getPassengers();
		
		if(g.getServiceClass().toLowerCase().equals("first")) {
			ArrayList<ArrayList<String>> r = emptyRows(firstSeats);
			
			if(checkCapacity(g.getSize(),firstSeats) == false)
					seated = false;
			else {
				int s = g.getSize();
				int rowSize = 0;
				
				outerloop:
				for(int i = r.size() - 1; i >= 0; i--) {
					ArrayList<String> adjEmpty = r.get(i);
					for(int j = 0; j < adjEmpty.size(); j++) {
						Passenger p = groupPassengers.get(j + rowSize);
						String seatNum = adjEmpty.get(j);
						int[] seat = getSeat(seatNum);
						
						this.firstSeats[seat[0]][seat[1]] = p;
						p.setSeat(seatNum);
						s--;
						if(s == 0) {
							seated = true;
							break outerloop;
						}
					}
					rowSize += adjEmpty.size();
				}
				this.groups.add(g);
			}
		}
		
		else if(g.getServiceClass().toLowerCase().equals("economy")) {
			ArrayList<ArrayList<String>> r = emptyRows(economySeats);
			
			if(checkCapacity(g.getSize(),economySeats) == false)
					seated = false;
			else {
				int s = g.getSize();	
				int rowSize = 0;
				
				outerloop:
				for(int i = r.size() - 1; i >= 0; i--) {
					ArrayList<String> adjEmpty = r.get(i);
					for(int j = 0; j < adjEmpty.size(); j++) {
						Passenger p = groupPassengers.get(j + rowSize);
						String seatNum = adjEmpty.get(j);
						int[] seat = getSeat(seatNum);
						
						this.economySeats[seat[0]][seat[1]] = p;
						p.setSeat(seatNum);
						s--;
						if(s == 0) {
							seated = true;
							break outerloop;
						}
					}
					rowSize += adjEmpty.size();
				}
				this.groups.add(g);
			}
		}	
		return seated;
	}
	
	/*
	 * Seats passenger based on their seat number
	 * 
	 * @param p the passenger to be seated
	 * @param sc the service class to seat the passenger in
	 * @param sn the passenger's seat number
	 */
	public void seatPassengerBySeatNumber(Passenger p, String sc, String sn) {
		int[] seat = getSeat(sn);
		
		if(sc.equals("first")) {
			this.firstSeats[seat[0]][seat[1]] = p; 
			if(p.getGroupName().equals(""))
				this.passengers.add(p);
		}
		else if(sc.equals("economy")) {
			this.economySeats[seat[0]][seat[1]] = p; 
			if(p.getGroupName().equals(""))
				this.passengers.add(p);
		}
		
	}
	
	/*
	 * Adds a passenger to the system's list of groups
	 * 
	 * @param g the group to be added to the system's list of groups
	 */
	public void addPassenger(Passenger p) {
		this.passengers.add(p);
	}
	
	/*
	 * Adds a group to the system's list of groups
	 * 
	 * @param g the group to be added to the system's list of groups
	 */
	public void addGroup(Group g) {
		this.groups.add(g);
	}
	
	/*
	 * Cancels reservation for an individual
	 * 
	 * @param name the name of the individual whose reservation is to be cancelled
	 */
	public void cancelIndividual(String name) {
		for(int i = 0; i < this.passengers.size(); i++) {
			Passenger p = this.passengers.get(i);
			if(p.getName().toLowerCase().equals(name)) {
				if(p.getServiceClass().toLowerCase().equals("first")) {
					outerloop:
					for(int row = 0; row < this.firstSeats.length; row++) {
						for(int col = 0; col < this.firstSeats[0].length; col++) {
							Passenger temp = this.firstSeats[row][col];
							if(temp != null && temp.getName().toLowerCase().equals(name)) {
								this.firstSeats[row][col] = null;
								this.passengers.remove(p);
								break outerloop;
							}
						}
					}
				}
				
				else if(p.getServiceClass().toLowerCase().equals("economy")) {
					outerloop:
					for(int row = 0; row < this.economySeats.length; row++) {
						for(int col = 0; col < this.economySeats[0].length; col++) {
							Passenger temp = this.economySeats[row][col];
							if(temp != null && temp.getName().toLowerCase().equals(name)) {
								this.economySeats[row][col] = null;
								this.passengers.remove(p);
								break outerloop;
							}
						}
					}
				}
			}
		}
	}
	
	/*
	 * Cancels reservation for an entire group
	 * 
	 * @param name the name of the group whose reservations is to be cancelled
	 */
	public void cancelGroup(String name) {
		outerloop:
		for(int i = 0; i < this.groups.size(); i++) {
			Group g = this.groups.get(i);
			if(g.getName().toLowerCase().equals(name)) {
				if(g.getServiceClass().toLowerCase().equals("first")) {
					for(int row = 0; row < this.firstSeats.length; row++) {
						for(int col = 0; col < this.firstSeats[0].length; col++) {
							Passenger p = this.firstSeats[row][col];
							if(p != null && p.getGroupName().toLowerCase().equals(name)) {
								this.firstSeats[row][col] = null;
							}
						}
					}
				}
				
				else if(g.getServiceClass().toLowerCase().equals("economy")) {
					for(int row = 0; row < this.economySeats.length; row++) {
						for(int col = 0; col < this.economySeats[0].length; col++) {
							Passenger p = this.economySeats[row][col];
							if(p != null && p.getGroupName().toLowerCase().equals(name)) {
								this.economySeats[row][col] = null;
							}
						}
					}
				}
				this.groups.remove(g);
				break outerloop;
			}
		}
	}
	
	/*
	 * Returns the available seats in each row in the desired service class
	 * 
	 * @param sc the service class whose availability is to be viewed 
	 * @return a list of the available seats in each row of a service class
	 */
	public String availabilityChart(String sc) {
		String ac = "\n" + sc.toUpperCase() + ": ";
		
		Passenger[][] chart = new Passenger[20][6];
		if(sc.equals("economy"))
			chart = this.economySeats;
		else if(sc.equals("first"))
			chart = this.firstSeats;
		
		for(int row = 0; row < chart.length; row++) {
			ac += "\n" + (row + 1) + ":";
			for(int col = 0; col < chart[0].length; col++) {
				if(chart[row][col] == null)
					ac += " " + getChar(col);
 			}
		}
		return ac;
	}
	
	/*
	 * Returns the list of occupied seats and the passengers seated in them
	 * 
	 * @param sc the service class whose manifest is to be viewed
	 * @return a list of the occupied seats in a service class
	 */
	public String manifest(String sc) {
		String m = "\n" + sc.toUpperCase() + ": ";
		
		Passenger[][] chart = new Passenger[20][6];
		if(sc.equals("economy"))
			chart = this.economySeats;
		else if(sc.equals("first"))
			chart = this.firstSeats;
		
		for(int row = 0; row < chart.length; row++) {
			for(int col = 0; col < chart[0].length; col++) {
				if(chart[row][col] != null) {
					Passenger p = chart[row][col];
					m += "\n" + p.getSeat() + ": " + p.getName();
				}
			}
		}
		
		return m;
	}
	
	/*
	 * Returns the position of a given seat number
	 * 
	 * @param sn the string representing a seat number
	 * @return the row and column number of the given seat number
	 */
	public int[] getSeat(String sn) {
		int[] result = new int[2];
		
		String[] c = sn.split("");
		
		String[] sub = Arrays.copyOfRange(c, 0, c.length - 1);
		String s = "";
		for(int i = 0; i < sub.length; i++) {
			s += sub[i];
		}
		
		result[0] = Integer.parseInt(s) - 1;
		result[1] = c[c.length - 1].charAt(0) - 'A';
				
		return result;
	}
	
	/*
	 * Returns the list of individual passengers in the system
	 * 
	 * @return the system's passengers
	 */
	public ArrayList<Passenger> getPassengers(){
		return this.passengers;
	}
	
	/*
	 * Returns the list of groups in the system
	 * 
	 * @return the system's groups
	 */
	public ArrayList<Group> getGroups(){
		return this.groups;
	}
	
	/*
	 * Helper method to find the alphabetical equivalent of an integer
	 * 
	 * @param i an integer
	 * @return the alphabetical equivalent of an integer
	 */
	public static char getChar(int i) {
	    return i<0 || i>25 ? '?' : (char)('A' + i);
	}
	
	/*
	 * Helper method to sort through the empty seats of a seating chart
	 * 
	 * @param s a 2D array of seats
	 * @return a list containing the number of empty seats in a plane and a list of empty rows
	 */
	public static ArrayList<ArrayList<String>> emptyRows(Passenger[][] s) {
		ArrayList<ArrayList<String>> emptySeats = new ArrayList<ArrayList<String>>();
		
		//Gather all adjacent empty seats in a row
		ArrayList<String> adjSeats = new ArrayList<String>();
		
		for(int row = 0; row < s.length; row++) {
			for(int col = 0; col < s[0].length; col++) {
				if(s[row][col] == null) {
					String seatNum = "" + (row + 1) + getChar(col);
					adjSeats.add(seatNum);
					
					if(((col + 1) != s[0].length) && s[row][col + 1] != null) {
						emptySeats.add(adjSeats);
						adjSeats = new ArrayList<String>();
					}
				}
			}
			
			if(!adjSeats.isEmpty())
				emptySeats.add(adjSeats);
			adjSeats = new ArrayList<String>();
		}
		
		//Sort the list of empty seats in a row in ascending order
		for(int i = 0; i < emptySeats.size(); i++) {
			int min = i;
			ArrayList<String> m = emptySeats.get(min);
			for(int j = i + 1; j < emptySeats.size(); j++) {
				ArrayList<String> l = emptySeats.get(j);
				if(l.size() < m.size()) {
					min = j;
					m = emptySeats.get(min);
				}
			}
			ArrayList<String> temp = emptySeats.get(min);
			emptySeats.set(min, emptySeats.get(i));
			emptySeats.set(i, temp);
		}
		return emptySeats;
	}
	
	/*
	 * Helper method to check whether the amount of empty seats is enough to seat a group
	 * 
	 * @param gs the size of the group
	 * @param s the seats in a specific service class
	 */
	public static boolean checkCapacity(int gs, Passenger[][]s) {
		boolean enoughEmpty = false; 
		
		//Gather all empty seats in a row
		int totalEmpty = 0;
		for(int row = 0; row < s.length; row++) {
			for(int col = 0; col < s[0].length; col++) {
				if(s[row][col] == null) {
					totalEmpty++;
				}
			}
		}
		
		if(gs <= totalEmpty)
			enoughEmpty = true;
		
		return enoughEmpty;	
	}
}