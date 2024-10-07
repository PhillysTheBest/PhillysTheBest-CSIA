package p2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class LoginScreen extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField usernameTextField;
	private JTextField userIDtextField;
	private JTextField emailTextField;
	private User currentUser; 
	private JTextField newUsernametextField;
	private JPasswordField newPasswordField;
	private boolean rememberMe = false;
	
	 private void saveLoginDetails(String username, String password, String userID) {
	        try {
	            BufferedWriter writer = new BufferedWriter(new FileWriter("SavedLoginDetails.txt"));
	            writer.write(username + "," + password + "," + userID);
	            writer.newLine(); 
	            writer.close();            
	            System.out.println("Login details saved successfully!");
	        } catch (IOException ex) {
	            ex.printStackTrace();           
	            System.out.println("Failed to save login details.");
	        }
	    }

	public LoginScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 586, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel CardManagerLabel = new JLabel("Card Manager");
		CardManagerLabel.setBounds(0, 0, 129, 51);
		CardManagerLabel.setOpaque(true);
		CardManagerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CardManagerLabel.setForeground(Color.WHITE);
		CardManagerLabel.setBackground(new Color(128, 0, 255));
		CardManagerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
	            dispose();  
	            CardManager cardManagerFrame = new CardManager();
	            cardManagerFrame.setSize(580, 376);
	            cardManagerFrame.setLocationRelativeTo(null);
	            cardManagerFrame.setResizable(false);
	            cardManagerFrame.setVisible(true);	            
	        }
            public void mouseEntered(MouseEvent e) {
				CardManagerLabel.setBackground(new Color(211,211,211));
				CardManagerLabel.setOpaque(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	CardManagerLabel.setBackground(new Color(128, 0, 255));
            	CardManagerLabel.setOpaque(true);
            }
		});
		contentPane.setLayout(null);
		contentPane.add(CardManagerLabel);
		
		JLabel lbllogin = new JLabel("Login");
		lbllogin.setBounds(134, 56, 134, 31);
		lbllogin.setOpaque(true);
		lbllogin.setHorizontalAlignment(SwingConstants.CENTER);
		lbllogin.setForeground(Color.WHITE);
		lbllogin.setBackground(new Color(128, 0, 255));
		contentPane.add(lbllogin);
		
		JLabel lblsignin = new JLabel("Create new acccount");
		lblsignin.setBounds(407, 56, 158, 31);
		lblsignin.setOpaque(true);
		lblsignin.setHorizontalAlignment(SwingConstants.CENTER);
		lblsignin.setForeground(Color.WHITE);
		lblsignin.setBackground(new Color(128, 0, 255));
		contentPane.add(lblsignin);
		
		JPanel loginDetailpanel = new JPanel();
		loginDetailpanel.setBounds(5, 92, 124, 240);
		loginDetailpanel.setLayout(null);
		contentPane.add(loginDetailpanel);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(10, 11, 114, 14);
		loginDetailpanel.add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 54, 104, 14);
		loginDetailpanel.add(passwordLabel);
		
		JLabel userIDLabel = new JLabel("UserID");
		userIDLabel.setBounds(10, 94, 114, 14);
		loginDetailpanel.add(userIDLabel);
		
		JRadioButton RememberMebutton = new JRadioButton("Remember me");
		RememberMebutton.setBackground(new Color(128, 0, 255));
		RememberMebutton.setForeground(new Color(255, 255, 255));
		RememberMebutton.setBounds(0, 143, 109, 23);
		loginDetailpanel.add(RememberMebutton);

		RememberMebutton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if (RememberMebutton.isSelected()) {
		            loadAndSetSavedLoginDetails();
		        } else {
		            clearLoginFields();
		        }
		    }

		    private void loadAndSetSavedLoginDetails() {
		        String savedUsername = getUsernameFromSavedDetails();
		        if (savedUsername != null) {	        	
		            User savedUser = User.loadSavedLoginDetails(savedUsername);
		            if (savedUser != null) {
		            	rememberMe=true;
		                usernameTextField.setText(savedUser.getUsername());
		                passwordField.setText(savedUser.getPassword());
		                userIDtextField.setText(savedUser.getUserID());
		            }
		        }
		    }

		    private String getUsernameFromSavedDetails() {
		        try {
		            BufferedReader reader = new BufferedReader(new FileReader("SavedLoginDetails.txt"));
		            String line = reader.readLine();
		            if (line != null) {
		                String[] parts = line.split(",");
		                if (parts.length >= 3) {  
		                    reader.close();
		                    return parts[0]; 
		                }
		            }
		            reader.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		        return null; 
		    }

		    private void clearLoginFields() {
		        usernameTextField.setText("");
		        passwordField.setText("");
		        userIDtextField.setText("");
		    }
		});

		
		
		JPanel Loginpanel = new JPanel();
		Loginpanel.setBounds(134, 92, 134, 240);
		Loginpanel.setLayout(null);
		contentPane.add(Loginpanel);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(128, 128, 128));
		passwordField.setBounds(0, 48, 107, 20);
		Loginpanel.add(passwordField);
		
		usernameTextField = new JTextField();
		usernameTextField.setBackground(new Color(128, 128, 128));
		usernameTextField.setBounds(0, 11, 107, 20);
		Loginpanel.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		userIDtextField = new JTextField();
		userIDtextField.setColumns(10);
		userIDtextField.setBackground(Color.GRAY);
		userIDtextField.setBounds(0, 93, 107, 20);
		Loginpanel.add(userIDtextField);
		
		JButton continueButton = new JButton("Next");
		continueButton.setHorizontalAlignment(SwingConstants.LEFT);
		continueButton.setBounds(0, 134, 118, 34);
		Loginpanel.add(continueButton);
		continueButton.setForeground(new Color(255, 255, 255));
		continueButton.setBackground(new Color(128, 0, 255));
		
		continueButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {	        
		        String username = usernameTextField.getText();
		        String password = new String(passwordField.getPassword());
		        String userID = userIDtextField.getText();
		        String email = null;
		        // Create a new User instance with the provided details.
		        currentUser = new User(username, password, userID, email);
		        // Check if the provided user details are valid using the isValidUser method.
		        if (currentUser.isValidUser()) {
		        	// If valid, set the current user and proceed to the main menu.
		        	User.setCurrentUser(currentUser);	
		        	// Optionally, save login details if "Remember Me" is selected.
		            if (RememberMebutton.isSelected()) {
		            	rememberMe = true;
		                saveLoginDetails(username, password, userID);
		            }
		            dispose();  
		            MainMenu MainMenuFrame = new MainMenu();
		            MainMenuFrame.setSize(580, 376);
		            MainMenuFrame.setLocationRelativeTo(null);
		            MainMenuFrame.setResizable(false);
		            MainMenuFrame.setVisible(true);		            
		        } else {
		        	// If user details are invalid, increment the failed login attempts.
		            User.incrementFailedAttempts();
		             // If the maximum attempts are reached, display an account locked message and exit.
		            if (User.getFailedAttempts() >= 3) {
		                SwingUtilities.invokeLater(() -> {
		                    JOptionPane.showMessageDialog(LoginScreen.this, "Account locked.");
		                    System.exit(0); 
		                });
		            } else {
		            	// Display an error message for invalid credentials
		                System.out.println("Invalid credentials.");
		                SwingUtilities.invokeLater(() -> {
		                    JOptionPane.showMessageDialog(LoginScreen.this, "Invalid credentials. Please try again.");
		                });
		            }
		        }
		    }
		});

		
		JPanel enterEmailpanel = new JPanel();
		enterEmailpanel.setBounds(273, 92, 129, 240);
		enterEmailpanel.setLayout(null);
		contentPane.add(enterEmailpanel);
		
		JLabel emailLabel = new JLabel("Enter email");
		emailLabel.setBounds(0, 11, 129, 23);
		enterEmailpanel.add(emailLabel);
		
		JLabel lblNewUsername = new JLabel("New username");
		lblNewUsername.setBounds(0, 54, 129, 14);
		enterEmailpanel.add(lblNewUsername);
		
		JLabel lblNewPassword = new JLabel("New password");
		lblNewPassword.setBounds(0, 96, 129, 14);
		enterEmailpanel.add(lblNewPassword);
		
		JPanel newAccountpanel = new JPanel();
		newAccountpanel.setBounds(407, 92, 158, 240);
		newAccountpanel.setLayout(null);
		contentPane.add(newAccountpanel);
		
		JCheckBox TermsNewCheckBox = new JCheckBox("I accept the T&C");
		TermsNewCheckBox.setHorizontalAlignment(SwingConstants.LEFT);
		TermsNewCheckBox.setBackground(new Color(128, 0, 255));
		TermsNewCheckBox.setForeground(new Color(255, 255, 255));
		TermsNewCheckBox.setBounds(0, 205, 158, 34);
		newAccountpanel.add(TermsNewCheckBox);
		
		emailTextField = new JTextField();
		emailTextField.setColumns(10);
		emailTextField.setBackground(Color.GRAY);
		emailTextField.setBounds(0, 11, 107, 20);
		newAccountpanel.add(emailTextField);
		
		JButton btnFinish = new JButton("Finish");
		btnFinish.setForeground(Color.WHITE);
		btnFinish.setBackground(new Color(128, 0, 255));
		btnFinish.setBounds(0, 138, 118, 34);
		newAccountpanel.add(btnFinish);
		btnFinish.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {	       
		        String newEmail = emailTextField.getText();
		        String newUsername = newUsernametextField.getText();
		        String newPassword = new String(newPasswordField.getPassword());
		        User.createAndWriteNewUser(newUsername, newPassword, newEmail);
		    }
		});
		
		newUsernametextField = new JTextField();
		newUsernametextField.setColumns(10);
		newUsernametextField.setBackground(Color.GRAY);
		newUsernametextField.setBounds(0, 58, 107, 20);
		newAccountpanel.add(newUsernametextField);
		
		newPasswordField = new JPasswordField();
		newPasswordField.setBackground(Color.GRAY);
		newPasswordField.setBounds(0, 96, 107, 20);
		newAccountpanel.add(newPasswordField);
			
		
}
}
