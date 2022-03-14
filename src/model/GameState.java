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
		System.out.println(theStartX);
		System.out.println(theStartY);
		System.out.println(theEndX);
		System.out.println(theEndY);
		//something wrong here. Needs to check for bounds.
		return !myPaths[theEndX][theStartY * 2 + (theEndY-theStartY)];
	}
	
	public boolean getPathOpenFromPlayer(final int theDeltaX, final int theDeltaY) {
		if(myXCoord == 0 && theDeltaX < 0) {
			System.out.println("x to small");
		}
		if(myYCoord == 0 && theDeltaY < 0) {
			System.out.println("y to small");
		}
		return getPathOpenBetweenRooms(myXCoord, myYCoord, myXCoord + theDeltaX, myYCoord + theDeltaY);
	}
	
	
	public boolean isPathAvailable() {
		boolean exists = false;
		boolean[][] visited = new boolean[myPaths.length][myPaths[0].length];
		for (int i = 0; i < myPaths.length; i++) {
			for (int j = 0; j < myPaths[0].length; j++) {
				if (i == myXCoord && j == myYCoord && !visited[i][j]) {
					if(isPathHelper(myPaths, i, j, visited)) {
						exists = true;
						break;
					}
				}
			}
		}
		return exists;
	}
	
	public static boolean isPathHelper(boolean[][] thePaths, int i, int j, boolean[][] theVisited) {
		if (inBounds(i, j, thePaths) && thePaths[i][j] != true && !theVisited[i][j]) {
			
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
		
		if (myXCoord == myMazeWidth - 1 && myYCoord == myMazeHeight - 1) {
			victory = true;
		}
		return victory;
	}
	
	public boolean checkDefeat() {
		boolean defeat = false;
		
		if (!isPathAvailable()) {
			defeat = true;
		}
		return defeat;
	
	}
}


