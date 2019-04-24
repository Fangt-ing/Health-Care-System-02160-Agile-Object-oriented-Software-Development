package hospitalmanagementsystem.users;

import hospitalmanagementsystem.departments.*;

import java.util.Objects;

import hospitalmanagementsystem.*;

/**
 *
 * @author Pieter O'Hearn
 */
public class Admin extends User implements HealthStaff{
	// Static Variables
	static int idCounter;
	
	// Instance Variables
	Department department;
	final String adminID;

	/**
	 * Creates a new Admin of the Hospital Management
	 */
	public Admin(String usersName, String usersAddress, String phone) {
		// super
		super(usersName, usersAddress, phone);
		// set the department to Admin
		this.department = Management.getInstance();
		
		idCounter++;
		adminID = "A" + Integer.toString(idCounter);
	}

	/**
	 * Admits a given patient to a given department, updating the patients
	 * department variable and the departments patient list.
	 *
	 * @param patient The Patient that is being admitted
	 * @param department The department to admit the patient to
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public void admitPatient(Patient patient, Department department) throws IllegalAccessException, IllegalArgumentException {
		// if department is Management then throw an exception
		if(department instanceof Management) {
			throw new IllegalAccessException();
		} else if(!Objects.equals(patient.getPatientInfo().get("Department"), "None")) { 
			throw new IllegalArgumentException("Can not admit a patient who is already admitted to a department.");
		} else {
			// Update the patients department variable
			patient.updateDepartment(department);

			// Add the Patient to the departments patient list
			department.addPatient(patient);
		}
	}

	/**
	 * Discharges the given patient from their department, removing the patient
	 * from the Departments patient list and the patients department variable
	 *
	 * @param patient The Patient that is being discharged
	 */
	public void dischargePatient(Patient patient) throws IllegalArgumentException{
		// check if already checked out
		if(patient.getPatientInfo().get("Department").equals("None")) {
			throw new IllegalArgumentException("Can not discharge a patient who is not already admitted into any department.");
		} else {
			// Update the patients department variable
			patient.updateDepartment(null);
			
			// remove the Patient from the departments patient list
			department.removePatient(patient);
		}
	}

	/**
	 * Assigns the given Patient a Bed in the department, updating the patients Bed
	 * variable and the beds Patient variable.
	 *
	 * @param patient The Patient who is being assigned a Bed
	 * @return The Bed the patient is assigned to
	 * @throws IllegalAccessException 
	 */
	public Bed assignBed(Patient patient, Bed bed) throws IllegalAccessException {
		// Update the patients Bed variable
		patient.updateBed(bed);

		// Add the Patient to the beds patient variable
		bed.addPatient(patient);
		
		return bed;
	}

	/**
	 * Returns the medical data of the given patient as a String
	 *
	 * @param patient The Patient who's medical data is being requested
	 * @return A string of the patients medical data
	 */
	public String getMedicalData(Patient patient){
		// request the updated record and return it
		return patient.getRecord();
	}

	/**
	 * This method updates the given Patients medical record. The data String
	 * is appended to the Paitent's medical data variable.
	 *
	 * @param patient The Patient who's medical data is being edited
	 * @param data The new medical data
	 * @return A string of the patients updated medical data
	 */
	public String editMedicalData(Patient patient, String data){
		// append the data to the patients medical data
		patient.updateRecord(data);

		// request the updated record and return it
		return patient.getRecord();
	}

	/**
	 * Creates a New User and adds it to the Hospital Management System.
	 *
	 * @param usersName The name of the User
	 * @param usersAddress the Address of the User
	 * @param phone The Phone Number of the User
	 * @return The new User
	 */
	public User addUser(String usersName, String usersAddress, String phone, String typeOfUser) {
		// create a new user variable and set to null
		User newUser = null;

		// check what typeOfUser
		if(typeOfUser.toLowerCase().equals("admin")) {
			// create a new Admin
			Admin newAdmin = new Admin(usersName, usersAddress, phone);
			newUser = newAdmin;
		} else if(typeOfUser.toLowerCase().equals("doctor")) {
			// create a new Doctor
			Doctor newDoctor = new Doctor(usersName, usersAddress, phone,"Emergency");
			newUser = newDoctor;
		} else if(typeOfUser.toLowerCase().equals("nurse")) {
			// create a new Nurse
			Nurse newNurse = new Nurse(usersName, usersAddress, phone,"Emergency");
			newUser = newNurse;
		} else if(typeOfUser.toLowerCase().equals("user")) {
			// create a new User
			newUser = new User(usersName, usersAddress, phone);
		}
		return newUser;
	}

	/**
	 * Removes a User from the Hospital Management System (HMS).
	 *
	 * @param oldUser The user to be removed from the HMS
	 */
	public void removeUser(User oldUser) {

	}

	public Department getDepartment() {
		return this.department;
	}
}
