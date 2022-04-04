package security;
import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import recordsUI.Home;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.UIManager;
import javax.swing.JSeparator;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JPasswordField passField;
	private Login vis;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 322);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel userNameLbl = new JLabel("Username");
		userNameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		userNameLbl.setForeground(Color.WHITE);
		userNameLbl.setBounds(82, 73, 98, 23);
		contentPane.add(userNameLbl);
		
		nameField = new JTextField();
		nameField.setBounds(208, 75, 115, 23);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(77, 123, 78, 20);
		contentPane.add(lblNewLabel_1);
		
		passField = new JPasswordField();
		passField.setBounds(208, 124, 115, 23);
		contentPane.add(passField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(175, 165, 89, 23);
		contentPane.add(btnLogin);
		
		JLabel picLbl = new JLabel("");
		Image image = new ImageIcon(this.getClass().getResource("/guardsman.jpg")).getImage();
		Image modifiedImage = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		picLbl.setIcon(new ImageIcon(modifiedImage));
		picLbl.setBounds(167, 194, 166, 89);
		contentPane.add(picLbl);
		
		JLabel lblNewLabel = new JLabel("User Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(188, 11, 78, 23);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBounds(82, 55, 280, 9);
		contentPane.add(separator);
		
		vis=this;
		btnLogin.addActionListener(new LoginButtonListener());
		
	}
	
	private String doHash(String pass) {
		try {
			MessageDigest msgDig = MessageDigest.getInstance("MD5");
			msgDig.update(pass.getBytes());
			byte[] resultArray = msgDig.digest();
			StringBuilder sb = new StringBuilder();
			
			for(byte b : resultArray) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		}catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		
		return "";
	}
	
	private class LoginButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			String uname = nameField.getText();
			String pass= new String(passField.getPassword());
			String sqlName="root";
			String sqlPassword="sg-epk@jtk931.048596";
			//String query = "SELECT name, password from user WHERE name= " + uname;
			//PreparedStatement prep= new 
			String url = "jdbc:mysql://localhost:3306/users";
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			}catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}
			
			try {
				Connection conn = DriverManager.getConnection(url,sqlName,sqlPassword);
				PreparedStatement pre = conn.prepareStatement("select name, password from user WHERE name=?");
				pre.setString(1, uname);
				//Statement statement= conn.createStatement();
				//System.out.println("first");
				ResultSet result = pre.executeQuery();
				//System.out.println("second");
				if (!result.next()) {
					JOptionPane.showMessageDialog(null,"Invalid Credentials","User Entry Error",JOptionPane.ERROR_MESSAGE);
				}else {
					String checkName=result.getString("name");
					String checkPass=result.getString("password");
					String checkHash= doHash(pass);
					if (checkHash.equalsIgnoreCase(checkPass)) {
						//JOptionPane.showMessageDialog(null,"User Login Successful!","User Entry",JOptionPane.INFORMATION_MESSAGE);
						//actual screen here
						vis.dispose();
						Home homer = new Home();
						
					}else {
						JOptionPane.showMessageDialog(null,"Invalid Credentials","User Entry Error",JOptionPane.ERROR_MESSAGE);
					}
					
					
					
				}
				
				result.close();
				
			}catch (SQLException ex) {
				ex.printStackTrace();
			}
			
	}
	}
}

	
