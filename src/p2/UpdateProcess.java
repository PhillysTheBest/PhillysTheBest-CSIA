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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class UpdateProcess extends JFrame {

	private JPanel contentPane;
	private JTextField txtEnterCardName;
	private JTextField txtEnterCardIssuer;
	private JTextField txtEnterCardNumber;
	private JTextField txtEnterCardType;
	private JTextField txtEnterCardScore;
	private JTextField txtEnterPin;
	private JTextField txtEnterTransactionDate;
	private JTextField txtEnterBalance;
	private JTextField txtEnterExpiryDate;
	private JTextField txtdeleteCard;
	private JButton UpdateButton;
	private JTextField txtUpdateBalance;
	private JTextField txtUpdateCreditScore;
	private JTextField cardNumberField;

	public UpdateProcess() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		JLabel BackLabel = new JLabel("Back");
        BackLabel.setOpaque(true);
        BackLabel.setForeground(new Color(255, 255, 255));
        BackLabel.setBackground(new Color(128, 0, 255));
        BackLabel.setHorizontalAlignment(SwingConstants.CENTER);
        BackLabel.setBounds(0, 0, 128, 51);
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
        contentPane.setLayout(null);
        contentPane.add(BackLabel);
		setContentPane(contentPane);
		
		txtEnterCardName = new JTextField();
		txtEnterCardName.setText("Enter Card Name");
		txtEnterCardName.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnterCardName.setBounds(0, 86, 128, 20);
		contentPane.add(txtEnterCardName);
		txtEnterCardName.setColumns(10);
		
		txtEnterCardIssuer = new JTextField();
		txtEnterCardIssuer.setText("Enter Card Issuer");
		txtEnterCardIssuer.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnterCardIssuer.setColumns(10);
		txtEnterCardIssuer.setBounds(234, 86, 108, 20);
		contentPane.add(txtEnterCardIssuer);
		
		txtEnterCardNumber = new JTextField();
		txtEnterCardNumber.setText("Enter Card Number");
		txtEnterCardNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnterCardNumber.setColumns(10);
		txtEnterCardNumber.setBounds(433, 86, 108, 20);
		contentPane.add(txtEnterCardNumber);
		
		txtEnterCardType = new JTextField();
		txtEnterCardType.setText("Enter Card Type");
		txtEnterCardType.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnterCardType.setColumns(10);
		txtEnterCardType.setBounds(0, 155, 128, 20);
		contentPane.add(txtEnterCardType);
		
		txtEnterCardScore = new JTextField();
		txtEnterCardScore.setText("Enter Credit Score");
		txtEnterCardScore.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnterCardScore.setColumns(10);
		txtEnterCardScore.setBounds(234, 155, 108, 20);
		contentPane.add(txtEnterCardScore);
		
		txtEnterPin = new JTextField();
		txtEnterPin.setText("Enter pin number");
		txtEnterPin.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnterPin.setColumns(10);
		txtEnterPin.setBounds(433, 155, 108, 20);
		contentPane.add(txtEnterPin);
		
		txtEnterTransactionDate = new JTextField();
		txtEnterTransactionDate.setText("Enter Transaction date");
		txtEnterTransactionDate.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnterTransactionDate.setColumns(10);
		txtEnterTransactionDate.setBounds(0, 219, 128, 20);
		contentPane.add(txtEnterTransactionDate);
		
		txtEnterBalance = new JTextField();
		txtEnterBalance.setText("Enter Balance");
		txtEnterBalance.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnterBalance.setColumns(10);
		txtEnterBalance.setBounds(234, 219, 108, 20);
		contentPane.add(txtEnterBalance);
		
		txtEnterExpiryDate = new JTextField();
		txtEnterExpiryDate.setText("Enter Expiry Date");
		txtEnterExpiryDate.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnterExpiryDate.setColumns(10);
		txtEnterExpiryDate.setBounds(433, 219, 108, 20);
		contentPane.add(txtEnterExpiryDate);
		
		JButton AddButton = new JButton("Add card");
		AddButton.setForeground(new Color(255, 255, 255));
		AddButton.setBackground(new Color(128, 255, 0));
		AddButton.setBounds(404, 11, 106, 51);
		AddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String cardName = txtEnterCardName.getText();
                    String cardIssuer = txtEnterCardIssuer.getText();
                    String cardNumber = txtEnterCardNumber.getText();
                    String cardType = txtEnterCardType.getText();
                    String creditScore = txtEnterCardScore.getText();
                    String pinNumber = txtEnterPin.getText();
                    String transactionDate = txtEnterTransactionDate.getText();
                    String balance = txtEnterBalance.getText();
                    String expiryDate = txtEnterExpiryDate.getText();
                    String freezeStatus = "Off"; 

                    
                    String cardDetails = cardName + "," + cardIssuer + "," + cardNumber + "," + cardType
                            + "," + creditScore + "," + pinNumber + "," + transactionDate + ","
                            + balance + "," + freezeStatus + "," + expiryDate; 

                    
                    BufferedWriter writer = new BufferedWriter(new FileWriter("Details.txt", true));
                    writer.write(cardDetails);
                    writer.newLine();
                    writer.close();

                    txtEnterCardName.setText("");
                    txtEnterCardIssuer.setText("");
                    txtEnterCardNumber.setText("");
                    txtEnterCardType.setText("");
                    txtEnterCardScore.setText("");
                    txtEnterPin.setText("");
                    txtEnterTransactionDate.setText("");
                    txtEnterBalance.setText("");
                    txtEnterExpiryDate.setText("");
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "An error occurred while adding the card.");
                }
            }
        });
		contentPane.add(AddButton);
		
		JButton DeleteButton = new JButton("Delete card");
		DeleteButton.setBackground(new Color(255, 0, 0));
		DeleteButton.setForeground(new Color(255, 255, 255));
		DeleteButton.setBounds(0, 273, 101, 53);
		DeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String cardNumberToDelete = txtdeleteCard.getText();                  
                    BufferedReader reader = new BufferedReader(new FileReader("Details.txt"));
                    StringBuilder updatedFile = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (!parts[2].equals(cardNumberToDelete)) {
                            updatedFile.append(line).append("\n");
                        }
                    }
                    reader.close();
                   
                    BufferedWriter writer = new BufferedWriter(new FileWriter("Details.txt"));
                    writer.write(updatedFile.toString());
                    writer.close();
                 
                    txtdeleteCard.setText("");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "An error occurred while deleting the card.");
                }
            }
        });
		contentPane.add(DeleteButton);
		
		txtdeleteCard = new JTextField();
		txtdeleteCard.setBackground(new Color(255, 0, 0));
		txtdeleteCard.setText("Enter Card Number");
		txtdeleteCard.setHorizontalAlignment(SwingConstants.CENTER);
		txtdeleteCard.setColumns(10);
		txtdeleteCard.setBounds(109, 289, 108, 20);
		contentPane.add(txtdeleteCard);
		
		UpdateButton = new JButton("Update card");
		UpdateButton.setBackground(new Color(0, 0, 255));
		UpdateButton.setForeground(new Color(255, 255, 255));
		UpdateButton.setBounds(234, 271, 108, 57);
		UpdateButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try {
		            String cardNumberToUpdate = cardNumberField.getText(); 
		            String updatedBalance = txtUpdateBalance.getText();
		            String updatedCreditScore = txtUpdateCreditScore.getText();		          
		            BufferedReader reader = new BufferedReader(new FileReader("Details.txt"));
		            StringBuilder updatedFile = new StringBuilder();
		            String line;
		            boolean updated = false; 
		            while ((line = reader.readLine()) != null) {
		                String[] parts = line.split(",");
		                if (parts.length >= 10 && parts[2].equals(cardNumberToUpdate)) {	                    
		                    parts[7] = updatedBalance;		                    
		                    if (parts[3].equals("Credit")) {
		                        parts[4] = updatedCreditScore;
		                    } else {
		                        parts[4] = "N/A";
		                    }
		                    line = String.join(",", parts);
		                    updated = true;
		                }
		                updatedFile.append(line).append("\n");
		            }
		            reader.close();

		            if (updated) {		            
		                BufferedWriter writer = new BufferedWriter(new FileWriter("Details.txt"));
		                writer.write(updatedFile.toString());
		                writer.close();		                
		                cardNumberField.setText("");
		                txtUpdateBalance.setText("");
		                txtUpdateCreditScore.setText("");

		                JOptionPane.showMessageDialog(null, "Card updated successfully.");
		            } else {
		                JOptionPane.showMessageDialog(null, "Card not found or update failed.");
		            }
		        } catch (IOException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "An error occurred while updating the card.");
		        }
		    }
		});
		contentPane.add(UpdateButton);
		
		txtUpdateBalance = new JTextField();
		txtUpdateBalance.setBackground(new Color(0, 0, 255));
		txtUpdateBalance.setHorizontalAlignment(SwingConstants.CENTER);
		txtUpdateBalance.setText("Balance");
		txtUpdateBalance.setBounds(352, 273, 101, 20);
		contentPane.add(txtUpdateBalance);
		txtUpdateBalance.setColumns(10);
		
		txtUpdateCreditScore = new JTextField();
		txtUpdateCreditScore.setBackground(new Color(0, 0, 255));
		txtUpdateCreditScore.setText("Credit Score");
		txtUpdateCreditScore.setHorizontalAlignment(SwingConstants.CENTER);
		txtUpdateCreditScore.setBounds(352, 306, 106, 20);
		contentPane.add(txtUpdateCreditScore);
		txtUpdateCreditScore.setColumns(10);
		
		cardNumberField = new JTextField();
		cardNumberField.setText("Enter Card Number");
		cardNumberField.setHorizontalAlignment(SwingConstants.CENTER);
		cardNumberField.setColumns(10);
		cardNumberField.setBackground(Color.BLUE);
		cardNumberField.setBounds(463, 289, 101, 20);
		contentPane.add(cardNumberField);
	}
}
