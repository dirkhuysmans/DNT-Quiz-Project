package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
		List<String> hints1= new ArrayList<String>();
		hints1.add("Carnavalstad");
		Opdracht opdracht1 = new Meerkeuze("Wat is de hoofdstad van brazilië?","Rio de Janeiro", hoofdStadBrazilie,hints1, 1,
				0, OpdrachtCategorie.algemeneKennis, Leraar.FRANK);
		OpdrachtCatalogus oc = new OpdrachtCatalogus();
		oc.voegOpdrachtToe(opdracht1);
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
		List<String> hints2= new ArrayList<String>();
		hints2.add("Vlaanderen");
		hints2.add("Wallonie");
		Opdracht opdracht2 = new Opsomming("Geef 3 Provincies van België","", provinciesBelgie, false, hints2, 1,
				0, OpdrachtCategorie.algemeneKennis, Leraar.FRANK);
		oc.voegOpdrachtToe(opdracht2);
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
