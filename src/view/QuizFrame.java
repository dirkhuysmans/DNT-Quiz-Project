package view;

import java.awt.Container;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.ListModel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import controller.*;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

import model.Opdracht;
import model.OpdrachtCatalogus;
import model.enumKlassen.Leraar;
import model.enumKlassen.QuizStatussen;
import model.enumKlassen.OpdrachtCategorie;

public class QuizFrame extends JFrame {
	private OpstartController opstartController;
	private ToevoegenQuizController toevoegenQuizController;
	private JTextField txtOnderwerp;

	public QuizFrame(OpstartController opstartController,
			ToevoegenQuizController toevoegenQuizController) {

		super("Beheren van quizzen/testen(leraar)");
		this.opstartController = opstartController;
		this.toevoegenQuizController = toevoegenQuizController;
		Container container = getContentPane();
		container.setBackground(Color.WHITE);
		container.setLayout(null);
		//
		//
		//
		JLabel lblOnderwerp = new JLabel("Onderwerp :");
		lblOnderwerp.setBounds(36, 24, 112, 15);
		getContentPane().add(lblOnderwerp);
		//
		// tekstveld onderwerp
		//
		txtOnderwerp = new JTextField();
		txtOnderwerp.setBounds(188, 22, 114, 19);
		getContentPane().add(txtOnderwerp);
		txtOnderwerp.setColumns(10);
		//
		// label tekst "Leerjaar :"
		//
		JLabel lblLeerjaar = new JLabel("Leerjaar :");
		lblLeerjaar.setBounds(36, 49, 70, 15);
		getContentPane().add(lblLeerjaar);
		//
		// label tekst "Van"
		//
		JLabel lblVan = new JLabel("Van");
		lblVan.setBounds(118, 49, 39, 15);
		getContentPane().add(lblVan);
		//
		// combobox leerjaren van
		// waarden van 1 tot en met 6
		//
		JComboBox cmbLeerjaarVan = new JComboBox();
		cmbLeerjaarVan.addItem(1);
		cmbLeerjaarVan.addItem(2);
		cmbLeerjaarVan.addItem(3);
		cmbLeerjaarVan.addItem(4);
		cmbLeerjaarVan.addItem(5);
		cmbLeerjaarVan.addItem(6);
		cmbLeerjaarVan.setBounds(188, 44, 50, 24);
		getContentPane().add(cmbLeerjaarVan);
		//
		// label tekt "Tot"
		//
		JLabel lblTot = new JLabel("Tot");
		lblTot.setBounds(118, 76, 39, 20);
		getContentPane().add(lblTot);
		//
		// combobox leerjaren tot
		// waarden van 1 tot en met 6
		//
		JComboBox cmbLeerjaarTot = new JComboBox();
		cmbLeerjaarTot.setBounds(188, 80, 50, 24);
		cmbLeerjaarTot.addItem(1);
		cmbLeerjaarTot.addItem(2);
		cmbLeerjaarTot.addItem(3);
		cmbLeerjaarTot.addItem(4);
		cmbLeerjaarTot.addItem(5);
		cmbLeerjaarTot.addItem(6);
		getContentPane().add(cmbLeerjaarTot);
		//
		// label tekst "Test ?"
		//
		JLabel lblIsTest = new JLabel("Test ?");
		lblIsTest.setBounds(36, 111, 70, 15);
		getContentPane().add(lblIsTest);
		//
		// checkbox isTest : wordt aangevinkt indien de quiz een test is
		//
		JCheckBox chckbxIsTest = new JCheckBox();
		chckbxIsTest.setBounds(185, 103, 27, 23);
		getContentPane().add(chckbxIsTest);
		//
		// tekst label "Unieke deelname ?"
		//
		JLabel lblIsUniekeDeelname = new JLabel("Unieke deelname ?");
		lblIsUniekeDeelname.setBounds(36, 138, 152, 15);
		getContentPane().add(lblIsUniekeDeelname);
		//
		// checkbox isUniekeDeelname
		//
		JCheckBox chckbxIsUniekeDeelname = new JCheckBox();
		chckbxIsUniekeDeelname.setBounds(188, 134, 27, 23);
		getContentPane().add(chckbxIsUniekeDeelname);
		//
		// tekst label "Auteur : "
		//
		JLabel lblAuteur = new JLabel("Auteur :");
		lblAuteur.setBounds(36, 165, 70, 24);
		getContentPane().add(lblAuteur);
		//
		// combobox Leraar
		//
		final JComboBox cmbAuteur = new JComboBox(Leraar.values());
		cmbAuteur.setBounds(188, 165, 140, 24);
		getContentPane().add(cmbAuteur);
		//
		// tekst label "Status : "
		//
		JLabel lblStatus = new JLabel("Status :");
		lblStatus.setBounds(36, 196, 70, 24);
		getContentPane().add(lblStatus);
		//
		// combobox QuizStatussen
		//
		final JComboBox cmbStatus = new JComboBox(QuizStatussen.values());
		cmbStatus.setBounds(188, 196, 140, 24);
		getContentPane().add(cmbStatus);
		//
		// tekst button "Registreer quiz"
		//
		JButton btnRegistreerQuiz = new JButton("Registreer Quiz");
		btnRegistreerQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String onderwerp = txtOnderwerp.getText();
				int leerJaar = 5;
				boolean isUniekeDeelname = true;
				boolean isTest = true;
				Leraar auteur = (Leraar) cmbAuteur.getSelectedItem();
				QuizStatussen quizStatus = (QuizStatussen) cmbStatus
						.getSelectedItem();
				try {
					QuizFrame.this.toevoegenQuizController.maakQuiz(onderwerp,
							leerJaar, isUniekeDeelname, isTest, quizStatus,
							auteur);
				} catch (IllegalArgumentException iaex) {
					IO.toonStringMetVenster(iaex);
					// e.printStackTrace();
				} catch (Exception ex) {
					IO.toonStringMetVenster(ex + "");
					// ex.printStackTrace();
				}
				setVisible(false);
				toonMenu();
			}
		});
		btnRegistreerQuiz.setBounds(319, 44, 174, 25);
		getContentPane().add(btnRegistreerQuiz);
		//
		// tekst label "Annuleer"
		//
		JButton btnAnnuleer = new JButton("Annuleer");
		btnAnnuleer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				toonMenu();

			}
		});
		btnAnnuleer.setBounds(319, 74, 117, 25);
		getContentPane().add(btnAnnuleer);
		//
		// combobox alle types (enum OpdrachtCategorie)
		//
		JComboBox cmbType = new JComboBox(OpdrachtCategorie.values());
		cmbType.setBounds(34, 235, 154, 24);
		getContentPane().add(cmbType);
		//
		// combobox alle opdrachten
		//
		List<Opdracht> opdrachtenCatalogus = new ArrayList<Opdracht>();

		String catalogus = "";

		System.out.println(catalogus.toString());
		String[] types = { "Eenvoudige opdracht", "Meerkeuze", "Opsomming",
				"Reproductie" };
		JComboBox typeOpdrachten = new JComboBox(types);
		typeOpdrachten.setBounds(198, 235, 154, 24);
		getContentPane().add(typeOpdrachten);
		typeOpdrachten.setVisible(true);
		
		
		List<Opdracht> opdrachten = OpdrachtCatalogus.opdrachtenCatalogus;
		String [] 
		//opdrachtCatalogus.get
		// ListModel model = new ListModel();

		JList opdrachtenLijst = new JList(opdrachten);
		for (Opdracht opdracht : opdrachten) {
			Component opdrachtAlsComponent = (Component) opdracht;
			opdrachtenLijst.
		}
		/*
		 * JComboBox cmbAlleOpdrachten = new JComboBox(types);
		 * cmbAlleOpdrachten.setBounds(198, 235, 154, 24);
		 * getContentPane().add(cmbAlleOpdrachten);
		 * cmbAlleOpdrachten.setVisible(true);
		 */

	}

	private void toonMenu() {
		opstartController.execute();
	}

	public static void main(String[] args) {
		// PlanningFrame pl = new PlanningFrame(new OpstartController());
		// pl.setVisible(true);
	}
}
