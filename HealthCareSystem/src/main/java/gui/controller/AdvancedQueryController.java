package gui.controller;

import gui.model.PatientModel;
import gui.model.Session;
import gui.view.AdvancedQueryView;

/**
 * 
 * @author pieterohearn
 *
 */
public class AdvancedQueryController {
	// Instance variables
	private PatientModel patientModel;
	private Session sessionModel;
	private AdvancedQueryView view;
	private ApplicationController applicationController;


	/**
	 * The Constructor, initialises the instance variables.
	 * @param session The current Session
	 * @param appController The applicationController
	 */
	public AdvancedQueryController(Session session, ApplicationController appController) {
		// initialise the instance variables
		this.patientModel = session.getPatientModel();
		this.sessionModel = session;
		this.applicationController = appController;
	}
	
	/**
	 * Displays the AdvancedQuery View Window.
	 */
	public void display() {
		// set the UserView to Visible
		view.setVisible(true);
	}

	/**
	 * Initialises the user view.
	 * @param view The user View
	 */
	public void setView(AdvancedQueryView view) {
		// store the provided view and set the table model and session
		this.view = view;
		this.view.setTableModel(patientModel);
		this.view.setSession(sessionModel);
	}

	/**
	 * Resets the Patient Model to the original.
	 */
	public void clearSearch() {
		// retrieve the original patient model and set it again
		this.view.setTableModel(patientModel);
	}

	/**
	 * Querys the search and set the new patient model.
	 */
	public void query() {
		// TODO
	}
	
	/**
	 * Closes the Advanced Query View and returns to the users main view.
	 */
	public void back() {
		// close the record view
		this.view.setVisible(false);
		
		// open the users main department view
		if(this.sessionModel.getUser().getType().equals("Admin")) {
			this.applicationController.manageDisplay(this.sessionModel);
		} else if(this.sessionModel.getUser().getType().equals("User")) {
			this.applicationController.user(this.sessionModel);
		} else {
			this.applicationController.healthStaffDisplay(this.sessionModel);
		}
	}
}