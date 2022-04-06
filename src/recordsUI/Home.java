package recordsUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import officerManagement.Records;
import officerManagement.RecordsController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home extends JFrame {

	private JPanel contentPane;
	private JTextField trnField;
	private JTextField fnameField;
	private JTextField lnameField;
	private JTextField companyField;
	private JTextField positionField;
	private JTextField servLengthField;
	private JTextField avsecGradeField;
	private JTextField textField_6;
	private JTable table;
	private DefaultTableModel model;
	private JTextField psraDateField;
	private Home vis;
	private JTextField fnameUpdateField;
	private JTextField lnameUpdateField;
	private JTextField medExpUpdateField;
	private JTextField psraExpUpdateField;
	private JTextField polExpUpdateField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		setBackground(Color.GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 705, 440);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 0, 0));
		panel.setBounds(0, 0, 105, 401);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton homeButton = new JButton("Home");
		
		homeButton.setBounds(10, 50, 89, 23);
		panel.add(homeButton);
		
		JButton createButton = new JButton("Create");
		
		createButton.setBounds(10, 116, 89, 23);
		panel.add(createButton);
		
		JButton notifyButton = new JButton("Notify");
		
		notifyButton.setBounds(10, 194, 89, 23);
		panel.add(notifyButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_1.setForeground(Color.BLACK);
		panel_1.setBounds(104, 0, 585, 80);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Guardsman Aviation Management");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Yu Gothic", Font.BOLD, 18));
		lblNewLabel_1.setBounds(47, 27, 315, 29);
		panel_1.add(lblNewLabel_1);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.GRAY);
		tabbedPane.setBounds(104, 57, 585, 344);
		contentPane.add(tabbedPane);
		/*table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));*/
		model=new DefaultTableModel();
		Object[] column = {"ID","First Name","Last Name","Med Exp","PSRA Exp", "Pol Rec Exp"};
		Object[] row = new Object[0];
		model.setColumnIdentifiers(column);
		
		JPanel home_panel = new JPanel();
		tabbedPane.addTab("New tab", null, home_panel, null);
		home_panel.setLayout(null);
		
		JPanel createPanel = new JPanel();
		tabbedPane.addTab("New tab", null, createPanel, null);
		createPanel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("First Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(28, 22, 69, 22);
		createPanel.add(lblNewLabel_2);
		
		fnameField = new JTextField();
		fnameField.setBounds(114, 24, 86, 20);
		createPanel.add(fnameField);
		fnameField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Last Name");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(28, 67, 69, 15);
		createPanel.add(lblNewLabel_3);
		
		lnameField = new JTextField();
		lnameField.setBounds(114, 65, 86, 20);
		createPanel.add(lnameField);
		lnameField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Company");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(28, 107, 69, 22);
		createPanel.add(lblNewLabel_4);
		
		companyField = new JTextField();
		companyField.setBounds(114, 109, 86, 20);
		createPanel.add(companyField);
		companyField.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Position");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(28, 154, 54, 22);
		createPanel.add(lblNewLabel_5);
		
		positionField = new JTextField();
		positionField.setBounds(114, 156, 86, 20);
		createPanel.add(positionField);
		positionField.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Service Length");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(28, 205, 86, 15);
		createPanel.add(lblNewLabel_6);
		
		servLengthField = new JTextField();
		servLengthField.setBounds(114, 203, 86, 20);
		createPanel.add(servLengthField);
		servLengthField.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Aviation Security Date");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(224, 27, 133, 14);
		createPanel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Aviation Security Grade");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_8.setBounds(224, 68, 133, 14);
		createPanel.add(lblNewLabel_8);
		
		avsecGradeField = new JTextField();
		avsecGradeField.setBounds(382, 65, 86, 20);
		createPanel.add(avsecGradeField);
		avsecGradeField.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Medical Doc Expiration");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_9.setBounds(224, 112, 122, 14);
		createPanel.add(lblNewLabel_9);
		
		JFormattedTextField avsecDateField = new JFormattedTextField();
		avsecDateField.setBounds(382, 24, 86, 20);
		createPanel.add(avsecDateField);
		
		JFormattedTextField medDateField = new JFormattedTextField();
		medDateField.setBounds(382, 109, 86, 20);
		createPanel.add(medDateField);
		
		JLabel lblNewLabel_10 = new JLabel("Police Doc Expiration");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_10.setBounds(224, 159, 133, 14);
		createPanel.add(lblNewLabel_10);
		
		JFormattedTextField polDateField = new JFormattedTextField();
		polDateField.setBounds(382, 156, 86, 20);
		createPanel.add(polDateField);
		
		JLabel lblNewLabel_11 = new JLabel("Recommendation Date");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_11.setBounds(224, 205, 133, 14);
		createPanel.add(lblNewLabel_11);
		
		JFormattedTextField recDateField = new JFormattedTextField();
		recDateField.setBounds(382, 203, 86, 20);
		createPanel.add(recDateField);
		
		JButton createSubmit = new JButton("Submit");
		
		createSubmit.setBounds(204, 282, 89, 23);
		createPanel.add(createSubmit);
		
		JLabel lblNewLabel_14 = new JLabel("PSRA Expire Date");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_14.setBounds(224, 238, 104, 22);
		createPanel.add(lblNewLabel_14);
		
		psraDateField = new JTextField();
		psraDateField.setBounds(382, 240, 86, 20);
		createPanel.add(psraDateField);
		psraDateField.setColumns(10);
		
		JLabel lblNewLabel_15 = new JLabel("Date Format: YYYY-MM-DD");
		lblNewLabel_15.setBounds(28, 256, 172, 14);
		createPanel.add(lblNewLabel_15);
		
		JPanel notifyPanel = new JPanel();
		tabbedPane.addTab("New tab", null, notifyPanel, null);
		notifyPanel.setLayout(null);
		
		JLabel lblNewLabel_12 = new JLabel("Mail Addess");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_12.setBounds(128, 37, 74, 25);
		notifyPanel.add(lblNewLabel_12);
		
		textField_6 = new JTextField();
		textField_6.setBounds(234, 40, 138, 20);
		notifyPanel.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("Message Field");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_13.setBounds(198, 88, 125, 14);
		notifyPanel.add(lblNewLabel_13);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
		textArea.setBounds(78, 136, 352, 116);
		notifyPanel.add(textArea);
		
		JPanel trn_panel = new JPanel();
		trn_panel.setBackground(Color.GRAY);
		tabbedPane.addTab("New tab", null, trn_panel, null);
		trn_panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter a TRN");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(99, 74, 115, 39);
		trn_panel.add(lblNewLabel);
		
		trnField = new JTextField();
		trnField.setBounds(318, 85, 107, 20);
		trn_panel.add(trnField);
		trnField.setColumns(10);
		
		JButton trnSubmitter = new JButton("Submit");
		
		trnSubmitter.setBounds(210, 141, 89, 23);
		trn_panel.add(trnSubmitter);
		
		
		
		//JPanel home_panel = new JPanel();
		//tabbedPane.addTab("New tab", null, home_panel, null);
		//home_panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(0, 0, 580, 128);
		home_panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				
				String fname= table.getModel().getValueAt(row,1).toString();
				String lname= table.getModel().getValueAt(row,2).toString();
				String medExp= table.getModel().getValueAt(row,3).toString();
				String psraExp= table.getModel().getValueAt(row,4).toString();
				String polExp= table.getModel().getValueAt(row,5).toString();
				
				
				fnameUpdateField.setText(fname);
				lnameUpdateField.setText(lname);
				medExpUpdateField.setText(medExp);
				psraExpUpdateField.setText(psraExp);
				polExpUpdateField.setText(polExp);
				
			}
		});
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JButton deleteButton = new JButton("Delete");
		
		deleteButton.setBounds(224, 144, 89, 23);
		home_panel.add(deleteButton);
		
		fnameUpdateField = new JTextField();
		fnameUpdateField.setBounds(10, 182, 80, 20);
		home_panel.add(fnameUpdateField);
		fnameUpdateField.setColumns(10);
		
		lnameUpdateField = new JTextField();
		lnameUpdateField.setBounds(100, 182, 67, 20);
		home_panel.add(lnameUpdateField);
		lnameUpdateField.setColumns(10);
		
		medExpUpdateField = new JTextField();
		medExpUpdateField.setBounds(177, 182, 81, 20);
		home_panel.add(medExpUpdateField);
		medExpUpdateField.setColumns(10);
		
		psraExpUpdateField = new JTextField();
		psraExpUpdateField.setBounds(277, 182, 73, 20);
		home_panel.add(psraExpUpdateField);
		psraExpUpdateField.setColumns(10);
		
		JButton updateButton = new JButton("Update");
		
		updateButton.setBounds(455, 181, 89, 23);
		home_panel.add(updateButton);
		
		polExpUpdateField = new JTextField();
		polExpUpdateField.setBounds(360, 182, 73, 20);
		home_panel.add(polExpUpdateField);
		polExpUpdateField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Export");
		btnNewButton_1.setBounds(100, 232, 89, 23);
		home_panel.add(btnNewButton_1);
		
		JButton randomizerButton = new JButton("Generate Audit");
		randomizerButton.addActionListener(new ActionListener() {//random records
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				RecordsController recorder = new RecordsController();
				loadPart(recorder.generateRandom());
			}
		});
		randomizerButton.setBounds(338, 232, 120, 23);
		home_panel.add(randomizerButton);
		
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);
			}
		});
		
		notifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		
		createSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String TRN= trnField.getText();
				trnField.setText("");
				
				RecordsController recorder = new RecordsController(TRN);
				
				
				String fname= fnameField.getText();
				String lname = lnameField.getText();
				String company = companyField.getText();
				String position = positionField.getText();
				String serv= servLengthField.getText();
				String avsecDate=avsecDateField.getText();
				String avsecGrade= avsecGradeField.getText();
				String medExp= medDateField.getText();
				String polExp= polDateField.getText();
				String recDate= recDateField.getText();
				String psraExp= psraDateField.getText();
				
				Records record = new Records(TRN,fname,lname,company,position,serv,avsecDate, avsecGrade, medExp, polExp, recDate, psraExp);
				recorder.createRecord(record);
				model.setRowCount(0);
				loadTables();
				
				
			}
		});
		
		trnSubmitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String TRN= trnField.getText();
				
				RecordsController recorder = new RecordsController(TRN);
				
				if (recorder.checkTRN()) {
					JOptionPane.showMessageDialog(null,"Invalid Credentials","TRN Entry error",JOptionPane.ERROR_MESSAGE);
				}else {
					tabbedPane.setSelectedIndex(1);
					
			}
		}});
		
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row>0) {
				String cell = table.getModel().getValueAt(row,0).toString();
				RecordsController recorder = new RecordsController();
				recorder.deleteRecord(cell);
				model.setRowCount(0);
				loadTables();
				}
			}
		});
		
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=table.getSelectedRow();
				
				if (row>0) {
					String id= table.getModel().getValueAt(row,0).toString();
					
					
					String fname=fnameUpdateField.getText();
					String lname= lnameUpdateField.getText();
					String medExp=medExpUpdateField.getText();
					String psraExp=psraExpUpdateField.getText();
					String polExp=polExpUpdateField.getText();
					
					
					
					//if (fname.equals("") || )
					
					RecordsController recorder = new RecordsController();
					recorder.editRecord(id, fname, lname, medExp, psraExp, polExp);
					model.setRowCount(0);
					loadTables();
				}
			}
		});
		vis=this;
		loadTables();
		vis.setVisible(true);
	}
	
	private void loadTables() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","sg-epk@jtk931.048596");
			Statement st = con.createStatement();
			String query = "SELECT ID, fname, lname, med_doc_exp, psra_exp, pol_doc_exp FROM officers";
			ResultSet rs = st.executeQuery(query);
			//DefaultTableModel model = (DefaultTableModel) tbldata.getModel();
			//int id;
			String id, fname, lname, med_doc_exp, psra_doc_exp, pol_doc_exp;
			
			while (rs.next()) {
				id=rs.getString(1);
				fname=rs.getString(2);
				lname=rs.getString(3);
				med_doc_exp=rs.getString(4);
				psra_doc_exp=rs.getString(5);
				pol_doc_exp=rs.getString(6);
				
				String[] row = {id, fname, lname, med_doc_exp, psra_doc_exp, pol_doc_exp};
				//System.out.println(row[2]);
				model.addRow(row);
				
			}
			
			st.close();
			rs.close();
			
		}catch (ClassNotFoundException | SQLException ix) {
			ix.printStackTrace();
		}
	}
	
	private void loadPart(String[][] rs) {
		for (int i=0; i<rs.length; i++) {
			//System.out.println(rs[0][1]);
			model.addRow(rs[i]);
		}
	}
}
