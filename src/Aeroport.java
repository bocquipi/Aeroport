/** Projet JAVA         */
/** Modele              */
/** @author :           */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Aeroport {
	
	/** Declaration des variables privees
	 * 
	 * @see Plateforme
	 * @see Point
	 * @see Line
	 * @see Runway
	 */
	private Plateforme plateforme;
	private String nom_aeroport;
	private ArrayList<Point> points; 
	private ArrayList<Line> lines;
	private ArrayList<Runway> runways;
	private String nom_fichier_aeroport;
	private Trafic trafic;
	
	/* utilisation du pattern Singleton */
	
	/** Constructeur de la classe Aeroport
	 * 
	 * @param plateforme
	 * 		creation de l'aeroport a partir des informations du fichier lfpg.
	 */
	public Aeroport(Plateforme plateforme){
		this.plateforme = plateforme;
		set_nom_aeroport("inconnu");
		points = new ArrayList<Point>();
		lines = new ArrayList<Line>();
		runways = new ArrayList<Runway>();
		set_nom_fichier_aeroport("inconnu");
		trafic = new Trafic(this.plateforme);
	}

	/** Getter de nom_aeroport
	 * 
	 * @return le nom de l'aeroport.
	 */
	public String get_nom_aeroport() {
		return nom_aeroport;
	}

	/** Setter de nom_aeroport
	 * 
	 * @param nom_aeroport
	 * 		rentre le nouveau nom de l'aeroport.
	 */
	public void set_nom_aeroport(String nom_aeroport) {
		this.nom_aeroport = nom_aeroport;
	}
	
	/** Getter de points
	 * 
	 * @return les points de l'aeroport.
	 */
	public ArrayList<Point> get_points(){
		return points;
	}
	
	/** Getter de lines
	 * 
	 * @return les lines de l'aeroport.
	 */
	public ArrayList<Line> get_lines(){
		return lines;
	}
	
	/** Getter de runways
	 * 
	 * @return les runways de l'aeroport.
	 */
	public ArrayList<Runway> get_runways(){
		return runways;
	}
	
	/** Getter de nom_fichier_aeroport
	 * 
	 * @return le nom du fichier contenant les informations sur l'aeroport.
	 */
	public String get_nom_fichier_aeroport() {
		return nom_fichier_aeroport;
	}
	
	/** Setter de nom_fichier_aeroport
	 * 
	 * @param nom_fichier_aeroport
	 * 		rentre le nom du fichier contenant les informations sur l'aeroport.
	 */
	public void set_nom_fichier_aeroport(String nom_fichier_aeroport) {
		this.nom_fichier_aeroport = nom_fichier_aeroport;
	}

	/** Getter de trafic
	 * 
	 * @return le trafic present sur l'aeroport.
	 */
	public Trafic get_trafic() {
		return trafic;
	}
	
	/** Setter de trafic
	 * 
	 * @param trafic
	 * 		rentre un nouveau trafic pour l'aeroport.
	 */
	public void set_trafic(Trafic trafic) {
		this.trafic = trafic;
	}
	
	/** add_point 
	 * fonction : ajouter un point
	 * 
	 * @see Point
	 * @param point
	 * 		ajout d'un point a l'arraylist points de type Point.
	 */
	public void add_point(Point point){
		points.add(point);
	}
	
	/** rechercher_point
	 * fonction : rechercher un point
	 * 
	 * @see Point
	 * @param nom_point
	 * 		rentre le nom du point a rechercher.
	 */
	public Point rechercher_point(String nom_point) {
		Point point = null;
		for(Point point_recherche : points) {
			if(point_recherche.equals(nom_point)) {
				point = point_recherche;
			}
		}
		return point;
	}
	
	/** add_line
	 * fonction : ajouter une line
	 * 
	 * @see Line
	 * @param line
	 * 		ajout d'une line a l'arraylist lines de type Line.
	 */
	public void add_line(Line line){
		lines.add(line);
	}
	
	/** add_runway
	 * fonction : ajouter un runway
	 * 
	 * @see Runway
	 * @param Runway
	 * 		ajout d'un runway a l'arraylist runways de type Runway.
	 */
	public void add_runway(Runway runway){
		runways.add(runway);
	}
	
	/** charger_fichier_aeroport
	 * fonction : chargement du fichier texte
	 *				creation de la plateforme aeroportuaire
	 *
	 *@param aeroport
	 *		
	 */
	public void charger_fichier_aeroport(Aeroport aeroport) {
		
		/* Declaration des variables locales */
		BufferedReader fe = null;
		String tampon;
		String type;
		Point point;
		Line line;
		Runway runway;
		
		/** Ouverture du fichier */
		try {
			fe = new BufferedReader(new FileReader(nom_fichier_aeroport));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch bloc;
			System.out.println("Erreur au niveau de l'ouverture du fichier "+nom_fichier_aeroport);
			e.printStackTrace();
		}
				
		/** Lecture du fichier */
		try {
			while((tampon = fe.readLine()) != null) {
				Scanner s = new Scanner(tampon);
				s.useDelimiter(" ");
				/* Informations de la plateforme aeroportuaire */
				type = s.next();
				/** Aeroport 
				 *
				 *@see Aeroport
				 */
				if(type.length() != 1) {
					aeroport.set_nom_aeroport(type);
				}
				/** Point
				 * 
				 * @see Point
				 */
				if(type.equals("P")) {
					point = new Point(s.next(), s.nextInt(), s.next());
					aeroport.add_point(point);
				}
				/** Line
				 * 
				 * @see Line
				 */
				if(type.equals("L")) {
					line = new Line(s.next(), s.nextInt(), s.next().charAt(0), s.next().charAt(0), s.next());
					aeroport.add_line(line);
				}
				/** Runway
				 * 
				 * @see Runway
				 */
				if(type.equals("R")) {
					runway = new Runway(s.next(), s.next(), s.next(), s.next(), aeroport, s.next());
					aeroport.add_runway(runway);
				}
			    s.close();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("Erreur au niveau de la lecture du fichier "+nom_fichier_aeroport);
			e1.printStackTrace();
		}
				
		/** Fermeture du fichier */
		try {
			fe.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur au niveau de la fermeture du fichier "+nom_fichier_aeroport);
			e.printStackTrace();
		}
	}
	
	/** calcul_max_min_coordonnees 
	 * fonction : calculer le x_max de la plateforme aeroportuaire
	 * 
	 * @see Coordonnees
	 * @param parametre
	 */
	public int calcul_max_min_coordonnees(String parametre) {
		
		/** Declaration des variables locales */
		int max_x = 0;
		int min_x = 0;
		int max_y = 0;
		int min_y = 0;
		
		/** Points
		 * 
		 * @see Point
		 * @see Coordonnees
		 */
		for(Point p : points){
			if(p.get_coordonnees_point().getX() > max_x){
				max_x = p.get_coordonnees_point().getX();
			}
			if(p.get_coordonnees_point().getX() < min_x){
				min_x = p.get_coordonnees_point().getX();
			}
			if(p.get_coordonnees_point().getY() > max_y){
				max_y = p.get_coordonnees_point().getY();
			}
			if(p.get_coordonnees_point().getY() < min_y){
				min_y = p.get_coordonnees_point().getY();
			}
		}
		
		/** Lines
		 * 
		 * @see Line
		 * @see Coordonnees
		 */
		for(Line l : lines){
			for(Coordonnees c : l.get_coordonnees_line()){
				if(c.getX() > max_x){
					max_x = c.getX();
				}
				if(c.getX() < min_x){
					min_x = c.getX();
				}
				if(c.getY() > max_y){
					max_y = c.getY();
				}
				if(c.getY() < min_y){
					min_y = c.getY();
				}
			}
		}
		
		/** Runways
		 * 
		 * @see Runway
		 * @see Coordonnees
		 */
		for(Runway r : runways){
			for(Coordonnees c : r.get_coordonnees_extremites()){
				if(c.getX() > max_x){
					max_x = c.getX();
				}
				if(c.getX() < min_x){
					min_x = c.getX();
				}
				if(c.getY() > max_y){
					max_y = c.getY();
				}
				if(c.getY() < min_y){
					min_y = c.getY();
				}
			}
		}
		
		/** Choix du parametre de retour
		 * 
		 */
		if(parametre.equals("max_x")){
			return max_x;
		}
		if(parametre.equals("min_x")){
			return min_x;
		}
		if(parametre.equals("max_y")){
			return max_y;
		}
		else {
			return min_y;
		}
	}
	
	
	/* TEST */
	
	/** test_programme_aeroport
	 * fonction : tester le chargement du fichier texte 
	 *				tester la creation de la plateforme aeroportuaire
	 * 
	 * @param aeroport
	 */
	public void test_programme_aeroport(Aeroport aeroport) {
		
		System.out.println(aeroport.get_nom_aeroport());
		for(Point point : aeroport.get_points()){
			System.out.println(point.get_nom_point());
		}
		for(Line line : aeroport.get_lines()){
			System.out.println(line.get_nom_line());
		}
		for(Runway runway : aeroport.get_runways()){
			System.out.println(runway.get_nom_runway());
		}
	}
	
	/** test_valeur_coordonnees_aeroport
	 * fonction : calculer les coordonnees min et max de la plateforme aeropportuaire
	 * 
	 */
	public void test_valeur_coordonnees_aeroport() {
		
		/** Points
		 * 
		 * @see Point
		 * @see Coordonnees
		 */
		int max_x = 0;
		int min_x = 0;
		int max_y = 0;
		int min_y = 0;
		System.out.println("Points :");
		for(Point p : points){
			if(p.get_coordonnees_point().getX() > max_x){
				max_x = p.get_coordonnees_point().getX();
			}
			if(p.get_coordonnees_point().getX() < min_x){
				min_x = p.get_coordonnees_point().getX();
			}
			if(p.get_coordonnees_point().getY() > max_y){
				max_y = p.get_coordonnees_point().getY();
			}
			if(p.get_coordonnees_point().getY() < min_y){
				min_y = p.get_coordonnees_point().getY();
			}
		}
		System.out.println("Maximum x = " + max_x);
		System.out.println("Minimum x = " + min_x);
		System.out.println("Maximum y = " + max_y);
		System.out.println("Minimum y = " + min_y);
		
		/** Lines
		 * 
		 * @see Line
		 * @see Coordonnees
		 */
		max_x = 0;
		min_x = 0;
		max_y = 0;
		min_y = 0;
		System.out.println("Lines :");
		for(Line l : lines){
			for(Coordonnees c : l.get_coordonnees_line()){
				if(c.getX() > max_x){
					max_x = c.getX();
				}
				if(c.getX() < min_x){
					min_x = c.getX();
				}
				if(c.getY() > max_y){
					max_y = c.getY();
				}
				if(c.getY() < min_y){
					min_y = c.getY();
				}
			}
		}
		System.out.println("Maximum x = " + max_x);
		System.out.println("Minimum x = " + min_x);
		System.out.println("Maximum y = " + max_y);
		System.out.println("Minimum y = " + min_y);
		
		/** Runways
		 * 
		 * @see Runways
		 * @see Coordonnees
		 */
		max_x = 0;
		min_x = 0;
		max_y = 0;
		min_y = 0;
		System.out.println("Runways :");
		for(Runway r : runways){
			for(Coordonnees c : r.get_coordonnees_extremites()){
				if(c.getX() > max_x){
					max_x = c.getX();
				}
				if(c.getX() < min_x){
					min_x = c.getX();
				}
				if(c.getY() > max_y){
					max_y = c.getY();
				}
				if(c.getY() < min_y){
					min_y = c.getY();
				}
			}
		}
		System.out.println("Maximum x = " + max_x);
		System.out.println("Minimum x = " + min_x);
		System.out.println("Maximum y = " + max_y);
		System.out.println("Minimum y = " + min_y);
	}
}
