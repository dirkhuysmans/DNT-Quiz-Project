package model;

import java.sql.Time;
import java.util.List;
import java.util.Set;

import model.enumKlassen.Leraar;
import model.enumKlassen.OpdrachtCategorie;
import utils.gregorian.Datum;

public class Opsomming extends Opdracht{
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

}
