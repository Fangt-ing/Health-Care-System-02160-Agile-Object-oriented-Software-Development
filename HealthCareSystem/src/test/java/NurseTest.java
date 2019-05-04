import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import hospitalmanagementsystem.*;
import hospitalmanagementsystem.users.*;
import hospitalmanagementsystem.departments.*;

/**
 * Testing the Nurse Class
 * @author Pieter O'Hearn
 */
public class NurseTest {
	// User
	static Nurse n1;
	static Nurse n2;

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
	 * Set up the necessary resources for the Nurse Test file
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		// create a Nurses
		n1 = new Nurse("John Doe", "+4512345678", "Emergency");
		n2 = new Nurse("Jane nurse", "+4912345678", "Inpatient");

		// create the Patients
		p1 = new Patient("Pieter", "O\'Hearn", "12/01/1990", "259 Nordvej 2800 Kongens Lyngby", "+4562473948");
		p2 = new Patient("Jack", "Rodman", "28/06/1997", "259 Nordvej 2800 Kongens Lyngby", "+4562870942");

		// create the Departments
		em = Emergency.getInstance();
		inPa = Inpatient.getInstance();
		outPa = Outpatient.getInstance();
		man = Management.getInstance();

		// create the Bed
		b1 = new Bed(em.getName());
	}

	/**
	 * Tests the Constructor method of the Doctor Class
	 */
	@Test
	public void ConstructorTest() {
		// create a nurse in Outpatient and check it works
		Nurse n3 = new Nurse("John Doe", "+4512345678", "Outpatient");
		assertEquals(outPa, n3.getDepartment());

		// create a doctor with an invalid department and expect an exception
		try {
			n3 = new Nurse("John Doe", "+4512345678", "Blahh");
			fail("Expected an IllegalArgumentException");
		} catch(IllegalArgumentException e) {
			assertEquals("Blahh is an invalid department.", e.getMessage());
		}
	}
}