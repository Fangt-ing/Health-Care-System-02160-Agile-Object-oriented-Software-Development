package gui.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import gui.model.Session;
import gui.view.RecordView;
import hospitalmanagementsystem.Patient;

/**
 * The Controller for the User view of the HMS GUI.
 * @author Pieter O'Hearn
 *
 */
public class RecordController {
	// Instance variables
	private Session sessionModel;
	private RecordView view;
	private ApplicationController applicationController;
	private Patient patient;

	/**
	 * The Constructor for the RecordController, initialises the instance variables.
	 * @param session The current session
	 * @param appController The main application controller
	 * @param patient The Patient whose record we are viewing
	 */
	public RecordController(Session session, ApplicationController appController, Patient patient) {
		// initialise the instance variables
		this.sessionModel = session;
		this.applicationController = appController;
		this.patient = patient;
	}

	/**
	 * Displays the Record view Window.
	 */
	public void display() {
		// set the HealthStaffView to Visible
		view.setVisible(true);
	}

	/**
	 * Initialises the health staff view.
	 * @param recordView The Record View
	 */
	public void setView(RecordView recordView) {
		// store the provided view and set the table model and session
		this.view = recordView;
		this.view.setSession(sessionModel);
	}

	/**
	 * Returns the record of the patient as a String.
	 * @return The Patients medical record
	 */
	public String getRecord() {
		// if the patient is not yet set, return an empty string
		if(this.patient.getRecord() == null) {
			return "";
		}

		// otherwise get the patients record and return
		return this.patient.getRecord();
	}

	/**
	 * Saves the patients record to the HMS and returns the department view, closing the Record view.
	 * @param text The addition to the Medical record
	 */
	public void saveRecord(String text) {
		// add the Users name and a time stamp to the entry
		String recordUpdate = "\n" + this.sessionModel.getUser().getUserID() + "  " + this.sessionModel.getUser().getUserName() + "\n" + LocalDate.now().toString() +"    " + LocalTime.now() + "\n" + text;

		// save the record to the HMS
		this.patient.setRecord(this.patient.getRecord() + recordUpdate);

		// close the record view
		this.view.setVisible(false);

		// open the users main department view
		if(this.sessionModel.getUser().getType().equals("Admin")) {
			this.applicationController.manageDisplay(this.sessionModel);
		} else {
			this.applicationController.healthStaffDisplay(this.sessionModel);
		}
	}

	/**
	 * Retrieves the Patients PatientID and returns it as a string.
	 * @return the patients patient ID
	 */
	public String getPatientId() {
		return this.patient.getPatientID();
	}
}
