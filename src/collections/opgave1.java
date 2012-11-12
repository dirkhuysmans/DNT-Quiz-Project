package collections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import model.Leerling;
import model.LeerlingContainer;
import model.Quiz;
import model.QuizCatalogus;

/**
 * 
 * @author thijs.maes
 * 
 */
public class opgave1 {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc  = new Scanner(System.in);
		Map<String, String> quizDeelname = new HashMap<String, String>();
		Set<Leerling> eersteLeerjaar = new HashSet<Leerling>();
		Set<Leerling> tweedeLeerjaar = new HashSet<Leerling>();
		Set<Leerling> derdeLeerjaar = new HashSet<Leerling>();
		Set<Leerling> vierdeLeerjaar = new HashSet<Leerling>();
		Set<Leerling> vijfdeLeerjaar = new HashSet<Leerling>();
		Set<Leerling> zesdeLeerjaar = new HashSet<Leerling>();
		QuizCatalogus catalogus = new QuizCatalogus();
		//Quiz quiz1 = new Quiz("rekenen", 4, true, false);
		//Quiz quiz2 = new Quiz("schrijven", 1, true, false);
		//Quiz quiz3 = new Quiz("geschiedenis", 5, false, false);
		//catalogus.voegQuizToe(quiz1);
		//catalogus.voegQuizToe(quiz2);
		//catalogus.voegQuizToe(quiz3);
		
		LeerlingContainer container = new LeerlingContainer();
		try {
			Leerling leerling1 = new Leerling("Jantje", 3);
			Leerling leerling2 = new Leerling("Piet", 1);
			Leerling leerling3= new Leerling("Margo", 6);
			Leerling leerling4= new Leerling("Kevin", 4); 
			Leerling leerling5 = new Leerling("Stijn", 4);
			Leerling leerling6 = new Leerling("Noor", 2);
			Leerling leerling7 = new Leerling("Vic", 3);
			Leerling leerling8 = new Leerling("Cas", 5);
			eersteLeerjaar.add(leerling2);
			tweedeLeerjaar.add(leerling6);
			derdeLeerjaar.add(leerling1);
			derdeLeerjaar.add(leerling7);
			vierdeLeerjaar.add(leerling4);
			vierdeLeerjaar.add(leerling5);
			vijfdeLeerjaar.add(leerling8);
			zesdeLeerjaar.add(leerling3);
			container.voegLeerlingToe(leerling1);
			container.voegLeerlingToe(leerling2);
			container.voegLeerlingToe(leerling3);
			container.voegLeerlingToe(leerling4);
			container.voegLeerlingToe(leerling5);
			container.voegLeerlingToe(leerling6);
			container.voegLeerlingToe(leerling7);
			container.voegLeerlingToe(leerling8);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(container);
		
		//quizDeelname.put(quiz1.getOnderwerp(), vierdeLeerjaar.toString());
		//quizDeelname.put(quiz2.getOnderwerp(), eersteLeerjaar.toString());
		//quizDeelname.put(quiz3.getOnderwerp(), vijfdeLeerjaar.toString());
		System.out.println(catalogus);
		System.out.println("van welke quiz wil je de deelnemers zien?");
		int temp = sc.nextInt();
		
	}
}
