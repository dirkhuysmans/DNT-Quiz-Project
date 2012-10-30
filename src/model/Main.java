package model;

import java.util.Scanner;

import model.enumKlassen.OpdrachtCategorie;

public class Main {

	/**
	 * @param args
	 * mainklasse aangemaakt om opdrachten en quizzen te testen
	 * 
	 */
	public static void main(String[] args) {
		OpdrachtCategorie categorie;
		int keuze=0;
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.print("Keuzemenu:");
			System.out.println("\t1. Beheren van opdrachten");
			System.out.println("\t2. Beheren van Quizzen/testen");
			System.out.println("\t3. Deelnemen aan Quiz");
			System.out.println("\t4. Overzicht scores");
			System.out.println("\t5. Quiz rapport");
			System.out.println("\t6. Instellingen van de quiz applicatie");
			System.out.println("\t7. Stoppen");
			System.out.println("\n\t");
			keuze = sc.nextInt();
			System.out.println("\n\n\n");
			switch (keuze) {
			case 1:
				System.out.println("\t1. Aanmaken opdracht");
				System.out.println("\t2. Wijzigen opdracht");
				System.out.println("\t3. Verwijderen opdracht");
				System.out.println("\t4. tonen opdrachten");
				System.out.println("\n\t");

				int tweedeKeuze = sc.nextInt();
				switch (tweedeKeuze) {
				case 1:
					System.out
							.print("Categorie (1.rekenen, 2.NederlandseTaal, 3.FranseTaal, 4.algemeneKennis): ");
					int intCategorie = sc.nextInt();
					categorie = OpdrachtCategorie.convertInt(intCategorie);
					System.out.print("\nvraag: ");
					String vraag = sc.nextLine();
					System.out.print("\nantwoord: ");
					String antwoord = sc.nextLine();
					break;
				case 2:
					System.out
							.println("Kies de opdracht die je wil wijzigen uit volgende lijst: ");
					OpdrachtCatalogus opdrachtCatalogus = new OpdrachtCatalogus();
					System.out.println(opdrachtCatalogus);
					break;
				}

			case 2:
				System.out.println("Iets doen met Quiz en zo");
				Quiz quiz;
				System.out.println("Onderwerp: ");
				String onderwerp = sc.nextLine();
				System.out.println("leerjaar: ");
				int leerjaar = sc.nextInt();
				System.out.println("unieke deelname?(j/n): ");
				String temp = sc.nextLine();
				boolean uniek = temp.equals("j");
				System.out.println("test?(j/n): ");
				temp = sc.nextLine();
				boolean test = temp.equals("j");
				quiz = new Quiz(onderwerp, leerjaar, uniek, test);
				QuizCatalogus quizCatalogus = new QuizCatalogus();
				quizCatalogus.voegQuizToe(quiz);
				quizCatalogus.wegschrijvenNaarFile(quiz);
				break;
			}
		} while (keuze != 7);
	}

}
