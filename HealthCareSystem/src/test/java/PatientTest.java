import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import hospitalmanagementsystem.*;
import hospitalmanagementsystem.departments.*;
import hospitalmanagementsystem.users.*;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Pieter O'Hearn
 */
public class PatientTest {
	// test Patients
	static Patient p1;
	static Patient p2;
	static Patient p3;
	static Patient p4;
	static Patient p5;
	static Patient p6;
	static Patient p7;

	// Departments
	static Emergency em;
	static Inpatient inPa;
	static Outpatient outPa;
	static Management man;

	// Users
	static Admin admin;
	static Doctor doc;
	static Nurse nurse;
	static User user;

	// Beds
	static Bed b1;
	static Bed b2;
	static Bed b3;
	static Bed b4;

	@BeforeClass
	public static void setUpBeforeClass() throws IllegalAccessException {
		// Create Departments
		em = Emergency.getInstance();
		inPa = Inpatient.getInstance();
		outPa = Outpatient.getInstance();
		man = Management.getInstance();

		// Create Patients
		p1 = new Patient("Pieter", "O\'Hearn", "12/01/1990", "259 Nordvej 2800 Kongens Lyngby", "+4562473948");
		p2 = new Patient("Jack", "Rodman", "28/06/1997", "259 Nordvej 2800 Kongens Lyngby", "+4562870942");
		p3 = new Patient("Anna", "Hogan", "21/08/1988", "Georg Brandes Pl. 2-6, 1307 K??benhavn", "+4552373549");
		p4 = new Patient("Asger", "Conradsen", "29/05/1999", "487 downtown 2800 Kongens Lyngby", "+4585476964");
		p5 = new Patient("Karoline", "??stergaard", "11/02/1994", "259 Nordvej 2800 Kongens Lyngby", "+4582373943");
		p6 = new Patient("Kun", "Zhu", "08/06/1996", "259 Nordvej 2800 Kongens Lyngby", "+4539562047");

		// assign patients a Department
		p1.updateDepartment(em);
		p2.updateDepartment(inPa);
		p3.updateDepartment(em);
		p4.updateDepartment(em);
		p5.updateDepartment(em);
		p6.updateDepartment(outPa);

		// Create Beds
		b1 = new Bed(em.getName());
		b2 = new Bed(em.getName());
		b3 = new Bed(em.getName());
		b4 = new Bed(inPa.getName());
		
		// create Users
		admin = new Admin("Steve Jobs", "+180249625");
		doc = new Doctor("Dr. Smith", "+4545252525", "Emergency");
		nurse = new Nurse("John Doe", "+4512345678", "Emergency");
		user = new User("James Gosling", "San Francisco Bay Area, California, U.S.", "+141558396");
	}

	/**
	 * Tests the getPatientInfo method of the Patient class
	 */
	@Test
	public void getPatientInfoTest() {
		assertEquals("Pieter", p1.getFirstName());
		assertEquals("O'Hearn", p1.getLastName());
		assertEquals("12/01/1990", p1.getDOB());
		assertEquals("259 Nordvej 2800 Kongens Lyngby", p1.getAddress());
		assertEquals("Emergency", p1.getDepartment());
		assertEquals("false", p1.getDeceased());
	}

	/**
	 * Tests the updateDepartment method of the Patient class
	 * @throws IllegalArgumentException
	 */
	@Test
	public void updateDepartmentToManagementTest() throws IllegalArgumentException {
		// Test that error is thrown when trying to update Patient to Management department
		try {
			p1.updateDepartment(man);
			fail("Expected an AccessDeniedException to be thrown");
		} catch(IllegalArgumentException expected) {
			assertEquals(expected.getMessage(), "Patients can not be assigned to the Managment Department");
		}
	}

	/**
	 * Tests the updateDepartment method of the Patient class
	 * @throws IllegalAccessException
	 */
	@Test
	public void updateDepartmentTest() throws IllegalAccessException {
		// check the original Department is Emergency
		assertEquals(p1.getDepartment(), "Emergency");

		// update the department and check it has changed
		p1.updateDepartment(outPa);
		assertEquals(p1.getDepartment(), "Outpatient");
		
		// tests updateDepartment when the patient has a bed
		p1.setBed(b1.getBedID());
		b1.setPatient(p1.getPatientID());
		ArrayList<Bed> beds = new ArrayList<Bed>();
		beds.add(b1);
		em.setBedList(beds);
		p1.updateDepartment(em);
		assertEquals(p1.getDepartment(), "Emergency");
		
		p1.updateDepartment(inPa);
		assertEquals(p1.getDepartment(), "Inpatient");
	}

	/**
	 * Tests the updateBed method of the Patient class
	 * @throws IllegalAccessException
	 */
	@Test
	public void updateBedTest() throws IllegalArgumentException, IllegalAccessException {
		// test assigning a patient to an empty bed in the same department
		p1.updateDepartment(em);
		p1.setBed(b1.getBedID());
		assertEquals(b1.getBedID(),p1.getBed());
	}

	/**
	 * Tests the getRecord method of the Patient class
	 */
	@Test
	public void getRecordTest() {
		// Give the patients a record
		String r1 = "The patient has a fracture on their right index finger, I recomend strapping the fingre and some good whiskey to reduce the pain.";
		String r2 = "The patient has severe lacerations on their right leg.";
		p1.setRecord(r1);
		p2.setRecord(r2);

		// check the records match
		assertEquals(r1,p1.getRecord());
		assertEquals(r2,p2.getRecord());
	}

	/**
	 * Tests the updateRecord method of the Patient class
	 */
	@Test
	public void updateRecordTest() {
		// Original records
		String r5 = "The patient is experiencing severe stomach pains.";
		String r6 = "The patient has severe lacerations on their right leg.";

		// updated records
		String uR5 = "The patient is experiencing severe stomach pains.\nI have assigned them to Dr. Smith who has better experience with this.";
		String uR6 = "The patient has severe lacerations on their right leg.\nI have cleaned the wound and have told the patient to keep it covered and clean.";

		// update and test
		p5.setRecord(r5);
		p6.setRecord(r6);

		assertEquals(r5, p5.getRecord());
		assertEquals(r6, p6.getRecord());

		// update again and test the new message has been appended to a new line
		p5.setRecord("I have assigned them to Dr. Smith who has better experience with this.");
		p6.setRecord("I have cleaned the wound and have told the patient to keep it covered and clean.");

		assertEquals(uR5,p5.getRecord());
		assertEquals(uR6,p6.getRecord());
	}

	/**
	 * Tests the constructor
	 */
	@Test
	public void testConstructor() {
		// create a new Patient
		Patient newPatient = new Patient("name", "surname", "01/01/2000", "address", "phoneNo");

		// check variables
		assertFalse(Objects.equals(newPatient.getPatientID(),null));
		assertTrue(Objects.equals(newPatient.getRecord(),null));
		assertTrue(newPatient.getDepartment() == null);
		assertEquals("false",newPatient.getDeceased());
	}
}
