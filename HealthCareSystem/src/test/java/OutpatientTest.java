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
 */
public class OutpatientTest {
	// Users
	static Doctor d1;

	// Patients
	static Patient p1;

	// Departments
	static Outpatient outPa;
	
	/**
	 * Initialise User variables to use in testing
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		// create the Departments
		outPa = Outpatient.getInstance();

		// create the patients
		p1 = new Patient("Pieter", "O\'Hearn", "12/01/1990", "259 Nordvej 2800 Kongens Lyngby", "62473948");
		
		// create the Users
		d1 = new Doctor("Doc", "12345678", null);
	}
	
	/**
	 * Test the addPatient method of the Outpatient Class.
	 */
	@Test
	public void addPatientTest() {
		// add p1 to the Outpatient department and test it worked
		outPa.addPatient(p1);
		assertTrue(outPa.getPatientList().contains(p1));
	}

	/**
	 * Tests the removePatient method of the Outpatient Class.
	 */
	@Test
	public void removePatientTest() {
		// remove p1 from the Outpatient department and test it worked
		outPa.removePatient(p1);
		assertFalse(outPa.getPatientList().contains(p1));
	}
	
	/**
	 * Tests the getName method of the Outpatient Class.
	 */
	@Test
	public void getNameTest() {
		// check that "Outpatient" is returned
		assertEquals("Outpatient",outPa.getName());
	}
	
	/**
	 * Tests the addUser method of the Outpatient Class.
	 */
	@Test
	public void addUserTest(){
		// add a User to the Outpatient Class. 
		outPa.addUser(d1);
		assertTrue(outPa.getUserList().contains(d1));
	}

	/**
	 * Tests the removeUser method of the Outpatient Class.
	 */
	@Test
	public void removeUserTest(){
		// remove a User from the Outpatient Class.
		outPa.removeUser(d1);
		assertFalse(outPa.getUserList().contains(d1));
	}
	
	/**
	 * Tests the addBed method of the Outpatient Class.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void addBedTest() {
		// try add a bed to the Outpatient Class
		outPa.addBed(new Bed("Emergency"));
	}
	
	/**
	 * Tests the removeBed method of the Outpatient Class.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void removeBedTest() {
		// try remove a bed from the Outpatient Class
		outPa.removeBed(null);
	}
	
	/**
	 * Tests the setBedList method in the Outpatient Class.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void setBedListTest() {
		// create an empty bed list and try set it as the Outpatient Bed list
		ArrayList<Bed> bedList = new ArrayList<Bed>();
		outPa.setBedList(bedList);
	}
	
	/**
	 * Tests the getBedList method in the Outpatient Class.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void getBedListTest() {
		// try get the bedList of the Outpatient Class
		outPa.getBedList();
	}
	
	/**
	 * Tests the setPatientList method in the Outpatient Class.
	 */
	@Test
	public void setPatientListTest() {
		// create a patient list and set it as the Outpatient Patient list
		ArrayList<Patient> patientList = new ArrayList<Patient>();
		patientList.add(new Patient("A","B","01/01/2000","D","12345678"));
		outPa.setPatientList(patientList);
		
		// test that it set
		assertEquals(patientList, outPa.getPatientList());
	}
	
	/**
	 * Tests the setUserList method in the Outpatient Class.
	 */
	@Test
	public void setUserListTest() {
		// create a User list and set it as the Outpatient User list
		ArrayList<User> userList = new ArrayList<User>();
		userList.add(new Doctor("Name","12345678","Inpatient"));
		outPa.setUserList(userList);
		
		// test that it set
		assertEquals(userList, outPa.getUserList());
	}
}