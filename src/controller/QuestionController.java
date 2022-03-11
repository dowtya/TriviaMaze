package controller;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Function;
import model.Question;
import model.QuestionState;
import gui.QuestionBox;

public class QuestionController {
	
	QuestionBox myQuestionBox;
	private ArrayList<Question> myQuestionList;
	
	QuestionController(ArrayList<Question> theQuestionList) {
		myQuestionBox = new QuestionBox();
		myQuestionList = theQuestionList;
	}
	
	QuestionState askNextQuestion(QuestionState questionState, Function<Game, Void> function, Game game) {
		Random r = new Random();
		int questionSelection = r.nextInt(myQuestionList.size());
		String question = myQuestionList.get(questionSelection).getMyQuestion();
		String correctAnswer = myQuestionList.get(questionSelection).getMyAnswer();
		
		String[] answers = {myQuestionList.get(questionSelection).getMyChoice1(),
							myQuestionList.get(questionSelection).getMyChoice2(),
							myQuestionList.get(questionSelection).getMyChoice3()};
		
		
		//TODO: determine question and answers
		
		//
		myQuestionBox.displaySingleChoiceQuestion(question, answers, (index) -> {
			// evaluate if the answer at that index is correct
			
			if (answers[index].equals(correctAnswer)) {
				
				questionState.setAnsweredCorrectly(true);
				
			} else {
				
				questionState.setAnsweredCorrectly(false);
			}
			
			return questionState.isAnsweredCorrectly();
		});
		
		
		// display current question
		// display possible answers
		
		// wait for user to select an answer
		// validate whether question was answered correctly
		// update questionstate
		
		return questionState;
	}
}
