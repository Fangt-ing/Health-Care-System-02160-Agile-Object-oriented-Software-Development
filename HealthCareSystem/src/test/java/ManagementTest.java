import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;
import hospitalmanagementsystem.Bed;
import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.departments.Management;
import hospitalmanagementsystem.users.Admin;
import hospitalmanagementsystem.users.User;
/**
 * 
 * @author Kun Zhu
 *
 */
public class ManagementTest {
	// Users
	static Admin a1;

	// Patients
	static Patient p1;

	// Departments
	static Management man;
	
	/**
	 * Initialise User variables to use in testing
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		// create the Departments
		man = Management.getInstance();

		// create the patients
		p1 = new Patient("Pieter", "O\'Hearn", "12/01/1990", "259 Nordvej 2800 Kongens Lyngby", "62473948");
		
		// create the Users
		a1 = new Admin("Admin", "12345678");
	}
	
	/**
	 * Test the addPatient method of the Management Class.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void addPatientTest() {
		// try add p1 to the Management department
		man.addPatient(p1);
	}

	/**
	 * Tests the removePatient method of the Management Class.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void removePatientTest() {
		// try remove p1 from the Management department
		man.removePatient(p1);
	}
	
	/**
	 * Tests the getName method of the Management Class.
	 */
	@Test
	public void getNameTest() {
		// check that "Management" is returned
		assertEquals("Management",man.getName());
	}
	
	/**
	 * Tests the addUser method of the Management Class.
	 */
	@Test
	public void addUserTest(){
		// add a User to the Management Class. 
		man.addUser(a1);
		assertTrue(man.getUserList().contains(a1));
	}

	/**
	 * Tests the removeUser method of the Management Class.
	 */
	@Test
	public void removeUserTest(){
		// remove a User from the Management Class.
		man.removeUser(a1);
		assertFalse(man.getUserList().contains(a1));
	}
	
	/**
	 * Tests the addBed method of the Management Class.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void addBedTest() {
		// try add a bed to the Management Class
		man.addBed(new Bed("Emergency"));
	}
	
	/**
	 * Tests the removeBed method of the Management Class.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void removeBedTest() {
		// try remove a bed from the Management Class
		man.removeBed(null);
	}
	
	/**
	 * Tests the setBedList method in the Management Class.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void setBedListTest() {
		// create an empty bed list and try set it as the Management Bed list
		ArrayList<Bed> bedList = new ArrayList<Bed>();
		man.setBedList(bedList);
	}
	
	/**
	 * Tests the getBedList method in the Management Class.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void getBedListTest() {
		// try get the bedList of the Management Class
		man.getBedList();
	}
	
	/**
	 * Tests the setPatientList method in the Management Class.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void setPatientListTest() {
		// try add a Patient list to the Management department
		ArrayList<Patient> patientList = new ArrayList<Patient>();
		man.setPatientList(patientList);
	}
	
	/**
	 * Tests the getPatientList method in the Management Class.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void getPatientListTest() {
		// try retrieve the Management department
		man.getPatientList();
	}
	
	/**
	 * Tests the setUserList method in the Management Class.
	 */
	@Test
	public void setUserListTest() {
		// create a User list and set it as the Management User list
		ArrayList<User> userList = new ArrayList<User>();
		userList.add(new Admin("Name","12345678"));
		man.setUserList(userList);
		
		// test that it set
		assertEquals(userList, man.getUserList());
	}
}