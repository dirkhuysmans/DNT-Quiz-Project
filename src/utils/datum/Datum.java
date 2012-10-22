package utils.datum;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import utils.MaandEnum;

//import java.util.Locale;
/**
 * Deze klasse behandeld een datum
 * 
 * @author Noella
 * @
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
		maand = today.getMonth();
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
					datum.getMaand(), dag);
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
	public int compareTo(Datum datum){
	return (this.jaar*10000+this.maand*100+this.dag) - (datum.jaar*10000+datum.maand*100+datum.dag);
			
	}
	/**
	 * Berekent de dagnummer in het jaar
	 * @returns de dag van het jaar
	 * @throws Exception
	 */
	public int berekenDagVanJaar() throws Exception{
		int [] dagenInMaand = new int [] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int dagen = 0;
		
			try {
				dagen += this.dag;
				if(this.jaar%4==0){
					dagenInMaand[1] = 29;
				}
				else{
					dagenInMaand[1]=28;
				}
				for (int m=0; m<this.maand-1;m++){
					dagen += dagenInMaand[m];
				}
				return dagen;
			} 
			catch (Exception e) {
				// TODO Auto-generated catch block
				throw new Exception("De bewerking berekenDagVanJaar kan niet uitgevoerd worden. " + e.getMessage());
			}
	}

	
	/**
	 * berekenDagDecimaal rekent de datum om naar decimaal  
	 * aantal volledige jaren * 365 dagen en + het aantal schrikkeljaren + kalenderdag van huidig jaar
	 * @throws Exception
	 */

	public int berekenDagDecimaal() throws Exception {
		int dag=0;
		int jaar=0;
		
			try {
					jaar = ((this.jaar-1) - ((this.jaar-1)%4))/4;
					dag = ((this.jaar-1) * 365) + jaar;
				
				dag += this.berekenDagVanJaar();
				
				return dag;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new Exception("De bewerking berekenDagDecimaal kan niet uitgevoerd worden. " + e.getMessage());
			}
	}
	public int verschilIndagen(Datum datum) throws Exception{
		int dag1 = 0;
		int dag2 = 0;
		
		try{
			if(this.kleinerDan(datum)){
				dag1=this.berekenDagDecimaal();
				dag2 = datum.berekenDagDecimaal();
			}
			else{
				dag2= this.berekenDagDecimaal();
				dag1 = datum.berekenDagDecimaal();
			}
			
			return dag2-dag1;
		}
		catch (Exception e){
			throw new Exception("Bij het berekenen van verschil in dagen is volgende fout opgetreden" + e.getMessage());
		}
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
			
			maanden = this.verschilInJaren(datum) * 12;
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
			return maanden;	
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("De bewerking verschilInMaanden kan niet uitgevoerd worden. " + e.getMessage());
		}
	}
	
	public void veranderDatum(int aantalDagen) throws Exception{
		int [] dagenInMaand = new int [] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		try {
			for(int d= aantalDagen;d>0;){
				if((this.dag+d) >  dagenInMaand[this.maand-1]){
					if(this.jaar%4==0 && this.maand-1 ==1){
						d -= 29 - this.dag + 1;	
					}
					else{
						d -= dagenInMaand[this.maand-1] - this.dag + 1;
					}
					
					this.dag=1;
					this.maand++;
						if(this.maand>12){
							this.jaar++;
							this.maand=1;
						}
				}
				else{
					this.dag+=d;
					d=0;					
				}				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("De bewerking veranderDatum kan niet uitgevoerd worden. " +e.getMessage());
		}
		
	}
	
	public Datum veranderDatum1(int aantalDagen) throws Exception{
		int [] dagenInMaand = new int [] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		try {
			Datum nDatum = new Datum(this.dag, this.maand, this.jaar);
			
			for(int d= aantalDagen;d>0;){
				if((nDatum.dag+d) >  dagenInMaand[nDatum.maand-1]){
					if(nDatum.jaar%4==0 && nDatum.maand-1 ==1){
						d -= 29 - nDatum.dag+1;	
					}
					else{
						d -= dagenInMaand[nDatum.maand-1] - nDatum.dag+1;
					}
					
					nDatum.dag=1;
					nDatum.maand++;
						if(nDatum.maand>12){
							nDatum.jaar++;
							nDatum.maand=1;
						}
				}
				else{
					nDatum.dag+=d;
					d=0;					
				}				
			}
			return nDatum;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("De bewerking VeranderDatum1 kan geen waarde teruggeven. " + e.getMessage());
		}
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
		System.out.println("Verschil in dagen: " + datum10.verschilIndagen(datum11));
		System.out.println("Datum 10 dag decimaal: " + datum10.berekenDagDecimaal());
		System.out.println("Datum 10 dag van jaar :" + datum10.berekenDagVanJaar());
		System.out.println("Datum 10 + 1 dagen: " + datum10.veranderDatum1(60));

	}
}