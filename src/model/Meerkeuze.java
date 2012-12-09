/*
 * package model;
 

import java.util.List;
import java.util.Map;
import model.enumKlassen.Leraar;
import model.enumKlassen.OpdrachtCategorie;

 * 
 * @author thijs
 *

public class Meerkeuze extends Opdracht implements Valideerbaar{
	 * 
	private static final long serialVersionUID = 1L;
	public Map<Integer, String> keuzes;

	public void setKeuzes(Map<Integer, String> keuzeMogelijkheden) {
		keuzes = keuzeMogelijkheden;
	}

	public Meerkeuze(String vraag, String antwoord, Map<Integer, String> keuzes, List<String> hints, int maxAantalPogingen,
			int maxAntwoordTijd, OpdrachtCategorie categorie, Leraar auteur) {
		super(vraag, antwoord, hints, maxAantalPogingen, maxAntwoordTijd, categorie, auteur);
		setKeuzes(keuzes);
		// TODO Auto-generated constructor stub
	}
	
	public Meerkeuze(String vraag, String antwoord, Map<Integer, String> keuzes, List<String> hints, int maxAantalPogingen,
			int maxAntwoordTijd, OpdrachtCategorie categorie, Leraar auteur, String opmaakdatum) {
		super(vraag, antwoord, hints, maxAantalPogingen, maxAntwoordTijd, categorie, auteur, opmaakdatum);
		setKeuzes(keuzes);
		// TODO Auto-generated constructor stub
	}	

	@Override
	public boolean isValide(String ant) {
		int temp = Integer.parseInt(ant);
		boolean uitkomst = false;
		for (int i : keuzes.keySet()){
			if(temp == i){
				uitkomst = true;
				break;
			}else{
				uitkomst = false;
			}
		}
		return uitkomst;
	}

	
	@Override
	public String getValideerTekst() {
		return "Kies één van de getallen die in de opgave staan";
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
			int maxAntwoordTijd, OpdrachtCategorie categorie, Leraar auteur) {
		super(vraag, antwoord, hints, maxAantalPogingen, maxAntwoordTijd, categorie, auteur);
		this.meerkeuze = meerkeuze; 
		// TODO Auto-generated constructor stub
	}	

	
	public Meerkeuze(String vraag, String antwoord, String meerkeuze, String hints, int maxAantalPogingen,
			int maxAntwoordTijd, OpdrachtCategorie categorie, Leraar auteur, Datum opmaakdatum) {
		super(vraag, antwoord, hints, maxAantalPogingen, maxAntwoordTijd, categorie, auteur, opmaakdatum);
		this.meerkeuze = meerkeuze; 
		// TODO Auto-generated constructor stub
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