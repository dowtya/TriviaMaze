package model;

import java.io.*;

public class GameState implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7912621071699318862L;
	int mazeWidth;
	int mazeHeight;
	boolean paths[][]; // which paths are closed/unavailable;
	
	int x = 0; // current player x-coord
	int y = 0; // current player y-coord
	
	
	QuestionState questions; // some way to identify what question set is used
			// and some way to identify what questions have already been asked
	
	
	enum direction {Up, Down, Left, Right, None}; // which direction they are going
	
	

	
	
	public int getMazeWidth() {
		return mazeWidth;
	}
	
	public int getMazeHeight() {
		return mazeHeight;
	}
	
	public int getXCoord() {
		return x;
	}
	
	public int getYCoord() {
		return y;
	}
}
