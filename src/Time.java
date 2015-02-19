/* Projet JAVA         */
/* Modele              */
/* author :            */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.SwingUtilities;
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
	private int index_pas;
	private int tableau_pas[] = {1, 5, 10, 30, 60, 1800, 3600};
	private int pas;
	private int index_pas_defaut;
	private boolean avance;
	
	/** Constructeur de la classe Echelle **/
	public Time(Plateforme plateforme) {
		
		this.plateforme = plateforme;
		temps = 0;
		secondes = 0;
		minutes = 0;
		heures = 0;
		delay = 1000; //Par defaut : vitesse = 1s
		index_pas = 1; //Index par defaut
		index_pas_defaut = 1; //Index par defaut
		pas = tableau_pas[index_pas]; //Par defaut : pas = 5s
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

	/** Getter/Setter de index_pas **/
	public int get_index_pas() {
		return index_pas;
	}
	
	public void set_index_pas(int index_pas) {
		this.index_pas = index_pas;
	}
	
	/** Getter/Setter de index_pas_defaut **/
	public int getIndex_pas_defaut() {
		return index_pas_defaut;
	}

	public void setIndex_pas_defaut(int index_pas_defaut) {
		this.index_pas_defaut = index_pas_defaut;
	}
	
	/** Getter de tableau_pas **/
	public int[] get_tableau_pas() {
		return tableau_pas;
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
	
	/** convertion **/
	/** fonction : Convertion du temps en heures, minutes et secondes **/
	public void convertir_time() {
		setHeures(getTemps()/3600);
		setMinutes((getTemps()%3600)/60);
		setSecondes((getTemps()%3600)%60);
	}
	
	/** afficher_time **/
	/** fonction : Afficher le temps du timer **/
	public String afficher_time() {
		convertir_time();
		/* Convertion Integer -> String */
		String heures = Integer.toString(getHeures());
		String minutes = Integer.toString(getMinutes());
		String secondes = Integer.toString(getSecondes());
		/* Gestion de l'affichage */
		if(getHeures() < 10) {
			heures = "0" + getHeures();
		}
		if(getMinutes() < 10) {
			minutes = "0" + getMinutes();
		}
		if(getSecondes() < 10) {
			secondes = "0" + getSecondes();
		}
		return heures + ":" + minutes + ":" + secondes;
	}
	
	/** rafraichir_time **/
	/** fonction : Rafraichir le temps du timer **/
	private void rafraichir_timer() {
		/* Depassement */
		if(temps >= 86400 & avance==true) {
			timer.stop();
			temps = 86400;
		}
		if(temps <= 0 & avance==false) {
			timer.stop();
			temps = 0;
		}		
		else {
			/* Play */
			if(avance == true) {
				temps += pas;
			}
			/* Rewind */
			else {
				temps -= pas;
			}	
		}
	}
	
	/* Listener de la classe Time */
	/* Fonction : Mise a jour du temps et du simulateur de vols */
	public void actionPerformed(ActionEvent e) {
		
		new Thread() {
			public void run() {
				rafraichir_timer();
				setChanged(); //Changement de l'objet Time
				notifyObservers(); //Appel de update et statechanged
				SwingUtilities.invokeLater(
						new Runnable() {
							public void run() {
								plateforme.get_fenetre().get_simulateur_vol().repaint(); //Mise a jour du SimulateurVol
							}
						}
				);
			}
		}.start();
	} 
}
