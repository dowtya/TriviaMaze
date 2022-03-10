package gui;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import controller.Game;
import model.GameState;

public class Map extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3119341685041081769L;

	Game myGame;
	
	JButton[][] roomButtons;
	
	public Map(Game theGame) {
		myGame = theGame;
		
		int mazeWidth = myGame.getGameState().getMazeWidth();
		int mazeHeight = myGame.getGameState().getMazeHeight();
		
		roomButtons = new JButton[mazeWidth][mazeHeight];
		this.setLayout(new GridLayout(mazeWidth, mazeHeight, 0, 0));
		// create the 'rooms'
		for (int y = 0; y < mazeHeight; y++) {
			for (int x = 0; x < mazeWidth; x++) {

				roomButtons[x][y] = new JButton("0");
				this.add(roomButtons[x][y]);
			}
		}
		
		this.setBounds(200,200, 1000, 1000);
		this.setBackground(Color.DARK_GRAY);
		this.setVisible(true);
		
	}
	
	// display the current map
	public void updateVisuals() {
		JButton northButton = null;
		if(myGame.getGameState().myYCoord > 0) {
			// check if door is open
			if(myGame.getGameState().myPaths[myGame.getGameState().myXCoord][myGame.getGameState().myYCoord - 1]) {
				northButton = roomButtons[myGame.getGameState().myXCoord][myGame.getGameState().myYCoord - 1];
			}
			
		}
		
		JButton southButton = null;
		if(myGame.getGameState().myYCoord < myGame.getGameState().getMazeHeight()) {
			if(myGame.getGameState().myPaths[myGame.getGameState().myXCoord][myGame.getGameState().myYCoord]) {
				southButton = roomButtons[myGame.getGameState().myXCoord][myGame.getGameState().myYCoord + 1];
			}
		}
		
		JButton eastButton = null;
		if(myGame.getGameState().myYCoord > 0) {
			if(myGame.getGameState().myPaths[myGame.getGameState().myXCoord - 1][myGame.getGameState().myYCoord]) {
				eastButton = roomButtons[myGame.getGameState().myXCoord - 1][myGame.getGameState().myYCoord];
			}
		}
		
		JButton westButton = null;
		if(myGame.getGameState().myXCoord < myGame.getGameState().getMazeWidth()) {
			if(myGame.getGameState().myPaths[myGame.getGameState().myXCoord][myGame.getGameState().myYCoord]) {
				westButton = roomButtons[myGame.getGameState().myXCoord + 1][myGame.getGameState().myYCoord];
			}
		}
		
		
	}
	
	// create clickable arrows to move the player; call callback with the direction selected.
	public void getPlayerMovement() {
		myGame.handleMovementResolution();
		myGame.getGameState().setDirection(GameState.Direction.NONE);
	}
	
	
	
}
