/*
 * package model;
 

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import model.enumKlassen.Leraar;
import model.enumKlassen.OpdrachtCategorie;

 * 
 * @author thijs
 *

public class Opsomming extends Opdracht implements Valideerbaar{

	private static final long serialVersionUID = 1L;

	//private String antwoord;
	public Map<Integer, String> opsommingLijst;
	private boolean inJuisteVolgorde;

	public Opsomming(String vraag, String antwoord, Map<Integer, String> opsomming, boolean juisteVolgorde, List<String> hints,
			int maxAantalPogingen, int maxAntwoordTijd,
			OpdrachtCategorie categorie, Leraar auteur) {
		super(vraag, antwoord, hints, maxAantalPogingen, maxAntwoordTijd, categorie,
				auteur);
		opsommingLijst = opsomming;
		inJuisteVolgorde = juisteVolgorde;
		setAntwoord(antwoord);
		// TODO Auto-generated constructor stub
	}

	public Opsomming(String vraag, String antwoord, Map<Integer, String> opsomming, boolean juisteVolgorde, List<String> hints,
			int maxAantalPogingen, int maxAntwoordTijd,
			OpdrachtCategorie categorie, Leraar auteur, String opmaakDatum) {
		super(vraag, antwoord, hints, maxAantalPogingen, maxAntwoordTijd, categorie,
				auteur, opmaakDatum);
		opsommingLijst = opsomming;
		inJuisteVolgorde = juisteVolgorde;
		setAntwoord(antwoord);
		// TODO Auto-generated constructor stub
	}

	public void setOpsomming(Map<Integer, String> opsomming){
		opsommingLijst = opsomming;
	}
	
	public Map<Integer, String>  getOpsomming(){
		return opsommingLijst;
	}
	
	public void setInJuisteVolgorde(boolean juisteVolgorde){
		inJuisteVolgorde = juisteVolgorde;
	}
	
	public boolean getInJuisteVolgorde(){
		return inJuisteVolgorde;
	}
	
	public void setAntwoord(String antwoord) {
		this.antwoord = antwoord;
	}

	@Override
	public boolean isValide(String antw) {
		StringTokenizer binnenkomend = new StringTokenizer(antw, ";");
		StringTokenizer origineel = new StringTokenizer(this.getAntwoord(), ";");
		if (binnenkomend.countTokens() == origineel.countTokens()){
			return true;
		}else{
			getValideerTekst();
			return false;
		}
	}

	@Override
	public String getValideerTekst() {
		return ("Typ je antwoorden achter elkaar gescheiden door ;");
	}

}
*/
package model;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import utils.datum.Datum;

import model.enumKlassen.Leraar;
import model.enumKlassen.OpdrachtCategorie;

/**
 * 
 * @author thijs
 *
 */

public class Opsomming extends Opdracht implements Valideerbaar{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String opsomming;
	private boolean inJuisteVolgorde;
	
	/**
	 * 
	 * @param vraag
	 * @param antwoord
	 * @param opsomming
	 * @param juisteVolgorde
	 * @param hints
	 * @param maxAantalPogingen
	 * @param maxAntwoordTijd
	 * @param categorie
	 * @param auteur
	 * @param opmaakDatum
	 */

	public Opsomming(String vraag, String antwoord, String opsomming, boolean juisteVolgorde, String hints,
			int maxAantalPogingen, int maxAntwoordTijd, OpdrachtCategorie categorie, Leraar auteur) {
		super(vraag, antwoord, hints, maxAantalPogingen, maxAntwoordTijd, categorie, auteur);
		
		this.opsomming = opsomming;
		this.inJuisteVolgorde = juisteVolgorde;
		// TODO Auto-generated constructor stub
	}

	
	public Opsomming(String vraag, String antwoord, String opsomming, boolean juisteVolgorde, String hints,
			int maxAantalPogingen, int maxAntwoordTijd, OpdrachtCategorie categorie, Leraar auteur, Datum opmaakDatum) {
		super(vraag, antwoord, hints, maxAantalPogingen, maxAntwoordTijd, categorie, auteur, opmaakDatum);
		
		this.opsomming = opsomming;
		this.inJuisteVolgorde = juisteVolgorde;
		// TODO Auto-generated constructor stub
	}
	
	public String getOpsomming(){
		return this.opsomming;
	}
	
	public Map<Integer, String>  getLijstOpsomming(){
		String[] velden = this.opsomming.split(";");
		Map <Integer, String> lijstOpsomming = new HashMap <Integer, String>();
		int i = 0;
		for (String opsomming : velden) {
			i++;
			lijstOpsomming.put(i, opsomming);
		}
		return lijstOpsomming;
	}
	
	public void setInJuisteVolgorde(boolean juisteVolgorde){
		inJuisteVolgorde = juisteVolgorde;
	}
	
	public boolean getInJuisteVolgorde(){
		return inJuisteVolgorde;
	}
	
	@Override
	public boolean isValide(String antw) {
		StringTokenizer binnenkomend = new StringTokenizer(antw, ";");
		StringTokenizer origineel = new StringTokenizer(this.getAntwoord(), ";");
		if (binnenkomend.countTokens() == origineel.countTokens()){
			return true;
		}else{
			getValideerTekst();
			return false;
		}
	}

	@Override
	public String getValideerTekst() {
		return ("Typ je antwoorden achter elkaar gescheiden door ;");
	}

}