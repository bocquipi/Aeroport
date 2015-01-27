import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Dialogue extends JDialog {
	
	/** Serialisation **/
	private static final long serialVersionUID = 1L;
	
	/** Declaration des variables privees **/
	private JLabel aeroport;
	private JLabel trafic;
	private JLabel icone;
	private JTextField nom_fichier_aeroport;
	private JTextField nom_fichier_trafic;
	private ImageIcon image = new ImageIcon("air_control.jpg");
	
	/** Constructeur de la classe Dialogue **/
	public Dialogue(JFrame parent, String title, boolean modal) {
		super(parent, title, modal);
		this.initDialogue();
		this.pack();
	}
	
	/** initDialogue **/
	/** fonction : Construction de la boite de dialogue **/
	private void initDialogue() {
		
		/* icone */
		icone = new JLabel(image);
		
		/* aeroport */
		aeroport = new JLabel("Saisir le nom du fichier :");
		
		/* nom_fichier_aeroport */
		nom_fichier_aeroport = new JTextField();
		nom_fichier_aeroport.setPreferredSize(new Dimension(100, 25));
		
		/* trafic */
		trafic = new JLabel("Saisir le nom du fichier :");
		
		/* nom_fichier_trafic */
		nom_fichier_trafic = new JTextField();	
		nom_fichier_trafic.setPreferredSize(new Dimension(100, 25));
		
		/* boutonOk */
		JButton boutonOk = new JButton("OK");
		boutonOk.addActionListener(new ActionValidation());
		
		/* boutonCancel */
		JButton boutonCancel = new JButton("Annuler");
		boutonCancel.addActionListener(new ActionAnnulation());
		
		/* panelIcone */
		JPanel panelIcone = new JPanel();
		panelIcone.add(icone);

		/* panelAeroport */
		JPanel panelAeroport = new JPanel();
		panelAeroport.setBorder(BorderFactory.createTitledBorder("Chargement du fichier aeroport"));
		panelAeroport.add(aeroport);
		panelAeroport.add(nom_fichier_aeroport);
			
		/* panelTrafic */
		JPanel panelTrafic = new JPanel();
		panelTrafic.setBorder(BorderFactory.createTitledBorder("Chargement du fichier trafic"));
		panelTrafic.add(trafic);
		panelTrafic.add(nom_fichier_trafic);
		
		/* panelChargement */
		JPanel panelChargement = new JPanel();
		panelChargement.setLayout(new GridLayout(1,2));
		panelChargement.add(panelAeroport);
		panelChargement.add(panelTrafic);
		
		/* panelBouton */
		JPanel panelBouton = new JPanel();
		panelBouton.add(boutonOk);
		panelBouton.add(boutonCancel);
			
		/* Conteneur */
		Container conteneur = this.getContentPane();
		conteneur.setLayout(new GridLayout(3,1));
		conteneur.add(panelIcone);
		conteneur.add(panelChargement);
		conteneur.add(panelBouton);
    }
	
	/** Classe inner pour les listeners **/
	
	/* Class ActionValidation */
	/* fonction : recuperation du nom des fichiers textes */
	class ActionValidation implements ActionListener {
		
	    public void actionPerformed(ActionEvent ae) {
	    	//set_nom_fichier_aeroport(nom_fichier_aeroport.getText());
	    	//set_nom_fichier_aeroport(nom_fichier_trafic.getText());
	    	Dialogue.this.setVisible(false);
	    }  
	}
	
	/* Class ActionAnnulation */
	/* fonction : fermeture de la boite de dialogue */
	class ActionAnnulation implements ActionListener {
		
	    public void actionPerformed(ActionEvent ae) {
	    	Dialogue.this.setVisible(false);
	    }  
	}
}