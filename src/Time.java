/* Projet JAVA         */
/* Modele              */
/* author :            */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Time extends Observable implements ActionListener {

	/** Declaration des variables privees
	 * 
	 * @see Plateforme
	 */
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
	
	/** Constructeur de la classe Echelle
	 * 
	 * @param plateforme
	 */
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

	/** Getter de timer
	 * 
	 * @return le timer utilise.
	 */
	public Timer getTimer() {
		return timer;
	}

	/** Getter de temps
	 * 
	 * @return le temps actuel du timer.
	 */
	public int getTemps() {
		return temps;
	}

	/** Setter de temps
	 * 
	 * @param temps
	 * 		rentre la nouvelle valeur du temps du timer.
	 */
	public void setTemps(int temps) {
		this.temps = temps;
	}
	
	/** Getter de secondes
	 * 
	 * @return la valeur des secondes du timer.
	 */
	public int getSecondes() {
		return secondes;
	}
	
	/** Setter de secondes
	 * 
	 * @param secondes
	 * 		rentre la nouvelle valeur des secondes du timer.
	 */
	public void setSecondes(int secondes) {
		this.secondes = secondes;
	}

	/** GetterSetter de minutes
	 * 
	 * @return la valeur des minutes du timer.
	 */
	public int getMinutes() {
		return minutes;
	}

	/** Setter de minutes
	 * 
	 * @param minutes
	 * 		rentre la nouvelle valeur des minutes du timer.
	 */
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	/** GetterSetter de heures
	 * 
	 * @return la valeur des heures du timer.
	 */
	public int getHeures() {
		return heures;
	}
	
	/** Setter de heures
	 * 
	 * @param heures
	 * 		rentre la nouvelle valeur des heures du timer.
	 */
	public void setHeures(int heures) {
		this.heures = heures;
	}

	/** Getter de delay
	 * 
	 * @return la valeur du delay.
	 */
	public int getDelay() {
		return delay;
	}

	/** Setter de delay
	 * 
	 * @param delay
	 * 		rentre la nouvelle valeur de delay.
	 */
	public void setDelay(int delay) {
		this.delay = delay;
	}

	/** Getter de pas
	 * 
	 * @return le pas du timer.
	 */
	public int getPas() {
		return pas;
	}

	/** Setter de pas
	 * 
	 * @param pas
	 * 		rentre la nouvelle valeur de pas du timer.
	 */
	public void setPas(int pas) {
		this.pas = pas;
	}

	/** Getter de index_pas
	 * 
	 * @return l'index du pas.
	 */
	public int get_index_pas() {
		return index_pas;
	}
	
	/** Setter de index_pas
	 * 
	 * @param index_pas
	 * 		rentre la nouvelle valeur d'index_pas.
	 */
	public void set_index_pas(int index_pas) {
		this.index_pas = index_pas;
	}
	
	/** Getter de index_pas_defaut
	 * 
	 * @return l'index pas par defaut.
	 */
	public int getIndex_pas_defaut() {
		return index_pas_defaut;
	}

	/** Setter de index_pas_defaut
	 * 
	 * @param index_pas_defaut
	 * 		rentre la nouvelle valeur d'index_pas_defaut.
	 */
	public void setIndex_pas_defaut(int index_pas_defaut) {
		this.index_pas_defaut = index_pas_defaut;
	}
	
	/** Getter de tableau_pas
	 * 
	 * @return le tableau de pas disponibles.
	 */
	public int[] get_tableau_pas() {
		return tableau_pas;
	}
	
	/** Getter de avance
	 * 
	 * @return l'etat de la variable avance du timer.
	 */
	public boolean getAvance() {
		return avance;
	}

	/** Setter de avance
	 * 
	 * @param avance
	 * 		rentre le nouvel etat de avance du timer.
	 */
	public void setAvance(boolean avance) {
		this.avance = avance;
	}
	
	/** create_timer 
	 * fonction : creation d'un timer.
	 */
	public void create_timer() {
		timer = new Timer(delay, this);
	}

	/** start_timer
	 * fonction : start du timer. 
	 */
	public void start_timer() {
		timer.start(); 
	}
	
	/** stop_timer
	 * fonction : stop du timer.
	 */
	public void stop_timer() {
		timer.stop(); 
	}
	
	/** conversion
	 *  fonction : Convertion du temps en heures, minutes et secondes
	 */
	public void convertir_time() {
		setHeures(getTemps()/3600);
		setMinutes((getTemps()%3600)/60);
		setSecondes((getTemps()%3600)%60);
	}
	
	/** afficher_time 
	 * fonction : Afficher le temps du timer.
	 */
	public String afficher_time() {
		convertir_time();
		/* Conversion Integer -> String */
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
	
	/** rafraichir_time
	 * fonction : Rafraichir le temps du timer.
	 */
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
