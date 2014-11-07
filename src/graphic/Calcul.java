package graphic;
/**
 * Programme principal du projet remboursement taxi
 *
 * @author tristan-sebillet
 * @version 2.0
 */
import java.util.List;
public class Calcul {
	public static double calculer(int i,
			List<AR> maListeAR, List<AS> maListeAS ,
			int nobkm, int tmps, boolean semaine,boolean weekend,boolean jour,boolean nuit,boolean ar,boolean as)

	{
		double montantRemb = 0;

		//Calcul remboursement
				//Si c'est un aller simple
				if(as ){
					//Si c'est en semaine de jour
					if(semaine && jour){
						montantRemb = maListeAS.get(i).getPriseEnCharge() + (nobkm * maListeAS.get(i).getTarifASJS());

						//Si le trajet dépasse 1h
						if(tmps > 1){
							montantRemb += montantRemb + (tmps * maListeAS.get(i).getHoraireJS());
						}
					}else
					//Sinon c'est de semaine de nuit ou en WE
					if((semaine && nuit) || weekend){
						montantRemb = maListeAS.get(i).getPriseEnCharge() + (nobkm * maListeAS.get(i).getTarifASNW());

						//Si le trajet dépasse 1h
						if(tmps > 1){
							montantRemb += montantRemb + (tmps * maListeAS.get(i).getHoraireNW());
						}
					}
				}else if(ar){
					//Si c'est en semaine de jour
					if(semaine && jour){
						montantRemb = maListeAR.get(i).getPriseEnCharge() + (nobkm * maListeAR.get(i).getTarifARJS());

						//Si le trajet dépasse 1h
						if(tmps > 1){
							montantRemb += montantRemb + (tmps * maListeAR.get(i).getHoraireJS());
						}
					}else
					//Sinon c'est de semaine de nuit ou en WE
					if((semaine&&  nuit)|| weekend){
						montantRemb = maListeAR.get(i).getPriseEnCharge() + (nobkm * maListeAR.get(i).getTarifARNW());

						//Si le trajet dépasse 1h
						if(tmps > 1){
							montantRemb = montantRemb + (tmps * maListeAR.get(i).getHoraireNW());
						}
				}}

				return montantRemb;
	}
}