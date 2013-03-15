import javax.swing.*;

import ui.UICreateRobot;

import model.robot.Robot;



public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Robot rob = new Robot(200,"Test",0,0);
		rob.loadRobot("robot.xml");
		System.out.println(rob.getName());
		/*
		rob = new LightSensor(rob);
		System.out.println(rob.getDescription());
		
		rob = new ContactSensor(rob);
		System.out.println(rob.getDescription());
		
		rob = new TemperatureSensor(rob);
		System.out.println(rob.getDescription());*/
		JFrame frame = new JFrame();
		frame.setSize((int)frame.getToolkit().getScreenSize().getWidth(), ((int)frame.getToolkit().getScreenSize().getHeight() - 40));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		UICreateRobot createRobot = new UICreateRobot();
		createRobot.setSize(frame.getSize());
		frame.setContentPane(createRobot);
		frame.setVisible(true);
	}

}
