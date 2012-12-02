package model;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import model.enumKlassen.Leraar;
import model.enumKlassen.OpdrachtCategorie;

/**
 * 
 * @author thijs
 *
 */
public class OpdrachtCatalogus extends FileContainer implements Iterable{
	
	private List<Opdracht> opdrachtenCatalogus = new ArrayList<Opdracht>();
	//private final static String OPDRACHTFILE = "Opdrachten.txt";
	private List<String> stringCatalogus = new ArrayList<String>();
	
	/**
	 * 
	 * @param opdracht
	 * 
	 * toevoegen en verwijderen van opdracht aan catalogus
	 */
	public void voegOpdrachtToe(Opdracht opdracht){
		opdrachtenCatalogus.add(opdracht);
		//schrijven(OPDRACHTFILE, toString());
	}
		
	public void verwijderOpdracht (int i){
		//lezen(OPDRACHTFILE);
		opdrachtenCatalogus.remove(i);
		//schrijven(OPDRACHTFILE, toString());
	}
	
	@Override
	public String toString(){
		String catalogus = "";
		for(Opdracht opdracht : opdrachtenCatalogus){
			catalogus += opdracht + "\n";
		}
		return catalogus;
	}
	
//	public String lezen(){
//		(OPDRACHTFILE);
//	}

	public  List<Opdracht> getOpdrachtenCatalogus(){
		return opdrachtenCatalogus;
	}
	
	@Override
	public Iterator iterator() {
		return opdrachtenCatalogus.iterator();
	}
	
	/*
	 * public void wegschrijvenNaarFile() {
	 
		try {
			obj = new ObjectOutputStream(new FileOutputStream("Opdrachten.ser"));
			obj.writeObject(opdrachtenCatalogus);
		} catch (IOException ex) {
			System.out.println("Error om naar de opdrachten-file te schrijven");
		} finally {
			try {
				obj.close();
			} catch (IOException iox) {
				System.out.println("probleem met het sluiten van de file "
						+ iox.getMessage());
			}
		}
	}

	public void lezenFile() {
		ObjectInputStream input = null;
		try {
			input = new ObjectInputStream(new FileInputStream("Opdrachten.ser"));
			Map<Integer, Opdracht> opdrachten = (Map<Integer, Opdracht>) input.readObject();
			int i = 1;
			for (Opdracht opdracht : opdrachten.values()) {
				opdrachtenCatalogus.add(opdracht);
				i++;
			}
		} catch (EOFException eofx) {
			System.out.println("End of file was reached " + eofx.getMessage());
			return;
		} catch (Exception ex) {
			System.out
					.println("Er is iets fout gegaan met het inlezen van de opdrachten "
							+ ex.getMessage());
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}*/

	public Opdracht getOpdracht(int k) throws IndexOutOfBoundsException{
		return opdrachtenCatalogus.get(k-1);		
	}
	public int getOpdrachtKey(Opdracht opdracht){
		return opdrachtenCatalogus.indexOf(opdracht);
	}

	@Override
	public void toevoegenLijn(String lijn) {
		stringCatalogus.add(lijn);		
	}
	
	
	public static void main(String[] args) {
		OpdrachtCatalogus catalogus = new OpdrachtCatalogus();
		boolean stop = false;
		//lezen(OPDRACHTFILE);
		Opdracht opdracht = null;
		while(!stop){
			
		}
		System.out.println(catalogus);
		System.out.println("Kies degene die weg moet: ");
		Scanner sc = new Scanner (System.in);
		int i = sc.nextInt();
		catalogus.verwijderOpdracht(i);
		System.out.println(catalogus);
	}

	@Override
	public String getFile() {
		// TODO Auto-generated method stub
		return "opdrachten.txt";
	}

	@Override
	public void wegschrijven() throws IOException, Exception {
		// TODO Auto-generated method stub
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(getDirectory(),getFile())));
		String	stringOpdracht="";
		for (Opdracht opdracht:opdrachtenCatalogus){
		stringOpdracht = opdracht.getClass().getName() +"," + getOpdrachtKey(opdracht) + "," + opdracht.getVraag() + "," + opdracht.getAntwoord() + "," + opdracht.getMaxAantalPogingen() + "," +opdracht.getMaxAntwoordTijd() +"," + opdracht.getCategorie() +"," + opdracht.getAuteur() + "," + opdracht.opmaakDatum.getDatumInEuropeesFormaat(opdracht.opmaakDatum) +",";
		int aantal=0;
		String bijkomend="";
		
		for (String hint:opdracht.getHints()){
		if(hint!=null){
			aantal++;
			if (bijkomend==""){
				bijkomend=hint;
			}
				else{	
					bijkomend+= "," + hint;
			}
		}	
		}
		
		stringOpdracht += "," + aantal +","+ bijkomend;
		aantal=0;
		bijkomend="";
		if(opdracht instanceof Meerkeuze){
			Meerkeuze meerkeuze = (Meerkeuze) opdracht;
			for (String keuze:meerkeuze.keuzes.values()){
				aantal++;
				if (bijkomend==""){
					//bijkomend=meerkeuze.keuzes.get(keuze) +"," + keuze;
					bijkomend=  keuze;
				}
					else{	
						//bijkomend += "," + meerkeuze.keuzes.get(keuze) +"," + keuze;
						bijkomend += "," + keuze;
				}
				
			}
			stringOpdracht += "," + aantal + "," + bijkomend;
		}
		else if(opdracht instanceof Opsomming){
			Opsomming opsomming = (Opsomming) opdracht;
			for (String keuze:opsomming.opsommingLijst.values()){
				aantal++;
				if (bijkomend==""){
					//bijkomend=opsomming.opsommingLijst.get(keuze) +"," + keuze;
					bijkomend= keuze;
				}
					else{	
						//bijkomend += ","+ opsomming.opsommingLijst.get(keuze) +"," + keuze;
						bijkomend += "," + keuze;
				}
				
			}
			stringOpdracht += "," + aantal + "," + bijkomend;
			stringOpdracht += "," + opsomming.getInJuisteVolgorde();
		}
		else if(opdracht instanceof Reproductie){
			Reproductie reproductie = (Reproductie) opdracht;
			for (String keuze:reproductie.trefwoorden){
				aantal++;
				if (bijkomend==""){
					bijkomend= keuze;
				}
					else{	
						bijkomend += "," + keuze;
				}
				
			}
			stringOpdracht += "," + aantal + "," + bijkomend;
			stringOpdracht += ","+ reproductie.minAantalJuisteTrefwoorden;
		}
		
		
		
		
			bw.write(stringOpdracht+"\n");
			
		}
		bw.write("EINDE");
		bw.flush();
		bw.close();

	}

	@SuppressWarnings("null")
	@Override
	public void maakObjectVanLijn(String[] velden) throws Exception {
		// TODO Auto-generated method stub
		String type = velden[0];
		int opdrachtId = Integer.parseInt(velden[1]);
		String vraag = velden[2];
		String antwoord = velden[3];
		int maxAantalPogingen= Integer.parseInt(velden[4]);
		int maxAntwoordTijd= Integer.parseInt(velden[5]);
		String cat = velden[6];
		OpdrachtCategorie categorie = OpdrachtCategorie.valueOf(cat); 
		String aut = velden[7];
		Leraar auteur = Leraar.valueOf(aut);
		String opmaakDatum = velden[8];
		int aantal = Integer.parseInt(velden[9]);
		int veldNummer = 11;
		List <String> hints = new ArrayList<String>();
		for (int i=1; i<aantal; i++) {
			hints.add(velden[veldNummer]);
			veldNummer++;
		}
		//aantal = Integer.parseInt(velden[veldNummer])*2;
		aantal = Integer.parseInt(velden[veldNummer]);
		veldNummer++;
		
		
		switch (type){
		case "Opdracht":
			
			break;
			
		case "Meerkeuze":
			Map<Integer, String> meerkeuze= null;
			for (int i=1; i<aantal; i++) {
				meerkeuze.put(i, velden[veldNummer+1]);
				veldNummer++;
			}	
			Meerkeuze mk = new Meerkeuze(vraag, antwoord, meerkeuze, hints, maxAantalPogingen, maxAntwoordTijd, categorie, auteur, opmaakDatum);
			this.opdrachtenCatalogus.add(opdrachtId,mk);
			break;
		case "Opsomming":
			Map<Integer, String> opsomming = null;
			for (int i=1; i<aantal; i++) {
				opsomming.put(i, velden[veldNummer]);
				veldNummer++;
			}	
			boolean inJuisteVolgorde = Boolean.parseBoolean(velden[veldNummer]);
			Opsomming os = new Opsomming(vraag, antwoord, opsomming, inJuisteVolgorde, hints, maxAantalPogingen, maxAntwoordTijd, categorie, auteur, opmaakDatum);
			this.opdrachtenCatalogus.add(opdrachtId, os);
			break;
			
		case "Reproductie":
			Set<String> trefwoorden = new HashSet<String>();
			for (int i=1; i<aantal; i++) {
				trefwoorden.add(velden[veldNummer]);
				veldNummer++;
			}	
			int aantalJuistTrefwoorden = Integer.parseInt(velden[veldNummer]);
			Reproductie reprod = new Reproductie(vraag, antwoord, trefwoorden, aantalJuistTrefwoorden, hints, maxAantalPogingen, maxAntwoordTijd, categorie, auteur, opmaakDatum);
			this.opdrachtenCatalogus.add(opdrachtId,reprod);
			break;
		
		default:
			break;
			
		}
		
	}
	
}
