package model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import utils.gregorian.Datum;
import model.enumKlassen.Leraar;
import model.enumKlassen.OpdrachtCategorie;

/**
 * 
 * @author thijs
 *
 */

public class Opdracht {
	private String vraag="";
	private String antwoord="";
	private List<String> hints= new ArrayList<String>();
	private int maxAantalPogingen = 1;
	private Time maxAntwoordTijd = null;
	private OpdrachtCategorie categorie;
	private Leraar auteur;
	private Datum opmaakDatum;
	
	/**
	 * 
	 * @param vraag
	 * @param antwoord
	 * @param maxAantalPogingen
	 * @param maxAntwoordTijd
	 * @param categorie
	 * @param auteur
	 * @param opmaak
	 * Constructor om de opdracht aan te maken
	 */
	
	public Opdracht (String vraag, String antwoord, List<String> hints, int maxAantalPogingen, 
			Time maxAntwoordTijd, OpdrachtCategorie categorie, Leraar auteur, Datum opmaak){
		setVraag(vraag);
		setAntwoord(antwoord);
		setHints(hints);
		setMaxAantalPogingen(maxAantalPogingen);
		setMaxAntwoordTijd(maxAntwoordTijd);
		setCategorie(categorie);
		setAuteur(auteur);
		setOpmaakDatum(opmaak);		
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
	
	public List<String> getHints() {
		return hints;
	}

	public void setHints(List<String> hints) {
		this.hints = hints;
	}

	public int getMaxAantalPogingen() {
		return maxAantalPogingen;
	}

	public void setMaxAantalPogingen(int maxAantalPogingen) {
		this.maxAantalPogingen = maxAantalPogingen;
	}

	public Time getMaxAntwoordTijd() {
		return maxAntwoordTijd;
	}

	public void setMaxAntwoordTijd(Time maxAntwoordTijd) {
		this.maxAntwoordTijd = maxAntwoordTijd;
	}

	public OpdrachtCategorie getCategorie() {
		return categorie;
	}

	public void setCategorie(OpdrachtCategorie categorie) {
		this.categorie = categorie;
	}

	public Leraar getAuteur() {
		return auteur;
	}

	public void setAuteur(Leraar auteur) {
		this.auteur = auteur;
	}

	public Datum getOpmaakDatum() {
		return opmaakDatum;
	}

	public void setOpmaakDatum(Datum opmaakDatum) {
		this.opmaakDatum = opmaakDatum;
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
		return (auteur + " " + categorie + ": " + vraag + "\n\t " + antwoord + 
				"\n\t" + hints + "\n\t" + maxAantalPogingen + "mogelijke pogingen in " + 
				maxAntwoordTijd + " aantal seconden");
	}
	
	public String toonOpdracht(){
		return (categorie + ": " + vraag + "\n\t antwoord:");
	}
}
