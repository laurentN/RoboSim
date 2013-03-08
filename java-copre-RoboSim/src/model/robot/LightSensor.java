package model.robot;

public class LightSensor extends ASensor {

	public LightSensor(Robot r) {
		super(r);
	}
	
	public String getDescription(){
		return super.getDescription()+" LightSensor";
	}

}
