import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import hospitalmanagementsystem.users.Doctor;

/**
 * Testing the Doctor Class
 * @author Pieter O'Hearn
 */
public class DoctorTest {
	// Users
	static Doctor d1;
	static Doctor d2;

	/**
	 * Initialise User variables to use in testing
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		// create Doctors
		d1 = new Doctor("John Doe", "12345678", "Emergency");
		d2 = new Doctor("Jane Doctor", "12345678", "Inpatient");
	}
	
	/**
	 * Tests the getType method in the Doctor class.
	 */
	@Test
	public void getTypeTest() { 
		// ensure that getType returns "Doctor"
		assertEquals("Doctor", d1.getType());
	}
	
	/**
	 * Tests the moveDepartment method of the Doctor class.
	 */
	@Test
	public void moveDepartmentTest() {
		// Move the Doctor d2 to the Emergency Department
		d2.moveDepartment("Emergency");
		
		// test that the department has been changed
		assertEquals("Emergency", d2.getDepartment());
	}
	
	/**
	 * Tests the Doctor Constructor.
	 */
	@Test
	public void constructorTest() {
		// create a new doctor in the "Outpatient class" and without a department
		Doctor d3 = new Doctor("Doc Three", "12345678", "Outpatient");
		Doctor d4 = new Doctor("Doc Four", "12345678", null);
		
		// check they where created correctly
		assertEquals("Outpatient", d3.getDepartment());
		assertEquals(null, d4.getDepartment());
	}
	
	/**
	 * Tests an invalid constructor
	 */
	@Test(expected = IllegalArgumentException.class) 
	public void illegalConstructorTest() {
		// create a Doctor with an invalid department
		d2 = new Doctor("Name", "12345678", "Department");
	}
}