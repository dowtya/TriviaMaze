package controller;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Function;
import model.Question;
import model.QuestionState;
import gui.QuestionBox;
/**
 * Class QuestionController which controls the Question being asked and if it is correct or not.
 * @author Alec Dowty
 * @author Aaron Gitell
 * @author Joel Hemphill
 *
 */
public class QuestionController {
	
	/*
	 * Game variable.
	 */
	Game myGame;
	
	/*
	 * QuestionBox variable.
	 */
	QuestionBox myQuestionBox;
	
	/*
	 * ArrayList of questions.
	 */
	private ArrayList<Question> myQuestionList;
	
	/**
	 * Constructor for QuestionController, takes in a list of questions and the game and sets them.
	 * Also creates the Question box based on the game.
	 * @param theQuestionList List of Questions.
	 * @param theGame Game variable.
	 */
	QuestionController(ArrayList<Question> theQuestionList, Game theGame) {
		myGame = theGame;
		myQuestionBox = new QuestionBox(myGame);
		myQuestionList = theQuestionList;
	}
	
	/**
	 * Method askNextQuestion which deals with the Question being asked next, what type it is, what the
	 * answer choices are, and what the correct answer is.
	 * @param questionState Current state of the question.
	 * @param function Game function.
	 * @param game Gamestate currently
	 * @return If the answer is answered correectly.
	 */
	QuestionState askNextQuestion(QuestionState questionState, Function<Game, Void> function, Game game) {
		// Random integer for selecting a question.
		Random r = new Random();
		int questionSelection = r.nextInt(myQuestionList.size());
		// Type of question, the question and the correct answer based on the random integer selection.
		String question = myQuestionList.get(questionSelection).getMyQuestion();
		String questionType = myQuestionList.get(questionSelection).getMyType();
		String correctAnswer = myQuestionList.get(questionSelection).getMyAnswer();
		ArrayList<String> myAnswers = new ArrayList<String>();
		
		// What to do if multiple choice
		if (questionType.equalsIgnoreCase("Multiple Choice")) {
			
			myAnswers.add(myQuestionList.get(questionSelection).getMyChoice1());
			myAnswers.add(myQuestionList.get(questionSelection).getMyChoice2());
			myAnswers.add(myQuestionList.get(questionSelection).getMyChoice3());
			
		// What to do in True or False.	
		} else if (questionType.equalsIgnoreCase("True/False")) {
			
			myAnswers.add(myQuestionList.get(questionSelection).getMyChoice1());
			myAnswers.add(myQuestionList.get(questionSelection).getMyChoice2());
			
		}
		
		// What to do if Short Answer.
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
			
			// Evaluating if the Question is correct or not.
			myQuestionBox.displaySingleChoiceQuestion(question, myAnswers, (index) -> {
				
				if (myAnswers.get(index).equals(correctAnswer)) {
					
					questionState.setAnsweredCorrectly(true);
					
				} else {
					
					questionState.setAnsweredCorrectly(false);
				}
				return questionState.isAnsweredCorrectly();
			});
		}

		myQuestionList.remove(questionSelection);
		return questionState;
	}
}
