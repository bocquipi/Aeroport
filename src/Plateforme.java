/* Projet JAVA         */
/* Controleur          */
/* author :            */

public class Plateforme {
	
	/** Declaration des variables privees **/
	private Aeroport aeroport;
	private Fenetre fenetre;
	
	/** Constructeur de la classe Plateforme **/
	public Plateforme() {
		aeroport = new Aeroport(this);
		fenetre = new Fenetre(this);
	}
	
	/** Getter de aeroport **/
	public Aeroport get_aeroport() {
		return aeroport;
	}
	
	/** Getter de fenetre **/
	public Fenetre get_fenetre() {
		return fenetre;
	}
}