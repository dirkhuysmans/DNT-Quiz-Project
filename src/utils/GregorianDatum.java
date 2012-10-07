package utils;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;



/**
 * 
 * @author java
 *
 */

public class GregorianDatum extends Datum{
		
	static GregorianCalendar kalender = new GregorianCalendar();
	
	public GregorianDatum(){
		//Calendar kalender = GregorianCalendar.getInstance(TimeZone.getTimeZone("Europe/Brussels"));
		super(kalender.get(GregorianCalendar.DAY_OF_MONTH), kalender.get(GregorianCalendar.MONTH),
				kalender.get(GregorianCalendar.YEAR));
				//kalender.DAY_OF_MONTH;
				//GregorianCalendar.DAY_OF_MONTH;
		//this.maand=kalender.get(GregorianCalendar.MONTH);
		//this.jaar = kalender.get(GregorianCalendar.YEAR);
	}
	
	/** 
	 * @param datum
	 * 
	 * ik vind het wat vreemd maar deze klasse dient toch een andere
	 * naam te krijgen dan de klasse Datum? daarom deze constructor
	 * met een GregorianDatum object als input...is mijn interpretatie
	 * juist?
	 */
	public GregorianDatum(GregorianDatum datum){
		super(datum);
	}
	
	public GregorianDatum(int dag, int maand, int jaar){
		super (dag, maand, jaar);
	}
	
	/**
	 * moet ik hier ook een throws exception bijzetten?
	 * @param datum
	 */
	public GregorianDatum(String datum){
		super(datum);
	}
	
	/*@Override
	public String toString()
	{		
		return  dag +" " + MaandEnum.values()[maand] + " " + jaar;
	}*/
	
	public static void main(String[] args) {
		try{
			GregorianDatum datum1 = new GregorianDatum();
			System.out.println("datum1 : " + datum1);
			}
		catch (IllegalArgumentException ex){System.out.println(ex.getMessage());}
	}
	
	/*
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
	}*/

	
}