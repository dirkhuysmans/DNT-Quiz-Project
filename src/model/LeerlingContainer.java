package model;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import model.Leerling;
import model.FileContainer;

public class LeerlingContainer extends FileContainer implements PersisteerbaarAlsTekst{

	/**
	 * @author Dirk Huysmans
	 */
	
	private Map<Integer, Leerling> leerlingContainer = new HashMap<Integer, Leerling>();
	static int i=1;
	private ObjectOutputStream obj = null;
	
	/**
	 * Voegt een leerling toe, indien deze nog niet in de lijst opgenomen is
	 * @throws Exception 
	 */
	
	public void voegLeerlingToe(Leerling leerling) throws Exception{
		try{
			if(leerlingContainer.containsValue(leerling)){
				throw new Exception("Deze leerling bestaat al in de lijst.");
			}
			else{
				leerlingContainer.put(i, leerling);
				wegschrijvenNaarFile();
				i++;
			}
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage());		
		}		
	}
	
	
	/**
	 * Verwijder een leerling
	 */
	public void verwijderLeerling(int bijhorendNummer) throws Exception{
		try{
			if(leerlingContainer.containsKey(bijhorendNummer)){
				leerlingContainer.remove(bijhorendNummer);
				wegschrijvenNaarFile();
			}
			else{
				throw new Exception("Leerling werd niet in de lijst gevonden en kan dan ook niet verwijderd worden.");
			}
		}
		catch (Exception e){
			throw new Exception(e.getMessage());
		}		
	}
	
	/**
	 * Wijzig  naam leerling
	 */
	public void wijzigNaamLeerling(int bijhorendNummer, String nieuweNaam) throws Exception{
		try{
			if(leerlingContainer.containsKey(bijhorendNummer)){
				Leerling leerling = leerlingContainer.get(bijhorendNummer);
				leerling.setLeerlingNaam(nieuweNaam);
				wegschrijvenNaarFile();
			}
			else{
				throw new Exception("Leerling niet gevonden in de lijst. Naam kan bijgevolg niet aangepast worden.");
			}
		}
		catch (Exception e){
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * Wijzig leerjaar van leerling
	 */
	public void wijzigKlasVanLeerling(int bijhorendNummer, int nieuwLeerjaar) throws Exception{
		try{
			if(leerlingContainer.containsKey(bijhorendNummer)){
				Leerling leerling = leerlingContainer.get(bijhorendNummer);
				leerling.setLeerjaar(nieuwLeerjaar);
				wegschrijvenNaarFile();
			}
			else{
				throw new Exception("Leerling niet gevonden in de lijst. Leerjaar voor deze leerling kan bijgevolg niet aangepast worden.");
			}
		}
		catch (Exception e){
			throw new Exception(e.getMessage());
		}
	}
	
	@Override
	public String toString() {
		String LijstLeerlingen ="";
		for (Leerling leerling : leerlingContainer.values()) {
			LijstLeerlingen += leerling + "/n";
		}
		if(LijstLeerlingen==""){
			LijstLeerlingen = "De lijst met leerlingen is leeg.";
		}				
		return LijstLeerlingen;
	}
	
	public void wegschrijvenNaarFile() {
		try {
			obj = new ObjectOutputStream(new FileOutputStream("Leerlingen.ser"));
			obj.writeObject(leerlingContainer);
		} catch (IOException ex) {
			System.out.println("Error om naar de leerlingen-file te schrijven");
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
		int j=1;
		try {
			input = new ObjectInputStream(new FileInputStream("Leerlingen.ser"));
			@SuppressWarnings("unchecked")
			Map<Integer, Leerling> leerlingen = (Map<Integer, Leerling>) input.readObject();
			for (Leerling leerling : leerlingen.values()) {
				leerlingContainer.put(j, leerling);
				j++;
			}
		} catch (EOFException eofx) {
			System.out.println("End of file was reached " + eofx.getMessage());
			return;
		} catch (Exception ex) {
			System.out
					.println("Er is iets fout gegaan met het inlezen van de leerlingen "
							+ ex.getMessage());
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Er is iets fout gegaan bij het sluiten van het bestand " + e.getMessage());
			}
		}
	}	
	
	@Override
	public String getFile() {
		return "leerlingen.txt";}

	@Override
	public void wegschrijven() throws IOException, Exception {
		try {
			//aanmaken schrijfbuffer met meegeven bestandsnaam
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(getDirectory(),getFile())));
			for (Leerling lln:leerlingContainer.values()){
				bw.write(lln.getLeerlingNaam()+","+lln.getLeerjaar()+"\n");
			}
			bw.write("EINDE");
			bw.flush();
			bw.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			throw new IOException(e.getMessage());
		}
		catch (Exception e){
			throw new Exception(e.getMessage());
		}
		finally {
			
		}

		
	}

/*	@Override
	public void toevoegenLijn(String lijn) throws Exception {
		// TODO Auto-generated method stub
		String[] velden = lijn.split(",");
		try {
			maakObjectVanLijn(velden);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
	}
*/
	@Override
	public void maakObjectVanLijn(String[] velden) throws Exception {
		// TODO Auto-generated method stub
		String naam = velden[0];
		int leerjaar = Integer.parseInt(velden[1]);
		Leerling leerling = new Leerling(naam,leerjaar);
		try {
			this.voegLeerlingToe(leerling);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
	}

	}


