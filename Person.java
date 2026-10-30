package phase1;

import java.util.*;

public abstract class Person implements IPerson {
	private String name;
	private int id;
	private String PhoneNumber;
	private LinkedList<IRide> rideHistory;

	public Person(int id, String name, String PhoneNumber) {
		setName(name);
		setPhoneNumber(PhoneNumber);
		this.id = id;
		rideHistory = new LinkedList<IRide>();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPhoneNumber(String PhoneNumber) {
		if ((PhoneNumber == null || !PhoneNumber.matches("[0-9]{10}")))
			throw new IllegalArgumentException();
		this.PhoneNumber = PhoneNumber;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public int getId() {
		return id;
	}
	
	public LinkedList<IRide> getRideHistory(){
		return rideHistory;
	}
	
	public String toString() {
		String s = "Name: "+name + ", id:"+id+", Phone Number: "+PhoneNumber;
		return s;
	}

}
