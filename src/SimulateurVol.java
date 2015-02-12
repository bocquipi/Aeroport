/* Projet JAVA         */
/* Vue                 */
/* author :            */

import java.awt.Color;
import java.awt.Dimension;
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
	    this.setOpaque(false);
	    /* Taille de la fenetre */
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int hauteur = (int)tailleEcran.getHeight();
		int largeur = (int)tailleEcran.getWidth();
	    this.setMinimumSize(new Dimension(hauteur,largeur));
	    this.setPreferredSize(new Dimension(hauteur, largeur));
	    this.setBounds(0, 0, hauteur, largeur);
	    this.setBackground(Color.WHITE);
	}
	
	/** paintComponent **/
	/** fonction : Construction du simulateur **/
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		/* Translation */
		translate(g);
		
		/* Test : Affichage de la trajectoire d'un avion */ 
		test_affichage_avion(g);
		
		/* Recuperation des vols */
		for(Vol v : aeroport.get_trafic().get_liste_vols()) {
			for(Point p : v.getTrajectoire_vol()) {
				p.get_coordonnees_point().getX();
				p.get_coordonnees_point().getY();
			}
		}
	}
	
	/** translate **/
	/** fonction : Translation au niveau de l'affichage du simulateur **/
	public void translate (Graphics g) {
		g.translate(echelle.getX_translation(), echelle.getY_translation());
	}
	
	/** test_affichage_avion **/
	/** fonction : Translation au niveau de l'affichage du simulateur **/
	public void test_affichage_avion (Graphics g) {
		if(aeroport.get_trafic().get_liste_vols().size() != 0){
			Vol v = aeroport.get_trafic().get_liste_vols().get(0);
			for(Point p : v.getTrajectoire_vol()) {
				g.setColor(Color.BLUE);
				g.fillOval(echelle.adapter(p.get_coordonnees_point().getX()), echelle.adapter(echelle.inverser(p.get_coordonnees_point().getY())), 5, 5);
			}
		}
	}
}
