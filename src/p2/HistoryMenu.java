package p2;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class HistoryMenu extends JFrame {

    private JPanel contentPane;
    private JTable table;
    

    public HistoryMenu(LinkedList<Card> cardsLinkedList) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 580, 376);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel MainMenulabel = new JLabel("Main Menu");
        MainMenulabel.setBounds(0, 0, 142, 49);
        MainMenulabel.setHorizontalAlignment(SwingConstants.CENTER);
        MainMenulabel.setOpaque(true);
        MainMenulabel.setForeground(new Color(255, 255, 255));
        MainMenulabel.setBackground(new Color(128, 0, 255));
        MainMenulabel.addMouseListener(new MouseAdapter() {
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
                MainMenulabel.setBackground(new Color(211, 211, 211));
                MainMenulabel.setOpaque(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                MainMenulabel.setBackground(new Color(128, 0, 255));
                MainMenulabel.setOpaque(true);
            }
        });

        table = new JTable();
        table.setForeground(new Color(0, 0, 0));
        table.setBackground(new Color(255, 255, 255));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(15, 60, 533, 208);
        DefaultTableModel tableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] { "Card Name", "Card issuer", "Transaction Date", "Amount of Money" }
            ) {
                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    if (columnIndex == 2) { 
                        return Date.class;
                    }
                    return super.getColumnClass(columnIndex);
                }
            };
        	
        	table.setModel(tableModel);
        	table.setAutoCreateRowSorter(false);
        	contentPane.setLayout(null);
        	contentPane.add(MainMenulabel);
        	contentPane.add(scrollPane);
        	
        	JButton CardNameButton = new JButton("Sort");
        	CardNameButton.setForeground(new Color(255, 255, 255));
        	CardNameButton.setBackground(new Color(128, 0, 255));
        	CardNameButton.setBounds(25, 273, 89, 23);
        	contentPane.add(CardNameButton);
        	CardNameButton.addActionListener(new ActionListener() {
        	    @Override
        	    public void actionPerformed(ActionEvent e) {
        	        List<Object[]> rowDataList = new ArrayList<>();
        	        for (int i = 0; i < tableModel.getRowCount(); i++) {
        	            Vector<?> rowVector = (Vector<?>) tableModel.getDataVector().elementAt(i);
        	            Object[] rowData = rowVector.toArray(new Object[0]);
        	            rowDataList.add(rowData);
        	        }
        	                	      
        	        Comparator<Object[]> cardNameComparator = (row1, row2) -> {
        	            String cardName1 = (String) row1[0];
        	            String cardName2 = (String) row2[0];
        	            return cardName1.compareTo(cardName2);
        	        };      	               	        
        	        MergeSort.mergeSort(rowDataList, cardNameComparator);        	         	       
        	        tableModel.setRowCount(0); 
        	        for (Object[] rowData : rowDataList) {
        	            tableModel.addRow(rowData);
        	        }
        	    }
        	});
        	
        	
        	JButton CardIssuerButton = new JButton("Sort");
        	CardIssuerButton.setForeground(Color.WHITE);
        	CardIssuerButton.setBackground(new Color(128, 0, 255));
        	CardIssuerButton.setBounds(163, 273, 89, 23);
        	contentPane.add(CardIssuerButton);
        	CardIssuerButton.addActionListener(new ActionListener() {
        	    @Override
        	    public void actionPerformed(ActionEvent e) {
        	        List<Object[]> rowDataList = new ArrayList<>();
        	        for (int i = 0; i < tableModel.getRowCount(); i++) {
        	            Vector<?> rowVector = (Vector<?>) tableModel.getDataVector().elementAt(i);
        	            Object[] rowData = rowVector.toArray(new Object[0]);
        	            rowDataList.add(rowData);
        	        }
        	               	       
        	        Comparator<Object[]> cardIssuerComparator = (row1, row2) -> {
        	            String cardIssuer1 = (String) row1[1];
        	            String cardIssuer2 = (String) row2[1];
        	            return cardIssuer1.compareTo(cardIssuer2);
        	        };           	        
        	        MergeSort.mergeSort(rowDataList, cardIssuerComparator);        	            	        
        	        tableModel.setRowCount(0); 
        	        for (Object[] rowData : rowDataList) {
        	            tableModel.addRow(rowData);
        	        }
        	    }
        	});
        	
        	JButton TransactionDateButton = new JButton("Sort");
        	TransactionDateButton.setForeground(Color.WHITE);
        	TransactionDateButton.setBackground(new Color(128, 0, 255));
        	TransactionDateButton.setBounds(298, 273, 89, 23);
        	contentPane.add(TransactionDateButton);
        	TransactionDateButton.addActionListener(new ActionListener() {
        	    @Override
        	    public void actionPerformed(ActionEvent e) {
        	        Comparator<Object[]> dateComparator = (row1, row2) -> {
        	            Date date1 = (Date) row1[2];
        	            Date date2 = (Date) row2[2];
        	            return date1.compareTo(date2);
        	        };
        	        
        	        MergeSort.mergeSortAndUpdate(tableModel, 2, dateComparator); 
        	        table.repaint();
        	    }
        	});
        	JButton moneyButton = new JButton("Sort");
        	moneyButton.setForeground(Color.WHITE);
        	moneyButton.setBackground(new Color(128, 0, 255));
        	moneyButton.setBounds(438, 273, 89, 23);
        	contentPane.add(moneyButton);
        	moneyButton.addActionListener(new ActionListener() {
        	    @Override
        	    public void actionPerformed(ActionEvent e) {
        	        Comparator<Object[]> amountComparator = (row1, row2) -> {
        	            Double amount1 = (Double) row1[3];
        	            Double amount2 = (Double) row2[3];
        	            return amount2.compareTo(amount1);
        	        };
        	        
        	        MergeSort.mergeSortAndUpdate(tableModel, 3, amountComparator); 
        	        table.repaint();
        	    }
        	});
        	


        	try {
        	    BufferedReader reader = new BufferedReader(new FileReader("Details.txt"));
        	    String line;
        	    while ((line = reader.readLine()) != null) {
        	        String[] parts = line.split(",");
        	        if (parts.length >= 4) {
        	            String cardName = parts[0];
        	            String cardIssuer = parts[1];
        	            String transactionDateStr = parts[6];
        	            double amount = Double.parseDouble(parts[5]);
        	            Date transactionDate = null;

        	            try {
        	                transactionDate = new SimpleDateFormat("yyyy-MM-dd").parse(transactionDateStr);
        	            } catch (ParseException e) {
        	                // Handle the parse exception
        	            }
        	            Object[] rowData = {
        	                cardName,
        	                cardIssuer,
        	                transactionDate, // Use the parsed Date object here
        	                amount
        	            };
        	            tableModel.addRow(rowData);
        	        }
        	    }
        	    reader.close();
        	} catch (IOException e) {
        	    e.printStackTrace();
        	}
        	 DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
                 private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                 @Override
                 public Component getTableCellRendererComponent(JTable table, Object value,
                         boolean isSelected, boolean hasFocus, int row, int column) {
                     if (value instanceof Date) {
                         value = sdf.format((Date) value);
                     }
                     return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                 }
             };
             table.getColumnModel().getColumn(2).setCellRenderer(renderer); 
         }
    }

