import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;


public class SimulatorInterface {

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
		frame.setResizable(false);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 188, 550);
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
			}
		});
		btnNewButton_1.setBounds(440, 492, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnChoisirUnRobot = new JButton("Choisir un robot");
		btnChoisirUnRobot.setBounds(675, 11, 109, 23);
		frame.getContentPane().add(btnChoisirUnRobot);
		
		JButton btnNewButton_2 = new JButton("Cr\u00E9er nouveau robot");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(675, 41, 109, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		Canvas canvas = new Canvas();
		canvas.setBackground(Color.GRAY);
		canvas.setBounds(204, 11, 465, 475);
		frame.getContentPane().add(canvas);
	}
}

