package model;

import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.enumKlassen.Leraar;
import model.enumKlassen.OpdrachtCategorie;

public class Reproductie extends Opdracht{
	public Reproductie(String vraag, List<String> hints, int maxAantalPogingen,
			Time maxAntwoordTijd, OpdrachtCategorie categorie, Leraar auteur) {
		super(vraag, hints, maxAantalPogingen, maxAntwoordTijd, categorie, auteur);
		// TODO Auto-generated constructor stub
	}
	Set<String> trefwoorden = new HashSet<String>();
	int minAantalJuisteTrefwoorden = 0;
	int aantalJuisteTrefwoorden = 0;

}
