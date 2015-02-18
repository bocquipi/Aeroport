/* Projet JAVA         */
/* Vue                 */
/* author :            */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
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
		
		/* Image : representation des vols */
		Image depart = Toolkit.getDefaultToolkit().getImage("depart.png");
		Image arrive = Toolkit.getDefaultToolkit().getImage("arrive.png");
		
		/* Recuperation des vols */
		for(Vol v : plateforme.get_aeroport().get_trafic().get_liste_vols()) {
			if((temps >= v.getTemps_depart_vol()) && (temps <= v.getTemps_fin_vol())) {
				Point p = v.getTrajectoire_vol().get((temps - v.getTemps_depart_vol())/(plateforme.get_time().getPas()));
				x = p.get_coordonnees_point().getX();
				y = plateforme.get_echelle().inverser(p.get_coordonnees_point().getY());
				if(v.getType_vol().equals("DEP")) {
					g2.drawImage(depart, x, y, 50, 50, this);
				}
				else {
					g2.drawImage(arrive, x, y, 50, 50, this);
				}
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
