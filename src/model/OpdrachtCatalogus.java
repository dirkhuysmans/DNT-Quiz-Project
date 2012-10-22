package model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author thijs
 *
 */
public class OpdrachtCatalogus {
	
	List<Opdracht> opdrachtenCatalogus = new ArrayList<Opdracht>();
	
	
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
	
}
