package controller;

import java.util.List;

import model.Opdracht;
import model.Quiz;
import model.QuizOpdracht;
import model.enumKlassen.Leraar;
import model.enumKlassen.QuizStatussen;
import persistenty.DaoFacade;
import statePattern.QuizStatus;
import view.IO;

public class ToevoegenQuizController {

	private DaoFacade daoFacade;
	private QuizOpdracht quizOpdracht;
	private Quiz quiz;

	public ToevoegenQuizController(DaoFacade daoFacade) {
		this.daoFacade = daoFacade;
	}

	public void maakQuiz(String onderwerp, int minLeerJaar, int maxLeerjaar,
			boolean isTest, boolean isUniekeDeelname, String quizStatus,
			Leraar auteur, List opdrachten) throws IllegalArgumentException,
			Exception {
		try {						
			quiz = new Quiz(onderwerp, minLeerJaar, maxLeerjaar, isTest,
					isUniekeDeelname, quizStatus, auteur);
			quiz.setQuizOpdracht(opdrachten);
			daoFacade.createQuiz(quiz);
		} catch (IllegalArgumentException iaex) {
			IO.toonStringMetVenster(iaex + "");
			// e.printStackTrace();
		} catch (Exception ex) {
			IO.toonStringMetVenster(ex + "");
			// e.printStackTrace();
		}
	}
}
