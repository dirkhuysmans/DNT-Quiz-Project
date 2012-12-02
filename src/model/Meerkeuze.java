package model;

import java.util.List;
import java.util.Map;
import model.enumKlassen.Leraar;
import model.enumKlassen.OpdrachtCategorie;

/**
 * 
 * @author thijs
 *
 */

public class Meerkeuze extends Opdracht implements Valideerbaar{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Map<Integer, String> keuzes;

	public void setKeuzes(Map<Integer, String> keuzeMogelijkheden) {
		keuzes = keuzeMogelijkheden;
	}

	public Meerkeuze(String vraag, String antwoord, Map<Integer, String> keuzes, List<String> hints, int maxAantalPogingen,
			int maxAntwoordTijd, OpdrachtCategorie categorie, Leraar auteur) {
		super(vraag,antwoord, hints, maxAantalPogingen, maxAntwoordTijd, categorie, auteur);
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
