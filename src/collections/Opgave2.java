package collections;
import model.*;
import java.util.*;

public class Opgave2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Opdracht opdracht1 = new EenvoudigeOpdracht("Wat is de hoofdstad van Franrijk?","Parijs");
		Opdracht opdracht2 = new EenvoudigeOpdracht("Wat is de hoodstad van Spanje?","Madrid");
		Opdracht opdracht3 = new EenvoudigeOpdracht("Wat is de hoofdstad van Itali�?","Rome");
		Opdracht opdracht4 = new EenvoudigeOpdracht("Wat is de hoodstad van Portugal?","Lissabon");
		Opdracht opdracht5 = new EenvoudigeOpdracht("Hoeveel provincies telt Belgi�?","Tien");
		
		Quiz quiz1 = new Quiz("Hoofdsteden Europa");
		Quiz quiz2 = new Quiz("Aardrijkskunde");
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz1, opdracht1, 2);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz1, opdracht2, 2);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz1, opdracht3, 2);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz1, opdracht4, 2);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz2, opdracht1, 4);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz2, opdracht3, 3);
		QuizOpdracht.koppelOpdrachtAanQuiz(quiz2, opdracht5, 3);
		
		ArrayList <Opdracht> opdrachtenQuiz1 = quiz1.getOpdrachten();
		ArrayList <Opdracht> opdrachtenQuiz2 = quiz2.getOpdrachten();
		Set <Opdracht> opdrachtenVanQuiz1 = new HashSet<Opdracht>(opdrachtenQuiz1);
		Set <Opdracht> opdrachtenVanQuiz2 = new HashSet<Opdracht>(opdrachtenQuiz2);
		
		Set <Opdracht> gezamelijkeOpdrachten = new HashSet<Opdracht>(opdrachtenVanQuiz1);
		gezamelijkeOpdrachten.retainAll(opdrachtenVanQuiz2);
		
		System.out.println(gezamelijkeOpdrachten);
	

	}

}
