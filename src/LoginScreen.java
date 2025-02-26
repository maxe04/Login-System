import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class LoginScreen implements ActionListener{

	JFrame window;

	JLabel usernameLabel;
	JTextField usernameTextField;
	
	JLabel passwordLabel;
	JPasswordField passwordField;
	
	JLabel registerLabel;
	
	JButton loginButton;
	JButton resetButton;
	JButton exitButton;
	

	Font labelFont = new Font("Arial", Font.PLAIN, 20);
	Font buttonFont = new Font("Arial", Font.PLAIN, 15);
	
	//Default constructor
	public LoginScreen() {
		initialize();
	}
	
	//Constructor to use after user has registered
	public LoginScreen(String user, String password) {
		initialize();
		usernameTextField.setText(user);
		passwordField.setText(password);
		
	}
	
	
	private void initialize() {
		
		//Initialize LoginScreen window
		window = new JFrame();
		
		window.setTitle("Login To Your Account");
		window.setSize(347, 250);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.setLayout(null);
		
		//Initialize Username Area
		usernameLabel = new JLabel("Username");
		usernameTextField = new JTextField(1);
		
		usernameLabel.setForeground(Color.BLACK);
		usernameLabel.setBounds(25, 10, 100, 50);
		usernameLabel.setFont(labelFont);

		usernameTextField.setBounds(130, 18, 150, 30);
		usernameTextField.setFont(new Font(usernameTextField.getFont().getFontName(), Font.PLAIN, 15));
		usernameTextField.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		
		window.add(usernameLabel);
		window.add(usernameTextField);
		
		//Initialize Password Area
		passwordLabel = new JLabel("Password");
		passwordField = new JPasswordField();
		
		passwordLabel.setBounds(25, 70, 100, 50);
		passwordLabel.setFont(labelFont);
		
		passwordField.setBounds(130, 80, 150, 30);
		passwordField.setFont(new Font(usernameTextField.getFont().getFontName(), Font.PLAIN, 15));
		passwordField.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		
		window.add(passwordLabel);
		window.add(passwordField);
		
		//Initialize Clickable Register Label
		registerLabel = new JLabel("<HTML><U>Not registered yet? Click here!</U></HTML>");
		
		registerLabel.setBounds(120, 110, 200, 30);
		registerLabel.setForeground(Color.BLUE);
		
			//Make registerLabel clickable
			//Open RegisterScreen on click
			//Animate registerLabel on hover
			registerLabel.addMouseListener(new MouseAdapter()  
			{  
				public void mouseClicked(MouseEvent e)  {  
					newRegisterScreen();
					window.dispose();
				}  
				public void mouseEntered(MouseEvent e) {
					registerLabel.setForeground(registerLabel.getForeground().darker());
				}
				public void mouseExited(MouseEvent e) {
					registerLabel.setForeground(registerLabel.getForeground().brighter());
				}
		
			}); 
		
		window.add(registerLabel);
		
		//Initialize Buttons
		loginButton = new JButton("Login");
		exitButton = new JButton("Exit");
		resetButton = new JButton("Reset");
		
		exitButton.setBounds(20, 165, 80, 30);
		exitButton.setFont(buttonFont);
		exitButton.setFocusable(false);
		exitButton.addActionListener(this);
		
		resetButton.setBounds(110, 165, 80, 30);
		resetButton.setFont(buttonFont);
		resetButton.setFocusable(false);
		resetButton.addActionListener(this);
		
		loginButton.setBounds(230, 165, 80,30);
		loginButton.setFont(buttonFont);
		loginButton.setFocusable(false);
		loginButton.addActionListener(this);
		
		
		window.add(loginButton);
		window.add(exitButton);
		window.add(resetButton);
		
	
	}

	//Handle Events
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Close Application if exit button is pressed
		if(e.getSource() == exitButton) {
			System.exit(0);
		}
		
		//Reset password and username field if reset button is pressed
		if(e.getSource() == resetButton) {
			passwordField.setText("");
			usernameTextField.setText("");
			
		}
		
		//If username-password combo exists then login
		//Otherwise throw error message
		if(e.getSource() == loginButton) {
			
			try {
				//What happens if you log in successfully
				if(UserDetails.checkUsername(usernameTextField.getText()) && UserDetails.checkPassword(usernameTextField.getText(), new String(passwordField.getPassword()))) {
					newWelcomeScreen();
					System.out.println("logged in successfully");
					window.dispose(); 
							
				}
				//What happens if you don't log in successfully
				else if(usernameTextField.getText().isEmpty() || passwordField.getPassword().length <= 0) {
					JOptionPane.showMessageDialog(window, "Error: Username or Password empty! Please try again.");
				}
				else {
					JOptionPane.showMessageDialog(window, "Username or Password incorrect! Please try again.");
				}
			}		
				
			catch(SQLException e1) {
				e1.printStackTrace();
			}	
		}
			
			
	}
	
	//Open new Welcome Screen
	private void newWelcomeScreen() {
		new WelcomeScreen();
	}
	
	//Open new Register Screen
	private void newRegisterScreen() {
		new RegisterScreen();
	}
	
	
}
