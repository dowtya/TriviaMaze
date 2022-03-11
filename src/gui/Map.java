package gui;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

				roomButtons[x][y] = new JButton("");
				roomButtons[x][y].setEnabled(false);
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
		
		JButton northButton = null;
		if(myGame.getGameState().myYCoord > 0) {
			// check if door is open
			if(myGame.getGameState().getPathOpenFromPlayer(0, -1)) {
				northButton = roomButtons[myGame.getGameState().myXCoord][myGame.getGameState().myYCoord - 1];
				northButton.setEnabled(true);
				
				northButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						myGame.getGameState().setDirection(GameState.Direction.NORTH);
						myGame.handleMovementResolution();
					}
				});
			}
			
		}
		
		JButton southButton = null;
		if(myGame.getGameState().myYCoord < myGame.getGameState().getMazeHeight()) {
			if(myGame.getGameState().getPathOpenFromPlayer(0, 1)) {
				southButton = roomButtons[myGame.getGameState().myXCoord][myGame.getGameState().myYCoord + 1];
				southButton.setEnabled(true);
				southButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						myGame.getGameState().setDirection(GameState.Direction.SOUTH);
						myGame.handleMovementResolution();
					}
				});
			}
		}
		
		JButton eastButton = null;
		if(myGame.getGameState().myYCoord > 0) {
			if(myGame.getGameState().getPathOpenFromPlayer(-1, 0)) {
				eastButton = roomButtons[myGame.getGameState().myXCoord - 1][myGame.getGameState().myYCoord];
				eastButton.setEnabled(true);
				eastButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						myGame.getGameState().setDirection(GameState.Direction.EAST);
						myGame.handleMovementResolution();
					}
				});
			}
		}
		
		JButton westButton = null;
		if(myGame.getGameState().myXCoord < myGame.getGameState().getMazeWidth()) {
			if(myGame.getGameState().getPathOpenFromPlayer(1, 0)) {
				westButton = roomButtons[myGame.getGameState().myXCoord + 1][myGame.getGameState().myYCoord];
				westButton.setEnabled(true);
				westButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						myGame.getGameState().setDirection(GameState.Direction.WEST);
						myGame.handleMovementResolution();
					}
				});
			}
		}
		
		
		if(northButton == null && southButton == null && eastButton == null && westButton == null) {
			myGame.getGameState().setDirection(GameState.Direction.NONE);
			myGame.handleMovementResolution();
		}
	}
	
	
	
}
