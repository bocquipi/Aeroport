import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.Timer;

/* Projet JAVA         */
/* Modele              */
/* author :            */

public class Time extends Observable implements ActionListener {

	/** Declaration des variables privees **/
	private Timer timer;
	private int delay;
	
	/** Constructeur de la classe Echelle **/
	public Time(Fenetre fenetre) {
		
		delay = 5000; //Par defaut : vitesse = 5ms
		create_timer();
	}

	/** create_timer **/
	/** fonction : creation d'un timer **/
	public void create_timer() {
		timer = new Timer(delay, this);
		timer.setInitialDelay(delay);
		timer.start(); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
