import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class RegisterScreen implements ActionListener {

	JFrame window;
	
	JLabel usernameLabel;
	JTextField usernameTextField;
	
	JLabel passwordLabel;
	JPasswordField passwordField;
	
	JLabel confirmPasswordLabel;
	JPasswordField confirmPasswordField;
	
	JLabel emailLabel;
	JTextField emailTextField;
	
	JButton registerButton;
	JButton loginButton;
	JButton exitButton;

	
	Font labelFont = new Font("Arial", Font.PLAIN, 20);
	Font buttonFont = new Font("Arial", Font.PLAIN, 15);
	
	
	public RegisterScreen() {
		initialize();
	}
	
	private void initialize() {
		
		//Initialize window
		window = new JFrame();
		
		window.setTitle("Registration page");
		window.setSize(347, 350);
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
		
		//Initialize Confirm Password Area
		confirmPasswordLabel = new JLabel("<html>Confirm </br>Password</html>");
		confirmPasswordField = new JPasswordField();
		
		confirmPasswordLabel.setFont(labelFont);
		confirmPasswordLabel.setForeground(Color.BLACK);
		confirmPasswordLabel.setBounds(25, 131, 100, 50);
		
		confirmPasswordField.setBounds(130, 142, 150, 30);
		confirmPasswordField.setFont(new Font(usernameTextField.getFont().getFontName(), Font.PLAIN, 15));
		confirmPasswordField.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		
		
		window.add(confirmPasswordField);
		window.add(confirmPasswordLabel);
		
		//Initialize Email Area
		emailLabel = new JLabel("E-mail");
		emailTextField = new JTextField();
		
		emailLabel.setFont(labelFont);
		emailLabel.setForeground(Color.BLACK);
		emailLabel.setBounds(25, 195, 100, 50);
		
		emailTextField.setBounds(130, 204, 150, 30);
		emailTextField.setFont(new Font(usernameTextField.getFont().getFontName(), Font.PLAIN, 15));
		emailTextField.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		
		window.add(emailLabel);
		window.add(emailTextField);
		
		//Initialize Buttons
		registerButton = new JButton("Register");
		exitButton = new JButton("Exit");
		loginButton = new JButton("Login");
				
		exitButton.setBounds(20, 265, 80, 30);
		exitButton.setFont(buttonFont);
		exitButton.setFocusable(false);
		exitButton.addActionListener(this);
				
		loginButton.setBounds(110, 265, 80, 30);
		loginButton.setFont(buttonFont);
		loginButton.setFocusable(false);
		loginButton.addActionListener(this);
				
		registerButton.setBounds(230, 265, 90,30);
		registerButton.setFont(buttonFont);
		registerButton.setFocusable(false);
		registerButton.addActionListener(this);
				
				
		window.add(registerButton);
		window.add(exitButton);
		window.add(loginButton);
				
		
		
	}

	//Handle Events
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Open LoginScreen if login button is pressed
		//Close current RegisterScreen
		if(e.getSource() == loginButton) {
			new LoginScreen();
			window.dispose();
		}
		
		//Exit Application if exit button is pressed
		if(e.getSource() == exitButton){
			System.exit(0);
		}
		
		
		if(e.getSource() == registerButton) {
			boolean emailCorrect = false;
			boolean passwordsMatch = false;
			boolean usernameAvailable = false;
			
			//Check if email is valid
			if(emailTextField.getText().contains("@") && emailTextField.getText().contains(".")) {
				emailCorrect = true;	
			}
			else {
				emailCorrect = false;
				JOptionPane.showMessageDialog(window, "Invalid E-mail! Please try again.");
			}
			
			//Check if passwords match
			if(new String(passwordField.getPassword()).equals(new String(confirmPasswordField.getPassword()))) {
				passwordsMatch = true;
			}
			else {
				JOptionPane.showMessageDialog(window, "Passwords don't match! Please try again.");
				passwordsMatch = false;
			}
			
			//Check if username already exists
			try {
				if(UserDetails.checkUsername(usernameTextField.getText())) {
					JOptionPane.showMessageDialog(window, "Username already exists! Please try again.");
					usernameAvailable = false;
				}
				else {
					usernameAvailable = true;
				}
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
			
			//If Email is valid, both passwords match and the username doesn't exist, then add user to database, open loginscreen
			//and close current RegisterScreen
			if(emailCorrect && passwordsMatch && usernameAvailable) {
				UserDetails.addUser(usernameTextField.getText(), new String(passwordField.getPassword()), emailTextField.getText());
				newLoginScreen(usernameTextField.getText(), new String(passwordField.getPassword()));
				window.dispose();
			}
				
			
		}
		
	}
	
	//Open new LoginScreen with new registered data already entered
	private void newLoginScreen(String user, String password) {
		new LoginScreen(user, password);
	}

	

}
