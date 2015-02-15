/* Projet JAVA         */
/* Vue                 */
/* author :            */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JPanel;

public class SimulateurAeroport extends JPanel {

	/** Serialisation **/
	private static final long serialVersionUID = 1L;

	/** Declaration des variables privees **/
	private Plateforme plateforme;
	
	/* utilisation du pattern Singleton */
	
	/** Constructeur de la classe SimulateurAeroport **/
	public SimulateurAeroport(Plateforme plateforme) {
		
		super();
		this.plateforme = plateforme;
		this.setOpaque(true);
		/* Taille de la fenetre */
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int hauteur = (int)tailleEcran.getHeight();
		int largeur = (int)tailleEcran.getWidth();
		this.setMinimumSize(new Dimension(hauteur,largeur));
		this.setPreferredSize(new Dimension(hauteur, largeur));
		this.setBounds(0, 0, hauteur, largeur);
	}
	
	/** paintComponent **/
	/** fonction : Construction du simulateur **/
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		/*Translation*/
		translate(g);
		
		/* Variables locales */
		int x1;
		int y1;
		int x2;
		int y2;
		
		/* Points */
		int point = 5;
		for(Point p : plateforme.get_aeroport().get_points()) {
			if (p.get_type_point()==0) {
				g.setColor(Color.BLUE); //Stand ou Aire de parking
			}
			if (p.get_type_point()==1) {
				g.setColor(Color.BLACK); //Deicing ou Zone de dégel
			}
			if(p.get_type_point()==2) {
				g.setColor(Color.GRAY); //Runway_Point ou Point d'intersection d'une piste
			}
			
			/* Recuperation des coordonnees */
			x1 = p.get_coordonnees_point().getX();
			y1 = p.get_coordonnees_point().getY();
			/* Creation du point */
			g.fillOval(plateforme.get_echelle().adapter(x1), plateforme.get_echelle().adapter(plateforme.get_echelle().inverser(y1)), point, point);
		}
		
		/* Lines */
		for(Line l : plateforme.get_aeroport().get_lines()) {
			Graphics2D g2 = (Graphics2D) g; //Utilisation de Graphics2D pour le BasicStroke
			for(int i=0, j=1;j<l.get_coordonnees_line().size();i++,j++) {
				if(l.get_nom_line().equals("_")) {
					g2.setColor(Color.BLACK); //Piste basique
				}
				else {
					g2.setColor(Color.GRAY); //Taxiway
				}
				
				/* Categorie des lines */
//				if(l.get_categorie_line()=='H') {
//					g.setColor(Color.GREEN);
//				}
//				if(l.get_categorie_line()=='M') {
//					g.setColor(Color.GRAY);
//				}
//				if(l.get_categorie_line()=='L') {
//					g.setColor(Color.MAGENTA);				
//				}
								
				/* Direction des lines (simple ou double) */				
//				if(l.get_direction_line()=='S') {
//					g2.setStroke(new BasicStroke(1));
//				}					
//				if(l.get_direction_line()=='D') {
//					g2.setStroke(new BasicStroke(2));
//				}
                		
				/* Vitesse de roulage des lines */
				if(l.get_vitesse_roulage() <= 0) {
						Stroke dashed = new BasicStroke(1,BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,0, new float[]{8},0);
					    g2.setStroke(dashed);
				}
				
				/* Recuperation des coordonnees */
				x1 = l.get_coordonnees_line().get(i).getX();
				y1 = l.get_coordonnees_line().get(i).getY();
				x2 = l.get_coordonnees_line().get(j).getX();
				y2 = l.get_coordonnees_line().get(j).getY();
				
				/* Creation des lines */
				g2.drawLine(plateforme.get_echelle().adapter(x1), plateforme.get_echelle().adapter(plateforme.get_echelle().inverser(y1)), plateforme.get_echelle().adapter(x2), plateforme.get_echelle().adapter(plateforme.get_echelle().inverser(y2)));
			}
		}
		
		/* Runway */
		for(Runway r: plateforme.get_aeroport().get_runways()) {
			
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.BLACK);
			Stroke dashed = new BasicStroke(1,BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,0, new float[]{8},0);
			g2.setStroke(dashed);
			
			/* Recuperation des coordonnees */
			x1 = r.get_coordonnees_extremites().get(0).getX();
			y1 = r.get_coordonnees_extremites().get(0).getY();
			x2 = r.get_coordonnees_extremites().get(1).getX();
			y2 = r.get_coordonnees_extremites().get(1).getY();
			
			/* Creation des runways */
			g2.drawLine(plateforme.get_echelle().adapter(x1), plateforme.get_echelle().adapter(plateforme.get_echelle().inverser(y1)), plateforme.get_echelle().adapter(x2), plateforme.get_echelle().adapter(plateforme.get_echelle().inverser(y2)));
		}
	}
	
	/** translate **/
	/** fonction : Translation au niveau de l'affichage du simulateur **/
	public void translate (Graphics g) {
		g.translate(plateforme.get_echelle().getX_translation(), plateforme.get_echelle().getY_translation());
	}
}
