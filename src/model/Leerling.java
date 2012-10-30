package model;

import java.io.Serializable;


/**
 * 
 * @author Dirk Huysmans
 *
 */

public class Leerling implements Serializable{
	
/**
 *  attributen	
 */
	
private String leerlingNaam;
private int leerjaar;

//constructor
public Leerling(String leerlingNaam, int leerjaar) {
	this.leerlingNaam = leerlingNaam;
	this.leerjaar = leerjaar;
}

//setters
public void setLeerlingNaam(String leerlingNaam) {
	this.leerlingNaam = leerlingNaam;
}

public void setLeerjaar(int leerjaar) {
	this.leerjaar = leerjaar;
}


public String getLeerlingNaam() {
	return leerlingNaam;
}

public int getLeerjaar() {
	return leerjaar;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + leerjaar;
	result = prime * result
			+ ((leerlingNaam == null) ? 0 : leerlingNaam.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Leerling other = (Leerling) obj;
	if (leerjaar != other.leerjaar)
		return false;
	if (leerlingNaam == null) {
		if (other.leerlingNaam != null)
			return false;
	} else if (!leerlingNaam.equals(other.leerlingNaam))
		return false;
	return true;
}

/**
 * @param Naam en leerjaar
 */
@Override
public String toString() {
	return "Naam: " + leerlingNaam + ", leerjaar=" + leerjaar;
}






}
