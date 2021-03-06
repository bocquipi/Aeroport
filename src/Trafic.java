/** Projet JAVA         */
/** Modele              */
/** @author : AP - PB   */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Trafic {

	/** Declaration des variables privees 
	 * @see Vol
	 * @see Plateforme
	 * @see Collision
	 */
	private Plateforme plateforme;
	private Collision collision;
	private ArrayList<Vol> liste_vols;
	private int nombre_vols;
	private String nom_fichier_trafic;
	
	/* utilisation du pattern Singleton */
	
	/** Constructeur de la classe Trafic
	 * @see Vol
	 * @see Plateforme
	 * @see Collision
	 */
	public Trafic(Plateforme plateforme){
		this.plateforme = plateforme;
		this.collision = new Collision(this.plateforme);
		this.liste_vols = new ArrayList<Vol>();
		set_nombre_vols(0);
		set_nom_fichier_trafic("inconnu");
	}
	
	/** Getter de collision
	 * 
	 * @return la collision entre deux avions.
	 * 
	 * @see Collision
	 */
	public Collision get_collision(){
		return collision;
	}
	
	/** Getter de liste_vols
	 * 
	 * @return la liste des vols de la plateforme aeroportuaire.
	 *
	 *@see Vol
	 */
	public ArrayList<Vol> get_liste_vols(){
		return liste_vols;
	}
	
	/** Getter de nombre_vols
	 * 
	 * @return le nombre de vols sur la plateforme aeroportuaire.
	 */
	public int get_nombre_vols(){
		return nombre_vols;
	}
	/**Setter de nombre_vols
	 * 
	 * @param nombre_vols
	 * 		rentre le nouveau nombre de vols sur la plateforme aeroportuaire.
	 * 			
	 */
	public void set_nombre_vols(int nombre_vols) {
		this.nombre_vols = nombre_vols;
	}
	
	/** Getter de nom_fichier_trafic
	 * 
	 * @return le nom du fichier contenant les informations des vols sur la plateforme aeroportuaire.
	 */
	public String get_nom_fichier_trafic() {
		return nom_fichier_trafic;
	}
	
	/** Setter de nom_fichier_trafic
	 * 
	 * @param nom_fichier_trafic
	 * 		rentre le nouveau nom du fichier trafic.
	 */
	public void set_nom_fichier_trafic(String nom_fichier_trafic) {
		this.nom_fichier_trafic = nom_fichier_trafic;
	}
	
	/** print_liste_vols
	 * fonction : afficher la liste des vols de la plateforme aeroportuaire.
	 */
	public void print_liste_vols() {
		for(Vol v : liste_vols) {
			System.out.println(v);
			v.print_trajectoire();
		}
	}
	
	/** charger_fichier_trafic
	 * fonction : chargement du fichier texte.
	 *			creation du trafic de la plateforme aeroportuaire.
	 * 
	 * @param trafic
	 * 
	 */
	public void charger_fichier_trafic(Trafic trafic) {
			
		/* Declaration des variables locales */
		BufferedReader fe = null;
		String tampon;
		String type;
		String identifiant;
		String categorie;
		String point_depart;
		String qfu;
		int temps_depart;
		String temps_limite;
		String trajectoire = " ";
		
		/** Ouverture du fichier */
		try {
			fe = new BufferedReader(new FileReader(nom_fichier_trafic));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch bloc;
			System.out.println("Erreur au niveau de l'ouverture du fichier "+nom_fichier_trafic);
			e.printStackTrace();
		}
		
		/** Lecture du fichier
		 * 	fonction : separation des differentes informations d'un vol.
		 */
		try {
			while((tampon = fe.readLine()) != null) {
				Scanner s = new Scanner(tampon);
				s.useDelimiter(" ");
				type = s.next();
				identifiant = s.next();
				categorie =	s.next();
				point_depart = s.next();
				qfu = s.next();
				temps_depart = s.nextInt();
				temps_limite = s.next();
				s.useDelimiter("\n");
				trajectoire = s.next();
				trafic.get_liste_vols().add(new Vol(type, identifiant, categorie, point_depart, qfu, temps_depart, temps_limite, trajectoire));
				s.close();
			}				
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("Erreur au niveau de la lecture du fichier "+nom_fichier_trafic);
			e1.printStackTrace();
		}
		
		/** Fermeture du fichier */
		try {
			fe.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur au niveau de la fermeture du fichier "+nom_fichier_trafic);
			e.printStackTrace();
		}
	}


	/* TEST */
	
	/** test_programme_trafic **/
	/** fonction : tester le chargement du fichier texte **/
	/**				tester la recuperation du trafic de la plateforme aeroportuaire **/
	public void test_programme_trafic(Trafic trafic) {
		trafic.print_liste_vols();
	}
	
	/** calcul_nombre_vols
	 * fonction : calculer le nombre de vols de la plateforme aeroportuaire
	 */
	public void calcul_nombre_vols(){
		String nom_fichier_trafic = "trafic.txt";
		/** Declaration des variables locales */
		BufferedReader fe = null;
		
		/** Ouverture du fichier */
		try {
			fe = new BufferedReader(new FileReader(nom_fichier_trafic));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch bloc;
			System.out.println("Erreur au niveau de l'ouverture du fichier "+nom_fichier_trafic);
			e.printStackTrace();
		}
		
		/** Lecture du fichier */
		try {
			while((fe.readLine()) != null) {	
 				nombre_vols++;
			}				
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("Erreur au niveau de la lecture du fichier "+nom_fichier_trafic);
			e1.printStackTrace();
		}
		
		/** Fermeture du fichier */
		try {
			fe.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur au niveau de la fermeture du fichier "+nom_fichier_trafic);
			e.printStackTrace();
		}
	}
}
