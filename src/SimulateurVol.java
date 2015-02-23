/* Projet JAVA         */
/* Vue                 */
/* author :            */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

public class SimulateurVol extends JPanel {
	
	/** Serialisation **/
	private static final long serialVersionUID = 1L;

	/** Declaration des variables privees **/
	private Plateforme plateforme;
	private AffineTransform affinetransform;
	private int	nombre_vols_display;

	/** Constructeur de la classe SimulateurAeroport **/
	public SimulateurVol(Plateforme plateforme) {

		super();
		this.plateforme = plateforme;
		this.affinetransform = new AffineTransform();
		this.nombre_vols_display = 0;
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
		affinetransform.translate(plateforme.get_fenetre().get_simulateur_aeroport().getNewTranslationX(), plateforme.get_fenetre().get_simulateur_aeroport().getNewTranslationY());
		affinetransform.scale(plateforme.get_echelle().get_zoom(), plateforme.get_echelle().get_zoom());
		g2.setTransform(affinetransform);
		
		/* Test : Affichage de la trajectoire d'un avion */ 
		//test_affichage_avion(g2);
		
		/* Recuperation du temps */
		int temps = plateforme.get_time().getTemps();
		
		/* Test : Affichage du temps toutes les secondes */
		//System.out.println("Affichage avion");
		
		/* Declaration de variables locales */
		Vol v;
		ArrayList<Vol> vols_collision;
		int x;
		int y;
		int x_centre;
		int y_centre;
		int index;
		int taille = 100;
		Point p;
		
		/* RAZ du nombre de vols sur la plateforme aeroportuaire */
		setNombre_vols_display(0);
		
		/* RAZ de la JList dans Informations */
		plateforme.get_fenetre().get_informations().get_listeModel().clear();
		
		/* Image : representation des vols */
		Image depart = Toolkit.getDefaultToolkit().getImage("depart.png");
		Image arrive = Toolkit.getDefaultToolkit().getImage("arrive.png");
		Image collision = Toolkit.getDefaultToolkit().getImage("collision.png");
		
		/* Parcours de la liste des vols */
		for(int i = 0 ; i < plateforme.get_aeroport().get_trafic().get_liste_vols().size() ; i++) {
			
			/* Recuperation des vols affichables */
			v = plateforme.get_aeroport().get_trafic().get_liste_vols().get(i);
			if((temps >= v.getTemps_depart_vol()) && (temps < v.getTemps_fin_vol())) {
				index = (temps - v.getTemps_depart_vol())/(plateforme.get_time().getPas()); //Calcul de l'index
				p = v.getTrajectoire_vol().get(index); //Recuperation du point de la trajectoire du vol
				x = p.get_coordonnees_point().getX(); //Recuperation de x
				y = plateforme.get_echelle().inverser(p.get_coordonnees_point().getY()); //Recuperation et inversion de y
				
				/* Calcul du centre de l'image */
				x_centre = x - (taille/2);
				y_centre = y - (taille/2);
			
				/* Affichage du vol en fonction du type (DEP ou ARR) */
				if(v.getType_vol().equals("DEP")) {
					g2.drawImage(depart, x_centre, y_centre, taille, taille, this);
				}
				else {
					g2.drawImage(arrive, x_centre, y_centre, taille, taille, this);
				}
				
				/* Remplissage de la JList dans Informations */
				plateforme.get_fenetre().get_informations().get_listeModel().addElement(v);;
				
				/* Verification de collisions */
				Iterator<ArrayList<Vol>> iterator_valeur = plateforme.get_aeroport().get_trafic().get_collision().getListe_collisions().values().iterator();
				Iterator<Integer> iterator_cle = plateforme.get_aeroport().get_trafic().get_collision().getListe_collisions().keySet().iterator();
				while((iterator_valeur.hasNext()) && (iterator_cle.hasNext())) {
					vols_collision = iterator_valeur.next();
					if(temps == iterator_cle.next()) {
						/* Affichage de la collision */
						if((v.getIdentifiant_vol().equals(vols_collision.get(0).getIdentifiant_vol()))
								|| (v.getIdentifiant_vol().equals(vols_collision.get(1).getIdentifiant_vol()))) {
							g2.drawImage(collision, x_centre, y_centre, taille, taille, this);
						}
					}
				}
				
				/* Calcul du nombre de vols sur la plateforme aeroportuaire */
				setNombre_vols_display(nombre_vols_display++);
			}
		}
		
		/* Exception */
		/* Exception in thread "AWT-EventQueue-0" java.util.ConcurrentModificationException */
//		/* Parcours de la liste des vols */
//		for(Vol v : plateforme.get_aeroport().get_trafic().get_liste_vols()) {
//			
//			/* Recuperation des vols affichables */
//			if((temps >= v.getTemps_depart_vol()) && (temps < v.getTemps_fin_vol())) {
//				index = (temps - v.getTemps_depart_vol())/(plateforme.get_time().getPas()); //Calcul de l'index
//				p = v.getTrajectoire_vol().get(index); //Recuperation du point de la trajectoire du vol
//				x = p.get_coordonnees_point().getX(); //Recuperation de x
//				y = plateforme.get_echelle().inverser(p.get_coordonnees_point().getY()); //Recuperation et inversion de y
//				
//				/* Calcul du centre de l'image */
//				x_centre = x - (taille/2);
//				y_centre = y - (taille/2);
//				
//				/* Affichage du vol en fonction du type (DEP ou ARR) */
//				if(v.getType_vol().equals("DEP")) {
//					g2.drawImage(depart, x_centre, y_centre, taille, taille, this);
//				}
//				else {
//					g2.drawImage(arrive, x_centre, y_centre, taille, taille, this);
//				}
//			}
//		}
	}
	
	/** Getter/Setter de nombre_vols_display **/
	public int getNombre_vols_display() {
		return nombre_vols_display;
	}

	public void setNombre_vols_display(int nombre_vols_display) {
		this.nombre_vols_display = nombre_vols_display;
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
