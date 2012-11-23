
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/*import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.enumKlassen.QuizStatussen;
*/


public abstract class FileContainer {
	
	/**
	 * @author Dirk Huysmans
	 * @throws Exception
	 */
	public void lezen() throws Exception{
		try{
			File bestand = new File(getDirectory(),getFile());
			BufferedReader lezer = new BufferedReader(new FileReader(bestand));
			String lijn = null;
			while(!(lijn=lezer.readLine()).equals("EINDE")){
				toevoegenLijn(lijn);
			}
			lezer.close();
		}
		catch (Exception e){
			throw new Exception(e.getMessage());
		}
	}

	public void toevoegenLijn(String lijn) throws Exception {
		// TODO Auto-generated method stub
		String[] velden = lijn.split(",");
		try {
			maakObjectVanLijn(velden);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
	}
	
	public String getDirectory(){
		return "bestanden";
	}
	
	public abstract String getFile();
	
	public abstract void wegschrijven() throws IOException, Exception;

	public abstract void maakObjectVanLijn(String[] velden) throws Exception;

	
	
	/**
	 * 
	 * @param bestand	naam van het bestand waarin geschreven wordt
	 * @param quiz		object quiz die weggeschreven word
	 * 
	 * @author Noella
	 */
	
/*	public static void schrijvenQuiz(String bestand,Quiz quiz){
		BufferedWriter output = null;
		try {
			output = new BufferedWriter(new FileWriter(new File(bestand)));
			for(int i = 0; i < quiz.getQuizOpdracht().size(); i++){
				output.write(quiz.getOnderwerp() + ";" + 
			                 quiz.getLeerJaar() + ";" +
			                 quiz.getDatum() + ";" +
			                 quiz.getLeraar().name() + ";" +
			                 quiz.getQuizStatus() + ";" +
			                 quiz.isTest() + ";" + 
			                 quiz.isUniekeDeelname() + ";" +
							 quiz.getQuizOpdracht().get(i).getOpdracht().getVraag().toString() + ";" +
							 quiz.getQuizOpdracht().get(i).getOpdracht().getAntwoord().toString() + ";" +
			                 quiz.getQuizOpdracht().get(i).getMaxScore() +"\n"
							 );
			}
			 
	
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * leest het bestand "bestanden\quiz.text"<br> * Deze klasse haalt gegevens uit het tekstbestand "bestanden\quiz.txt" <br>
	 * en zet deze om naar een quiz-object <br>
	 * past tevens de QuizCatalogus aan en koppelt de quiz
	 * 
	 * @param fileName		naam van het bestand
	 * @return quiz			een object van het type Quiz
	 * 
	 * @throws Exception
	 * 
	 * @author Noella
	 */
/*	public static Quiz lezenQuiz (String fileName) throws Exception{
		Scanner lezer = null;
		String tekst = "";
		Quiz quiz = null;
		try {
			lezer = new Scanner(new FileReader(new File(fileName)));
			String lijn="";
			    int i = 1;
				while (lezer.hasNextLine()) {
					lijn = lezer.nextLine();
				
					if (lijn.trim().length() > 0 ){
						if(i == 1){
							String onderwerp = lijn.split(";")[0];
							int leerJaar = Integer.parseInt(lijn.split(";")[1]);
							String quizStatus = lijn.split(";")[4];
							String isTest  = lijn.split(";")[5];
							boolean test = isTest.equals("true")?true:false;
							
							String isUniekeDeelname  = lijn.split(";")[6];
							boolean uniekeDeelname = isUniekeDeelname.equals("true")?true:false;
							
							QuizStatussen status = null;
							//if(quizStatus.equals("INCONSTRUCTIE")){
								//status = quizStatus;
								QuizStatussen.valueOf(quizStatus);
							//}
							quiz = new Quiz(onderwerp,leerJaar,uniekeDeelname,test,status);
							
							QuizCatalogus quizCatalogus = new QuizCatalogus();
							quizCatalogus.voegQuizToe(quiz);
							i ++;
						}
						String vraag = lijn.split(";")[7];
						String antwoord = lijn.split(";")[8];
						Opdracht opdracht = new Opdracht(vraag,antwoord);
						
						QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht, Integer.parseInt(lijn.split(";")[9]));
						
					}
				}
		} 
		catch (Exception ex2) {
				throw new Exception("Leesfout");
		} 
		finally {
				// sluit lezer
				if (lezer != null) {
					lezer.close();
				}
				return quiz;
		}	
	}
	
	
	
	
	
	
	public static void schrijven(String bestand, String info){
		BufferedWriter output = null;
		try {
			output = new BufferedWriter(new FileWriter(new File(bestand)));
			
			output.write(info);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
	
	
	
	//
	
	
		
		
		
		
	public static String lezen (String bestand){
		BufferedReader input = null;
		String catalogus="";
		try {
			input = new BufferedReader(new FileReader(new File(bestand)));
			String lijn="";
			try {
				while((lijn = input.readLine()) != null){
					catalogus += lijn + "\n";
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return catalogus;		
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	*/
}
