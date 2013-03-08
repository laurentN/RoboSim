import java.awt.event.*;


public class SimulatorLauncher implements ActionListener {
	
    public void actionPerformed(ActionEvent arg0) {
      SimulatorInterface si = new SimulatorInterface();
      arg0.getSource();
    }
    
    SimulatorLauncher(){
    	super();
    }
    
 }