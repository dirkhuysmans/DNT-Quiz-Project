package testing;
/**
 * Deze klasse test de klasse Datum
 * @author 		Noella
 * @version		september 2012
 */
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import utils.Datum;

public class DatumTest {
	private Datum datum;
	private Datum datum2;
	
	@Before
	public void setUp() throws Exception {
		datum = new Datum();
		datum.setDag(1);
		datum.setMaand(10);
		datum.setJaar(2012);
		datum2 = new Datum();
		datum2.setDag(1);
		datum2.setMaand(10);
		datum2.setJaar(2002);
	}
	//
	// testen van de constructoren
	//
	/**
	 * test Constructor zonder parameters
	 * <br> geldige waarde
	 */
	@Test
	public void test_constructor_object_wordt_gemaakt_zonder_parameter(){
		datum = new Datum();
		datum.setDag(1);
		datum.setMaand(10);
		datum.setJaar(2012);
		datum2 = new Datum();
		datum2.setDag(1);
		datum2.setMaand(10);
		datum2.setJaar(2012);
		assertEquals(datum.getDag(), datum2.getDag());
		assertEquals(datum.getMaand(), datum2.getMaand());
		assertEquals(datum.getJaar(), datum2.getJaar());
	}
	/**
	 * test Constructor met Datum
	 * <br> geldige waarde
	 */
	@Test
	public void test_constructor_object_wordt_gemaakt_met_Datum(){
		Datum datum = new Datum(12,11,2012);
		Datum datum2 = new Datum(datum);
		assertEquals(datum.getDag(), datum2.getDag());
		assertEquals(datum.getMaand(), datum2.getMaand());
		assertEquals(datum.getJaar(), datum2.getJaar());
	}
	/**
	 * testen op foutieve input van de  Constructor met parameter null waarde 
	 * <br>deze moet een exception geven
	 * @throws RuntimeException
	 */
	@Test(expected = RuntimeException.class)
	public void test_constructor_object_wordt_gemaakt_parameter_null_geeft_exception() {
		datum = null;
		assertEquals(01, datum.getDag());
		assertEquals(12, datum.getMaand());
		assertEquals(2012, datum.getJaar());
	}
	/**
	 * test Constructor met parameters int dag, int maand, int jaar
	 * <br> geldige waarde
	 */
	@Test
	public void test_constructor_object_wordt_gemaakt_3_parameters(){
		Datum datum = new Datum(12,11,2012);
		assertEquals(12, datum.getDag());
		assertEquals(11, datum.getMaand());
		assertEquals(2012, datum.getJaar());
	}
	/**
	 * testen Constructor met parameters int dag, int maand, int jaar
	 * <br> één van de parameters(dag) is foutief
	 * @throws IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_constructor_object_wordt_gemaakt_een_parameter_dag_is_foutief(){
		Datum datum = new Datum(88,11,2012);
		assertEquals(12, datum.getDag());
		assertEquals(11, datum.getMaand());
		assertEquals(2012, datum.getJaar());
	}
	/**
	 * testen Constructor met parameters int dag, int maand, int jaar
	 * <br> één van de parameters(maand) is foutief
	 * @throws IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_constructor_object_wordt_gemaakt_een_parameter_maand_is_foutief(){
		Datum datum = new Datum(12,88,2012);
		assertEquals(12, datum.getDag());
		assertEquals(11, datum.getMaand());
		assertEquals(2012, datum.getJaar());
	}
	/**
	 * test Constructor met parameter String datum
	 * <br> geldige waarde
	 */
	@Test
	public void test_constructor_object_wordt_gemaakt_parameter_string(){
		Datum datum = new Datum("01/12/2012");
		assertEquals(01, datum.getDag());
		assertEquals(12, datum.getMaand());
		assertEquals(2012, datum.getJaar());
	}
	/**
	 * testen op foutieve input van de  Constructor met parameter String datum
	 * <br>deze moet een RuntimeException geven
	 * @throws RuntimeException 
	 */
	@Test(expected = RuntimeException.class)
	public void test_constructor_object_wordt_gemaakt_parameter_string_geeft_exception() {
		datum = new Datum("01122012");
		assertEquals(01, datum.getDag());
		assertEquals(12, datum.getMaand());
		assertEquals(2012, datum.getJaar());
	}
	//
	// testen op methodes
	//
	/**
	 * test op getDatumInAmerikaansFormaat
	 * <br> geldige waarde
	 */
	@Test
	public void test_getDatumInAmerikaansFormaat_geldige_wordt_aanvaard() {
		assertEquals("2012/10/01", datum.getDatumInAmerikaansFormaat(datum));
	}
	/**
	 * test op datum getDatumInAmerikaansFormaat
	 * <br> datum = null moet een exception geven
	 * @throws RuntimeException
	 */
	@Test(expected = RuntimeException.class)
	public void test_getDatumInAmerikaansFormaat_geeft_NullPointer() {
		datum.getDatumInAmerikaansFormaat(null);
	}
	/**
	 * test op getDatumInEuropeesFormaat
	 * <br> geldige waarde
	 */
	@Test
	public void test_getDatumInEuropeesFormaat_geldige_wordt_aanvaard() {
		assertEquals("01/10/2012", datum.getDatumInEuropeesFormaat(datum));
	}
	/**
	 * test op datum getDatumInEuropeesFormaat
	 * <br> datum = null moet een exception geven
	 * @throws RuntimeException
	 */
	@Test(expected = RuntimeException.class)
	public void test_getDatumInEuropeesFormaat_geeft_NullPointer() {
		datum.getDatumInEuropeesFormaat(null);
	}
	/**
	 * testen op een geldige waarde van de methode setDag
	 * <br>geldige input
	 */
	@Test
	public void test_setDag_controleDag_geldige_dag_wordt_aanvaard() {
		datum.setDag(5);
		assertEquals(5,datum.getDag());
	}
	/**
	 * testen op de minimumwaarde van de methode setDag
	 * <br>geldige minimum input
	 */
	@Test
	public void test_setDag_controleDag_min_waarde_1_wordt_aanvaard(){
		datum.setDag(1);
		assertEquals(1, datum.getDag());
	}
	/**
	 * testen op de maximumwaarde van de methode setDag
	 * <br>geldige maximum input
	 */
	@Test
	public void test_setDag_controleDag_max_waarde_31_wordt_aanvaard(){
		datum.setDag(31);
		assertEquals(31, datum.getDag());
	}
	/**
	 * testen op negatieve dag van de methode setDag, deze moet een exception geven
	 * <br>foutieve input negatieve dag
	 * @throws IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_setDag_controleDag_negatieve_waarde_geeft_illegalArgument_exception() {
		datum.setDag(-1);
	}	
	/**
	 * testen op een te hoge maximumwaarde van de methode setDag
	 * <br>deze moet een exception geven
	 * @throws IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_setDag_controleDag_groter_dan_max_toegelaten_31_geeft_illegalArgument_exception() {
		datum.setDag(55);
	}
	/**
	 * testen op een te lage minimumwaarde van de methode setDag
	 * <br>deze moet een exception geven
	 * @throws IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_setDag_controleDag_kleiner_dan_min_toegelaten_1_geeft_illegalArgument_exception() {
		datum.setDag(0);
	}
	/**
	 * testen op een geldige waarde van de methode setMaand
	 */
	@Test
	public void test_setMaand_controleMaand_geldige_maand_wordt_aanvaard() {
		datum.setMaand(10);
		assertEquals(10,datum.getMaand());
	}
	/**
	 * testen op de minimumwaarde van de methode setMaand
	 * <br>juiste minimum input
	 */
	@Test
	public void test_setMaand_controleMaand_min_waarde_1_wordt_aanvaard(){
		datum.setMaand(1);
		assertEquals(1, datum.getMaand());
	}
	/**
	 * testen op de maximumwaarde van de methode setMaand
	 * <br> juiste maximum input
	 */
	@Test
	public void test_setMaand_controleMaand_max_waarde_12_wordt_aanvaard(){
		datum.setMaand(12);
		assertEquals(12, datum.getMaand());
	}
	/**
	 * testen op negatieve maand van de methode setMaand, deze moet een exception geven
	 * <br>foutieve input negatieve maand
	 * @throws IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_setMaand_controleMaand_negatieve_waarde_geeft_illegalArgument_exception() {
		datum.setMaand(-1);
	}
	/**
	 * testen op een te hoge maximumwaarde van de methode setMaand
	 * <br>deze moet een exception geven
	 * @throws IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_setMaand_controleMaand_groter_dan_max_toegelaten_12_geeft_illegalArgument_exception() {
		datum.setMaand(55);
	}
	/**
	 * testen op een te lage minimumwaarde van de methode setMaand
	 * <br>deze moet een exception geven
	 * @throws IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_setMaand_controleMaand_kleiner_dan_min_toegelaten_1_geeft_illegalArgument_exception() {
		datum.setMaand(0);
	}
	/**
	 * testen op een geldige waarde van de methode setJaar
	 */
	@Test
	public void test_setJaar_controleJaar_geldig_jaar_wordt_aanvaard() {
		datum.setJaar(2012);
		assertEquals(2012,datum.getJaar());
	}
	/**
	 * testen methode om een Datumobject een geldige waarde te geven
	 * @throws IllegalArgumentException
	 */
	@Test
	public void test_setDatum_geldige_datum_wordt_aanvaard(){
		Datum datum = new Datum(1,10,2012);
		assertEquals(1,datum.getDag());
		assertEquals(10,datum.getMaand());
		assertEquals(2012,datum.getJaar());
	}
	/**
	 * testen methode om een Datumobject een geldige waarde te geven
	 * <br> foutieve input dag moet een exception geven
	 * @throws IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_setDatum_foutieve_dag_geeft_illegalArgument_exception(){
		datum.setDag(55);
		datum.setMaand(10);
		datum.setJaar(2012);
		assertEquals(1,datum.getDag());
		assertEquals(10,datum.getMaand());
		assertEquals(2012,datum.getJaar());
	}
	/**
	 * testen methode om een Datumobject een geldige waarde te geven
	 * <br> foutieve input maand moet een exception geven
	 * @throws IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_setDatum_foutieve_maand_geeft_illegalArgument_exception(){
		datum.setDag(1);
		datum.setMaand(88);
		datum.setJaar(2012);
		assertEquals(1,datum.getDag());
		assertEquals(10,datum.getMaand());
		assertEquals(2012,datum.getJaar());
	}
	/**
	 * test methode kleinerDan
	 * <br> returns true indien eerste datum kleiner is dan tweede datum
	 */
	@Test
	public void test_kleiner_dan(){
		assertTrue("eerste datum is kleiner dan tweede datum",datum2.kleinerDan(datum));
	}
	/**
	 * test methode kleinerDan
	 * <br> returns false indien eerste datum groter is dan tweede datum
	 */
	@Test
	public void test_kleiner_dan_eerste_goter_dan_tweede(){
		assertFalse("eerste datum is groter dan tweede datum",datum.kleinerDan(datum2));
	}
	/**
	 * testen methode compareTo return false indien beide waarde verschillend zijn
	 */
	@Test
	public void test_equals_true_als_datum1_en_datum2_gelijk_zijn(){
		assertFalse("beide waarden zijn verschillend",datum.equals(datum2));
		assertFalse("beide waarden zijn verschillend",datum2.equals(datum));
	}
}
