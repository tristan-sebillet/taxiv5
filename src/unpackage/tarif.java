package unpackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class tarif extends remboursement2 {

	public tarif() {

		 // ajout d'éléments aux listes
		// création d'une liste
		double [][] dept ={
		  {21, 2, 0.86, 1.72, 21.93, 1.29, 2.58, 21.93}
		, {25,2.1, 0.83, 1.66, 22.5, 1.2, 2.4, 22.5}
		, {39,2.1, 0.83, 1.66, 22.5, 1.23, 2.46, 25}
		, {44,2.2, 0.79,	1.58, 24.19, 1.19, 2.37, 24.19}
		, {72,2.15, 0.79, 1.58, 22.86, 1.19,	2.38, 22.86}
		, {73,2.4, 0.84,	1.68, 25.4,	1.26, 2.52,	25.4}
		, {74,3.15, 0.92, 1.84, 17.3, 1.38, 2.76, 17.3}
	    , {75,2.5, 1, 1.24, 0, 1.5, 1.5, 0}
		, {85,2.3, 0.8, 1.6, 22.2, 1.2, 2.4,22.2}
		, {90,2.2, 0.83,1.66, 21, 1.15,	2.3, 21}};

		List<Double> listeTarifAR =  new ArrayList<Double>();
		List<Double> listeTarifAS =  new ArrayList<Double>();

		for (int i=0;i<10;i++){
			listeTarifAR.add(dept[i][0]) ;
			listeTarifAR.add(dept[i][1]) ;
			listeTarifAR.add(dept[i][2]) ;
			listeTarifAR.add(dept[i][4]) ;
			listeTarifAR.add(dept[i][5]) ;
			listeTarifAR.add(dept[i][7]) ;

			listeTarifAS.add(dept[i][0]) ;
			listeTarifAS.add(dept[i][1]) ;
			listeTarifAS.add(dept[i][3]) ;
			listeTarifAS.add(dept[i][4]) ;
			listeTarifAS.add(dept[i][6]) ;
			listeTarifAS.add(dept[i][7]) ;
		}
	}
	public static void calcule(double [] dept){
		Scanner deptObjet = new Scanner(System.in);

		System.out.println("Veuillez saisir le type de déplacement (AS ou AR) :");
		String trajet = deptObjet.next();

		System.out.println("Veuillez saisir le jour de déplacement (S ou WE) :");
		String dateDep = deptObjet.next();

		System.out.println("Veuillez saisir l'heure du déplacement (J ou N) :");
		String heureDep = deptObjet.next();

		System.out.println("Veuillez le temps du parcours (arrondi Ã  l'heure inférieure) :");
		int heurePar = deptObjet.nextInt();

		System.out.println("Veuillez le nb km parcouru :");
		int nbKm = deptObjet.nextInt();

		double montantRemb = 0;

		//Calcul remboursement
		//Si c'est un aller simple
		if(trajet.equals("AS")){
			//Si c'est en semaine de jour
			if(dateDep.equals("S") && heureDep.equals("J")){
				montantRemb = dept[0] + (nbKm * dept[2]);

				//Si le trajet dépasse 1h
				if(heurePar > 1){
					montantRemb += montantRemb + (heurePar * dept[3]);
				}
			}else
			//Sinon c'est de semaine de nuit ou en WE
			if((dateDep.equals("S") && heureDep.equals("N")) || dateDep.equals("WE")){
				montantRemb = dept[0] + (nbKm * dept[5]);

				//Si le trajet dépasse 1h
				if(heurePar > 1){
					montantRemb += montantRemb + (heurePar * dept[6]);
				}
			}
		}else
		//Sinon c'est un aller retour
		if(trajet.equals("AR")){
			//Si c'est en semaine de jour
			if(dateDep.equals("S") && heureDep.equals("J")){
				montantRemb = dept[0] + (nbKm * dept[1]);

				//Si le trajet dépasse 1h
				if(heurePar > 1){
					montantRemb += montantRemb + (heurePar * dept[3]);
				}
			}else
			//Sinon c'est de semaine de nuit ou en WE
			if((dateDep.equals("S") && heureDep.equals("N")) || dateDep.equals("WE")){
				montantRemb = dept[0] + (nbKm * dept[5]);

				//Si le trajet dépasse 1h
				if(heurePar > 1){
					montantRemb += montantRemb + (heurePar * dept[6]);
				}
			}
		}

		System.out.println("Le remboursement est de " + montantRemb + "€");
	}


	}
