package Model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Robot extends ARobot{

	public Robot(int speed, String name){
		super(speed,name);
	}
	public Robot(int speed, String name, int xPosition, int yPosistion) {
		super(speed, name, xPosition, yPosistion);
	}

	@Override
	public String getDescription() {
		return "Sensor List :";
	}

	/**
	 * 
	 * @param sensorList
	 */
	public void saveRobot(String fileName,ArrayList<Element> sensorList){
		Element racine = new Element(ConstantesXML.robot);
		Document document = new Document(racine);
		
		Element name = new Element(ConstantesXML.name);
		name.setText(this.getName());
		racine.addContent(name);
		
		Element speed = new Element(ConstantesXML.speed);
		speed.setText(String.valueOf(this.getSpeed()));
		racine.addContent(speed);
		
		Element position = new Element(ConstantesXML.position);
		Element positionx = new Element(ConstantesXML.positionx);
		positionx.setText(String.valueOf(this.getxPosition()));
		Element positiony = new Element(ConstantesXML.positiony);
		positiony.setText(String.valueOf(this.getyPosition()));
		position.addContent(positionx);
		position.addContent(positiony);
		racine.addContent(position);
		
		if(sensorList.size()>0){
			Element sensor = new Element(ConstantesXML.sensorList);
			for(Element el : sensorList){
				sensor.addContent(el);
			}
			racine.addContent(sensor);
		}
		try
		   {
		      //On utilise ici un affichage classique avec getPrettyFormat()
		      XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		      //Remarquez qu'il suffit simplement de créer une instance de FileOutputStream
		      //avec en argument le nom du fichier pour effectuer la sérialisation.
		      sortie.output(document, new FileOutputStream(fileName));
		   }
		   catch (IOException e){
			   e.printStackTrace();
		   }
	}
	
	
	public Robot loadRobot(String fileName){
		 SAXBuilder sxb = new SAXBuilder();
		 try
	      {
	         //On crée un nouveau document JDOM avec en argument le fichier XML
	         //Le parsing est terminé ;)
	        Document  document = sxb.build(new File(fileName));
	        Element racine = document.getRootElement();
	        String name = racine.getChildText(ConstantesXML.name);
	        int speed = Integer.parseInt(racine.getChildText(ConstantesXML.speed));
	        Element position = racine.getChild(ConstantesXML.position);
	        int positionx = Integer.parseInt(position.getChildText(ConstantesXML.positionx));
	        int positiony = Integer.parseInt(position.getChildText(ConstantesXML.positiony));
	        Robot robot = new Robot(speed, name, positionx,positiony);
	        if(racine.getChild(ConstantesXML.sensorList)!=null){
	        	Element listSensor = racine.getChild(ConstantesXML.sensorList);
	        	for(Element el : listSensor.getChildren()){
	        		if(el.getText().equals(ConstantesXML.contactSensor)){
	        			robot = new ContactSensor(robot);
	        		}
	        		else if(el.getText().equals(ConstantesXML.lightSensor)){
	        			robot = new LightSensor(robot);
	        		}
	        		else if(el.getText().equals(ConstantesXML.temperatureSensor)){
	        			robot = new TemperatureSensor(robot);
	        		}
	        	}
	        }
	        System.out.println(robot.getName()+" "+robot.getSpeed()+" "+robot.getxPosition()+" "+robot.getyPosition()+" "+robot.getDescription());
	        return robot;
	      }
	      catch(Exception e){
	    	  e.printStackTrace();
	      }
		 return null;
	}

}
