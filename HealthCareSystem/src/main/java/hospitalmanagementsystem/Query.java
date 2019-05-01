package hospitalmanagementsystem;


import java.util.*;

import hospitalmanagementsystem.departments.Inpatient;
import hospitalmanagementsystem.departments.Management;
import hospitalmanagementsystem.departments.Emergency;
import hospitalmanagementsystem.departments.Outpatient;

/**
 * 
 * @author Karoline
 *
 */
public class Query {
	// static variables
	private static Query single_instance = null;
	
	// Instance variables
	private int inSize;
	private int outSize;
	private int emSize;
	private List<Integer> patientSizes;
	private int  maxPatients;
	private int minPatients;
	private int inUsers;
	private int outUsers;
	private int emUsers;
	private int manUsers;
	private List<Integer> userSizes;
	private int  maxUsers;
	private int minUsers;
	private List<String> departments;

	private Query() {
		//get number of patients currently in each department
	inSize = Inpatient.getInstance().getPatientList().size();
	outSize = Outpatient.getInstance().getPatientList().size();
	emSize = Emergency.getInstance().getPatientList().size();
	
	//get max and min number of patients
	patientSizes = Arrays.asList(inSize,outSize,emSize);
	maxPatients= Collections.max(patientSizes);
	minPatients = Collections.min(patientSizes);
	
	//get number of users currently in each department
	inUsers = Inpatient.getInstance().getUserList().size();
	outUsers = Outpatient.getInstance().getUserList().size();
	emUsers = Emergency.getInstance().getUserList().size();
	manUsers = Management.getInstance().getUserList().size();
	
	//get max and min number of users
	userSizes = Arrays.asList(inSize,outSize,emSize);
	maxUsers = Collections.max(userSizes);
	minUsers = Collections.min(userSizes);
	
	//Save department names as Strings
	departments = Arrays.asList("Inpatient","Outpatient","Emergency","Management");
	}
	
	public static Query getInstance() 
	{ 
	    if (single_instance == null) {
	        single_instance = new Query();
	    }
	
	    return single_instance; 
	}
	
	
	public static String depMostPatients() {
		//department with most admitted patients
		
		int i = patientSizes.indexOf(maxPatients);
		
		return departments.get(i);
	}
	public static String depLeastPatients() {
		//least patients 
		
		int i = patientSizes.indexOf(minPatients);
		
		return departments.get(i);
	}
	public static String depMostUsers() {
		//department with most admitted patients
		
		int i = userSizes.indexOf(maxUsers);
		
		return departments.get(i);
	}
	public static String depLeastUsers() {
		//least patients 
		int i = userSizes.indexOf(minUsers);
		
		return departments.get(i);
	}
	public static int totPatients() {
		//get total number of patients in entire hospital
		return inSize+emSize+outSize;
	}
	public static int totUsers() {
		//total number of users in entire hospital
		return inUsers+outUsers+emUsers;
	}
}