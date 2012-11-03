package model;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeerlingContainer {

	/**
	 * @author Dirk Huysmans
	 */
	
	private Set<Leerling> leerlingContainer = new HashSet<Leerling>();
	private ObjectOutputStream obj = null;
	/**
	 * Voegt een leerling toe, indien deze nog niet in de lijst opgenomen is
	 * @throws Exception 
	 */
	
	public void voegLeerlingToe(Leerling leerling) throws Exception{
		try{
			if(leerlingContainer.contains(leerling)){
				throw new Exception("Deze leerling bestaat al in de lijst.");
			}
			else{
				leerlingContainer.add(leerling);
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
	public void verwijderLeerling(Leerling leerling) throws Exception{
		try{
			if(leerlingContainer.contains(leerling)){
				leerlingContainer.remove(leerling);
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
	public void wijzigNaamLeerling(Leerling leerling, String nieuweNaam) throws Exception{
		try{
			if(leerlingContainer.contains(leerling)){
				leerling.setLeerlingNaam(nieuweNaam);
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
	public void wijzigKlasVanLeerling(Leerling leerling, int nieuwLeerjaar) throws Exception{
		try{
			if(leerlingContainer.contains(leerling)){
				leerling.setLeerjaar(nieuwLeerjaar);
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
		for (Leerling leerling : leerlingContainer) {
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
		try {
			input = new ObjectInputStream(new FileInputStream("Leerlingen.ser"));
			Set<Leerling> leerlingen = (Set<Leerling>) input.readObject();
			for (Leerling leerling : leerlingen) {
				leerlingContainer.add(leerling);
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
				e.printStackTrace();
			}
		}
	}
	
}
