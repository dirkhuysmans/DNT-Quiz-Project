package statePattern;

import model.Quiz;



/**
 * klasse voor het verwerken van de opengesteldStatus
 * 
 * @author Noella
 *
 */
public class OpengesteldStatus extends QuizStatus{
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
	public OpengesteldStatus(Quiz quiz){
		this.quiz = quiz;
	}
	@Override
	public void statusOpengesteld() throws Exception{
		throw new Exception("De status is reeds opengesteld");
	}
	@Override
	public void statusLaatsteKans() throws Exception {
		quiz.setQuizStatus(quiz.getLaatsteKansStatus());	
	}
	@Override
	public String toString(){
		return "opengesteld";
	}	
	
}
