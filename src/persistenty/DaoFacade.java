package persistenty;

import model.Opdracht;
import model.Quiz;

public interface DaoFacade {

	public void createQuiz(Quiz quiz);

	public void createOpdracht(Opdracht opdracht);
	
}
