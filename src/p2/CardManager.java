package p2;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CardManager extends JFrame {

	private JPanel contentPane;

	
	public CardManager() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lbllogin = new JLabel("Login");
		lbllogin.setBounds(424, 50, 140, 85);
		lbllogin.setOpaque(true);
		lbllogin.setHorizontalAlignment(SwingConstants.CENTER);
		lbllogin.setForeground(Color.WHITE);
		lbllogin.setBackground(new Color(128, 0, 255));
		lbllogin.setBackground(new Color(128, 0, 255));
		lbllogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
	            dispose();  
	            LoginScreen LoginScreenFrame = new LoginScreen();
	            LoginScreenFrame.setSize(580, 376);
	            LoginScreenFrame.setLocationRelativeTo(null);
	            LoginScreenFrame.setResizable(false);
	            LoginScreenFrame.setVisible(true);
			}
            public void mouseEntered(MouseEvent e) {
				lbllogin.setBackground(new Color(211,211,211));
				lbllogin.setOpaque(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	lbllogin.setBackground(new Color(128, 0, 255));
            	lbllogin.setOpaque(true);
            }
		});
		
		JLabel lblsignup = new JLabel("Sign up");
		lblsignup.setBounds(5, 50, 140, 85);
		lblsignup.setOpaque(true);
		lblsignup.setHorizontalAlignment(SwingConstants.CENTER);
		lblsignup.setForeground(Color.WHITE);
		lblsignup.setBackground(new Color(128, 0, 255));
		lblsignup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
	            dispose();  
	            LoginScreen LoginScreenFrame = new LoginScreen();
	            LoginScreenFrame.setSize(400, 350);
	            LoginScreenFrame.setLocationRelativeTo(null);
	            LoginScreenFrame.setResizable(false);
	            LoginScreenFrame.setVisible(true);
	            LoginScreenFrame.pack();
			}
            public void mouseEntered(MouseEvent e) {
				lblsignup.setBackground(new Color(211,211,211));
				lblsignup.setOpaque(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	lblsignup.setBackground(new Color(128, 0, 255));
            	lblsignup.setOpaque(true);
            }
		});
		contentPane.setLayout(null);
		
		JLabel lblIntroducingCardManager = new JLabel("Introducing Card Manager for windows 11");
		lblIntroducingCardManager.setBounds(155, 0, 259, 51);
		lblIntroducingCardManager.setOpaque(true);
		lblIntroducingCardManager.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroducingCardManager.setForeground(Color.WHITE);
		lblIntroducingCardManager.setBackground(new Color(128, 0, 255));
		contentPane.add(lblIntroducingCardManager);
		contentPane.add(lblsignup);
		contentPane.add(lbllogin);
		
		JPanel panel = new JPanel();
		panel.setBounds(212, 113, 202, 85);
		panel.setLayout(null);
		contentPane.add(panel);
		
		JLabel lblcardimage = new JLabel("");
		lblcardimage.setBounds(39, 11, 334, 97);
		panel.add(lblcardimage);
		lblcardimage.setIcon(new ImageIcon("C:\\Users\\Win10\\Downloads\\credit_card_icon_129105 (6).png"));
		
		JLabel lblrated = new JLabel("Rated 5 stars");
		lblrated.setBounds(0, 284, 128, 53);
		lblrated.setHorizontalAlignment(SwingConstants.CENTER);
		lblrated.setHorizontalAlignment(SwingConstants.CENTER);
		lblrated.setOpaque(true);
		lblrated.setBackground(new Color(128, 0, 255));
		lblrated.setForeground(new Color(255, 255, 255));
		contentPane.add(lblrated);
		
		JLabel lblendless = new JLabel("Endless opportunities with Card Manager");
		lblendless.setBounds(155, 284, 246, 53);
		lblendless.setHorizontalAlignment(SwingConstants.CENTER);
		lblendless.setOpaque(true);
		lblendless.setBackground(new Color(128, 0, 255));
		lblendless.setForeground(new Color(255, 255, 255));
		contentPane.add(lblendless);
		
		JLabel lbltransaction = new JLabel("No transaction fees");
		lbltransaction.setBounds(424, 285, 140, 50);
		lbltransaction.setHorizontalAlignment(SwingConstants.CENTER);
		lbltransaction.setOpaque(true);
		lbltransaction.setBackground(new Color(128, 0, 255));
		lbltransaction.setForeground(new Color(255, 255, 255));
		contentPane.add(lbltransaction);
	}

}
