/** Projet JAVA         */
/** Modele              */
/** @author : PB - CC   */

public class Echelle {

	/** Declaration des variables privees.
	 * 
	 * @see Plateforme
	 */
	private Plateforme plateforme;
	private int x; //Google Maps : valeur en metres (8450)
	private int y ; //Google Maps : valeur en metres (4107)
	private double x_translation;
	private double y_translation;
	double zoom;
	int index_zoom;
	double tableau_zoom[] = {0.5, 0.4, 0.3, 0.2, 0.1, 0.09, 0.08, 0.07, 0.06, 0.05};
	
	/** Constructeur de la classe Echelle
	 * 
	 * @see Plateforme
	 * 
	 * @param plateforme
	 * 		envoie la plateforme pour gerer son echelle.
	 */
	public Echelle(Plateforme plateforme) {
		this.plateforme = plateforme;
		x = 0;
		y = 0;
		x_translation = 0;
		y_translation = 0;
		index_zoom = 4; //Index par defaut
		zoom = tableau_zoom[index_zoom]; //Zoom par defaut = 0.1;
	}
	
	/** Getter de x
	 * 
	 * @return l'abscisse de l'echelle.
	 */
	public int getX() {
		return x;
	}
	
	/** Setter de x 
	 * 
	 * @param x
	 * 		rentre la nouvelle abscisse de l'echelle.
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/** Getter de y
	 * 
	 * @return l'ordonnee de l'echelle
	 */
	public int getY() {
		return y;
	}
	
	/** Setter de y
	 * 
	 * @param y
	 * 		rentre la nouvelle ordonnee de l'echelle.
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/** Getter de x_translation
	 * 
	 * @return l'abscisse translatee de l'echelle.
	 */
	public double getX_translation() {
		return x_translation;
	}
	
	/** Setter de x_translation
	 * 
	 * @param x_translation
	 * 		rentre la nouvelle abscisse translatee de l'echelle. 
	 */
	public void setX_translation(double x_translation) {
		this.x_translation = x_translation;
	}
	
	/** Getter de y_translation
	 * 
	 * @return l'ordonnee translatee de l'echelle.
	 */
	public double getY_translation() {
		return y_translation;
	}
	
	/** Setter de y_translation
	 * 
	 * @param y_translation
	 * 		rentre la nouvelle ordonnee translatee
	 */
	public void setY_translation(double y_translation) {
		this.y_translation = y_translation;
	}
	
	/** Getter de zoom
	 * 
	 * @return la valeur du zoom.
	 */
	public double get_zoom() {
		return zoom;
	}
	
	/**  Setter de zoom 
	 * 
	 * @param zoom
	 * 		rentre la nouvelle valeur de zoom.
	 */
	public void set_zoom(double zoom) {
		this.zoom = zoom;
	}
	
	/** Getter de index_zoom
	 * 
	 * @return l'index du zoom.
	 */
	public int get_index_zoom() {
		return index_zoom;
	}
	
	/** Setter de index_zoom
	 * 
	 * @param index_zoom
	 * 		rentre le nouvel index du zoom.
	 */
	public void set_index_zoom(int index_zoom) {
		this.index_zoom = index_zoom;
	}
	
	/** Getter de tableau_zoom
	 * 
	 * @return le tableau de valeurs de zoom.
	 */
	public double[] get_tableau_zoom() {
		return tableau_zoom;
	}
	
	/** inverser
	 * fonction : inversement de l'axe des ordonnees.
	 * 
	 * @param nombre
	 * 		rentre le nombre a inverser.
	 */
	public int inverser(int nombre){
		return -nombre;
	}
	
	/** moduler **/
	/** fonction : calcul du module d'un nombre
	 * 
	 * @param nombre
	 * 		rentre le nombre a moduler.
	 */
	public int moduler(int nombre) {
		if(nombre < 0) {
			return -nombre;
		}
		else {
			return nombre;
		}
	}
	
	/** calculer_translation
	 * fonction : calculer la translation des coordonnees de la plateforme aeroportuaire.
	 * 
	 * @see Plateforme
	 * @see Coordonnee
	 */
	public void calculer_translation(){
		setX(moduler(plateforme.get_aeroport().calcul_max_min_coordonnees("max_x")) + moduler(plateforme.get_aeroport().calcul_max_min_coordonnees("min_x")));
		setY(moduler(plateforme.get_aeroport().calcul_max_min_coordonnees("max_y")) + moduler(plateforme.get_aeroport().calcul_max_min_coordonnees("min_y")));
		setX_translation(((getX()/2)*get_zoom()));
		setY_translation(((getY()/2)*get_zoom()));
	}
}
