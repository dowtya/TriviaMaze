package controller;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Function;
import model.Question;
import model.QuestionState;
import gui.QuestionBox;

public class QuestionController {
	
	Game myGame;
	QuestionBox myQuestionBox;
	private ArrayList<Question> myQuestionList;
	//private  myAnswers;
	
	QuestionController(ArrayList<Question> theQuestionList, Game theGame) {
		myGame = theGame;
		myQuestionBox = new QuestionBox(myGame);
		myQuestionList = theQuestionList;
	}
	
	QuestionState askNextQuestion(QuestionState questionState, Function<Game, Void> function, Game game) {
		Random r = new Random();
		int questionSelection = r.nextInt(myQuestionList.size());
		String question = myQuestionList.get(questionSelection).getMyQuestion();
		String questionType = myQuestionList.get(questionSelection).getMyType();
		String correctAnswer = myQuestionList.get(questionSelection).getMyAnswer();
		ArrayList<String> myAnswers = new ArrayList<String>();
		
		if (questionType.equalsIgnoreCase("Multiple Choice")) {
			
			myAnswers.add(myQuestionList.get(questionSelection).getMyChoice1());
			myAnswers.add(myQuestionList.get(questionSelection).getMyChoice2());
			myAnswers.add(myQuestionList.get(questionSelection).getMyChoice3());
			
		} else if (questionType.equalsIgnoreCase("True/False")) {
			
			myAnswers.add(myQuestionList.get(questionSelection).getMyChoice1());
			myAnswers.add(myQuestionList.get(questionSelection).getMyChoice2());
			
		}
		
//		String[] answers = {myQuestionList.get(questionSelection).getMyChoice1(),
//							myQuestionList.get(questionSelection).getMyChoice2(),
//							myQuestionList.get(questionSelection).getMyChoice3()};
		
		
		//TODO: determine question and answers
		
		//
		if (questionType.equalsIgnoreCase("Short Answer")) {
			myQuestionBox.displayShortAnswerQuestion(question, (answer) -> {
				System.out.println(correctAnswer);
				// evaluate if the answer at that index is correct
				
				if (answer.equalsIgnoreCase(correctAnswer)) {
					
					questionState.setAnsweredCorrectly(true);
					
				} else {
					
					questionState.setAnsweredCorrectly(false);
				}
				return questionState.isAnsweredCorrectly();
			});
		} else {
			myQuestionBox.displaySingleChoiceQuestion(question, myAnswers, (index) -> {
				// evaluate if the answer at that index is correct
				
				if (myAnswers.get(index).equals(correctAnswer)) {
					
					questionState.setAnsweredCorrectly(true);
					
				} else {
					
					questionState.setAnsweredCorrectly(false);
				}
				return questionState.isAnsweredCorrectly();
			});
		}
		
		
		// display current question
		// display possible answers
		
		// wait for user to select an answer
		// validate whether question was answered correctly
		// update questionstate
		myQuestionList.remove(questionSelection);
		return questionState;
	}
}
