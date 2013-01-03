package model;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.datum.Datum;
import model.enumKlassen.Leraar;
import model.enumKlassen.OpdrachtCategorie;

/**
 * 
 * @author Thijs + aanpassingen Dirk
 *
 */

public abstract class Opdracht implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String vraag="";
	protected String antwoord="";
	private String hints;
	private int maxAantalPogingen = 1;
	private Time maxAntwoordTijd= null;
	private OpdrachtCategorie categorie;
	private Leraar auteur = Leraar.MYRIAM;
	protected Datum opmaakDatum = new Datum();
	private List<QuizOpdracht> quizOpdrachten = new ArrayList<QuizOpdracht>();
	
	/**
	 * 
	 * @param vraag
	 * @param antwoord
	 * @param hints (string gescheiden door;)
	 * @param maxAantalPogingen
	 * @param maxAntwoordTijd
	 * @param categorie
	 * @param auteur
	 * @param datum (indien leeg systeemdatum)
	 * Constructor om de opdracht aan te maken
	 */
	
	public Opdracht(String vraag, String antwoord){
		this.vraag = vraag;
		this.antwoord = antwoord;
		
	}
	
	public Opdracht (String vraag, String antwoord, String hints, int maxAantalPogingen, 
			Time maxAntwoordTijd, OpdrachtCategorie categorie, Leraar auteur){
		this.vraag=vraag;
		this.antwoord = antwoord;
		this.hints = hints;
		this.maxAantalPogingen = maxAantalPogingen;
		this.maxAntwoordTijd = maxAntwoordTijd;
		this.categorie = categorie;
		this.auteur = auteur;
	}

	public Opdracht (String vraag, String antwoord, int maxAantalPogingen, String hints,
			Time maxAntwoordTijd){
		this.vraag=vraag;
		this.antwoord = antwoord;
		this.hints = hints;
		this.maxAantalPogingen = maxAantalPogingen;
		this.maxAntwoordTijd = maxAntwoordTijd;
		/*this.categorie = categorie;
		this.auteur = auteur;*/
	}
	
	public Opdracht (String vraag, String antwoord, String hints, int maxAantalPogingen, 
			Time maxAntwoordTijd, OpdrachtCategorie categorie, Leraar auteur, Datum opmaakDatum){
		this.vraag=vraag;
		this.antwoord = antwoord;
		this.hints = hints;
		this.maxAantalPogingen = maxAantalPogingen;
		this.maxAntwoordTijd = maxAntwoordTijd;
		this.categorie = categorie;
		this.auteur = auteur;
		if (opmaakDatum != null){this.opmaakDatum = opmaakDatum;}
	}

	
	/**
	 * 
	 * getters en setters voor de opdracht - variabelen
	 * 
	 */
	public String getVraag() {
		return vraag;
	}

	public void setVraag(String vraag) {
		this.vraag = vraag;
	}

	public String getAntwoord() {
		return antwoord;
	}

	public void setAntwoord(String antwoord) {
		this.antwoord = antwoord;
	}
	
	public void setHints(String hints){
		// TODO Auto-generated method stub
		this.hints = hints;
	}

	public String getHints() {
		return hints;
	}
	
	public Map<Integer, String>  getLijstHints(){
		String[] velden = this.hints.split(";");
		Map <Integer, String> lijstHints = new HashMap <Integer, String>();
		int i = 0;
		for (String hint : velden) {
			i++;
			lijstHints.put(i, hint);
		}
		return lijstHints;
	}


	public void setMaxAantalPogingen(int maxAantalPogingen) {
		this.maxAantalPogingen = maxAantalPogingen;
	}

	public int getMaxAantalPogingen() {
		return maxAantalPogingen;
	}

	public Time getMaxAntwoordTijd() {
		return maxAntwoordTijd;
	}

	public void setMaxAntwoordTijd(Time antwoordTijd) {
		this.maxAntwoordTijd = antwoordTijd;
	}

	public void setCategorie(OpdrachtCategorie categorie) {
		this.categorie = categorie;
	}

	public OpdrachtCategorie getCategorie() {
		return categorie;
	}

	public void setAuteur(Leraar auteur) {
		this.auteur = auteur;
	}

	public Leraar getAuteur() {
		return auteur;
	}

	public void setOpmaakDatum(Datum opmaakDatum) {
		this.opmaakDatum = opmaakDatum;
	}

	public void setOpmaakDatum(String opmaakDatum) {
		this.opmaakDatum = new Datum(opmaakDatum);
	}
	
	public Datum getOpmaakDatum() {
		return opmaakDatum;
	}
	
	public List<QuizOpdracht> getQuizOpdrachten() {
		return quizOpdrachten;
	}

	/**
	 * 
	 * @param antwoord
	 * ingevoerde antwoord is inkomende parameter
	 * @return
	 * als ingevoerde antwoord gelijk is aan antwoord dat hoort bij de opdracht
	 * is de return "true"
	 */
	public boolean isJuisteAntwoord(String antwoord){
		if (this.antwoord.equals(antwoord)){
			return true;
		}
		else return false;
	}
	
	/**
	 * volledige omschrijving van de opdracht die weergegeven wordt voor bvb de catalogus
	 */
	@Override
	public String toString(){
		return ( vraag );
	}
	
	public String toonOpdracht(){
		return (categorie + ":\t" + vraag + "\n\t antwoord:");
	}
	
	public void verwijderQuizOpdracht(QuizOpdracht qo){
		quizOpdrachten.remove(qo);
	}
	
	public void voegQuizOpdrachtToe(QuizOpdracht qo){
		quizOpdrachten.add(qo);
	}
	
}
