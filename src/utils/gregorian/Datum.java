package utils.gregorian;

import java.util.Calendar;
import java.util.GregorianCalendar;
import utils.MaandEnum;

/**
 * 
 * @author thijs
 * 
 */

public class Datum {

	GregorianCalendar kalender = new GregorianCalendar();
	int dag, maand, jaar;

	/**
	 * 
	 * @return getters voor dag, maand, jaar
	 * 
	 */
	public int getDag() {
		return this.dag;
	}

	public int getMaand() {
		return this.maand;
	}

	public int getJaar() {
		return this.jaar;
	}

	/**
	 * default constructor zonder parameters
	 */
	public Datum() {
		//GregorianCalendar kalender = new GregorianCalendar(getDag(), getMaand(), getJaar());
		kalender = new GregorianCalendar();
		this.dag =kalender.get(Calendar.DAY_OF_MONTH);
		this.maand= kalender.get(Calendar.MONTH)+1;
		this.jaar = kalender.get(Calendar.YEAR);
	}

	/**
	 * @param datum
	 */
	public Datum(Datum datum) {
		GregorianCalendar kalender = new GregorianCalendar(datum.getDag(), datum.getMaand(),datum.getJaar());
				this.dag = datum.dag;
				this.maand = datum.maand;
				this.jaar = datum.jaar;
	}

	public Datum(int dag, int maand, int jaar) {
		kalender = new GregorianCalendar(dag, maand, jaar);
		this.dag = dag;
		this.maand= maand;
		this.jaar=jaar;
	}

	/**
	 * moet ik hier ook een throws exception bijzetten?
	 * 
	 * @param datum
	 */
	public Datum(String datum) {
		int d, m, j;
		try {
			String[] datumSplitsing = datum.split("/");
			d = (controleDag(Integer.parseInt((datumSplitsing[0]))));
			m = (controleMaand(Integer.parseInt((datumSplitsing[1]))));
			j = (Integer.parseInt((datumSplitsing[2])));
		} catch (Exception ex) {
			throw new RuntimeException(
					"Gelieve een correcte datum mee te geven onder de vorm dd/mm/jjjj");
		}
		//kalender = new GregorianCalendar(d, m, j);
		this.dag = d;
		this.maand=m;
		this.jaar=j;
	}

	@Override
	public String toString() {
		return dag + " " + MaandEnum.values()[maand-1] + " " + jaar;
	}

	public static void main(String[] args) {
		//GregorianCalendar c = new GregorianCalendar();
		//System.out.println(c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR));
		try {
			Datum datum1 = new Datum();
			System.out.println("datum1 : " + datum1 +  datum1.dag +"/" + datum1.maand +"/" + datum1.jaar);
			datum1 = new Datum ("12/11/1970");
			System.out.println("datum1 : " + datum1);
			datum1 = new Datum(1,10,2012);
			Datum datum2 = new Datum(datum1);
			System.out.println("datum2=datum1 : " + datum2 + "=" + datum1);
		} catch (IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
		}
	}

	private int controleMaand(int maand) {
		if (maand > 0 && maand < 13) {
			String m = String.valueOf(maand);
			if (m.length() == 2) {
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
			String d = String.valueOf(dag);
			if (d.length() == 2) {
				return dag;
			}
		}
		throw new IllegalArgumentException("dag moet tussen 01 en 31 liggen");
	}

	/**
	 * boolean kleinerDan(Datum datum) <br>
	 * Returns true indien de eerste datum kleiner is dan de tweede datum anders
	 * false
	 */
	public boolean kleinerDan(Datum datum) {
		if (this.compareTo(datum) >= 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public int hashCode() {
		/*
		 * final int prime = 31; int result = 1; result = prime * result + dag;
		 * result = prime * result + jaar; result = prime * result + maand;
		 */
		return kalender.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return this.equals(obj);
	}

	/**
	 * vergelijkt twee datums. <br>
	 * Returns 0 indien datums gelijk zijn. <br>
	 * Returns een negatief getal indien de eerste datum kleiner is. <br>
	 * Returns een positief getal indien de eerste datum groter is.
	 */
	public int compareTo(Datum datum) {
		int i;
		i = this.getJaar() - datum.getJaar();
		if (i == 0) {
			i = this.getMaand() - datum.getMaand();
			if (i == 0) {
				i = this.getDag() - datum.getDag();
				return i;
			} else
				return i;
		} else
			return i;
	}

	public int verschilInJaren(Datum d) {
		int jaar = this.jaar - d.jaar;
		if (this.maand < d.maand || (this.maand == d.maand && this.dag < d.dag)) {
			jaar--;
		}
		return jaar;
	}

	public int verschilInMaanden(Datum d) {
		return (this.getJaar() * 12 + this.getMaand())
				- (d.getJaar() * 12 + d.getMaand());
	}

	
	public int verschilInDagen(Datum d) throws CloneNotSupportedException{
		int verschil = 0;
		Datum d2 = (Datum) kalender.clone();			
		for (int i = 0; (d.kleinerDan(d2)); i++) {
			verschil = i++;
			d2.kalender.add(dag, -1);			 
		}
		/*GregorianCalendar kalender2 = new GregorianCalendar(d.getDag(),
				d.getMaand(), d.getJaar());
		
		 * return (kalender.computeTime() - kalender2.computeTime())/
		 * (24*60*60*1000);
		 */
		return verschil;
	}

	public boolean setDatum(int dag, int maand, int jaar)
			throws IllegalArgumentException {
		controleDag(dag);
		controleMaand(maand);
		kalender = new GregorianCalendar(dag, maand, jaar);
		return true;
	}
	
	public String getDatumInAmerikaansFormaat(Datum datum) {
		if (datum == null) {
			throw new RuntimeException("datum kan niet null zijn");
		} else {
			return String.format("%04d/%02d/%02d", datum.getJaar(),
					datum.getMaand(), dag);
		}
	}

	public String getDatumInEuropeesFormaat(Datum datum) {
		return String.format("%02d/%02d/%04d", datum.getDag(),
				datum.getMaand(), datum.getJaar());
	}
	
	public void veranderDatum (int aantalDagen){
		kalender.add(dag, aantalDagen);
	}
	
	public Datum nieuweDatum (int aantalDagen){
		kalender.add(dag, aantalDagen); 
		return this; 
	}
}