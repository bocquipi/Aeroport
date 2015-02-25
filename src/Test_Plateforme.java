/** Projet JAVA         
 * Programme principal 
 * @author 	Alexis PAPIN
 * 			Cedric CORNETTE
 * 			Pierre BOCQUIER
 */

/** Lancement de la plateforme aeroportuaire.
 *
 */
import javax.swing.SwingUtilities;

public class Test_Plateforme {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Plateforme();
			}
		});
	}
}