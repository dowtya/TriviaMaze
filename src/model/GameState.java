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
	
	public int myXCoord = 0; // current player x-coord
	public int myYCoord = 0; // current player y-coord
	
	
	QuestionState questions; // some way to identify what question set is used
			// and some way to identify what questions have already been asked
	
	
	enum direction {Up, Down, Left, Right, None}; // which direction they are going
	
	

	
	
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
}
