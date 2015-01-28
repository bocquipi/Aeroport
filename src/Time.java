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
	private int delais;
	
	/** Constructeur de la classe Echelle **/
	public Time(Fenetre fenetre) {
		
		delais = 5000; //Par defaut : vitesse = 5ms
		create_timer();
	}

	/** create_timer **/
	/** fonction : creation d'un timer **/
	public void create_timer() {
		timer = new Timer(delais, this);
		timer.setInitialDelay(delais);
		timer.start(); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
