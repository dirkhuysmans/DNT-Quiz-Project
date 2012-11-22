package controller;

import java.sql.Time;

import model.FileContainer;
import model.Opdracht;
import model.Quiz;
import model.QuizCatalogus;
import model.QuizOpdracht;
import model.enumKlassen.Leraar;
import model.enumKlassen.QuizStatussen;

/**
 * Deze klasse maakt een quiz aan en schrijft de aangemaakte quiz <br>
 * in een tekst-file in het bestand "bestanden\quiz.txt"
 * 
 * @author Noella
 *
 */
public class QuizController extends FileContainer{

	public static void main(String[] args) throws Exception {
		try {
			
			QuizCatalogus quizCatalogus = new QuizCatalogus();
			
			Time time = new Time(120000); // in milliseconden
			
			Leraar auteur1 = Leraar.MYRIAM;
			
			QuizStatussen quizStatus = QuizStatussen.AFGEWERKT;
			
			Quiz quiz = new Quiz("Hoofdsteden/Provincies", 2, true,true,quizStatus);
			
			quizCatalogus.voegQuizToe(quiz);
			
			Opdracht opdracht1 = new Opdracht("Wat is de hoofdstad van Franrijk?","Parijs");
			Opdracht opdracht2 = new Opdracht("Wat is de hoofdstad van Spanje?","Madrid");
			Opdracht opdracht3 = new Opdracht("In welke provincie ligt de hoofdstad van België?","Brabant");
			Opdracht opdracht4 = new Opdracht("In welke provincie ligt Genk?","Limburg");
			Opdracht opdracht5 = new Opdracht("In welke provincie ligt Antwerpen","Antwerpen");
			
			QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht1, 2);
			QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht2, 2);
			QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht3, 5);
			QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht4, 3);
			QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht5, 4);
			
			System.out.println(quiz.getOpdrachten());
			
			System.out.println(quiz.toString());
			
			String fileName = "bestanden\\quiz.txt";
			schrijvenQuiz(fileName, quiz);
		} 
		catch (IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
		} 
		catch (RuntimeException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
