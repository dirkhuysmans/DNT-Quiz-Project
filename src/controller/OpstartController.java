package controller;

import javax.swing.JFrame;

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
	public void execute(){
		int keuze = menu.getMenuKeuze();
		
		switch (keuze){
			case 1: //openen lessenroosterOverzicht frame
			        break;
			case 2:	quiz = new QuizFrame(this,toevoegenQuizController);
	        		quiz.setVisible(true);
	        		quiz.MAXIMIZED_BOTH;
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
	public static void main(String[] args) {
		new OpstartController().execute();

	}

}
