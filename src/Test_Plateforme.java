/* Projet JAVA         */
/* Programme principal */
/* author :            */

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