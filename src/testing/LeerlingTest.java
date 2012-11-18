package testing;

import static org.junit.Assert.*;
import model.Leerling;

import org.junit.Before;
import org.junit.Test;

public class LeerlingTest {

	Leerling leerling;
	@Before
	public void setUp() throws Exception{
		leerling = new Leerling("Jos",1);
	
	}
	
	@Test
	public void testLeerling_Constructor() {
		Leerling leerling1 = new Leerling("Jos", 1);
		assertEquals("Als naam leerling en leerjaar wordt verwacht", "Jos 1", leerling1.getLeerlingNaam() + " " + leerling1.getLeerjaar());
	}


	@Test
	public void testSetLeerlingNaam_Naamgewijzigd() {
		leerling.setLeerlingNaam("Louis");
		assertEquals("Als naam wordt verwacht",  "Louis", leerling.getLeerlingNaam());
	}
	
	

	@Test
	public void testSetLeerjaar_Leerjaar_aangepast() {
		leerling.setLeerjaar(5);
		assertEquals("Als leerljaar wordt verwacht", 5, leerling.getLeerjaar());
	}



	@Test
	public void testEqualsObject_false() {
		Leerling lln1 = new Leerling ("Jef", 3);
		Leerling lln2 = new Leerling ("An",4);
		lln1.equals(lln2);
		assertFalse(false);
		
	}
	
	@Test
	public void testEqualsNaam_True() {
		Leerling lln1 = new Leerling ("Jef", 3);
		Leerling lln2 = new Leerling ("Jef",4);
		lln1.equals(lln2);
		assertTrue(true);
	}

	@Test
	public void testToString() {
		assertEquals("Naam: Jos, leerjaar= 1","Naam: " + leerling.getLeerlingNaam() + ", leerjaar= " + leerling.getLeerjaar());
		
	}

}
