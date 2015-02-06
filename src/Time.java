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
	private int temps;
	private int secondes;
	private int minutes;
	private int heures;
	private int delay;
	private int pas;
	private boolean avance;
	
	/** Constructeur de la classe Echelle **/
	public Time(AfficheurTime afficheur) {
		
		temps = 0;
		secondes = 0;
		minutes = 0;
		heures = 0;
		delay = 5000; //Par defaut : vitesse = 5ms
		pas = 1; //Par defaut : pas = 1
		avance = true;
		create_timer();
		addObserver(afficheur);
	}

	/** Getter de timer **/
	public Timer getTimer() {
		return timer;
	}

	/** Getter/Setter de temps **/
	public int getTemps() {
		return temps;
	}

	public void setTemps(int temps) {
		this.temps = temps;
	}
	
	/** Getter/Setter de secondes **/
	public int getSecondes() {
		return secondes;
	}

	public void setSecondes(int secondes) {
		this.secondes = secondes;
	}

	/** Getter/Setter de minutes **/
	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	/** Getter/Setter de heures **/
	public int getHeures() {
		return heures;
	}

	public void setHeures(int heures) {
		this.heures = heures;
	}

	/** Getter/Setter de delay **/
	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	/** Getter/Setter de pas **/
	public int getPas() {
		return pas;
	}

	public void setPas(int pas) {
		this.pas = pas;
	}

	/** Getter/Setter de avance **/
	public boolean getAvance() {
		return avance;
	}

	public void setAvance(boolean avance) {
		this.avance = avance;
	}
	
	/** create_timer **/
	/** fonction : creation d'un timer **/
	public void create_timer() {
		timer = new Timer(delay, this);
		timer.setInitialDelay(delay);
		
	}

	/** start_timer **/
	/** fonction : start du timer **/
	public void start_timer() {
		timer.start(); 
	}
	
	/** convertion**/
	public void convertir_time() {
		setHeures(getTemps()/3600);
		setMinutes((getTemps()%3600)/60);
		setSecondes((getTemps()%3600)%60);
	}
	/** afficher_time **/
	/** fonction : Afficher le temps du timer **/
	public String afficher_time() {
		
		return getHeures()+" : "+getMinutes()+" : "+getSecondes();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
