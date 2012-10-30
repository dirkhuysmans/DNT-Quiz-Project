package model;

public class QuizOpdracht {
	private Quiz quiz;
	private Opdracht opdracht;
	private int maxScore;
	private int volgNummer;
	
	public QuizOpdracht (Quiz quiz, Opdracht opdracht, int score){
		setQuiz(quiz);
		setOpdracht(opdracht);
		setMaxScore(score);
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
}
