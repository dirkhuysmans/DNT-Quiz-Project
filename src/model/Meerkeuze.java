package model;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import utils.datum.Datum;
import model.enumKlassen.Leraar;
import model.enumKlassen.OpdrachtCategorie;

/**
 * 
 * @author thijs aangepast door Dirk
 *
 */

public class Meerkeuze extends Opdracht implements Valideerbaar{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String meerkeuze;

	public Meerkeuze(String vraag, String antwoord, String meerkeuze, String hints, int maxAantalPogingen,
			Time maxAntwoordTijd, OpdrachtCategorie categorie, Leraar auteur) {
		super(vraag, antwoord, hints, maxAantalPogingen, maxAntwoordTijd, categorie, auteur);
		this.meerkeuze = meerkeuze; 
	}	

	
	public Meerkeuze(String vraag, String antwoord, String meerkeuze, String hints, int maxAantalPogingen,
			Time maxAntwoordTijd, OpdrachtCategorie categorie, Leraar auteur, Datum opmaakdatum) {
		super(vraag, antwoord, hints, maxAantalPogingen, maxAntwoordTijd, categorie, auteur, opmaakdatum);
		this.meerkeuze = meerkeuze; 
	}	

	public void setMeerkeuze(String meerkeuze) {
		this.meerkeuze = meerkeuze;
	}
	
	public String getMeerkeuze() {
		return meerkeuze;
	}

	public Map<Integer, String>  getLijstMeerkeuze(){
		String[] velden = this.meerkeuze.split(";");
		Map <Integer, String> lijstMeerkeuze = new HashMap <Integer, String>();
		int i = 0;
		for (String meerkeuze : velden) {
			i++;
			lijstMeerkeuze.put(i, meerkeuze);
		}
		return lijstMeerkeuze;
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
		return "Kies één van de getallen die in de opgave staan";
	}

}