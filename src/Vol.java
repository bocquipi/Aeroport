/* Projet JAVA
 * La classe Vol est la classe representant un Vol de depart ou
 * d'arrivee extrait du fichier trafic.txt
 * 
 * @author : Cornette Cedric
 * 			 Bocquier Pierre
 * 			 Papin Alexis
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Vol {

	/** Declaration des variables privees
	 * @see Point
	 */
	private ArrayList<Point> trajectoire_vol;
	//enum Type_vol {ARR, DEP};
	private String type_vol;
	private String identifiant_vol;
	//enum Categorie_avion {H, M, L}
	private String categorie_avion;
	private String point_depart_vol;
	private String qfu_piste;
	private int temps_depart_vol; //Temps de la premiere position du vol
	private int temps_fin_vol; //Temps de la derniere position du vol
	private int temps_limite_vol; //Temps limite du depart du vol
	
	/** Constructeur de la classe Vol 
	 *
	 * @param type
	 * 			le type de vol (DEP/ARR).
	 * @param identifiant
	 * 			l'identifiant du vol.
	 * @param categorie
	 * 			la categorie de l'avion (H/M/L).
	 * @param point_depart
	 * 			le point de depart du vol.
	 * @param qfu
	 * 			le qfu de la piste.
	 * @param temps_depart
	 * 			le temps de depart de l'avion.
	 * @param temps_limite
	 * 			l'heure à laquelle l'avion doit etre parti.
	 * @param trajectoire
	 * 			l'ensemble des coordonnees d'un vol.
	 * 
	 * @see Point
	 * @see traiter_temps_limite
	 */
	public Vol(String type, String identifiant, String categorie, String point_depart, String qfu,
			   int temps_depart, String temps_limite, String trajectoire)
	{
		this.trajectoire_vol = new ArrayList <Point>();
		remplir_trajectoire(trajectoire);
		this.type_vol = type;
		this.identifiant_vol = identifiant;
		this.categorie_avion = categorie;
		this.point_depart_vol = point_depart;
		this.qfu_piste = qfu;
		this.temps_depart_vol = temps_depart;
		this.temps_fin_vol = traiter_temps_fin();
		this.temps_limite_vol = traiter_temps_limite(temps_limite);
	}
	
	/** Getter de trajectoire_vol
	 *
	 * @return la trajectoire du vol dans un arraylist de type Point.
	 */
	public ArrayList<Point> getTrajectoire_vol() {
		return trajectoire_vol;
	}
	
	/** Getter/Setter de type_vol
	 *
	 * @return le type du vol dans une string.
	 */
	public String getType_vol() {
		return type_vol;
	}
	
	/**
	 * @param type_vol
	 * 			rentre le nouveau type du vol.
	 */
	public void setType_vol(String type_vol) {
		this.type_vol = type_vol;
	}

	/** Getter/Setter de trajectoire_vol
	 *
	 * @return l'identifiant du vol.
	 */
	public String getIdentifiant_vol() {
		return identifiant_vol;
	}
	/**
	 * @param identifiant_vol
	 * 			rentre le nouveau type du vol.
	 */
	public void setIdentifiant_vol(String identifiant_vol) {
		this.identifiant_vol = identifiant_vol;
	}

	/** Getter/Setter de trajectoire_vol
	 *
	 * @return la categorie de l'avion.
	 */
	public String getCategorie_avion() {
		return categorie_avion;
	}
	/**
	 * @param categorie_avion
	 * 			rentre la nouvelle categorie de l'avion.	
	 */
	public void setCategorie_avion(String categorie_avion) {
		this.categorie_avion = categorie_avion;
	}

	/** Getter/Setter de trajectoire_vol
	 *
	 * @return le point de depart du vol.
	 */
	public String getPoint_depart_vol() {
		return point_depart_vol;
	}
	/**
	 * @param point_depart_vol
	 * 			rentre le nouveau point de depart du vol.
	 */
	public void setPoint_depart_vol(String point_depart_vol) {
		this.point_depart_vol = point_depart_vol;
	}

	/** Getter/Setter de trajectoire_vol
	 *
	 * @return le qfu de la piste.
	 */
	public String getQfu_piste() {
		return qfu_piste;
	}
	/**
	 * @param qfu_piste
	 * 			rentre le nouveau qfu de la piste.
	 */
	public void setQfu_piste(String qfu_piste) {
		this.qfu_piste = qfu_piste;
	}

	/** Getter/Setter de trajectoire_vol 
	 *
	 * @return l'heure de depart du vol.
	 */
	public int getTemps_depart_vol() {
		return temps_depart_vol;
	}
	/**
	 * @param temps_depart_vol
	 * 			rentre la nouvelle heure de depart du vol.
	 */
	public void setTemps_depart_vol(int temps_depart_vol) {
		this.temps_depart_vol = temps_depart_vol;
	}
	
	/* Getter/Setter de temps_fin_vol */
	public int getTemps_fin_vol() {
		return temps_fin_vol;
	}
	
	public void setTemps_fin_vol(int temps_fin_vol) {
		this.temps_fin_vol = temps_fin_vol;
	}
	
	/** Getter/Setter de trajectoire_vol
	 *
	 * @return l'heure limite ou l'avion doit etre parti.
	 */
	public int getTemps_limite_vol() {
		return temps_limite_vol;
	}
	/**
	 * @param temps_limite_vol
	 * 			rentre le nouveau temps limite du vol.
	 */
	public void setTemps_limite_vol(int temps_limite_vol) {
		this.temps_limite_vol = temps_limite_vol;
	}
	
	/* traiter_temps_fin */
	/* fonction : traitement du temps de la derniere position du vol */
	public int traiter_temps_fin() {
		
		int pas = 5; //Option : Recuperation du pas dans Time
		return this.temps_depart_vol + this.trajectoire_vol.size()*pas;
	}
	
	/** traiter_temps_limite
	 * fonction :
	 *
	 * @param temps_limite
	 * @return le temps limite du vol si ce dernier existe.
	 */
	public int traiter_temps_limite(String temps_limite) {
		
		if(temps_limite.equals("_")) {
			return 0;
		}
		else {
			return Integer.parseInt(temps_limite);
		}
	}
	
	/** remplir_trajectoire **/
	/** fonction : afficher la trajectoire d'un vol **/
	/**
	 * @param trajectoire
	 * 			remplit l'arraylist trajectoire_vol a partir de la string contenant l'ensemble des coordonnees
	 * 			du vol.
	 * @see Point
	 */
	public void remplir_trajectoire(String trajectoire) {
		
		Scanner s = new Scanner(trajectoire);
		while(s.hasNext()) {
			String coordonnees = s.next();
			this.trajectoire_vol.add(new Point(coordonnees));
		}
		s.close();
	}
	
	/** print_trajectoire 
	 * fonction : afficher la trajectoire d'un vol 
	 *
	 * @see Point
	 */
	public void print_trajectoire() {
		for(Point p : trajectoire_vol) {
			System.out.println(p);
		}
	}
	
	/** toString **/
	/** fonction : surcharge de toString pour l'affichage des caracteristiques d'un vol **/
	public String toString(){
		return type_vol + " " + identifiant_vol + " "  + categorie_avion + " " 
				+ point_depart_vol + " " + qfu_piste + " " + temps_depart_vol + " " + temps_limite_vol;
	}
}
