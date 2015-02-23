/* Projet JAVA         */
/* Vue                 */
/* author :            */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


//import sun.reflect.annotation.TypeAnnotation.LocationInfo.Location;

public class Informations extends JPanel{

	/** Serialization **/
	private static final long serialVersionUID = 1L;

	/** Declaration des variables privees **/
	private Plateforme plateforme;
	static final int WIDTH = 30;
	static final int HEIGHT = 30;
	private JLabel titre_vol, titre_temps, titre_zoom, infos, vol, vide, sec, valeur_zoom, 
	valeur_pas, type_vol, categorie_vol, id_vol, qfu_vol, ptdep_vol, heuredep_vol, heurelimite_vol, vol_type;
	private JButton ouvrir, fermer, zoom_avant, zoom_arriere, play_pause;
	private JTextField secondes;
	private JTabbedPane onglets;
	private JPanel tab1, tab2, liste_avion, info_vol, outil_temps, outil_zoom, ouvrir_timer, echelle_temps,
					gestion_secondes, titre_tps, titre_z, boutons_zoom, affiche_zoom, echelle_zoom, panel_vide1,panel_vide2;
	private JList<DefaultListModel<Vol>> liste_vol_tempsreel;
	private ArrayList<Vol> liste_vols; 
    private DefaultListModel<Vol> listModel;   
    private JSlider slider;

	/** Constructeur de la classe Informations **/
	public Informations(Plateforme plateforme) {
		
		/* Plateforme */
		this.plateforme = plateforme;
		
		/** Creation des Panels **/
		tab1 = new JPanel();
		liste_avion = new JPanel();
		info_vol = new JPanel();
		
		tab2 = new JPanel();
		outil_temps = new JPanel();
		//outil_temps.setBorder(BorderFactory.createLineBorder(Color.black));
		outil_zoom = new JPanel();
		//outil_zoom.setBorder(BorderFactory.createLineBorder(Color.black));
		
		titre_tps = new JPanel();
		ouvrir_timer = new JPanel();
		echelle_temps = new JPanel();
		gestion_secondes = new JPanel();
		
		titre_z = new JPanel();
		boutons_zoom =  new JPanel();
		echelle_zoom = new JPanel();
		affiche_zoom = new JPanel();
		panel_vide2 = new JPanel();
		panel_vide1 = new JPanel();
		
		liste_avion.setBackground(Color.LIGHT_GRAY);
		info_vol.setBackground(Color.LIGHT_GRAY);
		
		titre_tps.setBackground(Color.LIGHT_GRAY);
		ouvrir_timer.setBackground(Color.LIGHT_GRAY);
		echelle_temps.setBackground(Color.LIGHT_GRAY);
		gestion_secondes.setBackground(Color.LIGHT_GRAY);
		panel_vide1.setBackground(Color.LIGHT_GRAY);
		
		titre_z.setBackground(Color.LIGHT_GRAY);
		boutons_zoom.setBackground(Color.LIGHT_GRAY);
		echelle_zoom.setBackground(Color.LIGHT_GRAY);
		affiche_zoom.setBackground(Color.LIGHT_GRAY);
		panel_vide2.setBackground(Color.LIGHT_GRAY);
		
		
		/** Creation des Onglets */
		
		onglets = new JTabbedPane(JTabbedPane.TOP);
		
		
        /** Initialisation du container. */
		tab1.setLayout(new GridLayout(2,1));
		liste_avion.setLayout(new GridLayout(3,1));
		info_vol.setLayout(new GridLayout(7,2));
		
		outil_temps.setLayout(new GridLayout(5, 1));
		titre_tps.setLayout(new GridLayout (1 ,1));
		ouvrir_timer.setLayout(new GridLayout (3,2,20,0));
		echelle_temps.setLayout(new GridLayout (3,1));
		gestion_secondes.setLayout(new GridLayout (3,3,7,0));
		panel_vide1.setLayout(new GridLayout());
		
		outil_zoom.setLayout(new GridLayout(5, 1));
		titre_z.setLayout(new GridLayout (1 ,1));
		boutons_zoom.setLayout(new GridLayout (3,2,20,0));
		echelle_zoom.setLayout(new GridLayout (3,1));
		affiche_zoom.setLayout(new GridLayout ());
		panel_vide1.setLayout(new GridLayout());
		
		
		/** Dimensionnement des panels */
		titre_tps.setPreferredSize(new Dimension(300,100));
		ouvrir_timer.setPreferredSize(new Dimension(300,100));
		echelle_temps.setPreferredSize(new Dimension(300,100));
		gestion_secondes.setPreferredSize(new Dimension(300,100));
		panel_vide1.setPreferredSize(new Dimension(300,100));
		
		
        /** Creation et initialisation d'une serie de composants. 
         *  Generation des JLabel */
		
        titre_vol = new JLabel("Liste des vols en temps reel  :");
        titre_vol.setForeground(Color.BLACK);
        Font p = new Font("Arial",Font.BOLD,15);
        titre_vol.setFont(p);
        
        titre_temps = new JLabel("    Application gestion du temps");
        titre_temps.setForeground(Color.BLACK);
        Font police = new Font("Arial",Font.BOLD,15);
        titre_temps.setFont(police);
        
        /** border titre simple */
        titre_temps.setBorder(BorderFactory.createRaisedBevelBorder());
         
        /** tentative border speciale de JPanel titre*/
        Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        TitledBorder title = BorderFactory.createTitledBorder(loweredetched);
        title.setTitleJustification(TitledBorder.RIGHT);
		titre_temps.setBorder(title);

        titre_zoom = new JLabel("Application de gestion du zoom");
        titre_zoom.setAlignmentX(CENTER_ALIGNMENT);
        titre_zoom.setForeground(Color.BLACK);
        Font police1 = new Font("Arial",Font.BOLD,15);
        titre_zoom.setFont(police1);
        titre_zoom.setBorder(BorderFactory.createLineBorder(Color.black));
        
        valeur_zoom = new JLabel("Valeur du zoom :" /*+ .getZoom() */);
        valeur_pas = new JLabel("Pas du timer :" /*+ .getPas() */);
        infos = new JLabel("Informations sur le vol : " /* + indice du vol */);
        infos = new JLabel("Informations sur le vol : ");
        sec = new JLabel("secondes");
        vol = new JLabel("            ");
        secondes = new JTextField("            ");
        
        type_vol = new JLabel ("Type du vol :");
        vol_type = new JLabel ("verge");
        vol_type.setForeground(Color.BLUE);
        Font p1 = new Font("Arial",Font.BOLD,11);
        vol_type.setFont(p1);
        id_vol = new JLabel ("Identifiant du vol :");
        qfu_vol = new JLabel ("Qfu de la piste :");
        categorie_vol = new JLabel ("Categorie du vol :");
        ptdep_vol = new JLabel ("Point de depart du vol :");
        heuredep_vol = new JLabel ("Heure de depart :");
        heurelimite_vol = new JLabel ("Heure limite du vol :");
        
        
        /** Generation des JButton */
        zoom_arriere = new JButton("-");      
        zoom_avant = new JButton("+");
        ouvrir = new JButton("OUVRIR");
        fermer = new JButton("FERMER");
        play_pause = new JButton("play");
        play_pause.setPreferredSize(new Dimension(25,25));
        
        play_pause.addActionListener(new ActionPlayPause());
        zoom_arriere.addActionListener(new ActionUnzoom());
        zoom_avant.addActionListener(new ActionZoom());
        ouvrir.addActionListener(new ActionOpen());
        fermer.addActionListener(new ActionClose());
        
        /** Creation du model */ 
        listModel = new DefaultListModel<Vol>();
	
	
        /** Generation des JLabel */

        
        /** Generation du JSlider */
		JSlider slider1 = new JSlider(0, 86400,0);
		slider1.setMajorTickSpacing(3600);
		slider1.setMinorTickSpacing(60);
		
		JSlider slider2 = new JSlider(0, 86400,0);
		slider2.setMajorTickSpacing(3600);
		slider2.setMinorTickSpacing(60);
        


        
        /** Creation du model */ 
        listModel = new DefaultListModel<Vol>();
         
        /** Remplir le model */
        remplir_liste();

        
        /** Onglet 1 */
		tab1.setMinimumSize(new Dimension(300,800));
		tab1.setPreferredSize(new Dimension(300,800));
		tab1.setBackground(Color.LIGHT_GRAY);
	    
        tab1.add(liste_avion);
        tab1.add(info_vol);
        //tab1.add(infos);
        //tab1.add(vol);
           
        /** Onglet 2 */
		tab2.setMinimumSize(new Dimension(300, 450));
		tab2.setPreferredSize(new Dimension(300, 900));
		tab2.setBackground(Color.LIGHT_GRAY);
		tab2.setLayout(new GridLayout(2,1));
		tab2.setBorder(BorderFactory.createLineBorder(Color.black));
		tab1.setBorder(BorderFactory.createLineBorder(Color.black));
		
		outil_temps.setPreferredSize(new Dimension(300,450));
		outil_temps.setBackground(Color.LIGHT_GRAY);


      
        
        /* 2- Creation et initialisation d'une serie de composants. */
        JLabel[] vide = new JLabel[40];
        for (int i = 0; i < 40; i++) {
            vide[i] = new JLabel("");
        }
        
        /** Application de gestion des vols presents en temps reel sur 
         * la plateforme aeroportuaire
         */
        
        /** Liste des avions */
        
        liste_avion.add(titre_vol);
        liste_avion.add(liste_vol_tempsreel);
        liste_avion.add(vide[13]);
        
        /** Affichage des informations des vols */
        
        info_vol.add(type_vol);
        info_vol.add(vol_type);
        info_vol.add(id_vol);
        info_vol.add(vide[31]);
        info_vol.add(categorie_vol);
        info_vol.add(vide[32]);
        info_vol.add(qfu_vol);
        info_vol.add(vide[33]);
        info_vol.add(ptdep_vol);
        info_vol.add(vide[34]);
        info_vol.add(heuredep_vol);
        info_vol.add(vide[35]);
        info_vol.add(heurelimite_vol);
        info_vol.add(vide[36]);
        
 /*************************************************************************************/       
        /** Application de gestion du timer de la plateforme aeroportuaire */
        /** Ligne 1 */ 
        titre_tps.add(titre_temps);
        
        /** Ligne 2 */
        ouvrir_timer.add(vide[1]);
        ouvrir_timer.add(vide[2]);
        ouvrir_timer.add(ouvrir);
        ouvrir_timer.add(fermer);
        ouvrir_timer.add(vide[3]);
        ouvrir_timer.add(vide[4]);
        
        /** Ligne 3 */
        echelle_temps.add(vide[5]);
        echelle_temps.add(slider1);
        echelle_temps.add(vide[6]);

        
        /** Ligne 4 */
        gestion_secondes.add(secondes);
        gestion_secondes.add(sec);
        gestion_secondes.add(play_pause);
        gestion_secondes.add(vide[7]);
        gestion_secondes.add(vide[8]);
        gestion_secondes.add(vide[9]);
        gestion_secondes.add(vide[10]);
        gestion_secondes.add(vide[11]);
        gestion_secondes.add(vide[12]);
        
        /** ligne 5 */ 
        panel_vide1.add(valeur_pas);
        
/*************************************************************************************/		
		
		/** Application de gestion du zoom de la plateforme aeroportuaire*/
		 /** Ligne 1 */ 
        titre_z.add(titre_zoom);
        
        /** Ligne 2 */
        boutons_zoom.add(vide[14]);
        boutons_zoom.add(vide[15]);
        boutons_zoom.add(zoom_avant);
        boutons_zoom.add(zoom_arriere);
        boutons_zoom.add(vide[16]);
        boutons_zoom.add(vide[17]);
        
        /** Ligne 3 */
        echelle_zoom.add(vide[18]);
        echelle_zoom.add(slider2);
        echelle_zoom.add(vide[19]);

        
        /** Ligne 4 */

        affiche_zoom.add(vide[27]);
       /* affiche_zoom.add(vide[28]);
        affiche_zoom.add(vide[29]);
        affiche_zoom.add(vide[20]);
        affiche_zoom.add(vide[21]);
        affiche_zoom.add(vide[22]);
        affiche_zoom.add(vide[23]);
        affiche_zoom.add(vide[24]);
        affiche_zoom.add(vide[25]);*/
        
        /** ligne 5 */ 
        panel_vide2.add(valeur_zoom);
        
        /** Remplissage du Panel outil_temps */
        outil_temps.add(titre_tps);
        outil_temps.add(ouvrir_timer);
        outil_temps.add(echelle_temps);
        outil_temps.add(gestion_secondes);
        outil_temps.add(panel_vide1);
		tab2.add(outil_temps);
		
		
		outil_zoom.add(titre_z);
		outil_zoom.add(boutons_zoom);
		outil_zoom.add(echelle_zoom);
		outil_zoom.add(affiche_zoom);
		outil_zoom.add(panel_vide2);
		tab2.add(outil_zoom);
		
		
		remplir_liste();
		/** Ajouts des onglets au Panel Informations**/
        onglets.addTab("Info Vols", tab1 ); //
        onglets.addTab("Outils", tab2);
        
        this.add(onglets);
	}
	
	/** Getter de liste_vol_tempsreel **/
	public DefaultListModel<Vol> get_listeModel() {
		return listModel;
	}
	
	@SuppressWarnings("unchecked")
	public void remplir_liste(){
       
	    liste_vol_tempsreel = new JList(listModel);
	        
		liste_vols = plateforme.get_aeroport().get_trafic().get_liste_vols();
        for(Vol v : liste_vols)
         {
         listModel.addElement(v);
         System.out.println(v);
         System.out.println("zizi");
         }
        liste_vol_tempsreel.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        liste_vol_tempsreel.setVisibleRowCount(2);
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
	
	
	class ActionPlayPause implements ActionListener {
		public void actionPerformed(ActionEvent e) {
	
		}
	}	
}