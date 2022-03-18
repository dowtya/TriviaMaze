package gui;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.function.Function;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import controller.Game;

/**
 * QuestionBox class which handles both the display of the question, as well as the submission of answers.
 * @author Alec Dowty
 * @author Aaron Gitell
 * @author Joel Hemphill
 */
@SuppressWarnings("serial")
public class QuestionBox extends JPanel implements ActionListener {
	
	/*
	 * A reference to the owning Game Object
	 */
	private Game myGame;
	
	/*
	 * The text box which displays the text of the question.
	 */
	private JLabel myQuestionPanel;
	
	/*
	 * The panel which contains the answers selection/submission elements.
	 */
	private AnswerPanel myAnswerPanel;
	
	/**
	 * Initializes swing components of the QuestionBox, and creates the question and answer panels.
	 * @param theGame the owning Game object
	 */
	public QuestionBox(Game theGame) {
		myGame = theGame;
		
		myQuestionPanel = new JLabel();
		myAnswerPanel = new AnswerPanel();
		myAnswerPanel.setBounds(50, 20, 400, 200);
		myAnswerPanel.setBackground(Color.GRAY);
		
		myQuestionPanel.setBorder(BorderFactory.createTitledBorder("Question"));
		myQuestionPanel.setBounds(50, 240, 400, 200);
		myQuestionPanel.setBackground(Color.GRAY);
		this.add(myQuestionPanel);
		this.add(myAnswerPanel);
		this.setBounds(0,0,000,1000);
		this.setLayout(new GridLayout(2,1));
		this.repaint();
	}
	
	/**
	 * Resets the QuestionBox and triggers the next phase of the game loop.
	 * @param theEvent The event object
	 */
	@Override
	public void actionPerformed(ActionEvent theEvent) {
		// on answer result received
		if (theEvent.getSource() == myAnswerPanel) {
			myAnswerPanel.reset();
			myQuestionPanel.removeAll();
    		myGame.handleQuestionResolution();
		}
	}
	
	/**
	 * Displays a short-answer question for the user to answer.
	 * @param theQuestion The text of the question
	 * @param theEvalAnswerFunc A function which evaluates a provided string, to see if the string matches the correct answer
	 */
	public void displayShortAnswerQuestion(String theQuestion, Function<String, Boolean> theEvalAnswerFunc) {
		myAnswerPanel.reset();
		myQuestionPanel.removeAll();
		
		myQuestionPanel.setText(theQuestion);
		myAnswerPanel.addShortAnswer(theEvalAnswerFunc, this);
		
		myQuestionPanel.repaint();
		myAnswerPanel.repaint();
	}
	
	/**
	 * Displays a single-choice question for the user to answer.
	 * @param theQuestion The text of the question
	 * @param theAnswers the array of options to provide the user
	 * @param theEvalAnswerFunc A function which evaluates a provided index into the array of answers, to see if the it matches the correct answer.
	 */
	public void displaySingleChoiceQuestion(String theQuestion, ArrayList<String> theAnswers, Function<Integer, Boolean> theEvalAnswerFunc) {
		myAnswerPanel.reset();
		myQuestionPanel.removeAll();
		
		myQuestionPanel.setText(theQuestion);
		myAnswerPanel.addSingleChoiceAnswers(theAnswers, theEvalAnswerFunc, this);
		
		myQuestionPanel.repaint();
		myAnswerPanel.repaint();
	}	
}
