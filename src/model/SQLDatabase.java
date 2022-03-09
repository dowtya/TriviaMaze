package model;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.sqlite.SQLiteDataSource;

public class SQLDatabase {
	
	private ArrayList<Question> myQuestionList;
	
	public SQLDatabase() {
		myQuestionList = new ArrayList<Question>();
	}
	
	public void setUp() {
		//SQLDatabase DB = new SQLDatabase();
    	SQLiteDataSource DS = establishConnection("jdbc:sqlite:questions.db");
    	createEmptyTable(DS);
    	addMultipleChoice(DS, "What year was the very first model of the iphone released?",
    			"2007", "2005", "2007", "2008");
    	addMultipleChoice(DS, "What is the shortcut to copy on most computers?", "ctrl c",
    			"alt c", "shift c", "ctrl c");
    	addMultipleChoice(DS, "What does HTTP stand for?", "hypertext transfer protocol",
    			"hypertext transfer protocol", "hypertext transfer procedure", 
    			"hyper transmitter protocol");
    	addMultipleChoice(DS, "Who is often called the father of the computer?", "Charles Babbage",
    			"Alan Touring", "Charles Babbage", "James Gosling");
    	addMultipleChoice(DS, "Who discovered Penacillin?", "Alexander Flemming",
    			"Dr. Frankenstein", "Albert Einstein", "Alexander Flemming");
    	addTrueFalse(DS, "Java is a type of OS.", "false");
    	addShortAnswer(DS, "What is the symbol for potassium?", "K");
    	ArrayList<Question> questions = createQuestionList(DS);
    	setMyQuestionList(questions);
    	//for (int i = 0; i < myQuestionList.size(); i++) {
    		//System.out.println(myQuestionList.get(i));
    	//}
    }
    
    public static SQLiteDataSource establishConnection(String theURL) {
        SQLiteDataSource ds = null;

        try {
            ds = new SQLiteDataSource();
            ds.setUrl(theURL);
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }

        return ds;
    }
    
    public static void createEmptyTable(SQLiteDataSource theDS) {
        String query = "CREATE TABLE IF NOT EXISTS questions ( " +
                "TYPE TEXT NOT NULL, " +
        		"QUESTION TEXT NOT NULL, " +
                "ANSWER TEXT NOT NULL, " +
                "CHOICE1 TEXT, " +
                "CHOICE2 TEXT, " +
                "CHOICE3 TEXT)";
        try ( Connection conn = theDS.getConnection();
                Statement stmt = conn.createStatement(); ) {
                stmt.executeUpdate( query );
          } catch ( SQLException e ) {
              e.printStackTrace();
              System.exit( 0 );
          }
    }
    
    public static void addMultipleChoice(SQLiteDataSource theDS, String theQuestion, 
    		String theAnswer, String theChoice1, String theChoice2, String theChoice3) {
    	String query = "INSERT INTO questions ( TYPE, QUESTION, ANSWER, CHOICE1, CHOICE2, CHOICE3 ) VALUES ( 'multiple choice', '" +
    		theQuestion + "', '" + theAnswer + "', '" + theChoice1 + "', '" + theChoice2 + "', '" + 
    		theChoice3 + "' )";
        try ( Connection conn = theDS.getConnection();
                Statement stmt = conn.createStatement(); ) {
              stmt.executeUpdate( query );
          } catch ( SQLException e ) {
              e.printStackTrace();
              System.exit( 0 );
          }
    }
    
    public static void addTrueFalse(SQLiteDataSource theDS, String theQuestion, String theAnswer) {
    	String query = "INSERT INTO questions ( TYPE, QUESTION, ANSWER, CHOICE1, CHOICE2 ) VALUES ( 'True/False', '" +
        		theQuestion + "', '" + theAnswer + "', 'true', 'false' )";
            try ( Connection conn = theDS.getConnection();
                    Statement stmt = conn.createStatement(); ) {
                  stmt.executeUpdate( query );
              } catch ( SQLException e ) {
                  e.printStackTrace();
                  System.exit( 0 );
              }
    }
    
    public static void addShortAnswer(SQLiteDataSource theDS, String theQuestion, String theAnswer) {
    	String query = "INSERT INTO questions ( TYPE, QUESTION, ANSWER ) VALUES ( 'Short Answer', '" +
        		theQuestion + "', '" + theAnswer + "' )";
            try ( Connection conn = theDS.getConnection();
                    Statement stmt = conn.createStatement(); ) {
                  stmt.executeUpdate( query );
              } catch ( SQLException e ) {
                  e.printStackTrace();
                  System.exit( 0 );
              }
    }
    
    public static ArrayList<Question> createQuestionList(SQLiteDataSource theDS) {
    	ArrayList<Question> result = new ArrayList<Question>(); 
        String query = "SELECT * FROM questions";

        try ( Connection conn = theDS.getConnection();
              Statement stmt = conn.createStatement(); ) {
            
            ResultSet rs = stmt.executeQuery(query);
            
            while ( rs.next() ) {
            	String type = rs.getString( "TYPE" );
                String question = rs.getString( "QUESTION" );
                String rightAnswer = rs.getString( "ANSWER" );
                String choice1 = rs.getString( "CHOICE1");
                String choice2 = rs.getString( "CHOICE2" );
                String choice3 = rs.getString( "CHOICE3" );
                Question q = new Question(type, question, rightAnswer, choice1, choice2, choice3);
                result.add(q);
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        
        return result;
    }
    
    public ArrayList<Question> getMyQuestionList() {
    	return myQuestionList;
    }
    
    public void setMyQuestionList(ArrayList<Question> theQuestionList) {
    	myQuestionList = theQuestionList;
    }
    
}
