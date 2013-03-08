package model.pathfinding;

import java.util.ArrayList;

import model.Position;
import model.map.Map;


public class PathFinding 
{
	private Node initialNode;
	private Node currentNode;
	private Node searchNode;
	private Node finalNode;
	
	private ArrayList<Node> openList = new ArrayList<Node>();
	private ArrayList<Node> closeList = new ArrayList<Node>();
	
	private Map map;
	
	public PathFinding(Position initialPosition, Position finalPosition, Map map)
	{
		this.initialNode = new Node (0, 0, 0, initialPosition, null);
		this.currentNode = this.initialNode;
		this.finalNode = new Node (0, 0, 0, finalPosition, null);
		this.map = map;
	}
	
	public ArrayList<Node> mainSearch()
	{
		ArrayList<Node> testPath = new ArrayList<Node>();
		boolean noSolution = false;
		
		do
		{

			testPath.add(this.currentNode);
			for(int i = this.currentNode.getPosition().getX() - 1 ; i <=  this.currentNode.getPosition().getX() + 1; i++)
			{
				for(int j = this.currentNode.getPosition().getY() - 1 ; j <= this.currentNode.getPosition().getY() + 1; j++)
				{
					if(!this.map.getGrid()[i][j])
					{
						this.searchNode = new Node (0, 0, 0, new Position(i, j), this.currentNode);
						
						boolean foundInCloseList = false;
						int k = 0;
						while(k < this.closeList.size() && !foundInCloseList)
						{
							if(this.closeList.get(k).equals(this.searchNode))
							{
								foundInCloseList = true;
							}
							k++;
						}
						
						if(!foundInCloseList)
						{
							int costH = this.computeManhattanDistance(i, j, this.finalNode.getPosition().getX(), this.finalNode.getPosition().getY());
							this.searchNode.setCostH(costH*10);
							this.searchNode.setCostG(this.computeCostG(i, j));
							this.searchNode.setCostF(this.searchNode.getCostG() + this.searchNode.getCostH());
							
							boolean foundInOpenList = false;
							k = 0;
							while(k < this.openList.size() && !foundInOpenList)
							{
								if(this.openList.get(k).equals(this.searchNode))
								{
									foundInOpenList = true;
									if(costH < this.openList.get(k).getCostH())
									{
										this.openList.add(k, this.searchNode);
									}
								}
								k++;
							}
							
							if(!foundInOpenList)
							{
								this.openList.add(this.searchNode);
							}
						}
						
					}
				}
			}
			
			this.currentNode = this.selectBestNode();
			
			if(this.currentNode == null)
			{
				noSolution = true;
			}
			else
			{
				this.openList.remove(this.currentNode);
				this.closeList.add(this.currentNode);
				
				if(this.currentNode.equals(this.finalNode))
				{
					this.finalNode.setParent(this.currentNode.getParent());
				}
			}
			
			
		}while(!this.currentNode.equals(this.finalNode) && !this.openList.isEmpty());

		
		ArrayList<Position> path = null;
		if(!noSolution)
		{
			path = new ArrayList<Position>();
			
			Node browseNode = this.finalNode;
			
			while(browseNode != null)
			{
				path.add(browseNode.getPosition());
				browseNode = browseNode.getParent();
			}
		}
		
		return testPath;
	}	
	
	/**
	 * 
	 * @param x abscissa
	 * @param y ordinate
	 * @return return the manhattan distance between the (x1,y1) and the (x2,y2) position
	 */
	private int computeManhattanDistance(int x1, int y1, int x2, int y2)
	{
		return (Math.abs(x1 - x2) + Math.abs(y1 - y2));
	}
	
	/**
	 * 
	 * @param i abscissa
	 * @param j ordinate
	 * @return the distance between the searchNode and the position (i,j)
	 */
	private int computeCostG(int i, int j)
	{
		int cost;
		if(this.searchNode.getPosition().getX() != i && this.searchNode.getPosition().getY() != j)
		{
			cost = 14;
		}
		else
		{
			cost = 10;
		}
		
		return cost + this.searchNode.getParent().getCostG();
	}
	
	
	/**
	 * 
	 * @return the node with the lower f value, if f values are the same, h value is minimized
	 */
	private Node selectBestNode()
	{
		Node bestNode;
		
		if(this.openList.isEmpty()){
			return null;
		}
		
		bestNode = this.openList.get(0);

		for(Node n : this.openList)
		{
			if(n.getCostF() < bestNode.getCostF())
			{
				bestNode = n;
			}
			else if(n.getCostF() == bestNode.getCostF() && n.getCostH() < bestNode.getCostH())
			{
				bestNode = n;
			}
		}
		return bestNode;
	}
}
