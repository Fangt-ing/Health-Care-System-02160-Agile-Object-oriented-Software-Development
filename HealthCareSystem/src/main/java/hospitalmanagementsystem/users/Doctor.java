package hospitalmanagementsystem.users;

import hospitalmanagementsystem.departments.*;
import java.util.Objects;
import hospitalmanagementsystem.*;

/**
 *
 * @author jackrodman
 *
 */
public class Doctor extends User implements HealthStaff{
	//INSTANCE VARIABLES
	private String department;
	private PersistenceLayer persist = new PersistenceLayer();;

	public Doctor(String usersName, String phone, String department) {
		super(usersName, phone, "D");
		
		//assign department based on input
		switch(department==null?"null":department) {
		  case "Emergency":
			  this.department = department;
			  Emergency.getInstance().addUser(this);
		    break;
		  case "Inpatient":
			  this.department = department;
			  Inpatient.getInstance().addUser(this);
		    break;
		  case "Outpatient":
			  this.department = department;
			  Outpatient.getInstance().addUser(this);
		    break;
		  case "null":
			  this.department = null;
			  System.out.println("null department");
		    break;
		  default:
		    throw new IllegalArgumentException(String.format("%s is an invalid department.",department));
		}

		// Save the new User
		persist.save(this, this.getUserID(), this.department);
	}

	public Doctor() {}
	
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
			throw new IllegalAccessException("Can not admit a patient to the Management department.");
		} else if(!Objects.equals(patient.getDepartment(), null)) {
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
		if(patient.getDepartment().equals("None")) {
			throw new IllegalArgumentException("Can not discharge a patient who is not already admitted into any department.");
		} else {
			// remove the Patient from the departments patient list
			switch(patient.getDepartment()) {
				case "Emergency":
					Emergency.getInstance().removePatient(patient);
				case "Inpatient":
					Inpatient.getInstance().removePatient(patient);
				case "Outpatient":
					Outpatient.getInstance().removePatient(patient);
			}

			// Update the patients department variable
			patient.updateDepartment(null);
		}
	}

	/**
	 * Assigns the given Patient a Bed in the department, updating the patients Bed
	 * variable and the beds Patient variable.
	 *
	 * @param patient The Patient who is being assigned a Bed
	 * @return The Bed the patient is assigned to
	 */
	public Bed assignBed(Patient patient, Bed bed) throws IllegalAccessException{
		// Update the patients Bed variable
		patient.updateBed(bed);

		// Add the Patient to the beds patient variable
		bed.addPatient(patient);

		return bed;
	}

	/**
	 * Returns the medical data of the given patient as a String
	 *
	 * @param patient The Patient whose medical data is being requested
	 * @return A string of the patients medical data
	 */
	public String getMedicalData(Patient patient) {
		// request the updated record and return it
		return patient.getRecord();
	}

	/**
	 * This method updates the given Patients medical record. The data String
	 * is appended to the Patients medical data variable.
	 *
	 * @param patient The Patient whose medical data is being edited
	 * @param data The new medical data
	 * @return A string of the patients updated medical data
	 */
	public String editMedicalData(Patient patient, String data) {
		// append the data to the patients medical data
		patient.setRecord(data);

		// request the updated record and return it
		return patient.getRecord();
	}

	@Override
	public String getType() {
		return "Doctor";
	}
	
	@Override
	public String getDepartment() {
		return this.department;
	}
	
	@Override
	public void setDepartment(String newDepartment) {
		this.department = newDepartment;
	}

	public void moveDepartment(String department) {
		// change department
		if(this.department != null) {
			switch(this.department) {
				case "Emergency":
					Emergency.getInstance().getUserList().remove(this);
				case "Outpatient":
					Outpatient.getInstance().getUserList().remove(this);
				case "Inpatient":
					Inpatient.getInstance().getUserList().remove(this);
			}
		}

		// delete the xml user file from the current department
		persist.delete(this.userID, this.department);

		// change the department
		this.department = department;

		// add the user to the new department list
		switch(department) {
			case "Emergency":
				Emergency.getInstance().getUserList().add(this);
			case "Outpatient":
				Outpatient.getInstance().getUserList().add(this);
			case "Inpatient":
				Inpatient.getInstance().getUserList().add(this);
		}

		// Save the updated User
		persist.save(this, this.userID, this.department);
	}
}
