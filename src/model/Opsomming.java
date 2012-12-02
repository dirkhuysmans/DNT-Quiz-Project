package model;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

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
	
/*	public void setAntwoord(String antwoord) {
		this.antwoord = antwoord;
	}
*/
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
