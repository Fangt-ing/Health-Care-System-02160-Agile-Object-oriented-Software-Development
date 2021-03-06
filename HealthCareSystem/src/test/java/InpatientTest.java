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
 * @author Karoline
 */
public class InpatientTest {
	// Users
	static Doctor d1;

	// Patients
	static Patient p1;

	// Departments
	static Inpatient inPa;
	
	// Beds
	static Bed b1;
	
	/**
	 * Initialise User variables to use in testing
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		// create the Departments
		inPa = Inpatient.getInstance();

		// create the patients
		p1 = new Patient("Pieter", "O\'Hearn", "12/01/1990", "259 Nordvej 2800 Kongens Lyngby", "+4562473948");
		
		// create the Users
		d1 = new Doctor("Doc", "12345678", null);
		
		// create the beds
		b1 = new Bed("Inpatient");
	}
	
	/**
	 * Test the addPatient method of the Inpatient Class.
	 */
	@Test
	public void addPatientTest() {
		// add p1 to the Inpatient department and test it worked
		inPa.addPatient(p1);
		assertTrue(inPa.getPatientList().contains(p1));
	}

	/**
	 * Tests the removePatient method of the Inpatient Class.
	 */
	@Test
	public void removePatientTest() {
		// remove p1 from the Inpatient department and test it worked
		inPa.removePatient(p1);
		assertFalse(inPa.getPatientList().contains(p1));
	}
	
	/**
	 * Tests the getName method of the Inpatient Class.
	 */
	@Test
	public void getNameTest() {
		// check that "Inpatient" is returned
		assertEquals("Inpatient",inPa.getName());
	}
	
	/**
	 * Tests the addUser method of the Inpatient Class.
	 */
	@Test
	public void addUserTest(){
		// add a User to the Inpatient Class. 
		inPa.addUser(d1);
		assertTrue(inPa.getUserList().contains(d1));
	}

	/**
	 * Tests the removeUser method of the Inpatient Class.
	 */
	@Test
	public void removeUserTest(){
		// remove a User from the Inpatient Class.
		inPa.removeUser(d1);
		assertFalse(inPa.getUserList().contains(d1));
	}
	
	/**
	 * Tests the addBed method of the Inpatient Class.
	 */
	@Test
	public void addBedTest() {
		// add the bed b1 to the Inpatient Department
		inPa.addBed(b1);
		assertTrue(inPa.getBedList().contains(b1));
	}
	
	/**
	 * Tests the removeBed method of the Inpatient Class.
	 */
	@Test
	public void removeBedTest() {
		// remove the bed b1 to the Inpatient Department
		inPa.removeBed(b1);
		assertFalse(inPa.getBedList().contains(b1));
	}
	
	/**
	 * Tests the setBedList method in the Inpatient Class.
	 */
	@Test
	public void setBedListTest() {
		// create a bed list and set it as the Inpatient Bed list
		ArrayList<Bed> bedList = new ArrayList<Bed>();
		bedList.add(new Bed("Inpatient"));
		inPa.setBedList(bedList);
		
		// test that it set
		assertEquals(bedList, inPa.getBedList());
	}
	
	/**
	 * Tests the setPatientList method in the Inpatient Class.
	 */
	@Test
	public void setPatientListTest() {
		// create a patient list and set it as the Inpatient Patient list
		ArrayList<Patient> patientList = new ArrayList<Patient>();
		patientList.add(new Patient("A","B","01/01/2000","D","12345678"));
		inPa.setPatientList(patientList);
		
		// test that it set
		assertEquals(patientList, inPa.getPatientList());
	}
	
	/**
	 * Tests the setUserList method in the Inpatient Class.
	 */
	@Test
	public void setUserListTest() {
		// create a User list and set it as the Inpatient User list
		ArrayList<User> userList = new ArrayList<User>();
		userList.add(new Doctor("Name","12345678","Inpatient"));
		inPa.setUserList(userList);
		
		// test that it set
		assertEquals(userList, inPa.getUserList());
	}
}