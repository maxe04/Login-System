import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class WelcomeScreen {

	JFrame window;
	JLabel welcomeLabel;
	
	public WelcomeScreen() {
		initialize();
	}
	
	private void initialize() {
		
		//Initialize WelcomeScreen window
		window = new JFrame();
		window.setVisible(true);
		window.setLocationRelativeTo(null);
		window.setSize(500,500);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("My App");
		window.setLayout(null);
	
		//Initialize Welcome Label
		welcomeLabel = new JLabel("WELCOME!");
		welcomeLabel.setFont(new Font("Arial", Font.BOLD,80));
		welcomeLabel.setBounds(25,10,500,200);
	
		window.add(welcomeLabel);
	
	}
	
	
}
