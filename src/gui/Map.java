package gui;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.Game;
import model.GameState;
import model.GameState.Direction;

import javax.swing.JOptionPane;

public class Map extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3119341685041081769L;

	Game myGame;
	
	RoomVisual[][] rooms;
	
	public class RoomVisual extends JPanel {

		private final Color COLOR_CLOSED = Color.RED;
		private final Color COLOR_OPEN = Color.BLUE;
		
		public JPanel upperPath;
		public JPanel leftPath;
		public JPanel lowerPath;
		public JPanel rightPath;
		public JButton button;
		
		private Direction myNavDirection;
		
		public void enableNavigation(Direction theNavDirection) {
			myNavDirection = theNavDirection;
			button.setEnabled(true);
		}
		
		public void disableNavigation() {
			button.setEnabled(false);
			myNavDirection = Direction.NONE;
		}
		
		public void markLeftClosed()  { leftPath.setBackground(COLOR_CLOSED); leftPath.repaint(); }
		public void markRightClosed() { rightPath.setBackground(COLOR_CLOSED); rightPath.repaint(); }
		public void markUpperClosed() { upperPath.setBackground(COLOR_CLOSED); upperPath.repaint(); }
		public void markLowerClosed() { lowerPath.setBackground(COLOR_CLOSED); lowerPath.repaint(); }
		
		public void markLeftOpen()  { leftPath.setBackground(COLOR_OPEN);  }
		public void markRightOpen() { rightPath.setBackground(COLOR_OPEN); }
		public void markUpperOpen() { upperPath.setBackground(COLOR_OPEN); }
		public void markLowerOpen() { lowerPath.setBackground(COLOR_OPEN); }
		
		
		
		public RoomVisual() {
			button = new JButton();
			button.setAlignmentX(CENTER_ALIGNMENT);
			button.setAlignmentY(CENTER_ALIGNMENT);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					myGame.getGameState().setDirection(myNavDirection);
					
					switch (myNavDirection) {
					case NORTH:
						System.out.println("Player choose to move North."); break;
					case SOUTH:
						System.out.println("Player choose to move South."); break;
					case EAST:
						System.out.println("Player choose to move East."); break;
					case WEST:
						System.out.println("Player choose to move West."); break;
					default:
						System.out.println("Player choose to move in an undefined direction.");
						break;
					}
					
					resetButtons();
					myGame.handleMovementResolution();
				}
			});
			button.setEnabled(false);
			
			upperPath = new JPanel();
			leftPath = new JPanel();
			lowerPath = new JPanel();
			rightPath = new JPanel();
			
			
			upperPath.setPreferredSize(new Dimension(10, 10));
			lowerPath.setPreferredSize(new Dimension(10, 10));
			rightPath.setPreferredSize(new Dimension(10, 10));
			leftPath.setPreferredSize(new Dimension(10, 10));
			
			
			JPanel upperPathContainer = new JPanel();
			upperPathContainer.setLayout(new GridBagLayout());
			upperPathContainer.add(upperPath);
			//((FlowLayout)upperPathContainer.getLayout()).setVgap(0);
			
			JPanel leftPathContainer = new JPanel();
			leftPathContainer.setLayout(new GridBagLayout());
			leftPathContainer.add(leftPath);
			
			JPanel lowerPathContainer = new JPanel();
			lowerPathContainer.setLayout(new GridBagLayout());
			lowerPathContainer.add(lowerPath);
			//((FlowLayout)lowerPathContainer.getLayout()).setVgap(0);
			
			JPanel rightPathContainer = new JPanel();
			rightPathContainer.setLayout(new  GridBagLayout());
			rightPathContainer.add(rightPath);
			//((FlowLayout)rightPathContainer.getLayout()).setHgap(0);
			
			setLayout(new BorderLayout(5,5));
			
			add(button, BorderLayout.CENTER);
			
			add(upperPathContainer, BorderLayout.PAGE_START);
			add(lowerPathContainer, BorderLayout.PAGE_END);
			add(leftPathContainer, BorderLayout.LINE_START);
			add(rightPathContainer, BorderLayout.LINE_END);
		}
	}
	
	public Map(Game theGame) {
		myGame = theGame;
		
		int mazeWidth = myGame.getGameState().getMazeWidth();
		int mazeHeight = myGame.getGameState().getMazeHeight();
		
		rooms = new RoomVisual[mazeWidth][mazeHeight];
		this.setLayout(new GridLayout(mazeWidth, mazeHeight, 0, 0));
		// create the 'rooms'
		for (int y = 0; y < mazeHeight; y++) {
			for (int x = 0; x < mazeWidth; x++) {
				rooms[x][y] = new RoomVisual();
				
				if(myGame.getGameState().getPathOpenBetweenRooms(x, y, x - 1, y)) {
					rooms[x][y].markLeftOpen();
				} else {
					rooms[x][y].markLeftClosed();
				}
				
				if(myGame.getGameState().getPathOpenBetweenRooms(x, y, x + 1, y)) {
					rooms[x][y].markRightOpen();
				} else {
					rooms[x][y].markRightClosed();
				}
				
				
				if(myGame.getGameState().getPathOpenBetweenRooms(x, y, x, y - 1)) {
					rooms[x][y].markUpperOpen();
				} else {
					rooms[x][y].markUpperClosed();
				}
				
				if(myGame.getGameState().getPathOpenBetweenRooms(x, y, x, y + 1)) {
					rooms[x][y].markLowerOpen();
				} else {
					rooms[x][y].markLowerClosed();
				}
				
				this.add(rooms[x][y]);
			}
		}
		
		this.setBounds(200,200, 1000, 1000);
		this.setBackground(Color.DARK_GRAY);
		this.setVisible(true);
		updateVisuals();
	}
	
	// display the current map
	public void updateVisuals() {
		
		if(myGame.getGameState().checkDefeat()) {
			//JOptionPane popUp = new JOptionPane();
			//this.add(popUp);
			JOptionPane.showMessageDialog(this.getRootPane(), "You Lose!");
			myGame.myExitRoutine.apply(null);
		}
		
		if(myGame.getGameState().checkVictory()) {
			JOptionPane.showMessageDialog(this.getRootPane(), "You Win!");
			myGame.myExitRoutine.apply(null);
		}
		
		// remark only the paths that should have changed
		
		if(!myGame.getGameState().getQuestionState().isAnsweredCorrectly()) {
			RoomVisual curRoom = rooms[myGame.getGameState().myXCoord][myGame.getGameState().myYCoord];
			
			switch (myGame.getGameState().getDirection()) {
			case NORTH:
				curRoom.markUpperClosed();
				rooms[myGame.getGameState().myXCoord][myGame.getGameState().myYCoord - 1].markLowerClosed();
				break;
			case SOUTH:
				curRoom.markLowerClosed();
				rooms[myGame.getGameState().myXCoord][myGame.getGameState().myYCoord + 1].markUpperClosed();
				break;
			case EAST:
				curRoom.markRightClosed();
				rooms[myGame.getGameState().myXCoord + 1][myGame.getGameState().myYCoord].markLeftClosed();
				break;
			case WEST:
				curRoom.markLeftClosed();
				rooms[myGame.getGameState().myXCoord - 1][myGame.getGameState().myYCoord].markRightClosed();
				break;
			default:
				break;
			}
		}
		
		
		// remark all paths; Unused due to lag
		/*
		for(int y = 0; y < myGame.getGameState().getMazeHeight(); y++) {
			for(int x = 0; x < myGame.getGameState().getMazeWidth(); x++) {
				if(myGame.getGameState().getPathOpenBetweenRooms(x, y, x - 1, y)) {
					rooms[x][y].markLeftOpen();
				} else {
					rooms[x][y].markLeftClosed();
				}
				
				if(myGame.getGameState().getPathOpenBetweenRooms(x, y, x + 1, y)) {
					rooms[x][y].markRightOpen();
				} else {
					rooms[x][y].markRightClosed();
				}
				
				
				if(myGame.getGameState().getPathOpenBetweenRooms(x, y, x, y - 1)) {
					rooms[x][y].markUpperOpen();
				} else {
					rooms[x][y].markUpperClosed();
				}
				
				if(myGame.getGameState().getPathOpenBetweenRooms(x, y, x, y + 1)) {
					rooms[x][y].markLowerOpen();
				} else {
					rooms[x][y].markLowerClosed();
				}
			}
		}*/
		
	}
	
	
	private void resetButtons() {
		for (int y = 0; y < myGame.getGameState().getMazeHeight(); y++) {
			for (int x = 0; x < myGame.getGameState().getMazeWidth(); x++) {
				rooms[x][y].disableNavigation();
			}
		}
	}
	
	// create clickable arrows to move the player; call callback with the direction selected.
	public void getPlayerMovement() {
		
		boolean movementAvailable = false;
		
		if(myGame.getGameState().getPathOpenFromPlayer(0, -1) && (myGame.getGameState().myYCoord > 0)) {
			rooms[myGame.getGameState().myXCoord][myGame.getGameState().myYCoord - 1].enableNavigation(Direction.NORTH);
			movementAvailable = true;
		}
		
		if(myGame.getGameState().getPathOpenFromPlayer(0, 1) && (myGame.getGameState().myYCoord < myGame.getGameState().getMazeHeight())) {
			rooms[myGame.getGameState().myXCoord][myGame.getGameState().myYCoord + 1].enableNavigation(Direction.SOUTH);
			movementAvailable = true;
		}
	
		if(myGame.getGameState().getPathOpenFromPlayer(-1, 0) && (myGame.getGameState().myXCoord > 0)) {
			rooms[myGame.getGameState().myXCoord - 1][myGame.getGameState().myYCoord].enableNavigation(Direction.WEST);
			movementAvailable = true;
		}
	
		if(myGame.getGameState().getPathOpenFromPlayer(1, 0) && (myGame.getGameState().myXCoord < myGame.getGameState().getMazeWidth())) {
			rooms[myGame.getGameState().myXCoord + 1][myGame.getGameState().myYCoord].enableNavigation(Direction.EAST);
			movementAvailable = true;
		}
		
		
		if(!movementAvailable) {
			myGame.getGameState().setDirection(GameState.Direction.NONE);
			myGame.handleMovementResolution();
		}
	}
	
	
	
}
