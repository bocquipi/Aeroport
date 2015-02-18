/* Projet JAVA         */
/* Vue                 */
/* author :            */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

public class SimulateurVol extends JPanel {
	
	/** Serialisation **/
	private static final long serialVersionUID = 1L;

	/** Declaration des variables privees **/
	private Plateforme plateforme;
	private AffineTransform affinetransform;
	
	/** Constructeur de la classe SimulateurAeroport **/
	public SimulateurVol(Plateforme plateforme) {

		super();
		this.plateforme = plateforme;
		this.affinetransform = new AffineTransform();
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
		
		/* Appel du constructeur de la classe mere */
		super.paintComponent(g);
		
		/* Graphics -> Graphics2D */
		Graphics2D g2 = (Graphics2D) g;
		
		/* Dimensionnement de l'affichage */
		affinetransform = g2.getTransform();
		affinetransform.translate(plateforme.get_echelle().getX_translation(), plateforme.get_echelle().getY_translation());
		affinetransform.scale(plateforme.get_echelle().get_zoom(), plateforme.get_echelle().get_zoom());
		g2.setTransform(affinetransform);
		
		/* Test : Affichage de la trajectoire d'un avion */ 
		//test_affichage_avion(g2);
		
		/* Recuperation du temps */
		int temps = plateforme.get_time().getTemps();
		
		/* Test : Affichage du temps toutes les secondes */
		//System.out.println("Affichage avion");
		
		/* Declaration de variables locales */
		int x;
		int y;
		
		/* Recuperation des vols */
		for(Vol v : plateforme.get_aeroport().get_trafic().get_liste_vols()) {
			if((temps >= v.getTemps_depart_vol()) && (temps <= v.getTemps_fin_vol())) {
				g2.setColor(Color.BLUE);
				Point p = v.getTrajectoire_vol().get((temps - v.getTemps_depart_vol())/(plateforme.get_time().getPas()));
				x = p.get_coordonnees_point().getX();
				y = plateforme.get_echelle().inverser(p.get_coordonnees_point().getY());
				g2.fillOval(x, y, 10, 10);
				
			}
		}
	}
	
	/** test_affichage_avion **/
	/** fonction : Translation au niveau de l'affichage du simulateur **/
	public void test_affichage_avion (Graphics g2) {
		if(plateforme.get_aeroport().get_trafic().get_liste_vols().size() != 0){
			Vol v = plateforme.get_aeroport().get_trafic().get_liste_vols().get(0);
			for(Point p : v.getTrajectoire_vol()) {
				g2.setColor(Color.BLUE);
				g2.fillOval(p.get_coordonnees_point().getX(), p.get_coordonnees_point().getY(), 5, 5);
			}
		}
	}
}
