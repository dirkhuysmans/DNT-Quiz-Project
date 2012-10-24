package model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import utils.gregorian.Datum;
import model.enumKlassen.Leraar;
import model.enumKlassen.OpdrachtCategorie;
import model.enumKlassen.QuizStatussen;

/**
 * Deze klasse wordt gebruikt voor het aanmaken van een Quiz
 * @author Noella
 *
 */
public class Quiz {
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
	//
	// Constructor
	//
	public Quiz(String onderwerp, int leerJaar, boolean isUniekeDeelname,boolean isTest){
		this.onderwerp = onderwerp;
		this.leerJaar = controleLeerjaar(leerJaar);
		this.isUniekeDeelname= isUniekeDeelname;
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
		QuizOpdracht qo = new QuizOpdracht(quiz,opdracht,score);
		quizOpdracht.remove(qo);
	}
	@Override
	public String toString(){
		String aangemaakteQuiz = "";
		aangemaakteQuiz += "Quiz : " + onderwerp + " voor het " + leerJaar + " e leerjaar ";
		aangemaakteQuiz += isUniekeDeelname ? "met unieke deelname" : "zonder unieke deelname";
		
		return aangemaakteQuiz;
	}
	
	
	
	
	
	public static void main(String[] args) throws Exception {
		try {
			Quiz quiz = new Quiz("rekenen",1,true,false);
			Quiz quiz2 = new Quiz("nederlands",5,true,false);
			QuizCatalogus catalogus = new QuizCatalogus();
			catalogus.voegQuizToe(quiz);
			catalogus.voegQuizToe(quiz2);
			System.out.println(catalogus.toString());
			
			
			List<String> hints = new ArrayList();
			hints.add("dag");
			Time time = new Time(120*1000);
			Datum datum = new Datum();
			Opdracht opdracht1 = new Opdracht("welke dag", "maandag",hints, 2, 
				time, OpdrachtCategorie.algemeneKennis, Leraar.Carla, datum);
			
			Quiz quiz3 = new Quiz("rekenen",1,true,false);
			Quiz quiz4 = new Quiz("rekenen",6,false,false);
			
			voegQuizOpdrachtToe(quiz,opdracht1,4);
			voegQuizOpdrachtToe(quiz2,opdracht1,6);
			voegQuizOpdrachtToe(quiz3,opdracht1,8);
			voegQuizOpdrachtToe(quiz4,opdracht1,1);
			
			for(QuizOpdracht lijst : quizOpdracht){
				System.out.println(lijst.getQuiz().toString() + "met als max score " + lijst.getMaxScore());
			}
			
			verwijderQuizOpdracht(quiz3,opdracht1,8);
			
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
