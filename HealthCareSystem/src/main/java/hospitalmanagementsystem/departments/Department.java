package hospitalmanagementsystem.departments;

import java.util.ArrayList;

import hospitalmanagementsystem.Bed;
import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.users.*;

/**
 * 
 * @author Kun
 *
 */

public abstract class Department {
	String name;
	ArrayList<Bed> bedList;
	ArrayList<User> userList;
	ArrayList<Patient> patientList;

	public abstract void addUser(User User);

	public void removeUser(User User) {
		userList.remove(User);
	}

	public abstract void addPatient(Patient patient);

	public abstract void removePatient(Patient patient);
	

	//Getters
	public abstract String getName();
	
	public abstract ArrayList<Bed> getBedList();
	
	public abstract ArrayList<Patient> getPatientList();
	
	public abstract ArrayList<User> getUserList();
	
	//Setters
	public abstract void setName(String Name);
	
	public abstract void setBedList(ArrayList<Bed> beds);
	
	public abstract void setPatientList(ArrayList<Patient> patients);
	
	public abstract void setUserList(ArrayList<User> users);
	
	

	
	
}
