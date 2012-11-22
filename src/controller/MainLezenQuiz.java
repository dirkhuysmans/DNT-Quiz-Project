package controller;

import model.FileContainer;
import model.Quiz;
/**
 * Deze klasse haalt gegevens uit het tekstbestand "bestanden\quiz.txt" <br>
 * en zet deze om naar een quiz-object <br>
 * past tevens de QuizCatalogus aan en koppelt de quiz
 * 
 * @author Noella
 *
 */
public class MainLezenQuiz extends FileContainer{
	public static void main(String[] args) throws Exception {
		String fileName = "bestanden\\quiz.txt";
		Quiz quiz = null;
		try {
			quiz = lezenQuiz(fileName);
			
			System.out.println(quiz.getOpdrachten());
			
			System.out.println(quiz.toString());
		} catch (Exception e) {
			throw new Exception("Bestand niet gevonden");
		}
	}
}
