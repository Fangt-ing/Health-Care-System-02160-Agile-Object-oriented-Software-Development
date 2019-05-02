package hospitalmanagementsystem.users;

import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.PersistenceLayer;
import hospitalmanagementsystem.departments.Management;

/**
 * A User of the Hospital Management System. They have the lowest privileges of
 * all the users but can still see a Users basic information and register the
 * patient into the Hospital Management System.
 *
 * @author Pieter O'Hearn
 */
public class User {
	// Static Variables
	private static PersistenceLayer persist = new PersistenceLayer();
//	public static int idCounter = 0;

	// Instance Variables
	protected String name;
	protected String userID;
	protected String email;
	protected String phoneNumber;
	private String department;

	/**
	 * Creates a new User of the Hospital Management System
	 * @param usersName
	 * @param phone
	 */
	public User(String usersName,  String phone){
		// assign the User with a unique ID
		int idCounter = persist.loadCounter() + 1;
		this.userID = "U" + Integer.toString(idCounter);
		// assign the remaining information
		this.name = usersName;
		this.email = this.userID + "@kapjak.com";
		this.phoneNumber = phone;
		this.department = Management.getInstance().getName();
		Management.getInstance().getUserList().add(this);

		// Save the new User
		persist.save(this, this.userID, this.department);
		persist.saveCounter(idCounter);
	}

	public User() {}

	/**
	 * Creates a new non generic type ofUser to the Hospital Management System
	 * @param usersName
	 * @param phone
	 * @param classString
	 */
	public User(String usersName, String phone, String classString) {
		// assign the User with a unique ID
		int idCounter = persist.loadCounter() + 1;
		this.userID = classString + Integer.toString(idCounter);
		persist.saveCounter(idCounter);
		
		// assign the remaining information
		this.name = usersName;
		this.email = this.userID + "@kapjak.com";
		this.phoneNumber = phone;
		this.email = this.userID + "@kapjak.com";
	}

	/**
	 * This method registers a new Patient into the hospital management system
	 * and returns that Patient.
	 *
	 * @return The Patient Object that has been registered to the HMS
	 */
	public Patient registerPatient(String name, String surname, String bday, String address, String phoneNo) {
		// create a new Patient
		Patient newPatient = new Patient(name, surname, bday, address, phoneNo);

		// Save the updated User
		persist.save(this, this.userID, this.department);

		// return the Patient
		return newPatient;
	}


	public String getType() {
		return "User";
	}
	
	public void editUser(String newName, String newPhone) {
		// set the users
		this.setUserName(newName);
		this.setNumber(newPhone);
		
		// Save the updated User
		persist.delete(this.userID, this.getDepartment());
		persist.save(this, this.userID, this.getDepartment());
	}

	// Gets
	public String getUserName() {
		return this.name;
	}

	public String getUserID() {
		return this.userID;
	}

	public String getEmail() {
		return this.email;
	}

	public String getNumber() {
		return this.phoneNumber;
	}

	public String getDepartment() {
		return this.department;
	}

	// Sets
	public void setUserName(String newName) {
		this.name = newName;
	}

	public void setUserID(String newID) {
		this.userID = newID;
	}

	public void setEmail(String newEmail) {
		this.email = newEmail;
	}

	public void setNumber(String newPhone) {
		this.phoneNumber = newPhone;
	}

	public void setDepartment(String newDepartment) {
		this.department = newDepartment;
	}
}
