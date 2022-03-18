package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.Game;
import controller.QuestionController;
import model.Question;

public class QuestionControllerTest {
	private QuestionController questionController;
	
	@BeforeEach
	void setUp() throws Exception {
		questionController = new QuestionController(new ArrayList<Question>(), new Game());
	}

	@Test
	void testConstructor() {
		assertNotNull(questionController, "Parameterized Constructor Failed");
	}
	
}
