package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.GameState;

class GameStateTest {
	
	private GameState gamestate1;
	@BeforeEach
	void setUp() throws Exception {
		
		gamestate1 = new GameState(5, 5);
		
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	
	
	@Test
	void testGameStateConstructor() {
		
		assertEquals(5, gamestate1.getMazeHeight(), "Parameterized Constructor Failed");
		assertEquals(5, gamestate1.getMazeWidth(), "Parameterized Constructor Failed");
		assertNotNull(gamestate1.getQuestionState(), "Failed to construct question state");
		
	}
	
	
}
