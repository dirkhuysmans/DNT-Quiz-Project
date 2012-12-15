package persistenty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Quiz;

public class SqlDaoFacade implements DaoFacade{

	@Override
	public void createQuiz(Quiz quiz){
		Connection con = null;
		String DB_URL = "jdbc:mysql://localhost/QuizDB";
		makeConnection(DB_URL);
		try{
			
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery("Select * from QuizDB.Quiz");
			
			while(rs.next()){
				System.out.println(rs.getString(2));
			}
		}
		catch (SQLException sqlEx){
			System.out.println("aiai" + sqlEx.getMessage());
		}
	}
	public Connection makeConnection(String DB_URL){
		//String DB_URL = "jdbc:mysql://localhost/QuizDB";
		Connection con = null;
		try{
			con = DriverManager.getConnection(DB_URL,"root","Kimruben1");
		}
		catch (SQLException sqlEx){
			System.out.println("No connection " + sqlEx.getMessage());
		}
		return con;
	}
}
