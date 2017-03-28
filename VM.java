import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JProgressBar;
import javax.swing.JEditorPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Canvas;
import java.awt.Button;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.Label;
import java.awt.TextField;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class VM {

	public static JFrame frmVm;
	public static JTable table;
	private int dataBloksNum = 0;
	
	public static TextField textAX;
	public static TextField textBX;
	public static TextField textIC;
	public static TextField textC;
	public static TextField textSF;
	/**
	 * Launch the application.
	 */
	public void vm() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VM window = new VM();
					window.frmVm.setVisible(true);
					window.checkCommands();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VM() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVm = new JFrame();
		frmVm.setTitle("VM");
		frmVm.getContentPane().setBackground(new Color(248, 248, 255));
		frmVm.getContentPane().setLayout(null);
		
		Label label = new Label("Nustatymai ");
		label.setForeground(Color.BLUE);
		label.setBounds(10, 28, 72, 22);
		frmVm.getContentPane().add(label);
		
		Label label_1 = new Label("Re\u017Eimas");
		label_1.setBounds(20, 56, 62, 22);
		frmVm.getContentPane().add(label_1);
		
		Label label_2 = new Label("Registrai");
		label_2.setForeground(Color.BLUE);
		label_2.setBounds(157, 28, 62, 22);
		frmVm.getContentPane().add(label_2);
		
		Label label_3 = new Label("AX");
		label_3.setBounds(157, 56, 28, 22);
		frmVm.getContentPane().add(label_3);
		
		textAX = new TextField();
		textAX.setText("0000");
		textAX.setEditable(false);
		textAX.setEnabled(false);
		textAX.setBackground(Color.LIGHT_GRAY);
		textAX.setForeground(Color.BLACK);
		textAX.setBounds(186, 56, 72, 22);
		frmVm.getContentPane().add(textAX);
		
		Label label_4 = new Label("BX");
		label_4.setBounds(157, 85, 28, 22);
		frmVm.getContentPane().add(label_4);
		
		textBX = new TextField();
		textBX.setText("0000");
		textBX.setEnabled(false);
		textBX.setEditable(false);
		textBX.setBackground(Color.LIGHT_GRAY);
		textBX.setBounds(186, 84, 72, 22);
		frmVm.getContentPane().add(textBX);
		
		Label label_6 = new Label("IC");
		label_6.setBounds(264, 56, 28, 22);
		frmVm.getContentPane().add(label_6);
		
		textIC = new TextField();
		textIC.setText("0000");
		textIC.setEnabled(false);
		textIC.setEditable(false);
		textIC.setBackground(Color.LIGHT_GRAY);
		textIC.setBounds(296, 56, 47, 22);
		frmVm.getContentPane().add(textIC);
		
		Label label_7 = new Label("C");
		label_7.setBounds(264, 85, 22, 22);
		frmVm.getContentPane().add(label_7);
		
		textC = new TextField();
		textC.setText("FALSE");
		textC.setEnabled(false);
		textC.setEditable(false);
		textC.setBackground(Color.LIGHT_GRAY);
		textC.setBounds(296, 84, 47, 22);
		frmVm.getContentPane().add(textC);
		
		Label label_9 = new Label("SF");
		label_9.setBounds(356, 56, 22, 22);
		frmVm.getContentPane().add(label_9);
		
		textSF = new TextField();
		textSF.setText("0000");
		textSF.setEnabled(false);
		textSF.setEditable(false);
		textSF.setBackground(Color.LIGHT_GRAY);
		textSF.setBounds(383, 56, 72, 22);
		frmVm.getContentPane().add(textSF);
		
		table = new JTable();
		table.setBorder(UIManager.getBorder("ComboBox.border"));
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setToolTipText("");
		table.setModel(new DefaultTableModel(
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
				{"A", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{"B", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{"C", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{"D", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{"E", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{"F", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		
		// !!!!! UZSETINTI VISA LENTELE NULIAIS
		//table.setValueAt("hshj", 6, 7);
		table.setBackground(new Color(255, 215, 0));
		table.setBounds(30, 177, 636, 272);
		frmVm.getContentPane().add(table);
		
		JButton btningsninis = new JButton("\u017Dingsninis");
		btningsninis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btningsninis.setBounds(30, 128, 109, 23);
		frmVm.getContentPane().add(btningsninis);
		
		JButton btnNuolatinis = new JButton("Nuolatinis");
		btnNuolatinis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNuolatinis.setBounds(30, 84, 109, 23);
		frmVm.getContentPane().add(btnNuolatinis);
		frmVm.setBounds(100, 100, 703, 510);
		frmVm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/*
	public JTable getTable(){
		return table;
	}
	*/
	// IS TIKRUJU PACHECKINTI
	public void checkCommands() throws Exception{	
    	// visa uzpildom nuliais
    	for(int row = 1; row < 17; row++){ 
    		for(int column = 1; column < 17; column++){
    			table.setValueAt("0000", row, column);
    		}
    	}
	}
	public static void printMemory(){
		String memory = "";
		for(int row = 1; row < 17; row++){ 
    		for(int column = 1; column < 17; column++){
    			int address = Machine.realAddress(row - 1, column - 1);
    			System.out.println("GAUNAMOS VM ADDRESAI X" + (row - 1) +" Y " + (column - 1));
    			//System.out.println("VM MASINA-------- "+ address);
    			for(int i = 0; i < Machine.WORD_SIZE; i++){
    				memory += (char)Machine.memory[address + i];
        		}
    			System.out.println("memory " +memory);
    			table.setValueAt(memory, row, column);
    			memory = "";
    		}
    	}
		/*String memory = "";
		for(int row = 1; row < 17; row++){
			for(int column = 1; column < 17; column++){
				int address = Machine.realAddress(row - 1, column - 1);
				for(int i = 0; i < Machine.WORD_SIZE; i++){
					memory += (char) Machine.memory[address + i];
	    		}
				System.out.println("ADRESAS!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + address);
				table.setValueAt(memory, row, column);
				memory = "";
			}
			
		}*/
	}
	public static void printRegisters(){
		textAX.setText(Integer.toHexString(Machine.unsignedToBytes(Machine.AX[0])).toUpperCase() + 
			Integer.toHexString(Machine.unsignedToBytes(Machine.AX[1])).toUpperCase() + 
			Integer.toHexString(Machine.unsignedToBytes(Machine.AX[2])).toUpperCase() + 
			Integer.toHexString(Machine.unsignedToBytes(Machine.AX[3])).toUpperCase());
		textBX.setText(Integer.toHexString(Machine.unsignedToBytes(Machine.BX[0])).toUpperCase() + 
				Integer.toHexString(Machine.unsignedToBytes(Machine.BX[1])).toUpperCase() + 
				Integer.toHexString(Machine.unsignedToBytes(Machine.BX[2])).toUpperCase() + 
				Integer.toHexString(Machine.unsignedToBytes(Machine.BX[3])).toUpperCase());
		textSF.setText(Integer.toHexString(Machine.unsignedToBytes(Machine.SF)).toUpperCase());
		textIC.setText((Integer.toHexString(Machine.IC[0]).toUpperCase()) + " " + 
	        	Integer.toHexString(Machine.IC[1]).toUpperCase());
		if(Machine.C == 0){
			textC.setText("FALSE");
		}else{
			textC.setText("TRUE");
		}
	}
}
