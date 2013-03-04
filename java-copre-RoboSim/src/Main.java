import Model.ContactSensor;
import Model.LightSensor;
import Model.Robot;
import Model.TemperatureSensor;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Robot rob = new Robot(200,"Test",0,0);
		System.out.println(rob.getName());
		
		rob = new LightSensor(rob);
		System.out.println(rob.getDescription());
		
		rob = new ContactSensor(rob);
		System.out.println(rob.getDescription());
		
		rob = new TemperatureSensor(rob);
		System.out.println(rob.getDescription());
	}

}
