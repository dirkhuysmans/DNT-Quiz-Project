package model;

import java.sql.Time;
import java.util.List;
import java.util.Set;

import model.enumKlassen.Leraar;
import model.enumKlassen.OpdrachtCategorie;
import utils.gregorian.Datum;

public class Meerkeuze extends Opdracht implements Valideerbaar{
	private Set<String> antwoord;

	public void setAntwoord(Set<String> antwoord) {
		this.antwoord = antwoord;
	}

	public Meerkeuze(String vraag, Set<String> antwoord, List<String> hints, int maxAantalPogingen,
			Time maxAntwoordTijd, OpdrachtCategorie categorie, Leraar auteur) {
		super(vraag, hints, maxAantalPogingen, maxAntwoordTijd, categorie, auteur);
		setAntwoord(antwoord);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValide(String antwoord) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getValideerTekst() {
		// TODO Auto-generated method stub
		return null;
	}

}
