package view;

import java.awt.Container;
import java.awt.Window;
import java.awt.event.*;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import controller.*;

import javax.swing.JTextField;
import javax.swing.JCheckBox;

import model.Opdracht;
import model.OpdrachtCatalogus;
import model.Quiz;
import model.QuizCatalogus;
import model.enumKlassen.Leraar;
import model.enumKlassen.QuizStatussen;
import model.enumKlassen.OpdrachtCategorie;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import statePattern.QuizStatus;

public class QuizFrame extends JFrame {
	QuizStatus quizStatus;
	private OpstartController opstartController;
	private ToevoegenQuizController toevoegenQuizController;
	private ToevoegenOpdrachtController toevoegenOpdrachtController;
	private JTextField txtOnderwerp;
	private List<Quiz> lijstQuizCatalogus = new ArrayList();
	private JTextField maxScore;
	private JTextField totScore;
	List<Opdracht> opdrachten = null;
	List<Opdracht> gekozenOpdrachten = new ArrayList<Opdracht>();
	boolean dubbelOpdracht = false;
	JList jListOpdracht = null;
	JList jListGekozenOpdracht = null;
	DefaultListModel model = null;
	JComboBox cmbType = null;
	JComboBox cmbCategorie = null;
	JScrollPane jOpdrachtPane = null;
	JScrollPane jGekozenOpdrachtPane = null;
	JComboBox cmbLeerjaarTot = null;
	JComboBox cmbLeerjaarVan = null;

	public QuizFrame(OpstartController opstartController,
			final ToevoegenQuizController toevoegenQuizController,
			final ToevoegenOpdrachtController toevoegenOpdrachtController,
			QuizCatalogus quizCatalogus, OpdrachtCatalogus opdrachtCatalogus) {
		super("Beheren van quizzen/testen(leraar)");
		this.opstartController = opstartController;
		this.toevoegenQuizController = toevoegenQuizController;
		this.toevoegenOpdrachtController = toevoegenOpdrachtController;
		opdrachten = toevoegenOpdrachtController.getAlleOpdrachten();
		Container container = getContentPane();
		container.setBackground(Color.WHITE);
		GroupLayout layout = new GroupLayout(container);
		container.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

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
		cmbLeerjaarVan = new JComboBox();
		for (int i = 1; i <= 6; i++) {
			cmbLeerjaarVan.addItem(i);
		}
		cmbLeerjaarVan.setBounds(188, 44, 50, 24);
//		cmbLeerjaarVan.add
		cmbLeerjaarVan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Integer.parseInt(cmbLeerjaarTot.getSelectedItem().toString())<Integer.parseInt(cmbLeerjaarVan.getSelectedItem().toString())){
				cmbLeerjaarTot.setSelectedItem(Integer.parseInt(cmbLeerjaarVan.getSelectedItem().toString()));
				}
			}
		});
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
		cmbLeerjaarTot = new JComboBox();
		for (int i = 1; i <= 6; i++) {
			cmbLeerjaarTot.addItem(i);
		}
		cmbLeerjaarTot.setBounds(188, 80, 50, 24);
		cmbLeerjaarTot.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Integer.parseInt(cmbLeerjaarVan.getSelectedItem().toString())>Integer.parseInt(cmbLeerjaarTot.getSelectedItem().toString())){
				cmbLeerjaarVan.setSelectedItem(Integer.parseInt(cmbLeerjaarTot.getSelectedItem().toString()));
				}
			}
		});
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
		// final JComboBox cmbStatus = new JComboBox(QuizStatussen.values() oo);
		final JComboBox cmbStatus = new JComboBox();
		cmbStatus.addItem("inConstructie");
		cmbStatus.addItem("afgewerkt");
		cmbStatus.addItem("opengesteld");
		cmbStatus.addItem("laatsteKans");
		cmbStatus.addItem("afgesloten");
		/**
		 * 
		 */

		JLabel lblDubbeleOpdracht = new JLabel(
				"Opdracht kan verschillende keer toegevoegd worden");
		lblDubbeleOpdracht.setBounds(36, 300, 330, 15);
		getContentPane().add(lblDubbeleOpdracht);
		//
		// checkbox isUniekeDeelname
		//
		final JCheckBox boxDubbeleOpdracht = new JCheckBox();
		boxDubbeleOpdracht.setBounds(340, 300, 27, 15);
		getContentPane().add(boxDubbeleOpdracht);
		boxDubbeleOpdracht.addItemListener(new CheckBoxListener(
				boxDubbeleOpdracht));

		/**
		 * 
		 */
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
				int minLeerjaar = Integer.parseInt(cmbLeerjaarVan
						.getSelectedItem() + "");
				int maxLeerjaar = Integer.parseInt(cmbLeerjaarTot
						.getSelectedItem() + "");
				boolean isUniekeDeelname = boxUniekeDeelname.isSelected();
				boolean isTest = boxIsTest.isSelected();
				Leraar auteur = (Leraar) cmbAuteur.getSelectedItem();
				String quizStatus = cmbStatus.getSelectedItem() + "";

				if (maxLeerjaar < minLeerjaar) {
					JOptionPane.showMessageDialog(null,
							"Het maximum leerjaar kan niet"
									+ " minder zijn dan het minimum leerjaar");
				} else {
					try {
						toevoegenQuizController.maakQuiz(onderwerp,
								minLeerjaar, maxLeerjaar, isUniekeDeelname,
								isTest, quizStatus, auteur, gekozenOpdrachten);
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
		final JComboBox cmbType = new JComboBox();
		cmbType.setBounds(34, 235, 154, 25);
		List<String> types = toevoegenOpdrachtController.getOpdrachtenTypes();
		cmbType.addItem("");
		for (String type : types) {
			cmbType.addItem(type);
		}
		cmbType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String geselecteerdType = cmbType.getSelectedItem().toString();
				try {
					if (!geselecteerdType.equals("")) {
						opdrachten = toevoegenOpdrachtController
								.getOpdrachtenPerType(geselecteerdType);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				model.clear();
				for (Opdracht opdracht : opdrachten) {
					model.addElement(opdracht);
				}
				jListOpdracht.clearSelection();
				jListOpdracht.setModel(model);
			}
		});
		getContentPane().add(cmbType);

		JButton btnAlleOpdrachten = new JButton("Alle Opdrachten");
		btnAlleOpdrachten.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cmbType.setSelectedItem("");
				cmbCategorie.setSelectedItem("");
				opdrachten = toevoegenOpdrachtController.getAlleOpdrachten();
				model.clear();
				for (Opdracht opdracht : opdrachten) {
					model.addElement(opdracht);
				}
				jListOpdracht.clearSelection();
				jListOpdracht.setModel(model);
			}
		});
		btnAlleOpdrachten.setBounds(190, 235, 150, 25);
		getContentPane().add(btnAlleOpdrachten);

		cmbCategorie = new JComboBox();
		cmbCategorie.setBounds(350, 235, 154, 25);
		List<String> categorieen = toevoegenOpdrachtController.getCategorieen();
		cmbCategorie.addItem("");
		for (String categorie : categorieen) {
			cmbCategorie.addItem(categorie);
		}
		cmbCategorie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String geselecteerdeCategorie = cmbCategorie.getSelectedItem()
						.toString();
				try {
					if (!geselecteerdeCategorie.equals("")) {
						opdrachten = toevoegenOpdrachtController
								.getOpdrachtenPerCategorie(geselecteerdeCategorie);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				model.clear();
				if (opdrachten != null) {
					for (Opdracht opdracht : opdrachten) {
						model.addElement(opdracht);
					}
				}
				jListOpdracht.clearSelection();
				jListOpdracht.setModel(model);
			}
		});
		getContentPane().add(cmbCategorie);

		model = new DefaultListModel();

		for (Opdracht opdracht : opdrachten) {
			model.addElement(opdracht);
		}
		final DefaultListModel gekozenOpdrachtModel = new DefaultListModel();

		jListOpdracht = new JList(model);
		jListOpdracht.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jListOpdracht.setBackground(Color.LIGHT_GRAY);
		jOpdrachtPane = new JScrollPane(jListOpdracht);
		jOpdrachtPane.setBounds(43, 350, 375, 150);
		container.add(jOpdrachtPane);

		jListGekozenOpdracht = new JList();
		jListGekozenOpdracht
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jListGekozenOpdracht.setBackground(Color.LIGHT_GRAY);
		jGekozenOpdrachtPane = new JScrollPane(jListGekozenOpdracht);
		jGekozenOpdrachtPane.setBounds(616, 350, 375, 150);
		container.add(jGekozenOpdrachtPane);

		//
		// button om naar rechts te doen
		//
		JButton btnToTheRight = new JButton(">");
		btnToTheRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// System.out.println(jListOpdracht.getSelectedValue());

				if (!gekozenOpdrachten.isEmpty()
						&& jListOpdracht.getSelectedValue()
								.equals(gekozenOpdrachten.get(gekozenOpdrachten
										.size() - 1)) && dubbelOpdracht) {
					JOptionPane
							.showMessageDialog(
									null,
									"Je kan niet twee keer na elkaar dezelfde opdracht toevoegen!",
									"WAARSCHUWING", JOptionPane.WARNING_MESSAGE);
				} else {
					gekozenOpdrachtModel.addElement(jListOpdracht
							.getSelectedValue());
					gekozenOpdrachten.add((Opdracht) jListOpdracht
							.getSelectedValue());
					jListGekozenOpdracht.clearSelection();
					jListGekozenOpdracht.setModel(gekozenOpdrachtModel);

					if (!dubbelOpdracht) {
						model.removeElement(jListOpdracht.getSelectedValue());
						jListOpdracht.clearSelection();
						jListOpdracht.setModel(model);
					}
				}
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
				gekozenOpdrachten.remove((Opdracht) jListGekozenOpdracht
						.getSelectedValue());
				gekozenOpdrachtModel.removeElement(jListGekozenOpdracht
						.getSelectedValue());
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

	class CheckBoxListener implements ItemListener {
		JCheckBox boxDubbeleOpdracht = null;

		public CheckBoxListener(JCheckBox boxDubbeleOpdracht) {
			this.boxDubbeleOpdracht = boxDubbeleOpdracht;
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (boxDubbeleOpdracht.isSelected()) {
				dubbelOpdracht = true;
			} else {
				dubbelOpdracht = false;
			}
		}
	}
}
