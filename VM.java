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

	private JFrame frmVm;
	public static JTable table;
	private int dataBloksNum = 0;

	/**
	 * Launch the application.
	 */
	public void vm(String filename) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VM window = new VM();
					window.frmVm.setVisible(true);
					window.checkCommands(filename);
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
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Nuolatinis");
		rdbtnNewRadioButton.setBackground(new Color(248, 248, 255));
		rdbtnNewRadioButton.setBounds(30, 84, 109, 23);
		frmVm.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("\u017Dingsninis");
		rdbtnNewRadioButton_1.setBackground(new Color(248, 248, 255));
		rdbtnNewRadioButton_1.setBounds(30, 110, 109, 23);
		frmVm.getContentPane().add(rdbtnNewRadioButton_1);
		
		Label label_2 = new Label("Registrai");
		label_2.setForeground(Color.BLUE);
		label_2.setBounds(157, 28, 62, 22);
		frmVm.getContentPane().add(label_2);
		
		Label label_3 = new Label("AX");
		label_3.setBounds(177, 56, 28, 22);
		frmVm.getContentPane().add(label_3);
		
		TextField textField = new TextField();
		textField.setText("0000");
		textField.setEditable(false);
		textField.setEnabled(false);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setForeground(Color.BLACK);
		textField.setBounds(211, 56, 47, 22);
		frmVm.getContentPane().add(textField);
		
		Label label_4 = new Label("BX");
		label_4.setBounds(177, 84, 28, 22);
		frmVm.getContentPane().add(label_4);
		
		TextField textField_1 = new TextField();
		textField_1.setText("0000");
		textField_1.setEnabled(false);
		textField_1.setEditable(false);
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setBounds(211, 84, 47, 22);
		frmVm.getContentPane().add(textField_1);
		
		Label label_6 = new Label("IC");
		label_6.setBounds(264, 56, 28, 22);
		frmVm.getContentPane().add(label_6);
		
		TextField textField_3 = new TextField();
		textField_3.setText("0000");
		textField_3.setEnabled(false);
		textField_3.setEditable(false);
		textField_3.setBackground(Color.LIGHT_GRAY);
		textField_3.setBounds(296, 56, 47, 22);
		frmVm.getContentPane().add(textField_3);
		
		Label label_7 = new Label("C");
		label_7.setBounds(264, 85, 22, 22);
		frmVm.getContentPane().add(label_7);
		
		TextField textField_4 = new TextField();
		textField_4.setText("FALSE");
		textField_4.setEnabled(false);
		textField_4.setEditable(false);
		textField_4.setBackground(Color.LIGHT_GRAY);
		textField_4.setBounds(296, 84, 47, 22);
		frmVm.getContentPane().add(textField_4);
		
		Label label_9 = new Label("SF");
		label_9.setBounds(356, 56, 22, 22);
		frmVm.getContentPane().add(label_9);
		
		TextField textField_6 = new TextField();
		textField_6.setText("0000");
		textField_6.setEnabled(false);
		textField_6.setEditable(false);
		textField_6.setBackground(Color.LIGHT_GRAY);
		textField_6.setBounds(383, 56, 47, 22);
		frmVm.getContentPane().add(textField_6);
		
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
		frmVm.setBounds(100, 100, 703, 510);
		frmVm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/*
	public JTable getTable(){
		return table;
	}
	*/
	// IS TIKRUJU PACHECKINTI
	public void checkCommands(String filename) throws Exception{	
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
}
