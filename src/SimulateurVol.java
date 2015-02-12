

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
		
		/* Translation */
		translate(g);
		
		/* Test : Affichage de la trajectoire d'un avion */ 
		if(aeroport.get_trafic().get_liste_vols().size() != 0){
			Vol v = aeroport.get_trafic().get_liste_vols().get(0);
			for(Point p : v.getTrajectoire_vol()) {
				g.setColor(Color.BLUE);
				g.fillOval(echelle.adapter(p.get_coordonnees_point().getX()), echelle.adapter(echelle.inverser(p.get_coordonnees_point().getY())), 20, 20);
			}
		}
		
		/* Recuperation des vols */
		for(Vol v : aeroport.get_trafic().get_liste_vols()) {
			for(Point p : v.getTrajectoire_vol()) {
				p.get_coordonnees_point().getX();
				p.get_coordonnees_point().getY();
				
			}
		}
		
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
