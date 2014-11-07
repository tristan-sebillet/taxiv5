package graphic;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import graphic.AR;
import graphic.AS;
import graphic.Saisie;
import graphic.Tarif;
import graphic.main;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;

import graphic.Calcul;
public class taxigraph extends JFrame {

	private JPanel contentPane;
	private JTextField dept;
	private JButton reinitialisation;
	private JTextField nbkm;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField temps;
	private JLabel lblDpartementInconnu;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					taxigraph frame = new taxigraph();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public taxigraph() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("département");
		lblNewLabel.setBounds(12, 0, 101, 37);
		contentPane.add(lblNewLabel);

		lblDpartementInconnu = new JLabel("Département inconnu, Veuillez ressaisir un département !");
		lblDpartementInconnu.setVisible(false);
		lblDpartementInconnu.setBounds(22, 33, 455, 15);
		contentPane.add(lblDpartementInconnu);

		final JRadioButton ar = new JRadioButton("Aller-Retour");
		ar.setBounds(194, 70, 149, 23);
		contentPane.add(ar);

		final JRadioButton as = new JRadioButton("Aller-Simple");
		as.setBounds(24, 70, 149, 23);
		contentPane.add(as);

		final JRadioButton semaine = new JRadioButton("semaine");
		semaine.setBounds(24, 97, 149, 23);
		contentPane.add(semaine);

		final JRadioButton weekend = new JRadioButton("Week-End");
		weekend.setBounds(194, 97, 149, 23);
		contentPane.add(weekend);

		final JRadioButton jour = new JRadioButton("Jour");
		jour.setBounds(24, 117, 149, 23);
		contentPane.add(jour);

		final JRadioButton nuit = new JRadioButton("Nuit");
		nuit.setBounds(194, 117, 149, 23);
		contentPane.add(nuit);

		final JLabel lblPrix = new JLabel("");
		lblPrix.setBounds(16, 224, 424, 15);
		lblPrix.setVisible(false);
		contentPane.add(lblPrix);

		final ButtonGroup typeTrajet = new ButtonGroup();
		typeTrajet.add(ar);
		typeTrajet.add(as);

		final ButtonGroup momentTrajet = new ButtonGroup();
		momentTrajet.add(jour);
		momentTrajet.add(nuit);

		final ButtonGroup jourTrajet = new ButtonGroup();
		jourTrajet.add(semaine);
		jourTrajet.add(weekend);


		dept = new JTextField();
		dept.setBounds(116, 9, 41, 19);
		contentPane.add(dept);
		dept.setColumns(10);
		dept.getText();

		nbkm = new JTextField();
		nbkm.setBounds(176, 168, 66, 19);
		contentPane.add(nbkm);
		nbkm.setColumns(10);
		nbkm.getText();

		lblNewLabel_1 = new JLabel("nb de km parcouru:");
		lblNewLabel_1.setBounds(12, 169, 145, 17);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("temps de parcours:");
		lblNewLabel_2.setBounds(12, 194, 145, 17);
		contentPane.add(lblNewLabel_2);

		temps = new JTextField();
		temps.setBounds(176, 193, 66, 19);
		contentPane.add(temps);
		temps.setColumns(10);
		temps.getText();
		JButton btnCalcul = new JButton("Calcul");
		btnCalcul.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
				// crÃ©ation d'une liste
				List<AR> maListeAR =  new ArrayList<AR>();
				List<AS> maListeAS =  new ArrayList<AS>();

				//Remise du lblDpartementInconnu en invisible dans le cas ou il a saisie un département non trouvée auparavant
				lblDpartementInconnu.setVisible(false);

				try{
					Class.forName("org.postgresql.Driver");
				} catch (Exception e1) {
				    System.out.println("Driver PostgreSQL introuvable !!!");
				    System.exit(0);
				}

				//Création d'un objet de type Connection
		    	Connection maConnect = null;

		    	try{
		    		String url = "jdbc:postgresql://172.16.99.2:5432/tsebillet";
		    		maConnect = DriverManager.getConnection(url, "t.sebillet", "plopnoobnoob");
		    	}catch(Exception e2){
		    		System.out.println("Une erreur est survenue lors de la connexion à la base de donnée");
		    	}

		    	String texteRequete = "select * from \"taxi\".\"tarif\"";

				// définition de l'objet qui récupérera le résultat de l'exécution de la requête :
				ResultSet curseurResultat = null;
				try {
					Statement maReq = maConnect.createStatement();
					curseurResultat = maReq.executeQuery(texteRequete);



					// tant qu'il y a encore une ligne résultat à lire
					while(curseurResultat.next()){
						maListeAR.add(new AR(curseurResultat.getInt("departement"), curseurResultat.getDouble("prisEnCharge") ,
								curseurResultat.getDouble("tarifHoraireJS"), curseurResultat.getDouble("tarifHoraireWE"), curseurResultat.getDouble("kmARJS")
								, curseurResultat.getDouble("kmARWE")));
						maListeAS.add(new AS(curseurResultat.getInt("departement"), curseurResultat.getDouble("prisEnCharge") ,
								curseurResultat.getDouble("tarifHoraireJS"), curseurResultat.getDouble("tarifHoraireWE"), curseurResultat.getDouble("kmASJS")
								, curseurResultat.getDouble("kmASWE")));
					 }

					// on ferme le flux résultat
					 curseurResultat.close();

					// on ferme l'objet lié à la connexion
					 maConnect.close();
				} catch (SQLException e3) {
				    System.out.println("La requête ne retourne aucun résultat !!!");
				    System.exit(0);
				}

				int i;
				boolean saisieOK = false;

				do{
					boolean trouve = false;
					i = 0;

					while(!trouve && i<maListeAR.size()){
						if(Integer.parseInt(dept.getText())==maListeAR.get(i).getDept()){
							trouve = true;
						}else{
							i++;
						}
					}

					if(trouve){
						saisieOK = true;
					}
					else{
						Scanner deptObjet = new Scanner(System.in);
						System.out.println("Département non trouvé; veuillez resaisir");
						dept.setText("");
						lblDpartementInconnu.setVisible(true);
					}
				}while(!saisieOK);

				int nombkl = Integer.parseInt(nbkm.getText());
				int tmps = Integer.parseInt(temps.getText());
				boolean s = semaine.isSelected();
				System.out.println("Résultat : " + Calcul.calculer(i,
						maListeAR,
						maListeAS,
						nombkl,
						tmps,
						semaine.isSelected(),
						weekend.isSelected(),
						jour.isSelected(),
						nuit.isSelected(),
						ar.isSelected(),
						as.isSelected()));
				lblPrix.setText("Le remboursement est de " + df.format(Calcul.calculer(i,
						maListeAR,
						maListeAS,
						nombkl,
						tmps,
						semaine.isSelected(),
						weekend.isSelected(),
						jour.isSelected(),
						nuit.isSelected(),
						ar.isSelected(),
						as.isSelected())) + " euros");
				lblPrix.setVisible(true);
			}

		});
		btnCalcul.setBounds(323, 190, 117, 25);
		contentPane.add(btnCalcul);


		reinitialisation = new JButton("Reinitialiser");
		reinitialisation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jourTrajet.clearSelection();
				momentTrajet.clearSelection();
				typeTrajet.clearSelection();
				dept.setText("");
				nbkm.setText("");
				temps.setText("");
				lblPrix.setVisible(false);
			}
		});
		reinitialisation.setBounds(305, 6, 131, 25);
		contentPane.add(reinitialisation);
	}
}
