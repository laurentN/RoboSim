import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import model.robot.ConstantesXML;
import model.robot.ContactSensor;
import model.robot.LightSensor;
import model.robot.Robot;
import model.robot.TemperatureSensor;

import org.jdom2.Element;

import ui.UICreateRobot;
import utils.StringUtils;


public class MenuInterface implements ActionListener {

	private JFrame frmRobosim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuInterface window = new MenuInterface();
					window.frmRobosim.setVisible(true);
					WindowListener exitListener = new WindowAdapter() {

			            public void windowClosing(WindowEvent e) {
			                int confirm = JOptionPane.showOptionDialog(null, "Êtes-vous sûr de vouloir quitter ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			                if (confirm == JOptionPane.YES_OPTION)
			                	System.exit(0);
			            }
			        };
					window.frmRobosim.addWindowListener(exitListener);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRobosim = new JFrame();
		
		WindowListener exitListener = new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(null, "Êtes-vous sûr de vouloir quitter ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == JOptionPane.YES_OPTION)
                	System.exit(0);
            }
        };
        
        
        frmRobosim.addWindowListener(exitListener);
		frmRobosim.setResizable(false);
		frmRobosim.setTitle("RoboSim");
		frmRobosim.setBounds(100, 100, 799, 600);
		frmRobosim.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRobosim.getContentPane().setLayout(null);
		
		JLabel lblBienvenuSurRobosim = new JLabel("Bienvenu Sur RoboSim");
		lblBienvenuSurRobosim.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenuSurRobosim.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblBienvenuSurRobosim.setBounds(286, 117, 210, 46);
		frmRobosim.getContentPane().add(lblBienvenuSurRobosim);
		
		JButton btnCrerRobot = new JButton("Cr\u00E9er Robot");
		btnCrerRobot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		btnCrerRobot.setBounds(246, 215, 135, 40);
		frmRobosim.getContentPane().add(btnCrerRobot);
		
		JButton btnCrerCarte = new JButton("Cr\u00E9er Carte");	
		
		btnCrerCarte.setBounds(401, 215, 135, 40);
		frmRobosim.getContentPane().add(btnCrerCarte);
		
		JButton btnLancerUneSimulation = new JButton("Lancer une Simulation");
		btnLancerUneSimulation.setBounds(246, 266, 290, 40);
		frmRobosim.getContentPane().add(btnLancerUneSimulation);
		btnLancerUneSimulation.addActionListener(this);
		
		try {
			BufferedImage myPicture = ImageIO.read(new File("image1.png"));
			
			Image thePicture = myPicture.getScaledInstance(500, 500, Image.SCALE_DEFAULT );
			BufferedImage pictureResized = createResizedCopy(thePicture, 200, 200);
			JLabel picLabel = new JLabel(new ImageIcon(pictureResized));
			picLabel.setBounds(131, 340, 200, 200);
			frmRobosim.getContentPane().add(picLabel);
			
			BufferedImage myPicture2 = ImageIO.read(new File("image2.jpg"));
			
			Image thePicture2 = myPicture2.getScaledInstance(500, 500, Image.SCALE_DEFAULT );
			BufferedImage pictureResized2 = createResizedCopy(thePicture2, 200, 200);
			JLabel picLabel2 = new JLabel(new ImageIcon(pictureResized2));
			picLabel2.setBounds(462, 339, 200, 200);
			frmRobosim.getContentPane().add(picLabel2);
		}
		catch (IOException ex) {
			System.out.println("impossible to load picture");
       }
		
		
		
		
	}
	
	
	public BufferedImage createResizedCopy(Image originalImage, 
    		int scaledWidth, int scaledHeight)
    {
    	int imageType = BufferedImage.TYPE_INT_RGB;
    	BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
    	Graphics2D g = scaledBI.createGraphics();
    	g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null); 
    	g.dispose();
    	return scaledBI;
    }
	
	public void closeWindow(){
		this.frmRobosim.dispose();
	}
	
	
	public void actionPerformed(ActionEvent arg0) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimulatorInterface window = new SimulatorInterface();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	      this.closeWindow();
	    }
	
}
