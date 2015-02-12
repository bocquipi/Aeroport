/* Projet JAVA         */
/* Controleur          */
/* author :            */

public class Plateforme {
	
	/** Declaration des variables privees **/
	private Aeroport aeroport;
	private Fenetre fenetre;
	private Echelle echelle;
	private AfficheurTime afficheur_time;
	
	/* utilisation du pattern Singleton */
	
	/** Constructeur de la classe Plateforme **/
	public Plateforme() {
		aeroport = new Aeroport(this);
		echelle = new Echelle(this);
		fenetre = new Fenetre(this);
		afficheur_time = new AfficheurTime();
	}
	
	/** Getter de aeroport **/
	public Aeroport get_aeroport() {
		return aeroport;
	}
	
	/** Getter de fenetre **/
	public Fenetre get_fenetre() {
		return fenetre;
	}
	
	/** Getter de echelle **/
	public Echelle get_echelle() {
		return echelle;
	}
	
	/** Getter de afficheur_time **/
	public AfficheurTime get_afficheur_time() {
		return afficheur_time;
	}
}