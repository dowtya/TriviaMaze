package controller;

import java.util.function.Function;

import gui.Map;
import gui.OptionBar;
import gui.QuestionBox;
import model.GameState;

public class Game {
	
	GameState gamestate;
	
	Map map;
	QuestionBox questionbox;
	OptionBar optionbar;
	
	
	public void start(String filename, Function<Void,Void> exitRoutine) {
		
		// if file provided, load gamestate from file
		// otherwise, randomly generate gamestate
		
		// create map
		map = new Map(gamestate);
		
		// create questionbox
		questionbox = new Questionbox(gamestate);
		
		// create optionbar
		optionbar = new OptionBar();
		
		if (gamestate.direction == None) {
			handleMovmenetSelection();
		} else {
			handleQuestionSelection();
		}
		
	}
	
	void handleMovementSelection() {
		Map.getPlayerMovement(handleMovementResolution);
	}
	
	void handleMovementResolution(direction) {
		handleQuestionSelection()
	}
	
	void handleQuestionSelection() {
		questionState = questionbox.askNextQuestion(questionState, handleQuestionResolution);
	}
	
	void handleQuestionResolution() {
		if (QuestionBox.successfulAnswer) {
			gamestate.x = x + direction;
			gamestate.y = y + direction;
			
			if (gamestate.getXCoord() == gamestate.getMazeWidth() - 1 && gamestate.getYCoord() == gamestate.getMazeHeight() - 1) {
				displayVictory();
				exit();
			}
			
		} else {
			gamestate.paths[x, y] = true; // block path in gamestate
			
			if (checkForBoxedIn() == true) {
				displayFailure();
				exit();
			}
		}
		
		Map.updateVisuals(gamestate);
		handleMovementSelection();
	}
	
	
	
	public void displayVictory() {
		
		System.out.println("You win!");
	}
	
	public void displayFailure() {
		System.out.println("You have lost, no more moves were found. Better luck next time!");
	}
	
	//incomplete
	public void exit() {
		
	}
	
	//incomplete
	public boolean checkforBoxedIn() {
		boolean boxedIn;
		
		return boxedIn;
	}
		
}