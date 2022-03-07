package controller;

public class Question {
	
	private String myType;
	
	private String myQuestion;
	
	private String myAnswer;
	
	private String myChoice1;
	
	private String myChoice2;
	
	private String myChoice3;
	
	
	public Question(String theType, String theQuestion, String theAnswer, String theChoice1,
					String theChoice2, String theChoice3) {
		myType = theType;
		myQuestion = theQuestion;
		myAnswer = theAnswer;
		myChoice1 = theChoice1;
		myChoice2 = theChoice2;
		myChoice3 = theChoice3;

	}
	
	public String getMyType() {
		return myType;
	}
	
	public String getMyQuestion() {
		return myQuestion;
	}
	
	public String getMyAnswer() {
		return myAnswer;
	}
	
	public String getMyChoice1() {
		return myChoice1;
	}
	
	public String getMyChoice2() {
		return myChoice2;
	}
	
	public String getMyChoice3() {
		return myChoice3;
	}
	
	public String toString() {
		return "Type = " + myType + ", Question = " + myQuestion + ", Answer = " + myAnswer +
				", Choice1 = " + myChoice1 + ", choice2 = " + myChoice2 + ", choice3 = " + myChoice3;
	}
}
