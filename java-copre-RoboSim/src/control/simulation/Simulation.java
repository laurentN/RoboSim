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
	 * Display the map with the initial position, final position, robot position
	 */
	public void displayMap()
	{
		String s="";
		for (int i = 0 ; i < this.map.getGrid().length;i++)
		{
			for (int j = 0 ; j < this.map.getGrid()[0].length;j++)
			{
				if(this.initialPosition.getX() == i && this.initialPosition.getY() == j)
				{
					s+="i";
				}
				else if(this.finalPosition.getX() == i && this.finalPosition.getY() == j)
				{
					s+="f";
				}
				else if(this.robotPosition.getX() == i && this.robotPosition.getY() == j)
				{
					s+="r";
				}
				else if (this.map.getGrid()[i][j]) 
					s+="@";
				else 
					s+=".";
			}
			s+="\n";
		}
		System.out.println(s);
	}
}
