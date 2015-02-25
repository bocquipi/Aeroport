/** Projet JAVA             */
/** Modele                  */
/** @author : PB - AP - CC  */

public class Point {

	/** Declaration des variables privees
	 * 
	 * @see Coordonnees
	 */
	private String nom_point;
	//enum Type_point {STAND, DEICING, RUNWAY_POINT};
	private int type_point;
	private Coordonnees coordonnees_point;
	
	/** Constructeurs de la classe Point */
	
	/** Constructeur sans parametres
	 *	
	 *	@see Coordonnees
	 */
	public Point() {
		set_nom_point("inconnue");
		set_type_point(-1);
		coordonnees_point = new Coordonnees();
	}
	
	/** Constructeur avec parametres
	 * fonction : creation d'un point sans nom et sans type
	 * utilisation : charger_fichier_trafic
	 * 
	 * @see Coordonnees
	 * 
	 * @param coordonnees
	 */
	public Point(String coordonnees) {
		String delimiter_axes = ",";
		String[] tampon_coordonnees;
		tampon_coordonnees = coordonnees.split(delimiter_axes);
		this.coordonnees_point = new Coordonnees(Integer.parseInt(tampon_coordonnees[0]),Integer.parseInt(tampon_coordonnees[1]));
	}
	
	/** Constructeur avec parametres
	 * fonction : creation d'un point avec nom et avec type
	 * utilisation : charger_fichier_aeroport
	 * 
	 * @param nom_point
	 * @param type_point
	 * @param coordonnees
	 */
	public Point(String nom_point, int type_point, String coordonnees) {
		String delimiter_axes = ",";
		String[] tampon_coordonnees;
		set_nom_point(nom_point);
		set_type_point(type_point);
		tampon_coordonnees = coordonnees.split(delimiter_axes);
		this.coordonnees_point = new Coordonnees(Integer.parseInt(tampon_coordonnees[0]),Integer.parseInt(tampon_coordonnees[1]));
	}
	
	/** Getter de nom_point
	 * 
	 * @return le nom du point.
	 */
	public String get_nom_point() {
		return nom_point;
	}
	
	/** Setter de nom_point
	 * 
	 * @param nom_point
	 * 		rentre le nouveau nom du point.
	 */
	public void set_nom_point(String nom_point) {
		this.nom_point = nom_point;
	}
	
	/** Getter de type_point
	 * 
	 * @return le type du point.
	 */
	public int get_type_point() {
		return type_point;
	}
	
	/** Setter de type_point
	 * 
	 * @param type_point
	 * 		rentre le nouveau type du point.
	 */
	public void set_type_point(int type_point) {
		this.type_point = type_point;
	}
	
	/** Getter de coordonnees_point
	 * 
	 * @return les coordonnees du point.
	 */
	public Coordonnees get_coordonnees_point() {
		return coordonnees_point;
	}
	
	/** toString
	 * fonction : surcharge de toString pour l'affichage des coordonnees d'un vol
	 * 
	 */
	
	public String toString(){
		return coordonnees_point.toString();
	}
}
