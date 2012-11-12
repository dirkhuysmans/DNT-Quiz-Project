package model;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import java.io.AppendableObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Deze klasse bevat een catalogus van alle bestaande quizen<br>
 * 
 * @author Noella Michiels
 * @version oktober 2012
 * 
 */
public class QuizCatalogus {
	private Set<Quiz> quizCatalogus = new HashSet<Quiz>();

	/**
	 * voeg een quiz toe
	 * 
	 * @param quiz
	 *            quiz is een object van het type Quiz
	 */
	public void voegQuizToe(Quiz quiz) {
		quizCatalogus.add(quiz);
	}

	/**
	 * verwijder een quiz toe
	 * 
	 * @param quiz		quiz is een object van het type Quiz
	 */
	public void verwijderQuiz(Quiz quiz) {
		quizCatalogus.remove(quiz);
	}

	@Override
	public String toString() {
		String catalogus = "";
		for (Quiz quiz : quizCatalogus) {
			catalogus += quiz + "\n";
		}
		return catalogus;
	}

	// private AppendableObjectOutputStream obj = null;
	private ObjectOutputStream obj = null;

	public void wegschrijvenNaarFile() {
		try {
			// obj = new AppendableObjectOutputStream(new
			// FileOutputStream("Quizzen.txt"));
			obj = new ObjectOutputStream(new FileOutputStream("Quizzen.ser"));
			obj.writeObject(quizCatalogus);
		} catch (IOException ex) {
			System.out.println("Error om naar de quiz-file te schrijven");
		} finally {
			try {
				obj.close();
			} catch (IOException iox) {
				System.out.println("probleem met het sluiten van de file "
						+ iox.getMessage());
			}
		}
	}

	public void lezenFile() {
		ObjectInputStream input = null;
		try {
			input = new ObjectInputStream(new FileInputStream("Quizzen.ser"));
			Set<Quiz> quizzen = (Set<Quiz>) input.readObject();
			for (Quiz quiz : quizzen) {
				quizCatalogus.add(quiz);
			}
		} catch (EOFException eofx) {
			System.out.println("End of file was reached " + eofx.getMessage());
			return;
		} catch (Exception ex) {
			System.out
					.println("Er is iets fout gegaan met het inlezen van de quizzen "
							+ ex.getMessage());
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
