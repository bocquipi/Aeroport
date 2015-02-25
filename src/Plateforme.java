/* Projet JAVA         */
/* Controleur          */
/* author :            */

public class Plateforme {
	
	/** Declaration des variables privees
	 * 
	 * @see Plateforme
	 * @see Fenetre
	 * @see Echelle
	 * @see Time
	 * @see AfficheurTime
	 */
	private Aeroport aeroport;
	private Fenetre fenetre;
	private Echelle echelle;
	private Time time;
	private AfficheurTime afficheur_time;
	
	/* utilisation du pattern Singleton */
	
	/** Constructeur de la classe Plateforme **/
	public Plateforme() {
		afficheur_time = new AfficheurTime(this);
		time = new Time(this);
		aeroport = new Aeroport(this);
		echelle = new Echelle(this);
		fenetre = new Fenetre(this);
	}
	
	/** Getter de aeroport
	 * 
	 * @return l'aeroport.
	 */
	public Aeroport get_aeroport() {
		return aeroport;
	}
	
	/** Getter de fenetre
	 * 
	 * @return la fenetre.
	 */
	public Fenetre get_fenetre() {
		return fenetre;
	}
	
	/** Getter de echelle
	 * 
	 * @return le zoom actuel.
	 */
	public Echelle get_echelle() {
		return echelle;
	}
	
	/** Getter de time
	 * 
	 * @return le temps actuel.
	 */
	public Time get_time() {
		return time;
	}
	
	/** Getter de afficheur_time
	 * 
	 * @return l'afficheur de la gestion du temps.
	 */
	public AfficheurTime get_afficheur_time() {
		return afficheur_time;
	}
}