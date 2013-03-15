package model.pathfinding;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

import control.simulation.Simulation;

import model.Position;
import model.map.Map;


public class PathFinding 
{
	public static int timerDisplaySearch = 10;
	
	private Node initialNode;
	private Node currentNode;
	private Node searchNode;
	private Node finalNode;
	
	private ArrayList<Node> openList = new ArrayList<Node>();
	private ArrayList<Node> closeList = new ArrayList<Node>();
	
	private Simulation simulation;
	private Map map;
	
	public PathFinding(Position initialPosition, Position finalPosition, Map map, Simulation simulation)
	{
		this.initialNode = new Node (0, 0, 0, initialPosition, null);
		this.currentNode = this.initialNode;
		this.finalNode = new Node (0, 0, 0, finalPosition, null);
		this.map = map;
		this.simulation = simulation;
	}
	
	public ArrayList<Node> mainSearch()
	{
		ArrayList<Node> newPath = new ArrayList<Node>();
		ArrayList<Node> tmpPath = new ArrayList<Node>();
		ArrayList<Node> currentPath = new ArrayList<Node>();
		boolean noSolution = false;
		Node newNode, commonNode;
		
		do
		{
			try {
				Thread.sleep(timerDisplaySearch);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
										this.openList.remove(k);
										this.openList.add(this.searchNode);
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
			this.openList.remove(this.currentNode);
			
			newNode = this.selectBestNode();

			tmpPath.clear();
			
			if(this.currentNode.isNextTo(newNode)){
				tmpPath.add(this.currentNode);
				tmpPath.add(newNode);
				
			}
			else
			{
				currentPath = this.computePath(this.currentNode);
				newPath = this.computePath(newNode);
				
				commonNode = this.searchCommonNode(newPath, currentPath);
				
				Collections.reverse(newPath);				
				
				if(commonNode != null)
				{
					int l = 0;
					while(l < currentPath.size() && !currentPath.get(l).equals(commonNode))
					{
						tmpPath.add(currentPath.get(l));
						l++;
					}					
					
					l = 0;
					while(l < newPath.size() && !newPath.get(l).equals(commonNode))
					{
						l++;
					}
					
					while(l < newPath.size())
					{
						tmpPath.add(newPath.get(l));
						l++;
					}
				}
				else
				{
					tmpPath.addAll(currentPath);
					tmpPath.addAll(newPath);
				}
				
			}
			
			this.simulation.changeRobotPosition(tmpPath);
			
			this.currentNode = newNode;
			
			if(this.currentNode == null)
			{
				noSolution = true;
			}
			else
			{
				this.closeList.add(this.currentNode);
				
				if(this.currentNode.equals(this.finalNode))
				{
					this.finalNode.setParent(this.currentNode.getParent());
				}
			}
			
			
		}while(this.currentNode != null && !this.currentNode.equals(this.finalNode) && !this.openList.isEmpty());

		
		ArrayList<Node> path = null;
		if(!noSolution)
		{
			path = this.computePath(this.finalNode);
		}
		
		return path;
	}	
	
	/**
	 * 
	 * @param referenceNode a node
	 * @return return the list of node between referenceNode and initialNode
	 */
	public ArrayList<Node> computePath(Node referenceNode)
	{
		ArrayList<Node> path = new ArrayList<Node>();
		
		Node browseNode = referenceNode;
		
		while(!browseNode.equals(this.initialNode))
		{
			path.add(browseNode);
			browseNode = browseNode.getParent();
		}
		
		return path;
	}
	
	/**
	 * 
	 * @param newNodeList an arrayList of node
	 * @param currentNodeList an arrayList of node
	 * @return the first commonNode between the 2 parameters, null otherwise
	 */
	public Node searchCommonNode(ArrayList<Node> newNodeList, ArrayList<Node> currentNodeList)
	{
		Node commonNode = null;
		int i = 0;
		
		while(i < currentNodeList.size() && commonNode == null)
		{
			if(newNodeList.contains(currentNodeList.get(i)))
			{
				commonNode = currentNodeList.get(i);
			}
			i++;
		}
		
		return commonNode;
	}
	
	public ArrayList<Node> mainSearchBis()
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
										this.openList.remove(k);
										this.openList.add(this.searchNode);
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
			this.openList.remove(this.currentNode);

			this.currentNode = this.selectBestNode();
			if(this.currentNode == null)
			{
				noSolution = true;
			}
			else
			{
				this.closeList.add(this.currentNode);
				
				if(this.currentNode.equals(this.finalNode))
				{
					this.finalNode.setParent(this.currentNode.getParent());
				}
			}
			
			
		}while(this.currentNode != null && !this.currentNode.equals(this.finalNode) && !this.openList.isEmpty());

		
		ArrayList<Node> path = null;
		if(!noSolution)
		{
			path = new ArrayList<Node>();
			
			Node browseNode = this.finalNode;
			
			while(browseNode != null)
			{
				path.add(browseNode);
				browseNode = browseNode.getParent();
			}
		}
		
		return path;
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
		if(this.currentNode.getPosition().getX() != i && this.currentNode.getPosition().getY() != j)
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
