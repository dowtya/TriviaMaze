package controller;

import java.util.function.Function;

import gui.Map;
import gui.OptionBar;
import gui.QuestionBox;
import model.GameState;

public class Game {
	
	GameState gamestate;
	
	Map map;
	private QuestionController myQuestioncontroller;
	OptionBar optionbar;
	
	public void start(String filename, Function<Void,Void> exitRoutine) {
	/**
	 * Problems atm, Need to work on this on sunday, I think it would just be a way to open up
	 * and search for a file to read, but I don't have much experience with loading files at all
	 * so I need to do further research to make this possible.
	 */
		
		// if file provided, load gamestate from file
		// otherwise, randomly generate gamestate
		
		gamestate = new GameState(5,5);
		
		// create map
		map = new Map(this);
		
		// create questionbox
		myQuestioncontroller = new QuestionController();
		
		// create optionbar
		optionbar = new OptionBar();
		
		if (gamestate.getDirection() == GameState.Direction.NONE) {
			handleMovementSelection();
		} else {
			handleQuestionSelection();
		}
		
	}
	
	//Method should handle movement selection, so it needs to show the options for movement, and call the
	//correct method when a selection is chosen.
	public void handleMovementSelection() {
		map.getPlayerMovement();
	}
	

	//This method will handle what happens based on correctness of the question answer they provide.
	//Stores the direction that was selected.  Psuedo code atm!
	//Variable issue, it needs to take in a direction parameter but was having issues for some dumb reason.
	public void handleMovementResolution() {
		handleQuestionSelection();
		
		//take direction in and store in a variable
		
		// Direction currentDirection = theDirection;
		
		
	}
	
	//This method will handle the question selection from the SQL database and work on displaying it.
	//Check Variables
	public void handleQuestionSelection() {
		gamestate.setQuestionState(myQuestioncontroller.askNextQuestion(gamestate.getQuestionState(), 
								  (Function<Game,Void>)(Game game)->{game.handleQuestionResolution(); return null;}, this));
		
	}
	
	public QuestionBox getQuestionBox() {
		return myQuestioncontroller.myQuestionBox;
	}
	public GameState getGameState() {
		return gamestate;
	}
	public Map getMap() {
		return map;
	}
	
	public void handleQuestionResolution() {
		if (gamestate.getQuestionState().isAnsweredCorrectly()) {
			
			if (gamestate.getDirection() == GameState.Direction.EAST) {
				gamestate.setXCoord(gamestate.myXCoord + 1);
			}
			if (gamestate.getDirection() == GameState.Direction.SOUTH) {
				//add +1 to row part of 2d array
				gamestate.setYCoord(gamestate.getYCoord() + 1);
			}
			if (gamestate.getDirection() == GameState.Direction.WEST) {
				gamestate.setXCoord(gamestate.myXCoord - 1);
			}
			
			if (gamestate.getDirection() == GameState.Direction.NORTH) {
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
		
		map.updateVisuals();
		map.getPlayerMovement();//handleMovementSelection();
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
		boolean boxedIn = false;
		
		return boxedIn;
	}
		
}