package Model;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.jdom2.Element;

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
