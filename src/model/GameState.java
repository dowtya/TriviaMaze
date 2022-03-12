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
		myPaths = new boolean[myMazeWidth][(myMazeHeight)*2 - 1];
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
	
	public boolean getPathOpenBetweenRooms(final int theStartX, final int theStartY, final int theEndX, final int theEndY) {
		return !myPaths[theEndX][theStartY * 2 + (theEndY-theStartY)];
	}
	
	public boolean getPathOpenFromPlayer(final int theDeltaX, final int theDeltaY) {
		return getPathOpenBetweenRooms(myXCoord, myYCoord, myXCoord + theDeltaX, myYCoord + theDeltaY);
	}
	
	
	public static boolean isPathAvailable(boolean thePath[][], int theXCoord, int theYCoord) {
		boolean exists = false;
		boolean[][] visited = new boolean[thePath.length][thePath[0].length];
		for (int i = 0; i < thePath.length; i++) {
			for (int j = 0; j < thePath[0].length; j++) {
				if (i == theXCoord && j == theYCoord && !visited[i][j]) {
					if(isPathHelper(thePath, i, j, visited)) {
						exists = true;
						break;
					}
				}
			}
		}
		return exists;
	}
	
	public static boolean isPathHelper(boolean[][] thePaths, int i, int j, boolean[][] theVisited) {
		if (inBounds(i, j, thePaths) && !theVisited[i][j]) {
			theVisited[i][j] = true;
			
			if (i == thePaths.length - 1 && j == thePaths[0].length - 1) {
				return true;
			}
			//go north
			boolean north = isPathHelper(thePaths, i - 1, j, theVisited);
			if (north) {
				return true;
			}
			
			//go west
			boolean west = isPathHelper(thePaths, i, j - 1, theVisited);
			if (west) {
				return true;
			}
			
			//go south
			boolean south = isPathHelper(thePaths, i + 1, j, theVisited);
			if (south) {
				return true;
			}
			
			//go east
			boolean east = isPathHelper(thePaths, i, j + 1, theVisited);
			if (east) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean inBounds(int theRow, int theCol, boolean[][] thePaths) {
		if (theRow >= 0 && theRow < thePaths.length 
				&& theCol >= 0 && theCol < thePaths[0].length) {
			return true;
		}
		return false;
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
		/*
		if (!isPathAvailable(myPaths)) {
			defeat = true;
		}
		*/
		return defeat;
	}
	
	public static void main(String[] theargs) {
		boolean[][] temp = { {true, true, true},
						 	 {false, false, true},
						 	 {false, false, false} };
		System.out.println(GameState.isPathAvailable(temp, 1, 1));
		
	}
	
}
