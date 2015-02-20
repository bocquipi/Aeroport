import java.util.ArrayList;

/* Projet JAVA         */
/* Modele              */
/* author :            */

public class Collision {
	
	/** Declaration des variables privees **/
	private Plateforme plateforme;
	private ArrayList<ArrayList<Vol>> liste_temps;
	private ArrayList<Vol> liste_vol_collision;
	int pas;
	int temps_total;
	
	/** Constructeur de la classe Collision **/
	public Collision(Plateforme plateforme){
		this.plateforme = plateforme;
		this.pas = 5;
		this.temps_total = 86400;
		this.liste_temps = new ArrayList<ArrayList<Vol>>(temps_total/pas);
	}
	
	/** recherche_vols_actifs **/
	/** fonction : recherche des vols actifs pour un temps donnes **/
	public void recherche_vols_actifs() {
		for(int i = 0 ; i < temps_total/pas ; i++) {
			for(Vol v : plateforme.get_aeroport().get_trafic().get_liste_vols()) {
				if((i*pas >= v.getTemps_depart_vol()) && (i*pas < v.getTemps_fin_vol())) {
					liste_vol_collision = new ArrayList<Vol>();
					liste_vol_collision.add(v);
				}
			}
			liste_temps.add(liste_vol_collision);
		}
	}
	
	/** detection_collision **/
	/** fonction : detection des collisions **/
	/** 			recuperation et analyse du trafic **/
	public void detection_collision() {
		
		/* Declaration des variables locales */
		//int index;
		//int temps;
		
		for(int i = 0; i < liste_temps.size() ; i++) {
			for(int j = 0 ; j < liste_temps.get(i).size() ; j++) {
				for(int k = 1 ; k < liste_temps.get(i).size() ; k++) {
					liste_temps.get(i).get(j).getTrajectoire_vol();
					liste_temps.get(i).get(k).getTrajectoire_vol();
					
				}
			}
		}
	}
}