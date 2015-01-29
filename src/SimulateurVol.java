/* Projet JAVA         */
/* Vue                 */
/* author :            */

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class SimulateurVol extends JPanel {
	
	/** Serialisation **/
	private static final long serialVersionUID = 1L;

	/** Constructeur de la classe SimulateurAeroport **/
	public SimulateurVol() {
		super();
		
		/* Taille du SimulateurAeroport */
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int hauteur = (int)tailleEcran.getHeight();
		int largeur = (int)tailleEcran.getWidth();
		this.setMinimumSize(new Dimension(hauteur,largeur));
		this.setPreferredSize(new Dimension(hauteur, largeur));
		this.setBounds(0, 0, hauteur, largeur);
		
		this.setBackground(Color.WHITE);
	}
}
