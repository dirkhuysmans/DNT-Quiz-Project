package model;

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
}
