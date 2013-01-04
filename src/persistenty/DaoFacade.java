package persistenty;

import java.util.List;

import model.Opdracht;
import model.Quiz;

public interface DaoFacade {

	public void createQuiz(Quiz quiz);
	public List<Quiz> selectAlleQuizzen();
	public List<Quiz> selectQuizTotBepaaldLeerjaar(int hoogsteLeerjaar);
	public void createOpdracht(Opdracht opdracht);
	public List<Opdracht> selectAlleOpdrachten();
	public List<Opdracht> selectOpdrachtenPerType(String type) throws Exception;
	public List<Opdracht> selectOpdrachtenPerCategorie(String geselecteerdType) throws Exception;
	
}
