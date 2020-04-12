import java.awt.*;
import javax.swing.*;

public class MemberGUI extends JFrame
{
	Color gray2 = new Color(105,105,105);
	
	public MemberGUI()
	{	
		JLabel label = new JLabel("Menu");
		label.setForeground(Color.white);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel buttonPanel = new JPanel();
		JPanel field = new CatalogViewPanel();
		JPanel holder = new JPanel();
		holder.setLayout(new BoxLayout(holder, BoxLayout.X_AXIS));

		
		JButton startButton = new JButton("Search");
		JButton stopButton = new JButton( "Quit");
		
		JButton bookButton = new JButton("Books");
		JButton audioButton = new JButton("Audio");
		JButton periodicalsButton = new JButton("Periodicals");

		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		startButton.setBackground(Color.white);
		
		stopButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		stopButton.setBackground(Color.white);

		bookButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		bookButton.setBackground(Color.white);

		audioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		audioButton.setBackground(Color.white);

		periodicalsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		periodicalsButton.setBackground(Color.white);
		
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.add(label);
		
		buttonPanel.add(Box.createRigidArea(new Dimension(0,25)));
		buttonPanel.add(startButton);
		
		buttonPanel.add(Box.createRigidArea(new Dimension(0,25)));
		buttonPanel.add(stopButton);
		
		buttonPanel.add(Box.createRigidArea(new Dimension(0,25)));
		buttonPanel.add(bookButton);
		
		buttonPanel.add(Box.createRigidArea(new Dimension(0,25)));
		buttonPanel.add(audioButton);
		
		buttonPanel.add(Box.createRigidArea(new Dimension(0,25)));
		buttonPanel.add(periodicalsButton);
		
		buttonPanel.add(Box.createVerticalGlue());
		
		buttonPanel.setBackground(gray2);
		
		field.setBorder(BorderFactory.createLineBorder(Color.black));
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		holder.add(buttonPanel);
		holder.add(Box.createRigidArea(new Dimension(10,0)));
		holder.add(field);
		
		setBackground(Color.white);
		setTitle("Library Catalog");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(holder);
		pack();
		setVisible(true);
		setSize(1125,1050);
	}
}

