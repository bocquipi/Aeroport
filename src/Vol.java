/* Projet JAVA         */
/* Modele              */
/* author :            */

import java.util.ArrayList;
import java.util.Scanner;

public class Vol {

	/** Declaration des variables privees **/
	private ArrayList<Point> trajectoire_vol;
	//enum Type_vol {ARR, DEP};
	private String type_vol;
	private String identifiant_vol;
	//enum Categorie_avion {H, M, L}
	private String categorie_avion;
	private String point_depart_vol;
	private String qfu_piste;
	private int temps_depart_vol;
	private int temps_limite_vol;
	
	/** Constructeur de la classe Vol **/
	public Vol(String type, String identifiant, String categorie, String point_depart, String qfu, int temps_depart, String temps_limite, String trajectoire)
	{
		this.trajectoire_vol = new ArrayList <Point>();
		remplir_trajectoire(trajectoire);
		this.type_vol = type;
		this.identifiant_vol = identifiant;
		this.categorie_avion = categorie;
		this.point_depart_vol = point_depart;
		this.qfu_piste = qfu;
		this.temps_depart_vol = temps_depart;
		this.temps_limite_vol = traiter_temps_limite(temps_limite);
	}
	
	/** Getter de trajectoire_vol **/
	public ArrayList<Point> get_trajectoire_vol() {
		return trajectoire_vol;
	}
	
	/** Getter/Setter de trajectoire_vol **/
	public String getType_vol() {
		return type_vol;
	}

	public void setType_vol(String type_vol) {
		this.type_vol = type_vol;
	}

	/** Getter/Setter de trajectoire_vol **/
	public String getIdentifiant_vol() {
		return identifiant_vol;
	}

	public void setIdentifiant_vol(String identifiant_vol) {
		this.identifiant_vol = identifiant_vol;
	}

	/** Getter/Setter de trajectoire_vol **/
	public String getCategorie_avion() {
		return categorie_avion;
	}

	public void setCategorie_avion(String categorie_avion) {
		this.categorie_avion = categorie_avion;
	}

	/** Getter/Setter de trajectoire_vol **/
	public String getPoint_depart_vol() {
		return point_depart_vol;
	}

	public void setPoint_depart_vol(String point_depart_vol) {
		this.point_depart_vol = point_depart_vol;
	}

	/** Getter/Setter de trajectoire_vol **/
	public String getQfu_piste() {
		return qfu_piste;
	}

	public void setQfu_piste(String qfu_piste) {
		this.qfu_piste = qfu_piste;
	}

	/** Getter/Setter de trajectoire_vol **/
	public int getTemps_depart_vol() {
		return temps_depart_vol;
	}

	public void setTemps_depart_vol(int temps_depart_vol) {
		this.temps_depart_vol = temps_depart_vol;
	}

	/** Getter/Setter de trajectoire_vol **/
	public int getTemps_limite_vol() {
		return temps_limite_vol;
	}

	public void setTemps_limite_vol(int temps_limite_vol) {
		this.temps_limite_vol = temps_limite_vol;
	}
	
	/** print_trajectoire **/
	/** fonction : afficher la trajectoire d'un vol **/
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
	public void remplir_trajectoire(String trajectoire) {
		
		Scanner s = new Scanner(trajectoire);
		while(s.hasNext()) {
			String coordonnees = s.next();
			this.trajectoire_vol.add(new Point(coordonnees));
		}
		s.close();
	}
	
	/** print_trajectoire **/
	/** fonction : afficher la trajectoire d'un vol **/
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
