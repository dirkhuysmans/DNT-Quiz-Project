package model;

import java.util.ArrayList;

//import utils.gregorian.Datum;
import utils.datum.Datum;


public class Quizdeelname {
/**
 * @author Dirk Huysmans
 */
	
	private Leerling leerling;
	//private OpdrachtAntwoord opdrachtAntwoord;
	private ArrayList <OpdrachtAntwoord> lijstOpdrachtAntwoord = new ArrayList<OpdrachtAntwoord>();
	private Quiz quiz;
	private Datum datumDeelname;
	
	/**
	 * Constructor
	 * @param leerling
	 * @param opdrachtAntwoord
	 * @param quiz
	 * @param datum
	 */
	
	public Quizdeelname(Leerling leerling, OpdrachtAntwoord opdrachtAntwoord,
			Quiz quiz, Datum datum) {
		this.leerling = leerling;
		this.lijstOpdrachtAntwoord.add(opdrachtAntwoord);
		this.quiz = quiz;
		this.datumDeelname = datum;
	}

	/**
	 * 
	 * Setters
	 */
	public void setLeerling(Leerling leerling) {
		this.leerling = leerling;
	}
	public void setOpdrachtAntwoord(OpdrachtAntwoord opdrachtAntwoord) {
		this.lijstOpdrachtAntwoord.add(opdrachtAntwoord);
	}
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	public void setdatumDeelname(Datum datum) {
		this.datumDeelname = datum;
	}
	
	/**
	 * Getters
	 */
	public Leerling getLeerling() {
		return leerling;
	}
	public ArrayList<OpdrachtAntwoord> getListOpdrachtAntwoord() {
		return lijstOpdrachtAntwoord;
	}
	
	public Quiz getQuiz() {
		return quiz;
	}
	public Datum getDatum() {
		return datumDeelname;
	}

	@Override
	public String toString() {
		return "Quizdeelname [leerling=" + leerling
				+ ", lijstOpdrachtAntwoord=" + lijstOpdrachtAntwoord
				+ ", quiz=" + quiz + ", datumDeelname=" + datumDeelname + "]";
	}
	
		
}
