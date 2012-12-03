package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import model.FileContainer;
import model.Meerkeuze;
import model.Opdracht;
import model.OpdrachtCatalogus;
import model.Opsomming;
import model.QuizCatalogus;
import model.enumKlassen.Leraar;
import model.enumKlassen.OpdrachtCategorie;

import persistenty.TextDaoFacade;
import view.IO;
import view.Menu;
import view.QuizFrame;

public class OpstartController {
	private Menu menu;
	private JFrame quiz2, quiz, lessenroosterOverzicht, opdrachtOverzicht;
	private ToevoegenQuizController toevoegenQuizController;
	//private InschrijvingController inschrijvingController;
	//private LessenroosterOverzichtController lessenroosterOverzichtController;
	//private  OpdrachtOverzichtController opdrachtOverzichtController;
	public OpstartController(){
		//inlezen tekstbestanden, opvullen containers
		toevoegenQuizController = new ToevoegenQuizController(new TextDaoFacade() );
		//inschrijvingController = new InschrijvingController();
		//lessenroosterOverzichtController = new LessenroosterOverzichtController();
		//opdrachtOverzichtController = new OpdrachtOverzichtController();
		menu = new Menu("Beheren van opdrachten(leraar)","Beheren van quizzen/testen(leraar)","Deelnemen aan quiz(leerling)","Overzicht scores(leraar)","Quiz rapport(deelnemer quiz)","Instellingen van de quiz applicatie");
	}
	public void execute() throws Exception{
		int keuze = menu.getMenuKeuze();
		
		
		QuizCatalogus quizCatalogus = new QuizCatalogus();
		quizCatalogus.lezen();
		
		
		Map <Integer, String>hoofdStadBrazilie = new HashMap<Integer, String>();
		hoofdStadBrazilie.put(1, "Sao Paulo");
		hoofdStadBrazilie.put(2, "Rio de Janeiro");
		hoofdStadBrazilie.put(3, "Basilia");
		hoofdStadBrazilie.put(4, "Curitiba");
		List<String> hints1= new ArrayList<String>();
		hints1.add("Carnavalstad");
		Opdracht opdracht1 = new Meerkeuze("Wat is de hoofdstad van brazilië?","Rio de Janeiro", hoofdStadBrazilie,hints1, 1,
				0, OpdrachtCategorie.algemeneKennis, Leraar.FRANK);
		
		OpdrachtCatalogus opdrachtCatalogus = new OpdrachtCatalogus();
		opdrachtCatalogus.voegOpdrachtToe(opdracht1);
		
		Map <Integer, String>provinciesBelgie = new HashMap<Integer, String>();
		provinciesBelgie.put(1, "Vlaams-Brabant");
		provinciesBelgie.put(2, "Antwerpen");
		provinciesBelgie.put(3, "Limburg");
		provinciesBelgie.put(4, "Oost-Vlaanderen");
		provinciesBelgie.put(5, "West-Vlaanderen");
		provinciesBelgie.put(6, "Waals-Brabant");
		provinciesBelgie.put(7, "Luik");
		provinciesBelgie.put(8, "Henegouwen");
		provinciesBelgie.put(9, "Luxemburg");
		List<String> hints2= new ArrayList<String>();
		hints2.add("Vlaanderen");
		hints2.add("Wallonie");
		Opdracht opdracht2 = new Opsomming("Geef 3 Provincies van België","", provinciesBelgie, false, hints2, 1,
				0, OpdrachtCategorie.algemeneKennis, Leraar.FRANK);
		
		opdrachtCatalogus.voegOpdrachtToe(opdracht2);
		
		
		//OpdrachtCatalogus opdrachtCatalogus = new OpdrachtCatalogus();
		//List<String> stringCatalogus = new ArrayList<String>();
		//opdrachtCatalogus.lezen();
		
	//	System.out.println("quizcatalogus " + quizCatalogus.getLijstQuizCatalogus().get(0).getOnderwerp());
	//	System.out.println("opdrachtCatalogus " + opdrachtCatalogus.getOpdrachtKey(opdracht));
	//	System.out.println("Stringcatalogus " + stringCatalogus.size());
		//System.out.println("opdrachtCatalogus " + opdrachtCatalogus.getOpdracht(0).getAntwoord());
		switch (keuze){
			case 1: //openen lessenroosterOverzicht frame
			        break;
			case 2:	quiz = new QuizFrame(this,toevoegenQuizController,quizCatalogus,opdrachtCatalogus);
	        		quiz.setVisible(true);
	        		//quiz.MAXIMIZED_BOTH;
				    break;
			case 3: //openen lessenroosterOverzicht frame
				    break;
			case 4: //openen opdrachtOverzicht frame
			    break;
			case 5:
				    //opslaan van de nieuwe objecten in tekstbestanden
				    break;
			case 6:
			    //opslaan van de nieuwe objecten in tekstbestanden
			    break;  
			default:
				    if (keuze != menu.getStopWaarde()){
				    	IO.toonStringMetVenster("Je hebt een verkeerde keuze gemaakt!!!");
				    	keuze = menu.getMenuKeuze();
				    }	
		}
	}
	public static void main(String[] args) throws Exception {
		new OpstartController().execute();

	}

}
