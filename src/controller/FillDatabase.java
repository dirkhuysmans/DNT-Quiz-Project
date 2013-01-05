package controller;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import model.Meerkeuze;
import model.Opdracht;
import model.Opsomming;
import model.Quiz;
import model.Reproductie;
import model.enumKlassen.Leraar;
import model.enumKlassen.OpdrachtCategorie;

import persistenty.SqlDaoFacade;

public class FillDatabase {
	static SqlDaoFacade sqlDaoFacade = new SqlDaoFacade();
	
	
	public static void createQuizzen(){
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
	
	public static void createOpdrachten(){
		List<Opdracht> opdrachten = new ArrayList<Opdracht>();
		opdrachten.add(new Reproductie("Wat weet je over de missie van Christoffel Columbus?", "1492;Spanjaard;India ontdekken;ontdekte Mexico"
				, 2, null, 1, new Time(0,0,30), OpdrachtCategorie.algemeneKennis, Leraar.FRANK));
		opdrachten.add(new Reproductie("Wie was Simone de Beauvoir?", "filosofe; feministe;" +
				" jaren 50; vrouw van Sartre", 2, null, 1, new Time(0,0,20), OpdrachtCategorie.FranseTaal, Leraar.GUY));
		opdrachten.add(new Opsomming("som de verschillende maanden van het jaar op", "januari; februari; maart; april; mei; juni; juli; augustus; september; oktober; november",
				true, null, 1, new Time(0,1,0), OpdrachtCategorie.NederlandseTaal, Leraar.FRANK));
		opdrachten.add(new Meerkeuze("Hoeveel is 37+(4*9)", "73", "67;71;73;79", null, 1, new Time(0,0,8), OpdrachtCategorie.rekenen, Leraar.TOM));
		
		for(Opdracht opdracht : opdrachten){
			sqlDaoFacade.createOpdracht(opdracht);
		}
	}
	
	public static void main(String[] args) throws Exception {
//		createQuizzen();
		createOpdrachten();
	}

}
