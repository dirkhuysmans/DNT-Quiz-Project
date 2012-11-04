package model;

/**
 * 
 * @author thijs
 *
 */

public interface Valideerbaar {
	boolean isValide (String antwoord);
	String getValideerTekst();
}
