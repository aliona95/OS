import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	JRadioButton[] fontButtons = new JRadioButton[3];
	ButtonGroup    fontGroup = new ButtonGroup();



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RM window = new RM();
					window.frmMm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		
		JButton btnIvalytiAtmint = new JButton("I\u0161valyti atmint\u012F");
		btnIvalytiAtmint.setBounds(464, 55, 128, 23);
		btnIvalytiAtmint.setForeground(new Color(255, 255, 255));
		btnIvalytiAtmint.setBackground(new Color(112, 128, 144));
		btnIvalytiAtmint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		frmMm.getContentPane().add(btnIvalytiAtmint);
		
		JButton btnIvalytiRegistrus = new JButton("I\u0161valyti registrus");
		btnIvalytiRegistrus.setBounds(464, 110, 128, 23);
		btnIvalytiRegistrus.setForeground(new Color(255, 255, 255));
		btnIvalytiRegistrus.setBackground(new Color(112, 128, 144));
		frmMm.getContentPane().add(btnIvalytiRegistrus);
		
		Label label_2 = new Label("Registrai");
		label_2.setBounds(157, 28, 62, 22);
		label_2.setForeground(Color.BLUE);
		frmMm.getContentPane().add(label_2);
		
		Label label_3 = new Label("PLR");
		label_3.setBounds(167, 56, 38, 22);
		frmMm.getContentPane().add(label_3);
		
		TextField textField = new TextField();
		textField.setBounds(211, 56, 47, 22);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setForeground(Color.BLACK);
		textField.setText("LABA");
		frmMm.getContentPane().add(textField);
		
		Label label_4 = new Label("R");
		label_4.setBounds(177, 84, 28, 22);
		frmMm.getContentPane().add(label_4);
		
		TextField textField_1 = new TextField();
		textField_1.setBounds(211, 84, 47, 22);
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setText("jdll");
		frmMm.getContentPane().add(textField_1);
		
		Label label_5 = new Label("IC");
		label_5.setBounds(177, 111, 28, 22);
		frmMm.getContentPane().add(label_5);
		
		TextField textField_2 = new TextField();
		textField_2.setBounds(211, 111, 47, 22);
		textField_2.setEnabled(false);
		textField_2.setEditable(false);
		textField_2.setBackground(Color.LIGHT_GRAY);
		frmMm.getContentPane().add(textField_2);
		
		Label label_6 = new Label("PI");
		label_6.setBounds(264, 56, 28, 22);
		frmMm.getContentPane().add(label_6);
		
		TextField textField_3 = new TextField();
		textField_3.setBounds(296, 56, 47, 22);
		textField_3.setEnabled(false);
		textField_3.setEditable(false);
		textField_3.setBackground(Color.LIGHT_GRAY);
		frmMm.getContentPane().add(textField_3);
		
		Label label_7 = new Label("SI");
		label_7.setBounds(264, 85, 22, 22);
		frmMm.getContentPane().add(label_7);
		
		TextField textField_4 = new TextField();
		textField_4.setBounds(296, 84, 47, 22);
		textField_4.setEnabled(false);
		textField_4.setEditable(false);
		textField_4.setBackground(Color.LIGHT_GRAY);
		frmMm.getContentPane().add(textField_4);
		
		Label label_8 = new Label("TI");
		label_8.setBounds(264, 110, 22, 22);
		frmMm.getContentPane().add(label_8);
		
		TextField textField_5 = new TextField();
		textField_5.setBounds(296, 110, 47, 22);
		textField_5.setEnabled(false);
		textField_5.setEditable(false);
		textField_5.setBackground(Color.LIGHT_GRAY);
		frmMm.getContentPane().add(textField_5);
		
		Label label_9 = new Label("C");
		label_9.setBounds(356, 56, 22, 22);
		frmMm.getContentPane().add(label_9);
		
		TextField textField_6 = new TextField();
		textField_6.setBounds(383, 56, 47, 22);
		textField_6.setEnabled(false);
		textField_6.setEditable(false);
		textField_6.setBackground(Color.LIGHT_GRAY);
		frmMm.getContentPane().add(textField_6);
		
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
		scrollPane.setBounds(30, 165, 562, 325);
		scrollPane.setBackground(Color.ORANGE);
		frmMm.getContentPane().add(scrollPane);
		
		table_1 = new JTable();
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
		lblkeltiIrVykdomi.setBounds(617, 163, 117, 42);
		frmMm.getContentPane().add(lblkeltiIrVykdomi);
		
		JLabel lblFailai = new JLabel("failai:");
		lblFailai.setBounds(617, 198, 46, 14);
		frmMm.getContentPane().add(lblFailai);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.DARK_GRAY, null, null, null));
		panel.setBounds(617, 223, 117, 230);
		frmMm.getContentPane().add(panel);
		
		btnPradti.addActionListener( new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        filenames = txt.getText();
		        for (int i = 0; i < 1; i++) {
					fontButtons[i] = new JRadioButton(txt.getText());
					fontGroup.add(fontButtons[i]);
				    panel.add(fontButtons[i]);
				    
				    // nurodome, kad vykdome pirma faila
				    // !!!!!!!!!!!!!!!!!!!!! pradedame vykdyti VM
				    fontButtons[0].setSelected(true);
				    frmMm.validate();
				    frmMm.getContentPane().repaint();
				    Machine.filename = txt.getText();
				    Machine.main();
				} 
		    }
		});
		
		
		System.out.println(panel.getComponentCount());
		
		frmMm.setBounds(100, 100, 760, 602);
		frmMm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
