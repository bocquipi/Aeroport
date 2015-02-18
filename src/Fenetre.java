/* Projet JAVA         */
/* Vue                 */
/* author :            */

import java.awt.Container;
import java.awt.Dimension;
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
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

public class Fenetre extends JFrame {

	/** Serialisation **/
	private static final long serialVersionUID = 1L;
	
	/** Declaration des variables privees **/
	private Plateforme plateforme;
	
	private Container conteneur; /* Conteneur */
	private JLayeredPane layer; /* JLayeredPane */
	
	private SimulateurAeroport pSimulateurAeroport; /* JPanel */
	private SimulateurVol pSimulateurVol;
	private Informations pInformations;
	
	private Dialogue dialogue; /* Boite de dialogue */
	
	private File repertoire_courant; /* File */
	private JFileChooser filechooser;
	
	private JMenuBar menuBar; /* MenuBar */
	
	private JMenu fichier; /* JMenu (Gestion de fichier) */
	private JMenu charger_fichier;
	private JMenuItem charger_aeroport; /* JMenuItem (Gestion de fichier) */
	private JMenuItem charger_trafic;

	private JMenu menu_timer; /* JMenu (Timer) */
	private JMenuItem ouvrir_timer; /* JMenuItem (Timer) */
	private JMenuItem fermer_timer;
	
	private JMenu boite_dialogue; /* JMenu (Boite de dialogue) */
	private JMenuItem ouvrir_boite_dialogue; /* JMenuItem (Boite de dialogue) */
	private JMenuItem fermer_boite_dialogue;

	/* utilisation du pattern Singleton */
	
	/** Constructeur de la classe Fenetre **/
	public Fenetre(Plateforme plateforme) {
		
		/* Plateforme */
		this.plateforme = plateforme;
		
		/* Icone de la fenetre */
		Image icone = Toolkit.getDefaultToolkit().getImage("icone.png");
		this.setIconImage(icone);
		
		/* Taille de la fenetre */
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int hauteur = (int)tailleEcran.getHeight();
		int largeur = (int)tailleEcran.getWidth();
		this.setPreferredSize(new Dimension(hauteur, largeur));
		
		/* FileChooser */
	    repertoire_courant = null;
		filechooser = new JFileChooser(repertoire_courant);
	    
	    /* Boite de dialogue */
		dialogue = new Dialogue(null, "Gestion de fichiers", true);
		
		/* MenuBar */
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		/* Menus primaires */
		fichier = new JMenu("Fichier");
		menu_timer = new JMenu("Timer");
		boite_dialogue = new JMenu("Boite de dialogue");
		menuBar.add(fichier);
		menuBar.add(menu_timer);
	    menuBar.add(boite_dialogue);
	    
	    /* Menus secondaires */
	    charger_fichier = new JMenu("Charger un fichier");
	    fichier.add(charger_fichier);
	    
	    /* MenuItems charger_fichier */
	    charger_aeroport = new JMenuItem("Aeroport");
		charger_trafic = new JMenuItem("Trafic");
	    charger_fichier.add(charger_aeroport);
	    charger_fichier.add(charger_trafic);
	    
	    /* MenuItems boite_dialogue */
	    ouvrir_boite_dialogue = new JMenuItem("Ouvrir");
		fermer_boite_dialogue = new JMenuItem("Fermer");
	    boite_dialogue.add(ouvrir_boite_dialogue);
	    boite_dialogue.add(fermer_boite_dialogue);
	    
	    /* MenuItems boite_dialogue */
	    ouvrir_timer = new JMenuItem("Ouvrir");
		fermer_timer = new JMenuItem("Fermer");
		menu_timer.add(ouvrir_timer);
		menu_timer.add(fermer_timer);
	    
	    /* Conteneur */
	    conteneur = new Container();
	    conteneur = this.getContentPane();
	    conteneur.setLayout(new GridBagLayout());
	    
	    /* GridBagConstraints */
	    GridBagConstraints gbc = new GridBagConstraints();
	    
	    //x correspond au colonne
	    //y correspond au ligne
	    
	    /* Positionnement de la case initiale */
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    
	    /* Contrainte de la case */
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.weightx = 95;
	    
	    /* LayeredPane layer */
	    layer = new JLayeredPane();
	    layer.setOpaque(false);
	    layer.setPreferredSize(new Dimension(hauteur, largeur));
	    conteneur.add(layer, gbc);
	    
	    /* Panel pSimulateurAeroport */
	    pSimulateurAeroport = new SimulateurAeroport(plateforme);
	    pSimulateurAeroport.setSize(layer.getSize());
	    pSimulateurAeroport.setBounds(0, 0, largeur, hauteur);
	    layer.add(pSimulateurAeroport, 1);
	    
	    /* Panel pSimulateurVol */
	    pSimulateurVol = new SimulateurVol(plateforme);
	    pSimulateurVol.setPreferredSize(layer.getSize());
	    pSimulateurVol.setBounds(0, 0, largeur, hauteur);
	    layer.add(pSimulateurVol, 0);
	    
	    /* Positionnement de la case initiale */
	    gbc.gridx = 1;
	    gbc.gridy = 0;
	    
	    /* Contrainte de la case */
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.weightx = 5;
	    
	    /* Panel pInformations */
	    pInformations = new Informations(plateforme.get_aeroport(), plateforme.get_echelle());
	    conteneur.add(pInformations, gbc);
	    
	    /* Listeners */
	    /* JMenuItem */
	    charger_aeroport.addActionListener(new ActionChargerAeroport());
	    charger_trafic.addActionListener(new ActionChargerTrafic());
	    ouvrir_boite_dialogue.addActionListener(new ActionOuvrirBD());
	    fermer_boite_dialogue.addActionListener(new ActionFermerBD());
	    ouvrir_timer.addActionListener(new ActionOuvrirTimer());
	    fermer_timer.addActionListener(new ActionFermerTimer());
	    /* MouseListener */
		this.addMouseListener(new ActionMouseListener());
		this.addMouseMotionListener(new ActionMouseMotion());
		this.addMouseWheelListener(new ActionMouseWheel());
	    
	    /* Affichage de la fenetre */
	    this.pack();
	    this.setVisible(true);
	}
	
	/** Getter de pSimulateurAeroport **/
	public SimulateurAeroport get_simulateur_aeroport() {
		return pSimulateurAeroport;
	}
	
	/** Getter de pSimulateurAeroport **/
	public SimulateurVol get_simulateur_vol() {
		return pSimulateurVol;
	}
	
	/** Classe inner pour les listeners **/
	
	/* Class ActionOuvrirTimer */
	/* fonction : ouvrir le timer */
	class ActionOuvrirTimer implements ActionListener {
		
	    public void actionPerformed(ActionEvent ae) {
	    	plateforme.get_afficheur_time().setVisible(true);
	    }  
	}
	
	/* Class ActionFermerTimer */
	/* fonction : fermer le timer */
	class ActionFermerTimer implements ActionListener {
		
	    public void actionPerformed(ActionEvent ae) {
	    	plateforme.get_afficheur_time().setVisible(false);
	    }  
	}
	
	/* Class ActionOuvrirBD */
	/* fonction : ouvrir la boite de dialogue */
	class ActionOuvrirBD implements ActionListener {
		
	    public void actionPerformed(ActionEvent ae) {
	    	dialogue.setVisible(true);
	    }  
	}
	
	/* Class ActionFermerBD */
	/* fonction : fermer la boite de dialogue */
	class ActionFermerBD implements ActionListener {
		
	    public void actionPerformed(ActionEvent ae) {
	    	dialogue.setVisible(false);
	    }  
	}
	
	/* Class ActionChargerAeroport */
	/* fonction : recuperation et chargement du fichier aeroport */
	class ActionChargerAeroport implements ActionListener {
		
	    public void actionPerformed(ActionEvent ae) {
	    	
	    	new Thread() {
				public void run() {
					
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
			    	
			    	/* Chargement du fichier trafic */
			    	plateforme.get_aeroport().charger_fichier_aeroport(plateforme.get_aeroport());
			    	
			    	/* TEST */
			    	//plateforme.get_aeroport().test_programme_aeroport(plateforme.get_aeroport());
			    	//plateforme.get_aeroport().test_valeur_coordonnees_aeroport();
			    	
					/* Mise a jour de l'echelle */
			    	plateforme.get_echelle().calculer_translation();
					
					SwingUtilities.invokeLater(
							new Runnable() {
								public void run() {
									/* Mise a jour du simulateur */
									plateforme.get_fenetre().get_simulateur_aeroport().repaint();
								}
								
							}
					);
					
				}
			}.start();
	    }
	}
	
	/* Class ActionChargerTrafic */
	/* fonction : recuperation et chargement du fichier aeroport */
	class ActionChargerTrafic implements ActionListener {
		
	    public void actionPerformed(ActionEvent ae) {
	    	
	    	new Thread(){
	    		public void run(){
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
	    	    	
	    	    	/* TEST : Calcul du nombre de vols */
	    	    	//plateforme.get_aeroport().get_trafic().calcul_nombre_vols();
	    	    	
	    	    	/* Chargement du fichier trafic */
	    	    	plateforme.get_aeroport().get_trafic().charger_fichier_trafic(plateforme.get_aeroport().get_trafic());
	    	    	
	    	    	/* TEST : Recuperation du fichier texte aeroport */
	    	    	//plateforme.get_aeroport().get_trafic().test_programme_trafic(plateforme.get_aeroport().get_trafic());
	    		}
	    	}.start();
	    } 
	}
	
	/** Classe inner pour les listeners **/
	
	/* Class ActionMouseListener */
	/* fonction : MouseListener */
	class ActionMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

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
			
			new Thread() {
				public void run() {
					
					if (contains(e.getX(),e.getY())) {
						/* Position du curseur de la souris */
						plateforme.get_echelle().setX_translation((int)(e.getLocationOnScreen().getX()));
						plateforme.get_echelle().setY_translation((int)(e.getLocationOnScreen().getY()));
					}
					
					SwingUtilities.invokeLater(
							new Runnable() {
								public void run() {
									repaint();
								}
							}
					);
				}
			}.start();
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
			
			new Thread() {
				public void run() {
					
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
					
					SwingUtilities.invokeLater(
							new Runnable() {
								public void run() {
									repaint();
								}
							}
					);
				}
			}.start();
		} 
	}
}
