/** Projet JAVA             */
/** Programme principal     */
/** @author : PB - CC - AP  */

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Test_Plateforme {

	public static void main(String[] args) {
		try {
			
			/** Look & Feel (Style de l'interface) **/
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new Plateforme();
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}
}