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
	 * @return return true if the position of this and the parameter are equals, false otherwise
	 */
	public boolean equals(Node node)
	{
		return (this.position.getX() == node.getPosition().getX() && this.position.getY() == node.getPosition().getY());
	}
	
	/**
	 * 
	 * @param node a node
	 * @return return true if the position of this is next (+/- 1) to the position of the parameter, false otherwise
	 */
	public boolean isNextTo(Node node)
	{
		return Math.abs(this.getPosition().getX() - node.getPosition().getX()) <= 1 && Math.abs(this.getPosition().getY() - node.getPosition().getY()) <= 1;
	}
	
	
	public String toString(){
		return "Node : x="+this.getPosition().getX()+", y="+this.getPosition().getY()+", g="+this.getCostG()+", h="+this.getCostH()+", f="+this.getCostF();
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
		this.costF = this.costG + this.costH;
	}

	public int getCostH() {
		return costH;
	}

	public void setCostH(int costH) {
		this.costH = costH;
		this.costF = this.costG + this.costH;
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
