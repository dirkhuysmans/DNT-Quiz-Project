package utils.gregorian;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import utils.MaandEnum;

/**
 * 
 * @author thijs
 * 
 */

public class Datum {

	static GregorianCalendar kalender;
	int dag, maand, jaar;

	/**
	 * default constructor zonder parameters
	 */
	public Datum() {
		dag = kalender.get(GregorianCalendar.DAY_OF_MONTH);
		maand = kalender.get(GregorianCalendar.MONTH);
		jaar = kalender.get(GregorianCalendar.YEAR);
		//getters schrijven
		kalender = new GregorianCalendar (dag, maand, jaar);
	}

	/**
	 * @param datum
	 * 
	 *            ik vind het wat vreemd maar deze klasse dient toch een andere
	 *            naam te krijgen dan de klasse Datum? daarom deze constructor
	 *            met een GregorianDatum object als input...is mijn
	 *            interpretatie juist?
	 */
	public Datum(Datum datum) {
		dag = datum.getDag();
		maand = datum.getMaand();
		jaar = datum.getJaar();
		kalender = new GregorianCalendar (dag, maand, jaar);
	}

	public Datum(int dag, int maand, int jaar) {
		this.dag = dag;
		this.maand = maand;
		this.jaar = jaar;
		kalender = new GregorianCalendar (dag, maand, jaar);
	}

	/**
	 * moet ik hier ook een throws exception bijzetten?
	 * 
	 * @param datum
	 */
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
		kalender = new GregorianCalendar (dag, maand, jaar);
	}

	@Override
	public String toString() {
		return dag + " " + MaandEnum.values()[maand] + " " + jaar;
	}

	public static void main(String[] args) {
		try {
			Datum datum1 = new Datum();
			System.out.println("datum1 : " + datum1);
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
		if (this.compareTo(datum) >= 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public int hashCode() {
		/*final int prime = 31;
		int result = 1;
		result = prime * result + dag;
		result = prime * result + jaar;
		result = prime * result + maand;*/
		return kalender.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return kalender.equals(obj);
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
		if (i==0){
			i = this.getMaand() - datum.getMaand();
			if (i==0){
				i = this.getDag() - datum.getDag();
				return i;
			}else return i;
		}else return i;
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
	
	public int verschilInDagen(Datum d){
		try {
			Object a = d.clone();
			int verschil;
			for(int i=0; ((Datum) a).kleinerDan(this); i++ ){
				verschil = i;
				
			}
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GregorianCalendar kalender2 = new GregorianCalendar(d.getDag(), d.getMaand(), d.getJaar());
		/*return (kalender.computeTime() - kalender2.computeTime())/
				(24*60*60*1000);*/
		
		return 0;
		}

	public int getDag() {
		return dag;
	}

	public void setDag(int dag) {
		this.dag = dag;
	}

	public int getMaand() {
		return maand;
	}

	public void setMaand(int maand) {
		this.maand = maand;
	}

	public int getJaar() {
		return jaar;
	}

	public void setJaar(int jaar) {
		this.jaar = jaar;
	}
	
	public boolean setDatum(int dag, int maand, int jaar)
			throws IllegalArgumentException {
		setDag(controleDag(dag));
		setMaand(controleMaand(maand));
		setJaar(jaar);
		kalender = new GregorianCalendar (dag, maand, jaar);
		return true;
	}
}