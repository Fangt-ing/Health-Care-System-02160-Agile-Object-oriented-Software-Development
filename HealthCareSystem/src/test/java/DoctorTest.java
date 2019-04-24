import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import hospitalmanagementsystem.*;
import hospitalmanagementsystem.departments.*;
import hospitalmanagementsystem.users.*;

/**
 * Testing the Doctor Class
 * @author Pieter O'Hearn
 */
public class DoctorTest {
	// User
	static Doctor d1;
	static Doctor d2;

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
	 * Set up the necessary resources for the Doctor Test file
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		// create Doctors
		d1 = new Doctor("John Doe", "123 Main St Anytown, Denmark", "+4512345678", "Emergency");
		d2 = new Doctor("Jane Doctor", "123 Main St Medicaltown, Germany", "+4912345678", "Inpatient");

		// create the Patients
		p1 = new Patient("Pieter", "O\'Hearn", LocalDate.of(1990, 1,12), "259 Nordvej 2800 Kongens Lyngby", "+4562473948");
		p2 = new Patient("Jack", "Rodman", LocalDate.of(1997, 6,28), "259 Nordvej 2800 Kongens Lyngby", "+4562870942");

		// create the Departments
		em = Emergency.getInstance();
		inPa = Inpatient.getInstance();
		outPa = Outpatient.getInstance();
		man = Management.getInstance();

		// create the Bed
		b1 = new Bed(em);
	}

	/**
	 * Tests the admitPatient method of the Doctor Class
	 * @throws IllegalAccessException 
	 */
	@Test
	public void admitPatientTest() throws IllegalAccessException {
		// admit p1 to Inpatient and check both patients variable and departments list
		assertEquals(null, p1.getPatientInfo().get("Department"));
		d2.admitPatient(p1,inPa);
		assertEquals("Inpatient",p1.getPatientInfo().get("Department"));
		assertTrue(inPa.getPatientList().contains(p1.getPatientInfo().get("Patient ID")));
		assertEquals(null,p1.getPatientInfo().get("Bed"));

		// admit to Emergency and check that a bed is assigned and that both patients variable and departments list
		assertEquals(null,p2.getPatientInfo().get("Department"));
		d1.admitPatient(p2,em);
		assertEquals("Emergency",p2.getPatientInfo().get("Department"));
		assertTrue(em.getPatientList().contains(p2.getPatientInfo().get("Patient ID")));
		
		// check doctors from other departments can admit patients
		d1.dischargePatient(p2);
		d1.admitPatient(p2,outPa);
		assertEquals("Outpatient",p2.getPatientInfo().get("Department"));
	}

	// create a rule
	@Rule public ExpectedException exception = ExpectedException.none();

	/**
	 * Tests the admitPatient method of the Doctor Class when the patient already has a Department
	 * @throws IllegalAccessException 
	 */
	@Test
	public void admitPatientErrorTest() throws IllegalAccessException {
		exception.expect(IllegalAccessException.class);
		exception.expectMessage("Can not admit a patient who is already admitted to a department.");
		d2.admitPatient(p1,outPa);
	}

	/**
	 * Tests the dischargePatient method of the Doctor Class
	 * @throws IllegalAccessException 
	 */
	@Test
	public void dischargePatientTest() throws IllegalAccessException {
		// Doctor2 discharges patient1 from Inpatient and check is Department is null
		assertEquals("Inpatient",p1.getPatientInfo().get("Department"));
		d2.dischargePatient(p1);
		assertEquals(null,p1.getPatientInfo().get("Department"));

		// check doctors from other departments can discharge Patients
		assertEquals("Outpatient",p2.getPatientInfo().get("Department"));
		assertEquals("Emergency",d1.getUserInfo().get("Department"));
		d1.dischargePatient(p2);
		assertEquals(null,p2.getPatientInfo().get("Department"));
	}

	/**
	 * Tests the dischargePatient method of the Doctor Class when the patient already has a Department
	 * @throws IllegalAccessException 
	 */
	@Test
	public void dischargePatientErrorTest() throws IllegalArgumentException, IllegalAccessException {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Can not discharge a patient who is not already admitted into any department.");
		d2.dischargePatient(p1);
	}

	/**
	 * Tests the assignBed method of the Doctor Class
	 * @throws IllegalAccessException 
	 */
	@Test
	public void assignBedTest() throws IllegalArgumentException, IllegalAccessException {
		// admit p1 to the Emergency department and assign the bed b1 to them
		d1.admitPatient(p1, em);
		d1.assignBed(p1, b1);

		// check that the patients info and beds info reflect this
		assertEquals(p1, b1.getPatient());
		assertEquals(b1.getBedID(),p1.getPatientInfo().get("Bed"));

		// try assign patient2 to the same bed, an IllegalArgumentException is expected
		try {
			d1.assignBed(p2, b1);
			fail("Expected an IllegalArgumentException to be thrown");
		} catch(IllegalArgumentException exception) {
			assertEquals(String.format("Bed %s is already occupied", b1.getBedID()),exception.getMessage());
		}
	}

	/**
	 * Tests the getMedicalData method of the Doctor Class
	 */
	@Test
	public void getMedicalDataTest() {
		// get patient1 record which should be null
		assertEquals(null,d1.getMedicalData(p1));

		// give p1 some medical data and test again
		p1.updateRecord("This patient has lost all their teeth. I recomend a diet of liquid food.");
		assertEquals("This patient has lost all their teeth. I recomend a diet of liquid food.",d1.getMedicalData(p1));
	}

	/**
	 * Tests the editMedicalData method of the Doctor Class
	 */
	@Test
	public void editMedicalDataTest() {
		// get patient2 record which should be null
		assertEquals(null,d1.getMedicalData(p2));

		// edit patient2's medical data and test again
		d1.editMedicalData(p2, "This Patient has died.");
		assertEquals("This Patient has died.",d1.getMedicalData(p2));

		// edit patient2's medical data again and test it has been appended
		d1.editMedicalData(p2, "Send patient to the morgue.");
		assertEquals("This Patient has died.\nSend patient to the morgue.",d1.getMedicalData(p2));
	}

	/**
	 * Tests the performTest method of the Doctor Class
	 */
	@Test
	public void performTestTes() {
		// TODO
	}
}