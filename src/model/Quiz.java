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
	private static List<QuizOpdracht> quizOpdracht = new ArrayList();
	private String auteur;
	private int score=0;
	//
	// Constructor
	//
	public Quiz(String onderwerp, int leerJaar, boolean isUniekeDeelname,boolean isTest){
		this.onderwerp = onderwerp;
		this.leerJaar = controleLeerjaar(leerJaar);
		this.isUniekeDeelname= isUniekeDeelname;
		this.isTest = isTest;
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
		return quizOpdracht;
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
		this.leerJaar = leerJaar;
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
		this.quizOpdracht = quizOpdracht;
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
	 * @param leerJaar
	 * @return leerjaar
	 */
	private int controleLeerjaar(int leerJaar) {
		if (leerJaar > 0 && leerJaar < 7) {
			return leerJaar;
		}
		throw new IllegalArgumentException("leerJaar moet tussen 01 en 6 liggen");

	} 
	/**
	 * voeg een quizOpdracht toe
	 * @param quiz
	 * @param opdracht
	 * @param score
	 */
	public static void voegQuizOpdrachtToe(Quiz quiz, Opdracht opdracht, int score){
		QuizOpdracht qo = new QuizOpdracht(quiz,opdracht,score);
		quizOpdracht.add(qo);
	}
	/**
	 * verwijder een quizOpdracht
	 * @param quiz
	 * @param opdracht
	 * @param score
	 */
	public static void verwijderQuizOpdracht(Quiz quiz, Opdracht opdracht, int score){
		QuizOpdracht qo = zoekQuizOpdracht(quiz,opdracht,score);
		//qo.equals(qo);
		if(qo != null){
			quizOpdracht.remove(qo);
		}
	}
	
	private static QuizOpdracht zoekQuizOpdracht(Quiz quiz, Opdracht opdracht, int score)
	{
		for(QuizOpdracht lijst : quizOpdracht){
			if(lijst.getMaxScore() == score &&
			   lijst.getOpdracht().equals(opdracht) &&
			   lijst.getQuiz().equals(quiz)){
				return lijst;
			}
		}
		throw new IllegalArgumentException("quizOpdracht kan niet verwijderd worden, geen gegevens gevonden");
	}
	
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
			Quiz quiz = new Quiz("rekenen",1,true,false);
			Quiz quiz2 = new Quiz("nederlands",5,true,false);
			QuizCatalogus catalogus = new QuizCatalogus();
			catalogus.voegQuizToe(quiz);
			catalogus.voegQuizToe(quiz2);
			catalogus.wegschrijvenNaarFile();
			catalogus.lezenFile();
			System.out.println(catalogus.toString());
			
			
			List<String> hints = new ArrayList();
			hints.add("dag");
			Time time = new Time(120*1000);
			Datum datum = new Datum();
			Opdracht opdracht1 = new Opdracht("welke dag", "maandag",hints, 2, 
				time, OpdrachtCategorie.algemeneKennis, Leraar.Carla);
			
			Quiz quiz3 = new Quiz("rekenen",1,true,false);
			Quiz quiz4 = new Quiz("rekenen",6,false,false);
			catalogus.voegQuizToe(quiz3);
			catalogus.voegQuizToe(quiz4);
			voegQuizOpdrachtToe(quiz,opdracht1,4);
			voegQuizOpdrachtToe(quiz2,opdracht1,6);
			voegQuizOpdrachtToe(quiz3,opdracht1,8);
			voegQuizOpdrachtToe(quiz4,opdracht1,1);
			catalogus.wegschrijvenNaarFile();
			catalogus.lezenFile();
			System.out.println(catalogus);
			
			for(QuizOpdracht lijst : quizOpdracht){
				System.out.println(lijst.getQuiz().toString() + "met als max score " + lijst.getMaxScore());
			}
			
			verwijderQuizOpdracht(quiz3,opdracht1,9);
			System.out.println("");
			for(QuizOpdracht lijst : quizOpdracht){
				System.out.println(lijst.getQuiz().toString() + "met als max score " + lijst.getMaxScore());
			}
		} 
		catch (IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
		} 
		catch (RuntimeException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
