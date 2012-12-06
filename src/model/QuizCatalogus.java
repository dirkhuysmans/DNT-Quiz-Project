package model;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import java.io.AppendableObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utils.datum.Datum;

import model.enumKlassen.Leraar;
import model.enumKlassen.QuizStatussen;

/**
 * Deze klasse bevat een catalogus van alle bestaande quizen<br>
 * 
 * @author Noella Michiels
 * @version oktober 2012
 * 
 */
public class QuizCatalogus extends FileContainer{
	private List<Quiz> lijstQuizCatalogus = new ArrayList();	
	//private final static String OPDRACHTFILE = "Opdrachten.txt";

	public List<Quiz> getLijstQuizCatalogus(){
		return lijstQuizCatalogus;
	}
	
	/**
	 * voeg een quiz toe
				System.out.println(this.lijstQuizCatalogus.get(i).getOnderwerp());
				System.out.println(quiz.getOnderwerp());
	 * 
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
				/*
				String opdrachten = "";
				Class klasse = quiz.getOpdrachten().get(0).getClass();
				String kl = klasse+"";
				
				if(kl.contains("EenvoudigeOpdracht")){
					for(int i = 0; i < quiz.getOpdrachten().size(); i ++){
						 opdrachten += quiz.getOpdrachten().get(i).getVraag() + "/" + 
								 	   quiz.getOpdrachten().get(i).getAntwoord();
						 if(i < quiz.getOpdrachten().size()){
							 opdrachten += ",";
						 }
					 }
					opdrachten += "STOP/";
				}
				*/
				bufferedWriter.write(lijstQuizCatalogus.indexOf(quiz) + "," + quiz.getOnderwerp() + "," + 
			                         quiz.getMinLeerjaar() + "," +
			                         quiz.isTest() + "," +
			                         quiz.isUniekeDeelname() + "," +
			                         quiz.getQuizStatus() + "," +
			                         quiz.getDatum().getDatumInEuropeesFormaat(quiz.getDatum()) + "," +
			                         quiz.getLeraar() + "\n");
			                         //quiz.getOpdrachten().get(0).getClass() + "," +  
									 //opdrachten + "," +  "\n");
									 //quiz.getOpdrachten() +  "\n");
				for (QuizOpdracht qo : quiz.getQuizOpdracht()) {
					bw.write(lijstQuizCatalogus.indexOf(quiz) + "," + qo.getOpdracht() + "," + qo.getMaxScore() +"\n");
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
		int quizId = Integer.parseInt(velden[0]);
		String onderwerp = velden[1];
		int leerJaar = Integer.parseInt(velden[2]);
		boolean isTest = velden[3].equals("true")?true:false;
		boolean isUniekeDeelname  = velden[4].equals("true")?true:false;
		String quizStatus = velden[5];
		Datum datum = new Datum(velden[6]);
		//String datum = velden[6];
		
		String leraar = velden[7];
		QuizStatussen quizStatusEnum = QuizStatussen.valueOf(quizStatus);
		Leraar auteurEnum = Leraar.valueOf(leraar);
		Quiz quiz = new Quiz(onderwerp, leerJaar, isUniekeDeelname,isTest, quizStatusEnum, datum, auteurEnum);
		this.lijstQuizCatalogus.add(quizId, quiz);
/*		
		String test = velden[7];
		if(test.contains("EenvoudigeOpdracht")){
			boolean testStop = false;
		
			for (int i = 8 ; !testStop; i ++){
				String[] vraagAntwoord = velden[i].split("/");
				String vraag = vraagAntwoord[0];
				
				if(vraag.equals("STOP")){
					testStop = true;
				}
				else{
					String antwoord = vraagAntwoord[1];
					Opdracht opdracht1 = new EenvoudigeOpdracht(vraag,antwoord);
					QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht1, 2);
				}
				
			}
		}
*/
	}

}
