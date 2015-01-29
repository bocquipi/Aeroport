/* Projet JAVA         */
/* Modele              */
/* author :            */

public class Echelle {

	/** Declaration des variables privees **/
	private Plateforme plateforme;
	private int x; //Google Maps : valeur en metres (8450)
	private int y ; //Google Maps : valeur en metres (4107)
	private int x_adapte;
	private int y_adapte;
	private int x_translation;
	private int y_translation;
	double zoom;
	int index_zoom;
	double tableau_zoom[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
	
	/** Constructeur de la classe Echelle **/
	public Echelle(Plateforme plateforme) {
		this.plateforme = plateforme;
		x = 0;
		y = 0;
		x_adapte = 0;
		y_adapte = 0;
		x_translation = 0;
		y_translation = 0;
		index_zoom = 7; //Index par defaut
		zoom = tableau_zoom[index_zoom]; //Zoom par defaut = 8;
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
	
	/** Getter/Setter de x_adapte **/
	public int getX_adapte() {
		return x_adapte;
	}
	
	public void setX_adapte(int x_adapte) {
		this.x_adapte = x_adapte;
	}
	
	/** Getter/Setter de y_adapte **/
	public int getY_adapte() {
		return y_adapte;
	}
	
	public void setY_adapte(int y_adapte) {
		this.y_adapte = y_adapte;
	}
	
	/** Getter/Setter de x_translation **/
	public int getX_translation() {
		return x_translation;
	}
	
	public void setX_translation(int x_translation) {
		this.x_translation = x_translation;
	}
	
	/** Getter/Setter de y_adapte **/
	public int getY_translation() {
		return y_translation;
	}
	
	public void setY_translation(int y_translation) {
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
	
	/** adapter **/
	/** fonction : adaptation des coordonnees avec la fenetre **/
	public int adapter(int nombre){
		return (int) (nombre/zoom);
	}
	
	/** calculer_translation **/
	/** fonction : calculer la translation des coordonnees de la plateforme aeroportuaire **/
	public void calculer_translation(){
		setX(plateforme.get_aeroport().calcul_max_min_coordonnees("max_x")-plateforme.get_aeroport().calcul_max_min_coordonnees("min_x"));
		setY(plateforme.get_aeroport().calcul_max_min_coordonnees("max_y")-plateforme.get_aeroport().calcul_max_min_coordonnees("min_y"));
		setX_adapte(adapter(getX()));
		setY_adapte(adapter(getY()));
		setX_translation(getX_adapte()/2);
		setY_translation(getY_adapte()/2);
	}
}
