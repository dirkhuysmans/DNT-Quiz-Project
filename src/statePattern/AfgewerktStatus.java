package statePattern;

import model.Quiz;

/**
 * klasse voor het verwerken van de afgewerktStatus
 * 
 * @author Noella
 *
 */
public class AfgewerktStatus extends QuizStatus{
	//
	// Attributen
	//
	Quiz quiz;
	//
	// Constructor
	//
	/**
	 * 
	 * @param quiz	quiz is een object van het type Quiz
	 */
	public AfgewerktStatus(Quiz quiz){
		this.quiz = quiz;
	}
	
	@Override
	public void statusAfgewerkt() throws Exception{
		throw new Exception("De status is reeds Afgewerkt");
	}
	
	@Override
	public void statusOpengesteld() throws Exception {
		quiz.setQuizStatus(quiz.getOpengesteldStatus());	
	}
	@Override
	public String toString(){
		return "afgewerkt";
	}
}
