package unpackage;
import java.util.Scanner;
public class MaClasse {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		  int departement[]= {21,25,39,44,72,73,74,75,85,90};
		  double priseEnCharge[]= {2,2.1,2.1,2.2,2.15,2.4,3.15,2.5,2.3,2.2};
		  double tarifKmArJs[]= {0.86,0.83,0.83,0.79,0.79,0.84,0.92,1,0.8,0.83};
		  double tarifKmAsJs[]= {1.72,1.66,1.66,1.58,1.58,1.68,1.84,1.24,1.6,1.66};
		  double tarifhoraireJs[]= {21.93,22.5,22.5,24.19,22.86,25.4,17.3,0,22.2,21};
		  double tarifKmArNuitDimanche[]= {1.29,1.2,1.23,1.19,1.19,1.26,1.38,1.5,1.2,1.15};
		  double tarifKmAsNuitDimanche[]= {2.58,2.4,2.46,2.37,2.38,2.52,2.76,1.5,2.4,2.3};
		  double tarifHoraireNuitDimanche[]= {21.93,22.5,25,24.19,22.86,25.4,17.3,0,22.2,21};


		  Scanner slam=new Scanner(System.in);
//saisie département/récupérer l'index du département
		  System.out.println("Veuillez saisir votre département : ");
		  int dep= slam.nextInt();
		  boolean trouve=false;
		  int i=0;
		  while(i<departement.length && trouve==false){
			  if(departement[i]==dep)
			  {trouve=true;}
			  else {i++;}
		  }
		  if(trouve==false){
				System.out.println("Veuillez saisir un département disponible(21/25/39/44/72/73/74/75/85/90) :");
			}
		  else{
		  System.out.println(i);
		  }

				System.out.println("Veuillez saisir le type de déplacement (AS ou AR) :");
				String trajet = slam.next();


				//Aller simple
				if (trajet == "AS"){
					System.out.println("Veuillez saisir le jour de déplacement (S ou WE) :");
					String dateDep = slam.next();
					//Aller simple en semaine
					if (dateDep == "S"){
						System.out.println("Veuillez saisir l'heure du déplacement (J ou N) :");
						String heureDep = slam.next();
						//Aller simple en semaine de jour
						if (heureDep == "J"){
							System.out.println("Veuillez saisir la durée du parcours (en minutes) :");
							int minPar = slam.nextInt();
							double heurePar=minPar/60;
							//Aller simple en semaine de jour pour une heure
							if (heurePar <= 1){
								System.out.println("Veuillez saisir le nombre de km parcouru :");
								int nbKm = slam.nextInt();
								double montantRemb = priseEnCharge[i] + (nbKm * tarifKmAsJs[i]);
								System.out.println("Le remboursement est de " + montantRemb+" €.");
							}
							//Aller simple en semaine de jour pour heure > 1
							else{
								System.out.println("Veuillez saisir le nombre de km parcouru :");
								int nbKm = slam.nextInt();
								double montantRemb = priseEnCharge[i] + (nbKm * tarifKmAsJs[i]) + (heurePar * tarifhoraireJs[i]);
								System.out.println("Le remboursement est de " + montantRemb+" €.");
							}
						}



						//Aller simple en semaine de nuit
						else{
							System.out.println("Veuillez saisir la durée du parcours (en minutes) :");
							int minPar = slam.nextInt();
							double heurePar=minPar/60;
							//Aller simple en semaine de nuit pour une heure
							if (heurePar == 1){
								System.out.println("Veuillez saisir le nb km parcouru :");
								int nbKm = slam.nextInt();
								double montantRemb = priseEnCharge[i] + (nbKm * departement[5]);
								System.out.println("Le remboursement est de " + montantRemb+" €.");
							}
							//Aller simple en semaine de nuit pour heure > 1
							else{
								System.out.println("Veuillez saisir le nb km parcouru :");
								int nbKm = slam.nextInt();
								double montantRemb = priseEnCharge[i] + (nbKm * departement[5]) + (heurePar * departement[6]);
								System.out.println("Le remboursement est de " + montantRemb+" €.");
							}
						}
					}



					//Aller simple en WE
					else{
						System.out.println("Veuillez saisir la durée du parcours (en minutes) :");
						int minPar = slam.nextInt();
						double heurePar=minPar/60;
						//Aller simple en WE pour une heure
						if (heurePar == 1){
							System.out.println("Veuillez saisir le nb km parcouru :");
							int nbKm = slam.nextInt();
							double montantRemb = priseEnCharge[i] + (nbKm * tarifKmArNuitDimanche[i]);
							System.out.println("Le remboursement est de " + montantRemb+" €.");
						}
						//Aller simple en WE pour heure > 1
						else{
							System.out.println("Veuillez saisir le nb km parcouru :");
							int nbKm = slam.nextInt();
							double montantRemb = priseEnCharge[i] + (nbKm * tarifKmAsNuitDimanche[i]) + (heurePar * tarifHoraireNuitDimanche[i]);
							System.out.println("Le remboursement est de " + montantRemb+" €.");
						}
					}
				}



				//Aller retour
				else{
					System.out.println("Veuillez saisir le jour de déplacement (S ou WE) :");
					String dateDep = slam.next();
					//Aller retour en semaine
					if (dateDep == "S"){
						System.out.println("Veuillez saisir l'heure du déplacement (J ou N) :");
						String heureDep = slam.next();
						//Aller retour en semaine de jour
						if (heureDep == "J"){
							System.out.println("Veuillez saisir le temps du parcours (en minutes) :");
							int heurePar = slam.nextInt();
							//Aller retour en semaine de jour pour une heure
							if (heurePar == 1){
								System.out.println("Veuillez saisir le nb km parcouru :");
								int nbKm = slam.nextInt();
								double montantRemb = priseEnCharge[i] + (nbKm * departement[1]);
								System.out.println("Le remboursement est de " + montantRemb+" €.");
							}
							//Aller retour en semaine de jour pour heure > 1
							else{
								System.out.println("Veuillez saisir le nombre de km parcouru :");
								int nbKm = slam.nextInt();
								double montantRemb = priseEnCharge[i] + (nbKm * tarifKmArJs[i]) + (heurePar * tarifhoraireJs[i]);
								System.out.println("Le remboursement est de " + montantRemb+" €.");
							}
						}





						//Aller retour en semaine de nuit
						else{
							System.out.println("Veuillez saisir le temps du parcours (en minutes) :");
							int heurePar = slam.nextInt();
							//Aller retour en semaine de nuit pour une heure
							if (heurePar == 1){
								System.out.println("Veuillez saisir le nombre de km parcouru :");
								int nbKm = slam.nextInt();
								double montantRemb = priseEnCharge[i] + (nbKm * tarifKmArNuitDimanche[i]);
								System.out.println("Le remboursement est de " + montantRemb+" €.");
							}
							//Aller retour en semaine de nuit pour heure > 1
							else{
								System.out.println("Veuillez saisir le nombre de km parcouru :");
								int nbKm = slam.nextInt();
								double montantRemb = priseEnCharge[i] + (nbKm * tarifKmArNuitDimanche[i]) + (heurePar * tarifHoraireNuitDimanche[i]);
								System.out.println("Le remboursement est de " + montantRemb+" €.");
							}
						}
					}



					//Aller retour en WE
					else{
						System.out.println("Veuillez saisir le temps du parcours (en minutes) :");
						int heurePar = slam.nextInt();
						if (heurePar == 1){
							System.out.println("Veuillez saisir le nombre de km parcouru :");
							int nbKm = slam.nextInt();
							double montantRemb = priseEnCharge[i] + (nbKm * tarifKmArNuitDimanche[i]);
							System.out.println("Le remboursement est de " + montantRemb+" €.");
						}else{
							System.out.println("Veuillez saisir le nombre de km parcouru :");
							int nbKm = slam.nextInt();
							double montantRemb = priseEnCharge[i] + (nbKm * tarifKmArNuitDimanche[i]) + (heurePar * tarifHoraireNuitDimanche[i]);
							System.out.println("Le remboursement est de " + montantRemb+" €.");
						}
					}
				}
		}

	  }

