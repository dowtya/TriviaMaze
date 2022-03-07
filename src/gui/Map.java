package gui;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import controller.Game;

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
		Dimension buttonDimension = new Dimension(10,10);
		// create the 'rooms'
		for (int y = 0; y < mazeHeight; y++) {
			for (int x = 0; x < mazeWidth; x++) {
				roomButtons[x][y] = new JButton(".");
				roomButtons[x][y].setMinimumSize(buttonDimension);
				this.add(roomButtons[x][y]);
			}
		}
		
		this.setBounds(200,200, 1000, 1000);
		this.setBackground(Color.DARK_GRAY);
		this.setVisible(true);
		
	}
	
	// display the current map
	public void updateVisuals() {
		
	}
	
	// create clickable arrows to move the player; call callback with the direction selected.
	public void getPlayerMovement() {
		myGame.handleMovementResolution();
	}
	
	
	
}
