package model;


import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * @author thijs
 *
 */
public class OpdrachtCatalogus extends FileContainer implements Iterable{
	
	private List<Opdracht> opdrachtenCatalogus = new ArrayList<Opdracht>();
	private final String OPDRACHTFILE = "Opdrachten.txt";
	private List<String> catalogus = new ArrayList<String>();
	
	/**
	 * 
	 * @param opdracht
	 * 
	 * toevoegen en verwijderen van opdracht aan catalogus
	 */
	public void voegOpdrachtToe(Opdracht opdracht){
		opdrachtenCatalogus.add(opdracht);
		schrijven(OPDRACHTFILE, toString());
	}
	
	public void verwijderOpdracht (int i){
		lezen(OPDRACHTFILE);
		opdrachtenCatalogus.remove(i);
		schrijven(OPDRACHTFILE, toString());
	}
	
	@Override
	public String toString(){
		String catalogus = "";
		for(Opdracht opdracht : opdrachtenCatalogus){
			catalogus = opdracht +"/n";
		}
		return catalogus;
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

	@Override
	public void toevoegenLijn(String lijn) {
		catalogus.add(lijn);		
	}
	
	
	public static void main(String[] args) {
		OpdrachtCatalogus catalogus = new OpdrachtCatalogus();
		boolean stop = false;
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
	
}
