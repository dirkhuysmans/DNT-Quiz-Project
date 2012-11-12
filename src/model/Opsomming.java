package model;

import java.sql.Time;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import model.enumKlassen.Leraar;
import model.enumKlassen.OpdrachtCategorie;
import utils.gregorian.Datum;

/**
 * 
 * @author thijs
 *
 */

public class Opsomming extends Opdracht implements Valideerbaar{
	private String antwoord;

	public Opsomming(String vraag, String antwoord, List<String> hints,
			int maxAantalPogingen, Time maxAntwoordTijd,
			OpdrachtCategorie categorie, Leraar auteur) {
		super(vraag, hints, maxAantalPogingen, maxAntwoordTijd, categorie,
				auteur);
		setAntwoord(antwoord);
		// TODO Auto-generated constructor stub
	}

	public void setAntwoord(String antwoord) {
		this.antwoord = antwoord;
	}

	@Override
	public boolean isValide(String antw) {
		StringTokenizer binnenkomend = new StringTokenizer(antw, ";");
		StringTokenizer origineel = new StringTokenizer(antwoord, ";");
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
