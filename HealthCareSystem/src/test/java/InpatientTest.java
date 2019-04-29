import static org.junit.Assert.*;

import org.junit.Test;


import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.departments.*;
import hospitalmanagementsystem.users.User;

//@author Karoline



class InpatientTest {
	
	//Define patients and departments
	static Patient p1;

	static User u1;
	
	static Management man;
	static Inpatient in;
	static Outpatient out;
	static Emergency em;
	public static void Departments(){
	em = Emergency.getInstance();
	in = Inpatient.getInstance();
	out = Outpatient.getInstance();
	man = Management.getInstance();
	}
	
	@Test
	public void addPatientTest() {
		
		in.addPatient(p1);
		assertTrue(in.getPatientList().contains(p1));

	}

	@Test
	public void removePatientTest() {
		
		in.removePatient(p1);
		assertFalse(in.getPatientList().contains(p1));

	}
	@Test
	public void getNameTest() {
		assertEquals("Inpatient",in.getName());
	}
	
	@Test
	public void addUserTest(){
		in.addUser(u1);
		assertTrue(in.getUserList().contains(u1));
	}

	@Test
	public void removeUserTest(){
		in.removeUser(u1);
		assertFalse(in.getUserList().contains(u1));
	}

}