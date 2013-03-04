package UI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UICreateRobot extends JPanel{
 
	private JTextField nameRobot = new JTextField();
	private JTextField speedRobot = new JTextField();
	
	public UICreateRobot(){
		this.setLayout(new GridBagLayout());
		GridBagConstraints c =new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		JLabel labelName = new JLabel("Robot Name");
		this.add(labelName);
	}

}
