import javax.swing.SwingUtilities;



public class Launcher {

	//Main Method
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new LoginScreen();
			
			}
			
		});
		
		
	
	}

}
