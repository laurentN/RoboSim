import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import model.robot.ConstantesXML;
import model.robot.ContactSensor;
import model.robot.LightSensor;
import model.robot.Robot;
import model.robot.TemperatureSensor;

import org.jdom2.Element;

import ui.UICreateRobot;
import utils.FileUtils;
import utils.StringUtils;
import java.awt.Font;


public class SimulatorInterface implements ActionListener {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimulatorInterface window = new SimulatorInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SimulatorInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		WindowListener exitListener = new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(null, "Êtes-vous sûr de vouloir quitter ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == JOptionPane.YES_OPTION)
                	System.exit(0);
            }
        };
		frame.addWindowListener(exitListener);
		frame.setResizable(true);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 188, 540);
		frame.getContentPane().add(scrollPane);
		
		Canvas canvas_1 = new Canvas();
		canvas_1.setBackground(Color.WHITE);
		
		//Graphics g = canvas_1.getGraphics(); 
		//System.out.println(g);
		//canvas_1.paint(canvas_1.getGraphics());
		scrollPane.setViewportView(canvas_1);
		//g.drawRect(5,5,20,30);
		
		JButton btnNewButton = new JButton("play");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(341, 492, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("stop");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			ArrayList<String> theRobots = FileUtils.findFiles("SavedRobots/");
			
			
			}
		});
		btnNewButton_1.setBounds(440, 492, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnChoisirUnRobot = new JButton("Choisir un robot");
		btnChoisirUnRobot.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnChoisirUnRobot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnChoisirUnRobot.setBounds(675, 11, 109, 23);
		frame.getContentPane().add(btnChoisirUnRobot);
		
		JButton btnNewButton_2 = new JButton("Nouveau robot");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UICreateRobot uicr = new UICreateRobot();
				int i = JOptionPane.showOptionDialog(null, uicr, "Create a Robot",JOptionPane.NO_OPTION,JOptionPane.NO_OPTION,null,new String[] {"create"},"Create");
				System.out.println(i);
				if(i==0){
					if(!uicr.getNameRobot().getText().equals("") && StringUtils.stringValide(uicr.getNameRobot().getText())){
						if(!uicr.getSpeedRobot().getText().equals("")){
							if(StringUtils.isANumber(uicr.getSpeedRobot().getText())){
								ArrayList<Element> sensorList = new ArrayList<Element>();
								Robot robot = new Robot(Integer.parseInt(uicr.getSpeedRobot().getText()),uicr.getNameRobot().getText());
								if(uicr.getLightSensor().isSelected()){
									robot = new LightSensor(robot);
									Element elementLight = new Element(ConstantesXML.sensorName);
									elementLight.setText(ConstantesXML.lightSensor);
									sensorList.add(elementLight);
								}
								if(uicr.getContactSensor().isSelected()){
									robot = new ContactSensor(robot);
									Element elementContact = new Element(ConstantesXML.sensorName);
									elementContact.setText(ConstantesXML.contactSensor);
									sensorList.add(elementContact);
								}
								if(uicr.getTemperatureSensor().isSelected()){
									robot = new TemperatureSensor(robot);
									Element elementTemp = new Element(ConstantesXML.sensorName);
									elementTemp.setText(ConstantesXML.temperatureSensor);
									sensorList.add(elementTemp);
								}
								robot.saveRobot(uicr.getNameRobot().getText()+".xml",sensorList);
								robot.loadRobot(uicr.getNameRobot().getText()+".xml");
							}
							else{
								JOptionPane.showMessageDialog(null,"Use only  number for your speed limit");
							}
						}
						else{
							JOptionPane.showMessageDialog(null,"Enter the speed limit for your Robot");
						}
					}
					else{
						JOptionPane.showMessageDialog(null,"Enter a name for your Robot");
					}
				}
			}
		});
		btnNewButton_2.setBounds(675, 41, 109, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		Canvas canvas = new Canvas();
		canvas.setBackground(Color.GRAY);
		canvas.setBounds(204, 11, 465, 475);
		frame.getContentPane().add(canvas);
	}
	
	public JFrame getFrame(){
		return this.frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	
}

