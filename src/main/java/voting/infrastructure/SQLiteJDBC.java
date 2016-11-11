package voting.infrastructure;

import java.sql.*;

public class SQLiteJDBC {

    private final static String CREATE_SURVEY_TABLE = "CREATE TABLE survey " +
            "(ID INT PRIMARY KEY    NOT NULL," +
            "Title          TEXT    NOT NULL," +
            "Description    TEXT    NOT NULL," +
            "CreatedTime    DATETIME    NOT NULL);";
    private final static String CREATE_VOTE_TABLE = "CREATE TABLE vote " +
            "(ID INT PRIMARY KEY    NOT NULL," +
            "Title          TEXT    NOT NULL," +
            "SurveyID       INT    NOT NULL," +
            "CreatedTime    DATETIME    NOT NULL);";


    public SQLiteJDBC(){

        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:voteing.db");
            System.out.println("Opened database successfully");

            statement = connection.createStatement();

            statement.executeUpdate(CREATE_SURVEY_TABLE + CREATE_VOTE_TABLE);
            statement.close();
            connection.close();
        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    public void addSurvey(String id, String title, String description){
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:voteing.db");
            System.out.println("Opened database successfully");

            statement = connection.createStatement();
            String sql = "INSERT INTO survey (ID, Title, Description, CreatedTime) " +
                    "VALUES ("+id+","+title+","+description+",)";
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Survey created successfully");

    }

}
