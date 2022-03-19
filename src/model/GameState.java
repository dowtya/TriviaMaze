package model;

import java.io.*;
/**
 * GameState Class which controls the state of the game throughout the playthrough a player has.
 * @author Alec Dowty
 * @author Aaron Gitell
 * @author Joel Hemphill
 *
 */
public class GameState implements Serializable {
	
	/*
	 * Serial ID.
	 */
	private static final long serialVersionUID = 7912621071699318862L;
	
	/*
	 * The Maze Width
	 */
	private int myMazeWidth;
	
	/*
	 * The Maze Height.
	 */
	private int myMazeHeight;
	
	/*
	 * The Paths which are available or not.
	 */
	private boolean myPaths[][];
	
	/*
	 * The Player's current X Coordinate.
	 */
	public int myXCoord;
	
	/*
	 * The Player's current Y Coordinate.
	 */
	public int myYCoord; // current player y-coord
	
	/*
	 * The Questions being used, referencing the QuestionState Class.
	 */
	private QuestionState myQuestions; // some way to identify what question set is used
			// and some way to identify what questions have already been asked
	
	/*
	 * The enum which states the direction a player is going, if going one at all.
	 */
	public enum Direction {NORTH, SOUTH, WEST, EAST, NONE};

	/*
	 * The current direction, referencing the direction enum above.
	 */
	private Direction myDirection;
	
	/**
	 * Constructor for GameState, builds a new game based on the height and width.
	 * Makes sure to make a game board bigger, but only shows a specific XxX.
	 * @param theMazeWidth Width of the maze.
	 * @param theMazeHeight Height of the maze.
	 */
	public GameState(int theMazeWidth, int theMazeHeight) {
		myMazeWidth = theMazeWidth;
		myMazeHeight = theMazeHeight;
		myXCoord = 0;
		myYCoord = 0;
		myQuestions = new QuestionState();
		myDirection = Direction.NONE;
		myPaths = new boolean[myMazeWidth][(myMazeHeight)*2 - 1];
	}
	
	/**
	 * Getter for the Question State.
	 * @return Questions.
	 */
	public QuestionState getQuestionState() {
		return myQuestions;
	}
	
	/**
	 * Setter for the Question State.
	 * @param theQuestions Question state to set to.
	 */
	public void setQuestionState(QuestionState theQuestions) {
		myQuestions = theQuestions;
	}
	
	/**
	 * Getter for the direction.
	 * @return The Direction.
	 */
	public Direction getDirection() {
		return myDirection;
	}
	
	/**
	 * Setter for the direction.
	 * @param theDirection Direction to set it to.
	 */
	public void setDirection(Direction theDirection) {
		myDirection = theDirection;
	}
	
	/**
	 * Getter for maze width.
	 * @return Maze width.
	 */
	public int getMazeWidth() {
		return myMazeWidth;
	}
	
	/**
	 * Getter for maze height.
	 * @return Maze Height.
	 */
	public int getMazeHeight() {
		return myMazeHeight;
	}
	
	/**
	 * Getter for the X Coordinate.
	 * @return the X Coordinate.
	 */
	public int getXCoord() {
		return myXCoord;
	}
	
	/**
	 * Getter for the Y Coordinate.
	 * @return the Y Coordinate.
	 */
	public int getYCoord() {
		return myYCoord;
	}
	
	/**
	 * Setter for the X Coordinate.
	 * @param the X Coordinate to set to.
	 */
	public void setXCoord(int theXCoord) {
		myXCoord = theXCoord;
	}
	
	/**
	 * Setter for the Y Coordinate.
	 * @param the Y Coordinate to set to.
	 */
	public void setYCoord(int theYCoord) {
		myYCoord = theYCoord;
	}
	
	/**
	 * Returns myPaths.
	 * 
	 * @return boolean[][] of paths.
	 */
	public boolean[][] getMyPaths() {
		return myPaths;
	}
	
	/**
	 * Sets given boolean array to myPaths.
	 * 
	 * @param thePaths array of paths.
	 */
	public void setMyPaths(boolean[][] thePaths) {
		myPaths = thePaths;
	}
	
	/**
	 * Method which gets any possible horizontal paths between two adjacent rooms.
	 * @param theY Current Y.
	 * @param theStartX the Current X.
	 * @param theEndX the Ending X.
	 * @return Boolean if there is a path between the rooms.
	 */
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
	
	/**
	 * Method which gets any possible vertical paths between two adjacent rooms.
	 * @param theX Current X.
	 * @param theStartY the Current Y.
	 * @param theEndY the Ending Y.
	 * @return Boolean if there is a path between the rooms.
	 */
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
	
	/**
	 * A Method which checks if there are any paths open between two adjacent rooms.
	 * @param theStartX Starting X point.
	 * @param theStartY starting Y point.
	 * @param theEndX Ending X point.
	 * @param theEndY Ending Y point.
	 * @return Boolean if there is any path open.
	 */
	public boolean getPathOpenBetweenRooms(final int theStartX, final int theStartY, final int theEndX, final int theEndY) {
		if (theEndX == theStartX) {
			return getVerticalPathBetweenRooms(theStartX, theStartY, theEndY);
		} else {	
			return getHorizontalPathBetweenRooms(theStartY, theStartX, theEndX);
		}
	}
	
	/**
	 * Setter method which sets if there is a horizontal path open between rooms, called when a question is answered.
	 * @param theY The current Y value.
	 * @param theStartX The starting X value.
	 * @param theEndX The Ending X value.
	 * @param value Boolean it takes in to set.
	 */
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
	
	/**
	 * Setter method which sets if there is a vertical path open between rooms, called when a question is answered.
	 * @param theX The current X value.
	 * @param theStartY The starting Y value.
	 * @param theEndY The Ending Y value.
	 * @param value Boolean it takes in to set.
	 */
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
	
	/**
	 * Method which sets a path open between rooms.
	 * @param theStartX Starting X value.
	 * @param theStartY Starting Y value.
	 * @param theEndX Ending X value.
	 * @param theEndY Ending Y value.
	 * @param thePath Boolean being used to set if true or false.
	 */
	public void setPathOpenBetweenRooms(final int theStartX, final int theStartY, final int theEndX, final int theEndY, boolean thePath) {
		if (theEndX == theStartX) {
			setVerticalPathBetweenRooms(theStartX, theStartY, theEndY, thePath);
		} else {
			setHorizontalPathBetweenRooms(theStartY, theStartX, theEndX, thePath);
		}
	}
	
	/**
	 * Method which gets a path that is open from a player.
	 * @param theDeltaX The change in X.
	 * @param theDeltaY The change in Y.
	 * @return Returns if there is a path open from where the player is.
	 */
	public boolean getPathOpenFromPlayer(final int theDeltaX, final int theDeltaY) {
		return getPathOpenBetweenRooms(myXCoord, myYCoord, myXCoord + theDeltaX, myYCoord + theDeltaY);
	}
	
	/**
	 * Method which checks if there is a victorious path from where the player currently is.
	 * @return Boolean of true or false if the player can win.
	 */
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
	
	/**
	 * Helper method for checking if there is a path to victory.
	 * @param i the row.
	 * @param j the column.
	 * @param theVisited rooms.
	 * @return
	 */
	private boolean isPathHelper(int theRow, int theCol, boolean[][] theVisited) {
		if (inBounds(theRow, theCol) && myPaths[theRow][theCol] != true && !theVisited[theRow][theCol]) {
			
			theVisited[theRow][theCol] = true;
			
			if (theRow == myPaths.length - 1 && theCol == myPaths[0].length - 1) {
				return true;
			}
			//go north
			boolean north = isPathHelper(theRow - 1, theCol, theVisited);
			if (north) {
				return true;
			}
			
			//go west
			boolean west = isPathHelper(theRow, theCol - 1, theVisited);
			if (west) {
				return true;
			}
			
			//go south
			boolean south = isPathHelper(theRow + 1, theCol, theVisited);
			if (south) {
				return true;
			}
			
			//go east
			boolean east = isPathHelper(theRow, theCol + 1, theVisited);
			if (east) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method which checks if something is in bounds.
	 * @param theRow The current Row.
	 * @param theCol The current Column.
	 * @return Boolean true or false if it is in bounds or not.
	 */
	private boolean inBounds(int theRow, int theCol) {
		if (theRow >= 0 && theRow < myPaths.length 
				&& theCol >= 0 && theCol < myPaths[0].length) {
			return true;
		}
		return false;
	}
	
	/**
	 * Method which checks for victory by checking if the current location of the player
	 * is the same as the maze width and height (the winning tile).
	 * @return Boolean true or false for if they have won.
	 */
	public boolean checkVictory() {
		boolean victory = false;
		
		if (myXCoord == myMazeWidth - 1 && myYCoord == myMazeHeight - 1) {
			victory = true;
		}
		return victory;
	}
	
	/**
	 * Method for checking the defeat, checking if there is a path available to the winning tile or not.
	 * @return Boolean true or false for it they can win.
	 */
	public boolean checkDefeat() {
		boolean defeat = false;
		
		if (!isPathAvailable()) {
			defeat = true;
		}
		return defeat;
	
	}

}

