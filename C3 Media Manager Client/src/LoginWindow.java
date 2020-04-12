import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class LoginWindow extends JFrame
{
	private String username;
	private String password;
	
	private JButton loginButton;
	private JButton exitButton;
	
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	
	private JPanel buttonPanel;
	private JPanel holder;

	private JTextField usernameTextField;
	private JTextField passwordTextField;
	
	private boolean filledOut;
		
	public LoginWindow()
	{	
		usernameLabel = new JLabel("Username:");
		passwordLabel = new JLabel("Password:");
		
		buttonPanel = new JPanel();
		holder = new JPanel();
		

		usernameTextField = new JTextField(20);
		passwordTextField = new JTextField(20);
		
		loginButton = new JButton("Log In");
		exitButton = new JButton( "Exit  ");
		
		loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		loginButton.addActionListener(new ButtonListener());
		exitButton.addActionListener(new ButtonListener());
		
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.add(loginButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(10,0)));
		buttonPanel.add(exitButton);
		buttonPanel.add(Box.createVerticalGlue());
		
		holder.setBorder(BorderFactory.createLineBorder(Color.black));
		
	    holder.add(Box.createRigidArea(new Dimension(0,55)));
		holder.add(usernameLabel);
		holder.add(usernameTextField);
		holder.add(passwordLabel);
		holder.add(passwordTextField);
		
		holder.add(buttonPanel);
		
		setTitle("C3 Media Manager Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(holder);
		pack();
		setVisible(true);
		setSize(800,200);
	}
	
	public String getUserName()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	private void verifyLogin()		//Currently Unused
	{
		if(username.contentEquals("jeb") && (password.contentEquals("pass")))
		{
			System.out.println("Password correct");
		}
		else
		{
			System.out.println("Invalid Username or Password");
			usernameTextField.setText("");
			passwordTextField.setText("");
		}
			
	}
	
	public boolean isFilledOut()
	{
		return filledOut;
	}
	
	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == loginButton)
			{
				username = usernameTextField.getText();
				password = passwordTextField.getText();
				
				filledOut = true;
				
				System.out.println("Username Entered: " + username);
				System.out.println("Password Entered: " + password);
			}
			
			if(e.getSource() == exitButton)
			{
				dispose();
			}
		}
	}
}

