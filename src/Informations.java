/* Projet JAVA         */
/* Vue                 */
/* author :            */

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;

public class Informations extends JPanel{

	/** Serialization **/
	private static final long serialVersionUID = 1L;

	/** Declaration des variables privees **/
	private Aeroport aeroport;
	private Echelle echelle;
	private JLabel depart, arrivee;
	private JButton zoom;
	private JTabbedPane Onglets = null;
	private JPanel tab1,tab2;
	private JList liste_depart, liste_arrivee;
	private ArrayList<Vol> liste_vols; 
    DefaultListModel listModel;            
                
//	private JTextField ;
	
	
	
	/** Constructeur de la classe Informations **/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Informations(Aeroport aeroport, Echelle echelle) {
		this.aeroport = aeroport;
		this.echelle = echelle;
		
		this.setMinimumSize(new Dimension(800,900));
		this.setPreferredSize(new Dimension(800,900));
		

		/** Création des Onglets **/
		Onglets = new JTabbedPane();
                Onglets.addTab("INFO", liste_depart); //
                Onglets.addTab("Outils", depart);
        
        /** Onglet 1 **/
        //tab1.setLayout(new GridLayout(2, 1));
        depart = new JLabel("Vols Départs  :");
        arrivee = new JLabel("Vols Arrivées  :");   
        

        
        //Création du model 
        listModel = new DefaultListModel();
         
        //Remplir le model

        //for(int i=0; i<liste_vols.size(); i++)
       // {
            // listModel.addElement(liste_vols.get(i));
        listModel.addElement("verge");
       // }
       // liste_depart.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        liste_depart = new JList(listModel);
        
        //tab1.add(depart);
        //tab1.add(liste_depart);
        //tab1.add(arrivee);
        //tab1.add(liste_arrivee);

     
        
        /** Onglet 2 **/

        
        


		
		
		
        this.add(Onglets);
	}
	
	
	
	
	
}
