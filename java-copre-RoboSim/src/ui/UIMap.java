package ui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Position;
import model.map.GridCell;
import model.map.Map;
import model.pathfinding.Node;

@SuppressWarnings("serial")
public class UIMap extends JPanel implements Serializable
{
    int w = 40;
    int h = 40;
    transient Image buffer; 
    int robotPositionX=-1;
    int robotPositionY=-1;
    boolean start = false;
    boolean stop = false;
    
    GridCell gridCell[][] = new GridCell[w][h];
	public UIMap(int FrameSizeX, int FrameSizeY, Map map)
	{
		super();

		boolean grid[][] = map.getGrid();
		//{{INIT_CONTROLS
		setLayout(new GridLayout(w,h));
		setSize(getInsets().left + getInsets().right + FrameSizeX ,getInsets().top + getInsets().bottom + FrameSizeY) ;
		//}}		
		
        for(int i=0;i<w;i++){
            for(int j=0;j<h;j++){
               gridCell[i][j] = new GridCell(grid[i][j],this);
               gridCell[i][j].setPosition(new Point(i,j));
               add(gridCell[i][j]);
            }
        }
        this.setVisible(true);
    }
    
    public void paint(Graphics g){
        if(buffer == null){
        	buffer = createImage(getBounds().width,getBounds().height);
        }
        Graphics bg = buffer.getGraphics();
        super.paint(bg);
        bg.setColor(Color.black);
        g.drawImage(buffer,0,0,null);
    }
    
    public void update(Graphics g){
        paint(g);
    }
        
    public Point getStartPosition(){
        GridCell start = GridCell.getStartCell();
        return start.getPosition();
    }
    
    public GridCell[] getAdjacent(GridCell g){
        GridCell next[] = new GridCell[4];
        Point p = g.getPosition();
        if(p.y!=0){
        	next[0]=gridCell[p.x][p.y-1];
        }
        if(p.x!=w-1){
        	next[1]=gridCell[p.x+1][p.y];
        }
        if(p.y!=h-1){
        	next[2]=gridCell[p.x][p.y+1];
        }
        if(p.x!=0){
        	next[3]=gridCell[p.x-1][p.y];
        }
        return next;
    }
    
    public void setRobot(Node node,JFrame frame) throws InterruptedException{
    	int x;
    	int y;
    		x = node.getPosition().getX();
    		y = node.getPosition().getY();
        	if (robotPositionX == -1 || robotPositionY == -1){
        		robotPositionX = x;
        		robotPositionY = y;
        		gridCell[x][y].setRobot(true);
        	}
        	else{
        		gridCell[robotPositionX][robotPositionY].setRobot(false);
        		robotPositionX = x;
        		robotPositionY = y;
        		gridCell[x][y].setRobot(true);
        	}
    }
    
    public void setStart(int x,int y)
	{
		gridCell[x][y].setStart(true);
	}
    public void setFinish(int x,int y)
	{
		gridCell[x][y].setFinish(true);
	}
		//{{REGISTER_LISTENERS
		//};
	//{{DECLARE_CONTROLS
	//}}

    public void deleteStart(){
    	for(int i = 0;i<this.h;i++){
    		for(int j = 0;j<this.w;j++){
    			if(this.gridCell[i][j].isStart()){
    				this.gridCell[i][j].setStart(false);
    			}
    		}
    	}
    }
    
    public void deleteStop(){
    	for(int i = 0;i<this.h;i++){
    		for(int j = 0;j<this.w;j++){
    			if(this.gridCell[i][j].isFinish()){
    				this.gridCell[i][j].setFinish(false);
    			}
    		}
    	}
    }
   
	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public GridCell[][] getGridCell() {
		return gridCell;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	
}