package model;


import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author thijs
 *
 */
public class OpdrachtCatalogus implements Iterable{
	
	Map<Integer, Opdracht> opdrachtenCatalogus = new HashMap<Integer, Opdracht>();
	private ObjectOutputStream obj = null;
	static int i = 1;
	
	/**
	 * 
	 * @param opdracht
	 * 
	 * toevoegen en verwijderen van opdracht aan catalogus
	 */
	public void voegOpdrachtToe(Opdracht opdracht){
		lezenFile();
		opdrachtenCatalogus.put(i, opdracht);
		i++;
		wegschrijvenNaarFile();
	}
	
	public void verwijderOpdracht (int i){
		lezenFile();
		opdrachtenCatalogus.remove(i);
		wegschrijvenNaarFile();
	}
	
	@Override
	public String toString(){
		lezenFile();
		String catalogus = "";
		for(Opdracht opdracht : opdrachtenCatalogus.values()){
			catalogus = opdracht +"/n";
		}
		return catalogus;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void wegschrijvenNaarFile() {
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
				opdrachtenCatalogus.put(i, opdracht);
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
	}

	public Opdracht getOpdracht(int k) {
		return null;
		
		
	}
	
}
