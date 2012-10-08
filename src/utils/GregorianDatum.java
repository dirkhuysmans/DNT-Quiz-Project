package utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 
 * @author thijs
 * 
 */

public class GregorianDatum {

	static GregorianCalendar kalender = new GregorianCalendar();
	int dag, maand, jaar;

	/**
	 * default constructor zonder parameters
	 */
	public GregorianDatum() {
		dag = kalender.get(GregorianCalendar.DAY_OF_MONTH);
		maand = kalender.get(GregorianCalendar.MONTH);
		jaar = kalender.get(GregorianCalendar.YEAR);
	}

	/**
	 * @param datum
	 * 
	 *            ik vind het wat vreemd maar deze klasse dient toch een andere
	 *            naam te krijgen dan de klasse Datum? daarom deze constructor
	 *            met een GregorianDatum object als input...is mijn
	 *            interpretatie juist?
	 */
	public GregorianDatum(GregorianDatum datum) {
		dag = datum.getDag();
		maand = datum.getMaand();
		jaar = datum.getJaar();
	}

	public GregorianDatum(int dag, int maand, int jaar) {
		this.dag = dag;
		this.maand = maand;
		this.jaar = jaar;
	}

	/**
	 * moet ik hier ook een throws exception bijzetten?
	 * 
	 * @param datum
	 */
	public GregorianDatum(String datum) {
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

	@Override
	public String toString() {
		return dag + " " + MaandEnum.values()[maand] + " " + jaar;
	}

	public static void main(String[] args) {
		try {
			GregorianDatum datum1 = new GregorianDatum();
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
	public boolean kleinerDan(GregorianDatum datum) {
		if (this.compareTo(datum) >= 0) {
			return false;
		} else {
			return true;
		}
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
	}

	/**
	 * vergelijkt twee datums. <br>
	 * Returns 0 indien datums gelijk zijn. <br>
	 * Returns een negatief getal indien de eerste datum kleiner is. <br>
	 * Returns een positief getal indien de eerste datum groter is.
	 */
	public int compareTo(GregorianDatum datum) {
		int i;
		i = this.getJaar() - datum.getJaar();
		if (i==0){
			i = this.getMaand() - datum.getMaand();
			if (i==0){
				i = this.getDag() - datum.getDag();
				return i;
			}else return i;
		}else return i;
		
		/*return (this.getDag(), this.getMaand(), this.getJaar())
				.compareTo(new Date(datum.getDag(), datum.getMaand(), datum
						.getJaar()));*/
	}

	public int verschilInJaren(GregorianDatum d) {
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

}