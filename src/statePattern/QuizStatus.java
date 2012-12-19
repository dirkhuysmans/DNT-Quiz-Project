package statePattern;
/**
 * 
 * @author Noella
 *
 */
public abstract class QuizStatus {
	/**
	 * 
	 * @throws Exception	indien de status reeds inConstructie is
	 */
	protected void statusInConstructie() throws Exception {
		throw new Exception("De status kan niet gewijzigd worden naar inConstructie");
	}
	/**
	 * 
	 * @throws Exception	indien de status reeds afgewerkt is
	 */
	protected void statusAfgewerkt() throws Exception {
		throw new Exception("De status kan niet gewijzigd worden naar afgewerkt");
	}
	/**
	 * 
	 * @throws Exception	indien de status reeds opgengesteld is
	 */
	protected void statusOpengesteld() throws Exception {
		throw new Exception("De status kan niet gewijzigd worden naar opengesteld");	
	}
	/**
	 * 
	 * @throws Exception	indien de status reeds laatsteKans is
	 */
	protected void statusLaatsteKans() throws Exception {
		throw new Exception("De status kan niet gewijzigd worden naar laatsteKans");
	}
	/**
	 * 
	 * @throws Exception	indien status reeds afgesloten is
	 */
	protected void statusAfgesloten() throws Exception {
		throw new Exception("De status kan niet gewijzigd worden naar afgesloten");
	}
}
