package statePattern;

import model.Quiz;

/**
 * klasse voor het verwerken van de inConstructieStatus
 * 
 * @author Noella
 *
 */
public class InConstructieStatus extends QuizStatus{
	//
	// Attributen
	//
	Quiz quiz;
	//
	// Constructor
	//
	/**
	 * 
	 * @param test	quiz is een object van het type Quiz
	 */
	public InConstructieStatus(Quiz quiz){
		this.quiz = quiz;
	}
	/**
	 * @throws	Exception	geeft een foutmelding indien de status zich reeds inConstructie bevindt
	 */
	@Override
	public void statusInConstructie() throws Exception{
		throw new Exception("De status is reeds inConstructie");
	}
	/**
	 * @throws 	Exception	
	 */
	@Override
	public void statusAfgewerkt() throws Exception{
		quiz.setQuizStatus(quiz.getAfgewerktStatus());
	}
	@Override
	public String toString(){
		return "inConstructie";
	}
}
