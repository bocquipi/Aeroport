/* Projet JAVA         */
/* Vue                 */
/* author :            */

public class Echelle {

	/** Declaration des variables privees **/
	private Plateforme plateforme;
	private int x;
	private int y ;
	double resolution;
	
	/** Constructeur de la classe Echelle **/
	public Echelle(Plateforme plateforme) {
		this.plateforme = plateforme;
		x = 0;
		y = 0;
		resolution = 8;
	}
	
	/** Getter/Setter de x **/
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	/** Getter/Setter de y **/
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	/** Getter/Setter de resolution **/
	public double get_resolution() {
		return resolution;
	}
	
	public void set_resolution(double resolution) {
		this.resolution = resolution;
	}
	
	/** inverser **/
	/** fonction : inversement de l'axe des ordonnees **/
	public int inverser(int nombre){
		return -nombre;
	}
	
	/** adapter **/
	/** fonction : adaptation des coordonnees avec la fenetre **/
	public int adapter(int nombre){
		return (int) (nombre/resolution);
	}
	
	/** calculer_translation **/
	/** fonction : calculer la translation des coordonnees de la plateforme aeroportuaire **/
	public void calculer_translation(){
		setX(plateforme.get_aeroport().calcul_max_min_coordonnees("max_x")-plateforme.get_aeroport().calcul_max_min_coordonnees("min_x"));
		setY(plateforme.get_aeroport().calcul_max_min_coordonnees("max_y")-plateforme.get_aeroport().calcul_max_min_coordonnees("min_y"));
		System.out.println(getX());
		System.out.println(getY());
	}
}
