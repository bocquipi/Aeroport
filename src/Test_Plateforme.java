/* Projet JAVA         */
/* Programme principal */
/* author :            */

import javax.swing.SwingUtilities;
//import javax.swing.UIManager;
//import javax.swing.plaf.metal.DefaultMetalTheme;
//import javax.swing.plaf.metal.MetalLookAndFeel;
//import javax.swing.plaf.nimbus.NimbusLookAndFeel;
//import javax.swing.plaf.synth.SynthLookAndFeel;

public class Test_Plateforme {

	public static void main(String[] args) {
		try {
			
			/** Look & Feel (Style de l'interface) **/
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			//UIManager.setLookAndFeel(new MetalLookAndFeel());
			//UIManager.setLookAndFeel(new NimbusLookAndFeel());
			//UIManager.setLookAndFeel(new SynthLookAndFeel());
			
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