package gui.model;

import java.util.ArrayList;
import java.util.Objects;
import javax.swing.table.AbstractTableModel;
import hospitalmanagementsystem.departments.*;
import hospitalmanagementsystem.users.Admin;
import hospitalmanagementsystem.users.HealthStaff;
import hospitalmanagementsystem.users.User;

/**
 * The Model for the User's Table.
 * @author pieterohearn
 *
 */
public class UserModel extends AbstractTableModel {
	// Instance variables
	private static final long serialVersionUID = -8100080945085186023L;
	private ArrayList<User> users;
	
	/**
	 * The Constructor of the UserModel. Creates an array of patients loaded from memory.
	 */
	public UserModel() {
		// create a new PersistenceLayer and load the Patients TODO
//		PersistenceLayer persistenceLayer = new PersistenceLayer();
//		persistenceLayer.load("Departments/Emergency/Users");
//		persistenceLayer.load("Departments/Inpatient/Users");
//		persistenceLayer.load("Departments/Outpatient/Users");
//		persistenceLayer.load("Departments/Management/Users");
		
		// create a new ArrayList of Users
		users = new ArrayList<User>();
		
		// add any saved patients into the ArrayList
		users.addAll(Emergency.getInstance().getUserList());
		users.addAll(Inpatient.getInstance().getUserList());
		users.addAll(Outpatient.getInstance().getUserList());
		users.addAll(Management.getInstance().getUserList());
	}
	
	/**
	 * Adds a new User to the HMS.
	 * @param newUser The new User
	 */
	public void addNewUser(User newUser) {
		// add the new User to the users List
		users.add(newUser);
		
		// TODO Add the User to the HMS
		
		
		// notify the views that data changed
		fireTableDataChanged();
	}
	
	/**
	 * Remove a User from the HMS.
	 * @param userID The user ID of the User
	 * @param admin The Admin Removing them
	 */
	public void removeUser(String userID, Admin admin) {
		// Find the user with this userID
		User toRemove = findUser(userID);
		
		// remove from list
		users.remove(toRemove);
		
		// remove form HMS
		admin.removeUser(toRemove);
		
		// notify the views that data changed
		fireTableDataChanged();
	}
	
	/**
	 * Updates the department of the given user
	 * @param userID The Users ID
	 * @param dept The department
	 */
	public void updateDepartment(String userID, Department dept) {
		// find the user with this userID
		HealthStaff u = (HealthStaff) findUser(userID);
		
		// update the users department
		u.moveDepartment(dept);
		
		// notify the views that data changed
		fireTableDataChanged();
	}
	
	/**
	 * Finds the User with a given unique userID.
	 * @param userId The user ID
	 * @return The User
	 */
	private User findUser(String userId) {
		// search all users in the user list
		for(User u : users) {
			// if the userIDs match, return u
			if(u.getUserID().equals(userId)) {
				return u;
			}
		}
		
		// else return null
		return null;
	}

	/**
	 * Edits the Users Information.
	 * @param userId The User ID
	 * @param name The Users Name
	 * @param address The Users Address
	 * @param phone The Users Phone Number
	 */
	public void edit(String userId, String name, String address, String phone) {
		// find the user with this userID
		User toEdit = findUser(userId);
		
		// set the users information to that provided by the arguments
		toEdit.setAddress(address);
		toEdit.setPhone(phone);
		toEdit.setUserName(name);
		
		// notify the views that data changed
		fireTableDataChanged();
	}
	
	/////////////////////////////////////////
	// METHODS BELOW TO EXTEND TABLE MODEL //
	/////////////////////////////////////////
	
	@Override
	public int getColumnCount() {
		return 5; // this is fixed: product and quantity
	}

	@Override
	public int getRowCount() {
		return users.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return users.get(rowIndex).getUserID();
		} else if (columnIndex == 1) {
			return users.get(rowIndex).getUserName();
		} else if (columnIndex == 2) {
			if(Objects.equals(null, users.get(rowIndex).getDepartment())) {
				return "-";
			} else {
				return users.get(rowIndex).getDepartment().getName();
			}
		} else if (columnIndex == 3) {
			return users.get(rowIndex).getAddress();
		} else if (columnIndex == 4) {
			return users.get(rowIndex).getNumber();
		}
		return null;
	}
	
	@Override
	public String getColumnName(int column) {
		if (column == 0) {
			return "User ID";
		} else if (column == 1) {
			return "Name";
		} else if (column == 2) {
			return "Department";
		} else if (column == 3) {
			return "Address";
		} else if (column == 4) {
			return "Phone Number";
		}
		return null;
	}
}