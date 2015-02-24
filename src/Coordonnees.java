/* Projet JAVA         */
/* Modele              */
/* author :            */

public class Coordonnees {

	/** Declaration des variables privees **/
	private int x;
	private int y;
	
	/** Constructeur de la classe Coordonnees **/
	public Coordonnees(){
		setX(0);
		setY(0);
	}
	
	public Coordonnees(int x, int y){
		setX(x);
		setY(y);
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
	
	/** toString **/
	/** fonction : surcharge de toString pour l'affichage des coordonnees x et y d'un vol **/
	public String toString() {
		return  getX() + "," + getY();
	}
}
