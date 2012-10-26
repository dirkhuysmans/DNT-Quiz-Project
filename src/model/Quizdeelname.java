package model;

import utils.gregorian.Datum;


public class Quizdeelname {

	private Leerling leerling;
	private OpdrachtAntwoord opdrachtAntwoord;
	private Quiz quiz;
	private utils.gregorian.Datum datum;
	
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
		this.opdrachtAntwoord = opdrachtAntwoord;
		this.quiz = quiz;
		this.datum = datum;
	}

	/**
	 * 
	 * Setters
	 */
	public void setLeerling(Leerling leerling) {
		this.leerling = leerling;
	}
	public void setOpdrachtAntwoord(OpdrachtAntwoord opdrachtAntwoord) {
		this.opdrachtAntwoord = opdrachtAntwoord;
	}
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	public void setDatum(utils.gregorian.Datum datum) {
		this.datum = datum;
	}
	
	/**
	 * Getters
	 */
	public Leerling getLeerling() {
		return leerling;
	}
	public OpdrachtAntwoord getOpdrachtAntwoord() {
		return opdrachtAntwoord;
	}
	public Quiz getQuiz() {
		return quiz;
	}
	public utils.gregorian.Datum getDatum() {
		return datum;
	}
	
	@Override
	public String toString() {
		return "Quizdeelname [leerling=" + leerling + ", opdrachtAntwoord="
				+ opdrachtAntwoord + ", quiz=" + quiz + ", datum=" + datum
				+ "]";
	}
}
