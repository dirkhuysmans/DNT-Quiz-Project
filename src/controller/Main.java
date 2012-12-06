package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.Meerkeuze;
import model.Opdracht;
import model.OpdrachtCatalogus;
import model.Opsomming;
import model.Quiz;
import model.QuizCatalogus;
import model.enumKlassen.Leraar;
import model.enumKlassen.OpdrachtCategorie;
import model.enumKlassen.QuizStatussen;

public class Main {

	/**
	 * @param args
	 *            mainklasse aangemaakt om opdrachten en quizzen te testen
	 * 
	 */
	public static void main(String[] args) {
		OpdrachtCategorie categorie;
		int keuze = 0;
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		Map <Integer, String>hoofdStadBrazilie = new HashMap<Integer, String>();
		hoofdStadBrazilie.put(1, "Sao Paulo");
		hoofdStadBrazilie.put(2, "Rio de Janeiro");
		hoofdStadBrazilie.put(3, "Brasilia");
		hoofdStadBrazilie.put(4, "Curitiba");
		Opdracht opdracht1 = new Meerkeuze("Wat is de hoofdstad van brazilië?","Rio de Janeiro", hoofdStadBrazilie, null, 1,
				0, OpdrachtCategorie.algemeneKennis, Leraar.FRANK);
		System.out.println("1.Registreren of 2.Inloggen");
		keuze = sc.nextInt();
		if (keuze == 1) {

		} else if (keuze == 2) {
			int k = 0;
			do {
				System.out.println("1.Leraar of 2.Leerling");
				k = sc.nextInt();
				if (k == 1) {
					// ��n of andere functie
				} else {
					// ��n of andere andere functie
				}
			} while (k != 1 && k != 2);

		}
		do {
			System.out.print("Keuzemenu:");
			System.out.println("\n\t1. Beheren van opdrachten");
			System.out.println("\t2. Beheren van Quizzen/testen");
			System.out.println("\t3. Deelnemen aan Quiz");
			System.out.println("\t4. Overzicht scores");
			System.out.println("\t5. Quiz rapport");
			System.out.println("\t6. Instellingen van de quiz applicatie");
			System.out.println("\t7. Stoppen");
			System.out.println("\n\t");
			keuze = sc.nextInt();
			System.out.println("\n");
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
					System.out.print("Aantal pogingen: ");
					int pogingen = sc.nextInt();
					//Opdracht opdracht = new Opsomming(vraag, antwoord,null, null,
					//		pogingen, 0, categorie, null);
					break;
				case 2:
					System.out
							.println("Kies de opdracht die je wil wijzigen uit volgende lijst: ");
					OpdrachtCatalogus opdrachtCatalogus = new OpdrachtCatalogus();
					System.out.println(opdrachtCatalogus);
					int k = sc.nextInt();
					Opdracht op = opdrachtCatalogus.getOpdracht(k);
					if (!op.getQuizOpdrachten().isEmpty()) {
						System.out
								.println("Je kan deze opdracht niet wijzigen omdat ze al gelinkt is aan een quiz");
					} else {
						System.out.print(op);
						System.out
								.print("Voer de nieuwe parameters in voor deze opdracht");
						System.out
								.print("Categorie (1.rekenen, 2.NederlandseTaal, 3.FranseTaal, 4.algemeneKennis): ");
						intCategorie = sc.nextInt();
						categorie = OpdrachtCategorie.convertInt(intCategorie);
						System.out.print("\nvraag: ");
						vraag = sc.nextLine();
						System.out.print("\nantwoord: ");
						antwoord = sc.nextLine();
						System.out.print("Aantal pogingen: ");
						pogingen = sc.nextInt();
						//op = new Opsomming(vraag, antwoord, null, null,
						//		pogingen, 0, categorie, null);
					}
					break;
				}
				break;

			case 2:
				QuizCatalogus catalogus = new QuizCatalogus();
//				catalogus.lezenFile();
				System.out.println(catalogus);

				System.out.println("\n\nOnderwerp: ");
				String onderwerp = sc.nextLine();
				System.out.println("Minimum leerjaar: ");
				int minLeerjaar = sc.nextInt();
				System.out.println("Maximum leerjaar: ");
				int maxLeerjaar = sc.nextInt();
				System.out.println("unieke deelname?(j/n): ");
				String temp = sc.next();
				boolean uniek = temp.equals("j");
				System.out.println("test?(j/n): ");
				temp = sc.next();
				boolean test = temp.equals("j");
				Quiz quiz = new Quiz(onderwerp, maxLeerjaar, maxLeerjaar, uniek, test, QuizStatussen.INCONSTRUCTIE);

				//catalogus.voegQuizToe(quiz);
//				catalogus.wegschrijvenNaarFile();
				break;
			}
		} while (keuze != 7);
	}

}
