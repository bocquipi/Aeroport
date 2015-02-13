/* Projet JAVA         */
/* Modele              */
/* author :            */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.Timer;

public class Time extends Observable implements ActionListener {

	/** Declaration des variables privees **/
	Plateforme plateforme;
	private Timer timer;
	private int temps;
	private int secondes;
	private int minutes;
	private int heures;
	private int delay;
	private int pas;
	private boolean avance;
	
	/** Constructeur de la classe Echelle **/
	public Time(Plateforme plateforme) {
		
		this.plateforme = plateforme;
		temps = 0;
		secondes = 0;
		minutes = 0;
		heures = 0;
		delay = 10; //Par defaut : vitesse = 1s
		pas = 5; //Par defaut : pas = 5s
		avance = true;
		create_timer();
		addObserver(this.plateforme.get_afficheur_time());
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
	}

	/** start_timer **/
	/** fonction : start du timer **/
	public void start_timer() {
		timer.start(); 
	}
	
	/** stop_timer **/
	/** fonction : stop du timer **/
	public void stop_timer() {
		timer.stop(); 
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
		
		convertir_time();
		return getHeures()+" : "+getMinutes()+" : "+getSecondes();
	}
	
	/** rafraichir_time **/
	/** fonction : Rafraichir le temps du timer **/
	private void rafraichir_timer() {
		if(temps >= 86400 || (temps <=0 & avance == false)) {
			timer.stop();
			temps = 0;
		}		
		else {
			if(avance == true) {
				temps += pas;
			}
			else {
				temps -= pas;
			}	
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		rafraichir_timer();
		plateforme.get_fenetre().get_simulateur_vol().repaint();
		setChanged(); //Changement de l'objet Timer
		notifyObservers(); //Appel de update et statechanged
	}
}
