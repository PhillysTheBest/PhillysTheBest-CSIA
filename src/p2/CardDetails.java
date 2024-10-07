package p2;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

public class CardDetails extends JFrame {

    private JPanel contentPane;

    public CardDetails(String cardNumber, String pinNumber) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 580, 376);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel CardNumberLabel = new JLabel("Card Number: " + cardNumber); 
        CardNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        CardNumberLabel.setBounds(268, 119, 229, 55);
        contentPane.add(CardNumberLabel);

        JLabel pinNumberLabel = new JLabel("PIN Number: " + pinNumber); 
        pinNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pinNumberLabel.setBounds(268, 164, 229, 55);
        contentPane.add(pinNumberLabel);
        
        JLabel BackLabel = new JLabel("Back");
        BackLabel.setOpaque(true);
        BackLabel.setForeground(new Color(255, 255, 255));
        BackLabel.setBackground(new Color(128, 0, 255));
        BackLabel.setHorizontalAlignment(SwingConstants.CENTER);
        BackLabel.setBounds(0, 0, 129, 54);
        BackLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose(); 
                DetailsMenu DetailsMenuFrame = new DetailsMenu();
                DetailsMenuFrame.setSize(580, 376);
                DetailsMenuFrame.setLocationRelativeTo(null);
                DetailsMenuFrame.setResizable(false);
                DetailsMenuFrame.setVisible(true);
            }
            public void mouseEntered(MouseEvent e) {
            	BackLabel.setBackground(new Color(211, 211, 211));
            	BackLabel.setOpaque(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	BackLabel.setBackground(new Color(128, 0, 255));
            	BackLabel.setOpaque(true);
            }
        });
        contentPane.add(BackLabel);
        
        JLabel creditCardLabel = new JLabel("");
        creditCardLabel.setIcon(new ImageIcon("C:\\Users\\Win10\\Downloads\\credit_card_icon_129105 (12).png"));
        creditCardLabel.setBounds(147, 87, 245, 151);
        contentPane.add(creditCardLabel);
    }
}