/**
 * 
 */
package spaceshooter.launch;

import javax.swing.JFrame;

import spaceshooter.model.Board;

/**
 * @author dey
 *
 */
public class Launch extends JFrame {
    private final static int DEFAULT_MAX_X = 600;
    private final static int DEFAULT_MAX_Y = 400;
    private final static String NAME_ON_TITLE = "SpaceShooter";
    
	public void launch() {
		add(new Board(DEFAULT_MAX_X, DEFAULT_MAX_Y));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(DEFAULT_MAX_X, DEFAULT_MAX_Y);
		setTitle(NAME_ON_TITLE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
        Launch launcher = new Launch();
        launcher.launch();
	}

}
