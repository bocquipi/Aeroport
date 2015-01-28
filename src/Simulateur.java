/* Projet JAVA         */
/* Vue                 */
/* author :            */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Simulateur extends JPanel {

	/** Serialisation **/
	private static final long serialVersionUID = 1L;

	/** Declaration des variables privees **/
	private Aeroport aeroport;
	private Echelle echelle;
	
	/* utilisation du pattern Singleton */
	
	/** Constructeur de la classe Simulateur **/
	public Simulateur(Aeroport aeroport, Echelle echelle) {
		super();
		this.setMinimumSize(new Dimension(1200,900));
		this.setPreferredSize(new Dimension(1200,900));
		this.setBackground(Color.WHITE);
		this.aeroport = aeroport;
		this.echelle = echelle;
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
		for(Point p : this.aeroport.get_points()) {
			g.setColor(Color.BLACK);
			x1 = p.get_coordonnees_point().getX();
			y1 = p.get_coordonnees_point().getY();
			g.fillOval(echelle.adapter(x1), echelle.adapter(echelle.inverser(y1)), point, point);
		}
		
		/* Lines */
		for(Line l : this.aeroport.get_lines()) {
			for(int i=0, j=1;j<l.get_coordonnees_line().size();i++,j++) {	
				if (l.get_nom_line().equals("_")) {
					g.setColor(Color.BLUE);
				}
				else {
					g.setColor(Color.RED);
				}
				x1 = l.get_coordonnees_line().get(i).getX();
				y1 = l.get_coordonnees_line().get(i).getY();
				x2 = l.get_coordonnees_line().get(j).getX();
				y2 = l.get_coordonnees_line().get(j).getY();
				g.drawLine(echelle.adapter(x1), echelle.adapter(echelle.inverser(y1)), echelle.adapter(x2), echelle.adapter(echelle.inverser(y2)));
			}
		}
		
		/* Taille du simulateur */
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int hauteur = (int)tailleEcran.getHeight();
		int largeur = (int)tailleEcran.getWidth();
		this.setPreferredSize(new Dimension(hauteur, largeur));
	}
	
	/** translate **/
	/** fonction : Translation au niveau de l'affichage du simulateur **/
	public void translate (Graphics g) {
		g.translate(echelle.getX_translation(), echelle.getY_translation());
		System.out.println(echelle.getX_translation());
		System.out.println(echelle.getY_translation());
		System.out.println(echelle.getX_adapte());
		System.out.println(echelle.getY_adapte());
		
	}
}
