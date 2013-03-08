package ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.robot.ConstantesXML;
import model.robot.ContactSensor;
import model.robot.LightSensor;
import model.robot.Robot;
import model.robot.TemperatureSensor;

import org.jdom2.Element;

import utils.StringUtils;


public class UICreateRobot extends JPanel implements ActionListener{
 
	private JTextField nameRobot = new JTextField();
	private JTextField speedRobot = new JTextField();
	private JCheckBox temperatureSensor = new JCheckBox("Temperature Sensor");
	private JCheckBox lightSensor = new JCheckBox("Light Sensor");
	private JCheckBox contactSensor = new JCheckBox("Contact Sensor");
	private JButton createButton = new JButton("Create");
	
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
		c.gridy = 7;
		c.gridwidth = 1;
		c.gridx = 1;
		this.createButton.addActionListener(this);
		this.add(this.createButton,c);
		
		this.updateUI();
		this.validate();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		/***
		 * 
		 * 
		 * 
		 * Pour regler le pb il suffit d'enlever le bouton create et de lancer l'action a partir du create du jpanel 
		 * on appel ensuite la fonction qui sauvegarde avec tous les test
		 * 
		 */
		if(arg0.getSource().equals(this.createButton)){
			if(!this.nameRobot.getText().equals("") && StringUtils.stringValide(this.nameRobot.getText())){
				if(!this.speedRobot.getText().equals("")){
					if(StringUtils.isANumber(this.speedRobot.getText())){
						ArrayList<Element> sensorList = new ArrayList<Element>();
						Robot robot = new Robot(Integer.parseInt(this.speedRobot.getText()),this.nameRobot.getText());
						if(this.lightSensor.isSelected()){
							robot = new LightSensor(robot);
							Element elementLight = new Element(ConstantesXML.sensorName);
							elementLight.setText(ConstantesXML.lightSensor);
							sensorList.add(elementLight);
						}
						if(this.contactSensor.isSelected()){
							robot = new ContactSensor(robot);
							Element elementContact = new Element(ConstantesXML.sensorName);
							elementContact.setText(ConstantesXML.contactSensor);
							sensorList.add(elementContact);
						}
						if(this.temperatureSensor.isSelected()){
							robot = new TemperatureSensor(robot);
							Element elementTemp = new Element(ConstantesXML.sensorName);
							elementTemp.setText(ConstantesXML.temperatureSensor);
							sensorList.add(elementTemp);
						}
						robot.saveRobot(this.nameRobot.getText()+".xml",sensorList);
						robot.loadRobot(this.nameRobot.getText()+".xml");
					}
					else{
						JOptionPane.showMessageDialog(this,"Use only  number for your speed limit");
					}
				}
				else{
					JOptionPane.showMessageDialog(this,"Enter the speed limit for your Robot");
				}
			}
			else{
				JOptionPane.showMessageDialog(this,"Enter a name for your Robot");
			}
		}
	}
}
