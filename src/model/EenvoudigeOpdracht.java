/*package model;

import java.util.List;

import model.enumKlassen.Leraar;
import model.enumKlassen.OpdrachtCategorie;

public class EenvoudigeOpdracht extends Opdracht{

	private static final long serialVersionUID = 1L;

	public EenvoudigeOpdracht(String vraag, String antwoord) {
		super(vraag, antwoord);
		
	}

	public EenvoudigeOpdracht(String vraag, String antwoord,
			List<String> hints, int maxAantalPogingen, int maxAntwoordTijd,
			OpdrachtCategorie categorie, Leraar auteur, String opmaakDatum) {
		super(vraag, antwoord, hints, maxAantalPogingen, maxAntwoordTijd, categorie,
				auteur);
		// TODO Auto-generated constructor stub
	}

}
*/
package model;

import java.sql.Time;

import utils.datum.Datum;
import model.enumKlassen.Leraar;
import model.enumKlassen.OpdrachtCategorie;

public class EenvoudigeOpdracht extends Opdracht{
/*
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	public EenvoudigeOpdracht(String vraag, String antwoord){
		super(vraag, antwoord);
	}
	
	public EenvoudigeOpdracht(String vraag, String antwoord,
			String hints, int maxAantalPogingen, Time maxAntwoordTijd,
			OpdrachtCategorie categorie, Leraar auteur) {		
		super(vraag, antwoord, hints, maxAantalPogingen, maxAntwoordTijd, categorie,
				auteur);
		setOpmaakDatum(new Datum());
		// TODO Auto-generated constructor stub
	}

	
}