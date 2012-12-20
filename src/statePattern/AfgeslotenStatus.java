package statePattern;

import model.Quiz;

/**
 * klasse voor het verwerken van de afgeslotenStatus
 * 
 * @author Noella
 *
 */
public class AfgeslotenStatus extends QuizStatus{
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
	public AfgeslotenStatus(Quiz quiz){
		this.quiz = quiz;
	}
	@Override
	public void statusAfgesloten() throws Exception{
		throw new Exception("De status is reeds afgesloten");
	}
	@Override
	public String toString(){
		return "afgesloten";
	}
}
