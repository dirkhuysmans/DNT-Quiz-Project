package persistenty;

import model.Opdracht;
import model.Quiz;

public class TextDaoFacade implements DaoFacade{

	
	@Override
	public void createQuiz(Quiz quiz){
		System.out.println(quiz.getOnderwerp());
		System.out.println(quiz.getMinLeerjaar());
		System.out.println(quiz.isTest());
		System.out.println(quiz.isUniekeDeelname());
		System.out.println(quiz.getQuizStatus());
	}

	@Override
	public void createOpdracht(Opdracht opdracht) {
		// TODO Auto-generated method stub
		
	}

}
