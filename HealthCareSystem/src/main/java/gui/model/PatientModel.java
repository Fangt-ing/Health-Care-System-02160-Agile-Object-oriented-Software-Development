package gui.model;

import java.util.ArrayList;
import java.util.Objects;
import javax.swing.table.AbstractTableModel;
import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.departments.*;
import hospitalmanagementsystem.users.*;

/**
 * The Model for the Patients Table. 
 * @author Pieter O'Hearn
 *
 */
public class PatientModel extends AbstractTableModel {
	// instance variables
	private static final long serialVersionUID = -8100080945056395023L;
	private ArrayList<Patient> patients;
	
	/**
	 * Constructor of the PatientModel. Creates an array of patients loaded from memory.
	 */
	public PatientModel() {
		// create a new PersistenceLayer and load the Patients TODO
//		PersistenceLayer persistenceLayer = new PersistenceLayer();
//		persistenceLayer.load("Departments/Emergency/Patients");
//		persistenceLayer.load("Departments/Inpatient/Patients");
//		persistenceLayer.load("Departments/Outpatient/Patients");
//		persistenceLayer.load("Departments/Management/Patients");
		
		// create a new ArrayList of patients
		patients = new ArrayList<Patient>();
		
		// add any saved patients into the ArrayList
		patients.addAll(Emergency.getInstance().getPatientList());
		patients.addAll(Inpatient.getInstance().getPatientList());
		patients.addAll(Outpatient.getInstance().getPatientList());
	}
	
	/**
	 * Adds a new patient to the HMS.
	 * @param newPatient The new Patient
	 */
	public void addNewPatient(Patient newPatient) {
		// add the new Patient to the Patient List
		patients.add(newPatient);
		
		// TODO Where should we save patient that are not in a department
		
		// notify the views that data changed
		fireTableDataChanged();
	}
	
	/**
	 * Discharges a patient from their current department.
	 * @param patientId The ID of the Patient
	 * @param user The User discharging the patient
	 */
	public void dischargePatient(String patientId, HealthStaff  user) {
		// find the patient with that PatientID
		Patient toDischarge = findPatient(patientId);
		
		// try and discharge patient from their department
		try {
			user.dischargePatient(toDischarge);
		} catch (IllegalAccessException e) {
			// User does not have access to perform this 
			e.printStackTrace();
		}
		
		// notify the views that data changed
		fireTableDataChanged();
	}

	/**
	 * Admits a patient to a department
	 * @param patientID The patients ID
	 * @param user The user admitting the patient
	 * @param dept The department
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public void admitPatient(String patientID, HealthStaff user, Department dept) throws IllegalAccessException, IllegalArgumentException {
		// find the patient and admit them to the department
		Patient toAdmit = findPatient(patientID);
		user.admitPatient(toAdmit, dept);
	}
	
	/**
	 * Finds the patient with the given patient ID
	 * @param patientId The patients ID
	 * @return The Patient with the given ID
	 */
	public Patient findPatient(String patientId) {
		// search through each patient in the List
		for(Patient p : patients) {
			// if a match is found return that patient
			if(p.getpatientID().equals(patientId)) {
				return p;
			}
		}
		
		// else return null
		return null;
	}

	/**
	 * Edits the patients information
	 * @param patientId
	 * @param firstName
	 * @param lastName
	 * @param dOB
	 * @param address
	 * @param phone
	 */
	public void edit(String patientId, String firstName, String lastName, String dOB, String address, String phone) {
		// find the user with the corresponding patient id
		Patient toEdit = findPatient(patientId);
		
		// set the patients information to that provided by the arguments
		toEdit.setFirstName(firstName);
		toEdit.setLastName(lastName);
		toEdit.setPhoneNo(phone);
		toEdit.setAddress(address);
		toEdit.setPhoneNo(phone);
		toEdit.setDOB(dOB);
		
		// notify the views that data changed
		fireTableDataChanged();
	}
	
	/////////////////////////////////////////
	// METHODS BELOW TO EXTEND TABLE MODEL //
	/////////////////////////////////////////
	
	@Override
	public int getColumnCount() {
		// this is fixed: product and quantity
		return 9;
	}

	@Override
	public int getRowCount() {
		return patients.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return patients.get(rowIndex).getpatientID();
		} else if (columnIndex == 1) {
			return patients.get(rowIndex).getFirstName();
		} else if (columnIndex == 2) {
			return patients.get(rowIndex).getLastName();
		} else if (columnIndex == 3) {
			if(Objects.equals(null,patients.get(rowIndex).getDepartment())) {
				return "-";
			}
			return patients.get(rowIndex).getDepartment();
		} else if (columnIndex == 4) {
			return patients.get(rowIndex).getNumber();
		} else if (columnIndex == 5) {
			return patients.get(rowIndex).getAddress();
		} else if (columnIndex == 6) {
			return patients.get(rowIndex).getDOB();
		} else if (columnIndex == 7) {
			return patients.get(rowIndex).getDeceased();
		} else if (columnIndex == 8) {
			if(Objects.equals(null,patients.get(rowIndex).getBed())) {
				return "-";
			}
			return patients.get(rowIndex).getBed().getBedID();
		}
		return null;
	}
	
	@Override
	public String getColumnName(int column) {
		if (column == 0) {
			return "Patient ID";
		} else if (column == 1) {
			return "First Name";
		} else if (column == 2) {
			return "Last Name";
		} else if (column == 3) {
			return "Department";
		} else if (column == 4) {
			return "Phone Number";
		} else if (column == 5) {
			return "Address";
		} else if (column == 6) {
			return "D.O.B";
		} else if (column == 7) {
			return "Deceased";
		} else if (column == 8) {
			return "Bed";
		}
		return null;
	}
}
