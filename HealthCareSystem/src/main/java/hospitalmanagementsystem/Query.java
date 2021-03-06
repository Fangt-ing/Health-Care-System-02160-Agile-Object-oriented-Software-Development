package hospitalmanagementsystem;

import java.util.ArrayList;
import hospitalmanagementsystem.departments.*;

/**
 * The Query Class is Used to make Advanced Queries about the Hospital.
 * @author Karoline Østergaard
 */
public class Query {
	// INSTANCE VARIABLES
	private int inSize;
	private int outSize;
	private int emSize;
	private int manSize;
	private String[] departments;
	private int[] sizes;
	private String returnValue;
	private int i;
	private int max;
	private int min;
	
	/**
	 * Calculates the department or departments with the most Patients.
	 * @return returnValue The department(s) name
	 */
	public String depMostPatients() {
		//Gets amount of patients in each department (excluding management as it has none)
		inSize = Inpatient.getInstance().getPatientList().size();
		outSize = Outpatient.getInstance().getPatientList().size();
		emSize = Emergency.getInstance().getPatientList().size();
		departments = new String[] {"Inpatient", "Outpatient", "Emergency"};
		sizes = new int[] {inSize, outSize, emSize};
		max = Integer.MIN_VALUE;
		
		//Goes through each department and compares against the prev. max
		i = 0;
		ArrayList<Integer> maxIndices = new ArrayList<Integer>(); 
		for(int depSize : sizes) {
			//if greater than prev. max: clear list and add this new value
			if(depSize > max) {
				maxIndices.clear();
				maxIndices.add(i);
				max = depSize;
				i++;
			}
			//if equal just add to the list
			else if(depSize == max) {
				maxIndices.add(i);
				i++;
			}
			else {
				i++;
			}	
		}
		
		//create a String to return containing the found department(s)
		returnValue = "";
		for(int maxIndex : maxIndices) {
			returnValue += departments[maxIndex] + ", ";
		}
		returnValue = returnValue.substring(0, returnValue.length()-2);
		return returnValue;
	}
	
	/**
	 * Calculates the department or departments with the least Patients.
	 * @return returnValue The department(s) name
	 */
	public String depLeastPatients() {
		//Gets amount of patients in each department (excluding management as it has none)
		inSize = Inpatient.getInstance().getPatientList().size();
		outSize = Outpatient.getInstance().getPatientList().size();
		emSize = Emergency.getInstance().getPatientList().size();
		departments = new String[] {"Inpatient", "Outpatient", "Emergency"};
		sizes = new int[] {inSize, outSize, emSize};
		min = Integer.MAX_VALUE;
				
		//Goes through each department and compares against the prev. min
		i = 0;
		ArrayList<Integer> minIndices = new ArrayList<Integer>(); 
		for(int depSize : sizes) {
			//if less than prev. min: clear list and add this
			if(depSize < min) {
				minIndices.clear();
				minIndices.add(i);
				min = depSize;
				i++;
			}
			//if equal just add to the list
			else if(depSize == min) {
				minIndices.add(i);
				i++;
			}
			else {
				i++;
			}	
		}
				
		//create a String to return containing the found department(s)
		returnValue = "";
		for(int minIndex : minIndices) {
			returnValue += departments[minIndex] + ", ";
		}
		returnValue = returnValue.substring(0, returnValue.length()-2);
		return returnValue;
	}
	
	/**
	 * Calculates the department or departments with the most Users.
	 * @return returnValue The department(s) name
	 */
	public String depMostUsers() {
		//Gets amount of users in each department
		inSize = Inpatient.getInstance().getUserList().size();
		outSize = Outpatient.getInstance().getUserList().size();
		emSize = Emergency.getInstance().getUserList().size();
		manSize = Management.getInstance().getUserList().size();		
		departments = new String[] {"Inpatient", "Outpatient", "Emergency", "Management"};
		sizes = new int[] {inSize, outSize, emSize, manSize};
		max = Integer.MIN_VALUE;
						
		//Goes through each department and compares against the prev. max
		i = 0;
		ArrayList<Integer> maxIndices = new ArrayList<Integer>(); 
		for(int depSize : sizes) {
			//if greater than prev. max: clear list and add this new value
			if(depSize > max) {
				maxIndices.clear();
				maxIndices.add(i);
				max = depSize;
				i++;
			}
			//if equal just add to the list
			else if(depSize == max) {
				maxIndices.add(i);
				i++;
			}
			else {
				i++;
			}	
		}
				
		//create a String to return containing the found department(s)
		returnValue = "";
		for(int maxIndex : maxIndices) {
			returnValue += departments[maxIndex] + ", ";
		}
		returnValue = returnValue.substring(0, returnValue.length()-2);
		return returnValue;
	}
	
	/**
	 * Calculates the department or departments with the least Uers.
	 * @return returnValue The department(s) name
	 */
	public String depLeastUsers() {
		//Gets amount of users in each department
		inSize = Inpatient.getInstance().getUserList().size();
		outSize = Outpatient.getInstance().getUserList().size();
		emSize = Emergency.getInstance().getUserList().size();
		manSize = Management.getInstance().getUserList().size();		
		departments = new String[] {"Inpatient", "Outpatient", "Emergency", "Management"};
		sizes = new int[] {inSize, outSize, emSize, manSize};
		min = Integer.MAX_VALUE;
		
		//Goes through each department and compares against the prev. min
		i = 0;
		ArrayList<Integer> minIndices = new ArrayList<Integer>(); 
		for(int depSize : sizes) {
			//if less than prev. min: clear list and add this
			if(depSize < min) {
				minIndices.clear();
				minIndices.add(i);
				min = depSize;
				i++;
			}
			//if equal just add to the list
			else if(depSize == min) {
				minIndices.add(i);
				i++;
			}
			else {
				i++;
			}	
		}
						
		//create a String to return containing the found department(s)
		returnValue = "";
		for(int minIndex : minIndices) {
			returnValue += departments[minIndex] + ", ";
		}
		returnValue = returnValue.substring(0, returnValue.length()-2);
		return returnValue;
	}
	
	/**
	 * Calculates the total number of patients in the hospital.
	 * @return The number of Patients
	 */
	public int totPatients() {
		inSize = Inpatient.getInstance().getPatientList().size();
		outSize = Outpatient.getInstance().getPatientList().size();
		emSize = Emergency.getInstance().getPatientList().size();
		return inSize + outSize + emSize;
	}
	
	/**
	 * Calculates the total number of users in the hospital.
	 * @return The number of Users
	 */
	public int totUsers() {
		inSize = Inpatient.getInstance().getUserList().size();
		outSize = Outpatient.getInstance().getUserList().size();
		emSize = Emergency.getInstance().getUserList().size();
		manSize = Management.getInstance().getUserList().size();
		return inSize + outSize + emSize + manSize;
	}
	
	/**
	 * Calculates the total number of Beds for each department.
	 * @return The name and number of beds for both departments
	 */
	public String bedStatus() {
		inSize = Inpatient.getInstance().getBedList().size();
		emSize = Emergency.getInstance().getBedList().size();
		
		returnValue = "Inpatient: " + inSize +
					  "\nEmergency: " + emSize;
		return returnValue;
	}
}