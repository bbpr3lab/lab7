package lab7;

import javax.swing.SwingUtilities;

public class Main {
	
	CaesarFrame frame;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Main(args).show());
	}
	
	Main(String[] args) {
		frame = new CaesarFrame();
		frame.pack();
	}
	
	void show() {
		frame.setVisible(true);
	}

}
