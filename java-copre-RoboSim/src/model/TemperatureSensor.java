package model;

public class TemperatureSensor extends ASensor {

	public TemperatureSensor(Robot r) {
		super(r);
	}
	
	public String getDescription(){
		return super.getDescription()+" TemperatureSensor";
	}

}
