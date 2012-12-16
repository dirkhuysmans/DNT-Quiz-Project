package utils.datum;

import java.util.Date;

import utils.MaandEnum;

//import java.util.Locale;
/**
 * Deze klasse behandeld een datum
 * 
 * @author Noella
 * 
 * @version september 2012
 * 
 */
public class Datum implements Comparable<Datum> {
	//
	// Attributen
	//
	private int dag = 0;
	private int maand = 0;
	private int jaar = 0;

	//
	// Constructor
	//
	public Datum() {
		Date today = new Date();
		dag = today.getDate();
		//maand = today.getMonth() + 1; // + 1 want maand ligt in java tussen 0 en
										// 11
		maand = today.getMonth()+1;
		jaar = today.getYear() + 1900;
	}

	public Datum(Datum datum) {
		try {
			this.dag = datum.getDag();
			this.maand = datum.getMaand();
			this.jaar = datum.getJaar();
		} catch (Exception ex) {
			throw new RuntimeException("object datum kan niet null zijn");
		}
	}

	public Datum(int dag, int maand, int jaar) throws IllegalArgumentException {
		try {
			setDag(controleDag(dag));
			setMaand(controleMaand(maand));
			setJaar(jaar);
		} catch (IllegalArgumentException argEx) {
			throw new IllegalArgumentException("verkeerde input");
		}
	}

	public Datum(String datum) {
		try {
			String[] datumSplitsing = datum.split("/");
			setDag(controleDag(Integer.parseInt((datumSplitsing[0]))));
			setMaand(controleMaand(Integer.parseInt((datumSplitsing[1]))));
			setJaar(Integer.parseInt((datumSplitsing[2])));
		} catch (Exception ex) {
			throw new RuntimeException(
					"Gelieve een correcte datum mee te geven onder de vorm dd/mm/jjjj");
		}
	}

	//
	// Getters
	//
	/**
	 * @return dag
	 */
	public int getDag() {
		return dag;
	}

	/**
	 * @return maand
	 */
	public int getMaand() {
		return maand;
	}
	
	public String getNaamMaand(){
		return MaandEnum.values()[this.maand-1].toString();
	}

	/**
	 * @return jaar
	 */
	public int getJaar() {
		return jaar;
	}

	/**
	 * 
	 * @return de datum in Amerikaans formaat jaar/maand/dag
	 * @throws RequiredField
	 */
	public String getDatumInAmerikaansFormaat(Datum datum) {
		if (datum == null) {
			throw new RuntimeException("datum kan niet null zijn");
		} else {
			return String.format("%04d/%02d/%02d", datum.getJaar(),
					datum.getMaand(), datum.getDag());
		}
	}

	/**
	 * @return de datum in Europees formaat dag/maand/jaar
	 */
	public String getDatumInEuropeesFormaat(Datum datum) {
		return String.format("%02d/%02d/%04d", datum.getDag(),
				datum.getMaand(), datum.getJaar());
	}

	//
	// Setters
	//
	/**
	 * methode om dag een waarde te geven
	 */
	public void setDag(int dag) {
		this.dag = controleDag(dag);
	}

	/**
	 * methode om maand een waarde te geven
	 */
	public void setMaand(int maand) {
		this.maand = controleMaand(maand);
	}

	/**
	 * methode om jaar een waarde te geven
	 */
	public void setJaar(int jaar) {
		this.jaar = jaar;
	}

	/**
	 * methode om een Datumobject een geldige waarde te geven
	 */
	public boolean setDatum(int dag, int maand, int jaar)
			throws IllegalArgumentException {
		setDag(controleDag(dag));
		setMaand(controleMaand(maand));
		setJaar(jaar);
		return true;
	}

	/**
	 * methode om de maand op een geldige waarde te controleren <br>
	 * waarde moet tussen 1 en 12 liggen
	 */
	private int controleMaand(int maand) {
		if (maand > 0 && maand < 13) {
			String m = String.valueOf(maand);
			if ((m.length() == 2)|| (m.length()==1)) {
				return maand;
			}
		}
		throw new IllegalArgumentException("maand moet tussen 01 en 12 liggen");

	}

	/**
	 * methode om de dag te controleren <br>
	 * waarde moet tussen 1 en 31 liggen
	 */
	private int controleDag(int dag) {
		if (dag > 0 && dag < 32) {
			return dag;
		} else {
			throw new IllegalArgumentException(
					"dag moet tussen 01 en 31 liggen");
		}
	}

	/**
	 * boolean kleinerDan(Datum datum) <br>
	 * Returns true indien de eerste datum kleiner is dan de tweede datum anders
	 * false
	 */
	public boolean kleinerDan(Datum datum) {
		if (compareTo(datum) >= 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * override toString()
	 */
	@Override
	public String toString() {
		return dag + " " + MaandEnum.values()[maand-1] + " " + jaar;
		/*
		 * Calendar cal = Calendar.getInstance(); cal.set(jaar, maand, dag);
		 * DateFormat format = new SimpleDateFormat("dd MMMM yyyy"); return
		 * format.format(cal.getTime());
		 */
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dag;
		result = prime * result + jaar;
		result = prime * result + maand;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this.hashCode() == obj.hashCode()) {
			return true;
		} else {
			return false;
		}

		/*
		 * if (this == obj) return true; if (obj == null) return false; if
		 * (getClass() != obj.getClass()) return false; Datum other = (Datum)
		 * obj; if (dag != other.dag) return false; if (jaar != other.jaar)
		 * return false; if (maand != other.maand) return false; return true;
		 */
	}

	/**
	 * vergelijkt twee datums. <br>
	 * Returns 0 indien datums gelijk zijn. <br>
	 * Returns een negatief getal indien de eerste datum kleiner is. <br>
	 * Returns een positief getal indien de eerste datum groter is.
	 */
	/*public int compareTo(Datum datum) {
		return new Date(this.getDag(), this.getMaand(), this.getJaar())
				.compareTo(new Date(datum.getDag(), datum.getMaand(), datum
						.getJaar()));
	}
	*/
	/**
	 * 
	 * @Bovenstaande vergelijking van 2 datums werkt blijkbaar niet zoals het hoort
	 * @Aangepast op onderstaande manier
	 * 
	 * vergelijkt twee datums. <br>
	 * Returns 0 indien datums gelijk zijn. <br>
	 * Returns een negatief getal indien de eerste datum kleiner is. <br>
	 * Returns een positief getal indien de eerste datum groter is.
	 */
	@Override
	public int compareTo(Datum datum){
	return (this.jaar*10000+this.maand*100+this.dag) - (datum.jaar*10000+datum.maand*100+datum.dag);
			
	}
	
	public int verschilInJaren(Datum datum) throws Exception{
		
		try {
			int jaren=0;
			int mnd1 = this.maand*100 + this.dag;
			int mnd2 = datum.maand*100 + datum.dag;
		
			if (datum.kleinerDan(this)){
			//if (this.compareTo(datum)){
				jaren = this.jaar - datum.jaar;
				if ((this.jaar!=datum.jaar) && (mnd1<mnd2)){
					jaren--;
				}
				
				
			}
			else{
				jaren = datum.jaar - this.jaar;
				if ((this.jaar!= datum.jaar) && (mnd1>mnd2)){
					jaren--;
				}
				if((this.jaar!=datum.jaar)&&(this.maand==datum.maand)&&(datum.dag<this.dag)){
					jaren--;
				}
			}	
			return jaren;
		}	
		 catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("De bewerking verschilInJaren kan niet uitgevoerd worden. " + e.getMessage());
		}
	}
	
	public int verschilInMaanden(Datum datum) throws Exception{
		
		try {
			int maanden = 0;
			//aangepast op 23/11/2012 door Dirk Huysmans
			/*maanden = this.verschilInJaren(datum) * 12;
			if(this.kleinerDan(datum)){
				maanden += datum.maand - this.maand;
					if((this.maand == datum.maand) && (this.dag>datum.dag)){
						maanden--;
					}
					
			}
			else{
				maanden+= this.maand-datum.maand;
				if((this.maand==datum.maand) && (this.dag < datum.dag)){
					maanden--;
				}
			}
			*/
			
			int maandenDatum1 = this.jaar * 12 + this.maand;
			int maandenDatum2 = datum.jaar * 12 + datum.maand;
			maanden = (maandenDatum1<maandenDatum2)?maandenDatum2-maandenDatum1:maandenDatum1-maandenDatum2;
			
			return maanden;	
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("De bewerking verschilInMaanden kan niet uitgevoerd worden. " + e.getMessage());
		}
	}

	/**
	 * Definitie schrikkeljaar volgens Wikipedia
	 * Een schrikkeldag valt op 29 februari 
	 * en komt in de gregoriaanse kalender voor in elk jaar dat deelbaar is door 4, 
	 * met uitzondering van eeuwjaren. 
	 * Deze hebben enkel een schrikkeldag als ze deelbaar zijn door 400.
	 * @return
	 */
	public boolean isSchrikkeljaar(){
		if(this.jaar%4==0 && this.jaar%100!=0){
			return true;
		}
		if(this.jaar%400==0){
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * 
	 * @return naam van de maand in Nederlands
	 * @throws Exception indien maand niet bestaat
	 */
	/*aangepast op 23/11/2012 door Dirk Huysmans Functie zie getters
	public String getNaamMaand() throws Exception{
		switch (this.maand){
		case 1:
			return "januari";
		case 2:
			return "februari";
		case 3:
			return "maart";
		case 4:
			return "april";
		case 5:
			return "mei";
		case 6:
			return "juni";
		case 7:
			return "juli";
		case 8:
			return "augustus";
		case 9:
			return "september";
		case 10:
			return "oktober";
		case 11:
			return "november";
		case 12:
			return "december";
			default:
				throw new Exception("De naam van de maand kan niet weergegeven worden. Gelieve de maand in de meegegeven datum na te kijken.");
		}
	}
	*/
	/**
	 * 
	 * @return aantal dagen in maand
	 * @throws Exception indiend meegegeven int maand niet in lijst
	 */
	public int aantalDageninMaand() throws Exception{
		switch (this.maand) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return 31;
			
		case 2:
			if(this.isSchrikkeljaar()){
				return 29;
			}
			else{
				return 28;
			}
			
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;

		default:
			throw new Exception("Aantal dagen kon niet bepaald worden");
		} 
		
	}
	
	public int verschilInDagen(Datum datum) throws Exception{
		int d = 0;
		Datum datKleinste = new Datum();
		Datum datGrootste = new Datum();
		if(this.kleinerDan(datum)){
			datKleinste= this;
			datGrootste = datum;
		}
		else{
			datKleinste= datum;
			datGrootste= this;
		}
		
		while(!datKleinste.equals(datGrootste)){
			d++;
			if(datKleinste.dag==datKleinste.aantalDageninMaand()){
				datKleinste.dag=1;
				if(datKleinste.maand==12){
					datKleinste.maand=1;
					datKleinste.jaar++;
				}
				else{
					datKleinste.maand++;
				}
			}
			else{
				datKleinste.dag++;
			}
		}
		return d;
	}
	
	/**
	 * Wijzigt de geselecteerde datum met het meegegeven aantal dagen
	 * Indien aantalDagen positief controle of aantal dagen in maand overschreden wordt
	 * dan wordt maand en eventueel jaar verhoogt
	 * Indien aantalDagen negatief controle of niet in lagere maand terecht gekomen, want dan maand en eventueel met 1 verlagen
	 * @param aantalDagen
	 * @throws Exception
	 */
	public void veranderDatum(int aantalDagen) throws Exception{
		while(aantalDagen>0){
			//int aantalDagenInMaand = this.aantalDageninMaand();
			if(this.dag==this.aantalDageninMaand()){
				this.dag=1;
				if(this.maand==12){
					this.maand=1;
					this.jaar++;
				}
				else{
					this.maand++;
				}
			}
			else{
				this.dag++;
			}
			aantalDagen--;
		}
		while(aantalDagen<0){
			if(this.dag==1){
				if(this.maand==1){
					this.jaar--;
					this.maand=12;
				}
				else{
					this.maand--;
				}
				dag = this.aantalDageninMaand();
			}
			else{
				this.dag--;
			}
			aantalDagen++;
		}
	
	}
	
	public Datum veranderDatum1(int aantalDagen) throws Exception{
		Datum datum = this;
		while(aantalDagen>0){
			//int aantalDagenInMaand = this.aantalDageninMaand();
			if(datum.dag==datum.aantalDageninMaand()){
				datum.dag=1;
				if(datum.maand==12){
					datum.maand=1;
					datum.jaar++;
				}
				else{
					datum.maand++;
				}
			}
			else{
				datum.dag++;
			}
			aantalDagen--;
		}
		while(aantalDagen<0){
			if(datum.dag==1){
				if(datum.maand==1){
					datum.jaar--;
					datum.maand=12;
				}
				else{
					datum.maand--;
				}
				dag = datum.aantalDageninMaand();
			}
			else{
				datum.dag--;
			}
			aantalDagen++;
		}
			return datum;
	}
	
	


	public static void main(String[] args) throws Exception {
		try {
			Datum datum1 = new Datum();
			System.out.println("datum1 : " + datum1);
			Datum datum2 = new Datum(datum1);
			System.out.println("datum2 : " + datum2);
			Datum datum3 = new Datum(8, 05, 2012);
			System.out.println(("datum3 : ") + datum3);
			Datum datum4 = new Datum("01/05/2012");
			System.out.println(("datum4 :") + datum4);
			System.out.println("Amerikaans formaat "
					+ datum2.getDatumInAmerikaansFormaat(datum2));
			System.out.println("Europees formaat "
					+ datum2.getDatumInEuropeesFormaat(datum2));
			Datum datum5 = new Datum();
			datum5.setDatum(8, 04, 2012);
			System.out.println(datum5);
			System.out.println(datum1.compareTo(datum3));
			System.out.println(datum1.kleinerDan(datum3));
			System.out.println(datum1.setDatum(10, 10, 2012));
		} catch (IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
		} catch (RuntimeException ex) {
			System.out.println(ex.getMessage());
		}
		Datum datum11 = new Datum(3,3,2012);
		Datum datum10 = new Datum(1,1,2012);
		System.out.println("Datum 10: " + datum10 + ", datum 11: " + datum11);
		System.out.println("Verschil in jaren: " + datum10.verschilInJaren(datum11));
		System.out.println("Verschil in maanden: " + datum10.verschilInMaanden(datum11));
		System.out.println("Verschil in dagen: " + datum10.verschilInDagen(datum11));
		System.out.println("Datum 10 + 1 dagen: " + datum10.veranderDatum1(60));
		//Controle van toegevoegde of aangepaste procedures
		Datum datums1 = new Datum(1,1,2013);
		if(datums1.isSchrikkeljaar()){
		System.out.println("datums1 : " + datums1 + " is schrikkeljaar");
		}
		else {
			System.out.println("datums1 : " + datums1 + " is geen schrikkeljaar");
		}
		Datum datums2 = new Datum(1,1,2012);
		if(datums2.isSchrikkeljaar()){
		System.out.println("datums2 : " + datums2 + " is schrikkeljaar");
		}
		else {
			System.out.println("datums2 : " + datums2 + " is geen schrikkeljaar");
		}
		System.out.println("Maand van datums1 : " + datums1 + " bevat " + datums1.aantalDageninMaand() + " dagen.");
		System.out.println("Maand van datums2 : " + datums2 + " bevat " + datums2.aantalDageninMaand() + " dagen.");	
		System.out.println("Maand van datums1 : " + datums1.getNaamMaand());
		System.out.println("Maand van datums2 : " + datums2.getNaamMaand());
		System.out.println("Het verschil in dagen tussen datums1 en datums2 bedraagt : " + datums1.verschilInDagen(datums2) + " dagen.");
		System.out.println("datums1 + 365 dagen = " + datums1.veranderDatum1(365));
		System.out.println("Het verschil in maanden tusse " + datums1 + " en " + datums2 + " bedraagt " + datums1.verschilInMaanden(datums2));

	}
}