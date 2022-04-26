/*
 * A class that represents a single passenger
 * 
 * @author Fariha Ahmed
 * @version 1.0
 */

import java.util.ArrayList;

public class Passenger {
	private String name, groupName, serviceClass, seatPreference, seat;
	
	/*
	 * Constructor for a single passenger
	 * 
	 * @param n the passenger's name
	 * @param sc the passenger's preferred service class
	 * @param sp the passenger's preferred seat location
	 */
	public Passenger(String n, String sc, String sp) {
		this.name = n;
		this.serviceClass = sc;
		this.seatPreference = sp;
		this.groupName = "";
	}
	
	/*
	 * Constructor for a single passenger (within a group)
	 * 
	 * @param n the passenger's name
	 * @param gn the group the passenger is a part of
	 */
	public Passenger(String n, String gn) {
		this.name = n;
		this.groupName = gn;
	}
	
	/*
	 * Returns the name of the passenger
	 * 
	 * @return the passenger's name
	 */
	public String getName() {
		return this.name;
	}
	
	/*
	 * Returns the name of the group
	 * 
	 * @return the group's name
	 */
	public String getGroupName() {
		return this.groupName;
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
	 * Returns the seat preference of the passenger
	 * 
	 * @return the passenger's seat preference
	 */
	public String getSeatPreference() {
		return this.seatPreference;
	}
	
	/*
	 * Sets the passenger at the given seat
	 * 
	 * @param s the seat that will be reserved for the passenger
	 */
	public void setSeat(String s) {
		this.seat = s;
	}
	
	/*
	 * Gets the reserved seat of the passenger
	 * 
	 * @return the passenger's reserved seat
	 */
	public String getSeat() {
		return this.seat;
	}
}