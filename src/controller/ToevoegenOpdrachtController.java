package controller;

import java.sql.Time;
import java.util.ArrayList;
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
			int maxAantalPogingen, Time maxAntwoordTijd,
			OpdrachtCategorie categorie, Leraar auteur, Datum opmaakDatum)
			throws IllegalArgumentException, Exception {
		try {
			opdracht = new Opdracht(vraag, antwoord, hints, maxAantalPogingen,
					maxAntwoordTijd, categorie, auteur);

			daoFacade.createOpdracht(opdracht);
		} catch (IllegalArgumentException iaex) {
			IO.toonStringMetVenster(iaex.getMessage() + "");
			// e.printStackTrace();
		} catch (Exception ex) {
			IO.toonStringMetVenster(ex.getMessage() + "");
			// e.printStackTrace();
		}
	}

	public List<Opdracht> getAlleOpdrachten() {
		try {
			return opdrachtCatalogus.getOpdrachtenCatalogus();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
			return null;
		}
	}
	
	public List<Opdracht> getOpdrachtenPerType(String geselecteerdType) throws Exception{		
		return daoFacade.selectOpdrachtenPerType(geselecteerdType);
	}

	public List<String> getOpdrachtenTypes() {
		List<String> types = new ArrayList();
		types.add("Meerkeuze");
		types.add("Opsomming");
		types.add("Eenvoudige Opdracht");
		types.add("Reproductie");
		return types;
	}
}
