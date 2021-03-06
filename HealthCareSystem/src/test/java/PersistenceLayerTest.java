import static org.junit.Assert.*;

import java.io.File;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hospitalmanagementsystem.*;
import hospitalmanagementsystem.departments.*;
import hospitalmanagementsystem.users.*;

/**
 *
 * @author Asger Conradsen
 */
 public class PersistenceLayerTest{
	 
	static PersistenceLayer persist;
	
	static Emergency em;
	static Inpatient inPa;
	static Outpatient outPa;
	static Management man;
	 
	static Admin admin;
	static Doctor doc;
	static Nurse nurse;
	static User user;
	
	static Bed b1;
	
	static Patient p1;
	static Patient p2;
	static Patient p3;
	static Patient p4;
	
	static final String[] departments = {"Emergency", "Inpatient", "Outpatient", "Management", "Temp"};
	static final String[] subfolders = {"Users", "Patients", "Beds"};
	
	/**
	 * Initializes objects with values used for testing
	 * @throws IllegalAccessException
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws IllegalAccessException {
		persist = new PersistenceLayer();

		em = Emergency.getInstance();
		inPa = Inpatient.getInstance();
		outPa = Outpatient.getInstance();
		man = Management.getInstance();
		
		p1 = new Patient("Asger", "Conradsen", "04/10/1995", "487 downtown 2800 Kongens Lyngby", "+4585476964");
		p1.updateDepartment(em);
		
		admin = new Admin("Steve Jobs","+180249625");
		doc = new Doctor("Dr. Smith", "+4545252525", "Emergency");
		nurse = new Nurse("John Doe", "+4512345678", "Emergency");
		user = new User("James Gosling", "+141558396");
		
		b1 = new Bed(em.getName());
	}
	 
	/**
	 * Cleans folders before each test
	 */
	@Before
	public void clean() {
		for(String depart : departments) {
			String dir = "Departments" + File.separator + depart + File.separator;
			
			for(String folder : subfolders) {
				File delDir = new File(dir + folder);
				if(delDir.exists()) {
					for(File file: delDir.listFiles()) {
						file.delete();
					}
				}
			}	
		}			
	}

	
	 /**
	 * Tests the save method of PersistenceLayer.
	 * Note that save is overloaded and returns a bool (true when succesfully saved)
	 * so that is what we check for.
	 */
	@Test
	public void saveTest(){
		//Saving a patient
		assertEquals(true, persist.save(p1, p1.getPatientID(), em.getName()));
		
		//Saving users
		assertEquals(true, persist.save(admin, admin.getUserID(), em.getName()));
		assertEquals(true, persist.save(doc, doc.getUserID(), outPa.getName()));
		assertEquals(true, persist.save(nurse, nurse.getUserID(), outPa.getName()));
		assertEquals(true, persist.save(user, user.getUserID(), man.getName()));
		assertEquals(false, persist.save(user, "k123", man.getName()));
		
		//Saving a bed
		assertEquals(true, persist.save(b1, b1.getBedID(), em.getName()));
	}
	
	/**
	 * Simple test for saving the ID counter.
	 */
	@Test
	public void saveCounterTest(){
		assertEquals(true, persist.saveCounter(5));
	}
	
	/**
	 * Test loadTemps by comparing with the id of the object we saved
	 */
	@Test
	public void loadTempsTest() {
		//Save objects in Temp
		persist.save(doc, doc.getUserID(), "Temp");
		persist.save(b1, b1.getBedID(), "Temp");
		persist.save(p1, p1.getPatientID(), "Temp");
		
		//Test if lists matches against the ones we saved and loaded.
		assertEquals(b1.getBedID(), ((Bed) persist.loadTemps('B').get(0)).getBedID());
		assertEquals(doc.getUserID(), ((User) persist.loadTemps('U').get(0)).getUserID());
		assertEquals(p1.getPatientID(), ((Patient) persist.loadTemps('P').get(0)).getPatientID());
		
		//Test for invalid input
		assertEquals(null, persist.loadTemps('x'));	
	}
	
	/**
	 * Test loadDepartment by comparing with the id of the objects we saved
	 */
	@Test
	public void loadDepartmentTest(){		
		//Save the objects
		persist.save(b1, b1.getBedID(), em.getName());
		persist.save(nurse, nurse.getUserID(), em.getName());
		persist.save(p1, p1.getPatientID(), em.getName());
		
		//Load the depart and test if it matches against what we just saved
		persist.loadDepartment(em);
		
		assertEquals(b1.getBedID(), em.getBedList().get(0).getBedID());
		assertEquals(nurse.getUserID(), em.getUserList().get(0).getUserID());
		assertEquals(p1.getPatientID(), em.getPatientList().get(0).getPatientID());	
	}
	
	/**
	 * Test of the delete method. Done by testing if it returns true.
	 */
	@Test
	public void deleteTest(){
		//First save files so we can delete something.
		p1.updateDepartment(inPa);
		persist.save(p1, p1.getPatientID(), inPa.getName());
		persist.save(b1, b1.getBedID(), inPa.getName());
		persist.save(doc, doc.getUserID(), inPa.getName());
		persist.save(admin, admin.getUserID(), inPa.getName());
		persist.save(nurse, nurse.getUserID(), inPa.getName());
		
		//Delete users
		assertEquals(true, persist.delete(p1.getPatientID(), inPa.getName()));
		assertEquals(true, persist.delete(doc.getUserID(), inPa.getName()));
		assertEquals(true, persist.delete(admin.getUserID(), inPa.getName()));
		assertEquals(true, persist.delete(nurse.getUserID(), inPa.getName()));
		
		//Delete bed
		assertEquals(true, persist.delete(b1.getBedID(), inPa.getName()));

		//Try deleting file of unknown type
		assertEquals(false, persist.delete("k123", inPa.getName()));
		
	}
	 
	/**
	 * Test of the loadCounter method. Test against the counter we just saved.
	 * Also has a testcase that deletes the saved counter and check if it correctly
	 * returns 0.
	 */
	@Test
	public void loadCounterTest() {
		persist.saveCounter(5);
		assertEquals(5, persist.loadCounter());
		
		File delFile = new File("Departments" + File.separator + "Temp" + 
				File.separator + "Counter");
		delFile.delete();
		assertEquals(0, persist.loadCounter());	
	}
	 

	 
 }
