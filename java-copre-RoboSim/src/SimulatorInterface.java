import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;

import model.Position;
import model.map.Map;
import model.map.MapException;
import model.pathfinding.Node;
import model.pathfinding.PathFinding;
import model.robot.ConstantesXML;
import model.robot.ContactSensor;
import model.robot.LightSensor;
import model.robot.Robot;
import model.robot.TemperatureSensor;

import org.jdom2.Element;

import ui.UICreateRobot;
import ui.UIMap;
import utils.FileUtils;
import utils.StringUtils;
import control.simulation.Simulation;


public class SimulatorInterface implements ActionListener {

	private JFrame frame;
	private UIMap uiMap;
	private Map map;
	private String path;
	private ArrayList<Node> listNode;
	private Timer timerPathFinding = new Timer(500,this);
	
	private int numberNode = 0;
	private ArrayList<Node> pathCalculate = new ArrayList<Node>();
	private ArrayList<Position> p = new ArrayList<Position>();
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

	/**/
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		WindowListener exitListener = new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(null, "Do you really want to quit ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
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
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(341, 492, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("stop");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			
			}
		});
		btnNewButton_1.setBounds(440, 492, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnChoisirUnRobot = new JButton("Choose robots");
		btnChoisirUnRobot.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnChoisirUnRobot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> theRobots = FileUtils.findFiles("SavedRobots/");
				if(theRobots.size()>0){
					ArrayList<JCheckBox> checkBoxList = new ArrayList<JCheckBox>();
					Object[][] donnees = new Object[theRobots.size()][2];
					for(int j=0;j<theRobots.size();j++){
						checkBoxList.add(new JCheckBox());
						donnees[j][0] = checkBoxList.get(j);
						donnees[j][1] = theRobots.get(j).split(".xml")[0];
					}
					String[] entetes = {"Check","Nom"};
					JTable table = new JTable(donnees, entetes);
					table.getColumnModel().getColumn(0).setCellRenderer(new ListeCellrendererRobot(checkBoxList,table));
					int i = JOptionPane.showOptionDialog(null, table, "Select Robot for Simulation",JOptionPane.NO_OPTION,JOptionPane.NO_OPTION,null,new String[] {"OK"},"OK");
					if(i==0){
						for(int j=0;j<theRobots.size();j++){
							if(checkBoxList.get(j).isSelected()){
								System.out.println(theRobots.get(j));
							}
						}
					}
				}
				else{
					JOptionPane.showMessageDialog(null,"You have to create a Robot before choosen one");
					
				}
			}
		});
		btnChoisirUnRobot.setBounds(675, 11, 109, 23);
		frame.getContentPane().add(btnChoisirUnRobot);
		
		JButton btnNewButton_2 = new JButton("New robot");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UICreateRobot uicr = new UICreateRobot();
				boolean finish = true;
				while(finish){
					int i = JOptionPane.showOptionDialog(null, uicr, "Create a Robot",JOptionPane.NO_OPTION,JOptionPane.NO_OPTION,null,new String[] {"create"},"Create");
					if(i==0){
						if(!uicr.getNameRobot().getText().equals("") && StringUtils.stringValide(uicr.getNameRobot().getText())){
							if(!uicr.getSpeedRobot().getText().equals("")){
								if(StringUtils.isANumber(uicr.getSpeedRobot().getText())){
									finish =false;
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
					else{
						finish =false;
					}
				}
			}
		});
		btnNewButton_2.setBounds(675, 41, 109, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		try
		{
			this.map = new Map();
			this.path = "src\\data\\mapNonVide.map";
			map.load(this.path);
			this.uiMap = new UIMap(465,475,this.map);
			uiMap.setStart(20,20);
			uiMap.setFinish(10,10);
		
			createSimulation();
			this.uiMap.setBounds(204, 11, 465, 475);
			frame.getContentPane().add(this.uiMap);

			
		}
		catch (MapException e){}
		
		/*
		Canvas canvas = new Canvas();
		canvas.setBackground(Color.GRAY);
		canvas.setBounds(204, 11, 465, 475);
		frame.getContentPane().add(canvas);
		//frame.getContentPane().add(uimap);*/
	}
	
	/**
	 * Function who created a simulation. It finds the path from a strat point to a finished point
	 */
	public void createSimulation(){
		Simulation sim = new Simulation(map, null, new Position(20,20), new Position(10,10));
		
		PathFinding pf = new PathFinding(new Position(20,20), new Position(10,10), map, sim);
		
		this.pathCalculate = pf.mainSearchBis();
		Collections.reverse(this.pathCalculate);
		for(Node n : pathCalculate)
		{
			p.add(n.getPosition());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().getClass().equals(JButton.class)){
			this.timerPathFinding.start();
		}
		if(e.getSource().equals(this.timerPathFinding)){
			if(this.numberNode<this.pathCalculate.size()){
				try {				
					this.uiMap.setRobot(this.pathCalculate.get(this.numberNode),this.frame);
					this.frame.validate();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.numberNode++;
			}
			else{
				this.timerPathFinding.stop();
			}
		}
		
	}
	
	public JFrame getFrame(){
		return this.frame;
	}
	
}

