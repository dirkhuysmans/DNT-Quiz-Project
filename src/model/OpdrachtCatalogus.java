package model;


import java.util.HashSet;
import java.util.Iterator;
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
	
}
