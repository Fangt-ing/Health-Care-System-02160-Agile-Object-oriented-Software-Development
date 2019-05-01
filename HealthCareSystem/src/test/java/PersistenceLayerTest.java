import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import hospitalmanagementsystem.*;
import hospitalmanagementsystem.departments.*;
import hospitalmanagementsystem.users.*;
import java.util.Objects;

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
		
		
		p2 = new Patient("John", "Conradsen", "04/10/1995", "487 downtown 2800 Kongens Lyngby", "+4585476964");
		p3 = new Patient("Niels", "Conradsen", "04/10/1995", "487 downtown 2800 Kongens Lyngby", "+4585476964");
		p4 = new Patient("Bo", "Conradsen", "04/10/1995", "487 downtown 2800 Kongens Lyngby", "+4585476964");
		p2.updateDepartment(man);
		p3.updateDepartment(man);
		p4.updateDepartment(man);
		
		
		admin = new Admin("Steve Jobs", "+180249625");
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
		//Saving departments
		assertEquals(true, persist.save(em));
		assertEquals(true, persist.save(inPa));
		assertEquals(true, persist.save(outPa));
		assertEquals(true, persist.save(man));
		
		//Saving a patient
		assertEquals(true, persist.save(p1, p1.getPatientId(), em));
		
		//Saving users
		assertEquals(true, persist.save(admin, admin.getUserID(), em));
		assertEquals(true, persist.save(doc, doc.getUserID(), inPa));
		assertEquals(true, persist.save(nurse, nurse.getUserID(), outPa));
		assertEquals(true, persist.save(user, user.getUserID(), em));
		
		//Saving a bed
		assertEquals(true, persist.save(b1, b1.getBedID(), inPa));
	}
	
	@Test
	public void loadDepartmentTest(){
		//Testing loadDepartment by saving -> setting to null -> loading -> comparing a value.
		persist.save(em);
		String temp = em.getName();
		em = null;
		em = (Emergency) persist.loadDepartment(em);
		assertEquals(temp, em.getName());
	}
	
	@Test
	public void deleteTest(){
		//Delete department
		persist.save(em);
		assertEquals(true, persist.delete(inPa));
		
		//Delete patient/user/bed (returns true when deleted and false when it cannot find the file)
		persist.save(p1, p1.getPatientId(), em);
		persist.save(doc, doc.getUserID(), inPa);
		persist.save(b1, b1.getBedID(), inPa);
		
		assertEquals(true, persist.delete(p1.getPatientId(), em));
		assertEquals(true, persist.delete(doc.getUserID(), inPa));
		assertEquals(true, persist.delete(b1.getBedID(), inPa));
		assertEquals(false, persist.delete(p1.getPatientId(), man));
		assertEquals(false, persist.delete(nurse.getUserID(), inPa));
		assertEquals(false, persist.delete(b1.getBedID(), outPa));
	}
	 
	 
	@Test
	public void loadObjs() {
		persist.save(p2, p2.getPatientId(), man);
		persist.save(p3, p3.getPatientId(), man);
		persist.save(p4, p4.getPatientId(), man);
		
		
		
	}
	 
 }