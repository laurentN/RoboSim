package Model;

public abstract class ARobot {
	private int speed;
	private String name;
	private int xPosition;
	private int yPosition;
	public abstract String getDescription();
	
	public ARobot(int speed,String name){
		this.speed = speed;
		this.name = name;
		this.xPosition = -1;
		this.yPosition = -1;
	}
	
	public ARobot(int speed,String name,int xPosition,int yPosistion){
		this.speed = speed;
		this.name = name;
		this.xPosition = xPosition;
		this.yPosition = yPosistion;
	}
	
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getxPosition() {
		return xPosition;
	}
	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}
	public int getyPosition() {
		return yPosition;
	}
	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}
}
