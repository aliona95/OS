import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.ScrollPane;
import java.awt.Canvas;
import java.awt.Scrollbar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class RM {

	private JFrame frmMm;
	private JTextField txt;
	private JTable table_1;
	private String filenames = "";
	private int dataBloksNum = 0;
	//private int pagingTablesNum = 4;
	private final static int from = 61 * Machine.BLOCK_SIZE * Machine.WORD_SIZE;   // 61 - nuo kur prasideda psl lentele pirma
	public int[] pagingNum = new int[3 * Machine.BLOCK_SIZE];
	
	JRadioButton[] fontButtons = new JRadioButton[3];
	ButtonGroup    fontGroup = new ButtonGroup();
	
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RM window = new RM();
					window.setPagingTable();
					window.frmMm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the application.
	 */
	public RM() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMm = new JFrame();
		frmMm.setTitle("RM");
		frmMm.getContentPane().setBackground(new Color(248, 248, 255));
		frmMm.getContentPane().setLayout(null);
		
		Label label = new Label("Nustatymai ");
		label.setBounds(10, 28, 72, 22);
		label.setForeground(Color.BLUE);
		frmMm.getContentPane().add(label);
		
		Label label_1 = new Label("Re\u017Eimas");
		label_1.setBounds(20, 56, 62, 22);
		frmMm.getContentPane().add(label_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Vartotojas");
		rdbtnNewRadioButton.setBounds(30, 84, 109, 23);
		rdbtnNewRadioButton.setBackground(new Color(248, 248, 255));
		frmMm.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Supervizorius");
		rdbtnNewRadioButton_1.setBounds(30, 110, 109, 23);
		rdbtnNewRadioButton_1.setBackground(new Color(248, 248, 255));
		frmMm.getContentPane().add(rdbtnNewRadioButton_1);
		
		// REALIOS MASINOS ATMINTIES ISVALYMAS
		JButton btnIvalytiAtmint = new JButton("I\u0161valyti atmint\u012F");
		btnIvalytiAtmint.setBounds(464, 55, 128, 23);
		btnIvalytiAtmint.setForeground(new Color(255, 255, 255));
		btnIvalytiAtmint.setBackground(new Color(112, 128, 144));
		btnIvalytiAtmint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				for(int row = 1; row < 66; row++){ 
		    		for(int column = 1; column < 17; column++){
		    			table_1.setValueAt("0000", row, column);
		    		}
		    	}
				frmMm.validate();
			    frmMm.getContentPane().repaint();
			}
		});
		frmMm.getContentPane().add(btnIvalytiAtmint);
		
		
		
		Label label_2 = new Label("Registrai");
		label_2.setBounds(157, 28, 62, 22);
		label_2.setForeground(Color.BLUE);
		frmMm.getContentPane().add(label_2);
		
		Label label_3 = new Label("PLR");
		label_3.setBounds(167, 56, 38, 22);
		frmMm.getContentPane().add(label_3);
		
		TextField textPLR = new TextField();
		textPLR.setText("0000");
		textPLR.setEditable(false);
		textPLR.setEnabled(false);
		textPLR.setBounds(211, 56, 47, 22);
		textPLR.setBackground(Color.LIGHT_GRAY);
		textPLR.setForeground(Color.BLACK);
		frmMm.getContentPane().add(textPLR);
		
		Label label_4 = new Label("R");
		label_4.setBounds(177, 84, 28, 22);
		frmMm.getContentPane().add(label_4);
		
		TextField textR = new TextField();
		textR.setText("0000");
		textR.setEnabled(false);
		textR.setEditable(false);
		textR.setBounds(211, 84, 47, 22);
		textR.setBackground(Color.LIGHT_GRAY);
		frmMm.getContentPane().add(textR);
		
		Label label_5 = new Label("IC");
		label_5.setBounds(177, 111, 28, 22);
		frmMm.getContentPane().add(label_5);
		
		TextField textIC = new TextField();
		textIC.setText("0000");
		textIC.setEditable(false);
		textIC.setBounds(211, 111, 47, 22);
		textIC.setEnabled(false);
		textIC.setBackground(Color.LIGHT_GRAY);
		frmMm.getContentPane().add(textIC);
		
		Label label_6 = new Label("PI");
		label_6.setBounds(264, 56, 28, 22);
		frmMm.getContentPane().add(label_6);
		
		TextField textPI = new TextField();
		textPI.setText("0000");
		textPI.setBounds(296, 56, 47, 22);
		textPI.setEnabled(false);
		textPI.setEditable(false);
		textPI.setBackground(Color.LIGHT_GRAY);
		frmMm.getContentPane().add(textPI);
		
		Label label_7 = new Label("SI");
		label_7.setBounds(264, 85, 22, 22);
		frmMm.getContentPane().add(label_7);
		
		TextField textSI = new TextField();
		textSI.setText("0000");
		textSI.setBounds(296, 84, 47, 22);
		textSI.setEnabled(false);
		textSI.setEditable(false);
		textSI.setBackground(Color.LIGHT_GRAY);
		frmMm.getContentPane().add(textSI);
		
		Label label_8 = new Label("TI");
		label_8.setBounds(264, 110, 22, 22);
		frmMm.getContentPane().add(label_8);
		
		TextField textTI = new TextField();
		textTI.setText("0000");
		textTI.setBounds(296, 110, 47, 22);
		textTI.setEnabled(false);
		textTI.setEditable(false);
		textTI.setBackground(Color.LIGHT_GRAY);
		frmMm.getContentPane().add(textTI);
		
		Label label_9 = new Label("C");
		label_9.setBounds(356, 56, 22, 22);
		frmMm.getContentPane().add(label_9);
		
		TextField textC = new TextField();
		textC.setText("FALSE");
		textC.setBounds(383, 56, 47, 22);
		textC.setEnabled(false);
		textC.setEditable(false);
		textC.setBackground(Color.LIGHT_GRAY);
		frmMm.getContentPane().add(textC);
		
		// REGISTRU ISVALYMAS
		JButton btnIvalytiRegistrus = new JButton("I\u0161valyti registrus");
		btnIvalytiRegistrus.setBounds(464, 110, 128, 23);
		btnIvalytiRegistrus.setForeground(new Color(255, 255, 255));
		btnIvalytiRegistrus.setBackground(new Color(112, 128, 144));
		btnIvalytiRegistrus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				textPLR.setText("0000");
				textR.setText("0000");
				textIC.setText("0000");
				textPI.setText("0000");
				textSI.setText("0000");
				textTI.setText("0000");
				textC.setText("0000");
				frmMm.validate();
			    frmMm.getContentPane().repaint();
			}
		});
		frmMm.getContentPane().add(btnIvalytiRegistrus);
		
		txt = new JTextField();
		txt.setText("");
		txt.setToolTipText("");
		txt.setBounds(157, 522, 102, 20);
		frmMm.getContentPane().add(txt);
		txt.setColumns(10);
		
		JLabel label_10 = new JLabel("U\u017Eduoties failas:");
		label_10.setBounds(30, 525, 109, 14);
		frmMm.getContentPane().add(label_10);
		
		JButton btnPradti = new JButton("Prad\u0117ti");
		btnPradti.setBounds(504, 521, 89, 23);
		btnPradti.setForeground(new Color(255, 255, 255));
		btnPradti.setBackground(new Color(112, 128, 144));
		frmMm.getContentPane().add(btnPradti);
		
			
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 165, 664, 325);
		scrollPane.setBackground(Color.ORANGE);
		frmMm.getContentPane().add(scrollPane);
		
		table_1 = new JTable();
		table_1.setRowSelectionAllowed(false);
		table_1.setBackground(Color.ORANGE);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, new Integer(0), new Integer(1), new Integer(2), new Integer(3), new Integer(4), new Integer(5), new Integer(6), new Integer(7), new Integer(8), new Integer(9), "A", "B", "C", "D", "E", "F"},
				{new Integer(0), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(1), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(2), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(3), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(4), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(5), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(6), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(7), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(8), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(9), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(10).toHexString(10).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(11).toHexString(11).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(12).toHexString(12).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(13).toHexString(13).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(14).toHexString(14).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(15).toHexString(15).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(16).toHexString(16).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(17).toHexString(17).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(18).toHexString(18).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(19).toHexString(19).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(20).toHexString(20).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(21).toHexString(21).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(22).toHexString(22).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(23).toHexString(23).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(24).toHexString(24).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(25).toHexString(25).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(26).toHexString(26).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(27).toHexString(27).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(28).toHexString(28).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(29).toHexString(29).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(30).toHexString(30).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(31).toHexString(31).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(32).toHexString(32).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(33).toHexString(33).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(34).toHexString(34).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(35).toHexString(35).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(36).toHexString(36).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(37).toHexString(37).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(38).toHexString(38).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(39).toHexString(39).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(40).toHexString(40).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(41).toHexString(41).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(42).toHexString(42).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(43).toHexString(43).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(44).toHexString(44).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(45).toHexString(45).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(46).toHexString(46).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(47).toHexString(47).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(48).toHexString(48).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(49).toHexString(49).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(50).toHexString(50).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(51).toHexString(51).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(52).toHexString(52).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(53).toHexString(53).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(54).toHexString(54).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(55).toHexString(55).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(56).toHexString(56).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(57).toHexString(57).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(58).toHexString(58).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(59).toHexString(59).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(60).toHexString(60).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(61).toHexString(61).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(62).toHexString(62).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(63).toHexString(63).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Integer(64).toHexString(64).toUpperCase(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""
			}
		));
		scrollPane.setViewportView(table_1);
		
		for(int row = 1; row < 66; row++){ 
    		for(int column = 1; column < 17; column++){
    			table_1.setValueAt("0000", row, column);
    		}
    	}
		
		JLabel lblkeltiIrVykdomi = new JLabel("\u012Ekelti ir \r\nvykdomi");
		lblkeltiIrVykdomi.setBounds(684, 163, 117, 42);
		frmMm.getContentPane().add(lblkeltiIrVykdomi);
		
		JLabel lblFailai = new JLabel("failai:");
		lblFailai.setBounds(684, 191, 46, 14);
		frmMm.getContentPane().add(lblFailai);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.DARK_GRAY, null, null, null));
		panel.setBounds(684, 214, 117, 230);
		frmMm.getContentPane().add(panel);
		
		// PATIKRINTI KOMANDAS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		btnPradti.addActionListener( new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	// PALEIDZIA VM
		    	// metodas, sudedantis i psl lentele random sk
		    	// PAKEISTI!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		    	// PADARYTI SU DAUG FAILU
		    	Machine.PLR[0] = 0;
		    	Machine.PLR[1] = 'E';
		    	Machine.PLR[2] = 3;
		    	Machine.PLR[3] = 'D';
		    	textPLR.setText("0E3D");
		    	
		    	// nemest i cikla, kai daug failu bus !!!!!!!!!!!!!!!!!!
		    	// mest i cikla, 1 - kelintas failas, po to is foro paimt ji
		    	pagingTableNumbers(1, (60 + 2));  // 60 + 2 blokas, kuriame pirma psl lentele
		        filenames = txt.getText();
		        for (int i = 0; i < 1; i++) {
					fontButtons[i] = new JRadioButton(txt.getText());
					fontGroup.add(fontButtons[i]);
				    panel.add(fontButtons[i]);
				    
				    // nurodome, kad vykdome pirma faila
				    // !!!!!!!!!!!!!!!!!!!!! pradedame vykdyti VM
				    fontButtons[0].setSelected(true);
				    
				    // NUSKAITO NORIMA FAILA
				    BufferedReader inputStream = null;
					try {
						inputStream = new BufferedReader(new FileReader(Machine.filename));
					} catch (FileNotFoundException e4) {
						// TODO Auto-generated catch block
						e4.printStackTrace();
					}
			    	
			    	try {
						expect("$WOW",inputStream);
						expect(".NAM ", inputStream);
						expect(".DAT ", inputStream);
					} catch (Exception e4) {
						// TODO Auto-generated catch block
						e4.printStackTrace();
					}
			    	
			    	int dataCounter = 0;
			    	
			    	// visa uzpildom nuliais
			    	for(int row = 1; row < 17; row++){ 
			    		for(int column = 1; column < 17; column++){
			    			
			    			table_1.setValueAt("0000", row, column);
			    		}
			    	}
			    	
			    	int dataIndex = 0;
			    	int row = row = 17 - dataBloksNum;
			    	int column = 1;
			    	String command = "";
					try {
						while(!(command  = inputStream.readLine()).startsWith("$WRT")){
							/*for(int i = 0; i < Machine.WORD_SIZE; i++){
								memory[(Machine.BLOCK_SIZE * Machine.BLOCK_SIZE * Machine.WORD_SIZE) - 
								       (dataBloksNum * Machine.BLOCK_SIZE * Machine.WORD_SIZE) + dataIndex] = (byte) command.charAt(i);
								dataIndex++;  // !!!! pachekinti  
							}*/
							table_1.setValueAt(command, row, column);
							column++;
							// PACHECKINTI KIEK YRA MAX< KAD NEUZEIT UZ RIBU
							if(column == 17){ 
								row++;
								column = 1;
							}
						}
					} catch (IOException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
			    	
			    	// WRT
			    	int index = 0;
			    	row = 1;
			    	column = 1;
			    	try {
						while(!(command = inputStream.readLine()).startsWith("$END")){
							//address = realAddress(row,column);
							table_1.setValueAt(command, row, column);
							column++;
							if(column == 17){ 
								row++;
								column = 1;
							}
							/*System.out.println(command);
							for(int i = 0; i < command.length(); i++){      // patikrinimas su 4
								memory[index] = (byte) command.charAt(i);
								if((index - 1) < BLOCKS * BLOCK_SIZE * WORD_SIZE){
									index++;
								}else{
									index = 0;
								}
							}*/
						}
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
			    		
			    	// END
			    	try {
						inputStream.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    	   	
			    	frmMm.validate();
				    frmMm.getContentPane().repaint();
				    Machine.filename = txt.getText();
				    Machine.main();
				    for(int j = 0; j < 4096; j++){
				    	System.out.println(Machine.memory[j]);
				    }
				} 
		    }
		});
		
		
		System.out.println(panel.getComponentCount());
		
		frmMm.setBounds(100, 100, 827, 602);
		frmMm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void setPagingTable(){
		for(int i = 0; i < pagingNum.length; i++){
			pagingNum[i] = i;
		}
		int counter = 0;
		Random randomGenerator = new Random(System.currentTimeMillis());
		for(int i = 0; i < pagingNum.length; i++){
			int random = randomGenerator.nextInt(pagingNum.length - counter++);
			swap(i, random + i);
		}
	}
	public void swap(int from, int to){
		int temp = pagingNum[from];
		pagingNum[from] = pagingNum[to];
		pagingNum[to] = temp;
	}
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public void pagingTableNumbers(int vmCounter, int row){
		/*for(int i = 0; i < Machine.BLOCK_SIZE; i++){
			Machine.memory[from + i] = (byte) random;
		}*/
		int index = vmCounter * Machine.BLOCK_SIZE - Machine.BLOCK_SIZE;
	 	for(int column = 1; column < 17; column++){
	    	table_1.setValueAt(new Integer(pagingNum[index]).toHexString(pagingNum[index]).toUpperCase(), row, column);
	   		System.out.println("RANDOM " + new Integer(pagingNum[index]).toHexString(pagingNum[index]).toUpperCase());	
	   		Machine.memory[((61 * 16 + index))] = (byte) pagingNum[index];
	   		index++;
	 	}
	}
	public int realAddress(int x, int y){
		int pagingTableAdr = Machine.BLOCK_SIZE * Machine.PLR[2] + Machine.PLR[3];
		//int pagingTableNum = paging
		
		return 0;
	
		 
	}
	
	public void expect(String expectCommand, BufferedReader inputStream) throws Exception{
    	String command = inputStream.readLine();
    	if(command.startsWith((expectCommand))){
    		if(expectCommand == ".DAT "){
    			dataBloksNum = Character.getNumericValue(command.charAt(5));  // kas po to seka - ignoruojama
    		}
    	}else{
    		throw new Exception("Invalid command " + command + " expected " + expectCommand);
    	}
    }
}
