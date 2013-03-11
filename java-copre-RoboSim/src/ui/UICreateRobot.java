package ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class UICreateRobot extends JPanel{
 
	private JTextField nameRobot = new JTextField();
	private JTextField speedRobot = new JTextField();
	private JCheckBox temperatureSensor = new JCheckBox("Temperature Sensor");
	private JCheckBox lightSensor = new JCheckBox("Light Sensor");
	private JCheckBox contactSensor = new JCheckBox("Contact Sensor");
	
	public UICreateRobot(){
		this.setLayout(new GridBagLayout());
		GridBagConstraints c =new GridBagConstraints();
		c.fill =GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;

		JLabel labelName = new JLabel("Robot Name");
		this.add(labelName,c);
		c.insets = new Insets(1, 0, 10,0);
		c.gridx = 1;
		this.nameRobot.setSize(this.getWidth()/4, 30);
		this.add(this.nameRobot,c);
		
		c.gridy = 1;
		c.gridx = 0;
		JLabel labelSpeed = new JLabel("Robot Speed");
		this.add(labelSpeed,c);
		c.gridx = 1;
		this.speedRobot.setSize(this.getWidth()/4, 30);
		this.add(this.speedRobot,c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		JLabel sensorLabel = new JLabel("Sensor :");
		this.add(sensorLabel,c);
		c.gridy = 4;
		this.add(this.lightSensor,c);
		c.gridy = 5;
		this.add(this.contactSensor,c);
		c.gridy = 6;
		this.add(this.temperatureSensor,c);		
		this.updateUI();
		this.validate();
	}

	public JTextField getNameRobot() {
		return nameRobot;
	}

	public void setNameRobot(JTextField nameRobot) {
		this.nameRobot = nameRobot;
	}

	public JTextField getSpeedRobot() {
		return speedRobot;
	}

	public void setSpeedRobot(JTextField speedRobot) {
		this.speedRobot = speedRobot;
	}

	public JCheckBox getTemperatureSensor() {
		return temperatureSensor;
	}

	public void setTemperatureSensor(JCheckBox temperatureSensor) {
		this.temperatureSensor = temperatureSensor;
	}

	public JCheckBox getLightSensor() {
		return lightSensor;
	}

	public void setLightSensor(JCheckBox lightSensor) {
		this.lightSensor = lightSensor;
	}

	public JCheckBox getContactSensor() {
		return contactSensor;
	}

	public void setContactSensor(JCheckBox contactSensor) {
		this.contactSensor = contactSensor;
	}

}
