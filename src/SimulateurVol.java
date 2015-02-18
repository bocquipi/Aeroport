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
	private Plateforme plateforme;
	
	/** Constructeur de la classe SimulateurAeroport **/
	public SimulateurVol(Plateforme plateforme) {

		super();
		this.plateforme = plateforme;
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
		//test_affichage_avion(g);
		
		/* Recuperation du temps */
		int temps = plateforme.get_time().getTemps();
		
		/* Test : Affichage du temps toutes les secondes */
		System.out.println("Affichage avion");
		
		/* Recuperation des vols */
		for(Vol v : plateforme.get_aeroport().get_trafic().get_liste_vols()) {
			if((temps >= v.getTemps_depart_vol())&&(temps <= v.getTemps_fin_vol())) {
				Point p = v.getTrajectoire_vol().get((temps - v.getTemps_depart_vol())/(plateforme.get_time().getPas()));
				g.fillOval(plateforme.get_echelle().adapter(p.get_coordonnees_point().getX()), plateforme.get_echelle().adapter(plateforme.get_echelle().inverser(p.get_coordonnees_point().getY())), 20, 20);
				
			}
		}
	}
	
	/** translate **/
	/** fonction : Translation au niveau de l'affichage du simulateur **/
	public void translate (Graphics g) {
		g.translate(plateforme.get_echelle().getX_translation(), plateforme.get_echelle().getY_translation());
	}
	
	/** test_affichage_avion **/
	/** fonction : Translation au niveau de l'affichage du simulateur **/
	public void test_affichage_avion (Graphics g) {
		if(plateforme.get_aeroport().get_trafic().get_liste_vols().size() != 0){
			Vol v = plateforme.get_aeroport().get_trafic().get_liste_vols().get(0);
			for(Point p : v.getTrajectoire_vol()) {
				g.setColor(Color.BLUE);
				g.fillOval(plateforme.get_echelle().adapter(p.get_coordonnees_point().getX()), plateforme.get_echelle().adapter(plateforme.get_echelle().inverser(p.get_coordonnees_point().getY())), 5, 5);
			}
		}
	}
}
