package model;

/**
 * Class QuestionState which deals with the current Question State.
 * @author Alec Dowty
 * @author Aaron Gitell
 * @author Joel Hemphill
 *
 */
public class QuestionState implements java.io.Serializable  {

	/**
	 * Serialization ID for the class.
	 */
	private static final long serialVersionUID = 2029067419416282575L;
	
	/*
	 * Array of asked questions.
	 */
	private boolean myAskedQuestions[];
	
	/*
	 * Question Set ID.
	 */
	private int myQuestionSetID;
	
	/*
	 * The Current Question.
	 */
	private int myCurrentQuestion;
	
	/*
	 * If answered correctly.
	 */
	private boolean myAnsweredCorrectly = false; // A flag for if the last answered questions was answered correctly
	
	/**
	 * Getter for the asked questions.
	 * @return The asked questions.
	 */
	public boolean[] getAskedQuestions() {
		return myAskedQuestions;
	}
	
	/**
	 * Setter to set asked questions.
	 * @param theAskedQuestions Current asked questions.
	 */
	public void setAskedQuestions(final boolean[] theAskedQuestions) {
		myAskedQuestions = theAskedQuestions;
	}
	
	/**
	 * Getter for Question set ID.
	 * @return the current Question set ID.
	 */
	public int getQuestionSetID() {
		return myQuestionSetID;
	}
	
	/**
	 * Setter for setting the question set ID.
	 * @param theQuestionSetID The ID to set it to.
	 */
	public void setQuestionSetID(int theQuestionSetID) {
		myQuestionSetID = theQuestionSetID;
	}
	
	/**
	 * Getter for getting the current question.
	 * @return The current Question.
	 */
	public int getCurrentQuestion() {
		return myCurrentQuestion;
	}
	
	/**
	 * Setter for setting the current question.
	 * @param theCurrentQuestion The question to set it to.
	 */
	public void setCurrentQuestion(final int theCurrentQuestion) {
		myCurrentQuestion = theCurrentQuestion;
	}
	
	/**
	 * Method to see if a question was answered correctly.
	 * @return Boolean true or false for correct or incorrect.
	 */
	public boolean isAnsweredCorrectly() {
		return myAnsweredCorrectly;
	}
	
	/**
	 * Setter to set if a question was answered correctly or not.
	 * @param theAnsweredCorrectly The boolean to use to set it true/false.
	 */
	public void setAnsweredCorrectly(final boolean theAnsweredCorrectly) {
		myAnsweredCorrectly = theAnsweredCorrectly;
	}
	
}
