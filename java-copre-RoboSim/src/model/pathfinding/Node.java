package model.pathfinding;

import model.Position;

public class Node 
{
	private int costF;
	private int costG;
	private int  costH;
	private Position position;
	private Node parent;
	
	public Node(int costF, int costG, int costH, Position position, Node parent)
	{
		this.costF = costF;
		this.costG = costG;
		this.costH = costH;
		this.position = position;
		this.parent = parent;
	}
	
	/**
	 * 
	 * @param node a node
	 * @return return true if the position of this and the parameter are equals
	 */
	public boolean equals(Node node)
	{
		return (this.position.getX() == node.getPosition().getX() && this.position.getY() == node.getPosition().getY());
	}

	public int getCostF() {
		return costF;
	}

	public void setCostF(int costF) {
		this.costF = costF;
	}

	public int getCostG() {
		return costG;
	}

	public void setCostG(int costG) {
		this.costG = costG;
	}

	public int getCostH() {
		return costH;
	}

	public void setCostH(int costH) {
		this.costH = costH;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	
}
