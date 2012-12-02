package controller;

import java.io.IOException;
import java.sql.Time;
import java.util.Map;
import java.util.TreeMap;

import model.EenvoudigeOpdracht;
import model.FileContainer;
import model.LeerlingContainer;
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
			
			Opdracht opdracht1 = new EenvoudigeOpdracht("Wat is de hoofdstad van Franrijk?","Parijs");
			Opdracht opdracht2 = new EenvoudigeOpdracht("Wat is de hoofdstad van Spanje?","Madrid");
			Opdracht opdracht3 = new EenvoudigeOpdracht("In welke provincie ligt de hoofdstad van Belgie?","Brabant");
			Opdracht opdracht4 = new EenvoudigeOpdracht("In welke provincie ligt Genk?","Limburg");
			Opdracht opdracht5 = new EenvoudigeOpdracht("In welke provincie ligt Antwerpen","Antwerpen");
			try{
				QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht1, 2);
				QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht2, 2);
				QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht3, 5);
				QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht4, 3);
				QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht5, 4);
				
				Map<Quiz, String> quizzen = new TreeMap<Quiz, String>(Quiz.QuizComparator());
				quizzen.put(quiz, quiz.voorTreeMap());
						
				
				
				System.out.println(quiz.toString());
				
				String fileName = "bestanden\\quiz.txt";
				//schrijvenQuiz(fileName, quiz);
				quizCatalogus.wegschrijven();
				quizCatalogus=null;
				quizCatalogus = new QuizCatalogus();
				quizCatalogus.lezen();
				System.out.print(quizCatalogus.toString());
			}
			catch(Exception e){
				e.printStackTrace();		
			}
			
			
		} 
		catch (IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
		} 
		catch (RuntimeException ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public String getFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void wegschrijven() throws IOException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void maakObjectVanLijn(String[] velden) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
