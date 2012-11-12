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
	
	Set<String> trefwoorden = new HashSet<String>();
	int minAantalJuisteTrefwoorden = 0;
	
	
	public Reproductie(String vraag, Set<String> trefwoorden, int minAantalTrefWoorden, 
			List<String> hints, int maxAantalPogingen, Time maxAntwoordTijd, OpdrachtCategorie categorie, Leraar auteur) {
		super(vraag, hints, maxAantalPogingen, maxAntwoordTijd, categorie, auteur);
		// TODO Auto-generated constructor stub
	}
	
	public int aantalJuisteTrefWoorden(String tekst){
		int trefwoordn = 0;
		String [] woordPerWoord = tekst.split(" ");
		for (String woord : woordPerWoord){
			if (trefwoorden.contains(woord)){
				trefwoordn += 1;
			}
		}
		return trefwoordn;
	}
	

}
