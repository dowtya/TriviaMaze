package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Question;

public class QuestionTest {

	Question myQ;
	
	@BeforeEach
	void setUp() throws Exception {
		myQ = new Question("multiple choice", "What is my name?", 
				"Aaron", "Joe", "Ben", "Aaron");
	}

	@Test
	void testGetMyType() {
		assertEquals("multiple choice", myQ.getMyType(), "getMyType method failed");
	}
	
	@Test
	void testGetMyQuestion() {
		assertEquals("What is my name?", myQ.getMyQuestion(), "getMyQuestion method failed");
	}
	
	@Test
	void testGetMyAnswer() {
		assertEquals("Aaron", myQ.getMyAnswer(), "getMyAnswermethod failed");
	}
	
	@Test
	void testGetMyChoice1() {
		assertEquals("Joe", myQ.getMyChoice1(), "getMyChoice1 method failed");
	}
	
	@Test
	void testgetMyChoice2() {
		assertEquals("Ben", myQ.getMyChoice2(), "getMyChoice2 method failed");
	}
	
	@Test
	void testGetMyChoice3() {
		assertEquals("Aaron", myQ.getMyChoice3(), "getMyChoice3 method failed");
	}
	
	@Test
	void testToString() {
		assertEquals("Type = multiple choice, Question = What is my name?, Answer = Aaron, choice1 = Joe, "
				+ "choice2 = Ben, choice3 = Aaron", myQ.toString(), "toString method failed");
	}
	
}
