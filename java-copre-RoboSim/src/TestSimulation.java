import java.util.ArrayList;

import model.Position;
import model.map.Map;
import model.map.MapException;
import model.pathfinding.Node;
import model.pathfinding.PathFinding;
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
			m.load("src\\data\\mapNonVide.map");
		} catch (MapException e) {
			e.printStackTrace();
		}
		Simulation sim = new Simulation(m, null, new Position(5,8), new Position(25,23));
		
		PathFinding pf = new PathFinding(new Position(5,8), new Position(25,23), m, sim);
		
		ArrayList<Node> path = pf.mainSearchBis();
		ArrayList<Position> p = new ArrayList<Position>();
		
		for(Node n : path)
		{
			//System.out.println(n);
			p.add(n.getPosition());
		}
		
		
//		sim.changeRobotPosition(new Position(8,8));
		
		
		sim.displayMap(p);
			
		
		
	}
}
