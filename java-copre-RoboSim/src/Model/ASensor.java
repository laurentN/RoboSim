package Model;

public abstract class ASensor extends Robot{
	
	private ARobot robot;

	public ASensor(ARobot r) {
		super(r.getSpeed(),r.getName(),r.getxPosition(),r.getyPosition());
		this.robot = r;
	}
	
	public String getDescription(){
		return robot.getDescription();
	}
}
