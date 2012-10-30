package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Deze klasse bevat een catalogus van alle bestaande quizen<br>
 * @author Noella Michiels
 *
 */
public class QuizCatalogus {
	private List<Quiz> quizCatalogus = new ArrayList<Quiz>();
	
	
	
	/**
	 * voeg een quiz toe
	 * @param quiz		quiz is een object van het type Quiz
	 */
	public void voegQuizToe(Quiz quiz){
		quizCatalogus.add(quiz);
	}
	/**
	 * verwijder een quiz toe
	 * @param quiz		quiz is een object van het type Quiz
	 */
	public void verwijderQuiz(Quiz quiz){
		quizCatalogus.remove(quiz);
	}
	@Override
	public String toString(){
		String catalogus = "";
		for(Quiz quiz : quizCatalogus){
			catalogus += quiz + "\n";
		}
		return catalogus;
	}
	
	public void wegschrijvenNaarFile (Quiz quiz){
		FileOutputStream file = null; 
		ObjectOutputStream obj = null;
		try{
			file = new FileOutputStream("Quizzen.ser");
			obj.writeObject(quiz);
		}catch (IOException ex){
			System.out.println("Error om naar de file te schrijven");
		}
	}
}
