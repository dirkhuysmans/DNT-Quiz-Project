package model.enumKlassen;

public  enum Leraar {
	GUY(1), CARLA(2), FRANK(3), TOM(4), MYRIAM(5);
	
	private final int id;
	Leraar(int id) { 
    	this.id = id; 
    }
    public int getValue() { 
    	return id; 
    }
}
