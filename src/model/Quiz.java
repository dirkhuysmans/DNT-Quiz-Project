package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import statePattern.AfgeslotenStatus;
import statePattern.AfgewerktStatus;
import statePattern.InConstructieStatus;
import statePattern.LaatsteKansStatus;
import statePattern.OpengesteldStatus;
import statePattern.QuizStatus;
import utils.datum.Datum;
import model.enumKlassen.Leraar;
import model.enumKlassen.QuizStatussen;

/**
 * Deze klasse wordt gebruikt voor het aanmaken van een Quiz
 * 
 * @author Noella
 * 
 */
public class Quiz implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public class QuizComparator {

	}

	//
	// Attributen
	//
	private String onderwerp;
	private int minLeerjaar, maxLeerjaar;
	private boolean isTest;
	private boolean isUniekeDeelname;
	//private QuizStatussen quizStatus;
	private Leraar auteur;
	private Datum datumRegistratie;
	private List<QuizOpdracht> quizOpdrachten = new ArrayList<QuizOpdracht>();
	//
	// voor het gebruik van het state-pattern
	//
	QuizStatus inConstructieStatus;
	QuizStatus afgewerktStatus;
	QuizStatus opengesteldStatus;
	QuizStatus laatsteKansStatus;
	QuizStatus afgeslotenStatus;
	
	QuizStatus quizStatus ;
	//
	// Constructor
	//
	/**
	 * 
	 * @param onderwerp		onderwerp van de quiz
	 */
	public Quiz(String onderwerp) {
		this.onderwerp = onderwerp;
	}
	/**
	 * @param onderwerp				onderwerp van de quiz
	 * @param leerJaar				formuleren voor welk leeljaar de quiz bestemd is (min 1 max 6)
	 * @param isUniekeDeelname		true indien quiz meerdere malen mag opgelost worden,false indien quiz 1 maal mag opgelost worden
	 * @param isTest				true indien geregistreerd als test (leerling kan maar 1 keer deelnemen)
	 * @param opdracht				opdracht is een object van het type Opdracht
	 * @param quizStatus			de status waarin de quiz zich bevindt
	 */
	public Quiz(String onderwerp, int minLeerjaar,int maxLeerjaar, boolean isUniekeDeelname,boolean isTest) {

		this.onderwerp = controleInhoudVeld(onderwerp);
		this.minLeerjaar = controleLeerjaar(minLeerjaar);
		this.maxLeerjaar = controleLeerjaar(maxLeerjaar);
		this.isTest = isTest;
		this.isUniekeDeelname = isUniekeDeelname;
		if (isTest) { // indien quiz in testfase
			isUniekeDeelname = false;
		}
		//this.quizStatus = QuizStatussen.INCONSTRUCTIE;
		this.datumRegistratie = new Datum();
		this.auteur = Leraar.MYRIAM;
		//
		// voor het status-pattern
		//
		inConstructieStatus = new InConstructieStatus(this);
		afgewerktStatus = new AfgewerktStatus(this);
		opengesteldStatus = new OpengesteldStatus(this);
		laatsteKansStatus = new LaatsteKansStatus(this);
		afgeslotenStatus = new AfgeslotenStatus(this);
		quizStatus = inConstructieStatus;
	}
	
	/**
	 * 
	 * @param onderwerp
	 * @param minLeerJaar
	 * @param isUniekeDeelname
	 * @param isTest
	 * @param quizStatus
	 * @param datumRegistratie
	 * @param auteur
	 */
	public Quiz(String onderwerp, int minLeerJaar, boolean isUniekeDeelname,boolean isTest, QuizStatus quizStatus, Datum datumRegistratie ,Leraar auteur){
		this.onderwerp = controleInhoudVeld(onderwerp);
		this.minLeerjaar = controleLeerjaar(minLeerJaar);
		this.maxLeerjaar = this.minLeerjaar;
		this.isTest = isTest;
		this.isUniekeDeelname = isUniekeDeelname;
		if (isTest) { // indien quiz in testfase
			isUniekeDeelname = true;
		}
		//this.quizStatus = quizStatus;
		this.datumRegistratie = datumRegistratie;
		this.auteur = auteur;
		//
		// voor het status-pattern
		//
		inConstructieStatus = new InConstructieStatus(this);
		afgewerktStatus = new AfgewerktStatus(this);
		opengesteldStatus = new OpengesteldStatus(this);
		laatsteKansStatus = new LaatsteKansStatus(this);
		afgeslotenStatus = new AfgeslotenStatus(this);
		quizStatus = quizStatus;
	}

	/**
	 * 
	 * @param onderwerp
	 * @param minLeerJaar
	 * @param maxLeerJaar
	 * @param isUniekeDeelname
	 * @param isTest
	 * @param quizStatus
	 * @param datumRegistratie
	 * @param auteur
	 */
	public Quiz(String onderwerp, int minLeerJaar, int maxLeerJaar, boolean isUniekeDeelname,boolean isTest, QuizStatus quizStatus, Datum datumRegistratie ,Leraar auteur){
		this.onderwerp = controleInhoudVeld(onderwerp);
		this.minLeerjaar = controleLeerjaar(minLeerJaar);
		this.maxLeerjaar = controleLeerjaar(maxLeerJaar);
		this.isTest = isTest;
		this.isUniekeDeelname = isUniekeDeelname;
		if (isTest) { // indien quiz in testfase
			isUniekeDeelname = true;
		}
		//this.quizStatus = quizStatus;
		this.datumRegistratie = datumRegistratie;
		this.auteur = auteur;
		//
		// voor het status-pattern
		//
		inConstructieStatus = new InConstructieStatus(this);
		afgewerktStatus = new AfgewerktStatus(this);
		opengesteldStatus = new OpengesteldStatus(this);
		laatsteKansStatus = new LaatsteKansStatus(this);
		afgeslotenStatus = new AfgeslotenStatus(this);
		quizStatus = quizStatus;
	}
	/**
	 * 
	 * @param onderwerp
	 * @param minLeerJaar
	 * @param maxLeerJaar
	 * @param isUniekeDeelname
	 * @param isTest
	 * @param quizStatus
	 * @param auteur
	 */
	public Quiz(String onderwerp, int minLeerJaar, int maxLeerJaar, boolean isUniekeDeelname,boolean isTest, QuizStatus quizStatus,Leraar auteur){
		this.onderwerp = controleInhoudVeld(onderwerp);
		this.minLeerjaar = controleLeerjaar(minLeerJaar);
		this.maxLeerjaar = controleLeerjaar(maxLeerJaar);
		this.isTest = isTest;
		this.isUniekeDeelname = isUniekeDeelname;
		if (isTest) { // indien quiz in testfase
			isUniekeDeelname = true;
		}
		//this.quizStatus = quizStatus;
		this.datumRegistratie = new Datum();
		this.auteur = auteur;
		//
		// voor het status-pattern
		//
		inConstructieStatus = new InConstructieStatus(this);
		afgewerktStatus = new AfgewerktStatus(this);
		opengesteldStatus = new OpengesteldStatus(this);
		laatsteKansStatus = new LaatsteKansStatus(this);
		afgeslotenStatus = new AfgeslotenStatus(this);
		quizStatus = quizStatus;
	}
	public Quiz(String onderwerp, int minLeerJaar, int maxLeerJaar, boolean isUniekeDeelname,boolean isTest, String status,Leraar auteur){
		this.onderwerp = controleInhoudVeld(onderwerp);
		this.minLeerjaar = controleLeerjaar(minLeerJaar);
		this.maxLeerjaar = controleLeerjaar(maxLeerJaar);
		this.isTest = isTest;
		this.isUniekeDeelname = isUniekeDeelname;
		if (isTest) { // indien quiz in testfase
			isUniekeDeelname = true;
		}
		//this.quizStatus = quizStatus;
		this.datumRegistratie = new Datum();
		this.auteur = auteur;
		//
		// voor het status-pattern
		//
		inConstructieStatus = new InConstructieStatus(this);
		afgewerktStatus = new AfgewerktStatus(this);
		opengesteldStatus = new OpengesteldStatus(this);
		laatsteKansStatus = new LaatsteKansStatus(this);
		afgeslotenStatus = new AfgeslotenStatus(this);
		
		quizStatus = getSelectedQuizSatus(status);
	}
	//
	// Getters
	//
	public String getOnderwerp() {
		return onderwerp;
	}

	public int getMinLeerjaar() {
		return minLeerjaar;
	}
	
	public int getMaxLeerjaar(){
		return maxLeerjaar;
	}

	public boolean isTest() {
		return isTest;
	}

	public boolean isUniekeDeelname() {
		return isUniekeDeelname;
	}

	public Leraar getLeraar() {
		
		return auteur;
	}

	public Datum getDatum() {
		return datumRegistratie;
	}

	public List<QuizOpdracht> getQuizOpdracht() {
		return quizOpdrachten;
	}

	//
	// Setters
	//
	public void setOnderwerp(String onderwerp) {
		this.onderwerp = onderwerp;
	}

	public void setMinLeerjaar(int leerJaar) {
		this.minLeerjaar = controleLeerjaar(leerJaar);
	}

	public void setTest(boolean isTest) {
		this.isTest = isTest;
	}

	public void setUniekeDeelname(boolean isUniekeDeelname) {
		this.isUniekeDeelname = isUniekeDeelname;
	}

	//public void setQuizStatus(QuizStatussen quizStatus) {
	//	this.quizStatus = quizStatus;
	//}

	public void setLeraar(Leraar auteur) {
		this.auteur = auteur;
	}

	public void setDatum(Datum datumRegistratie) {
		this.datumRegistratie = datumRegistratie;
	}

	public void setQuizOpdracht(List<QuizOpdracht> quizOpdracht) {
		this.quizOpdrachten = quizOpdracht;
	}

	//
	// Methodes
	//
	private QuizStatus getSelectedQuizSatus(String quizStatus){
		if(quizStatus.equals("inConstructie")){
			return this.getInConstructieStatus();
		}
		else{
			if(quizStatus.equals("afgewerkt")){
				return this.getAfgewerktStatus();
			}
			else{
				if(quizStatus.equals("opengesteld")){
					return this.getOpengesteldStatus();
				}
				else{
					if(quizStatus.equals("laatsteKans")){
						return this.getLaatsteKansStatus();
					}
					else{
						if(quizStatus.equals("afgesloten")){
							return this.getAfgeslotenStatus();
						}
						else{
							return null;
						}
					}
				}
			}
		}
	}
	/**
	 * methode om het leerJaar op een geldige waarde te controleren <br>
	 * waarde min 1 en max 6
	 * 
	 * @param leerJaar
	 *            het te controleren leerjaar
	 * 
	 * @return leerjaar geeft de waarde van het leerjaar terug indien correct
	 * 
	 * @author Noella
	 */
	public int controleLeerjaar(int leerJaar) {
		if (leerJaar > 0 && leerJaar < 7) {
			return leerJaar;
		}
		throw new IllegalArgumentException(
				"leerJaar moet tussen 01 en 6 liggen");

	}
	/**
	 * Deze methode controleert of een veld (String) ingevuld is
	 * 
	 * @param veldTeControleren		het te controleren veld moet ingevuld zijn
	 * @return						de ingevulde waarde
	 * 
	 * @author Noella
	 */
	public String controleInhoudVeld(String veldTeControleren){
		if(veldTeControleren!= null && !veldTeControleren.equals("")){
			return veldTeControleren;
		} else {
			throw new IllegalArgumentException("Onderwerp moet ingevuld worden !!!!!");
		}
	}
	/**
	 * voeg een quizOpdracht toe
	 * 
	 * @param quizOpdracht		quizOpdracht is een object van het type QuizOpdracht
	 * 
	 * @author Noella
	 * @throws Exception 
	 */
	protected void voegQuizOpdrachtToe(QuizOpdracht quizOpdracht) throws Exception {
		try{
			for(int i = 0; i < this.quizOpdrachten.size(); i ++){
				if(this.quizOpdrachten.get(i).getOpdracht().getVraag().contentEquals(quizOpdracht.getOpdracht().getVraag())){
					throw new Exception("Deze Opdracht bestaat reeds.");
				}
			}
			quizOpdrachten.add(quizOpdracht);
		}
		catch (Exception e){
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * verwijderen van een quizOpdracht
	 * 
	 * @param quizOpdracht			quizOpdracht is een object van het type QuizOpdracht
	 * 
	 * @author Noella
	 */
	protected void verwijderQuizOpdracht(QuizOpdracht quizOpdracht) throws Exception{
		try{
			for(int i = 0; i < this.quizOpdrachten.size(); i ++){
				if(!this.quizOpdrachten.get(i).getOpdracht().getVraag().contentEquals(quizOpdracht.getOpdracht().getVraag())){
					throw new Exception("Deze Opdracht bestaat niet.");
				}
			}
			quizOpdrachten.remove(quizOpdracht);
		}
		catch (Exception e){
			throw new Exception(e.getMessage());
		}
		
		
	}

	/**
	 * maakt een lijst van alle bestaande opdrachten
	 * 
	 * @return lijst met bestaande opdrachten
	 */
	public ArrayList<Opdracht> getOpdrachten() {
		ArrayList<Opdracht> opdrachten = new ArrayList<Opdracht>();
		for (QuizOpdracht quizOpdracht : quizOpdrachten) {
			opdrachten.add(quizOpdracht.getOpdracht());
		}
		return opdrachten;
	}

	/**
	 * zoekt een bepaalde opdracht in een lijst
	 * 
	 * @param volgnr
	 *            om een opdracht op die plaats in de lijst te halen
	 * @return een bepaalde opdracht
	 * 
	 * @author Noella
	 */
	public QuizOpdracht getOpdracht(int volgnr) {
		return quizOpdrachten.get(volgnr - 1);
	}

	/*
	 * override van de String-methode
	 */

	/**
	 * Comparator klasse geschreven als inner class omdat die enkel zal gebruikt worden voor de Quiz klasse
	 * dit is gebeurd op basis van het aantal opdrachten dat een klasse  bezit, als het aantal opdrachten gelijk is
	 * moet er op het onderwerp alfabetisch gesorteerd worden. 
	 * @return
	 */
	
	public static Comparator<Quiz> QuizComparator() {
		return new Comparator<Quiz>() {
			@Override
			public int compare(Quiz quiz1, Quiz quiz2) {
				if (quiz1.compareTo(quiz2) == 0) {
					return 0;
				} else if (quiz1.getOpdrachten().size() == quiz2
						.getOpdrachten().size()) {
					return (quiz1.getOnderwerp()
							.compareTo(quiz2.getOnderwerp()));
				} else if (quiz1.getOpdrachten().size() < quiz2.getOpdrachten()
						.size()) {
					return -1;
				} else {
					return 1;
				}
			}
		};
	}
	
	public String voorTreeMap(){
		return ("Aantal opdrachten: " + getOpdrachten().size() + "\n\t " + getOnderwerp());
	}

	protected int compareTo(Quiz quiz) {
		if (this.equals(quiz)) {
			return 0;
		} else if (this.hashCode() < quiz.hashCode()) {
			return -1;
		} else
			return 1;
	}
	//
	// voor het gebruik van statepattern
	//
	public QuizStatus getInConstructieStatus() {
		return inConstructieStatus;
	}
	public QuizStatus getAfgewerktStatus() {
		return afgewerktStatus;
	}
	public QuizStatus getOpengesteldStatus() {
		return opengesteldStatus;
	}
	public QuizStatus getLaatsteKansStatus() {
		return laatsteKansStatus;
	}
	public QuizStatus getAfgeslotenStatus() {
		return afgeslotenStatus;
	}
	public QuizStatus getQuizStatus() {
		return quizStatus;
	}
	//
	//
	//

	public void setQuizStatus(QuizStatus quizStatus){
		this.quizStatus = quizStatus;
	}
	@Override
	public String toString() {
		String tekst = "";
		if (isTest) {
			tekst = ", deze quiz is een test ";
		} else {
			tekst = ", deze quiz is geen test ";
		}
		if (isUniekeDeelname) {
			tekst += "en heeft een uniek deelname,";
		} else {
			tekst += "en heeft geen unieke deelname,";
		}
		tekst += " de status van de quiz is ";
		String aangemaakteQuiz = "";
		aangemaakteQuiz += "Quiz : " + onderwerp + "\n" + "bedoeld voor het "
				+ minLeerjaar + " e leerjaar, aangemaakt op "
				+ datumRegistratie.getDatumInEuropeesFormaat(datumRegistratie)
				+ " door " + auteur + tekst + quizStatus;
		return aangemaakteQuiz;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((auteur == null) ? 0 : auteur.hashCode());
		result = prime * result + (isTest ? 1231 : 1237);
		result = prime * result + (isUniekeDeelname ? 1231 : 1237);
		result = prime * result + minLeerjaar;
		result = prime * result + ((auteur == null) ? 0 : auteur.hashCode());
		result = prime * result
				+ ((onderwerp == null) ? 0 : onderwerp.hashCode());
		result = prime * result
				+ ((quizStatus == null) ? 0 : quizStatus.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Quiz other = (Quiz) obj;
		if (auteur == null) {
			if (other.auteur != null) {
				return false;
			}
		} else if (!auteur.equals(other.auteur)) {
			return false;
		}
		if (isTest != other.isTest) {
			return false;
		}
		if (isUniekeDeelname != other.isUniekeDeelname) {
			return false;
		}
		if (minLeerjaar != other.minLeerjaar) {
			return false;
		}
		if (maxLeerjaar != other.maxLeerjaar) {
			return false;
		}
		if (auteur != other.auteur) {
			return false;
		}
		if (onderwerp == null) {
			if (other.onderwerp != null) {
				return false;
			}
		} else if (!onderwerp.equals(other.onderwerp)) {
			return false;
		}
		if (quizStatus != other.quizStatus) {
			return false;
		}
		return true;
	}
}
