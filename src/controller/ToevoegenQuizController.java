package controller;

import java.util.concurrent.ExecutionException;

import model.Quiz;
import model.enumKlassen.Leraar;
import model.enumKlassen.QuizStatussen;
import persistenty.DaoFacade;
import view.IO;



public class ToevoegenQuizController{

	private DaoFacade daoFacade;
	
	public ToevoegenQuizController(DaoFacade daoFacade){
		this.daoFacade = daoFacade;
	}
	
	public void maakQuiz(String onderwerp, int leerJaar, boolean isUniekeDeelname,
			             boolean isTest, QuizStatussen quizStatus, Leraar auteur) throws IllegalArgumentException, Exception{
		try{
			Quiz quiz = new Quiz(onderwerp,leerJaar,isUniekeDeelname,isTest,quizStatus);
			daoFacade.createQuiz(quiz);
		}
		catch(IllegalArgumentException iaex){
			IO.toonStringMetVenster(iaex+"");
			//e.printStackTrace();		
		}
		catch(Exception ex){
			IO.toonStringMetVenster(ex+"");
			//e.printStackTrace();		
		}
	}
	
}
