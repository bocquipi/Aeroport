/* Projet JAVA         */
/* Vue                 */
/* author :            */

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class SimulateurVol extends JPanel {
	
	/** Serialisation **/
	private static final long serialVersionUID = 1L;

	/** Declaration des variables privees **/
	private Aeroport aeroport;
	private Echelle echelle;
	
	/** Constructeur de la classe SimulateurAeroport **/
	public SimulateurVol(Aeroport aeroport, Echelle echelle) {

		super();
		this.aeroport = aeroport;
		this.echelle = echelle;
	}
	
	/** paintComponent **/
	/** fonction : Construction du simulateur **/
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		/*Translation*/
		translate(g);
		
		/* Recuperation des vols */
		//Vol v = aeroport.get_trafic().get_liste_vols().get(0);
		
		/* Creation des Getters/Setters dans Vol */
		
		/* Affichage */ 
		g.setColor(Color.pink);
		g.fillOval(echelle.adapter(500), echelle.adapter(echelle.inverser(500)), 50, 50);
		
	}
	
	/** translate **/
	/** fonction : Translation au niveau de l'affichage du simulateur **/
	public void translate (Graphics g) {
		g.translate(echelle.getX_translation(), echelle.getY_translation());
	}
}
