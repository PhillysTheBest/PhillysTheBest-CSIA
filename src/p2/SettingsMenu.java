package p2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SettingsMenu extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JTextField passwordField;
	private User currentUser;
	private JLabel MainMenuLabel;
	private JLabel PreferencesLabel;
	private JLabel myProfileLabel;

	public SettingsMenu(User currentUser) {
		this.currentUser = currentUser;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		MainMenuLabel = new JLabel("Main Menu");
        MainMenuLabel.setOpaque(true);
        MainMenuLabel.setForeground(new Color(255, 255, 255));
        MainMenuLabel.setBackground(new Color(128, 0, 255));
        MainMenuLabel.setHorizontalAlignment(SwingConstants.CENTER);
        MainMenuLabel.setBounds(0, 0, 129, 54);
        MainMenuLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose(); 
                MainMenu MainMenuFrame = new MainMenu();
                MainMenuFrame.setSize(580, 376);
                MainMenuFrame.setLocationRelativeTo(null);
                MainMenuFrame.setResizable(false);
                MainMenuFrame.setVisible(true);
            }

            public void mouseEntered(MouseEvent e) {
                MainMenuLabel.setBackground(new Color(211, 211, 211));
                MainMenuLabel.setOpaque(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                MainMenuLabel.setBackground(new Color(128, 0, 255));
                MainMenuLabel.setOpaque(true);
            }
        });
        contentPane.add(MainMenuLabel);
        
        myProfileLabel = new JLabel("My Profile");
        myProfileLabel.setOpaque(true);
        myProfileLabel.setForeground(new Color(255, 255, 255));
        myProfileLabel.setBackground(new Color(128, 0, 255));
        myProfileLabel.setHorizontalAlignment(SwingConstants.CENTER);
        myProfileLabel.setBounds(73, 81, 151, 44);
        contentPane.add(myProfileLabel);
        myProfileLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String newUsername = usernameField.getText();
                String newPassword = passwordField.getText();               
                User currentUser = User.getCurrentUser();
                if (currentUser != null) {
                    String oldUsername = currentUser.getUsername();
                    String oldPassword = currentUser.getPassword();                   
                    boolean accountUpdated = currentUser.updateAccountInfo(oldUsername, oldPassword, newUsername, newPassword);
                    generateProfileChangeConfirmationPDF();
                    if (accountUpdated) {

                        JOptionPane.showMessageDialog(SettingsMenu.this, "Account updated successfully!");
                    } else {
                    
                        JOptionPane.showMessageDialog(SettingsMenu.this, "Failed to update account.");
                    }
                } else {                   
                    JOptionPane.showMessageDialog(SettingsMenu.this, "Current user not set.");
                }
            }
            public void mouseEntered(MouseEvent e) {
            	myProfileLabel.setBackground(new Color(211, 211, 211));
            	myProfileLabel.setOpaque(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	myProfileLabel.setBackground(new Color(128, 0, 255));
            	myProfileLabel.setOpaque(true);
            }
        });
        
        PreferencesLabel = new JLabel("Preferences");
        PreferencesLabel.setOpaque(true);
        PreferencesLabel.setForeground(new Color(255, 255, 255));
        PreferencesLabel.setBackground(new Color(128, 0, 255));
        PreferencesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        PreferencesLabel.setBounds(363, 81, 151, 44);
        contentPane.add(PreferencesLabel);
        PreferencesLabel.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
        	
        }
        public void mouseEntered(MouseEvent e) {
        	PreferencesLabel.setBackground(new Color(211, 211, 211));
        	PreferencesLabel.setOpaque(true);
        }

        @Override
        public void mouseExited(MouseEvent e) {
        	PreferencesLabel.setBackground(new Color(128, 0, 255));
        	PreferencesLabel.setOpaque(true);
        }
        });
        JButton deleteAccountButton = new JButton("Delete Account");
        deleteAccountButton.setBackground(new Color(128, 0, 255));
        deleteAccountButton.setForeground(new Color(255, 255, 255));
        deleteAccountButton.setBounds(373, 209, 129, 35);
        contentPane.add(deleteAccountButton);
        deleteAccountButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	// Check if the current user is null
            if (User.getCurrentUser() != null) {
                User currentUser = User.getCurrentUser();
                boolean accountDeleted = currentUser.deleteAccount("Accounts.txt");               
                if (accountDeleted) {                   
                    User.setCurrentUser(null);    
                    generateAccountDeletionConfirmationPDF();
                    JOptionPane.showMessageDialog(SettingsMenu.this, "Account deleted successfully!");
                    dispose();
                    LoginScreen LoginScreenFrame = new LoginScreen();
                    LoginScreenFrame.setSize(580, 376);
                    LoginScreenFrame.setLocationRelativeTo(null);
                    LoginScreenFrame.setResizable(false);
                    LoginScreenFrame.setVisible(true);   
                } else {
                   
                    JOptionPane.showMessageDialog(SettingsMenu.this, "Failed to delete account.");
                }
            } else {               
                JOptionPane.showMessageDialog(SettingsMenu.this, "Current user not set.");
            }
        }
        });      
        usernameField = new JTextField();
        usernameField.setText("Enter new username");
        usernameField.setHorizontalAlignment(SwingConstants.CENTER);
        usernameField.setBounds(83, 146, 129, 35);
        contentPane.add(usernameField);
        usernameField.setColumns(10);
        
        passwordField = new JTextField();
        passwordField.setText("Enter new password");
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        passwordField.setColumns(10);
        passwordField.setBounds(83, 209, 129, 35);
        contentPane.add(passwordField);
        
        JButton changeAccountButton = new JButton("Change Account");
        changeAccountButton.setBackground(new Color(128, 0, 255));
        changeAccountButton.setForeground(new Color(255, 255, 255));
        changeAccountButton.setBounds(373, 146, 129, 35);
        changeAccountButton.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		dispose(); 
                LoginScreen LoginScreenFrame = new LoginScreen();
                LoginScreenFrame.setSize(580, 376);
                LoginScreenFrame.setLocationRelativeTo(null);
                LoginScreenFrame.setResizable(false);
                LoginScreenFrame.setVisible(true);
            }
        });
        contentPane.add(changeAccountButton);
	}
	private void generateProfileChangeConfirmationPDF() {
	    try {
	        Document document = new Document();
	        PdfWriter.getInstance(document, new FileOutputStream("profile_change_confirmation.pdf"));
	        document.open();
	        Paragraph title = new Paragraph("Profile Change Confirmation");
	        document.add(title);
	        Paragraph content = new Paragraph("You have successfully changed your profile details.");
	        document.add(content);
	        document.close();
	        System.out.println("Profile change confirmation PDF generated successfully.");
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Failed to generate profile change confirmation PDF.");
	    }
	}
	private void generateAccountDeletionConfirmationPDF() {
	    try {	    
	        Document document = new Document();
	        PdfWriter.getInstance(document, new FileOutputStream("account_deletion_confirmation.pdf"));
	        document.open();	      
	        Paragraph title = new Paragraph("Account Deletion Confirmation");
	        document.add(title);
	        Paragraph content = new Paragraph("Your account has been successfully deleted.");
	        document.add(content);
	        document.close();
	        System.out.println("Account deletion confirmation PDF generated successfully.");
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Failed to generate account deletion confirmation PDF.");
	    }
	}

}
