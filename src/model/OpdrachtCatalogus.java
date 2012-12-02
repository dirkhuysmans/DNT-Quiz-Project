package model;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

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
			catalogus = opdracht +"/n";
		}
		return catalogus;
	}
	
//	public String lezen(){
//		(OPDRACHTFILE);
//	}

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
		stringOpdracht = opdracht.getClass().getName() +"," + opdrachtenCatalogus.indexOf(opdracht) + "," + opdracht.getVraag() + "," + opdracht.getAntwoord() + "," + opdracht.getMaxAantalPogingen() + "," +opdracht.getMaxAntwoordTijd() +"," + opdracht.getCategorie() +"," + opdracht.getAuteur() + "," + opdracht.getOpmaakDatum() +",";
		int aantal=0;
		String bijkomend="";
		for (String hint:opdracht.getHints()){
			aantal++;
			if (bijkomend==""){
				bijkomend=hint;
			}
				else{	
					bijkomend+=hint;
			}
			
		}
		stringOpdracht += aantal +","+ bijkomend;
		aantal=0;
		bijkomend="";
		if(opdracht instanceof Meerkeuze){
			Meerkeuze meerkeuze = (Meerkeuze) opdracht;
			for (String keuze:meerkeuze.keuzes.values()){
				aantal++;
				if (bijkomend==""){
					bijkomend=keuze.indexOf(keuze) +"," + keuze;
				}
					else{	
						bijkomend=keuze.indexOf(keuze) +"," + keuze;
				}
				
			}
			stringOpdracht +=aantal + "," + bijkomend;
		}
		else if(opdracht instanceof Opsomming){
			Opsomming opsomming = (Opsomming) opdracht;
			for (String keuze:opsomming.opsommingLijst.values()){
				aantal++;
				if (bijkomend==""){
					bijkomend=keuze.indexOf(keuze) +"," + keuze;
				}
					else{	
						bijkomend=keuze.indexOf(keuze) +"," + keuze;
				}
				
			}
			stringOpdracht +=aantal + "," + bijkomend;
		}
		else if(opdracht instanceof Reproductie){
			Reproductie reproductie = (Reproductie) opdracht;
			for (String keuze:reproductie.trefwoorden){
				aantal++;
				if (bijkomend==""){
					bijkomend=keuze.indexOf(keuze) +"," + keuze;
				}
					else{	
						bijkomend=keuze.indexOf(keuze) +"," + keuze;
				}
				
			}
			stringOpdracht +=aantal + "," + bijkomend;
		}
		
		
		
		
			bw.write(stringOpdracht+"\n");
			
		}
		bw.write("EINDE");
		bw.flush();
		bw.close();

	}

	@Override
	public void maakObjectVanLijn(String[] velden) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
