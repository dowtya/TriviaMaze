package model;

import java.io.*;

public class GameState implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7912621071699318862L;
	private int myMazeWidth;
	private int myMazeHeight;
	private boolean myPaths[][]; // which paths are closed/unavailable;
	
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
	
	
	private boolean getHorizontalPathBetweenRooms(final int theY, final int theStartX, final int theEndX) {
		int tempX = theStartX;
		if(theEndX > tempX) {
			tempX = theEndX;
		}
		
		int pathX = tempX;
		int pathY = theY * 2;
		
		if (inBounds(pathX, pathY)) {
			return !myPaths[pathX][pathY];
		} else {
			return false;
		}
	}
	
	private boolean getVerticalPathBetweenRooms(final int theX, final int theStartY, final int theEndY) {
		int tempY = theStartY;
		if(theEndY > tempY) {
			tempY = theEndY;
		}
		
		int pathX = theX;	
		int pathY = tempY * 2 - 1;
		
		if (inBounds(pathX, pathY)) {
			return !myPaths[pathX][pathY];
		} else {
			return false;
		}
	}
	
	public boolean getPathOpenBetweenRooms(final int theStartX, final int theStartY, final int theEndX, final int theEndY) {
		if (theEndX == theStartX) {
			boolean value = getVerticalPathBetweenRooms(theStartX, theStartY, theEndY);
			if (getVerticalPathBetweenRooms(theStartX, theStartY, theEndY) != getVerticalPathBetweenRooms(theStartX, theEndY, theStartY)) {
				System.out.println("Error: pathcheck not symmetrical");
			}
			return value;
		} else {
			
			boolean value = getHorizontalPathBetweenRooms(theStartY, theStartX, theEndX);
			if (getHorizontalPathBetweenRooms(theStartY, theStartX, theEndX) != getHorizontalPathBetweenRooms(theStartY, theEndX, theStartX)) {
				System.out.println("Error: pathcheck not symmetrical");
			}
			return value;
		}
	}
	
	private void setHorizontalPathBetweenRooms(final int theY, final int theStartX, final int theEndX, boolean value) {
		int tempX = theStartX;
		if(theEndX > tempX) {
			tempX = theEndX;
		}
		
		int pathX = tempX;
		int pathY = theY * 2;
		
		if (inBounds(pathX, pathY)) {
			myPaths[pathX][pathY] = !value;
		}
	}
	
	private void setVerticalPathBetweenRooms(final int theX, final int theStartY, final int theEndY, boolean value) {
		int tempY = theStartY;
		if(theEndY > tempY) {
			tempY = theEndY;
		}
		
		int pathX = theX;	
		int pathY = tempY * 2 - 1;
		
		if (inBounds(pathX, pathY)) {
			myPaths[pathX][pathY] = !value;
		}
	}
	
	
	public void setPathOpenBetweenRooms(final int theStartX, final int theStartY, final int theEndX, final int theEndY, boolean thePath) {
		if (theEndX == theStartX) {
			setVerticalPathBetweenRooms(theStartX, theStartY, theEndY, thePath);
		} else {
			setHorizontalPathBetweenRooms(theStartY, theStartX, theEndX, thePath);
		}
	}
	
	public boolean getPathOpenFromPlayer(final int theDeltaX, final int theDeltaY) {
		return getPathOpenBetweenRooms(myXCoord, myYCoord, myXCoord + theDeltaX, myYCoord + theDeltaY);
	}
	
	
	public boolean isPathAvailable() {
		boolean exists = false;
		boolean[][] visited = new boolean[myPaths.length][myPaths[0].length];
		for (int i = 0; i < myPaths.length; i++) {
			for (int j = 0; j < myPaths[0].length; j++) {
				if (i == myXCoord && j == myYCoord && !visited[i][j]) {
					if(isPathHelper(i, j, visited)) {
						exists = true;
						break;
					}
				}
			}
		}
		return exists;
	}
	
	private boolean isPathHelper(int i, int j, boolean[][] theVisited) {
		if (inBounds(i, j) && myPaths[i][j] != true && !theVisited[i][j]) {
			
			theVisited[i][j] = true;
			
			if (i == myPaths.length - 1 && j == myPaths[0].length - 1) {
				return true;
			}
			//go north
			boolean north = isPathHelper(i - 1, j, theVisited);
			if (north) {
				return true;
			}
			
			//go west
			boolean west = isPathHelper(i, j - 1, theVisited);
			if (west) {
				return true;
			}
			
			//go south
			boolean south = isPathHelper(i + 1, j, theVisited);
			if (south) {
				return true;
			}
			
			//go east
			boolean east = isPathHelper(i, j + 1, theVisited);
			if (east) {
				return true;
			}
		}
		return false;
	}
	
	private boolean inBounds(int theRow, int theCol) {
		if (theRow >= 0 && theRow < myPaths.length 
				&& theCol >= 0 && theCol < myPaths[0].length) {
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
<<<<<<< HEAD
}


=======
}
>>>>>>> master
