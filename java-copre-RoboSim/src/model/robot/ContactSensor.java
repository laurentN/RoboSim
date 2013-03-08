package model.robot;

public class ContactSensor extends ASensor {

	
	public ContactSensor(Robot r) {
		super(r);
	}
	
	public String getDescription(){
		return super.getDescription()+" ContactSensor";
	}

}
