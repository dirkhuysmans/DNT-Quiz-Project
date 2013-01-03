package model;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
import utils.datum.Datum;
import model.enumKlassen.Leraar;
import model.enumKlassen.OpdrachtCategorie;

/**
 * 
 * @author thijs, aangepast door Dirk Huysmans
 *
 */

public class Reproductie extends Opdracht{
		
	private static final long serialVersionUID = 1L;
	String trefwoorden;
	int minAantalJuisteTrefwoorden = 0;
	
	/**
	 * @author Dirk Huysmans
	 * @param vraag
	 * @param antwoord
	 * @param trefwoorden
	 * @param minAantalTrefWoorden
	 * @param hints
	 * @param maxAantalPogingen
	 * @param maxAntwoordTijd
	 * @param categorie
	 * @param auteur
	 * @param opmaakdatum
	 */
	public Reproductie(String vraag, String antwoord, String trefwoorden, int minAantalTrefWoorden, 
			String hints, int maxAantalPogingen, Time maxAntwoordTijd, OpdrachtCategorie categorie, Leraar auteur) {
		super(vraag, antwoord, hints, maxAantalPogingen, maxAntwoordTijd, categorie, auteur);
		// TODO Auto-generated constructor stub
		this.trefwoorden = trefwoorden;
		this.minAantalJuisteTrefwoorden = minAantalTrefWoorden;
		setOpmaakDatum(new Datum());
	}
	

	public void setTrefwoorden(String trefwoorden) {
		this.trefwoorden = trefwoorden;
	}

	public void setMinAantalJuisteTrefwoorden(int minAantalJuisteTrefwoorden) {
		this.minAantalJuisteTrefwoorden = minAantalJuisteTrefwoorden;
	}

	public String getTrefwoorden() {
		return trefwoorden;
	}

	public Map<Integer, String>  getLijstTrefwoorden(){
		String[] velden = this.trefwoorden.split(";");
		Map <Integer, String> lijstTrefwoorden = new HashMap <Integer, String>();
		int i = 0;
		for (String trefwoord : velden) {
			i++;
			lijstTrefwoorden.put(i, trefwoord);
		}
		return lijstTrefwoorden;
	}

	public int getMinAantalJuisteTrefwoorden() {
		return minAantalJuisteTrefwoorden;
	}
	
	public int aantalJuisteTrefWoorden(String tekst){
		int aantalTrefwoorden = 0;
		String [] woordPerWoord = tekst.split(" ");
		
		for (String woord : woordPerWoord){
			if (getLijstTrefwoorden().containsValue(woord)){
				aantalTrefwoorden ++;
			}
		}
		return aantalTrefwoorden;
	}
}