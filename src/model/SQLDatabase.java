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
    	addMultipleChoice(DS, "Mario originated as a character in which video game?",
    			"donkey kong", "donkey kong", "super mario", "mario party");
    	addMultipleChoice(DS, "Which video game franchise uses V-bucks as currency?", "fortnite",
    			"PUBG", "H1Z1", "fortnite");
    	addMultipleChoice(DS, "What product did Nintendo originally sell?", "playing cards",
    			"game consoles", "arcade games", "playing cards");
    	addMultipleChoice(DS, "How many overworlds are in Cupworld?", "four",
    			"two", "four", "seven");
    	addMultipleChoice(DS, "To celebrate its 30th birthday in 2010, Google placed a playable version of what arcade game?", 
    			"Pac-Man", "Asteroids", "Pac-Man", "Doodle Jump");
    	addMultipleChoice(DS, "Which French video game company is publishing the Far Cry series?", "ubisoft",
    			"ubisoft", "fromSoftware", "electronic arts");
    	addMultipleChoice(DS, "In which game do players compete in the future version of soccer with cars?", "rocket league",
    			"fifa", "mario kart", "rocket league");
    	addMultipleChoice(DS, "What was Marios first job?", "carpenter", "plumber", "carpenter", "electrician");
    	addMultipleChoice(DS, "What year was the first Call of Duty game released?", "2003", "2000",
    			"2007", "2003");
    	addMultipleChoice(DS, "Whats the best selling video game of all time?", "minecraft", "super smash bros",
    			"minecraft", "halo 2");
    	addTrueFalse(DS, "The final course in all of the mario kart games is rainbow road.", "true");
    	addTrueFalse(DS, "The first nintendo console to use optical disks was the Wii.", "false");
    	addTrueFalse(DS, "Blizzard Entertainment is most well known for the World of War Craft franchise.", "true");
    	addTrueFalse(DS, "The US air force used the playstation 3 to create a cluster supercomputer.", "true");
    	addTrueFalse(DS, "Game Boy is the best-selling handheld gaming system to date.", "false");
    	addTrueFalse(DS, "The first virtual reality headset was created in 1995.", "true");
    	addTrueFalse(DS, "Nintendo released the first flight simulator game.", "false");
    	addTrueFalse(DS, "The creator of the Game Boy was a janitor at Nintendo.", "true");
    	addTrueFalse(DS, "Marios dinosaur sidekick is named bowser.", "false");
    	addTrueFalse(DS, "Motion sickness plagues up to 40% of virtual reality users", "true");
    
    	addShortAnswer(DS, "What was the first commercially successful video game?", "pong");
    	addShortAnswer(DS, "Which Nintendo system first had a 007 video game?", "nintendo 64");
    	addShortAnswer(DS, "What is the name of Crash Bandicoots sister?", "naughty dog");
    	addShortAnswer(DS, "What computer video game allowed you to play real life simulation in the 1990s?", "the sims");
    	addShortAnswer(DS, "What Mortal Kombat character was based on a ninja?", "sub zero");
    	addShortAnswer(DS, "Duke Nukem was a video game created by which game developer?", "apogee");
    	addShortAnswer(DS, "What was the most popular arcade video game in the 1980s?", "pac man");
    	addShortAnswer(DS, "GTA stands for what?", "grand theft auto");
    	addShortAnswer(DS, "In the game ‘Fortnight Battle Royale’, how many players can play simultaneously in competition?", "100");
    	addShortAnswer(DS, "The famous video game heroin Lara Croft belonged to which game?", "tomb raider");
    	
    	ArrayList<Question> questions = createQuestionList(DS);
    	setMyQuestionList(questions);
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
