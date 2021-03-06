package hospitalmanagementsystem;

import hospitalmanagementsystem.departments.Department;
import hospitalmanagementsystem.departments.Emergency;
import hospitalmanagementsystem.departments.Inpatient;

/**
 * The Patient Class represents a Patient in the Hospital.
 * @author Jack Rodman
 */
public class Patient {
	// STATIC VARIABLES
	private static PersistenceLayer persist = new PersistenceLayer();

	// INSTANCE VARIABLES
	String name;
	String surname;
	String patientID;
	String bday;
	String address;
	String phoneNo;
	String deceased;
	String record;
	String dept;
	String bed;

	// CONSTRUCTORS
	public Patient(String name, String surname, String bday, String address, String phoneNo) {
		//patient info input by user
		this.name = name;
		this.surname = surname;
		int idCounter = persist.loadCounter() + 1;
		this.patientID = "P" + Integer.toString(idCounter);;
		persist.saveCounter(idCounter);
		this.bday = bday;
		this.address = address;
		this.phoneNo = phoneNo;
		this.deceased = "false";
		this.record = null;
		this.dept = null;
		this.bed = null;
		
		persist.save(this, this.patientID, this.dept);
	}

	/**
	 * A Blank COnstructor for the Persistence Layer
	 */
	public Patient() {}
	
	// METHODS
	/**
	 * Updates a patient's department to enable admitting or moving a patient to a new department
	 * @param department
	 * @throws IllegalArgumentException
	 */
	public void updateDepartment(Department department) throws IllegalArgumentException {
		// check if department is Management
		if(department != null && department.getName().equals("Management")) {
			throw new IllegalArgumentException("Patients can not be assigned to the Managment Department");
		} else { 
			// Delete from current department
			persist.delete(this.patientID, this.dept);
			
			// check if the patient is in a bed
			if(this.bed != null && (this.dept.equals("Emergency") || this.dept.equals("Inpatient"))) {
				// if they are remove the patient from the bed
				for(Bed bed : (this.dept.equals("Emergency")) ? Emergency.getInstance().getBedList():Inpatient.getInstance().getBedList()) {
					if(bed.getBedID().equals(this.bed)) {
						bed.setPatient(null);
						this.bed = null;
						break;
					}
				}
			}
			
			// update the department
			this.dept = (department==null) ? null:department.getName();
			
			// Save the new Patient
			persist.save(this, this.patientID, this.dept);
		}
	}

	// GETTER METHODS
	public String getFirstName() {
		return this.name;
	}

	public String getLastName() {
		return this.surname;
	}

	public String getPatientID() {
		return this.patientID;
	}

	public String getDOB() {
		return this.bday;
	}

	public String getAddress() {
		return this.address;
	}
	
	public String getPhoneNo() {
		return this.phoneNo;
	}
	
	public String getDeceased() {
		return this.deceased;
	}
	
	public String getRecord() {
		return this.record;
	}
	
	public String getDepartment() {
		return this.dept;
	}
	
	public String getBed() {
		return this.bed;
	}

	// SETTER METHODS
	public void setFirstName(String firstName) {
		this.name = firstName;
	}

	public void setLastName(String lastName) {
		this.surname = lastName;
	}

	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}
	
	public void setDOB(String dOB) {
		this.bday = dOB;
		
	}
	
	public void setAddress(String newAddress) {
		this.address = newAddress;
	}
	
	public void setPhoneNo(String phone) {
		this.phoneNo = phone;
	}

	public void setDeceased(String deceased) {
		this.deceased = deceased;
	}
	
	/**
	 * Appends the given data to the end of the current Record String with
	 * a new Line character separating the old and new data.
	 * @param data
	 */
	public void setRecord(String data) {
		if(this.record != null) {
			this.record = this.record + "\n" + data;
		} else {
			this.record = data;
		}
	}
	
	public void setDepartment(String department) {
		this.dept = department;
	}

	public void setBed(String bedID) {
		this.bed = bedID;
	}
}