package model;

public class GameState {
	
	int mazeWidth;
	int mazeHeight;
	boolean paths[][]; // which paths are closed/unavailable;
	
	int x = 0; // current player x-coord
	int y = 0; // current player y-coord
	
	
	QuestionState questions; // some way to identify what question set is used
			// and some way to identify what questions have already been asked
	
	
	enum direction {Up, Down, Left, Right, None}; // which direction they are going
	
	
	Serialize();
	Deserialize();
}
