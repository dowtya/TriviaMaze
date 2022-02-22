package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteDataSource;

public class SQLDatabase {
    public static void main(String[] theArgs) {
    	establishConnection("jdbc:sqlite:questions.db");
    }
    
    public static Connection establishConnection(String theURL) {
        Connection c = null;

        try {
            c = DriverManager.getConnection(theURL);
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit(0);
        }
        //System.out.println( "Opened database successfully" );
        return c;
    }
}
