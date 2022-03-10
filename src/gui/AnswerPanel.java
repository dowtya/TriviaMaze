package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.function.Function;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class AnswerPanel extends JPanel implements ActionListener {
	
	ActionListener myAnswerResultHandler;
	
	ArrayList<JButton> mySingleChoiceButtons;
	boolean isCorrect;
	
	Function<Integer, Boolean> myEvalAnswerFunc;
	
	public AnswerPanel() {
		mySingleChoiceButtons = new ArrayList<JButton>();
		this.setBorder(BorderFactory.createTitledBorder("Answers"));
	}
	
	public void addSingleChoiceAnswers(final String[] answers, Function<Integer, Boolean> theEvalAnswerFunc, ActionListener theAnswerResultHandler) {
		myAnswerResultHandler = theAnswerResultHandler;
		myEvalAnswerFunc = theEvalAnswerFunc;
		
		if (answers != null) {
			for (int i = 0; i < answers.length; i++) {
				final JButton button = new JButton(i + ") " + answers[i]);
				mySingleChoiceButtons.add(button);
				button.addActionListener(this);
				this.add(button);
			}
		}
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
            boolean wasCorrect = myEvalAnswerFunc.apply(buttonIndex);
            if (wasCorrect) {
            	button.setBackground(Color.GREEN);
            } else {
            	button.setBackground(Color.RED);
            }
            
            e.setSource(this);
            myAnswerResultHandler.actionPerformed(e);
            
            // Clear the answer panel after 1 second
            new Timer(1000, this).start();
		}
	}
	
}
