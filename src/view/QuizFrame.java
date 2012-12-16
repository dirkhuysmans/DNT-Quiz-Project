package view;

import java.awt.Container;
import java.awt.event.*;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import controller.*;

import javax.swing.JTextField;
import javax.swing.JCheckBox;

import model.OpdrachtCatalogus;
import model.Quiz;
import model.QuizCatalogus;
import model.enumKlassen.Leraar;
import model.enumKlassen.QuizStatussen;
import model.enumKlassen.OpdrachtCategorie;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class QuizFrame extends JFrame {
	private OpstartController opstartController;
	private ToevoegenQuizController toevoegenQuizController;
	private JTextField txtOnderwerp;
	private List<Quiz> lijstQuizCatalogus = new ArrayList();
	private JTextField maxScore;
	private JTextField totScore; 

	public QuizFrame(OpstartController opstartController,
			final ToevoegenQuizController toevoegenQuizController,
			QuizCatalogus quizCatalogus, OpdrachtCatalogus opdrachtCatalogus) {
		super("Beheren van quizzen/testen(leraar)");
		this.opstartController = opstartController;
		this.toevoegenQuizController = toevoegenQuizController;
		Container container = getContentPane();
		container.setBackground(Color.WHITE);
		container.setLayout(null);
		container.setSize(50, 50);
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
		final JComboBox cmbLeerjaarVan = new JComboBox();
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
		final JComboBox cmbLeerjaarTot = new JComboBox();
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
		final JCheckBox boxIsTest = new JCheckBox();
		boxIsTest.setBounds(185, 103, 27, 23);
		getContentPane().add(boxIsTest);
		//
		// tekst label "Unieke deelname ?"
		//
		JLabel lblIsUniekeDeelname = new JLabel("Unieke deelname ?");
		lblIsUniekeDeelname.setBounds(36, 138, 152, 15);
		getContentPane().add(lblIsUniekeDeelname);
		//
		// checkbox isUniekeDeelname
		//
		final JCheckBox boxUniekeDeelname = new JCheckBox();
		boxUniekeDeelname.setBounds(188, 134, 27, 23);
		getContentPane().add(boxUniekeDeelname);
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
			@Override
			public void actionPerformed(ActionEvent e) {

				String onderwerp = txtOnderwerp.getText();
				int minLeerjaar = Integer.parseInt((String) cmbLeerjaarVan
						.getSelectedItem());
				int maxLeerjaar = Integer.parseInt((String) cmbLeerjaarTot
						.getSelectedItem());
				boolean isUniekeDeelname = boxUniekeDeelname.isSelected();
				boolean isTest = boxIsTest.isSelected();
				Leraar auteur = (Leraar) cmbAuteur.getSelectedItem();
				QuizStatussen quizStatus = (QuizStatussen) cmbStatus
						.getSelectedItem();
				if (maxLeerjaar < minLeerjaar) {
					JOptionPane.showMessageDialog(null, "Het maximum leerjaar kan niet" +
							" minder zijn dan het minimum leerjaar");
				} else {
					try {
						toevoegenQuizController.maakQuiz(onderwerp,
								minLeerjaar, maxLeerjaar, isUniekeDeelname,
								isTest, quizStatus, auteur);
					} catch (IllegalArgumentException iaex) {
						IO.toonStringMetVenster(iaex);
						// e.printStackTrace();
					} catch (Exception ex) {
						IO.toonStringMetVenster(ex + "");
						// ex.printStackTrace();
					}
					setVisible(false);
					try {
						toonMenu();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnRegistreerQuiz.setBounds(319, 44, 174, 25);
		getContentPane().add(btnRegistreerQuiz);
		//
		// tekst label "Annuleer"
		//
		JButton btnAnnuleer = new JButton("Annuleer");
		btnAnnuleer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					toonMenu();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		btnAnnuleer.setBounds(319, 74, 117, 25);
		getContentPane().add(btnAnnuleer);
		//
		// combobox alle types
		//
		JComboBox cmbType = new JComboBox();
		cmbType.setBounds(34, 235, 154, 24);
		cmbType.addItem("");
		cmbType.addItem("Meerkeuze");
		cmbType.addItem("Opsomming");
		cmbType.addItem("Reproductie");
		cmbType.addItem("Eenvoudige Opdracht");
		getContentPane().add(cmbType);
		//
		// combobox alle opdrachten
		//

		JComboBox cmbAlleOpdrachten = new JComboBox();
		cmbAlleOpdrachten.setBounds(198, 235, 357, 24);
		cmbAlleOpdrachten.addItem("");
		for (int i = 0; i < opdrachtCatalogus.getOpdrachtenCatalogus().size(); i++) {
			cmbAlleOpdrachten.addItem(opdrachtCatalogus
					.getOpdrachtenCatalogus().get(i));
		}
		getContentPane().add(cmbAlleOpdrachten);
		//
		// combobox alle tcategorieën (enum OpdrachtCategorie)
		//
		JComboBox cmbCategorie = new JComboBox();
		cmbCategorie.setBounds(567, 235, 154, 24);
		cmbCategorie.addItem("");
		cmbCategorie.addItem(OpdrachtCategorie.algemeneKennis);
		cmbCategorie.addItem(OpdrachtCategorie.FranseTaal);
		cmbCategorie.addItem(OpdrachtCategorie.NederlandseTaal);
		cmbCategorie.addItem(OpdrachtCategorie.rekenen);
		getContentPane().add(cmbCategorie);

		final DefaultListModel model = new DefaultListModel();				
		for (int i = 0; i < opdrachtCatalogus.getOpdrachtenCatalogus().size(); i++) {
			model.addElement(opdrachtCatalogus.getOpdrachtenCatalogus().get(i));
		}
		final DefaultListModel gekozenOpdrachtModel = new DefaultListModel();
		
		final JList jListOpdracht = new JList(model);
		jListOpdracht.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jListOpdracht.setBackground(Color.LIGHT_GRAY);
		jListOpdracht.setBounds(43, 340, 450, 83);
		getContentPane().add(jListOpdracht);
				
		final JList jListGekozenOpdracht = new JList();
		jListGekozenOpdracht
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jListGekozenOpdracht.setBackground(Color.LIGHT_GRAY);
		jListGekozenOpdracht.setBounds(616, 348, 317, 77);
		getContentPane().add(jListGekozenOpdracht);
				
		//
		// button om naar rechts te doen
		//
		JButton btnToTheRight = new JButton(">");
		btnToTheRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				System.out.println(jListOpdracht.getSelectedValue());
				gekozenOpdrachtModel.addElement(jListOpdracht.getSelectedValue());
				jListGekozenOpdracht.clearSelection();
				jListGekozenOpdracht.setModel(gekozenOpdrachtModel);
				model.removeElement(jListOpdracht.getSelectedValue());
				jListOpdracht.clearSelection();
				jListOpdracht.setModel(model);
			}
		});
		btnToTheRight.setBounds(502, 361, 60, 25);
		getContentPane().add(btnToTheRight);
				
		//
		// button om naar links te doen
		//
		
		JButton btnToTheLeft = new JButton("<");
		btnToTheLeft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {						
				model.addElement(jListGekozenOpdracht.getSelectedValue());
				jListOpdracht.clearSelection();
				jListOpdracht.setModel(model);				
				gekozenOpdrachtModel.removeElement(jListGekozenOpdracht.getSelectedValue());
				jListGekozenOpdracht.clearSelection();
				jListGekozenOpdracht.setModel(gekozenOpdrachtModel);
			}
		});
		btnToTheLeft.setBounds(505, 398, 57, 25);
		getContentPane().add(btnToTheLeft);
		//
		// label met tekst "Vraag"
		//
		JLabel lblVraag = new JLabel("Vraag");
		lblVraag.setBounds(606, 322, 70, 24);
		getContentPane().add(lblVraag);
		//
		// label met tekst "MaxScore
		//
		JLabel lblMaxscore = new JLabel("MaxScore");
		lblMaxscore.setBounds(831, 322, 102, 24);
		getContentPane().add(lblMaxscore);
		//
		// label met tekst MaxScore input
		//
		JLabel lblMaxscoreInput = new JLabel("MaxScore");
		lblMaxscoreInput.setBounds(606, 271, 70, 15);
		getContentPane().add(lblMaxscoreInput);
		//
		// tekstveld met berekening maxScore
		//
		maxScore = new JTextField();
		maxScore.setBounds(730, 269, 114, 19);
		getContentPane().add(maxScore);
		maxScore.setColumns(10);
		//
		// label met teks "TotScore"
		//
		JLabel lblTotscore = new JLabel("TotScore");
		lblTotscore.setBounds(606, 298, 70, 15);
		getContentPane().add(lblTotscore);
		//
		// tekstveld met totaal score
		//
		totScore = new JTextField();
		totScore.setBounds(730, 296, 114, 19);
		getContentPane().add(totScore);
		totScore.setColumns(10);		
	}

	private void toonMenu() throws Exception {		
		opstartController.execute();
	}

	public static void main(String[] args) {
		// PlanningFrame pl = new PlanningFrame(new OpstartController());
		// pl.setVisible(true);
	}
}
