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
	public Vol(String type, String identifiant, String categorie, String point_depart, String qfu,
			int temps_depart, String temps_limite, String trajectoire)
	{
		trajectoire_vol = new ArrayList <Point>();
		this.type_vol = type;
		this.identifiant_vol = identifiant;
		this.categorie_avion = categorie;
		this.point_depart_vol = point_depart;
		this.qfu_piste = qfu;
		this.temps_depart_vol = temps_depart;
		
		/* Traitement du temps limite de depart pour un vol */
		if(temps_limite.equals("_")){
			this.temps_limite_vol = 0;
		}
		else {
			this.temps_limite_vol = Integer.parseInt(temps_limite);
		}
		
		/* Traitement de la trajectoire pour un vol */
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
