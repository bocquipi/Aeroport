/** Projet JAVA         */
/** Vue                 */
/** @author : PB - CC   */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class AfficheurTime extends JFrame implements Observer, ChangeListener {

	/** Serialisation **/
	private static final long serialVersionUID = 1L;

	/** Declaration des variables privees
	 * 
	 * @see Plateforme
	 */
	private Plateforme plateforme;
	private JSlider slider;
	private JLabel label;
	
	/** Image (Boutons) */
	private ImageIcon iPlay = new ImageIcon("images/play.png");
	private ImageIcon iPause = new ImageIcon("images/pause.png");
	private ImageIcon iAvanceRap = new ImageIcon("images/avance_rapide.png");
	private ImageIcon iRewind = new ImageIcon("images/rewind.png");
	private ImageIcon iRestart = new ImageIcon("images/restart.png");
	
	/** Constructeur de la classe Echelle
	 * 
	 * @param plateforme
	 * 
	 * @see Plateforme
	 */
	public AfficheurTime(Plateforme plateforme) {
		
		/** Taille maximale de la fenetre */
		this.setMaximumSize(new Dimension(400, 300));
		
		/** Titre de la fenetre */
		this.setTitle("Timer");
		
		/** Plateforme */
		this.plateforme = plateforme;
		
		/** Conteneur */
		Container conteneur = this.getContentPane();
		conteneur.setLayout(new GridLayout(3,1));
		
		/** panel1 */
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel1.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
		/** label1 */
		label = new JLabel();
		label.setText("00:00:00");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.BLUE);
		Font font = new Font("Verdana",Font.BOLD,40);
		label.setFont(font);
		
		/** panel1 <- label1 */
		panel1.add(label);	
		
		/** panel2 */
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1,1));
		
		/** slider */
		slider = new JSlider(0, 86400,0);
		slider.setMajorTickSpacing(3600);
		slider.setMinorTickSpacing(60);
		slider.addChangeListener(this);
		
		/** echelle du slider */
		Hashtable <Integer, JLabel> echelle_slider = new Hashtable <Integer, JLabel>();
		echelle_slider.put( new Integer(0), new JLabel("0h") );
		echelle_slider.put( new Integer(86400/24*6), new JLabel("6h") );
		echelle_slider.put( new Integer(86400/24*12), new JLabel("12h") );
		echelle_slider.put( new Integer(86400/24*18), new JLabel("18h") );
		echelle_slider.put( new Integer(86400), new JLabel("24h") );
		slider.setLabelTable(echelle_slider);
		slider.setPaintLabels(true);
		
		/** panel2 <- slider1 */
		panel2.add(slider);		
		
		/** panel3 */
		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(1,5));
		
		JButton rewind = new JButton(iRewind); 
		panel3.add(rewind); 						
		
		JButton pause = new JButton(iPause); 
		panel3.add(pause); 
		
		JButton play = new JButton(iPlay); 
		panel3.add(play); 
		
		JButton restart = new JButton(iRestart); 
		panel3.add(restart);
		
		JButton avance = new JButton(iAvanceRap);
		panel3.add(avance); 
		
		/** conteneur <- panels */
		conteneur.add(panel1); 
		conteneur.add(panel2); 
		conteneur.add(panel3);
		
		/** Listeners */
		rewind.addActionListener(new ActionRewind());
		play.addActionListener(new ActionPlay());
		pause.addActionListener(new ActionPause());
		restart.addActionListener(new ActionRestart());
		avance.addActionListener(new ActionAvance());
		
		/** Dimensionnement et affichage de la fenetre */
		this.pack();
		this.setVisible(false);
		this.setResizable(false);
	}

	/** stateChanged
	 * fonction : Mise a jour au changement d'etat du slider
	 * 
	 * @param e
	 */
	public void stateChanged(ChangeEvent e) {
		int valeur= 0;
		if(slider.getValue()%5 >= 2.5)
			valeur = slider.getValue() + (5-(slider.getValue()%5));
		if(slider.getValue()%5 < 2.5)
			valeur =slider.getValue() - (slider.getValue()%5);
		slider.setValue(valeur);
		plateforme.get_time().setTemps(valeur);
		label.setText(plateforme.get_time().afficher_time());
	}
	
	/** update 
	 * fonction : Mise a jour au changement d'etat de l'objet Time
	 * 
	 * @param Time
	 * @param arg
	 * 
	 * @see Plateforme
	 */
	public void update(Observable Time, Object arg) {
		slider.setValue(plateforme.get_time().getTemps());
		label.setText(plateforme.get_time().afficher_time());
	}
	
	/** Classe inner pour les listeners **/
	
	/** Class ActionRewind
	 * fonction : timer en mode rewind
	 * 
	 * @see Plateforme
	 * @see Time
	 */
	class ActionRewind implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			int index_pas;
			int tableau_pas[] = plateforme.get_time().get_tableau_pas();
			
			if(plateforme.get_time().getAvance() == true) {
				
				index_pas = plateforme.get_time().getIndex_pas_defaut();
				plateforme.get_time().set_index_pas(index_pas);
				plateforme.get_time().setPas(tableau_pas[index_pas]);
				plateforme.get_time().setAvance(false);
				plateforme.get_time().start_timer();
			}
			else {
				index_pas = plateforme.get_time().get_index_pas();
				
				/** Augmentation du pas */
				if(index_pas != (tableau_pas.length-1) ) {
						index_pas++;
				}
				
				plateforme.get_time().set_index_pas(index_pas);
				plateforme.get_time().setPas(tableau_pas[index_pas]);
				plateforme.get_time().start_timer();
			}
		}
	}		
   
	/** Class ActionPlay
	 * fonction : timer en mode play
	 * 
	 * @see Plateforme
	 * @see Time
	 */
	class ActionPlay implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if(plateforme.get_fenetre().get_bTrafic() == true) {
				int index_pas = plateforme.get_time().getIndex_pas_defaut();
				int tableau_pas[] = plateforme.get_time().get_tableau_pas();
				plateforme.get_time().setAvance(true);
				plateforme.get_time().set_index_pas(index_pas);
				plateforme.get_time().setPas(tableau_pas[index_pas]);
				plateforme.get_time().start_timer();
			}
			else {
				JOptionPane.showMessageDialog(plateforme.get_fenetre(), "Veuillez charger la plateforme aeroportuaire et le trafic", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
   
	/** Class ActionStop
	/* fonction : stop le timer
	 * 
	 * @see Plateforme
	 * @see Time
	 */
	class ActionPause implements ActionListener {		   
		public void actionPerformed(ActionEvent e) {
			plateforme.get_time().stop_timer();
		}
	}	
   
	/** Class ActionRestart
	 * fonction : restart le timer
	 * 
	 * @see Plateforme
	 * @see Time
	 */
	class ActionRestart implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			slider.setValue(0);
			plateforme.get_time().setTemps(0);
			label.setText(plateforme.get_time().afficher_time());
			plateforme.get_time().stop_timer();
			plateforme.get_time().setAvance(true);
		}
	}
	
	/** Class ActionAvance
	/* fonction : timer en mode avance rapide
	 * 
	 * @see Plateforme
	 * @see Time
	 */
	class ActionAvance implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			int index_pas;
			int tableau_pas[] = plateforme.get_time().get_tableau_pas();
			
			if(plateforme.get_time().getAvance() == false) {
				
				index_pas = plateforme.get_time().getIndex_pas_defaut();
				plateforme.get_time().set_index_pas(index_pas);
				plateforme.get_time().setPas(tableau_pas[index_pas]);
				plateforme.get_time().setAvance(true);
				plateforme.get_time().start_timer();
			}
			else {
				index_pas = plateforme.get_time().get_index_pas();
				
				/** Augmentation du pas */
				if(index_pas != (tableau_pas.length-1) ) {
						index_pas++;
				}
				
				plateforme.get_time().set_index_pas(index_pas);
				plateforme.get_time().setPas(tableau_pas[index_pas]);
				plateforme.get_time().start_timer();
			}
		}
	}
}
