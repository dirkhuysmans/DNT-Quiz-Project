package statePattern;

import model.Quiz;



/**
 * klasse voor het verwerken van de ilaatsteKansStatus
 * 
 * @author Noella
 *
 */
public class LaatsteKansStatus extends QuizStatus{
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
	public LaatsteKansStatus(Quiz quiz){
		this.quiz = quiz;
	}
	@Override
	public void statusLaatsteKans() throws Exception{
		throw new Exception("De status is reeds laatsteKans");
	}
	@Override
	public void statusAfgesloten() throws Exception {
		quiz.setQuizStatus(quiz.getAfgeslotenStatus());	
	}
	@Override
	public String toString(){
		return "laatsteKans";
	}
}
