/* Projet JAVA         */
/* Modele              */
/* author :            */

public class Echelle {

	/** Declaration des variables privees **/
	private Plateforme plateforme;
	private int x; //Google Maps : valeur en metres (8450)
	private int y ; //Google Maps : valeur en metres (4107)
	private double x_translation;
	private double y_translation;
	double zoom;
	int index_zoom;
	double tableau_zoom[] = {0.5, 0.4, 0.3, 0.2, 0.1, 0.09, 0.08, 0.07, 0.06, 0.05};
	
	/** Constructeur de la classe Echelle **/
	public Echelle(Plateforme plateforme) {
		this.plateforme = plateforme;
		x = 0;
		y = 0;
		x_translation = 0;
		y_translation = 0;
		index_zoom = 4; //Index par defaut
		zoom = tableau_zoom[index_zoom]; //Zoom par defaut = 0.1;
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
	
	/** Getter/Setter de x_translation **/
	public double getX_translation() {
		return x_translation;
	}
	
	public void setX_translation(double x_translation) {
		this.x_translation = x_translation;
	}
	
	/** Getter/Setter de y_adapte **/
	public double getY_translation() {
		return y_translation;
	}
	
	public void setY_translation(double y_translation) {
		this.y_translation = y_translation;
	}
	
	/** Getter/Setter de zoom **/
	public double get_zoom() {
		return zoom;
	}
	
	public void set_zoom(double zoom) {
		this.zoom = zoom;
	}
	
	/** Getter/Setter de index_zoom **/
	public int get_index_zoom() {
		return index_zoom;
	}
	
	public void set_index_zoom(int index_zoom) {
		this.index_zoom = index_zoom;
	}
	
	/** Getter de tableau_zoom **/
	public double[] get_tableau_zoom() {
		return tableau_zoom;
	}
	
	/** inverser **/
	/** fonction : inversement de l'axe des ordonnees **/
	public int inverser(int nombre){
		return -nombre;
	}
	
	/** moduler **/
	/** fonction : calcul du module d'un nombre **/
	public int moduler(int nombre) {
		if(nombre < 0) {
			return -nombre;
		}
		else {
			return nombre;
		}
	}
	
	/** calculer_translation **/
	/** fonction : calculer la translation des coordonnees de la plateforme aeroportuaire **/
	public void calculer_translation(){
		setX(moduler(plateforme.get_aeroport().calcul_max_min_coordonnees("max_x")) + moduler(plateforme.get_aeroport().calcul_max_min_coordonnees("min_x")));
		setY(moduler(plateforme.get_aeroport().calcul_max_min_coordonnees("max_y")) + moduler(plateforme.get_aeroport().calcul_max_min_coordonnees("min_y")));
		setX_translation(((getX()/2)*get_zoom()));
		setY_translation(((getY()/2)*get_zoom()));
	}
}
