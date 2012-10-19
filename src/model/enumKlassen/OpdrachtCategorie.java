package model.enumKlassen;

public enum OpdrachtCategorie {
	rekenen, NederlandseTaal, FranseTaal, algemeneKennis;
	
	
	public static OpdrachtCategorie convertInt(int x){
		x = x-1;
		switch (x){
		case 0: 
			return rekenen;
		case 1: 
			return NederlandseTaal;
		case 2:
			return FranseTaal;
		case 3: 
			return algemeneKennis;
		}
		return null;
	}
}
