package utils;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//import java.util.Locale;
/**
 * Deze klasse behandeld een datum
 * 
 * @author Noella
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
		maand = today.getMonth() + 1; // + 1 want maand ligt in java tussen 0 en
										// 11
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
		return dag + " " + MaandEnum.values()[maand] + " " + jaar;
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
	public int compareTo(Datum datum) {
		return new Date(this.getDag(), this.getMaand(), this.getJaar())
				.compareTo(new Date(datum.getDag(), datum.getMaand(), datum
						.getJaar()));
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

	public static void main(String[] args) {
		try {
			Datum datum1 = new Datum();
			System.out.println("datum1 : " + datum1);
			Datum datum2 = new Datum(datum1);
			System.out.println("datum2 : " + datum2);
			Datum datum3 = new Datum(8, 5, 2012);
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
	}
}