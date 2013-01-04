package persistenty;

import java.util.List;

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

	@Override
	public List<Quiz> selectAlleQuizzen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Quiz> selectQuizTotBepaaldLeerjaar(int hoogsteLeerjaar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Opdracht> selectAlleOpdrachten() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Opdracht> selectOpdrachtenPerType(String type) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Opdracht> selectOpdrachtenPerCategorie(String geselecteerdType)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
