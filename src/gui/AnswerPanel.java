package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.function.Function;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;


/**
 * AnswerPanel class which handles the display and submission of answers.
 * @author Alec Dowty
 * @author Aaron Gitell
 * @author Joel Hemphill
 */

@SuppressWarnings("serial")
public class AnswerPanel extends JPanel implements ActionListener {
	
	/*
	 * A reference to the ActionListener QuestionBox which handles the submitted answer
	 */
	private ActionListener myAnswerResultHandler;
	
	/*
	 * The list of all active single-choice buttons
	 */
	private ArrayList<JButton> mySingleChoiceButtons;
	
	/*
	 * The button used to submit short answers
	 */
	private JButton myShortAnswerSubmitButton;
	
	/*
	 * The TextField used to submit short answers
	 */
	private JTextField myShortAnswerTextField;
	
	/*
	 * The variable used to store the evaluation of the submitted answer
	 */
	private boolean myIsCorrect;
	
	/*
	 * The function used to evaluate single-choice questions
	 */
	private Function<Integer, Boolean> myEvalChoiceAnswerFunc;
	
	/*
	 * the function used to evaluate short-answer questions
	 */
	private Function<String, Boolean> myEvalStringAnswerFunc;
	
	
	/**
	 * A simple constructor for the AnswerPanel, which initialize visual settings and allocates the button ArrayList.
	 */
	public AnswerPanel() {
		mySingleChoiceButtons = new ArrayList<JButton>();
		this.setBorder(BorderFactory.createTitledBorder("Answers"));
	}
	
	/**
	 * Adds options to the AnswerPanel for a single-choice question.
	 * @param answers An ArrayList of strings which represent the text to be displayed as separate choices in a single-choice question.
	 * @param theEvalChoiceAnswerFunc A function that determines whether a provided index into the array of provided answers is correct or incorrect
	 * @param theAnswerResultHandler The parent 'QuestionBox' ActionListener, which handles answer resolution
	 */
	public void addSingleChoiceAnswers(final ArrayList<String> answers, Function<Integer, Boolean> theEvalChoiceAnswerFunc, ActionListener theAnswerResultHandler) {
		myAnswerResultHandler = theAnswerResultHandler;
		myEvalChoiceAnswerFunc = theEvalChoiceAnswerFunc;
		
		if (answers != null) {
			for (int i = 0; i < answers.size(); i++) {
				final JButton button = new JButton((i+1) + ") " + answers.get(i));
				mySingleChoiceButtons.add(button);
				button.addActionListener(this);
				this.add(button);
			}
		}
	}
	
	/**
	 * Adds options to the AnswerPanel for a short-answer question.
	 * @param theEvalStringAnswerFunc A function that determines whether a provided string is correct or incorrect
	 * @param theAnswerResultHandler The parent 'QuestionBox' ActionListener, which handles answer resolution
	 */
	public void addShortAnswer(Function<String, Boolean> theEvalStringAnswerFunc, ActionListener theAnswerResultHandler) {
		myAnswerResultHandler = theAnswerResultHandler;
		myEvalStringAnswerFunc = theEvalStringAnswerFunc;
		myShortAnswerTextField = new JTextField();
		myShortAnswerTextField.setBounds(0, 0, 200, 20);
		myShortAnswerSubmitButton = new JButton();
		myShortAnswerSubmitButton.setText("Submit");
		myShortAnswerSubmitButton.addActionListener(this);
		this.add(myShortAnswerTextField);
		this.add(myShortAnswerSubmitButton);
		this.setLayout(new GridLayout(2, 1));
	}
	
	/**
	 * Destroys all the buttons used for answer submission.
	 */
	public void reset() {
		mySingleChoiceButtons.clear();
		this.removeAll();
		myEvalStringAnswerFunc = null;
		myEvalChoiceAnswerFunc = null;
		myAnswerResultHandler = null;
		this.repaint();
	}
	
	/**
	 * Handles button-press/text-enter callback.
	 * @param theEvent The event object
	 */
	@Override
	public void actionPerformed(ActionEvent theEvent) {	
		
		// Handle answer-clearing
		if (theEvent.getSource() == this) {
			mySingleChoiceButtons.clear();
			this.removeAll();
		}
		
		// If this is coming from one of the buttons
		int buttonIndex = mySingleChoiceButtons.indexOf(theEvent.getSource());
		if (buttonIndex != -1) {
			JButton button = (JButton) theEvent.getSource();
			
			// Color the button depending on if the answer was correct
           myIsCorrect = myEvalChoiceAnswerFunc.apply(buttonIndex);
            if (myIsCorrect) {
            	button.setBackground(Color.GREEN);
            } else {
            	button.setBackground(Color.RED);
            }
            
            theEvent.setSource(this);
            myAnswerResultHandler.actionPerformed(theEvent);
            
            // Clear the answer panel after 1 second
            new Timer(1000, this).start();
		} else if (theEvent.getSource() == myShortAnswerSubmitButton) {
			myIsCorrect = myEvalStringAnswerFunc.apply(myShortAnswerTextField.getText());
			theEvent.setSource(this);
			myAnswerResultHandler.actionPerformed(theEvent);
		}
	}

}
