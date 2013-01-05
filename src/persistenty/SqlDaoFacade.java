package persistenty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.text.StyleContext.SmallAttributeSet;

import statePattern.InConstructieStatus;
import statePattern.QuizStatus;

import model.EenvoudigeOpdracht;
import model.Meerkeuze;
import model.Opdracht;
import model.Opsomming;
import model.Quiz;
import model.Reproductie;

public class SqlDaoFacade implements DaoFacade {
	private static final String insertQuiz = "insert into Quiz(Onderwerp, VanLeerjaar, TotLeerjaar, IsTest,IsUniekeDeelname,QuizSatus) values(?,?,?,?,?,?)";
	private static final String leesAlleQuizzen = "select * from quiz";
	private static final String leesQuizzenTotLeerjaar = "select * from quiz where vanLeerjaar <= ?";

	private static final String insertOpdracht = "insert into opdracht(opdrachtId, Vraag, JuisteAntwoord, maxAantalPogingen, antwoorHints, "
			+ "MaxAntwoordTijd, Categorie, auteur, type) values (?,?,?,?,?,?,?,?,?)";
	private static final String insertEenvoudigeOpdracht = "insert into eenvoudigeopdracht(eenvoudigeOpdrachtId, eevoudigeOpdrachtVolgnr) values (?,?)";
	private static final String insertOpsomming = "insert into opsomming(opsommingVolgnr, inJuisteVolgorde) values (?,?)";
	private static final String insertMeerkeuze = "insert into meerkeuze(meerkeuzeVolgnr, keuzen) values (?,?)";
	private static final String insertReproductie = "insert into reproductie(reproductieVolgnr, trefwoorden, minAantalJuisteTrefwoorden) values (?,?,?)";

	private static final String leesAlleOpdrachten = "select * from opdracht";
	private static final String geefOpdrachtID = "select max(opdrachtId) from opdracht";
	private static final String selecteerEenvoudigeOpdrachten = "select type, vraag, juisteAntwoord, maxAantalPogingen, "
			+ "antwoorHints, maxAntwoordTijd from opdracht inner join eenvoudigeOpdracht on (opdrachtId = eenvoudigeOpdrachtVolgnr)";
	private static final String selecteerMeerkeuzeOpdrachten = "select type, vraag, JuisteAntwoord, keuzen, "
			+ "antwoorHints, maxAantalPogingen, MaxantwoordTijd from opdracht inner join meerkeuze on (opdrachtId = meerkeuzeVolgnr)";
	private static final String selecteerOpsommingsOpdrachten = "select type, vraag, JuisteAntwoord, inJuisteVolgorde, "
			+ "antwoorHints, maxAantalPogingen, MaxantwoordTijd from opdracht inner join opsomming on (opdrachtId = opsommingVolgnr)";
	private static final String selecteerReproductieOpdrachten = "select type, vraag, JuisteAntwoord, minAantalJuisteTrefwoorden, "
			+ "antwoorHints, maxAantalPogingen, MaxantwoordTijd from opdracht inner join reproductie on (opdrachtId = reproductieVolgnr)";
	private static final String selecteerOpdrachtenPerCategorie = "select type, vraag, JuisteAntwoord "
			+ "antwoorHints, maxAantalPogingen, MaxantwoordTijd, categorie from opdracht where categorie = ?";
	static final String DB_URL = "jdbc:mysql://localhost/QuizDB";

	int opdrachtID = 0;
	static int eenvoudigeOpdrachtID = 100;
	static int opsommingID = 100;
	static int meerkeuzeID = 100;
	static int reproductieID = 100;

	private Connection maakVerbinding() {
		try {
			Connection con = DriverManager.getConnection(DB_URL, "root",
					"Scoren92");
			// "Quizdnt123");
			return con;
		} catch (SQLException sqlEx) {
			System.out.println(sqlEx.getMessage());
		}
		return null;
	}

	/**
	 * 
	 * Komende gedeelte dient om quizzen aan te maken en terug op te roepen uit
	 * de database
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

	@Override
	public List<Quiz> selectAlleQuizzen() {
		Connection con = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			List<Quiz> quizzen = new ArrayList<Quiz>();
			con = maakVerbinding();
			statement = con.createStatement();
			rs = statement.executeQuery(leesAlleQuizzen);
			while (rs.next()) {
				quizzen.add(zetDBQuizOmNaarObject(rs));
			}
			return quizzen;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			close(rs, statement, con);
		}
	}

	@Override
	public List<Quiz> selectQuizTotBepaaldLeerjaar(int hoogsteLeerjaar) {
		Connection con = null;
		PreparedStatement prepStatement = null;
		ResultSet rs = null;
		try {
			List<Quiz> quizzen = new ArrayList<Quiz>();
			con = maakVerbinding();
			prepStatement = con.prepareStatement(leesQuizzenTotLeerjaar);
			prepStatement.setInt(1, hoogsteLeerjaar);
			rs = prepStatement.executeQuery();
			while (rs.next()) {
				quizzen.add(zetDBQuizOmNaarObject(rs));
			}
			return quizzen;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			close(rs, prepStatement, con);
		}
	}

	private Quiz zetDBQuizOmNaarObject(ResultSet rs) throws SQLException {
		return new Quiz(rs.getString("Onderwerp"), rs.getInt("VanLeerjaar"),
				rs.getInt("TotLeerjaar"), rs.getBoolean("IsTest"),
				rs.getBoolean("IsUniekeDeelname"), rs.getString("QuizSatus"));
	}

	/**
	 * 
	 * Komende gedeelte dient om opdrachten aan te maken en terug op te roepen
	 * uit de database
	 * 
	 */
	@Override
	public void createOpdracht(Opdracht opdracht) {
		Connection con = maakVerbinding();
		PreparedStatement addOpdracht = null;
		String type = opdracht.getClass().getSimpleName();
		try {
			opdrachtID = getopdrachtID();
			opdrachtID++;
			addOpdracht = con.prepareStatement(insertOpdracht);
			addOpdracht.setInt(1, opdrachtID);
			addOpdracht.setString(2, opdracht.getVraag());
			addOpdracht.setString(3, opdracht.getAntwoord());
			addOpdracht.setInt(4, opdracht.getMaxAantalPogingen());
			addOpdracht.setString(5, opdracht.getHints());
			addOpdracht.setTime(6, opdracht.getMaxAntwoordTijd());
			addOpdracht.setString(7, opdracht.getCategorie().toString());
			addOpdracht.setString(8, opdracht.getAuteur().toString());
			addOpdracht.setString(9, type);
			addOpdracht.executeUpdate();
			createOpdrachtLink(opdracht);
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

	private int getopdrachtID() {
		Connection con = maakVerbinding();
		Statement statement = null;
		ResultSet rs = null;
		try {
			int opdrachtID = 0;
			statement = con.createStatement();
			rs = statement.executeQuery(geefOpdrachtID);
			rs.next();
			opdrachtID = rs.getInt(1);
			return opdrachtID;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	private void createOpdrachtLink(Opdracht opdracht) {
		String type = opdracht.getClass().getSimpleName();
		Connection con = maakVerbinding();
		PreparedStatement createLink = null;
		if (type.equals("EenvoudigeOpdracht")) {
			try {
				createLink = con.prepareStatement(insertEenvoudigeOpdracht);
				createLink.setInt(1, eenvoudigeOpdrachtID);
				createLink.setInt(2, opdrachtID);
				eenvoudigeOpdrachtID++;
				createLink.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (type.equals("Opsomming")) {
			try {
				createLink = con.prepareStatement(insertOpsomming);
//				createLink.setInt(1, opsommingID);
				createLink.setInt(1, opdrachtID);
				createLink.setBoolean(2,
						((Opsomming) opdracht).getInJuisteVolgorde());
				opsommingID++;
				createLink.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (type.equals("Meerkeuze")) {
			try {
				createLink = con.prepareStatement(insertMeerkeuze);
				// createLink.setInt(1, meerkeuzeID);
				createLink.setInt(1, opdrachtID);
				createLink.setString(2, ((Meerkeuze) opdracht).getMeerkeuze());
				meerkeuzeID++;
				createLink.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (type.equals("Reproductie")) {
			try {
				createLink = con.prepareStatement(insertReproductie);
//				createLink.setInt(1, reproductieID);
				createLink.setInt(1, opdrachtID);
				createLink.setString(2, ((Reproductie) opdracht).getAntwoord());
				createLink.setInt(3, ((Reproductie) opdracht)
						.getMinAantalJuisteTrefwoorden());
				reproductieID++;
				createLink.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("fucking hell waarom lukt het niet " + type);
		}
	}

	@Override
	public List<Opdracht> selectAlleOpdrachten() {
		Connection con = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			List<Opdracht> opdrachten = new ArrayList<Opdracht>();
			con = maakVerbinding();
			statement = con.createStatement();
			rs = statement.executeQuery(leesAlleOpdrachten);
			while (rs.next()) {
				opdrachten.add(zetDBOpdrachtOmNaarObject(rs));
				if (rs.getString("type").equalsIgnoreCase("opsomming")) {
					System.out.println(rs.getString("vraag") + " "
							+ rs.getString("juisteantwoord") + " "
							+ rs.getBoolean(3));
				} else {
					System.out.println(rs.getString("vraag") + " "
							+ rs.getString("juisteantwoord"));
				}
			}
			return opdrachten;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			close(rs, statement, con);
		}
	}

	@Override
	public List<Opdracht> selectOpdrachtenPerType(String type) throws Exception {
		Connection con = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			List<Opdracht> opdrachten = new ArrayList<Opdracht>();
			con = maakVerbinding();
			statement = con.createStatement();
			if (type.equals("Eenvoudige Opdracht")) {
				rs = statement.executeQuery(selecteerEenvoudigeOpdrachten);
			} else if (type.equals("Meerkeuze")) {
				rs = statement.executeQuery(selecteerMeerkeuzeOpdrachten);
			} else if (type.equals("Opsomming")) {
				rs = statement.executeQuery(selecteerOpsommingsOpdrachten);
			} else if (type.equals("Reproductie")) {
				rs = statement.executeQuery(selecteerReproductieOpdrachten);
			} else {
				throw new Exception(
						"niet het juiste type meegekregen, kan niks teruggeven");
			}
			while (rs.next()) {
				opdrachten.add(zetDBOpdrachtOmNaarObject(rs));
			}
			return opdrachten;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			close(rs, statement, con);
		}
	}

	@Override
	public List<Opdracht> selectOpdrachtenPerCategorie(String geselecteerd)
			throws Exception {
		Connection con = null;
		PreparedStatement prepStatement = null;
		ResultSet rs = null;
		try {
			List<Opdracht> opdrachten = new ArrayList<Opdracht>();
			con = maakVerbinding();
			prepStatement = con
					.prepareStatement(selecteerOpdrachtenPerCategorie);
			geselecteerd.toLowerCase();
			prepStatement.setString(1, geselecteerd);
			rs = prepStatement.executeQuery();
			while (rs.next()) {
				opdrachten.add(zetDBOpdrachtOmNaarObject(rs));
			}
			return opdrachten;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			close(rs, prepStatement, con);
		}
	}

	private Opdracht zetDBOpdrachtOmNaarObject(ResultSet rs)
			throws SQLException {
		if (rs.getString("type").equalsIgnoreCase("eenvoudigeopdracht")) {
			return new EenvoudigeOpdracht(rs.getString("Vraag"),
					rs.getString(2), rs.getString("antwoorHints"),
					rs.getInt("MaxAantalPogingen"),
					rs.getTime("MaxAntwoordTijd"), null, null);
		} else if (rs.getString("type").equalsIgnoreCase("Meerkeuze")) {
			return new Meerkeuze(rs.getString("Vraag"), rs.getString(2),
					rs.getString(3), rs.getString("antwoorHints"),
					rs.getInt("MaxAantalPogingen"),
					rs.getTime("MaxAntwoordTijd"), null, null);
		} else if (rs.getString("type").equalsIgnoreCase("Opsomming")) {
			return new Opsomming(rs.getString("Vraag"), rs.getString(2),
					rs.getBoolean(3), rs.getString("antwoorHints"),
					rs.getInt("MaxAantalPogingen"),
					rs.getTime("MaxAntwoordTijd"), null, null);
		} else if (rs.getString("type").equalsIgnoreCase("Reproductie")) {
			return new Reproductie(rs.getString("Vraag"), rs.getString(2),
					rs.getInt(4), rs.getString("antwoorHints"),
					rs.getInt("MaxAantalPogingen"),
					rs.getTime("MaxAntwoordTijd"), null, null);
		}
		return null;
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

	public void close(ResultSet rs, Statement statement, Connection con) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				System.out.println("kan resultset niet sluiten "
						+ ex.getMessage());
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
				System.out.println("kan resultset niet sluiten "
						+ ex.getMessage());
			}
			close(prepStatement, con);
		}
	}
}
