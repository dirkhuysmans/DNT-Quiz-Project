package model;

public class QuizOpdracht {
	private Quiz quiz;
	private Opdracht opdracht;
	private int maxScore;
	private int volgNummer;
	
	private QuizOpdracht (Quiz quiz, Opdracht opdracht, int maxScore){
		this.quiz = quiz;
		this.opdracht = opdracht;
		this.maxScore = maxScore;		
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public Opdracht getOpdracht() {
		return opdracht;
	}

	public void setOpdracht(Opdracht opdracht) {
		this.opdracht = opdracht;
	}

	public int getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}
	
	public static void koppelOpdrachtAanQuiz(Quiz quiz, Opdracht opdracht, int maxScore){
		QuizOpdracht quizOpdracht = 
		new QuizOpdracht(quiz,opdracht,maxScore);
		quiz.voegQuizOpdrachtToe(quizOpdracht);
		opdracht.voegQuizToe(quizOpdracht);
		}

		public void ontKoppelOpdrachtVanQuiz(){
			quiz.verwijderQuizOpdracht(this);
			opdracht.verwijderQuiz(this);
		}	

}
