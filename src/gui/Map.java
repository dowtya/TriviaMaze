package gui;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.Game;
import model.GameState;
import model.GameState.Direction;

import javax.swing.JOptionPane;

/**
 * Map class which displays the current state of the maze and handles the movement selection to navigate between rooms.
 * @author Alec Dowty
 * @author Aaron Gitell
 * @author Joel Hemphill
 */

@SuppressWarnings("serial")
public class Map extends JPanel {

	/*
	 * A reference to the owning Game object.
	 */
	Game myGame;
	
	/*
	 * A grid of visual components that represent each room in the maze.
	 */
	RoomVisual[][] rooms;
	
	/**
	 * A helper class which handles the display of individual rooms within the map grid.
	 */
	public class RoomVisual extends JPanel {
		private final Color COLOR_CLOSED = Color.RED;
		private final Color COLOR_OPEN = Color.BLUE;
		
		/*
		 * The swing element marking the connection to the room above this one.
		 */
		private JPanel myUpperPath;
		/*
		 * The swing element marking the connection to the room above this one.
		 */
		private JPanel myLeftPath;
		/*
		 * The swing element marking the connection to the room below this one.
		 */
		private JPanel myLowerPath;
		/*
		 * The swing element marking the connection to the room to the left of this one.
		 */
		private JPanel myRightPath;
		/*
		 * The button used to navigate into this room.
		 */
		private JButton myButton;
		
		/*
		 * The direction it should be consider the player moved if the player navigates into this room.
		 */
		private Direction myNavDirection;
		
		/**
		 * Enables navigation for this room, and records which direction it would be considered if the player navigated to it.
		 * @param theNavDirection the Direction it would be considered if the player navigated to this room
		 */
		public void enableNavigation(Direction theNavDirection) {
			myNavDirection = theNavDirection;
			myButton.setEnabled(true);
		}
		
		/**
		 * Disables navigation for this room.
		 */
		public void disableNavigation() {
			myButton.setEnabled(false);
			myNavDirection = Direction.NONE;
		}
		
		/**
		 * Marks the left path as closed.
		 */
		public void markLeftClosed()  { myLeftPath.setBackground(COLOR_CLOSED); }
		/**
		 * Marks the right path as closed.
		 */
		public void markRightClosed() { myRightPath.setBackground(COLOR_CLOSED); }
		/**
		 * Marks the upper path as closed.
		 */
		public void markUpperClosed() { myUpperPath.setBackground(COLOR_CLOSED); }
		/**
		 * Marks the lower path as closed.
		 */
		public void markLowerClosed() { myLowerPath.setBackground(COLOR_CLOSED); }
		
		/**
		 * Marks the left path as open.
		 */
		public void markLeftOpen()  { myLeftPath.setBackground(COLOR_OPEN);  }
		/**
		 * Marks the right path as open.
		 */
		public void markRightOpen() { myRightPath.setBackground(COLOR_OPEN); }
		/**
		 * Marks the upper path as open.
		 */
		public void markUpperOpen() { myUpperPath.setBackground(COLOR_OPEN); }
		/**
		 * Marks the lower path as open.
		 */
		public void markLowerOpen() { myLowerPath.setBackground(COLOR_OPEN); }
		
		/**
		 * Initializes a room's swing elements and establishes the button's functionality.
		 */
		public RoomVisual() {
			myButton = new JButton();
			myButton.setAlignmentX(CENTER_ALIGNMENT);
			myButton.setAlignmentY(CENTER_ALIGNMENT);
			myButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent theEvent) {
					
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
			myButton.setEnabled(false);
			
			myUpperPath = new JPanel();
			myLeftPath = new JPanel();
			myLowerPath = new JPanel();
			myRightPath = new JPanel();
			
			
			myUpperPath.setPreferredSize(new Dimension(10, 10));
			myLowerPath.setPreferredSize(new Dimension(10, 10));
			myRightPath.setPreferredSize(new Dimension(10, 10));
			myLeftPath.setPreferredSize(new Dimension(10, 10));
			
			
			JPanel upperPathContainer = new JPanel();
			upperPathContainer.setLayout(new GridBagLayout());
			upperPathContainer.add(myUpperPath);
			
			JPanel leftPathContainer = new JPanel();
			leftPathContainer.setLayout(new GridBagLayout());
			leftPathContainer.add(myLeftPath);
			
			JPanel lowerPathContainer = new JPanel();
			lowerPathContainer.setLayout(new GridBagLayout());
			lowerPathContainer.add(myLowerPath);
			
			JPanel rightPathContainer = new JPanel();
			rightPathContainer.setLayout(new  GridBagLayout());
			rightPathContainer.add(myRightPath);
			
			setLayout(new BorderLayout(5,5));
			add(myButton, BorderLayout.CENTER);
			add(upperPathContainer, BorderLayout.PAGE_START);
			add(lowerPathContainer, BorderLayout.PAGE_END);
			add(leftPathContainer, BorderLayout.LINE_START);
			add(rightPathContainer, BorderLayout.LINE_END);
		}
	}
	
	/**
	 * Initializes the Map and creates the necessary swing elements.
	 * @param theGame A reference to the owning Game Object
	 */
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
	
	/**
	 *  Updates the map to reflect changes in GameState.
	 */
	public void updateVisuals() {
		
		if(myGame.getGameState().checkDefeat()) {
			JOptionPane.showMessageDialog(this.getRootPane(), "You Lose!");
			myGame.myExitRoutine.apply(null);
		}
		
		if(myGame.getGameState().checkVictory()) {
			JOptionPane.showMessageDialog(this.getRootPane(), "You Win!");
			myGame.myExitRoutine.apply(null);
		}
			
		// remark all paths
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
		}
		
	}
	
	/**
	 * Resets all the buttons used to navigate between rooms.
	 */
	private void resetButtons() {
		for (int y = 0; y < myGame.getGameState().getMazeHeight(); y++) {
			for (int x = 0; x < myGame.getGameState().getMazeWidth(); x++) {
				rooms[x][y].disableNavigation();
			}
		}
	}
	
	/**
	 *  Creates buttons on available rooms to move the player; calls callback with the direction selected.
	 */
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
