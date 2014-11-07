package graphic;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import unpackage.Calcul;


public class main {

	public static void main(String[] args) {

		// crÃ©ation d'une liste
		List<AR> maListeAR =  new ArrayList<AR>();
		List<AS> maListeAS =  new ArrayList<AS>();

		try{
    		Class.forName("org.postgresql.Driver");
    	} catch (Exception e) {
    		System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
    	}//Fin catch
		//Création d'un objet de type Connection
    	Connection connect = null;

    	try{
    		String url = "jdbc:postgresql://172.16.99.2:5432/tsebillet";
    		connect = DriverManager.getConnection(url, "t.sebillet", "plopnoobnoob");
    	}catch(Exception e){
    		System.out.println("Une erreur est survenue lors de la connexion à la base de donnée");
    	}

		Statement maRequete = null;
		try {
			maRequete = connect.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String texteRequete = "select * from \"taxi\".tarif";
		// définition de l'objet qui récupérera le résultat de l'exécution de la requête :

		ResultSet curseurResultat = null;
		try {
			curseurResultat = maRequete.executeQuery(texteRequete);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// récupération des détails du résultats

		try {
			ResultSetMetaData detailsDonnees = curseurResultat.getMetaData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			while(curseurResultat.next()){
				maListeAR.add(new AR(curseurResultat.getInt("departement"),
						curseurResultat.getDouble("prisEnCharge"),
						curseurResultat.getDouble("tarifHoraireJS"),
						curseurResultat.getDouble("tarifHoraireWE"),
						curseurResultat.getDouble("kmARJS"),
						curseurResultat.getDouble("kmARWE")));
				maListeAS.add(new AS(curseurResultat.getInt("departement"),
						curseurResultat.getDouble("prisEnCharge"),
						curseurResultat.getDouble("tarifHoraireJS"),
						curseurResultat.getDouble("tarifHoraireWE"),
						curseurResultat.getDouble("kmASJS"),
						curseurResultat.getDouble("kmASWE")));
			}
		}

			catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int i;
		/*
		// ajout d'Ã©lÃ©ments Ã  la liste
		for (i=0;i<10;i++){
			maListeAR.add(new AR((int)dept[i][0], dept[i][1], dept[i][4], dept[i][7],
					dept[i][2], dept[i][5]));
			maListeAS.add(new AS((int)dept[i][0], dept[i][1], dept[i][5], dept[i][7],
					dept[i][3], dept[i][6]));
		}
		*/

		Saisie maSaisie = new Saisie();

		boolean saisieOK = false;

		do{

			boolean trouve = false;
			i = 0;

			while(!trouve && i<maListeAR.size()){
				if(maSaisie.getNumDept()==maListeAR.get(i).getDept()){
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
				maSaisie.setNumDept(deptObjet.nextInt());
			}
		}while(!saisieOK);

		System.out.println("Résultat : " + Calcul.calculer(i, maListeAR, maListeAS, maSaisie));

	}

}