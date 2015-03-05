/** Projet JAVA         */
/** Vue                 */
/** @author : PB        */

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
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
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

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
	
	private JMenu menu_collision; /* JMenu (Collision) */
	private JMenuItem detection_collision; /* JMenuItem (Detection collision) */
	private JMenuItem rapport_collision; /* JMenuItem (Rapport collision) */
	
	private JMenu aide; /* JMenu (Aide) */
	private JMenuItem ouvrir_aide; /* JMenuItem (Ouverture de l'aide) */
	
	private InfiniteProgressPanel pAttente; /* Panel de chargement */
	
	private int oldX; /* Variables de recuperation de la position du curseur sur la fenetre */
	private int oldY;
	private int newX;
	private int newY;
	
	private boolean bAeroport = false;
	private boolean bTrafic = false;

	/* utilisation du pattern Singleton */
	
	/** Constructeur de la classe Fenetre **/
	public Fenetre(Plateforme plateforme) {
		
		/* Plateforme */
		this.plateforme = plateforme;
		
		/* Icone de la fenetre */
		Image icone = Toolkit.getDefaultToolkit().getImage("images/icone.png");
		this.setIconImage(icone);
		
		/* Titre de la fenetre */
		this.setTitle("Aeroport <sans nom>");
		
		/* Taille de la fenetre */
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int hauteur = (int)tailleEcran.getHeight();
		int largeur = (int)tailleEcran.getWidth();
		this.setPreferredSize(new Dimension(hauteur, largeur));
		
		/* FileChooser */
	    repertoire_courant = null;
		filechooser = new JFileChooser(repertoire_courant);
		
		/* MenuBar */
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		/* Menus primaires */
		fichier = new JMenu("Fichier");
		menu_timer = new JMenu("Timer");
		menu_collision = new JMenu("Collision");
		aide = new JMenu("Aide");
		menuBar.add(fichier);
		menuBar.add(menu_timer);
		menuBar.add(menu_collision);
	    menuBar.add(aide);
	    
	    /* Menus secondaires */
	    charger_fichier = new JMenu("Charger un fichier");
	    fichier.add(charger_fichier);
	    
	    /* MenuItems charger_fichier */
	    charger_aeroport = new JMenuItem("Aeroport");
	    charger_aeroport.setAccelerator(KeyStroke.getKeyStroke('a'));
		charger_trafic = new JMenuItem("Trafic");
		charger_trafic.setAccelerator(KeyStroke.getKeyStroke('b'));
	    charger_fichier.add(charger_aeroport);
	    charger_fichier.add(charger_trafic);
	    
	    /* MenuItems timer */
	    ouvrir_timer = new JMenuItem("Ouvrir");
	    ouvrir_timer.setAccelerator(KeyStroke.getKeyStroke('t'));
		fermer_timer = new JMenuItem("Fermer");
		fermer_timer.setAccelerator(KeyStroke.getKeyStroke('f'));
		menu_timer.add(ouvrir_timer);
		menu_timer.add(fermer_timer);
		
		/* MenuItems collision */
	    detection_collision = new JMenuItem("Detection");
	    detection_collision.setAccelerator(KeyStroke.getKeyStroke('c'));
	    rapport_collision = new JMenuItem("Rapport");
	    rapport_collision.setAccelerator(KeyStroke.getKeyStroke('r'));
		menu_collision.add(detection_collision);
		menu_collision.add(rapport_collision);
	    
		/* MenuItems aide */
	    ouvrir_aide = new JMenuItem("Ouvrir");
	    ouvrir_aide.setAccelerator(KeyStroke.getKeyStroke('o'));
	    aide.add(ouvrir_aide);
	    
	    
	    /* Conteneur */
	    conteneur = new Container();
	    conteneur = this.getContentPane();
	    //conteneur.setLayout(new GridBagLayout());
	    conteneur.setLayout(new BorderLayout());
	    
	    /* GridBagConstraints */
	    //GridBagConstraints gbc = new GridBagConstraints();
	    
	    //x correspond au colonne
	    //y correspond au ligne
	    
	    /* Positionnement de la case initiale */
	    //gbc.gridx = 0;
	    //gbc.gridy = 0;
	    
	    /* Contrainte de la case */
	    //gbc.gridheight = 1;
	    //gbc.gridwidth = 1;
	    //gbc.fill = GridBagConstraints.BOTH;
	    //gbc.weightx = 90;
	    
	    /* LayeredPane layer */
	    layer = new JLayeredPane();
	    layer.setOpaque(false);
	    layer.setPreferredSize(new Dimension(hauteur, largeur));
	    //conteneur.add(layer, gbc);
	    conteneur.add(layer, BorderLayout.CENTER);
	    
	    /* Panel pAttente */
	    pAttente = new InfiniteProgressPanel();
	    pAttente.setBounds(0, 0, largeur, hauteur);
	    layer.add(pAttente, 1);
	    
	    /* Panel pSimulateurAeroport */
	    pSimulateurAeroport = new SimulateurAeroport(plateforme);
	    pSimulateurAeroport.setSize(layer.getSize());
	    pSimulateurAeroport.setBounds(0, 0, largeur, hauteur);
	    layer.add(pSimulateurAeroport, 1);
	    
	    /* Panel pSimulateurVol */
	    pSimulateurVol = new SimulateurVol(plateforme);
	    pSimulateurVol.setSize(layer.getSize());
	    pSimulateurVol.setBounds(0, 0, largeur, hauteur);
	    layer.add(pSimulateurVol, 0);
	    
	    /* Positionnement de la case initiale */
	    //gbc.gridx = 1;
	    //gbc.gridy = 0;
	    
	    /* Contrainte de la case */
	    //gbc.gridheight = 1;
	    //gbc.gridwidth = 1;
	    //gbc.fill = GridBagConstraints.BOTH;
	    //gbc.weightx = 10;
	    
	    /* Panel pInformations */
	    pInformations = new Informations(plateforme);
	    //conteneur.add(pInformations, gbc);
	    conteneur.add(pInformations, BorderLayout.EAST);
	    pInformations = new Informations(plateforme);
	    //pInformations.setPreferredSize(new Dimension(hauteur, largeur));
	    //conteneur.add(pInformations, gbc);
	    conteneur.add(pInformations, BorderLayout.EAST);
	    
	    /* Listeners */
	    /* JMenuItem */
	    charger_aeroport.addActionListener(new ActionChargerAeroport());
	    charger_trafic.addActionListener(new ActionChargerTrafic());
	    ouvrir_timer.addActionListener(new ActionOuvrirTimer());
	    fermer_timer.addActionListener(new ActionFermerTimer());
	    detection_collision.addActionListener(new ActionDetectionCollision());
	    rapport_collision.addActionListener(new ActionRapportCollision());
	    ouvrir_aide.addActionListener(new ActionAide());
	    
	    /* MouseListener */
		this.addMouseListener(new ActionMouseListener());
		this.addMouseMotionListener(new ActionMouseMotion());
		this.addMouseWheelListener(new ActionMouseWheel());
	    
	    /* Affichage de la fenetre */
	    this.pack();
	    this.setVisible(true);
	    
	    /* Fermeture */
	    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	/** Getter de pSimulateurAeroport **/
	public SimulateurAeroport get_simulateur_aeroport() {
		return pSimulateurAeroport;
	}
	
	/** Getter de pSimulateurAeroport **/
	public SimulateurVol get_simulateur_vol() {
		return pSimulateurVol;
	}
	
	/** Getter de pInformations **/
	public Informations get_informations() {
		return pInformations;
	}
	
	/** Classe inner pour les listeners **/
	
	/* Class ActionRapportCollision */
	/* fonction : ouvrir le rapport de collision */
	class ActionRapportCollision implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Desktop desk = Desktop.getDesktop();
			try {
				desk.open(new File("collision.txt"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	/* Class ActionAide */
	/* fonction : ouvrir un fichier texte avec l'aide d'utilisation */
	class ActionAide implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Desktop desk = Desktop.getDesktop();
			try {
				desk.open(new File("aide.txt"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
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
	
	/* Class ActionDetectionCollision */
	/* fonction : detecter les collisions de la plateforme aeroportuaire */
	class ActionDetectionCollision implements ActionListener {
		
	    public void actionPerformed(ActionEvent ae) {
	    	new Thread() {
				public void run() {
					plateforme.get_aeroport().get_trafic().get_collision().detection_collision(); //Analyse des collisions
				}
			}.start();
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
						System.out.println("Erreur au niveau de la recuperation du nom du fichier");
						e.printStackTrace();
					}
					/* Creation d'un filtre pour le choix du fichier (.txt) */
					filechooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
						public boolean accept(File f) {
							return f.getName().toLowerCase().endsWith(".txt") || f.isDirectory();
						}
						public String getDescription() {
							return "Fichier texte";
						}
					});
					
					/* TEST : Affichage du repertoire courant */
				    //System.out.println("Repertoire courant : " + repertoire_courant);
				    filechooser.showOpenDialog(null);
				    
				    /* TEST : Affichage du fichier selectionnee */
				    //System.out.println("Fichier choisi : " + filechooser.getSelectedFile());
				    plateforme.get_aeroport().set_nom_fichier_aeroport(filechooser.getSelectedFile().toString());
				    
			    	/* Lancement du panel pAttente */
			    	pAttente.start();
			    	
			    	/* Chargement du fichier trafic */
			    	plateforme.get_aeroport().charger_fichier_aeroport(plateforme.get_aeroport());
			    	
			    	/* TEST */
			    	//plateforme.get_aeroport().test_programme_aeroport(plateforme.get_aeroport());
			    	//plateforme.get_aeroport().test_valeur_coordonnees_aeroport();
					
			    	/* Mise a jour de l'echelle */
			    	plateforme.get_echelle().calculer_translation();
			    	
			    	/* Mise a jour de la translation de SimulateurAeroport */
			    	plateforme.get_fenetre().get_simulateur_aeroport().setNewTranslationX((int) plateforme.get_echelle().getX_translation());
			    	plateforme.get_fenetre().get_simulateur_aeroport().setNewTranslationY((int) plateforme.get_echelle().getY_translation()*2);
			    	
			    	/* Gestion des exceptions */
			    	bAeroport = true;
			    	
			    	/* Mise a jour graphique */
					SwingUtilities.invokeLater(
							new Runnable() {
								public void run() {
									
							    	/* Mise a jour du simulateur */
									plateforme.get_fenetre().get_simulateur_aeroport().repaint();
								}	
							}
					);
					
					/* Arret du panel pAttente */
					pAttente.stop();
					
					/* Changement du nom de la fenetre */
					plateforme.get_fenetre().setTitle("Aeroport - " + plateforme.get_aeroport().get_nom_aeroport());
				}
			}.start();
	    }
	}
	
	/* Class ActionChargerTrafic */
	/* fonction : recuperation et chargement du fichier aeroport */
	class ActionChargerTrafic implements ActionListener {
		
	    public void actionPerformed(ActionEvent ae) {
	    	
	    	if(bAeroport == true) {
		    	new Thread(){
		    		public void run(){
		    			try {
		    				repertoire_courant = new File(".").getCanonicalFile();
		    			} catch (IOException e) {
		    				// TODO Auto-generated catch block
		    				System.out.println("Erreur au niveau de la recuperation du nom du fichier");
		    				e.printStackTrace();
		    			}
		    			/* Creation d'un filtre pour le choix du fichier (.txt) */
						filechooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
							public boolean accept(File f) {
								return f.getName().toLowerCase().endsWith(".txt") || f.isDirectory();
							}
							public String getDescription() {
								return "Fichier texte";
							}
						});
						
						/* TEST : Affichage du repertoire courant */
		    	    	System.out.println("Repertoire courant : " + repertoire_courant);
		    	    	filechooser.showOpenDialog(null);
		    	    	
		    	    	/* TEST : Affichage du fichier selectionnee */
		    	    	System.out.println("Fichier choisi : " + filechooser.getSelectedFile());
		    	    	plateforme.get_aeroport().get_trafic().set_nom_fichier_trafic(filechooser.getSelectedFile().toString());
		    	    	
		    	    	/* TEST : Calcul du nombre de vols */
		    	    	//plateforme.get_aeroport().get_trafic().calcul_nombre_vols();
		    	    	
		    	    	/* Chargement du fichier trafic */
		    	    	plateforme.get_aeroport().get_trafic().charger_fichier_trafic(plateforme.get_aeroport().get_trafic());
		    	    	
		    	    	/* TEST : Recuperation du fichier texte aeroport */
		    	    	//plateforme.get_aeroport().get_trafic().test_programme_trafic(plateforme.get_aeroport().get_trafic());
		    	    	
		    	    	/* Gestion des exceptions */
		    	    	bTrafic = true;
		    		}
		    	}.start();
	    	}
	    	else {
	    		JOptionPane.showMessageDialog(plateforme.get_fenetre(), "Veuillez charger la plateforme aeroportuaire", "Erreur", JOptionPane.ERROR_MESSAGE);
	    	}
	    }
	}
	
	/** Getter de bTrafic
	 * 
	 * @return la valeur de bTrafic.
	 */
	public boolean get_bTrafic() {
		return bTrafic;
	}
	
	/** Classe inner pour les listeners **/
	
	/* Class ActionMouseListener */
	/* fonction : MouseListener */
	class ActionMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			
			/* Declaration des variables locales */
			ArrayList<Vol> vols_plateforme = new ArrayList<Vol>();
			Vol v;
			Point p;
			int x;
			int y;
			int x_c;
			int y_c;
			int index;
			
			/* Recuperation du temps */
			int temps = plateforme.get_time().getTemps();
			
			/* Timer en mode STOP */
			if(plateforme.get_time().getTimer().isRunning() != true) {
				
				/* Recuperation valeur du curseur */
				x_c = (int) e.getX();
				y_c = (int) e.getY();
				
				x_c = (int) (x_c - plateforme.get_fenetre().get_simulateur_aeroport().getNewTranslationX());
				y_c = (int) (y_c -  plateforme.get_fenetre().get_simulateur_aeroport().getNewTranslationY());
				
				System.out.println(x_c);
				System.out.println(y_c);
				
				/* Analyse */
				/* Parcours de la liste des vols */
				for(int i = 0 ; i < plateforme.get_aeroport().get_trafic().get_liste_vols().size() ; i++) {
					
					/* Recuperation des vols affichables */
					v = plateforme.get_aeroport().get_trafic().get_liste_vols().get(i);
					if((temps >= v.getTemps_depart_vol()) && (temps < v.getTemps_fin_vol())) {
						vols_plateforme.add(v);
					}
				}
				
				/* Test du MouseCLicked */
				for(Vol vol : vols_plateforme) {
					index = (temps - vol.getTemps_depart_vol())/(plateforme.get_time().getPas()); //Calcul de l'index
					p = vol.getTrajectoire_vol().get(index); //Recuperation du point de la trajectoire du vol
					x = p.get_coordonnees_point().getX(); //Recuperation de x
					y = plateforme.get_echelle().inverser(p.get_coordonnees_point().getY()); //Recuperation et inversion de y
					//x = x + plateforme.get_fenetre().get_simulateur_aeroport().getNewTranslationX();
					//y = y + plateforme.get_fenetre().get_simulateur_aeroport().getNewTranslationY();
					//x = (int) (x*plateforme.get_echelle().get_zoom());
					//y = (int) (y*plateforme.get_echelle().get_zoom());
					x = (int) (x - plateforme.get_fenetre().get_simulateur_aeroport().getNewTranslationX());
					y = (int) (y - plateforme.get_fenetre().get_simulateur_aeroport().getNewTranslationY());
					
					x = (int) (x*plateforme.get_echelle().get_zoom());
					y = (int) (y*plateforme.get_echelle().get_zoom());
					
					System.out.println(x);
					System.out.println(y);
					/* Creation d'une zone de verification */
					if((x > x_c - 10) && (x < x_c + 10)  && (y > y_c - 10)  && (y < y_c + 10)) {
						System.out.println(vol.getIdentifiant_vol());
					}
				}
				
				/* Analyse */
				/* Si un VOL dans la Zone -> Affichage */
				/* Changement d'etat de l'image du VOL (VERT) */
			}
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
			oldX = (int) e.getLocationOnScreen().getX();
			oldY = (int) e.getLocationOnScreen().getY();
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
						newX = (int) e.getLocationOnScreen().getX();
						newY = (int) e.getLocationOnScreen().getY();
						/* Calcul de la nouvelle translation */
						plateforme.get_fenetre().get_simulateur_aeroport().setNewTranslationX(plateforme.get_fenetre().get_simulateur_aeroport().getNewTranslationX() + (newX - oldX));
						plateforme.get_fenetre().get_simulateur_aeroport().setNewTranslationY(plateforme.get_fenetre().get_simulateur_aeroport().getNewTranslationY() + (newY - oldY));
						/* Mise a jour des anciennes valeurs de X et Y */
						oldX = newX;
						oldY = newY;
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
