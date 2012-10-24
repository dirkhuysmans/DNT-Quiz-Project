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
	inConstructie,afgewerkt,opengesteld,laatsteKans,afgesloten;
	
	public static QuizStatussen convertInt(int x){
		x = x-1;
		switch (x){
		case 0: 
			return inConstructie;
		case 1: 
			return afgewerkt;
			
		case 2:
			return opengesteld;
		case 3: 
			return laatsteKans;
		case 4: 
			return afgesloten;
		}
		return null;
	}
}
