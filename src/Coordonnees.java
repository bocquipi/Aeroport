/** Projet JAVA             */
/** Modele                  */
/** @author : PB - AP - CC  */

public class Coordonnees {

	/** Declaration des variables privees */
	private int x;
	private int y;
	
	/** Constructeur de la classe Coordonnees */
	public Coordonnees(){
		setX(0);
		setY(0);
	}
	
	/** Constructeur de la classe Coordonnees prenant en parametre abscisses et ordonnees
	 * 
	 * @param x
	 * 		abscisse de l'objet
	 * @param y
	 * 		ordonnee de l'objet
	 */
	public Coordonnees(int x, int y){
		setX(x);
		setY(y);
	}
	
	/** Getter de x
	 * 
	 * @return l'abscisse de l'objet
	 */
	public int getX() {
		return x;
	}

	/** Setter de x
	 * 
	 * @param x
	 * 		rentre la nouvelle abscisse
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/** Getter de y
	 * 
	 * @return l'ordonnee de l'objet
	 */
	public int getY() {
		return y;
	}

	/** Setter de y
	 * 
	 * @param y
	 * 		rentre la nouvelle ordonnee
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/** toString
	 * fonction : surcharge de toString pour l'affichage des coordonnees x et y d'un vol
	 * 
	 * @return l'abscisse et l'ordonnee de l'objet
	 */
	public String toString() {
		return  getX() + "," + getY();
	}
}
