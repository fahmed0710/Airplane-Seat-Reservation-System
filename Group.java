/*
 * A class that represents a group of passengers
 * 
 * @author Fariha Ahmed
 * @version 1.0
 */

import java.util.ArrayList;

public class Group {
	private String groupName, serviceClass;
	private Integer size;
	private ArrayList<Passenger> passengers;
	
	/*
	 * Constructor for a group of passengers
	 * 
	 * @param n the group's name
	 * @param names the names of the passengers in the group
	 * @param sc the group's preferred service class
	 */
	public Group(String n, String[] names, String sc) {
		this.groupName = n;
		this.serviceClass = sc;
		this.passengers = new ArrayList<Passenger>();
		for(int i = 0; i < names.length; i++) {
			Passenger p = new Passenger(names[i],this.groupName);
			this.passengers.add(p);
		}
		this.size = names.length;
	}
	
	/*
	 * Returns the name of the group
	 * 
	 * @return the group's name
	 */
	public String getName(){
		return this.groupName;
	}
	
	/*
	 * Returns the passengers in a group
	 * 
	 * @return the group's passengers
	 */
	public ArrayList<Passenger> getPassengers(){
		return this.passengers;
	}
	
	/*
	 * Returns the service class of the passenger
	 * 
	 * @return the passenger's service class
	 */
	public String getServiceClass() {
		return this.serviceClass;
	}
	
	/*
	 * Returns the size of a group
	 * 
	 * @return the group's size
	 */
	public Integer getSize(){
		return this.size;
	}
}