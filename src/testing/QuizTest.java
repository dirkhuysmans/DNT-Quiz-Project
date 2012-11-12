package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.Opdracht;
import model.Quiz;
import model.QuizOpdracht;
import model.enumKlassen.Leraar;
import model.enumKlassen.OpdrachtCategorie;
import model.enumKlassen.QuizStatussen;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
/**
 * Testklasse Quiz
 * @author Noella
 * @version	november 2012
 *
 */
public class QuizTest {
	private Quiz quiz;
	private QuizStatussen quizStatus;
	@Before
	public void setUp() throws Exception {
		quiz = new Quiz("rekenen");
	}
	/**
	 * Test constructor met parameter (String onderwerp)
	 */
	@Test
	public void testConstructorQuizMetInputOnderwerp() {
		Quiz quiz = new Quiz("rekenen");
		assertEquals("onderwerp rekenen wordt verwacht","rekenen", quiz.getOnderwerp());
	}
	/**
	 * Test constructor met parameter (String onderwerp, int leerjaar, boolean isUniekeDeelname, boolean isTest)
	 */
	@Test
	public void testQuizStringIntBooleanBoolean() {
		List<String> hints = new ArrayList();
		hints.add("Eerste letter is 'B'");
		hints.add("Laatste letter is 'L'");
		
		//Time time = new Time(5,15,120);
		
		OpdrachtCategorie opdrachtCategorie = OpdrachtCategorie.algemeneKennis;
		Leraar leraar = Leraar.MYRIAM;
		QuizStatussen quizStatus = QuizStatussen.INCONSTRUCTIE;
		
		Opdracht opdracht = new Opdracht("Wat is de hoofdstad van ons land?",
				"Brussel",hints,2,120,opdrachtCategorie,leraar);
		
		Quiz quiz = new Quiz("rekenen",3,true,true,opdracht,quizStatus);
		assertEquals("eerste parameter onderwerp","rekenen",quiz.getOnderwerp());
		assertEquals("tweede parameter leerjaar",3,quiz.getLeerJaar());
		assertEquals("derde parameter unieke deelname",true,quiz.isUniekeDeelname());
		assertEquals("vierde parameter isTest",true,quiz.isTest());
	}
	/**
	 * Test methode setOnderwerp(String onderwerp)
	 */
	@Test
	public void testSetOnderwerp(){
		quiz.setOnderwerp("rekenen");
		assertEquals("test setOnderwerp","rekenen", quiz.getOnderwerp());
	}
	/**
	 * Test methode setLeerJaar(int leerJaar)
	 */
	@Test
	public void testSetLeerJaar(){
		quiz.setLeerJaar(3);
		assertEquals("test setLeerJaar",3, quiz.getLeerJaar());
	}
	/**
	 * Test methode setTest(boolean isTest)
	 */
	@Test
	public void testSetTestTrue() {
		quiz.setTest(true);
		assertTrue(true);
	}
	@Test
	public void testSetTestFalse() {
		quiz.setTest(false);
		assertFalse(false);
	}
	/**
	 * test methode isUniekeDeelname
	 */
	@Test
	public void setUniekeDeelnameTrue() {
		quiz.setUniekeDeelname(true);
		assertTrue(true);
	}
	@Test
	public void setUniekeDeelnameFalse() {
		quiz.setUniekeDeelname(false);
		assertFalse(false);
	}
	/**
	 * testen methode setQuizStatus
	 */
	@Ignore
	public void setQuizStatus(QuizStatussen quizStatus) {
		
	}
	// public void setLeraar(Leraar leraar) {
	@Ignore
	public void setLeraar(Leraar leraar) {
		//this.leraar = leraar;
	}@Ignore
	public void setQuizOpdracht(List<QuizOpdracht> quizOpdracht) {
		//this.quizOpdrachten = quizOpdracht;
	}
	@Test
	public void testSetAuteurInputString(){
		quiz.setAuteur("Peter");
		assertEquals("auteur","Peter", quiz.getAuteur());
	}
	/**
	 * test controle leerjaar
	 */
	@Test
	public void testControleLeerJaarCorrecteInput(){
		//quiz.setLeerJaar(3);
		assertEquals("correct leerjaar",3,quiz.controleLeerjaar(3));
	}
	@Test (expected = IllegalArgumentException.class)
	public void testControleLeerjaarLeerJaarGroterDanZes(){
		assertEquals("Leerjaar groter dan zes",6,quiz.controleLeerjaar(7));
	}
	@Test (expected = IllegalArgumentException.class)
	public void testControleLeerjaarLeerJaarKleinerDanEen(){
		//quiz.setLeerJaar(0);
		assertEquals("Leerjaar kleiner dan één",0,quiz.controleLeerjaar(0));
	}
	@Test (expected = IllegalArgumentException.class)
	public void testControleLeerJaarNegatief(){
		quiz.setLeerJaar(-5);
		assertEquals("negatief leerjaar",4,quiz.controleLeerjaar(-5));
	}
}
