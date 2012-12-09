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
			String hints, int maxAantalPogingen, int maxAntwoordTijd,
			OpdrachtCategorie categorie, Leraar auteur, Datum opmaakDatum) {
		super(vraag, antwoord, hints, maxAantalPogingen, maxAntwoordTijd, categorie,
				auteur, opmaakDatum);
		// TODO Auto-generated constructor stub
	}

}