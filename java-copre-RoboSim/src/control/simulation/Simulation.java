package control.simulation;
import model.Position;
import model.map.Map;
import model.robot.Robot;


public class Simulation 
{
	
	private Map map;
	private Robot robot;
	
	private Position initialPosition;
	private Position finalPosition;
	private Position robotPosition;
	
	public Simulation(Map map, Robot robot, Position initialPosition, Position finalPosition)
	{
		this.map = map;
		this.robot = robot;
		this.initialPosition = initialPosition;
		this.finalPosition = finalPosition;
	}
	
	/**
	 * change the position of the robot
	 * @param newPosition it's the new position for the robot
	 */
	public void changeRobotPosition(Position newPosition)
	{
		this.robotPosition = newPosition;
	}
	
	/**
	 * Display the map with the intial position, final position, robot position
	 */
	public void displayMap()
	{
		
	}
}
