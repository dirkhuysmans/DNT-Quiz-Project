package controller;

import java.util.ArrayList;
import java.util.List;

import model.Quiz;

import persistenty.SqlDaoFacade;

public class FillDatabase {
	static SqlDaoFacade sqlDaoFacade = new SqlDaoFacade();
	
	
	public static void createQuiz(){
		List<Quiz> quizzen = new ArrayList<Quiz>();
		quizzen.add(new Quiz("Hoofdsteden", 2, 3, true, true, "afgewerkt"));
		quizzen.add(new Quiz("Feestdagen", 1, 3, false, false, "inConstructie"));
		quizzen.add(new Quiz("Weekdagen", 1, 2, false, true, "afgewerkt"));
		quizzen.add(new Quiz("Seizoenen", 2, 3, true, false, "afgewerkt"));
		quizzen.add(new Quiz("Extensies", 2, 4, true, false, "afgewerkt"));
		quizzen.add(new Quiz("Munten", 4, 4, true, true, "inConstructie"));
		quizzen.add(new Quiz("Planeten", 5, 6, false, true, "afgewerkt"));
		
		for(Quiz quiz : quizzen){
			sqlDaoFacade.createQuiz(quiz);
		}
	}
	
	public static void main(String[] args) throws Exception {
		createQuiz();
	}

}
