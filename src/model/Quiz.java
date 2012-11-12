package model;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xpath.internal.operations.Equals;

import utils.gregorian.Datum;
import model.enumKlassen.Leraar;
import model.enumKlassen.OpdrachtCategorie;
import model.enumKlassen.QuizStatussen;

/**
 * Deze klasse wordt gebruikt voor het aanmaken van een Quiz
 * @author Noella
 *
 */
public class Quiz  implements Serializable{
	//
	// Attributen
	//
	private String onderwerp;
	private int leerJaar;
	private boolean isTest;
	private boolean isUniekeDeelname;
	private QuizStatussen quizStatus;
	private Leraar leraar;
	private List<QuizOpdracht> quizOpdrachten = new ArrayList();
	private String auteur;
	private int score=0;
	private Opdracht opdracht;
	//
	// Constructor
	//
	/**
	 * 
	 * @param onderwerp		onderwerp van de quiz
	 */
	public Quiz (String onderwerp){
		this.onderwerp = onderwerp;
		quizOpdrachten = new ArrayList<QuizOpdracht>();
	}
	/**
	 * @param onderwerp				onderwerp van de quiz
	 * @param leerJaar				formuleren voor welk leeljaar de quiz bestemd is (min 1 max 6)
	 * @param isUniekeDeelname		true indien quiz meerdere malen mag opgelost worden, false indien quiz 1 maal mag opgeloste worden
	 * @param isTest				true indien geregistreerd als test (leerling kan maar 1 keer deelnemen)
	 * @param opdracht				opdracht is een object van het type Opdracht
	 * @param quizStatus			de status waarin de quiz zich bevindt
	 */
	public Quiz(String onderwerp, int leerJaar, boolean isUniekeDeelname,boolean isTest, 
			    QuizStatussen quizStatus){
		this.onderwerp = onderwerp;
		this.leerJaar = controleLeerjaar(leerJaar);
		this.isTest = isTest;
		this.isUniekeDeelname= isUniekeDeelname;
		if(isTest){ // indien quiz in testfase 
			isUniekeDeelname = false;
		}
		this.quizStatus = quizStatus;
	}
	
	//
	// Getters
	//
	public String getOnderwerp() {
		return onderwerp;
	}

	public int getLeerJaar() {
		return leerJaar;
	}

	public boolean isTest() {
		return isTest;
	}

	public boolean isUniekeDeelname() {
		return isUniekeDeelname;
	}

	public QuizStatussen getQuizStatus() {
		return quizStatus;
	}
	public Leraar getLeraar() {
		return leraar;
	}
	public List<QuizOpdracht> getQuizOpdracht() {
		return quizOpdrachten;
	}
	public String getAuteur() {
		return auteur;
	}
	//
	// Setters
	//
	public void setOnderwerp(String onderwerp) {
		this.onderwerp = onderwerp;
	}
	public void setLeerJaar(int leerJaar) {
		this.leerJaar = controleLeerjaar(leerJaar);
	}
	public void setTest(boolean isTest) {
		this.isTest = isTest;
	}
	public void setUniekeDeelname(boolean isUniekeDeelname) {
		this.isUniekeDeelname = isUniekeDeelname;
	}
	public void setQuizStatus(QuizStatussen quizStatus) {
		this.quizStatus = quizStatus;
	}
	public void setLeraar(Leraar leraar) {
		this.leraar = leraar;
	}
	public void setQuizOpdracht(List<QuizOpdracht> quizOpdracht) {
		this.quizOpdrachten = quizOpdracht;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	//
	// Methodes
	//
	/**
	 * methode om het leerJaar op een geldige waarde te controleren <br>
	 * waarde min 1 en max 6
	 * 
	 * @param leerJaar		het te controleren leerjaar
	 * 
	 * @return leerjaar		geeft de waarde van het leerjaar terug indien correct
	 * 
	 * @author Noella
	 */
	public int controleLeerjaar(int leerJaar) {
		if (leerJaar > 0 && leerJaar < 7) {
			return leerJaar;
		}
		throw new IllegalArgumentException("leerJaar moet tussen 01 en 6 liggen");

	} 
	/**
	 * voeg een quizOpdracht toe
	 * 
	 * @param quizOpdracht		quizOpdracht is een object van het type QuizOpdracht
	 * 
	 * @author Noella
	 */
	protected void voegQuizOpdrachtToe(QuizOpdracht quizOpdracht){
		quizOpdrachten.add(quizOpdracht);
	}

	/**
	 * verwijderen van een quizOpdracht
	 * 
	 * @param quizOpdracht		quizOpdracht is een object van het type QuizOpdracht
	 * 
	 * @author Noella
	 */
	protected void verwijderQuizOpdracht(QuizOpdracht quizOpdracht){
		quizOpdrachten.remove(quizOpdracht);
	}
	/**
	 * maakt een lijst van alle bestaande opdrachten
	 * 
	 * @return lijst met bestaande opdrachten
	 */
	public ArrayList <Opdracht> getOpdrachten(){
		ArrayList <Opdracht> opdrachten = new ArrayList <Opdracht>();
		for (QuizOpdracht quizOpdracht : quizOpdrachten){
			opdrachten.add(quizOpdracht.getOpdracht());
		}
		return opdrachten;
	}
	/**
	 * zoekt een bepaalde opdracht in een lijst
	 * 
	 * @param volgnr om een opdracht op die plaats in de lijst te halen
	 * @return een bepaalde opdracht
	 * 
	 * @author Noella
	 */
	public QuizOpdracht getOpdracht(int volgnr){
		return quizOpdrachten.get(volgnr-1);
	}
	/**
	 * wijzigen van een quiz/test indien quizStatus = 'INCONSTRUCTIE'
	 * 
	 * @param quizStatus	status waarin de quiz zich bevindt
	 * @param quiz			de te wijzigen quiz
	 * 
	 * @author Noella
	 * 
	 */
	public void wijzigQuiz(QuizStatussen quizStatus,Quiz quiz){
		if(quizStatus.equals(quizStatus.INCONSTRUCTIE)){
			this.setOnderwerp(quiz.getOnderwerp());
			
		}
	}
	/**
	 * verwijderen van een quiz
	 * 
	 * @param quizStatus	quizStatus is een object van het type QuizStatussen
	 * @param quiz			quiz is een object van het type Quiz
	 * 
	 * @author Noella
	 */
	public void verwijderQuiz(QuizStatussen quizStatus,Quiz quiz){
		if(quizStatus.equals(quizStatus.INCONSTRUCTIE)){
			System.out.println("wijzig quiz");
			
		}
	}
	/*
	 * override van de String-methode
	 */
	@Override
	public String toString(){
		String aangemaakteQuiz = "";
		aangemaakteQuiz += "Quiz : " + onderwerp + " voor het " + leerJaar + " e leerjaar ";
		aangemaakteQuiz += isUniekeDeelname ? "met unieke deelname" : "zonder unieke deelname";
		
		return aangemaakteQuiz;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((auteur == null) ? 0 : auteur.hashCode());
		result = prime * result + (isTest ? 1231 : 1237);
		result = prime * result + (isUniekeDeelname ? 1231 : 1237);
		result = prime * result + leerJaar;
		result = prime * result + ((leraar == null) ? 0 : leraar.hashCode());
		result = prime * result
				+ ((onderwerp == null) ? 0 : onderwerp.hashCode());
		result = prime * result
				+ ((quizStatus == null) ? 0 : quizStatus.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Quiz other = (Quiz) obj;
		if (auteur == null) {
			if (other.auteur != null) {
				return false;
			}
		} else if (!auteur.equals(other.auteur)) {
			return false;
		}
		if (isTest != other.isTest) {
			return false;
		}
		if (isUniekeDeelname != other.isUniekeDeelname) {
			return false;
		}
		if (leerJaar != other.leerJaar) {
			return false;
		}
		if (leraar != other.leraar) {
			return false;
		}
		if (onderwerp == null) {
			if (other.onderwerp != null) {
				return false;
			}
		} else if (!onderwerp.equals(other.onderwerp)) {
			return false;
		}
		if (quizStatus != other.quizStatus) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		try {
			List<String> hints = new ArrayList();
			hints.add("Eerste letter is 'B'");
			hints.add("Laatste letter is 'L'");
			
			Time time = new Time(120000); // in milliseconden
			
			OpdrachtCategorie opdrachtCategorie = OpdrachtCategorie.algemeneKennis;
			Leraar leraar = Leraar.MYRIAM;
			QuizStatussen quizStatus = QuizStatussen.INCONSTRUCTIE;
			//Opdracht opdracht = new Opdracht("Wat is de hoofdstad van ons land?",
			//		"Brussel",hints,2,time,opdrachtCategorie,leraar);
			
			Quiz quiz = new Quiz("Hoofdsteden", 2, true,true,quizStatus);
			Opdracht opdracht1 = new Opdracht("Wat is de hoofdstad van Franrijk?","Parijs");
			Opdracht opdracht2 = new Opdracht("Wat is de hoofdstad van Spanje?","Madrid");
			
			QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht1, 2);
			QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht2, 2);
			System.out.println(quiz.getOpdrachten());
			System.out.println(quiz.toString());
			quiz.setOnderwerp("Belgie");
			quiz.wijzigQuiz(quizStatus, quiz);
			System.out.println(quiz.toString());
			/*
			Quiz quiz = new Quiz("Hoofdsteden Europa");
			
			Opdracht opdracht1 = new Opdracht("Wat is de hoofdstad van Franrijk?","Parijs");
			Opdracht opdracht2 = new Opdracht("Wat is de hoofdstad van Spanje?","Madrid");
			
			QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht1, 2);
			QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht2, 2);
			System.out.println(quiz.getOpdrachten());
			QuizOpdracht quizOpdracht = quiz.getOpdracht(1);
			quizOpdracht.ontKoppelOpdrachtVanQuiz();
			System.out.println(quiz.getOpdrachten());
*/
			
			
		} 
		catch (IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
		} 
		catch (RuntimeException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
