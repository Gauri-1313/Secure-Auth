package ui;

import javax.swing.*;
import java.awt.event.*;

public class Dashboard extends JFrame implements ActionListener {
	JButton logout;
	String username;
	
	public Dashboard(String username) {
		this.username = username;
		setTitle("Welcome,"+ username);
		setSize(400,200);
		setLocation(500 , 250);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		
		JLabel welcome = new JLabel("Welcome  " + username + "  ");
		welcome.setBounds(150,50,200,30);
		add(welcome);
		
		logout = new JButton("Logout");
		logout.setBounds(150,100,100,30);
		add(logout);
		logout.addActionListener(this);
		
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae) {
		dispose();
		new Login();
	}
}
