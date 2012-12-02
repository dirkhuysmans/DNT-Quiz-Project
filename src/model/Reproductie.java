package model;

import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.enumKlassen.Leraar;
import model.enumKlassen.OpdrachtCategorie;
/**
 * 
 * @author thijs
 *
 */

public class Reproductie extends Opdracht{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Set<String> trefwoorden = new HashSet<String>();
	int minAantalJuisteTrefwoorden = 0;
	
	
	public Reproductie(String vraag, String antwoord, Set<String> trefwoord, int minAantalTrefWoorden, 
			List<String> hints, int maxAantalPogingen, int maxAntwoordTijd, OpdrachtCategorie categorie, Leraar auteur) {
		super(vraag, antwoord, hints, maxAantalPogingen, maxAntwoordTijd, categorie, auteur);
		// TODO Auto-generated constructor stub
		trefwoorden = trefwoord;
		minAantalJuisteTrefwoorden = minAantalTrefWoorden;
	}

	public Reproductie(String vraag, String antwoord, Set<String> trefwoord, int minAantalTrefWoorden, 
			List<String> hints, int maxAantalPogingen, int maxAntwoordTijd, OpdrachtCategorie categorie, Leraar auteur, String opmaakdatum) {
		super(vraag, antwoord, hints, maxAantalPogingen, maxAntwoordTijd, categorie, auteur, opmaakdatum);
		// TODO Auto-generated constructor stub
		trefwoorden = trefwoord;
		minAantalJuisteTrefwoorden = minAantalTrefWoorden;
	}
	
	public int aantalJuisteTrefWoorden(String tekst){
		int aantalTrefwoorden = 0;
		String [] woordPerWoord = tekst.split(" ");
		for (String woord : woordPerWoord){
			if (trefwoorden.contains(woord)){
				aantalTrefwoorden ++;
			}
		}
		return aantalTrefwoorden;
	}
	

}
