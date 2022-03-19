package tests;

import model.QuestionState;
import model.GameState;
import model.GameState.Direction;
import controller.Game;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class GameTest {

	private GameState myGamestate;
	private Game myGame;
	private QuestionState myQuestionState;
	
	@BeforeEach
	void setUp() throws Exception {
		myGame = new Game();
		myGamestate = myGame.getGameState();
		myQuestionState = myGamestate.getQuestionState();
		
	}
	
	@Test
	void testGetXCoord() {
		assertEquals(0, myGamestate.getXCoord(), "Not correct xcoord");
	}
	
	@Test
	void testGetYCoord() {
		assertEquals(0, myGamestate.getYCoord(), "Not correct ycoord");
	}
	
	
	
	@Test
	void testHandleQuestionResolutionEAST() {
		myGamestate.setDirection(Direction.EAST);
		final Direction theDirection = myGamestate.getDirection();
		myQuestionState.setAnsweredCorrectly(true);
		myGame.handleQuestionResolution();
		assertEquals(1, myGamestate.getXCoord(), "Didn't add to XCoord properly");
	}
	
	@Test
	void testHandleQuestionResolutionWEST() {
		myGamestate.setXCoord(1);
		myGamestate.setDirection(Direction.WEST);
		final Direction theDirection = myGamestate.getDirection();
		myQuestionState.setAnsweredCorrectly(true);
		myGame.handleQuestionResolution();
		assertEquals(0, myGamestate.getXCoord(), "Didn't add to XCoord properly");
	}
	
	@Test
	void testHandleQuestionResolutionNORTH() {
		myGamestate.setYCoord(1);
		myGamestate.setDirection(Direction.NORTH);
		final Direction theDirection = myGamestate.getDirection();
		myQuestionState.setAnsweredCorrectly(true);
		myGame.handleQuestionResolution();
		assertEquals(0, myGamestate.getYCoord(), "Didn't subtract from YCoord properly");
	}
	
	@Test
	void testHandleQuestionResolutionSOUTH() {
		myGamestate.setDirection(Direction.SOUTH);
		final Direction theDirection = myGamestate.getDirection();
		myQuestionState.setAnsweredCorrectly(true);
		myGame.handleQuestionResolution();
		assertEquals(1, myGamestate.getYCoord(), "Didn't add to YCoord properly");
	}
	
	@Test
	void testHandleQuestionResolutionWrongEAST() {
		myGamestate.setDirection(Direction.EAST);
		final Direction theDirection = myGamestate.getDirection();
		myQuestionState.setAnsweredCorrectly(false);
		myGame.handleQuestionResolution();
		assertEquals(false, myGamestate.getPathOpenBetweenRooms(myGamestate.getXCoord(), myGamestate.getYCoord(), myGamestate.getXCoord() + 1, myGamestate.getYCoord()), "Didn't block room properly!");
	}
	
	@Test
	void testHandleQuestionResolutionWrongNORTH() {
		myGamestate.setXCoord(3);
		myGamestate.setDirection(Direction.WEST);
		final Direction theDirection = myGamestate.getDirection();
		myQuestionState.setAnsweredCorrectly(false);
		myGame.handleQuestionResolution();
		assertEquals(false, myGamestate.getPathOpenBetweenRooms(myGamestate.getXCoord(), myGamestate.getYCoord(), myGamestate.getXCoord(), myGamestate.getYCoord() - 1), "Didn't block room properly!");
	}
	
	@Test
	void testHandleQuestionResolutionWrongSOUTH() {
		myGamestate.setDirection(Direction.WEST);
		final Direction theDirection = myGamestate.getDirection();
		myQuestionState.setAnsweredCorrectly(false);
		myGame.handleQuestionResolution();
		assertEquals(false, myGamestate.getPathOpenBetweenRooms(myGamestate.getXCoord(), myGamestate.getYCoord(), myGamestate.getXCoord(), myGamestate.getYCoord() + 1), "Didn't block room properly!");
	}
	
	@Test
	void testHandleQuestionResolutionWrongWEST() {
		myGamestate.setXCoord(3);
		myGamestate.setDirection(Direction.WEST);
		final Direction theDirection = myGamestate.getDirection();
		myQuestionState.setAnsweredCorrectly(false);
		myGame.handleQuestionResolution();
		assertEquals(false, myGamestate.getPathOpenBetweenRooms(myGamestate.getXCoord(), myGamestate.getYCoord(), myGamestate.getXCoord() - 1, myGamestate.getYCoord()), "Didn't block room properly!");
	}
}