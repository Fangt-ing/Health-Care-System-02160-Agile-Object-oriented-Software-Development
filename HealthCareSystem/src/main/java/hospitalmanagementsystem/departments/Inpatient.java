package hospitalmanagementsystem.departments;

import java.util.ArrayList;
import hospitalmanagementsystem.*;
import hospitalmanagementsystem.users.User;

/**
 * 
 */
public class Inpatient implements Department {
	// Static Variables
    private static Inpatient single_instance = null; 
    
    // Instance Variables
    private ArrayList<Bed> bedList;
    private ArrayList<User> userList;
    private ArrayList<Patient> patientList;
    
    /**
     * 
     */
    private Inpatient() {
    	patientList = new ArrayList<Patient>();
    	bedList = new ArrayList<Bed>();
    	userList = new ArrayList<User>();
    }
	
    /**
     * 
     * @return
     */
    public static Inpatient getInstance() 
    { 
        if (single_instance == null) {
            single_instance = new Inpatient();
        }
  
        return single_instance; 
    }

    /**
	 * 
	 * @param User
	 */
	public void addUser(User user) {
		userList.add(user);
	}

	/**
	 * 
	 * @param User
	 */
	public void removeUser(User user) {
		userList.remove(user);
	}

	/**
	 * 
	 * @param patient
	 */
	public void addPatient(Patient patient) {
		patientList.add(patient);
	}

	/**
	 * 
	 * @param patient
	 */
	public void removePatient(Patient patient) {
		patientList.remove(patient);
	}
	
	/**
	 * 
	 * @param bed
	 */
	public void addBed(Bed bed) {
		this.bedList.add(bed);
	}

	/**
	 * 
	 * @param bed
	 */
	public void removeBed(Bed bed) {
		this.bedList.remove(bed);
	}

	// GETTER METHODS
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return "Inpatient";
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Bed> getBedList(){
		return this.bedList;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Patient> getPatientList() {
		return this.patientList;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<User> getUserList() {
		return this.userList;
	}

	// SETTER  METHODS
	
	/**
	 * 
	 * @param beds
	 */
	public void setBedList(ArrayList<Bed> beds) {
		this.bedList = beds;
	}

	/**
	 * 
	 * @param patients
	 */
	public void setPatientList(ArrayList<Patient> patients) {
		this.patientList = patients;
	}

	/**
	 * 
	 * @param users
	 */
	public void setUserList(ArrayList<User> users) {
		this.userList = users;
	}
}
