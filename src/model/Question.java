/*
 * TriviaMaze Project
 * TCSS 360
 * Winter 2022
 */
package model;

/**
 * This class represents a row from the questions.db table.
 * 
 * 
 * @author Aaron Gitell
 * @version 3/18/2022
 */
public class Question {
	
	/**
	 * Type of question.
	 */
	private String myType;
	
	/**
	 * A question.
	 */
	private String myQuestion;
	
	/**
	 * The answer to the question.
	 */
	private String myAnswer;
	
	/**
	 * First potential choice for the answer.
	 */
	private String myChoice1;
	
	/**
	 * Second potential choice for the answer.
	 */
	private String myChoice2;
	
	/**
	 * Third potential choice for the answer.
	 */
	private String myChoice3;
	
	/**
	 * Constructor that initiates the fields to the given parameters.
	 * 
	 * @param theType
	 * @param theQuestion
	 * @param theAnswer
	 * @param theChoice1
	 * @param theChoice2
	 * @param theChoice3
	 */
	public Question(String theType, String theQuestion, String theAnswer, String theChoice1,
					String theChoice2, String theChoice3) {
		myType = theType;
		myQuestion = theQuestion;
		myAnswer = theAnswer;
		myChoice1 = theChoice1;
		myChoice2 = theChoice2;
		myChoice3 = theChoice3;

	}
	
	/**
	 * Returns myType.
	 * 
	 * @return string of myType.
	 */
	public String getMyType() {
		return myType;
	}
	
	/**
	 * Returns myQuestion.
	 * 
	 * @return string of myQuestion.
	 */
	public String getMyQuestion() {
		return myQuestion;
	}
	
	/**
	 * Returns myAnswer.
	 * 
	 * @return string of myAnswer.
	 */
	public String getMyAnswer() {
		return myAnswer;
	}
	
	/**
	 * Returns myChoice1
	 * 
	 * @return string of myChoice.
	 */
	public String getMyChoice1() {
		return myChoice1;
	}
	
	/**
	 * Returns myChoice2.
	 * 
	 * @return string of myChoice2.
	 */
	public String getMyChoice2() {
		return myChoice2;
	}
	
	/**
	 * Returns myChoice3.
	 * 
	 * @return string of myChoice3.
	 */
	public String getMyChoice3() {
		return myChoice3;
	}
	
	/**
	 * Overrides the toString method to print each field separated by commas.
	 * 
	 * @return string representing this class.
	 */
	public String toString() {
		return "Type = " + myType + ", Question = " + myQuestion + ", Answer = " + myAnswer +
				", Choice1 = " + myChoice1 + ", choice2 = " + myChoice2 + ", choice3 = " + myChoice3;
	}
}
