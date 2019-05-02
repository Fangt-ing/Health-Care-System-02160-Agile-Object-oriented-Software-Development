import static org.junit.Assert.*;
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
		
		b1 = new Bed(em);
	}
	 

	
	 /**
	 * Tests the save method of PersistenceLayer.
	 * Note that save is overloaded and returns a bool (true when succesfully saved) so that is what we check for.
	 */
	@Test
	public void saveTest(){
		//Saving a patient
		assertEquals(true, persist.save(p1, p1.getpatientID(), em.getName()));
		
		//Saving users
		assertEquals(true, persist.save(admin, admin.getUserID(), em.getName()));
		assertEquals(true, persist.save(doc, doc.getUserID(), inPa.getName()));
		assertEquals(true, persist.save(nurse, nurse.getUserID(), outPa.getName()));
		assertEquals(true, persist.save(user, user.getUserID(), man.getName()));
		
		//Saving a bed
		assertEquals(true, persist.save(b1, b1.getBedID(), inPa.getName()));
	}
	
	@Test
	public void loadDepartmentTest(){
		//Testing loadDepartment by saving -> setting to null -> loading -> comparing a value.
		String temp = em.getName();
		em = null;
		persist.loadDepartment(em);
		assertEquals(temp, em.getName());
	}
	
	@Test
	public void deleteTest(){
		assertEquals(true, persist.delete(p1.getpatientID(),p1.getDepartment()));
		
		//Delete patient/user/bed (returns true when deleted and false when it cannot find the file)
		persist.save(p1, p1.getpatientID(), em.getName());
		persist.save(doc, doc.getUserID(), inPa.getName());
		persist.save(b1, b1.getBedID(), inPa.getName());
		
		assertEquals(true, persist.delete(p1.getpatientID(), em.getName()));
		assertEquals(true, persist.delete(doc.getUserID(), inPa.getName()));
		assertEquals(true, persist.delete(b1.getBedID(), inPa.getName()));
		assertEquals(false, persist.delete(p1.getpatientID(), man.getName()));
		assertEquals(false, persist.delete(nurse.getUserID(), inPa.getName()));
		assertEquals(false, persist.delete(b1.getBedID(), outPa.getName()));
	}
	 
	 
	@Test
	public void loadObjs() {
		persist.save(p2, p2.getpatientID(), man.getName());
		persist.save(p3, p3.getpatientID(), man.getName());
		persist.save(p4, p4.getpatientID(), man.getName());
		
		
		
	}
	 
 }