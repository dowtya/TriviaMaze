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


@SuppressWarnings("serial")
public class AnswerPanel extends JPanel implements ActionListener {
	
	ActionListener myAnswerResultHandler;
	
	ArrayList<JButton> mySingleChoiceButtons;
	JButton myShortAnswerSubmitButton;
	JTextField myShortAnswerTextField;
	boolean isCorrect;
	
	Function<Integer, Boolean> myEvalChoiceAnswerFunc;
	Function<String, Boolean> myEvalStringAnswerFunc;
	
	public AnswerPanel() {
		mySingleChoiceButtons = new ArrayList<JButton>();
		this.setBorder(BorderFactory.createTitledBorder("Answers"));
	}
	

	public void addSingleChoiceAnswers(final ArrayList<String> answers, Function<Integer, Boolean> theEvalChoiceAnswerFunc, ActionListener theAnswerResultHandler) {
		myAnswerResultHandler = theAnswerResultHandler;
		myEvalChoiceAnswerFunc = theEvalChoiceAnswerFunc;
		
		if (answers != null) {
			for (int i = 0; i < answers.size(); i++) {
				final JButton button = new JButton(i + ") " + answers.get(i));
				mySingleChoiceButtons.add(button);
				button.addActionListener(this);
				this.add(button);
			}
		}
	}
	
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
	
	public void reset() {
		mySingleChoiceButtons.clear();
		this.removeAll();
		myEvalStringAnswerFunc = null;
		myEvalChoiceAnswerFunc = null;
		myAnswerResultHandler = null;
		this.repaint();
	}
	
	public void actionPerformed(ActionEvent e) {	
		
		// Handle answer-clearing
		if (e.getSource() == this) {
			mySingleChoiceButtons.clear();
			this.removeAll();
		}
		
		// If this is coming from one of the buttons
		int buttonIndex = mySingleChoiceButtons.indexOf(e.getSource());
		if (buttonIndex != -1) {
			JButton button = (JButton) e.getSource();
			
			// Color the button depending on if the answer was correct
           isCorrect = myEvalChoiceAnswerFunc.apply(buttonIndex);
            if (isCorrect) {
            	button.setBackground(Color.GREEN);
            } else {
            	button.setBackground(Color.RED);
            }
            
            e.setSource(this);
            myAnswerResultHandler.actionPerformed(e);
            
            // Clear the answer panel after 1 second
            new Timer(1000, this).start();
		} else if (e.getSource() == myShortAnswerSubmitButton) {
			isCorrect = myEvalStringAnswerFunc.apply(myShortAnswerTextField.getText());
			e.setSource(this);
			myAnswerResultHandler.actionPerformed(e);
		}
	}
	
}
