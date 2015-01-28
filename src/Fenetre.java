/* Projet JAVA         */
/* Vue                 */
/* author :            */

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Fenetre extends JFrame {

	/** Serialisation **/
	private static final long serialVersionUID = 1L;
	
	/** Declaration des variables privees **/
	private Plateforme plateforme;
	private Simulateur pSimulateur;
	private Dialogue dialogue;
	private File repertoire_courant;
	private JFileChooser filechooser;
	private JMenuBar menuBar;
	private JMenu fichier;
	private JMenu charger_fichier;
	private JMenuItem charger_aeroport;
	private JMenuItem charger_trafic;
	private JMenu boite_dialogue;
	private JMenuItem ouvrir;
	private JMenuItem fermer;
	private Container conteneur;

	/* utilisation du pattern Singleton */
	
	/** Constructeur de la classe Fenetre **/
	public Fenetre(Plateforme plateforme) {
		
		/* Icone de la fenetre */
		Image icone = Toolkit.getDefaultToolkit().getImage("air_control.png");
		this.setIconImage(icone);
		
		/* Plateforme */
		this.plateforme = plateforme;
		
		/* FileChooser */
	    repertoire_courant = null;
		filechooser = new JFileChooser(repertoire_courant);
	    
	    /* Boite de dialogue */
		dialogue = new Dialogue(null, "Gestion de fichiers", true);

		/* Items principaux */
		menuBar = new JMenuBar();
		fichier = new JMenu("Fichier");
		boite_dialogue = new JMenu("Boite de dialogue");
		this.menuBar.add(fichier);
	    this.menuBar.add(boite_dialogue);
	    this.setJMenuBar(menuBar);
	    
	    /* Items secondaires */
	    charger_fichier = new JMenu("Charger un fichier");
	    ouvrir = new JMenuItem("Ouvrir");
		fermer = new JMenuItem("Fermer");
	    this.fichier.add(charger_fichier);
	    this.boite_dialogue.add(ouvrir);
	    ouvrir.addActionListener(new ActionOuvrir());
	    this.boite_dialogue.add(fermer);
	    fermer.addActionListener(new ActionFermer());
	    
	    /* Items tertiaires */
	    charger_aeroport = new JMenuItem("Aeroport");
		charger_trafic = new JMenuItem("Trafic");
	    this.charger_fichier.add(charger_aeroport);
	    charger_aeroport.addActionListener(new ActionChargerAeroport());
	    this.charger_fichier.add(charger_trafic);
	    charger_trafic.addActionListener(new ActionChargerTrafic());
	    
	    /* Conteneur */
	    conteneur = new Container();
	    conteneur = this.getContentPane();
	    conteneur.setLayout(new GridBagLayout());
	    
	    /* GridBagConstraints */
	    GridBagConstraints gbc = new GridBagConstraints();
	    
	    /* Positionnement de la case initiale */
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    
	    /* Contrainte de la case */
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.fill = GridBagConstraints.VERTICAL;
	    
	    /* Panel pSimulateur */
	    pSimulateur = new Simulateur(plateforme.get_aeroport(), plateforme.get_echelle());
	    conteneur.add(pSimulateur, gbc);
	    
	    /* Positionnement de la case initiale */
	    gbc.gridx = 1;
	    gbc.gridy = 0;
	    
	    /* Contrainte de la case */
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.fill = GridBagConstraints.VERTICAL;
	    
	    /* Panel pInformations */
	    JPanel pInformations = new JPanel();
	    conteneur.add(pInformations, gbc);
	    
		/* Listeners */
		this.addMouseListener(new ActionMouseListener());
		this.addMouseMotionListener(new ActionMouseMotion());
		this.addMouseWheelListener(new ActionMouseWheel());
	    
	    /* Affichage de la fenetre */
	    this.pack();
	    this.setVisible(true);
	}
	
	/** Getter de simulateur **/
	public Simulateur get_simulateur() {
		return pSimulateur;
	}
	
	/** Classe inner pour les listeners **/
	
	/* Class ActionOuvrir */
	/* fonction : ouvrir la boite de dialogue */
	class ActionOuvrir implements ActionListener {
		
	    public void actionPerformed(ActionEvent ae) {
	    	dialogue.setVisible(true);
	    }  
	}
	
	/* Class ActionFermer */
	/* fonction : fermer la boite de dialogue */
	class ActionFermer implements ActionListener {
		
	    public void actionPerformed(ActionEvent ae) {
	    	dialogue.setVisible(false);
	    }  
	}
	
	/* Class ActionChargerAeroport */
	/* fonction : recuperation et chargement du fichier aeroport */
	class ActionChargerAeroport implements ActionListener {
		
	    public void actionPerformed(ActionEvent ae) {
	    	try {
				repertoire_courant = new File(".").getCanonicalFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Erreur au niveau de la recuperation du nom du fichier");
				e.printStackTrace();
			}
	    	System.out.println("Repertoire courant : " + repertoire_courant);
	    	filechooser.showOpenDialog(null);
	    	System.out.println("Fichier choisi : " + filechooser.getSelectedFile());
	    	plateforme.get_aeroport().set_nom_fichier_aeroport(filechooser.getSelectedFile().toString());
	    	plateforme.get_aeroport().charger_fichier_aeroport(plateforme.get_aeroport());
	    	/* TEST */
	    	//plateforme.get_aeroport().test_programme_aeroport(plateforme.get_aeroport());
	    	//plateforme.get_aeroport().test_valeur_coordonnees_aeroport();
	    }
	}
	
	/* Class ActionChargerTrafic */
	/* fonction : recuperation et chargement du fichier aeroport */
	class ActionChargerTrafic implements ActionListener {
		
	    public void actionPerformed(ActionEvent ae) {
	    	try {
				repertoire_courant = new File(".").getCanonicalFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Erreur au niveau de la recuperation du nom du fichier");
				e.printStackTrace();
			}
	    	System.out.println("Repertoire courant : " + repertoire_courant);
	    	filechooser.showOpenDialog(null);
	    	System.out.println("Fichier choisi : " + filechooser.getSelectedFile());
	    	plateforme.get_aeroport().get_trafic().set_nom_fichier_trafic(filechooser.getSelectedFile().toString());
	    	/* TEST */
	    	//plateforme.get_aeroport().get_trafic().calcul_nombre_vols();
	    	plateforme.get_aeroport().get_trafic().charger_fichier_trafic(plateforme.get_aeroport().get_trafic());
	    	/* TEST */
	    	//plateforme.get_aeroport().get_trafic().test_programme_trafic(plateforme.get_aeroport().get_trafic());
	    }  
	}
	
	/** Classe inner pour les listeners **/
	
	/* Class ActionMouseListener */
	/* fonction : MouseListener */
	class ActionMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			/* Position du curseur de la souris */
			plateforme.get_echelle().setX_translation((int)(e.getPoint().getX() + getLocationOnScreen().getX()));
			plateforme.get_echelle().setY_translation((int)(e.getPoint().getY() + getLocationOnScreen().getY()));
			repaint();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		} 
	}
	
	/* Class ActionMouseMotion */
	/* fonction : MouseMotionListener */
	class ActionMouseMotion implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	/* Class ActionMouseMotion */
	/* fonction : MouseMotionListener */
	class ActionMouseWheel implements MouseWheelListener {

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			// TODO Auto-generated method stub
			int index_zoom = plateforme.get_echelle().get_index_zoom();
			double tableau_zoom[] = plateforme.get_echelle().get_tableau_zoom();
			int wheelRotation = e.getWheelRotation();
			/* Zoom + */
			if(wheelRotation == 1) {
				if(index_zoom != (tableau_zoom.length-1) ) {
					index_zoom++;
				}
			}
			/* Zoom - */
			else {
				if(index_zoom != 0) {
					index_zoom--;
				}
			}
			plateforme.get_echelle().set_index_zoom(index_zoom);
			plateforme.get_echelle().set_zoom(tableau_zoom[index_zoom]);
			System.out.println("Zoom = " + plateforme.get_echelle().get_zoom());
			repaint();
		}
	}
}