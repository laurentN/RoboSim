package model.map;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.JComponent;

import ui.UIMap;

@SuppressWarnings("serial")
public class GridCell extends JComponent implements Serializable,MouseListener
{
    public static final int SET_BLOCKS=0,SET_START=1,SET_FINISH=2;
    private static int editMode = SET_BLOCKS;
    private static GridCell startCell;
    private static GridCell finishCell;
    private UIMap map ;
    private boolean isStart = false;
    private boolean isFinish = false;
    private boolean isWall = false;
    private boolean isRobot = false;// if wall true
    
    private static Vector<GridCell> cells = new Vector<GridCell>();
    
    public static boolean tidy = false;

    private transient boolean used = false;
   // private boolean totalBlock = false;

    private Point position;
    
    public GridCell(UIMap map){
    	this.addMouseListener(this);
        this.cells.addElement(this);
        this.tidy=true;
        this.map= map;
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
    }
    
    /** 
     * Constructer with option for making this cell impasable
     * @param block Boolean, set to true if this cell can not be passed through
     */
    public GridCell(boolean block,UIMap map){
        this(map);
        if(block){
        	this.isWall = true;
        	this.setBackground(Color.black);
        }
    }
    
    public void setPosition(Point p){
        this.position = p;
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
            if(startCell !=null){
            	temp = startCell;
            	temp.setStart(false);
            }
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
            if(finishCell!=null){
            	temp=finishCell;
            	temp.setFinish(false);
            }
            finishCell=this;
            isFinish=true;
            temp.repaint();
        }
        else{
             isFinish=false;
        }
    } 
 
    
    public boolean isUsed(){
        return used;
    }
    
    public static void setFinishCell(GridCell finishCell) {
		GridCell.finishCell = finishCell;
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
           g.setColor(Color.BLACK);
        }
        if(isRobot){
            g.setColor(Color.ORANGE);
        }
        if(startCell == this){
            g.setColor(Color.GREEN);
        }
        if(finishCell == this){
            g.setColor(Color.red);
        }
        g.fillRect(0,0,size.width,size.height);             
        g.setColor(Color.black);
        g.drawRect(0,0,size.width,size.height);    
    }
    
    public void setRobot(boolean robot){
    	this.isRobot = robot;
    	this.updateUI();
    	repaint();
    }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(this.map.isStart()){
			this.map.deleteStart();
			this.setStart(true);
			this.map.setStart(false);
		}
		else if(this.map.isStop()){
			this.map.deleteStop();
			this.setFinish(true);
			this.map.setStop(false);
			this.repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
 
}