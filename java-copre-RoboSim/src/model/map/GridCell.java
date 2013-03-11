package model.map;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.Vector;

@SuppressWarnings("serial")
public class GridCell extends java.awt.Component implements Serializable
{
    public static final int SET_BLOCKS=0,SET_START=1,SET_FINISH=2;
    private static int editMode = SET_BLOCKS;
    private static GridCell startCell;
    private static GridCell finishCell;
    
    private boolean isStart = false;
    private boolean isFinish = false;
    private boolean isWall = false;
    private boolean isRobot = false;// if wall true
    
    private static Vector<GridCell> cells = new Vector<GridCell>();
    
    public static boolean tidy = false;

    private transient boolean used = false;
   // private boolean totalBlock = false;

    private Point position;
    
    public GridCell(){
        cells.addElement(this);

        tidy=true;
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
    }
    
    /** 
     * Constructer with option for making this cell impasable
     * @param block Boolean, set to true if this cell can not be passed through
     */
    public GridCell(boolean block){
        this();
        if(block){
        	isWall = true;
        	this.setBackground(Color.black);
        }
    }
    
    public void setPosition(Point p){
        position = p;
    }
    
    public Point getPosition(){
        return position;
    }
      
    public static void setEditMode(int mode){
        editMode = mode;
        System.out.println("mode set");
    }    

    public void processMouseEvent(MouseEvent e){
        super.processMouseEvent(e);
        if(e.getID()==MouseEvent.MOUSE_CLICKED){
            switch(editMode){
               /* case(SET_BLOCKS):                                     
                    if(cost!=newBlockStrength){cost=newBlockStrength;}
                    else{cost=NORMAL;}
					is
                    repaint();
                    break;*/
                case(SET_START):
                    setStart(true);
                    break;
                case(SET_FINISH):
                    setFinish(true);
                    break;         
            }
        }
        
    }
   
    public Dimension getPreferredSize(){
        return new Dimension(10,10);
    }
 
    public static GridCell getStartCell(){
        return startCell;
    }
    
    public boolean isStart(){
        return startCell == this;
    }
    
    public void setStart(boolean flag){
            if(flag){
                GridCell temp = this;
                if(startCell !=null){temp = startCell;temp.setStart(false);}
                startCell=this;
                isStart=true;
                repaint();
                temp.repaint();
            }
            else{
                isStart=false;
            }
               
    }
    
     public static GridCell getFinishCell(){
        return finishCell;
    }
    
    public boolean isFinish(){
        return finishCell == this;
    }
    
    public void setFinish(boolean flag){
        if(flag){
            GridCell temp = this;
            if(finishCell!=null){temp=finishCell;temp.setFinish(false);}
            finishCell=this;
            isFinish=true;
            repaint();
            temp.repaint();
        }
        else{
                isFinish=false;
        }
    } 
 
    
    public boolean isUsed(){
        return used;
    }
    
    private void resetCell(){
        used = false;
    }
    
    public static void reset(){
        for(int i=0;i<cells.size();i++){
            ((GridCell)cells.elementAt(i)).resetCell();
        }
    }

    
    public void paint(Graphics g){
        Dimension size = getSize();
        g.setColor(Color.white); 
        if(isWall){
           g.setColor(Color.black);
        }
        if(isRobot){
            g.setColor(Color.yellow);
        }
        if(startCell == this){
            g.setColor(Color.green);
        }
        if(finishCell == this){
            g.setColor(Color.red);
        }
        g.fillRect(0,0,size.width,size.height);
                
            
        g.setColor(Color.black);
        g.drawRect(0,0,size.width,size.height);    
            
    }
    
    public void setRobot(boolean robot){
    	isRobot = robot;
    	repaint();
    }
 
}