package model;
/**
 * @author Dirk Huysmans
 */

public class OpdrachtAntwoord {

	/**
	 * Attributen
	 */
	
	private String laatsteAntwoord;
	private int aantalPogingen;
	private int antwoordTijd;
	private QuizOpdracht quizOpdracht;
	

	/**
	 * Constructor
	 * @param laatsteAntwoord
	 * @param aantalPogingen
	 * @param antwoordTijd
	 * @param quizOpdracht
	 */
	public OpdrachtAntwoord(String laatsteAntwoord, int aantalPogingen,
			int antwoordTijd, QuizOpdracht quizOpdracht) {
		this.laatsteAntwoord = laatsteAntwoord;
		this.aantalPogingen = aantalPogingen;
		this.antwoordTijd = antwoordTijd;
		this.quizOpdracht = quizOpdracht;
	}
	/**
	 * 
	 * @setters
	 */
	public void setLaatsteAntwoord(String laatsteAntwoord) {
		this.laatsteAntwoord = laatsteAntwoord;
	}
	public void setAantalPogingen(int aantalPogingen) {
		this.aantalPogingen = aantalPogingen;
	}
	public void setAntwoordTijd(int antwoordtijd) {
		this.antwoordTijd = antwoordtijd;
	}
	public void setQuizOpdracht(QuizOpdracht quizOpdracht) {
		this.quizOpdracht = quizOpdracht;
	}

	/**
	 * 
	 * getters
	 */
	public String getLaatsteAntwoord() {
		return laatsteAntwoord;
	}
	public int getAantalPogingen() {
		return aantalPogingen;
	}
	
	public int getAntwoordTijd() {
		return antwoordTijd;
	}
	public QuizOpdracht getQuizOpdracht() {
		return quizOpdracht;
	}

	@Override
	public String toString() {
		return "LaatsteAntwoord=" + laatsteAntwoord
				+ ", aantalPogingen=" + aantalPogingen + ", antwoordTijd="
				+ antwoordTijd + ", quizOpdracht=" + quizOpdracht;
		}	
}
