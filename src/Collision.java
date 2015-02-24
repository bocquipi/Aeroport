/* Projet JAVA         */
/* Modele              */
/* author :            */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList; 
//import java.util.Enumeration;
//import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class Collision {
	
	/** Declaration des variables privees **/
	private Plateforme plateforme;
	private ArrayList<ArrayList<Vol>> liste_temps;
	private ArrayList<Vol> liste_vols_temps[];
	//private Hashtable<Integer, ArrayList<Vol>> liste_collisions;
	private LinkedHashMap<Integer, ArrayList<Vol>> liste_collisions;
	private ArrayList<Vol> liste_vols_collisions[];
	private static final int nb_collisions_max = 200;
	private int pas;
	private int temps_total;
	private int nb_collisions;
	private String nom_fichier_collision = "collision.txt";
	
	/** Constructeur de la classe Collision **/
	@SuppressWarnings("unchecked") //Warning : this.liste_vols_collision = new ArrayList[temps_total/pas];
									//Warning : this.liste_vols_collisions = new ArrayList[2];
	public Collision(Plateforme plateforme){
		this.plateforme = plateforme;
		this.pas = 5;
		this.temps_total = 86400;
		this.nb_collisions = 0;
		this.liste_temps = new ArrayList<ArrayList<Vol>>(temps_total/pas);
		this.liste_vols_temps = new ArrayList[temps_total/pas];
		//this.liste_collisions = new Hashtable<Integer, ArrayList<Vol>>();
		this.liste_collisions = new LinkedHashMap<Integer, ArrayList<Vol>>();
		this.liste_vols_collisions = new ArrayList[nb_collisions_max];
	}
	
	/** Getter de liste_collisions **/
//	public Hashtable<Integer, ArrayList<Vol>> getListe_collisions() {
//		return liste_collisions;
//	}
	public LinkedHashMap<Integer, ArrayList<Vol>> getListe_collisions() {
	return liste_collisions;
	}
	
	/** recherche_vols_actifs **/
	/** fonction : recherche des vols actifs pour un temps donnes **/
	public void recherche_vols_actifs() {
		
		/* Parcours : ArrayList <ArrayList<Vol>> */
		for(int i = 0 ; i < temps_total/pas ; i++) {
			
			/* Parcours de la liste des vols */
			liste_vols_temps[i] = new ArrayList<Vol>();
			for(Vol v : plateforme.get_aeroport().get_trafic().get_liste_vols()) {
				
				/* Classement des vols par temps dans une ArrayList<Vol> */
				if((i*pas >= v.getTemps_depart_vol()) && (i*pas < v.getTemps_fin_vol())) {
					liste_vols_temps[i].add(v);
				}
			}
			liste_temps.add(liste_vols_temps[i]);
		}
	}
	
	/** detection_collision **/
	/** fonction : detection des collisions **/
	/** 			recuperation et analyse du trafic **/
	public void detection_collision() {
		
		/* Recherche des vols actifs */
		recherche_vols_actifs();
		
		/* Declaration des variables locales */
		int index_c1;
		int index_c2;
		Point point_c1;
		Point point_c2;
		int temps;
		
		for(int i = 0; i < liste_temps.size() ; i++) {
			temps = i*pas;
			if(liste_temps.get(i).size() > 1) {
				for(int j = 0 ; j < liste_temps.get(i).size() ; j++) {
					for(int k = j+1 ; k < liste_temps.get(i).size() ; k++) {
						if(((temps - liste_temps.get(i).get(j).getTemps_depart_vol())/(pas)) >= 0) {
							if(((temps - liste_temps.get(i).get(k).getTemps_depart_vol())/(pas)) >= 0) {
								index_c1 = (temps - liste_temps.get(i).get(j).getTemps_depart_vol())/(pas); //Calcul de l'index du premier comparateur
								index_c2 = (temps - liste_temps.get(i).get(k).getTemps_depart_vol())/(pas); //Calcul de l'index du second comparateur
								point_c1 = liste_temps.get(i).get(j).getTrajectoire_vol().get(index_c1);
								point_c2 = liste_temps.get(i).get(k).getTrajectoire_vol().get(index_c2);
								/* Collisions (Danger) */
								if(point_c1.get_coordonnees_point().getX() == point_c2.get_coordonnees_point().getX()) {
									if(point_c1.get_coordonnees_point().getY() == point_c2.get_coordonnees_point().getY()) {
										/* Enregistrement des deux vols dans une ArrayList */
										liste_vols_collisions[nb_collisions] = new ArrayList<Vol>();
										liste_vols_collisions[nb_collisions].add(liste_temps.get(i).get(j));
										liste_vols_collisions[nb_collisions].add(liste_temps.get(i).get(k));
										/* Enregistrement de l'ArrayList des deux vols dans la liste des collisions */
										liste_collisions.put(temps ,liste_vols_collisions[nb_collisions]);
										/* Incrementation du nombre de collisions */
										nb_collisions++;
									}
								}
							}
						}
					}
				}
			}
		}
		/* Enregistrement des collisions dans un fichier texte */
		enregistrer_collision();
	}
	
	/** enregistrer_collision **/
	/** fonction : enregistrement des collisions dans un fichier texte **/
	public void enregistrer_collision() {
		
		/* Declaration des variables locales */
		BufferedWriter fs = null;
		
		/* Ouverture du fichier */
		try {
			fs = new BufferedWriter(new FileWriter(nom_fichier_collision));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur au niveau de l'ouverture du fichier " + nom_fichier_collision);
			e.printStackTrace();
		}
		/* Ecriture dans le fichier */
		try {
			
			/* Declaration des variables locales */
			//Enumeration<ArrayList<Vol>> enumeration_valeur = liste_collisions.elements();
			//Enumeration<Integer> enumeration_cle = liste_collisions.keys();
			Iterator<ArrayList<Vol>> iterator_valeur = liste_collisions.values().iterator();
			Iterator<Integer> iterator_cle = liste_collisions.keySet().iterator();
			
			ArrayList<Vol> liste_tampon;
			int temps;
			int heures;
			int minutes;
			int secondes;
			String tampon;
			
			/* Presentation du fichier */
			fs.write("Liste des collisions de la plateforme de " + plateforme.get_aeroport().get_nom_aeroport());
			fs.newLine();
			
			/* Nombre de collisions */
			fs.write("Nombre de collisions : " + nb_collisions);
			fs.newLine();
			
			/* Affichage des collisions */
//			while((enumeration_valeur.hasMoreElements()) && (enumeration_cle.hasMoreElements())) {
//				liste_tampon = enumeration_valeur.nextElement();
//				temps = enumeration_cle.nextElement();
//				heures = temps/3600;
//				minutes = (temps%3600)/60;
//				secondes = (temps%3600)%60;
//				tampon = liste_tampon.get(0).getIdentifiant_vol() + " et " + liste_tampon.get(1).getIdentifiant_vol();
//				fs.write(heures + ":" + minutes + ":" + secondes + " - " + tampon);
//				fs.newLine();
//			}
			while((iterator_valeur.hasNext()) && (iterator_cle.hasNext())) {
				liste_tampon = iterator_valeur.next();
				temps = iterator_cle.next();
				heures = temps/3600;
				minutes = (temps%3600)/60;
				secondes = (temps%3600)%60;
				tampon = liste_tampon.get(0).getIdentifiant_vol() + " et " + liste_tampon.get(1).getIdentifiant_vol();
				fs.write(heures + ":" + minutes + ":" + secondes + " - " + tampon);
				fs.newLine();
			}
			
			/* Fin du fichier */
			fs.write("Fin du fichier");
			fs.newLine();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erreur au niveau de l'ecriture du fichier " + nom_fichier_collision);
			e.printStackTrace();
		}
		
		/* Fermeture du fichier */
		try {
			fs.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur au niveau de la fermeture du fichier " + nom_fichier_collision);
			e.printStackTrace();
		}
	}
}
