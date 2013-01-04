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
import model.EenvoudigeOpdracht;
import model.Meerkeuze;
import model.Opdracht;
import model.OpdrachtCatalogus;
import model.Opsomming;
import model.Reproductie;

public class ToevoegenOpdrachtController {
	private DaoFacade daoFacade;
	private OpdrachtCatalogus opdrachtCatalogus;
	private Opdracht opdracht;

	public ToevoegenOpdrachtController(DaoFacade daoFacade) {
		this.daoFacade = daoFacade;
		opdrachtCatalogus = new OpdrachtCatalogus();
	}

	public void maakOpdracht(String vraag, String antwoord, String meerkeuze, boolean juisteVolgorde, int minAantalTrefWoorden, String hints, 
			int maxAantalPogingen, Time maxAntwoordTijd, OpdrachtCategorie categorie, Leraar auteur, Datum opmaakDatum, String type)
			throws IllegalArgumentException, Exception {
		try {
			if(type.equals("EevoudigeOpdracht")){
				opdracht = new EenvoudigeOpdracht(vraag, antwoord, hints, maxAantalPogingen,
						maxAntwoordTijd, categorie, auteur);
			}else if(type.equals("Meerkeuze")){
				opdracht = new Meerkeuze(vraag, antwoord, meerkeuze, hints, maxAantalPogingen,
						maxAntwoordTijd, categorie, auteur);
			}else if(type.equals("Opsomming")){
				opdracht = new Opsomming(vraag, antwoord, juisteVolgorde, hints, maxAantalPogingen,
						maxAntwoordTijd, categorie, auteur);
			}else if(type.equals("Reproductie")){
				opdracht = new Reproductie(vraag, antwoord, minAantalTrefWoorden, hints, maxAantalPogingen,
						maxAntwoordTijd, categorie, auteur);
			}else{opdracht = null;}
			
			if(opdracht != null){
				daoFacade.createOpdracht(opdracht);
			}			
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

	public List<String> getCategorieen() {
		List<String> categorieen = new ArrayList<String>();
		categorieen.add(OpdrachtCategorie.algemeneKennis.toString());
		categorieen.add(OpdrachtCategorie.NederlandseTaal.toString());
		categorieen.add(OpdrachtCategorie.FranseTaal.toString());
		categorieen.add(OpdrachtCategorie.rekenen.toString());
		return categorieen;
	}

	public List<Opdracht> getOpdrachtenPerCategorie(String geselecteerdType) throws Exception {
		return daoFacade.selectOpdrachtenPerCategorie(geselecteerdType);
	}
}
