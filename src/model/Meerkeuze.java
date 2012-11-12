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
	private Map<Integer, String> keuzes;

	public void setKeuzes(Map<Integer, String> keuzes) {
		keuzes = keuzes;
	}

	public Meerkeuze(String vraag, Map<Integer, String> keuzes, List<String> hints, int maxAantalPogingen,
			Time maxAntwoordTijd, OpdrachtCategorie categorie, Leraar auteur) {
		super(vraag, hints, maxAantalPogingen, maxAntwoordTijd, categorie, auteur);
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
