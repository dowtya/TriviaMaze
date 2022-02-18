package model;

public class QuestionState implements java.io.Serializable  {

	// A list of questions
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2029067419416282575L;
	
	private boolean myAskedQuestions[]; // An array of flags marking which have been asked
	private int myQuestionSetID; // The ID of the question set
	private int myCurrentQuestion; // The index of the current question
	private boolean myAnsweredCorrectly = false; // A flag for if the last answered questions was answered correctly
	
	public boolean[] getAskedQuestions() {
		return myAskedQuestions;
	}
	
	public void setAskedQuestions(final boolean[] theAskedQuestions) {
		myAskedQuestions = theAskedQuestions;
	}
	
	public int getQuestionSetID() {
		return myQuestionSetID;
	}

	public void setQuestionSetID(int theQuestionSetID) {
		myQuestionSetID = theQuestionSetID;
	}
	
	public int getCurrentQuestion() {
		return myCurrentQuestion;
	}
	
	public void setCurrentQuestion(final int theCurrentQuestion) {
		myCurrentQuestion = theCurrentQuestion;
	}
	
	public boolean isAnsweredCorrectly() {
		return myAnsweredCorrectly;
	}
	
	public void setAnsweredCorrectly(final boolean theAnsweredCorrectly) {
		myAnsweredCorrectly = theAnsweredCorrectly;
	}
	
}
