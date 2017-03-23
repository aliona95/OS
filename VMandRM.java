import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.TextField;
import javax.swing.JTextPane;
import java.awt.Label;

public class VMandRM {

	private JFrame frmVm;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VMandRM window = new VMandRM();
					window.frmVm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VMandRM() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVm = new JFrame();
		frmVm.setTitle("VM");
		frmVm.setBounds(100, 100, 446, 362);
		frmVm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVm.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 49, 240, 263);
		scrollPane.setToolTipText("");
		frmVm.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"RM", "VM", "Command"
			}
		));
		
		
		scrollPane.setViewportView(table);
		
		Label label = new Label("R");
		label.setBounds(286, 163, 13, 22);
		frmVm.getContentPane().add(label);
		
		TextField textField = new TextField();
		textField.setBounds(318, 163, 44, 22);
		textField.setText("aaaa");
		frmVm.getContentPane().add(textField);
		
		Label label_1 = new Label("C");
		label_1.setBounds(286, 191, 13, 22);
		frmVm.getContentPane().add(label_1);
		
		TextField textField_1 = new TextField();
		textField_1.setBounds(318, 191, 44, 22);
		frmVm.getContentPane().add(textField_1);
		
		Label label_2 = new Label("IC");
		label_2.setBounds(286, 219, 18, 22);
		frmVm.getContentPane().add(label_2);
		
		TextField textField_2 = new TextField();
		textField_2.setBounds(318, 219, 44, 22);
		frmVm.getContentPane().add(textField_2);
		table.setValueAt("hfjh", 5, 2);
	}
}
