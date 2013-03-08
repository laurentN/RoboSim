import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.swing.text.html.ImageView;
import javax.swing.text.Element;


public class MenuInterface {

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
		btnCrerRobot.setBounds(246, 215, 135, 40);
		frmRobosim.getContentPane().add(btnCrerRobot);
		
		JButton btnCrerCarte = new JButton("Cr\u00E9er Carte");
		
		btnCrerCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		btnCrerCarte.setBounds(401, 215, 135, 40);
		frmRobosim.getContentPane().add(btnCrerCarte);
		
		JButton btnLancerUneSimulation = new JButton("Lancer une Simulation");
		btnLancerUneSimulation.setBounds(246, 266, 290, 40);
		frmRobosim.getContentPane().add(btnLancerUneSimulation);
		
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
	
	
	BufferedImage createResizedCopy(Image originalImage, 
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
}
