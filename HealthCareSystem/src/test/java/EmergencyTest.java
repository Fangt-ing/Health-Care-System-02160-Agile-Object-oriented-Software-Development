import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;
import hospitalmanagementsystem.Bed;
import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.departments.*;
import hospitalmanagementsystem.users.*;

/**
 *
 * @author Kun
 *
 */
public class EmergencyTest {
	// Users
	static Doctor d1;
	static Nurse n1;

	// Patients
	static Patient p1;
	static Patient p2;

	// Departments
	static Emergency em;
	static Inpatient inPa;
	static Outpatient outPa;
	static Management man;
	
	// Beds
	static Bed b1;

	/**
	 * Initialise User variables to use in testing
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		// create the Departments
		em = Emergency.getInstance();
		inPa = Inpatient.getInstance();

		// create the patients
		p1 = new Patient("Pieter", "O\'Hearn", "12/01/1990", "259 Nordvej 2800 Kongens Lyngby", "+4562473948");
		p2 = new Patient("Jack", "Rodman", "28/06/1997", "259 Nordvej 2800 Kongens Lyngby", "+4562870942");
		
		// create the Users
		d1 = new Doctor("Doc", "12345678", null);
		n1 = new Nurse("Nurse", "12345678", "Emergency");
		
		// create the beds
		b1 = new Bed("Emergency");
	}

	/**
	 * Testing add method in Emergency class
	 */
	@Test
	public void addPatientTest() throws IllegalAccessException{
		// add the p1 to patientList in Emergency class
		em.addPatient(p1);
		
		// checks if the p1 is added to patientList
		assertTrue(em.getPatientList().contains(p1));

		// add the p2 to patientList in Emergency class
		em.addPatient(p2);
		
		// checks if the p2 is added to patientList
		assertTrue(em.getPatientList().contains(p2));
	}

	/**
	 * Testing getPatientData method in User class
	 */
	@Test
	public void removePatientTest() throws IllegalAccessException{
		// remove the p1 to patientList in Emergency class
		em.removePatient(p1);
		
		// checks if the p1 is added to patientList
		assertFalse(em.getPatientList().contains(p1));

		// remove the p2 to patientList in Emergency class
		em.removePatient(p2);
		
		// checks if the p2 is added to patientList
		assertFalse(em.getPatientList().contains(p2));
	}

	/**
	 * Tests the addUser method of the Emergency Class.
	 */
	@Test
	public void addUserTest() {
		// add a User to the department
		em.addUser(d1);
		assertTrue(em.getUserList().contains(d1));
	}
	
	/**
	 * Tests the removeUser method of the Emergency Class.
	 */
	@Test
	public void removeUserTest() {
		// remove a User to the department
		em.removeUser(n1);
		assertFalse(em.getUserList().contains(n1));
	}
	
	/**
	 * Tests the addBed method of the Emergency Class.
	 */
	@Test
	public void addBedTest() {
		// add a User to the department
		em.addBed(b1);
		assertTrue(em.getBedList().contains(b1));
	}
	
	/**
	 * Tests the removeBed method of the Emergency Class.
	 */
	@Test
	public void removeBedTest() {
		// remove a User to the department
		em.removeBed(b1);
		assertFalse(em.getBedList().contains(b1));
	}
	
	/**
	 * Tests the getName method of the Emergency Class.
	 */
	@Test
	public void getNameTest() {
		// test that getName returns "Emergency"
		assertEquals("Emergency", em.getName());
	}
	
	/**
	 * Tests the setBedList method in the Emergency Class.
	 */
	@Test
	public void setBedListTest() {
		// create a bed list and set it as the Emergency Bed list
		ArrayList<Bed> bedList = new ArrayList<Bed>();
		bedList.add(new Bed("Emergency"));
		em.setBedList(bedList);
		
		// test that it set
		assertEquals(bedList, em.getBedList());
	}
	
	/**
	 * Tests the setPatientList method in the Emergency Class.
	 */
	@Test
	public void setPatientListTest() {
		// create a patient list and set it as the Emergency Patient list
		ArrayList<Patient> patientList = new ArrayList<Patient>();
		patientList.add(new Patient("A","B","01/01/2000","D","12345678"));
		em.setPatientList(patientList);
		
		// test that it set
		assertEquals(patientList, em.getPatientList());
	}
	
	/**
	 * Tests the setUserList method in the Emergency Class.
	 */
	@Test
	public void setUserListTest() {
		// create a User list and set it as the Emergency User list
		ArrayList<User> userList = new ArrayList<User>();
		userList.add(new Doctor("Name","12345678","Emergency"));
		em.setUserList(userList);
		
		// test that it set
		assertEquals(userList, em.getUserList());
	}
}
