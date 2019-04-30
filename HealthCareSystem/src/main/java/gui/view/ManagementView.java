package gui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import gui.controller.*;
import gui.model.Session;

/**
 * The view for the Admin Users
 * @author Pieter O'Hearn
 *
 */
public class ManagementView extends JFrame {
	// Static variable
	private static final long serialVersionUID = -4500084641006846370L;
	
	//Instance variables
	private ManagementController controller;
	private JTabbedPane tabbedPane;
	private JTable tblUsers;
	private JTable tblPatients;
	private JLabel lblSession;
	private JScrollPane userPane;
	private JScrollPane patientPane;
	private JTextField idSearchField;
	private JTextField nameSearchField;
	private JTextField thirdSearchField;
	private JTextField departmentSearchField;
	private JLabel lblSearchName;
	private JLabel lblThirdSearch;
	
	/**
	 * Constructor for the Management View sets up the view by calling the initGUI() method.
	 * @param controller The Management Controller
	 */
	public ManagementView(ManagementController controller) {
		// set the ManagementView and call initGUI
		this.controller = controller;
		initGUI();
	}
	
	/**
	 * Initialises the Graphical User Interface
	 */
	private void initGUI() {
		// set the Default Close Operation, Tile and Window Size
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Hospital Management System");
		setPreferredSize(new Dimension(800, 600));
		
		// Create a new tabbed Pane and set the tab placement to the top
		tabbedPane = new JTabbedPane();
		tabbedPane.setTabPlacement(JTabbedPane.TOP);
		
		// BUTTONS
		// Create a new button "Add User" with an Action Listener and set Enabled to false
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When clicked add a user 
				controller.addUser();
			}
		});
		
		// Create a new button "Edit" with an Action Listener and set Enabled to false
		JButton btnEdit = new JButton("Edit");
		btnEdit.setEnabled(false);
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When clicked check what tab is selected
				if(tabbedPane.getSelectedIndex() == 0 && tblUsers.getSelectedRow() >= 0) {
					// if the selected tab is the user tab edit the selected User
					controller.editUser(tblUsers.getSelectedRow());
				} else if(tabbedPane.getSelectedIndex() == 1 && tblPatients.getSelectedRow() >= 0) {
					// if the selected tab is the patient tab edit the selected Patient
					controller.editPatient(tblPatients.getSelectedRow());
				}
			}
		});
		
		// Create a new button "Remove User" with an Action Listener and set Enabled to false
		JButton btnRemoveUser = new JButton("Remove User");
		btnRemoveUser.setEnabled(false);
		btnRemoveUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When clicked remove the selected User
				controller.removeUser(tblUsers.getSelectedRow());
			}
		});
		
		// Create a new button "Register Patient" with an Action Listener and set Enabled to false
		JButton btnRegisterPatient = new JButton("Register Patient");
		btnRegisterPatient.setEnabled(false);
		btnRegisterPatient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When clicked add a new Patient
				controller.addPatient();
			}
		});
		
		// Create a new button "Admit Patient" with an Action Listener and set Enabled to false
		JButton btnAdmitPatient = new JButton("Admit Patient");
		btnAdmitPatient.setEnabled(false);
		btnAdmitPatient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When clicked admit the selected patient
				controller.admitPatient(tblPatients.getSelectedRow());
			}
		});
		
		// Create a new button "Discharge Patient" with an Action Listener and set Enabled to false
		JButton btnDischargePatient = new JButton("Discharge Patient");
		btnDischargePatient.setEnabled(false);
		btnDischargePatient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.dischargePatient(tblPatients.getSelectedRow());
			}
		});
		
		// Create a new button "Log Out" with an Action Listener
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When clicked Log Out
				controller.logOut();
			}
		});
		
		// Create a new button "Record" with an Action Listener and set Enabled to false
		JButton btnRecord = new JButton("Record");
		btnRecord.setEnabled(false);
		btnRecord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When clicked show the selected patient record
				controller.showRecord(tblPatients.getSelectedRow());
			}
		});
		
		// Create a new button "Clear" with an Action Listener and set Enabled to false
		JButton btnClear = new JButton("Clear");
		btnClear.setEnabled(false);
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When clicked reset to the original patient model
				controller.clearSearch();
				btnClear.setEnabled(false);
			}
		});
		
		// Create a new button "Search" with an Action Listener
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When clicked Search
				btnClear.setEnabled(true);
				
				if(tabbedPane.getSelectedIndex() == 1) {
					controller.patientSearch(idSearchField.getText(), nameSearchField.getText(), thirdSearchField.getText(), departmentSearchField.getText());
				} else if(tabbedPane.getSelectedIndex() == 0) {
					controller.userSearch(idSearchField.getText(), nameSearchField.getText(), thirdSearchField.getText(), departmentSearchField.getText());
				}
			}
		});
		
		// Create a new button "Query" with an Action Listener
		JButton btnQuery = new JButton("Query");
		btnQuery.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When clicked reset to the original patient model
				controller.advancedQuery();
			}
		});
		
		// TOOLBAR
		// create a new label and set Horizontal Alignment right
		lblSession = new JLabel();
		lblSession.setHorizontalAlignment(SwingConstants.RIGHT);
		
		// create a new JToolBar and add all buttons and the above label
		JToolBar toolbar = new JToolBar();
		toolbar.add(btnAddUser);
		toolbar.add(btnRemoveUser);
		toolbar.add(btnRegisterPatient);
		toolbar.add(btnAdmitPatient);
		toolbar.add(btnDischargePatient);
		toolbar.add(btnEdit);
		toolbar.add(btnRecord);
		toolbar.add(btnQuery);
		toolbar.add(Box.createHorizontalGlue());
		toolbar.add(lblSession);
		toolbar.add(btnLogOut);
		
		// create a label and Text are for the search bar
		JLabel lblSearchID = new JLabel("ID:");
		idSearchField = new JTextField(5);
		lblSearchName = new JLabel("Name:");
		nameSearchField = new JTextField(5);
		lblThirdSearch = new JLabel("Email:");
		thirdSearchField = new JTextField(5);
		JLabel lblSearchDepartment = new JLabel("Department:");
		departmentSearchField = new JTextField(5);
		
		// create a new JToolBar and add all search features
		JToolBar searchBar = new JToolBar();
		searchBar.add(lblSearchID);
		searchBar.add(idSearchField);
		searchBar.add(lblSearchName);
		searchBar.add(nameSearchField);
		searchBar.add(lblThirdSearch);
		searchBar.add(thirdSearchField);
		searchBar.add(lblSearchDepartment);
		searchBar.add(departmentSearchField);
		searchBar.add(btnSearch);
		searchBar.add(btnClear);
		
		// Place the toolbars in a JPanel with a Grid Layout and add to the Frame
		JPanel toolbars = new JPanel(new GridLayout(0, 1));
		toolbars.add(toolbar);
		toolbars.add(searchBar);
		add(toolbars, BorderLayout.NORTH);
		
		// TABLES
		// create a table for the users with an Selection Listener and set the Selection mode to single selection
		tblUsers = new JTable();
		tblUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblUsers.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// When a row is selected enable the following buttons
				btnRemoveUser.setEnabled((tblUsers.getSelectedRow() >= 0));
				btnEdit.setEnabled((tabbedPane.getSelectedIndex() == 0 && tblUsers.getSelectedRow() >= 0));
			}
		});
		
		// add the user table to a new JScrollPane
		userPane = new JScrollPane(tblUsers);
		
		// create a table for the patient with an Selection Listener and set the Selection mode to single selection
		tblPatients = new JTable();
		tblPatients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblPatients.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// When a row is selected enable the following buttons
				btnDischargePatient.setEnabled((tblPatients.getSelectedRow() >= 0));
				btnAdmitPatient.setEnabled((tblPatients.getSelectedRow() >= 0));
				btnEdit.setEnabled((tabbedPane.getSelectedIndex() == 1 && tblPatients.getSelectedRow() >= 0));
				btnRecord.setEnabled((tblPatients.getSelectedRow() >= 0));
			}
		});
		
		// add the patient table to a new JScrollPane
		patientPane = new JScrollPane(tblPatients);
		
		// Add the userPane and Patient Pane to the tabbed Pane
		tabbedPane.addTab("Users", userPane);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_0);
		tabbedPane.addTab("Patients", patientPane);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_1);
		
		// add a Change Listener to the tabbedPane
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent changeEvent) {
				// if the Users tab is selected
				if(tabbedPane.getSelectedIndex() == 0) {
					// enable the AddUser button
					btnAddUser.setEnabled(true);
					
					// and disable the following buttons
					btnRegisterPatient.setEnabled(false);
					btnAdmitPatient.setEnabled(false);
					btnDischargePatient.setEnabled(false);
					btnRecord.setEnabled(false);
					
					// change the following labels
					lblSearchName.setText("Name:");
					lblThirdSearch.setText("Email:");
				} // if the Patients tab is selected  
				else if(tabbedPane.getSelectedIndex() == 1) {
					// enable the Register Patient button
					btnRegisterPatient.setEnabled(true);
					
					// and disable the following buttons
					btnAddUser.setEnabled(false);
					btnRemoveUser.setEnabled(false);
					
					// change the following labels
					lblSearchName.setText("First Name:");
					lblThirdSearch.setText("Last Name:");
				}
			}
		});
		
		//The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
        // add the tabbedPane to the Management Frame
     	add(tabbedPane);
		
     	// Pack the content to the window and place in the window in the center of the screen
		pack();
		setLocationRelativeTo(null);
	}
	
	/**
	 * Sets the table model for the Patient and User tables
	 * @param userModel The Model for the User table
	 * @param patientModel The Model for the Patient table
	 */
	public void setTableModel(TableModel userModel,TableModel patientModel) {
		// set the model of the table patient and user patient
		tblUsers.setModel(userModel);
		tblPatients.setModel(patientModel);
	}

	/**
	 * Sets session label with current Users name.
	 * @param sessionModel the Session User
	 */
	public void setSession(Session sessionModel) {
		// set the text of the lblSession
		lblSession.setText("<html>" + sessionModel.getUser().getUserName() + " <i>(" + sessionModel.getUser().getType()+ ") </i></html>");
	}

	/**
	 * Shows an Error Dialog with a given message
	 * @param message The given message
	 */
	public void showError(String errorMessage) {
		// Show an Error message in a new JOptionPane
		JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
	}
}