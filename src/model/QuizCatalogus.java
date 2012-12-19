package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import statePattern.InConstructieStatus;
import utils.datum.Datum;

import model.enumKlassen.Leraar;
import model.enumKlassen.QuizStatussen;

/**
 * Deze klasse bevat een catalogus van alle bestaande quizen<br>
 * 
 * @author Noella Michiels aangepast door Dirk Huysmans
 * @version oktober 2012
 * 
 */
public class QuizCatalogus extends FileContainer{
	private List<Quiz> lijstQuizCatalogus = new ArrayList<Quiz>();	
	//private final static String OPDRACHTFILE = "Opdrachten.txt";

	public List<Quiz> getLijstQuizCatalogus(){
		return lijstQuizCatalogus;
	}
	
	/**
	 * voeg een quiz toe
	 * @param quiz
	 *            quiz is een object van het type Quiz
	 */
	public void voegQuizToe(Quiz quiz) throws Exception{
		try{
			
			for(int i = 0; i < this.lijstQuizCatalogus.size(); i ++){
				if(this.lijstQuizCatalogus.get(i).getOnderwerp().contentEquals(quiz.getOnderwerp())){
					throw new Exception("Deze quiz bestaat reeds.");
				}
			}
			lijstQuizCatalogus.add(quiz);
		}
		catch (Exception e){
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * verwijder een quiz toe
	 * 
	 * @param quiz		quiz is een object van het type Quiz
	 */
	public void verwijderQuiz(Quiz quiz) {
		lijstQuizCatalogus.remove(quiz);
	}

	@Override
	public String toString() {
		String catalogus = "";
		for (Quiz quiz : lijstQuizCatalogus) {
			catalogus += quiz + "\n";
		}
		return catalogus;
	}

	@Override
	public String getFile() {
		return "quiz.txt";
	}

	public String getFileQuizOpdracht(){
		return "quizopdracht.txt";
	}
	
	
	@Override
	public void wegschrijven() throws IOException, Exception {
		BufferedWriter bufferedWriter = null;
		BufferedWriter bw = null;
		
		try {
			//aanmaken schrijfbuffer met meegeven bestandsnaam
			bufferedWriter = new BufferedWriter(new FileWriter(new File(getDirectory(),getFile())));
			bw = new BufferedWriter(new FileWriter(new File(getDirectory(),getFileQuizOpdracht())));
			
			for (Quiz  quiz : lijstQuizCatalogus){
				bufferedWriter.write(quiz.getOnderwerp() + "," + 
			                         quiz.getMinLeerjaar() + "," +
			                         quiz.getMaxLeerjaar() + "," +
			                         quiz.isTest() + "," +
			                         quiz.isUniekeDeelname() + "," +
			                         quiz.getQuizStatus() + "," +
			                         quiz.getDatum().getDatumInEuropeesFormaat(quiz.getDatum()) + "," +
			                         quiz.getLeraar() + "\n");
				for (QuizOpdracht qo : quiz.getQuizOpdracht()) {
					bw.write(quiz.getOnderwerp() + "," + qo.getOpdracht() + "," + qo.getMaxScore() +"\n");
				}
			}

			
		}
		catch (IOException e) {
			throw new IOException(e.getMessage());
		}
		catch (Exception e){
			throw new Exception(e.getMessage());
		}
		finally {
			bufferedWriter.write("EINDE");
			bufferedWriter.flush();
			bufferedWriter.close();
			bw.write("EINDE");
			bw.flush();
			bw.close();
		}
		
	}

	@Override
	public void maakObjectVanLijn(String[] velden) throws Exception {
		String onderwerp = velden[0];
		int minLeerJaar = Integer.parseInt(velden[1]);
		int maxLeerJaar = Integer.parseInt(velden[2]);
		boolean isTest = velden[3].equals("true")?true:false;
		boolean isUniekeDeelname  = velden[4].equals("true")?true:false;
		String quizStatus = velden[5];
		Datum datum = new Datum(velden[6]);
		String leraar = velden[7];
		//QuizStatussen quizStatusEnum = QuizStatussen.valueOf(quizStatus);
		Leraar auteurEnum = Leraar.valueOf(leraar);
		//if((QuizStatus)quizStatus instanceof InConstructieStatus)){
			
		//}
		//Quiz quiz = new Quiz(onderwerp, minLeerJaar, maxLeerJaar, isUniekeDeelname,isTest, quizStatusEnum, datum, auteurEnum);
		Quiz quiz = new Quiz(onderwerp, minLeerJaar, maxLeerJaar, isUniekeDeelname,isTest);
		this.lijstQuizCatalogus.add(quiz);
	}

}