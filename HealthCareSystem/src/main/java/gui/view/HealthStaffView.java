package gui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import gui.controller.HealthStaffController;
import gui.model.Session;
import hospitalmanagementsystem.Query;

/**
 * The View for the HealthStaff Users
 * @author Pieter O'Hearn
 *
 */
public class HealthStaffView extends JFrame {
	// Static Variables
	private static final long serialVersionUID = -5921945552466222819L;
	
	// Instance Variables
	private HealthStaffController controller;
	private JTable tblPatients;
	private JLabel lblSession;
	private JScrollPane patientPane;
	private JTextField idSearchField;
	private JTextField nameSearchField;
	private JTextField surnameSearchField;
	private JTextField departmentSearchField;
	
	/**
	 * Constructor for the HealthStaff View sets up the view by calling the initGUI() method.
	 * @param controller The HealthStaff Controller
	 */
	public HealthStaffView(HealthStaffController controller) {
		// set the HealthStaffView and call initGUI
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
		
		// BUTTONS
		// Create a new button "Edit" with an Action Listener and set Enabled to false
		JButton btnEdit = new JButton("Edit");
		btnEdit.setEnabled(false);
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When clicked edit the selected patient
				controller.editPatient(tblPatients.getSelectedRow());
			}
		});
		
		// Create a new button "Register Patient" with an Action Listener
		JButton btnRegisterPatient = new JButton("Register");
		btnRegisterPatient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When clicked add a new patient
				controller.addPatient();
			}
		});
		
		// Create a new button "Admit Patient" with an Action Listener and set Enabled to false
		JButton btnAdmitPatient = new JButton("Admit");
		btnAdmitPatient.setEnabled(false);
		btnAdmitPatient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When clicked admit a new Patient
				controller.admitPatient(tblPatients.getSelectedRow());
			}
		});
		
		// Create a new button "Discharge Patient" with an Action Listener and set Enabled to false
		JButton btnDischargePatient = new JButton("Discharge");
		btnDischargePatient.setEnabled(false);
		btnDischargePatient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When clicked discharge the selected patient
				controller.dischargePatient(tblPatients.getSelectedRow());
			}
		});
		
		// Create a new button "Log Out" with an Action Listener
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When selected, Log Out
				controller.logOut();
			}
		});
		
		// Create a new button "Record" with an Action Listener and set Enabled to false
		JButton btnRecord = new JButton("Record");
		btnRecord.setEnabled(false);
		btnRecord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When selected show the selected patients medical record
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
				// When clicked Search for the patient(s)
				controller.patientSearch(idSearchField.getText(), nameSearchField.getText(), surnameSearchField.getText(), departmentSearchField.getText());
				btnClear.setEnabled(true);
			}
		});
		
		// Create a new button "Query" with an Action Listener
		JButton btnQuery = new JButton("Query");
		btnQuery.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When clicked Display the JPanel using a JOptionPane and store the confirmation result
				JPanel queryPanel = new JPanel();
				String[] typesOfQuerys = { "Department with most Patients", "Department with least Patients", "Department with most Users", "Department with least Users", "Total number of Patients", "Total number of Users" };
				JComboBox<String> querys = new JComboBox<String>(typesOfQuerys);
				queryPanel.add(querys);
	
		        int confirmation = JOptionPane.showConfirmDialog(null, queryPanel, "Please Choose an Advanced Query", JOptionPane.OK_CANCEL_OPTION);

		        // check the confirmation result
		        if (confirmation == JOptionPane.OK_OPTION) {
		        	// if OK was selected create a new result label
		        	JLabel result = new JLabel();
		        	
		        	// create a new Advanced Query Object
		        	Query q = new Query();
		        	
		        	// check what query was selected
		        	if(querys.getSelectedIndex() == 0) {
		        		// Print the result
		        		result.setText("Department with most Patients:  " + q.depMostPatients());
		        	} else if(querys.getSelectedIndex() == 1) {
		        		// Print the result
		        		result.setText("Department with least Patients:  " + q.depLeastPatients());
		        	} else if(querys.getSelectedIndex() == 2) {
		        		// Print the result
		        		result.setText("Department with most Users:  " + q.depMostUsers());
		        	} else if(querys.getSelectedIndex() == 3) {
		        		// Print the result
		        		result.setText("Department with least Users:  " + q.depLeastUsers());
		        	} else if(querys.getSelectedIndex() == 4) {
		        		// Print the result
		        		result.setText("Total number of Patients:  " + q.totPatients());
		        	} else if(querys.getSelectedIndex() == 5) {
		        		// Print the result
		        		result.setText("Total number of Users:  " + q.totUsers());
		        	} 
		        	
		        	JOptionPane.showConfirmDialog(null, result, "Advanced Query Result", JOptionPane.OK_CANCEL_OPTION);
		        }
			}
		});

		// Create a new button "Remove" with an Action Listener and set Enabled to false
		JButton btnRemove = new JButton("Check Out");
		btnRemove.setEnabled(false);
		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When clicked remove the selected patient
				controller.removePatient(tblPatients.getSelectedRow());
			}
		});
		
		// Create a new button "Assign Bed" with an Action Listener and set Enabled to false
		JButton btnAssignBed = new JButton("Assign Bed");
		btnAssignBed.setEnabled(false);
		btnAssignBed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When clicked assign a new bed to the patient
				controller.assignBed(tblPatients.getSelectedRow());
			}
		});
		
		// TOOLBAR
		// create a new label and set Horizontal Alignment right
		lblSession = new JLabel();
		lblSession.setHorizontalAlignment(SwingConstants.RIGHT);
		
		// create a new JToo;Bar and add all buttons and the above label
		JToolBar toolbar = new JToolBar();
		toolbar.add(btnRegisterPatient);
		toolbar.add(btnAdmitPatient);
		toolbar.add(btnDischargePatient);
		toolbar.add(btnAssignBed);
		toolbar.add(btnEdit);
		toolbar.add(btnRemove);
		toolbar.add(btnRecord);
		toolbar.add(btnQuery);
		toolbar.add(Box.createHorizontalGlue());
		toolbar.add(lblSession);
		toolbar.add(btnLogOut);
		
		// create a label and Text are for the search bar
		JLabel lblSearchID = new JLabel("ID:");
		idSearchField = new JTextField(5);
		JLabel lblSearchName = new JLabel("First Name:");
		nameSearchField = new JTextField(5);
		JLabel lblSurnameSearch = new JLabel("Last Name:");
		surnameSearchField = new JTextField(5);
		JLabel lblSearchDepartment = new JLabel("Department:");
		departmentSearchField = new JTextField(5);
		
		// create a new JToolBar and add all search features
		JToolBar searchBar = new JToolBar();
		searchBar.add(lblSearchID);
		searchBar.add(idSearchField);
		searchBar.add(lblSearchName);
		searchBar.add(nameSearchField);
		searchBar.add(lblSurnameSearch);
		searchBar.add(surnameSearchField);
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
		// create a table for the patients with an Selection Listener and set the Selection mode to single selection
		tblPatients = new JTable();
		tblPatients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblPatients.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// When a row is selected enable the following buttons
				btnDischargePatient.setEnabled((tblPatients.getSelectedRow() >= 0 && tblPatients.getValueAt(tblPatients.getSelectedRow(), 3) != "-"));
				btnAdmitPatient.setEnabled((tblPatients.getSelectedRow() >= 0 && tblPatients.getValueAt(tblPatients.getSelectedRow(), 3) == "-"));
				btnEdit.setEnabled((tblPatients.getSelectedRow() >= 0));
				btnRecord.setEnabled((tblPatients.getSelectedRow() >= 0));
				btnRemove.setEnabled((tblPatients.getSelectedRow() >= 0));
				btnAssignBed.setEnabled((tblPatients.getSelectedRow() >= 0));
			}
		});
		
		// add the table to a new JScrollPane and add to the HealthStaff Frame
		patientPane = new JScrollPane(tblPatients);
		add(patientPane);
		
		// Pack the content to the window and place in the window in the center of the screen
		pack();
		setLocationRelativeTo(null);
	}
	
	/**
	 * Sets the table model for the Patients table
	 * @param patientModel The table model
	 */
	public void setTableModel(TableModel patientModel) {
		// set the model of the table patient
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