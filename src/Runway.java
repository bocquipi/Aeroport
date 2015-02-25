/** Projet JAVA             */
/** Modele                  */
/** @author : PB - AP - CC  */

import java.util.ArrayList;

public class Runway {
	
	/** Declaration des variables privees
	 * 
	 * @see Coordonnees
	 * @see Point
	 */
	private String nom_runway;
	private String qfu[] = new String[2];
	private ArrayList<Coordonnees> coordonnees_extremites;
	ArrayList<Point> points;
	Point point;
	
	/** Constructeur de la classe Runway
	 * 
	 * @see Coordonnees
	 */
	public Runway() {
		set_nom_runway("inconnue");
		qfu[0] = "inconnue";
		qfu[1] = "inconnue";
		coordonnees_extremites = new ArrayList<Coordonnees>();
		this.points = new ArrayList<Point>();
	}
	
	public Runway(String nom_line, String qfu0, String qfu1, String coordonnees, Aeroport aeroport, String points) {
		Coordonnees coordonnees_extremite;
		String delimiter = ";";
		String delimiter_axes = ",";
		String[] tampon_coordonnees;
		String[] tampon_axes;
		String[] tampon_points;
		set_nom_runway(nom_line);
		qfu[0] = qfu0;
		qfu[1] = qfu1;
		coordonnees_extremites = new ArrayList<Coordonnees>();
		tampon_coordonnees = coordonnees.split(delimiter);
		for(String coord : tampon_coordonnees) {
			tampon_axes = coord.split(delimiter_axes);
			coordonnees_extremite = new Coordonnees(Integer.parseInt(tampon_axes[0]),Integer.parseInt(tampon_axes[1]));
			coordonnees_extremites.add(coordonnees_extremite);
		}
		this.points = new ArrayList<Point>();
		tampon_points = points.split(delimiter);
		for(String nom_point : tampon_points) {
			this.point = aeroport.rechercher_point(nom_point);
			this.points.add(this.point);
		}
	}
	
	/** Getter de nom_runway
	 * 
	 * @return le nom du runway.
	 */
	public String get_nom_runway() {
		return nom_runway;
	}
	
	/** Setter de nom_runway
	 * 
	 * @param nom_runway
	 * 		rentre le nouveau nom du runway.
	 */
	public void set_nom_runway(String nom_runway) {
		this.nom_runway = nom_runway;
	}
	
	/** Getter de coordonnees_extremites
	 * 
	 * @see Coordonnees
	 * 
	 * @return les coordonnees des extremites du runway.
	 */
	public ArrayList<Coordonnees> get_coordonnees_extremites() {
		return coordonnees_extremites;
	}
}
