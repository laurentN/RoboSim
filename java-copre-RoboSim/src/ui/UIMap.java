package ui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.ArrayList;

import model.Position;
import model.map.GridCell;
import model.map.Map;
import model.pathfinding.Node;

@SuppressWarnings("serial")
public class UIMap extends java.awt.Panel implements Serializable
{
    int w = 40;
    int h = 40;
    transient Image buffer; 
    int robotPositionX=-1;
    int robotPositionY=-1;
    
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
               gridCell[j][i] = new GridCell(grid[i][j]);
               gridCell[j][i].setPosition(new Point(i,j));
               add(gridCell[j][i]);
            }
        }
        
        this.setVisible(true);
       //this.setResizable(false);
        /*
        this.addWindowListener(new WindowAdapter() {
        	public void windowClosing(WindowEvent we){
        		System.exit(0);
        	}
		});*/
    }
    
    public void paint(Graphics g){
        if(buffer == null){buffer = createImage(getBounds().width,getBounds().height);}
        Graphics bg = buffer.getGraphics();
        super.paint(bg);
        bg.setColor(Color.black);
        g.drawImage(buffer,0,0,null);
        //g.drawRect(0,0,getBounds().width-1,getBounds().height-1);
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
        if(p.y!=0){next[0]=gridCell[p.x][p.y-1];}
        if(p.x!=w-1){next[1]=gridCell[p.x+1][p.y];}
        if(p.y!=h-1){next[2]=gridCell[p.x][p.y+1];}
        if(p.x!=0){next[3]=gridCell[p.x-1][p.y];}
        return next;
    }
    
    public void setRobot(ArrayList<Node> arrayNode) throws InterruptedException{
    	Position pos;
    	int x;
    	int y;
    	for(int i = 0;i < arrayNode.size(); i ++){
    		pos = arrayNode.get(i).getPosition();
    		x = pos.getX();
    		y = pos.getY();
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
        	Thread.sleep(1500);
    	}

    	
    }
		//{{REGISTER_LISTENERS
		//};
	//{{DECLARE_CONTROLS
	//}}

}