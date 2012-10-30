package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeerlingContainer {

	/**
	 * @author Dirk Huysmans
	 */
	
	private Set<Leerling> LeerlingContainer = new HashSet<Leerling>();
	
	/**
	 * Voegt een leerling toe, indien deze nog niet in de lijst opgenomen is
	 * @throws Exception 
	 */
	
	public void voegLeerlingToe(Leerling leerling) throws Exception{
		try{
			if(LeerlingContainer.contains(leerling)){
				throw new Exception("Deze leerling bestaat al in de lijst.");
			}
			else{
				LeerlingContainer.add(leerling);
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
			if(LeerlingContainer.contains(leerling)){
				LeerlingContainer.remove(leerling);
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
			if(LeerlingContainer.contains(leerling)){
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
			if(LeerlingContainer.contains(leerling)){
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
		for (Leerling leerling : LeerlingContainer) {
			LijstLeerlingen += leerling + "/n";
		}
		if(LijstLeerlingen==""){
			LijstLeerlingen = "De lijst met leerlingen is leeg.";
		}
				
		return LijstLeerlingen;
	}
	
}
