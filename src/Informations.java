/* Projet JAVA         */
/* Vue                 */
/* author :            */

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;

public class Informations extends JPanel{

	/** Serialization **/
	private static final long serialVersionUID = 1L;

	/** Declaration des variables privees **/
	static final int WIDTH = 30;
	static final int HEIGHT = 30;
	private Aeroport aeroport;
	private Echelle echelle;
	private JLabel depart, arrivee, infos, vol;
	private JButton zoom;
	private JTabbedPane onglets;
	private JPanel tab1,tab2;
	private JList liste_depart, liste_arrivee;
	private ArrayList<Vol> liste_vols; 
    private DefaultListModel listModel;            
	
	/** Constructeur de la classe Informations **/
	public Informations(Aeroport aeroport, Echelle echelle) {
		
		this.aeroport = aeroport;
		this.echelle = echelle;
		
		this.setMinimumSize(new Dimension(400,900));
		this.setPreferredSize(new Dimension(400,900));
		//this.setLocation(450, 500);
		
		/** Creation des Panels **/
		tab1 = new JPanel();
		tab2 = new JPanel();

		/** Création des Onglets **/
		
		onglets = new JTabbedPane(JTabbedPane.TOP);
		//onglets.setTabPlacement(JTabbedPane.LEFT);
		ImageIcon icon = new ImageIcon("avion.png");
		
//		Image img = icon.getImage();
//		BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
//		Graphics g = bi.createGraphics();
//		g.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
//		ImageIcon avion = new ImageIcon(bi);

        /** Onglet 1 **/
		tab1.setLayout(new GridLayout(4, 1));
		tab1.setMinimumSize(new Dimension(400,800));
		tab1.setPreferredSize(new Dimension(400,800));
		
        tab2.setLayout(new GridLayout(1, 1));
        
        /* Générations des JLabel*/
        depart = new JLabel("Vols Departs  :");
        arrivee = new JLabel("Vols Arrivees  :");   
        infos = new JLabel("Informations sur le vol : ");
        vol = new JLabel();
        
        tab2.add(arrivee);
        
        //Création du model 
        listModel = new DefaultListModel<Vol>();
         
        //Remplir le model
        remplir_liste();
       
        tab1.add(depart);
        tab1.add(liste_depart);
        tab1.add(infos);
        tab1.add(vol);
     
        /** Onglet 2 **/
	
		/** Ajouts des onglets au Panel Informations**/
        onglets.addTab("Info Vols", tab1 ); //
        onglets.addTab("Outils", tab2);
        this.add(onglets);
	}
	
	public void remplir_liste() {
       
	    liste_depart = new JList(listModel);
	        
		liste_vols = aeroport.get_trafic().get_liste_vols();
        for(Vol v : liste_vols)
         {
         listModel.addElement(v);
         System.out.println(v);
         }
        liste_depart.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        liste_depart.setVisibleRowCount(2);
        liste_depart.addMouseListener(new ActionInformation());
	}
	
	class ActionInformation implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			int index = liste_depart.getSelectedIndex();

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
}