import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import ui.UICreateRobot;


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
				JOptionPane.showOptionDialog(null, uicr, "Create a Robot",JOptionPane.NO_OPTION,JOptionPane.NO_OPTION,null,new String[] {"create"},"Create");
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
    	System.out.println("resizing...");
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
