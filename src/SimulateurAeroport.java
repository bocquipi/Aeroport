/* Projet JAVA         */
/* Vue                 */
/* author :            */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

public class SimulateurAeroport extends JPanel {

	/** Serialisation **/
	private static final long serialVersionUID = 1L;

	/** Declaration des variables privees **/
	private Plateforme plateforme;
	private AffineTransform affinetransform;
	private int newTranslationX;
	private int newTranslationY;
	
	/* utilisation du pattern Singleton */
	
	/** Constructeur de la classe SimulateurAeroport **/
	public SimulateurAeroport(Plateforme plateforme) {
		
		super();
		this.plateforme = plateforme;
		this.affinetransform = new AffineTransform();
		this.newTranslationX = 0;
		this.newTranslationY = 0;
		this.setOpaque(true);
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
		affinetransform.translate(newTranslationX, newTranslationY);
		affinetransform.scale(plateforme.get_echelle().get_zoom(), plateforme.get_echelle().get_zoom());
		g2.setTransform(affinetransform);
		
		/* Variables locales */
		int x1;
		int y1;
		int x2;
		int y2;
		
		/* Points */
		int point = 20;
		
		for(int i = 0; i < plateforme.get_aeroport().get_points().size() ; i++) {
			if (plateforme.get_aeroport().get_points().get(i).get_type_point() == 0) {
				g2.setColor(Color.BLUE); //Stand ou Aire de parking
			}
			if (plateforme.get_aeroport().get_points().get(i).get_type_point() == 0) {
				g2.setColor(Color.BLACK); //Deicing ou Zone de degel
			}
			if(plateforme.get_aeroport().get_points().get(i).get_type_point() == 0) {
				g2.setColor(Color.RED); //Runway_Point ou Point d'intersection d'une piste
			}
			
			/* Recuperation des coordonnees */
			x1 = plateforme.get_aeroport().get_points().get(i).get_coordonnees_point().getX();
			y1 = plateforme.get_echelle().inverser(plateforme.get_aeroport().get_points().get(i).get_coordonnees_point().getY());
			
			/* Creation du point */
			g2.fillOval(x1, y1, point, point);
		}
		
		/* Exception */
		/* Exception in thread "AWT-EventQueue-0" java.util.ConcurrentModificationException */
		/* Analyse : Modification par affinetransform */
//		for(Point p : plateforme.get_aeroport().get_points()) {
//			if (p.get_type_point()==0) {
//				g2.setColor(Color.BLUE); //Stand ou Aire de parking
//			}
//			if (p.get_type_point()==1) {
//				g2.setColor(Color.BLACK); //Deicing ou Zone de degel
//			}
//			if(p.get_type_point()==2) {
//				g2.setColor(Color.RED); //Runway_Point ou Point d'intersection d'une piste
//			}
//			
//			/* Recuperation des coordonnees */
//			x1 = p.get_coordonnees_point().getX();
//			y1 = plateforme.get_echelle().inverser(p.get_coordonnees_point().getY());
//			
//			/* Creation du point */
//			g2.fillOval(x1, y1, point, point);
//		}
		
		/* Lines */
		for(int i = 0; i < plateforme.get_aeroport().get_lines().size() ; i++) {
			for(int j = 0, k = 1 ; k < plateforme.get_aeroport().get_lines().get(i).get_coordonnees_line().size() ; j++, k++) {
				
//				/* Categorie des lines */
//				if(plateforme.get_aeroport().get_lines().get(i).get_categorie_line() == 'H') {
//					g2.setColor(Color.GREEN);
//				}
//				if(plateforme.get_aeroport().get_lines().get(i).get_categorie_line() =='M') {
//					g2.setColor(Color.GRAY);
//				}
//				if(plateforme.get_aeroport().get_lines().get(i).get_categorie_line()=='L') {
//					g2.setColor(Color.MAGENTA);				
//				}
				
				/* Nom des pistes (Basique ou Taxiway) */
				if(plateforme.get_aeroport().get_lines().get(i).get_nom_line().equals("_")) {
					g2.setColor(Color.BLACK); //Piste basique
				}
				else {
					g2.setColor(Color.GRAY); //Taxiway
				}
								
				/* Direction des lines (simple ou double) */				
				if(plateforme.get_aeroport().get_lines().get(i).get_direction_line()=='S') {
					g2.setStroke(new BasicStroke(1));
				}					
				if(plateforme.get_aeroport().get_lines().get(i).get_direction_line()=='D') {
					g2.setStroke(new BasicStroke(10));
				}
                		
				/* Vitesse de roulage des lines */
				if(plateforme.get_aeroport().get_lines().get(i).get_vitesse_roulage() <= 0) {
						Stroke dashed = new BasicStroke(1,BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,0, new float[]{8},0);
					    g2.setStroke(dashed);
				}
				
				/* Recuperation des coordonnees */
				x1 = plateforme.get_aeroport().get_lines().get(i).get_coordonnees_line().get(j).getX();
				y1 = plateforme.get_echelle().inverser(plateforme.get_aeroport().get_lines().get(i).get_coordonnees_line().get(j).getY());
				x2 = plateforme.get_aeroport().get_lines().get(i).get_coordonnees_line().get(k).getX();
				y2 = plateforme.get_echelle().inverser(plateforme.get_aeroport().get_lines().get(i).get_coordonnees_line().get(k).getY());
				
				/* Creation des lines */
				g2.drawLine(x1, y1, x2, y2);
			}
		}
		
		/* Exception */
		/* Exception in thread "AWT-EventQueue-0" java.util.ConcurrentModificationException */
		/* Analyse : Modification par affinetransform */
//		for(Line l : plateforme.get_aeroport().get_lines()) {
//			for(int i=0, j=1;j<l.get_coordonnees_line().size();i++,j++) {
//				
//				/* Categorie des lines */
//				if(l.get_categorie_line() == 'H') {
//					g2.setColor(Color.GREEN);
//				}
//				if(l.get_categorie_line() =='M') {
//					g2.setColor(Color.GRAY);
//				}
//				if(l.get_categorie_line()=='L') {
//					g2.setColor(Color.MAGENTA);				
//				}
//				
//				/* Nom des pistes (Basique ou Taxiway) */
//				if(l.get_nom_line().equals("_")) {
//					g2.setColor(Color.BLACK); //Piste basique
//				}
//				else {
//					g2.setColor(Color.GRAY); //Taxiway
//				}
//								
//				/* Direction des lines (simple ou double) */				
//				if(l.get_direction_line()=='S') {
//					g2.setStroke(new BasicStroke(1));
//				}					
//				if(l.get_direction_line()=='D') {
//					g2.setStroke(new BasicStroke(2));
//				}
//                		
//				/* Vitesse de roulage des lines */
//				if(l.get_vitesse_roulage() <= 0) {
//						Stroke dashed = new BasicStroke(1,BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,0, new float[]{8},0);
//					    g2.setStroke(dashed);
//				}
//				
//				/* Recuperation des coordonnees */
//				x1 = l.get_coordonnees_line().get(i).getX();
//				y1 = plateforme.get_echelle().inverser(l.get_coordonnees_line().get(i).getY());
//				x2 = l.get_coordonnees_line().get(j).getX();
//				y2 = plateforme.get_echelle().inverser(l.get_coordonnees_line().get(j).getY());
//				
//				/* Creation des lines */
//				g2.drawLine(x1, y1, x2, y2);
//			}
//		}
		
		/* Runway */
		for(Runway r: plateforme.get_aeroport().get_runways()) {
			
			/* Recuperation des coordonnees */
			x1 = r.get_coordonnees_extremites().get(0).getX();
			y1 = plateforme.get_echelle().inverser(r.get_coordonnees_extremites().get(0).getY());
			x2 = r.get_coordonnees_extremites().get(1).getX();
			y2 = plateforme.get_echelle().inverser(r.get_coordonnees_extremites().get(1).getY());
			
			/* Configuration de l'affichage */
			int largeur_piste = 60;
			g2.setColor(new Color(0,0,0,0.5f)); //Creation d'une nouvelle couleur en RGBA (A determine l'opacite)
	
			/* Creation des pistes */
			int x3[] = {x1, x2, x2, x1};
			int y3[] = {y1+largeur_piste, y2+largeur_piste, y2-largeur_piste , y1-largeur_piste};				
			g2.fillPolygon(x3, y3, 4);
			
			/* Configuration de l'affichage */
			g2.setColor(Color.WHITE);
			Stroke dashed = new BasicStroke(1,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,0,new float[]{50},0);
			g2.setStroke(dashed);
			
			/* Creation des runways */
			g2.drawLine(x1, y1, x2, y2);
		}
	}
	
	/** Getter/Setter de newTranslationX **/
	public int getNewTranslationX() {
		return newTranslationX;
	}

	public void setNewTranslationX(int newTranslationX) {
		this.newTranslationX = newTranslationX;
	}

	/** Getter/Setter de newTranslationX **/
	public int getNewTranslationY() {
		return newTranslationY;
	}

	public void setNewTranslationY(int newTranslationY) {
		this.newTranslationY = newTranslationY;
	}
}
