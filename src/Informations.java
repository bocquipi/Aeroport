/* Projet JAVA         */
/* Vue                 */
/* author :            */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Informations extends JPanel implements Observer, ChangeListener {

	/** Serialization **/
	private static final long serialVersionUID = 1L;

	/** Declaration des variables privees **/
	private Plateforme plateforme;
	
	/* JLabel */
	private JLabel titre_vol, titre_temps, titre_zoom, titre_infos, sec, valeur_zoom, valeur_pas, 
						type_vol, categorie_vol, id_vol, qfu_vol, ptdep_vol, heuredep_vol, heurelimite_vol,
							vol_type;
	
	/* JButton */
	private JButton ouvrir, fermer, zoom_avant, zoom_arriere, play, pause;
	
	/* JTextField */
	private JTextField secondes;
	
	/* Table d'onglets */
	private JTabbedPane onglets;
	
	/* JPanel */
	private JPanel tab1, tab2, liste_avion, info_vol, outil_temps, outil_zoom, ouvrir_timer, 
					echelle_temps, gestion_secondes, titre_tps, titre_z, boutons_zoom, affiche_zoom, 
						echelle_zoom, panel_vide1,panel_vide2;
	
	/* JList */
	private JList<DefaultListModel<Vol>> liste_vol_tempsreel = new JList<DefaultListModel<Vol>>();
    private DefaultListModel<Vol> listModel;   
    
    /* Slider */
    private JSlider slider;
    
    /* Image (Boutons) */
    private ImageIcon iPlay = new ImageIcon("play.png");
    private ImageIcon iPause = new ImageIcon("pause.png");
	private ImageIcon iMoins = new ImageIcon("moins.png");
	private ImageIcon iPlus = new ImageIcon("plus.png");

	/** Constructeur de la classe Informations **/
	public Informations(Plateforme plateforme) {
		
		/* Plateforme */
		this.plateforme = plateforme;
		
		/* Creation du support d'onglets */
		onglets = new JTabbedPane(JTabbedPane.TOP);
		this.add(onglets);
		
		/** Panels principaux **/
		
		/* Onglet 1 */
		tab1 = new JPanel(); 
		tab1.setLayout(new GridLayout(2,1));
		tab1.setMinimumSize(new Dimension(300,800));
		tab1.setPreferredSize(new Dimension(300,800));
		tab1.setBackground(Color.LIGHT_GRAY);
		tab1.setBorder(BorderFactory.createLineBorder(Color.black));
        onglets.addTab("Vols", tab1 );
        
		/* Onglet 2 */
		tab2 = new JPanel();
		tab2.setLayout(new GridLayout(2,1));
		tab2.setMinimumSize(new Dimension(300, 450));
		tab2.setPreferredSize(new Dimension(300, 900));
		tab2.setBackground(Color.LIGHT_GRAY);
		tab2.setBorder(BorderFactory.createLineBorder(Color.black));
		onglets.addTab("Outils", tab2);
		
		/** Panels secondaires **/
		
		/* Onglet 1 */
		liste_avion = new JPanel();
		liste_avion.setLayout(new GridLayout(2,1));
		liste_avion.setBackground(Color.LIGHT_GRAY);
		tab1.add(liste_avion);
		info_vol = new JPanel();
		info_vol.setLayout(new GridLayout(8,1));
		info_vol.setBackground(Color.LIGHT_GRAY);
        tab1.add(info_vol);
		
        /* Onglet 2 */
        outil_temps = new JPanel();
		outil_temps.setLayout(new GridLayout(5, 1));
		outil_temps.setPreferredSize(new Dimension(300,450));
		outil_temps.setBackground(Color.LIGHT_GRAY);
		tab2.add(outil_temps);
		outil_zoom = new JPanel();
		outil_zoom.setLayout(new GridLayout(5, 1));
		tab2.add(outil_zoom);
        
        /** Panels tertiaires **/
        
		/* Onglet 2 */
		//Panel de gestion du temps
		titre_tps = new JPanel();
		titre_tps.setLayout(new GridLayout(1 ,1));
		titre_tps.setPreferredSize(new Dimension(300,100));
		titre_tps.setBackground(Color.LIGHT_GRAY);
		outil_temps.add(titre_tps);
		ouvrir_timer = new JPanel();
		ouvrir_timer.setLayout(new GridLayout(3,2,20,0));
		ouvrir_timer.setPreferredSize(new Dimension(300,100));
		ouvrir_timer.setBackground(Color.LIGHT_GRAY);
		outil_temps.add(ouvrir_timer);
		echelle_temps = new JPanel();
		echelle_temps.setLayout(new GridLayout(3,1));
		echelle_temps.setPreferredSize(new Dimension(300,100));
		echelle_temps.setBackground(Color.LIGHT_GRAY);
		outil_temps.add(echelle_temps);
		gestion_secondes = new JPanel();
		gestion_secondes.setLayout(new GridLayout(3,3,7,0));
		gestion_secondes.setPreferredSize(new Dimension(300,100));
		gestion_secondes.setBackground(Color.LIGHT_GRAY);
		outil_temps.add(gestion_secondes);
		panel_vide1 = new JPanel();
		panel_vide1.setLayout(new GridLayout());
		panel_vide1.setPreferredSize(new Dimension(300,100));
		panel_vide1.setBackground(Color.LIGHT_GRAY);
        outil_temps.add(panel_vide1);
		
		//Panel de gestion du zoom
		titre_z = new JPanel();
		titre_z.setLayout(new GridLayout(1 ,1));
		titre_z.setBackground(Color.LIGHT_GRAY);
		outil_zoom.add(titre_z);
		boutons_zoom =  new JPanel();
		boutons_zoom.setLayout(new GridLayout(3,2,20,0));
		boutons_zoom.setBackground(Color.LIGHT_GRAY);
		outil_zoom.add(boutons_zoom);
		echelle_zoom = new JPanel();
		echelle_zoom.setLayout(new GridLayout(3,1));
		echelle_zoom.setBackground(Color.LIGHT_GRAY);
		outil_zoom.add(echelle_zoom);
		affiche_zoom = new JPanel();
		affiche_zoom.setLayout(new GridLayout());
		affiche_zoom.setBackground(Color.LIGHT_GRAY);
		outil_zoom.add(affiche_zoom);
		panel_vide2 = new JPanel();
		panel_vide2.setLayout(new GridLayout());
		panel_vide2.setBackground(Color.LIGHT_GRAY);
		outil_zoom.add(panel_vide2);
		
        /** Creation des titres des panels **/
		
		/* Creation d'une bordure */
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder title = BorderFactory.createTitledBorder(loweredetched);
		title.setTitleJustification(TitledBorder.RIGHT);
		title.setTitlePosition(TitledBorder.CENTER);
		
		/* Creation d'une police d'ecriture */
		Font police = new Font("Verdana",Font.BOLD,15);
		
		/* Onglet 1 */
        titre_vol = new JLabel("Liste des vols");
        titre_vol.setForeground(Color.BLACK);
        titre_vol.setFont(police);
        titre_vol.setBorder(title);
        titre_vol.setHorizontalAlignment(SwingConstants.CENTER);
        
        titre_infos = new JLabel("Informations sur un vol");
        titre_infos.setForeground(Color.BLACK);
        titre_infos.setFont(police);
        titre_infos.setBorder(title);
        titre_infos.setHorizontalAlignment(SwingConstants.CENTER);
        
        /* Onglet 2 */
        //Panel de gestion du temps
        titre_temps = new JLabel("Gestion du temps");
        titre_temps.setForeground(Color.BLACK);
        titre_temps.setFont(police);
        titre_temps.setBorder(title);
        titre_temps.setHorizontalAlignment(SwingConstants.CENTER);
        
        //Panel de gestion du zoom
        titre_zoom = new JLabel("Gestion du zoom");
        titre_zoom.setForeground(Color.BLACK);
        titre_zoom.setFont(police);
        titre_zoom.setBorder(title);
        titre_zoom.setHorizontalAlignment(SwingConstants.CENTER);
        
        /** Composants **/

        /* Onglet 1 */
        /* DefaultListModel */
        listModel = new DefaultListModel<Vol>();
	
        /* Onglet 2 */
        
        /* JLabel */
        //Panel de gestion du temps
        valeur_pas = new JLabel("Pas du timer :" ); /*+ .getPas() */
        sec = new JLabel("secondes");
        secondes = new JTextField("   ");
        
        //Panel de gestion des vols
        type_vol = new JLabel ("Type du vol :");
        vol_type = new JLabel ("verge");
        vol_type.setForeground(Color.BLUE);
        vol_type.setFont(police);
        id_vol = new JLabel ("Identifiant du vol :");
        qfu_vol = new JLabel ("Qfu de la piste :");
        categorie_vol = new JLabel ("Categorie du vol :");
        ptdep_vol = new JLabel ("Point de depart du vol :");
        heuredep_vol = new JLabel ("Heure de depart :");
        heurelimite_vol = new JLabel ("Heure limite du vol :");
        
        /* JButton */
        //Panel de gestion du zoom
        valeur_zoom = new JLabel("Valeur du zoom :");
        zoom_arriere = new JButton(iMoins);
        zoom_avant = new JButton(iPlus);
        
        //Panel de gestion du temps
        ouvrir = new JButton("OUVRIR");
        fermer = new JButton("FERMER");
        play = new JButton(iPlay);
        pause = new JButton(iPause);
        
		/* Slider */
		slider = new JSlider(0, 86400,0);
		slider.setMajorTickSpacing(3600);
		slider.setMinorTickSpacing(60);
		slider.addChangeListener(this);
		
		/* echelle du slider */
		Hashtable <Integer, JLabel> echelle_slider = new Hashtable <Integer, JLabel>();
		echelle_slider.put( new Integer(0), new JLabel("0h") );
		echelle_slider.put( new Integer(86400/24*6), new JLabel("6h") );
		echelle_slider.put( new Integer(86400/24*12), new JLabel("12h") );
		echelle_slider.put( new Integer(86400/24*18), new JLabel("18h") );
		echelle_slider.put( new Integer(86400), new JLabel("24h") );
		slider.setLabelTable(echelle_slider);
		slider.setPaintLabels(true);
        
        /** Listeners **/
        play.addActionListener(new ActionPlay());
        pause.addActionListener(new ActionPause());
        zoom_arriere.addActionListener(new ActionUnzoom());
        zoom_avant.addActionListener(new ActionZoom());
        ouvrir.addActionListener(new ActionOpen());
        fermer.addActionListener(new ActionClose());
        
        /** Application de gestion des vols presents en temps reel sur la plateforme aeroportuaire **/
        
        /* Creation de JLabel vide */
        JLabel[] vide = new JLabel[40];
        for (int i = 0; i < 40; i++) {
            vide[i] = new JLabel("");
        }
        
        /* Creation de JPanel pour la separation des informations des vols */
        JPanel[] separation = new JPanel[7];
        for (int i = 0; i < 7; i++) {
            separation[i] = new JPanel();
            separation[i].setLayout(new GridLayout(1,2));
        }
        
        /** Liste des avions */
        
        liste_avion.add(titre_vol);
        liste_avion.add(liste_vol_tempsreel);
        
        /** Affichage des informations des vols */
        
        info_vol.add(titre_infos);
        info_vol.add(separation[0]);
        separation[0].add(type_vol);
        separation[0].add(vol_type);
        info_vol.add(separation[1]);
        separation[1].add(id_vol);
        separation[1].add(vide[31]);
        info_vol.add(separation[2]);
        separation[2].add(categorie_vol);
        separation[2].add(vide[32]);
        info_vol.add(separation[3]);
        separation[3].add(qfu_vol);
        separation[3].add(vide[33]);
        info_vol.add(separation[4]);
        separation[4].add(ptdep_vol);
        separation[4].add(vide[34]);
        info_vol.add(separation[5]);
        separation[5].add(heuredep_vol);
        separation[5].add(vide[35]);
        info_vol.add(separation[6]);
        separation[6].add(heurelimite_vol);
        separation[6].add(vide[36]);
            
        /** Application de gestion du timer de la plateforme aeroportuaire */
        
        /* Ligne 1 */ 
        titre_tps.add(titre_temps);
        
        /* Ligne 2 */
        ouvrir_timer.add(vide[1]);
        ouvrir_timer.add(vide[2]);
        ouvrir_timer.add(ouvrir);
        ouvrir_timer.add(fermer);
        ouvrir_timer.add(vide[3]);
        ouvrir_timer.add(vide[4]);
        
        /* Ligne 3 */
        echelle_temps.add(vide[5]);
        echelle_temps.add(slider);
        echelle_temps.add(vide[6]);

        
        /* Ligne 4 */
        gestion_secondes.add(secondes);
        gestion_secondes.add(sec);
        gestion_secondes.add(play);
        gestion_secondes.add(vide[7]);
        gestion_secondes.add(vide[8]);
        gestion_secondes.add(vide[9]);
        gestion_secondes.add(vide[10]);
        gestion_secondes.add(vide[11]);
        gestion_secondes.add(vide[12]);
        
        /* ligne 5 */ 
        panel_vide1.add(valeur_pas);	
		
		/** Application de gestion du zoom de la plateforme aeroportuaire*/
        
        /* Ligne 1 */ 
        titre_z.add(titre_zoom);
        
        /* Ligne 2 */
        boutons_zoom.add(vide[14]);
        boutons_zoom.add(vide[15]);
        boutons_zoom.add(zoom_avant);
        boutons_zoom.add(zoom_arriere);
        boutons_zoom.add(vide[16]);
        boutons_zoom.add(vide[17]);
        
        /* Ligne 3 */
        echelle_zoom.add(vide[18]);
        echelle_zoom.add(pause);
        echelle_zoom.add(vide[19]);
        
        /* Ligne 4 */
        affiche_zoom.add(vide[27]);
        
        /* Ligne 5 */ 
        panel_vide2.add(valeur_zoom);
	}
	
	/** Getter de liste_vol_tempsreel **/
	public DefaultListModel<Vol> get_listeModel() {
		return listModel;
	}
	
	/** stateChanged **/
	/** fonction : Mise a jour au changement d'etat du slider **/
	public void stateChanged(ChangeEvent e) {
		int valeur= 0;
		if(slider.getValue()%5 >= 2.5)
			valeur = slider.getValue() + (5-(slider.getValue()%5));
		if(slider.getValue()%5 < 2.5)
			valeur =slider.getValue() - (slider.getValue()%5);
		slider.setValue(valeur);
		plateforme.get_time().setTemps(valeur);
	}
	
	/** update **/
	/** fonction : Mise a jour au changement d'etat de l'afficheur **/
	public void update(Observable Time, Object arg) {
		slider.setValue(plateforme.get_time().getTemps());
	}
	
	/** Classe inner pour les listeners **/
	
	/* Class ActionFermerTimer */
	/* fonction : fermer le timer */
	class ActionFermerTimer implements ActionListener {
		
	    public void actionPerformed(ActionEvent ae) {
	    	plateforme.get_afficheur_time().setVisible(false);
	    }  
	}

	/* Class ActionFermerTimer */
	/* fonction : fermer le timer */
	class ActionZoom implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
			new Thread() {
				public void run() {
					
					int index_zoom = plateforme.get_echelle().get_index_zoom();
					double tableau_zoom[] = plateforme.get_echelle().get_tableau_zoom();
					
					/* Zoom + */
					if(index_zoom != 0) {
						index_zoom--;
					}
					
					/* RAZ du zoom */
					plateforme.get_echelle().set_index_zoom(index_zoom);
					plateforme.get_echelle().set_zoom(tableau_zoom[index_zoom]);
					
					SwingUtilities.invokeLater(
							new Runnable() {
								public void run() {
									plateforme.get_fenetre().repaint();
								}
							}
					);
				}
			}.start();
			
		}
	}	
	
	/* Class ActionFermerTimer */
	/* fonction : fermer le timer */
	class ActionUnzoom implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new Thread() {
				public void run() {
					
					int index_zoom = plateforme.get_echelle().get_index_zoom();
					double tableau_zoom[] = plateforme.get_echelle().get_tableau_zoom();
					
					/* Zoom + */
					if(index_zoom != (tableau_zoom.length-1) ) {
						index_zoom++;
					}
					
					/* RAZ du zoom */
					plateforme.get_echelle().set_index_zoom(index_zoom);
					plateforme.get_echelle().set_zoom(tableau_zoom[index_zoom]);
					
					SwingUtilities.invokeLater(
							new Runnable() {
								public void run() {
									plateforme.get_fenetre().repaint();
								}
							}
					);
				}
			}.start();
		}
	}		
	
	/* Class ActionOpen */
	/* fonction : ouvrir le timer */
	class ActionOpen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
	    	plateforme.get_afficheur_time().setVisible(true);
	    }  
	}	
	
	/* Class ActionClose */
	/* fonction : fermer le timer */
	class ActionClose implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			plateforme.get_afficheur_time().setVisible(false);
		}
	}	
	
	/* Class ActionPlay */
	/* fonction : Activer le timer */
	class ActionPlay implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}	
	
	/* Class ActionPause */
	/* fonction : Mettre le timer en mode pause */
	class ActionPause implements ActionListener {
		public void actionPerformed(ActionEvent e) {
	
		}
	}
}