package graphic;
/**
 * Programme principal du projet remboursement taxi
 *
 * @author tristan-sebillet
 * @version 2.0
 */

public class AS extends Tarif{
	//************* ATTRIBUTS ****************
	/**
	* Attributs de Tarif
	*/
	private double tarifASJS;
	private double tarifASNW;

	//*************   ACCESSEURS    *************
	/**
	* Accesseurs de Tarif
	*/
	public double getTarifASJS()
	{
	    return tarifASJS;
	}

	public double getTarifASNW()
	{
	    return tarifASNW;
	}

	//*************   CONSTRUCTEUR   *************
	/**
	* Constructeur de Tarif
	*/
	AS(int dept, double priseEnCharge, double horaireJS, double horaireNW, double tarifASJS, double tarifASNW){
		super(dept, priseEnCharge, horaireJS, horaireNW);
		this.tarifASJS = tarifASJS;
		this.tarifASNW = tarifASNW;
	}
}