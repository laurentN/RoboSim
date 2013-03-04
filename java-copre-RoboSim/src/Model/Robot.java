package Model;

public class Robot extends ARobot{

	public Robot(int speed, String name){
		super(speed,name);
	}
	public Robot(int speed, String name, int xPosition, int yPosistion) {
		super(speed, name, xPosition, yPosistion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Sensor List :";
	}


	
	

}
