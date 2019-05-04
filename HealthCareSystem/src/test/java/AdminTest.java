import org.junit.BeforeClass;
import hospitalmanagementsystem.*;
import hospitalmanagementsystem.departments.*;
import hospitalmanagementsystem.users.*;

/**
 * Tests for Admin Class
 * @author Jack Rodman
 */
public class AdminTest {
	// User
	static Admin ad1;
	static Admin ad2;

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
	 * Initialise admin variables to use in testing
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		// create Administrators
		ad1 = new Admin("John Doe", "+4512345678");
		ad2 = new Admin("Andy Admin", "+4912345678");

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
}
