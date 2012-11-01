package model;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author thijs
 *
 */
public class OpdrachtCatalogus implements Iterable{
	
	Set<Opdracht> opdrachtenCatalogus = new HashSet<Opdracht>();
	
	
	/**
	 * 
	 * @param opdracht
	 * 
	 * toevoegen en verwijderen van opdracht aan catalogus
	 */
	public void voegOpdrachtToe(Opdracht opdracht){
		opdrachtenCatalogus.add(opdracht);
	}
	
	public void verwijderOpdracht (Opdracht opdracht){
		opdrachtenCatalogus.remove(opdracht);
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
		// TODO Auto-generated method stub
		return null;
	}
	
	public void wegschrijvenNaarFile (Opdracht opdracht){
		FileOutputStream file = null; 
		ObjectOutputStream obj = null;
		try{
			file = new FileOutputStream("Opdrachten.ser");
			obj = new ObjectOutputStream(file);
			obj.writeObject(opdracht);
		}catch (IOException ex){
			System.out.println("Error om naar de opdrachten-file te schrijven");
		}
	}
	
	public void lezenFile(){
		FileInputStream file = null;
		ObjectInputStream obj = null;
		try{
			file = new FileInputStream("Opdrachten.ser");
			obj = new ObjectInputStream(file);
			List<Opdracht> opdrachten = (List<Opdracht>) obj.readObject();
		}catch (Exception ex){
			System.out.println("Er is iets fout gegaan met het inlezen van de opdrachten " + ex.getMessage());
		}
	}
	
}
