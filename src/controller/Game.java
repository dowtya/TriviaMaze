package controller;

import gui.Map;
import gui.OptionBar;
import gui.QuestionBox;
import model.GameState;

public class Game {
	
	GameState gamestate;
	
	Map map;
	QuestionBox questionbox;
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
	//Method should handle movement selection, so it needs to show the options for movement, and call the
	//correct method when a selection is chosen.
	public void handleMovementSelection() {
		Map.getPlayerMovement(handleMovementResolution);
		
		
	}
	
	//This method will handle what happens based on correctness of the question answer they provide.
	public void handleMovementResolution(direction) {
		handleQuestionResolution();
	}
	
	//This method will handle the question selection from the SQL database and work on displaying it.
	public void handleQuestionSelection() {
		questionState = questionbox.askNextQuestion(questionState, handleQuestionResolution);
	}
	
	public void handleQuestionResolution() {
		if (QuestionBox.successfulAnswer) {
			
			if (direction == EAST) {
				gamestate.setXCoord(gamestate.myXCoord + 1);
			}
			if (direction == SOUTH) {
				//add +1 to row part of 2d array
				gamestate.setYCoord(gamestate.getYCoord() + 1);
			}
			if (direction == WEST) {
				gamestate.setXCoord(gamestate.myXCoord - 1);
			}
			
			if (direction == NORTH) {
				//add +1 to row part of 2d array
				gamestate.setYCoord(gamestate.getYCoord() - 1);
			}
			
			if (gamestate.getXCoord() == gamestate.getMazeWidth() - 1 && gamestate.getYCoord() == gamestate.getMazeHeight() - 1) {
				displayVictory();
				displayGameOver();
			}
			
		} else {
			gamestate.myPaths[gamestate.myXCoord][gamestate.myYCoord] = true; // block path in gamestate
			
			if (checkForBoxedIn() == true) {
				displayFailure();
				displayGameOver();
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
	
	
	public void displayGameOver() {	
		System.out.println("GAME OVER! \nThank you for playing our game, the game will now exit.");
		System.exit(0);		
	}
	
	//incomplete
	public boolean checkForBoxedIn() {
		boolean boxedIn;
		
		return boxedIn;
	}
		
}