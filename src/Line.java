import java.util.ArrayList;

public class Line {
	
	/** Declaration des variables privees **/
	//enum Type_line {PUSHBACK, TAXIWAY};
	private String nom_line;
	private int vitesse_roulage;
	//enum Categorie_line {H, M, L};
	private char categorie_line;
	//enum Direction_line {S, D};
	private char direction_line;
	private ArrayList<Coordonnees> coordonnees_line;
	
	/** Constructeur de la classe Line **/
	/* Constructeur sans parametres */
	public Line() {
		set_nom_line("inconnue");
		set_vitesse_roulage(0);
		set_categorie_line('U');
		set_direction_line('U');
	}
	/* Constructeur avec parametres */
	public Line(String nom_line, int vitesse_roulage, char categorie_line, char direction_line, String coordonnees) {
		Coordonnees coordonnees_line;
		String delimiter_coordonnees = ";";
		String delimiter_axes = ",";
		String[] tampon_coordonnees;
		String[] tampon_axes;
		set_nom_line(nom_line);
		set_vitesse_roulage(vitesse_roulage);
		set_categorie_line(categorie_line);
		set_direction_line(direction_line);
		this.coordonnees_line = new ArrayList<Coordonnees>();
		tampon_coordonnees = coordonnees.split(delimiter_coordonnees);
		for(String coord : tampon_coordonnees) {
			tampon_axes = coord.split(delimiter_axes);
			coordonnees_line = new Coordonnees(Integer.parseInt(tampon_axes[0]),Integer.parseInt(tampon_axes[1]));
			this.coordonnees_line.add(coordonnees_line);
		}
	}
	
	/** Getter/Setter de nom_line **/
	public String get_nom_line() {
		return nom_line;
	}
	
	public void set_nom_line(String nom_line) {
		this.nom_line = nom_line;
	}
	
	/** Getter/Setter de vitesse_roulage **/
	public int get_vitesse_roulage() {
		return vitesse_roulage;
	}
	
	public void set_vitesse_roulage(int vitesse_roulage) {
		this.vitesse_roulage = vitesse_roulage;
	}

	/** Getter/Setter de categorie_line **/
	public char get_categorie_line() {
		return categorie_line;
	}
	
	public void set_categorie_line(char categorie_line) {
		this.categorie_line = categorie_line;
	}
	
	/** Getter/Setter de direction_line **/
	public char get_direction_line() {
		return direction_line;
	}
	
	public void set_direction_line(char direction_line) {
		this.direction_line = direction_line;
	}
	
	/** Getter de coordonnees_line **/
	public ArrayList<Coordonnees> get_coordonnees_line() {
		return coordonnees_line;
	}
}
