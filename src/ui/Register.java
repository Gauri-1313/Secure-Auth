package ui;

import javax.swing.*;
import db.DatabaseConnection;
import utils.PasswordUtil;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.SQLIntegrityConstraintViolationException;

public class Register extends JFrame implements ActionListener {
	
	JTextField usernameField , emailField;
	JPasswordField passwordField;
    JButton registerBtn ,loginBtn;

	public Register() {
		setTitle("User Registration");
		setSize(400 , 300);
		setLocation(500 , 250);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(5, 2, 10,10));
		
		add(new JLabel("Username:"));
		usernameField = new JTextField();
		usernameField.setBounds(50,50,300,40);
		//usernameField.setFont(new Font("Raleway" , Font.BOLD , 25));
		add(usernameField);
		
		add(new JLabel("Email:"));
		emailField = new JTextField();
		add(emailField);
		
		add(new JLabel("Password:"));
		passwordField = new JPasswordField();
		add(passwordField);
		
		registerBtn = new JButton("Register");
		registerBtn.setBounds(160 , 150 , 100 , 30);
		registerBtn.addActionListener(this);
		add(registerBtn);
		
		loginBtn = new JButton("Go to Login");
		loginBtn.setBounds(50 , 150,100,30);
		loginBtn.addActionListener(this);
		add(loginBtn);
		
		setVisible(true);
	}

	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == registerBtn) {
			String username = usernameField.getText();
			String email = emailField.getText();
			String password = new String (passwordField.getPassword());
			
			if(username.isEmpty()) {
				JOptionPane.showMessageDialog(null,"Please Enter Your Username!" );
				return;
			}
			if(password.isEmpty()) {
				JOptionPane.showMessageDialog(null,"Please Enter Your Password!" );
				return;
			}
			
			try {
				Connection con = DatabaseConnection.getConnection();
				PreparedStatement ps = con.prepareStatement("INSERT INTO users(username , email , password_hash) VALUES (?,?,?)"); 
				ps.setString(1,username);
				ps.setString(2,email);
				ps.setString(3,PasswordUtil.hashPassword(password));
				
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null,"Registration Successful ! Now You Can Login.");
				dispose();
				new Login();
				
			}catch(SQLIntegrityConstraintViolationException e) {
				JOptionPane.showMessageDialog(null ,  "Username or Email already exists!");
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(ae.getSource() == loginBtn) {
			dispose();
			new Login();
		}
		
	}
}
