package controller;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import utils.datum.Datum;

import model.Opdracht;
import model.Quiz;
import model.QuizCatalogus;
import model.QuizOpdracht;
import model.enumKlassen.Leraar;
import model.enumKlassen.OpdrachtCategorie;
import model.enumKlassen.QuizStatussen;


public class QuizController {

	public static void main(String[] args) throws Exception {
		try {
			QuizCatalogus quizCatalogus = new QuizCatalogus();
			
			List<String> hints = new ArrayList();
			hints.add("Eerste letter is 'B'");
			hints.add("Laatste letter is 'L'");
			
			Time time = new Time(120000); // in milliseconden
			
			OpdrachtCategorie opdrachtCategorie = OpdrachtCategorie.algemeneKennis;
			Leraar auteur = Leraar.MYRIAM;
			QuizStatussen quizStatus = QuizStatussen.INCONSTRUCTIE;

			Quiz quiz = new Quiz("Hoofdsteden", 2, true,true,quizStatus);
			
			quizCatalogus.voegQuizToe(quiz);
			
			Opdracht opdracht1 = new Opdracht("Wat is de hoofdstad van Franrijk?","Parijs");
			Opdracht opdracht2 = new Opdracht("Wat is de hoofdstad van Spanje?","Madrid");
			
			
			
			QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht1, 2);
			QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht2, 2);
			
			System.out.println(quiz.getOpdrachten());
			
			System.out.println(quiz.toString());
			
			
		} 
		catch (IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
		} 
		catch (RuntimeException ex) {
			System.out.println(ex.getMessage());
		}
	}

}
