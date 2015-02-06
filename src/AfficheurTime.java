/* Projet JAVA         */
/* Vue                 */
/* author :            */

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
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
	private Time time;
	
	/** Constructeur de la classe Echelle **/
	public AfficheurTime() {
		
		/* Time */
		time = new Time(this);
		
		/* Conteneur */
		Container conteneur = this.getContentPane();
		conteneur.setLayout(new java.awt.FlowLayout());
		conteneur.setLayout(new GridLayout(3,1));
		
		/* panel1 */
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1,1));
		panel1.setBorder(BorderFactory.createLineBorder(Color.black));
		
		/* label1 */
		JLabel label1 = new JLabel();
		label1.setHorizontalTextPosition(JLabel.CENTER);
		label1.setVerticalTextPosition(JLabel.CENTER);
		label1.setText(time.afficher_time());
		
		/* panel1 <- label1 */
		panel1.add(label1);	
		
		/* panel2 */
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1,1));
		
		/* slider1 */
		JSlider slider = new JSlider(0, 86400,0);
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
		
		/* Dimensionnement et affichage de la fenetre */
		this.pack();
		this.setSize(800, 300);
		this.setVisible(true);
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
