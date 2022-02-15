package model;

public class QuestionState {

	// A list of questions
	
	boolean askedQuestions[]; // An array of flags marking which have been asked
	int currentQuestion;
	boolean answeredQuestionCorrectly = false;
	
	Serialize();
	Deserialize();
}
