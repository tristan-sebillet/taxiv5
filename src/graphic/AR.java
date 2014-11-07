package graphic;
/**
 * Programme principal du projet remboursement taxi
 *
 * @author tristan-sebillet
 * @version 2.0
 */

public class AR extends Tarif{
	//************* ATTRIBUTS ****************
	/**
	 * Attributs de Tarif
	 */
	private double tarifARJS;
	private double tarifARNW;

	//*************   ACCESSEURS *************
	/**
	 * Accesseur de Tarif
	 */
	public double getTarifARJS()
	{
	    return tarifARJS;
	}

	public double getTarifARNW()
	{
	    return tarifARNW;
	}

	//*************   CONSTRUCTEUR   *************
	/**
	 * Constructeur de Tarif
	 */
	AR(int dept, double priseEnCharge, double horaireJS, double horaireNW, double tarifARJS, double tarifARNW){
		super(dept, priseEnCharge, horaireJS, horaireNW);
		this.tarifARJS = tarifARJS;
		this.tarifARNW = tarifARNW;
	}
}