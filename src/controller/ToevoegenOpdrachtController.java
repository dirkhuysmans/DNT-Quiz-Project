package controller;

import java.util.List;

import model.enumKlassen.Leraar;
import model.enumKlassen.OpdrachtCategorie;
import model.enumKlassen.QuizStatussen;
import persistenty.DaoFacade;
import utils.datum.Datum;
import view.IO;
import model.Opdracht;
import model.OpdrachtCatalogus;

public class ToevoegenOpdrachtController {
	private DaoFacade daoFacade;
	private OpdrachtCatalogus opdrachtCatalogus;
	private Opdracht opdracht;

	public ToevoegenOpdrachtController(DaoFacade daoFacade) {
		this.daoFacade = daoFacade;
		opdrachtCatalogus = new OpdrachtCatalogus();
	}

	public void maakOpdracht(String vraag, String antwoord, String hints,
			int maxAantalPogingen, int maxAntwoordTijd,
			OpdrachtCategorie categorie, Leraar auteur, Datum opmaakDatum)
			throws IllegalArgumentException, Exception {
		try {
			// opdracht = new Opdracht(vraag, antwoord);

			/*
			 * opdracht = new Opdracht(vraag, antwoord, hints,
			 * maxAantalPogingen, maxAntwoordTijd, categorie, auteur);
			 */
			daoFacade.createOpdracht(opdracht);
		} catch (IllegalArgumentException iaex) {
			IO.toonStringMetVenster(iaex + "");
			// e.printStackTrace();
		} catch (Exception ex) {
			IO.toonStringMetVenster(ex + "");
			// e.printStackTrace();
		}
	}

	
	  public List<Opdracht> getOpdrachten() { 
		  return opdrachtCatalogus.getOpdrachtenCatalogus(); 
		  }
	 
}
