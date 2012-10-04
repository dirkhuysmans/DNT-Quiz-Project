package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import utils.Datum;

public class DatumTest {
	private Datum datum;
	
	@Before
	public void setUp() throws Exception {
		datum = new Datum();
		datum.setDag(1);
	}

	@Test
	public void test_setDag_controleDag_geldige_dag_wordt_aanvaard() {
		datum.setDag(5);
		assertEquals(5,datum.getDag());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test() {
		datum.setDag(42);
	}

	
	
}
