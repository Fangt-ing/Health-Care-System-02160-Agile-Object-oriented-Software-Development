package gui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import gui.controller.RecordController;
import gui.model.Session;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * The view for the patient Record screen
 * @author Pieter O'Hearn
 *
 */
public class RecordView extends JFrame {
	// Static variables
	private static final long serialVersionUID = -4273488500574966476L;
	
	// Instance variables
	private RecordController controller;
	private JTextArea textArea;
	private JScrollPane jScrollPane1;
	private JLabel lblSession;
	private JLabel medicalRecord;

	/**
	 * Constructor for the Record View sets up the view by calling the initGUI() method.
	 * @param recordController The Record Controller
	 */
	public RecordView(RecordController recordController) {
		// set the RecordView and call initGUI
		this.controller = recordController;
		initGUI();
	}
	
	/**
	 * Initialises the Graphical User Interface
	 */
	private void initGUI() {
		// set the Default Close Operation, Tile and Window Size
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(String.format("Patient %s Medical Record",controller.getPatientId()));
		setPreferredSize(new Dimension(800, 600));
		
		// BUTTONS
		// Create a new button "Save" with an Action Listener and set Enabled to false
		JButton btnSave = new JButton("Save");
		btnSave.setEnabled(false);
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When clicked save the record
				controller.saveRecord(textArea.getText());
			}
		});
		
		// LABELS
		// replace all the "\n" with "<br>" symbols in the Patients record and add HTML tags
		String formatted = controller.getRecord().replace("\n", "<br>");
		formatted = "<html><font size='9'>Medical Record<br></font><font size='3'><div WIDTH=1>" + formatted + "</div></font></html>";
		
		// create a new JLabel medicalRecord with the newly formated String
		medicalRecord = new JLabel(formatted);
		medicalRecord.setHorizontalAlignment(SwingConstants.LEFT);
		
		// create a new JScrollPane with an empty border and add the recordPane
		JScrollPane recordPane = new JScrollPane(medicalRecord);
		recordPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(recordPane, BorderLayout.WEST);
		
		// create a second label for the session
		lblSession = new JLabel();
		lblSession.setHorizontalAlignment(SwingConstants.RIGHT);
		
		// TOOLBAR
		// create a new JToolBar and add the save button and Session label
		JToolBar toolbar = new JToolBar();
		toolbar.add(btnSave);
		toolbar.add(Box.createHorizontalGlue());
		toolbar.add(lblSession);
		
		// Place the toolbar on the top of the page
		add(toolbar, BorderLayout.NORTH);
		
		// create a new Text Area with LineWrap to enter the new record
		textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
         
        // create new JScrollPane and add the TextArea to it
        jScrollPane1 = new JScrollPane(textArea);
        jScrollPane1.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // add a documentListener to the textArea
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	// When Text is entered enable the save button
            	btnSave.setEnabled(true);
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) { }
            
			@Override
			public void changedUpdate(DocumentEvent e) {}
        });
		
        // add the textArea to the Record Frame
        add(jScrollPane1,BorderLayout.CENTER);
		
        // Pack the content to the window and place in the window in the center of the screen
		pack();
		setLocationRelativeTo(null);
	}

	/**
	 * Sets session label with current Users name.
	 * @param sessionModel the Session User
	 */
	public void setSession(Session sessionModel) {
		// set the text of the lblSession
		lblSession.setText("<html>" + sessionModel.getUser().getUserName() + " <i>(" + sessionModel.getUser().getType()+ ")</i></html>");
	}
}