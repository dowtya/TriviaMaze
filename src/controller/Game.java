package controller;

import gui.Map;
import gui.OptionBar;
import gui.Questionbox;
import model.GameState;

public class Game {
	
	GameState gamestate;
	
	Map map;
	Questionbox questionbox;
	OptionBar optionbar;
	
	
	start(filename) {
		
		// if file provided, load gamestate from file
		// otherwise, randomly generate gamestate
		
		// create map
		
		Map map = new Map(gamestate);
		
		// create questionbox
		Questionbox = new Questionbox(gamestate);
		
		// create optionbar
		optionbar = new OptionBar();
		
		if (gamestate.direction == None) {
			handleMovmenetSelection();
		} else {
			handleQuestionSelection();
		}
		
	}
	
	handleMovementSelection() {
		Map.getPlayerMovement(handleMovementResolution);
	}
	
	handleMovementResolution(direction) {
		handleQuestionSelection()
	}
	
	handleQuestionSelection() {
		questionState = questionbox.askNextQuestion(questionState, handleQuestionResolution);
	}
	
	handleQuestionResolution() {
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
	
	public void exit() {
		
	}
	
	public boolean checkforBoxedIn() {
		boolean boxedIn;
		
		return boxedIn;
	}
		
}