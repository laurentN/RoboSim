import model.Position;
import model.map.Map;
import model.map.MapException;
import control.simulation.Simulation;



public class TestSimulation 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Map m = new Map();
		try {
			m.load("C:/Users/Guillaume/workspace/RoboSim/java-copre-RoboSim/src/data/mapNonVide.map");
		} catch (MapException e) {
			e.printStackTrace();
		}
		Simulation sim = new Simulation(m, null, new Position(5,8), new Position(15,15));
		sim.changeRobotPosition(new Position(8,8));
		sim.displayMap();
	}
}
