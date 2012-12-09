package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Meerkeuze;
import model.Opdracht;
import model.OpdrachtCatalogus;
import model.Opsomming;
import model.enumKlassen.Leraar;
import model.enumKlassen.OpdrachtCategorie;

public class OpdrachtController {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		//Scanner sc = new Scanner(System.in);
		
		Map <Integer, String>hoofdStadBrazilie = new HashMap<Integer, String>();
		hoofdStadBrazilie.put(1, "Sao Paulo");
		hoofdStadBrazilie.put(2, "Rio de Janeiro");
		hoofdStadBrazilie.put(3, "Basilia");
		hoofdStadBrazilie.put(4, "Curitiba");
		String strHoofdsteden = "Sao Paulo; Rio de Janeiro; Basilia;Curitiba";
		List<String> hints1= new ArrayList<String>();
		hints1.add("Carnavalstad");
		String hints = "Carnavalstad";
		Opdracht opdracht1 = new Meerkeuze("Wat is de hoofdstad van brazilië?","Rio de Janeiro", strHoofdsteden,hints, 1,
				0, OpdrachtCategorie.algemeneKennis, Leraar.FRANK);
		OpdrachtCatalogus oc = new OpdrachtCatalogus();
		try {
			oc.voegOpdrachtToe(opdracht1);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Map <Integer, String>provinciesBelgie = new HashMap<Integer, String>();
		provinciesBelgie.put(1, "Vlaams-Brabant");
		provinciesBelgie.put(2, "Antwerpen");
		provinciesBelgie.put(3, "Limburg");
		provinciesBelgie.put(4, "Oost-Vlaanderen");
		provinciesBelgie.put(5, "West-Vlaanderen");
		provinciesBelgie.put(6, "Waals-Brabant");
		provinciesBelgie.put(7, "Luik");
		provinciesBelgie.put(8, "Henegouwen");
		provinciesBelgie.put(9, "Luxemburg");
		String provincies="Vlaams-Brabant; Antwerpen; Limburg; Oost-Vlaanderen; West-Vlaanderen; Waals-Brabant; Luik; Henegouwen; Luxemburg";
		List<String> hints2= new ArrayList<String>();
		hints2.add("Vlaanderen");
		hints2.add("Wallonie");
		String hint2="Vlaanderen; Wallonie";
		Opdracht opdracht2 = new Opsomming("Geef 3 Provincies van België",provincies, provincies, false, hint2, 1,
				0, OpdrachtCategorie.algemeneKennis, Leraar.FRANK);
		try {
			oc.voegOpdrachtToe(opdracht2);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			oc.wegschrijven();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			oc.lezen();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(oc.toString());
		

	}

}
