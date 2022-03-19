package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.GameState;
import model.QuestionState;

class GameStateTest {
	
	private GameState gamestate1;
	private GameState gamestate2;
	
	@BeforeEach
	void setUp() throws Exception {
		
		gamestate1 = new GameState(5, 5);
		gamestate2 = new GameState(5, 5);
	}
	
	@Test
	void testGameStateConstructor() {
		assertEquals(5, gamestate1.getMazeHeight(), "Parameterized Constructor Failed");
		assertEquals(5, gamestate1.getMazeWidth(), "Parameterized Constructor Failed");
		assertNotNull(gamestate1.getQuestionState(), "Failed to construct question state");
	}
	
	@Test
	void testSetQuestionState() {
		QuestionState q = new QuestionState();
		gamestate1.setQuestionState(q);
		assertEquals(q, gamestate1.getQuestionState(), "setQuestionState method failed");
	}
	
	@Test
	void testSetDirection() {
		gamestate1.setDirection(GameState.Direction.EAST);
		assertEquals(GameState.Direction.EAST, gamestate1.getDirection(), "setDirection method has failed");
	}
	
	@Test
	void testSetXCoord() {
		gamestate1.setXCoord(3);
		assertEquals(3, gamestate1.getXCoord(), "setXCoord method failed");
	}
	
	@Test
	void testSetYCoord() {
		gamestate1.setYCoord(3);
		assertEquals(3, gamestate1.getYCoord(), "setYCoord method failed");
	}
	
	@Test
	void testGetPathOpenBetweenRooms() {
		assertEquals(true, gamestate1.getPathOpenBetweenRooms(1, 2, 1, 1), "getPathOpenBetweenRooms method failed.");
		assertEquals(true, gamestate1.getPathOpenBetweenRooms(1, 1, 1, 2), "getPathOpenBetweenRooms method failed.");
		assertEquals(false, gamestate1.getPathOpenBetweenRooms(1, 6, 1, 3), "getPathOpenBetweenRooms method failed.");
		assertEquals(true, gamestate1.getPathOpenBetweenRooms(1, 3, 2, 3), "getPathOpenBetweenRooms method failed.");
		assertEquals(false, gamestate1.getPathOpenBetweenRooms(6, 3, 4, 3), "getPathOpenBetweenRooms method failed.");
	}
	
	@Test
	void testSetPathOpenBetweenRooms() {
		gamestate1.setPathOpenBetweenRooms(1, 2, 1, 1, false);
		gamestate1.setPathOpenBetweenRooms(1, 2, 2, 2, false);
		gamestate1.setPathOpenBetweenRooms(1, 2, 1, 3, false);
		assertEquals(false, gamestate1.getMyPaths()[1][1], "setPathOpenBetweenRooms method failed.");
		assertEquals(true, gamestate1.getMyPaths()[2][4], "setPathOpenBetweenRooms method failed.");
		assertEquals(true, gamestate1.getMyPaths()[1][5], "setPathOpenBetweenRooms method failed.");
	}
	
	@Test
	void testIsPathAvailable() {
		boolean[][] paths1 = {{false, false, false, false, false},
							 {false, false, false, false, false},
							 {false, false, false, false, false},
							 {false, false, false, false, false},
							 {false, false, false, false, false}};
		boolean[][] paths2 = {{false, false, false, false, false},
				             {false, false, false, false, false},
				             {false, false, false, false, false},
				             {false, false, false, true, true},
				             {false, false, false, true, true}};
		gamestate1.setXCoord(2);
		gamestate1.setYCoord(2);
		gamestate2.setXCoord(2);
		gamestate2.setYCoord(2);
		gamestate1.setMyPaths(paths1);
		gamestate2.setMyPaths(paths2);
		assertEquals(true, gamestate1.isPathAvailable(), "isPathAvailable method failed");
		assertEquals(false, gamestate2.isPathAvailable(), "isPathAvailable method failed");
	}
	
	@Test
	void testCheckVictory() {
		gamestate1.setXCoord(4);
		gamestate1.setYCoord(4);
		gamestate2.setXCoord(3);
		gamestate2.setYCoord(4);
		assertEquals(true, gamestate1.checkVictory(), "checkVictory method failed");
		assertEquals(false, gamestate2.checkVictory(), "checkVictory method failed");
	}
	
	@Test
	void testCheckDefeat() {
		boolean[][] paths1 = {{false, false, false, false, false},
	             			  {false, false, false, false, false},
	             			  {false, false, false, false, false},
	             			  {false, false, false, true, true},
	             			  {false, false, false, true, true}};
		boolean[][] paths2 = {{false, false, false, false, false},
				 			  {false, false, false, false, false},
				 			  {false, false, false, false, false},
				 			  {false, false, false, false, false},
				 			  {false, false, false, false, false}};
		gamestate1.setMyPaths(paths1);
		gamestate1.setXCoord(2);
		gamestate1.setYCoord(2);
		gamestate2.setMyPaths(paths2);
		gamestate2.setXCoord(2);
		gamestate2.setYCoord(2);
		assertEquals(true, gamestate1.checkDefeat(), "checkDefeat method failed");
		assertEquals(false, gamestate2.checkDefeat(), "checkDefeat method failed");
	}
}
