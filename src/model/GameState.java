package model;

import java.io.*;

public class GameState implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7912621071699318862L;
	private int myMazeWidth;
	private int myMazeHeight;
	public boolean myPaths[][]; // which paths are closed/unavailable;
	
	public int myXCoord; // current player x-coord
	public int myYCoord; // current player y-coord
	
	
	private QuestionState myQuestions; // some way to identify what question set is used
			// and some way to identify what questions have already been asked
	
	
	public enum Direction {NORTH, SOUTH, WEST, EAST, NONE}; // which direction they are going
	private Direction myDirection;
	
	public GameState(int theMazeWidth, int theMazeHeight) {
		myMazeWidth = theMazeWidth;
		myMazeHeight = theMazeHeight;
		myXCoord = 0;
		myYCoord = 0;
		myQuestions = new QuestionState();
		myDirection = Direction.NONE;
		myPaths = new boolean[myMazeWidth][myMazeHeight];
	}
	
	
	public QuestionState getQuestionState() {
		return myQuestions;
	}
	
	public void setQuestionState(QuestionState theQuestions) {
		myQuestions = theQuestions;
	}
	
	public Direction getDirection() {
		return myDirection;
	}
	
	public void setDirection(Direction theDirection) {
		myDirection = theDirection;
	}
	
	
	public int getMazeWidth() {
		return myMazeWidth;
	}
	
	public int getMazeHeight() {
		return myMazeHeight;
	}
	
	public int getXCoord() {
		return myXCoord;
	}
	
	public int getYCoord() {
		return myYCoord;
	}
	
	public void setXCoord(int theXCoord) {
		myXCoord = theXCoord;
	}
	
	public void setYCoord(int theYCoord) {
		myYCoord = theYCoord;
	}
	
	
	public boolean isPathAvailable(boolean thePath[][]) {
		boolean avail = false;
		
		return avail;
		
	}
	
	public boolean checkVictory() {
		boolean victory = false;
		
		if (myXCoord == myMazeWidth && myYCoord == myMazeHeight) {
			victory = true;
		}
		
		return victory;
	}
	
	public boolean checkDefeat() {
		boolean defeat = false;
		
		if (!isPathAvailable(myPaths)) {
			defeat = true;
		}
		
		return defeat;
	}
}
