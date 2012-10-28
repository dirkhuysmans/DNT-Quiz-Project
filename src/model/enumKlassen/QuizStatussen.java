package model.enumKlassen;

/**
 * Deze enum bevat de mogelijke statussen van een quiz <br>
 * 0 = in construcite <br>
 * 1 = afgewerkt <br>
 * 2 = opgengesteld <br>
 * 3 = laatse kans <br>
 * 4 = afgesloten <br>
 * 
 * @author Michiels Noella
 * @version oktober 2012
 *
 */

public enum QuizStatussen {
	
	INCONSTRUCTIE(1), AFGEWERKT(2), OPENGESTELD(3), LAATSTEKANS(4), AFGESLOTEN(5);

    private final int id;
    QuizStatussen(int id) { 
    	this.id = id; 
    }
    public int getValue() { 
    	return id; 
    }

}
