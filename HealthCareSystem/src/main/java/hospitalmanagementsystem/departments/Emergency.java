package hospitalmanagementsystem.departments;

import java.util.ArrayList;
import hospitalmanagementsystem.*;
import hospitalmanagementsystem.users.User;

/**
 * A Singleton Class Which represents the Emergency Department at the Hospital.
 * @author Karoline Østergaard
 * @author Pieter O'Hearn
 */
public class Emergency implements Department {
	// Static Variables
    private static Emergency single_instance = null; 
    
    // Instance Variables
    private ArrayList<Bed> bedList;
    private ArrayList<User> userList;
    private ArrayList<Patient> patientList;
	
    /**
     * The Private Constructor for the Emergency class.
     */
    private Emergency() {
    	patientList = new ArrayList<Patient>();
    	userList = new ArrayList<User>();
    	bedList = new ArrayList<Bed>();
    }
	
    /**
     * Returns the saved instance of the Emergency Class or creates a one if one has not yet been created.
     * @return The Emergency Instance
     */
    public static Emergency getInstance() 
    { 
        if (single_instance == null) {
            single_instance = new Emergency();
        }
  
        return single_instance; 
    }

    /**
	 * Adds a User to the Department.
	 * @param User The User to add
	 */
	public void addUser(User user) {
		userList.add(user);
	}

	/**
	 * Removes a user from the Department.
	 * @param User The user to remove
	 */
	public void removeUser(User user) {
		userList.remove(user);
	}

	/**
	 * Adds a Patient to the Department.
	 * @param patient The patient to add
	 */
	public void addPatient(Patient patient) {
		patientList.add(patient);
	}

	/**
	 * Removes a Patient to the Department.
	 * @param patient The patient to remove
	 */
	public void removePatient(Patient patient) {
		patientList.remove(patient);
	}
	
	/**
	 * Adds a Bed to the Department.
	 * @param bed The bed to add
	 */
	public void addBed(Bed bed) {
		this.bedList.add(bed);
	}

	/**
	 * Removes a Bed to the Department.
	 * @param bed The bed to remove
	 */
	public void removeBed(Bed bed) {
		this.bedList.remove(bed);
	}

	// GETTER METHODS
	public String getName() {
		return "Emergency";
	}
	
	public ArrayList<Bed> getBedList(){
		return this.bedList;
	}
	
	public ArrayList<Patient> getPatientList() {
		return this.patientList;
	}
	
	public ArrayList<User> getUserList() {
		return this.userList;
	}

	// SETTER  METHODS
	public void setBedList(ArrayList<Bed> beds) {
		this.bedList = beds;
	}

	public void setPatientList(ArrayList<Patient> patients) {
		this.patientList = patients;
	}

	public void setUserList(ArrayList<User> users) {
		this.userList = users;
	}
}