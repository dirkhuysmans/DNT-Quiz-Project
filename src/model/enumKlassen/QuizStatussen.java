package model.enumKlassen;

/**
 * Deze enum bevat de mogelijke statussen van een quiz <br>
 * 1 = in construcite <br>
 * 2 = afgewerkt <br>
 * 3 = opgengesteld <br>
 * 4 = laatse kans <br>
 * 5 = afgesloten <br>
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
