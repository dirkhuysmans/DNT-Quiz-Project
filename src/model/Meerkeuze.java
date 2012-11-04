package model;

import java.sql.Time;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.enumKlassen.Leraar;
import model.enumKlassen.OpdrachtCategorie;
import utils.gregorian.Datum;

/**
 * 
 * @author thijs
 *
 */

public class Meerkeuze extends Opdracht implements Valideerbaar{
	private Map<Integer, String> antwoord;

	public void setAntwoord(Map<Integer, String> antwoord) {
		this.antwoord = antwoord;
	}

	public Meerkeuze(String vraag, Map<Integer, String> antwoord, List<String> hints, int maxAantalPogingen,
			Time maxAntwoordTijd, OpdrachtCategorie categorie, Leraar auteur) {
		super(vraag, hints, maxAantalPogingen, maxAntwoordTijd, categorie, auteur);
		setAntwoord(antwoord);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValide(String ant) {
		int temp = Integer.parseInt(ant);
		boolean uitkomst = false;
		for (int i : antwoord.keySet()){
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
		// TODO Auto-generated method stub
		return null;
	}

}
