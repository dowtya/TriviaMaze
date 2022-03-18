package tests;

import model.QuestionState;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuestionStateTest {

	private QuestionState myQuestionState;
	
	@BeforeEach
	void setUp() throws Exception {
		myQuestionState = new QuestionState();
	}

	@Test
	void testAnsweredCorrectly() {
		myQuestionState.setAnsweredCorrectly(true);
		assertEquals(true, myQuestionState.isAnsweredCorrectly(), "Failed to set answered correctly.");
		myQuestionState.setAnsweredCorrectly(false);
		assertEquals(false, myQuestionState.isAnsweredCorrectly(), "Failed to set answered correctly.");
	}
	
}
