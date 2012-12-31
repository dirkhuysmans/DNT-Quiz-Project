package persistenty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.text.StyleContext.SmallAttributeSet;

import statePattern.InConstructieStatus;
import statePattern.QuizStatus;

import model.Opdracht;
import model.Quiz;

public class SqlDaoFacade implements DaoFacade {
	private static final String insertQuiz = "insert into Quiz(Onderwerp, VanLeerjaar, TotLeerjaar, IsTest,IsUniekeDeelname,QuizSatus) values(?,?,?,?,?,?)";
	private static final String leesAlleQuizzen = "select * from quiz";
	private static final String leesQuizzenTotLeerjaar = "select * from quiz where vanLeerjaar <= ?";
	private static final String insertOpdracht = "insert into opdracht(Vraag, JuisteAntwoord, maxAantalPogingen, antwoorHints, MaxAntwoordTijd) values (?,?,?,?,?)";
    private static final String leesAlleOpdrachten = "select * from opdracht";
    private static final String selecteerEenvoudigeOpdrachten = "select vraag, juisteAntwood, maxAantalPogingen, antwoorHints, maxAntwoordTijd from opdracht, eenvoudigeOpdracht " +
    		"where opdrachtId = eenvoudigeOpdrachtVolgnr";
    private static final String selecteerMeerkeuzeOpdrachten = "select vraag, juisteAntwood, maxAantalPogingen, antwoorHints, maxAntwoordTijd from opdracht, meerkeuze " +
    		"where opdrachtId = eenvoudigeOpdrachtVolgnr";
    private static final String selecteerOpsommingsOpdrachten = "select vraag, juisteAntwood, maxAantalPogingen, antwoorHints, maxAntwoordTijd from opdracht, opsomming " +
    		"where opdrachtId = eenvoudigeOpdrachtVolgnr";
    private static final String selecteerReproductieOpdrachten = "select vraag, juisteAntwood, maxAantalPogingen, antwoorHints, maxAntwoordTijd from opdracht, reproductie " +
    		"where opdrachtId = eenvoudigeOpdrachtVolgnr";
	static final String DB_URL = "jdbc:mysql://localhost/QuizDB";

	private Connection maakVerbinding() {
		try {
			Connection con = DriverManager.getConnection(DB_URL, "root",
					//"Scoren92");
					"Quizdnt123");
			return con;
		} catch (SQLException sqlEx) {
			System.out.println(sqlEx.getMessage());
		}
		return null;
	}

	/**
	 * 
	 * Komende gedeelte dient om quizzen aan te maken en terug op te roepen uit de database
	 * 
	 */
	@Override
	public void createQuiz(Quiz quiz) {
		Connection con = maakVerbinding();
		PreparedStatement addQuiz = null;
		try {
			QuizStatus quizStatus = quiz.getQuizStatus();
			String status = quizStatus.toString();
			addQuiz = con.prepareStatement(insertQuiz);
			addQuiz.setString(1, quiz.getOnderwerp());
			addQuiz.setInt(2, quiz.getMinLeerjaar());
			addQuiz.setInt(3, quiz.getMaxLeerjaar());
			addQuiz.setBoolean(4, quiz.isTest());
			addQuiz.setBoolean(5, quiz.isUniekeDeelname());
			addQuiz.setString(6, status);
			addQuiz.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (addQuiz != null) {
					addQuiz.close();
				}
			} catch (SQLException ex) {
			}
		}
	}

	public List<Quiz> selectAlleQuizzen(){
		Connection con =null;
		Statement statement = null;
		ResultSet rs = null;
		try{
			List<Quiz> quizzen = new ArrayList<Quiz>();
			con = maakVerbinding();
			statement = con.createStatement();
			rs = statement.executeQuery(leesAlleQuizzen);
			while(rs.next()){
				quizzen.add(zetDBQuizOmNaarObject(rs));
			}
			return quizzen;
		}catch(SQLException e){
			System.out.println(e.getMessage());
			return null;
		}finally{
			close(rs, statement, con);
		}
	}
	
	public List<Quiz> selectQuizTotBepaaldLeerjaar(int hoogsteLeerjaar){
		Connection con =null;
		PreparedStatement prepStatement = null;
		ResultSet rs = null;
		try{
			List<Quiz> quizzen = new ArrayList<Quiz>();
			con = maakVerbinding();
			prepStatement = con.prepareStatement(leesQuizzenTotLeerjaar);
			prepStatement.setInt(1, hoogsteLeerjaar);
			rs = prepStatement.executeQuery();
			while(rs.next()){
				quizzen.add(zetDBQuizOmNaarObject(rs));
			}
			return quizzen;
		}catch(SQLException e){
			System.out.println(e.getMessage());
			return null;
		}finally{
			close(rs, prepStatement, con);
		}
	}
			
	private Quiz zetDBQuizOmNaarObject(ResultSet rs) throws SQLException{
		return new Quiz(rs.getString("Onderwerp"), rs.getInt("VanLeerjaar"), rs.getInt("TotLeerjaar"), rs.getBoolean("IsTest"),
				rs.getBoolean("IsUniekeDeelname"), rs.getString("QuizSatus"));
	}

	/**
	 * 
	 * Komende gedeelte dient om opdrachten aan te maken en terug op te roepen uit de database
	 * 
	 */	
	@Override
	public void createOpdracht(Opdracht opdracht) {
		Connection con = maakVerbinding();
		PreparedStatement addOpdracht = null;
		try {			
			addOpdracht = con.prepareStatement(insertOpdracht);
			addOpdracht.setString(1, opdracht.getVraag());
			addOpdracht.setString(2, opdracht.getAntwoord());
			addOpdracht.setInt(3, opdracht.getMaxAantalPogingen());
			addOpdracht.setString(4, opdracht.getHints());
			addOpdracht.setTime(5, opdracht.getMaxAntwoordTijd());
			addOpdracht.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (addOpdracht != null) {
					addOpdracht.close();
				}
			} catch (SQLException ex) {
			}
		}		
	}
	
	public List<Opdracht> selectAlleOpdrachten(){
		Connection con =null;
		Statement statement = null;
		ResultSet rs = null;
		try{
			List<Opdracht> opdrachten = new ArrayList<Opdracht>();
			con = maakVerbinding();
			statement = con.createStatement();
			rs = statement.executeQuery(leesAlleOpdrachten);
			while(rs.next()){
				opdrachten.add(zetDBOpdrachtOmNaarObject(rs));
			}
			return opdrachten;
		}catch(SQLException e){
			System.out.println(e.getMessage());
			return null;
		}finally{
			close(rs, statement, con);
		}
	}
	
	public List<Opdracht> selectOpdrachtenPerType(String type) throws Exception{
		Connection con =null;
		Statement statement = null;
		ResultSet rs = null;
		try{
			List<Opdracht> opdrachten = new ArrayList<Opdracht>();
			con = maakVerbinding();
			statement = con.createStatement();
			if (type.equals("Eenvoudige Opdracht")){
				rs = statement.executeQuery(selecteerEenvoudigeOpdrachten);
			}else if(type.equals("Meerkeuze")){
				rs = statement.executeQuery(selecteerMeerkeuzeOpdrachten);
			}else if(type.equals("Opsomming")){
				rs = statement.executeQuery(selecteerOpsommingsOpdrachten);
			}else if(type.equals("Reproductie")){
				rs = statement.executeQuery(selecteerReproductieOpdrachten);
			}else{
				throw new Exception ("niet het juiste type meegekregen, kan niks teruggeven");
			}
			while(rs.next()){
				opdrachten.add(zetDBOpdrachtOmNaarObject(rs));
			}
			return opdrachten;
		}catch(SQLException e){
			System.out.println(e.getMessage());
			return null;
		}finally{
			close(rs, statement, con);
		}
	}
	
	private Opdracht zetDBOpdrachtOmNaarObject(ResultSet rs) throws SQLException{
		return new Opdracht(rs.getString("Vraag"), rs.getString("JuisteAntwoord"), rs.getInt("MaxAantalPogingen"),
				rs.getString("antwoorHints"), rs.getTime("MaxAntwoordTijd"));
	}
	
	/**
	 * 
	 * Onderstaand gedeelte dient om openstaande connecties te sluiten.
	 * 
	 */
	public void close(Statement statement, Connection con) {
		if (statement != null) {
			try {
				con.close();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	public void close(ResultSet rs, Statement statement,
			Connection con) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				System.out.println("kan resultset niet sluiten " + ex.getMessage());
			}
			close(statement, con);
		}
	}
	
	public void close(PreparedStatement prepStatement, Connection con) {
		if (prepStatement != null) {
			try {
				con.close();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	public void close(ResultSet rs, PreparedStatement prepStatement,
			Connection con) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				System.out.println("kan resultset niet sluiten " + ex.getMessage());
			}
			close(prepStatement, con);
		}
	}
}
