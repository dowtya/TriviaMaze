package controller;

import java.util.function.Function;

import model.QuestionState;
import gui.QuestionBox;

public class QuestionController {
	
	QuestionBox myQuestionBox;
	
	QuestionController() {
		myQuestionBox = new QuestionBox();
	}
	
	QuestionState askNextQuestion(QuestionState questionState, Function<Game, Void> function, Game game) {
		
		String question = "TEST";
		String[] answers = {"Test1", "test2", "thisIsCorrect", "ThisIsNOTcorrect"};
		
		//TODO: determine question and answers
		
		//
		myQuestionBox.displaySingleChoiceQuestion(question, answers, (index) -> {
			// evaluate if the answer at that index is correct
			return true;
		});
		
		
		// display current question
		// display possible answers
		
		// wait for user to select an answer
		// validate whether question was answered correctly
		// update questionstate
		
		return questionState;
	}
}
