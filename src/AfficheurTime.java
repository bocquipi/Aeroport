/* Projet JAVA         */
/* Vue                 */
/* author :            */

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class AfficheurTime extends JFrame implements Observer, ChangeListener {

	/** Serialisation **/
	private static final long serialVersionUID = 1L;

	/** Declaration des variables privees **/
	private Plateforme plateforme;
	private JSlider slider;
	private JLabel label;
	
	/** Constructeur de la classe Echelle **/
	public AfficheurTime(Plateforme plateforme) {
		
		/* Plateforme */
		this.plateforme = plateforme;
		
		/* Conteneur */
		Container conteneur = this.getContentPane();
		conteneur.setLayout(new java.awt.FlowLayout());
		conteneur.setLayout(new GridLayout(3,1));
		
		/* panel1 */
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1,1));
		panel1.setBorder(BorderFactory.createLineBorder(Color.black));
		
		/* label1 */
		label = new JLabel();
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.CENTER);
		
		/* panel1 <- label1 */
		panel1.add(label);	
		
		/* panel2 */
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1,1));
		
		/* slider */
		slider = new JSlider(0, 86400,0);
		slider.setMajorTickSpacing(3600);
		slider.setMinorTickSpacing(60);
		slider.addChangeListener(this);
		
		/* panel2 <- slider1 */
		panel2.add(slider);		
		
		/* panel3 */
		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(1,2));
		
		JButton rewind = new JButton("Rewind"); 
		panel3.add(rewind); 
		
		JButton play = new JButton("Play"); 
		panel3.add(play); 						
		
		JButton stop = new JButton("Stop"); 
		panel3.add(stop); 
		
		JButton restart = new JButton("Restart"); 
		panel3.add(restart); 					
		
		/* conteneur <- panels */
		conteneur.add(panel1); 
		conteneur.add(panel2); 
		conteneur.add(panel3);
		
		/* Listeners */
		rewind.addActionListener(new ActionRewind());
		play.addActionListener(new ActionPlay());
		stop.addActionListener(new ActionStop());
		restart.addActionListener(new ActionRestart());
		
		/* Dimensionnement et affichage de la fenetre */
		this.pack();
		this.setSize(800, 300);
		this.setVisible(true);
	}
	
	/** update **/
	/** fonction : Mise a jour au changement d'etat du slider **/
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		int valeur;
		valeur = slider.getValue();
		if(valeur%5 >= 2.5){
			plateforme.get_time().setTemps(valeur+(5-(valeur%5)));  
		}
		if(valeur%5 < 2.5) {
			plateforme.get_time().setTemps(valeur-(valeur%5));
		}
		label.setText(plateforme.get_time().afficher_time());
	}

	/** update **/
	/** fonction : Mise a jour au changement d'etat de l'objet Time **/
	public void update(Observable Time, Object arg) {
		// TODO Auto-generated method stub
		slider.setValue(plateforme.get_time().getTemps());
		label.setText(plateforme.get_time().afficher_time());
	}
	
	/** Classe inner pour les listeners **/
	
	/* Class ActionRewind */
	/* fonction : timer en mode rewind */
	class ActionRewind implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			plateforme.get_time().setAvance(false);
			plateforme.get_time().start_timer();
		}
	}		
   
	/* Class ActionPlay */
	/* fonction : timer en mode play */
	class ActionPlay implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			plateforme.get_time().setAvance(true);
			plateforme.get_time().start_timer();
		}
	}
   
	/* Class ActionStop */
	/* fonction : stop le timer */
	class ActionStop implements ActionListener {		   
		public void actionPerformed(ActionEvent e) {
			plateforme.get_time().stop_timer();
		}
	}	
   
	/* Class ActionRestart */
	/* fonction : restart le timer */
	class ActionRestart implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			slider.setValue(0);
			plateforme.get_time().setTemps(0);
			label.setText(plateforme.get_time().afficher_time());
			plateforme.get_time().stop_timer(); 
		}
	}
}
