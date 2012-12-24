package persistenty;

import java.sql.*;
import java.util.logging.Logger;

import javax.swing.text.StyleContext.SmallAttributeSet;

import statePattern.InConstructieStatus;
import statePattern.QuizStatus;

import model.Opdracht;
import model.Quiz;

public class SqlDaoFacade implements DaoFacade{

	@Override
	public void createQuiz(Quiz quiz){
		try{
			
			Statement statement = null;
			String DB_URL = "jdbc:mysql://localhost/QuizDB";
			
			Connection con = DriverManager.getConnection(DB_URL,"root","Quizdnt123");
				
			statement = con.createStatement();
			voegQuizToe(con,quiz);
			
			if (con != null) {
                con.close();
            }
		}
		catch (SQLException sqlEx){
			System.out.println("aiai" + sqlEx.getMessage());
		}
	}
	
	public void voegQuizToe(Connection con, Quiz quiz) throws SQLException{
		PreparedStatement insertQuiz = null;
		
		try {
			QuizStatus quizStatus = quiz.getQuizStatus();
			String status = quizStatus.toString();
			insertQuiz = con.prepareStatement("INSERT INTO Quiz " +
                    "(onderwerp,leerjaren,isTest,isUniekeDeelname,quizSatus) " +
                    "values (?,?,?,?,?)");
			
			insertQuiz.setString(1,quiz.getOnderwerp());
			insertQuiz.setInt(2,quiz.getMinLeerjaar());
			insertQuiz.setBoolean(3,quiz.isTest());
			insertQuiz.setBoolean(4, quiz.isUniekeDeelname());
			insertQuiz.setString(5, status);
			
			insertQuiz.executeUpdate();

        } catch (SQLException ex) {
        	ex.printStackTrace();

        } finally {

            try {
                if (insertQuiz != null) {
                	insertQuiz.close();
                }
            } catch (SQLException ex) {
            }
        }

	}
	
	@Override
	public void createOpdracht(Opdracht opdracht) {
		// TODO Auto-generated method stub
		
	}
}
