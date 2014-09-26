package unpackage;

import java.util.Scanner;

public class AS {

	public AS() {
		Scanner deptsaisie = new Scanner(System.in);
		System.out.println("Veuillez saisir le département :");
		int numDept = deptsaisie.nextInt();
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
	}

}
