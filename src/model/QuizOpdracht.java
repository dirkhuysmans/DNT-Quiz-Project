package model;
/**
 * 
 * @author Noella
 * @version oktober 2012
 *
 */
public class QuizOpdracht {
	private Quiz quiz;
	private Opdracht opdracht;
	private int maxScore;
	private int volgNummer;
	/**
	 * Aanmaken QuizOpdracht
	 * 
	 * @param quiz			quiz is een object van het type Quiz
	 * @param opdracht		opdracht is een object van het type opdracht
	 * @param maxScore		maximum score die kan behaald worden
	 */
	private QuizOpdracht (Quiz quiz, Opdracht opdracht, int maxScore){
		this.quiz = quiz;
		this.opdracht = opdracht;
		this.maxScore = maxScore;		
	}
	//
	// getters
	//
	public Quiz getQuiz() {
		return quiz;
	}

	public Opdracht getOpdracht() {
		return opdracht;
	}

	public int getMaxScore() {
		return maxScore;
	}

	public int getVolgNummer() {
		return volgNummer;
	}
	//
	// setters
	//
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	public void setOpdracht(Opdracht opdracht) {
		this.opdracht = opdracht;
	}
	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}
	public void setVolgNummer(int volgNummer) {
		this.volgNummer = volgNummer;
	}
	/**
	 * QuizOpdracht aan een Quiz koppelen
	 * 
	 * @param quiz			quiz is een object van het type Quiz
	 * @param opdracht		opdracht is een object van het type Opdracht
	 * @param maxScore		maximum score die kan behaald worden
	 * @throws Exception 
	 */
	public static void koppelOpdrachtAanQuiz(Quiz quiz, Opdracht opdracht, int maxScore) throws Exception{
		QuizOpdracht quizOpdracht = new QuizOpdracht(quiz,opdracht,maxScore);
		try{
			quiz.voegQuizOpdrachtToe(quizOpdracht);

			opdracht.voegQuizOpdrachtToe(quizOpdracht);	
		}
		catch (Exception e){
			throw new Exception(e.getMessage());
		}
	}
	/**
	 * 	
	 */
	public void ontKoppelOpdrachtVanQuiz() throws Exception{
		try{
			quiz.verwijderQuizOpdracht(this);
			opdracht.verwijderQuizOpdracht(this);
		}
		catch (Exception e){
			throw new Exception(e.getMessage());
		}
	}
}
