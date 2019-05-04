package hospitalmanagementsystem;

/**
 * The bed has a unique final ID, patient and department where it belongs.
 * A bed can only lay one patient and belong to one department.
 * @author Pieter O'Hearn
 */
public class Bed {
	// STATIC VARIABLES
	private static PersistenceLayer persist = new PersistenceLayer();

	// INSTANCE VARIABLES
	String bedID;
	String department;
	String patient;
	
	// CONSTRUCTRS
	/**
	 * Creates a new instance of a Bed with no Patient but a specified department
	 * @param dep
	 * @throws IllegalArgumentException
	 */
	public Bed(String dep) throws IllegalArgumentException {
		// give the new bed a unique ID
		int IDCounter = persist.loadCounter() + 1;
		this.bedID = "B" + Integer.toString(IDCounter);
		persist.saveCounter(IDCounter);
		
		// assign other variables
		if(dep != null && (dep.equals("Emergency") || dep.equals("Inpatient"))) {
			this.department = dep;
		} else {
			throw new IllegalArgumentException(String.format("Cannot create a bed in the %s department.",dep));
		}
		
		// save the bed to file
		persist.save(this, this.bedID, this.department);
	}
	
	/**
	 * A Blank constructor for the Persistency Class.
	 */
	public Bed() {}

	// METHODS
	/**
	 * Adds a Patient to the bed
	 * @param patient
	 * @throws IllegalArgumentException
	 */
	public void updatePatient(Patient patient) throws IllegalArgumentException {
		// check if the bed is full
		if(this.patient != null) {
			// if full throw IllegalArgumentException
			throw new IllegalArgumentException(String.format("Bed %s is already occupied", this.bedID));
		} else if(!patient.getDepartment().equals(this.department)) {
			// if bed is in wrong department throw IllegalArgumentException
			throw new IllegalArgumentException(String.format("Bed %s is in a different Department to %s", this.bedID, patient.getFirstName()));
		} else {
			// if empty and in the same department update patient
			this.patient = patient.getPatientID();
		}
		
		// update the beds file
		persist.save(this, this.bedID, this.department);
	}
	
	/**
	 * 
	 * @param dep
	 */
	public void updateDepartment(String dep) {
		// assign other variables
		if(dep != null && (dep.equals("Emergency") || dep.equals("Inpatient"))) {
			// remove the bed file from its department folder
			persist.delete(this.bedID, this.department);

			// set the department
			this.department = dep;
		} else {
			throw new IllegalArgumentException(String.format("%s is an invalid department.",dep));
		}
		
		// save the bed file to its new department folder
		persist.save(this, this.bedID, this.department);
	}
	
	// GETTER METHODS
	/**
	 * 
	 * @return
	 */
	public String getBedID() {
		return this.bedID;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDepartment() {
		return this.department;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getPatient() {
		return this.patient;
	}
	
	// SETTER METHODS
	/**
	 * 
	 * @param id
	 */
	public void setBedID(String id) {
		this.bedID = id;
	}
	
	/**
	 * 
	 * @param dep
	 */
	public void setDepartment(String dep) {
		this.department = dep;
	}
	
	/**
	 * 
	 * @param patientID
	 */
	public void setPatient(String patientID) {
		this.patient = patientID;
	}
}