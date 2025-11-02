package ui;

import javax.swing.*;
import db.DatabaseConnection;
import utils.PasswordUtil;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
	JTextField usernameField;
	JPasswordField passwordField;
	JButton loginBtn , registerBtn;
	
	public Login() {
		setTitle("User Login");
		setSize(400 , 250);
		setLocation(500 , 250);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(4,2,10,10));
		
		add(new JLabel("Username:"));
		usernameField = new JTextField();
		add(usernameField);
		
		add(new JLabel("Password:"));
		passwordField = new JPasswordField();
		add(passwordField);
		
		registerBtn = new JButton("Register");
		registerBtn.addActionListener(this);
		add(registerBtn);
		
		loginBtn = new JButton("Go to Login");
		loginBtn.addActionListener(this);
		add(loginBtn);
		
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == loginBtn) {
			String username = usernameField.getText();
			String password = new String(passwordField.getPassword());
			String hash = PasswordUtil.hashPassword(password);
			
			if(username.isEmpty() || password.isEmpty()) {
				JOptionPane.showMessageDialog(null , "Username or Password cannot be empty!");
				return;
			}
			
			try {
				Connection con = DatabaseConnection.getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username =? AND password_hash=?"); 
				ps.setString(1,username);
				ps.setString(2,hash);
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
				JOptionPane.showMessageDialog(null,"Login Successful !");
				dispose();
				new Dashboard(username);
				
				}else {
					JOptionPane.showMessageDialog(null ,  "Invalid Credentials!");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(ae.getSource() == registerBtn) {
			dispose();
			new Register();
		}
		
	}

}
