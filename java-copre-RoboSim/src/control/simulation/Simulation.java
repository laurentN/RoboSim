package control.simulation;
import java.util.ArrayList;

import model.Position;
import model.map.Map;
import model.pathfinding.Node;
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
	public void displayMap(ArrayList<Position> path)
	{
		String s="";
		for (int i = 0 ; i < this.map.getGrid().length;i++)
		{
			for (int j = 0 ; j < this.map.getGrid()[0].length;j++)
			{
				boolean bool = false;
				if(this.initialPosition.getX() == i && this.initialPosition.getY() == j)
				{
					s+="i";
					bool = true;
				}
				else if(this.finalPosition.getX() == i && this.finalPosition.getY() == j)
				{
					s+="f";
					bool = true;
				}
//				else if(this.robotPosition.getX() == i && this.robotPosition.getY() == j)
//				{
//					s+="r";
//				}
				
				for(Position p : path)
				{
					if(!bool && p.getX() == i && p.getY() == j)
					{
						s+="p";
						bool = true;
					}
				}
				
				if (!bool && this.map.getGrid()[i][j]) 
					s+="@";
				else if(!bool)
					s+=".";
			}
			s+="\n";
		}
		
		System.out.println(s);
	}
	
	/**
	 * Display the map with the initial position, final position, robot position
	 */
	public void displayMap(Position p)
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
//				else if(this.robotPosition.getX() == i && this.robotPosition.getY() == j)
//				{
//					s+="r";
//				}
				boolean bool = false;

					if(p.getX() == i && p.getY() == j)
					{
						s+="p";
						bool = true;
					}
				
				if (!bool && this.map.getGrid()[i][j]) 
					s+="@";
				else if(!bool)
					s+=".";
			}
			s+="\n";
		}
		
		System.out.println(s);
	}
}
