//package map;
//
//import java.awt.AWTEvent;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Graphics;
//import java.awt.Point;
//import java.awt.event.MouseEvent;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.Serializable;
//import java.util.Vector;
//
//@SuppressWarnings("serial")
//public class GridCellEditor extends java.awt.Component implements Serializable
//{
//    public static final int SET_BLOCKS=0,SET_START=1,SET_FINISH=2;
//    public static final double NORMAL = 1,BLOCK=Double.MAX_VALUE;
//    private static double newBlockStrength = BLOCK;
//    private static int editMode = SET_BLOCKS;
//    private static GridCell startCell;
//    private static GridCell finishCell;
//    
//    private boolean isStart = false;
//    private boolean isFinish = false;
//    private boolean isWall = false; // if wall true
//    
//    private static Vector<GridCell> cells = new Vector<GridCell>();
//    
//    public static boolean tidy = false;
//    
//    private static boolean showPath = true;
//    private static boolean showDist = true;
//    
//    private double cost = 1.0;
//    private transient boolean used = false;
//    private transient double distFromStart = -1;
//    private transient double distFromFinish = -1;
//   // private boolean totalBlock = false;
//    
//    private boolean partOfPath = false;
//    
//    private Point position;
//    
//    public GridCellEditor(){
//        cells.addElement(this);
//
//        tidy=true;
//        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
//    }
//    
//    /** 
//     * Constructer with option for making this cell impasable
//     * @param block Boolean, set to true if this cell can not be passed through
//     */
//    public GridCellEditor(boolean block){
//        this();
//        if(block){
//        	isWall = true;
//        	this.setBackground(Color.black);
//        }
//        setTotalBlock(block);
//    }
//    
//    public void setPosition(Point p){
//        position = p;
//    }
//    
//    public Point getPosition(){
//        return position;
//    }
//      
//    public static void setEditMode(int mode){
//        editMode = mode;
//        System.out.println("mode set");
//    }    
//
//    public void processMouseEvent(MouseEvent e){
//        super.processMouseEvent(e);
//        if(e.getID()==MouseEvent.MOUSE_CLICKED){
//            setShowPath(false);
//            switch(editMode){
//               /* case(SET_BLOCKS):                                     
//                    if(cost!=newBlockStrength){cost=newBlockStrength;}
//                    else{cost=NORMAL;}
//					is
//                    repaint();
//                    break;*/
//                case(SET_START):
//                    setStart(true);
//                    break;
//                case(SET_FINISH):
//                    setFinish(true);
//                    break;         
//            }
//        }
//        
//    }
//   
//    public Dimension getPreferredSize(){
//        return new Dimension(10,10);
//    }
// 
//    public void addToPathFromStart(double distSoFar){
//        used = true;
//        
//        if(distFromStart == -1){
//            distFromStart = distSoFar+cost;
//            return;
//        }
//        if(distSoFar+cost<distFromStart){
//            distFromStart = distSoFar+cost;
//        }
//    }
//    
//    public void addToPathFromFinish(double distSoFar){
//        used = true;
//        if(distFromFinish == -1){
//            distFromFinish = distSoFar+cost;
//            return;
//        }
//        if(distSoFar+cost<distFromFinish){
//            distFromFinish = distSoFar+cost;
//        }
//    }
//        
//    
//    public double getCost(){
//        return cost;
//    }
//    
//    
//    public void setCost(double c){
//        cost=c;
//    }
//    
//    public static GridCell getStartCell(){
//        return startCell;
//    }
//    
//    public boolean isStart(){
//        return startCell == this;
//    }
//    
//    public void setStart(boolean flag){
//            if(flag){
//                GridCell temp = this;
//                if(startCell !=null){temp = startCell;temp.setStart(false);}
//                startCell=this;
//                isStart=true;
//                repaint();
//                temp.repaint();
//            }
//            else{
//                isStart=false;
//            }
//               
//    }
//    
//     public static GridCell getFinishCell(){
//        return finishCell;
//    }
//    
//    public boolean isFinish(){
//        return finishCell == this;
//    }
//    
//    public void setFinish(boolean flag){
//        if(flag){
//            GridCell temp = this;
//            if(finishCell!=null){temp=finishCell;temp.setFinish(false);}
//            finishCell=this;
//            isFinish=true;
//            repaint();
//            temp.repaint();
//        }
//        else{
//                isFinish=false;
//        }
//    } 
//    
//    public boolean isTotalBlock(){
//        return cost==BLOCK;
//    }
//    
//    
//    public void setTotalBlock(boolean flag){
//        if(flag){cost = BLOCK;}
//        else{cost = NORMAL;}
//    }
//    
//    public boolean isUsed(){
//        return used;
//    }
//    
//    private void resetCell(){
//        used = false;
//        setPartOfPath(false);
//        distFromStart = distFromFinish = -1;
//    }
//    
//    public static void reset(){
//        for(int i=0;i<cells.size();i++){
//            ((GridCell)cells.elementAt(i)).resetCell();
//        }
//    }
//    
//    private void clearCell(){
//        setCost(NORMAL);
//    }
//    
//   public static void clearAll(){
//        for(int i=0;i<cells.size();i++){
//            ((GridCell)cells.elementAt(i)).clearCell();
//        }
//    }
//        
//    
//    public static void setNewBlockStrength(double s){
//        if(s<0){newBlockStrength = BLOCK;}
//        else{newBlockStrength = s;}
//    }
//    
//    public static void setShowPath(boolean flag){
//            showPath = flag;
//    }
//    
//    public static boolean isShowPath(){
//        return showPath;
//    }
//    
//    public boolean isPartOfPath(){
//        return partOfPath;
//    }
//    
//    public void setPartOfPath(boolean flag){
//        partOfPath = flag;
//    }
//    
//    
//   
//    public double getDistFromStart(){
//        if(GridCell.startCell == this){return 0;}
//        if(isTotalBlock()){return -1;}
//        return distFromStart;
//    }
//    
//    public void paint(Graphics g){
//        Dimension size = getSize();
//        g.setColor(Color.white); 
//        if(cost!=NORMAL){
//            if(cost==BLOCK){g.setColor(Color.black);}
//        }
//        if(showPath && partOfPath){
//            g.setColor(Color.yellow);
//        }
//        if(startCell == this){
//            g.setColor(Color.green);
//        }
//        if(finishCell == this){
//            g.setColor(Color.red);
//        }
//        g.fillRect(0,0,size.width,size.height);
//                
//            
//        g.setColor(Color.black);
//        if(showDist && distFromStart>0){g.drawString(""+distFromStart,1,(int)(size.height*0.75));}
//        g.drawRect(0,0,size.width-1,size.height-1);    
//            
//    }
//    
//    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
//        if(!tidy){
//            
//            cells = new Vector<GridCell>();
//            tidy=true;
//        }
//        
//        ois.defaultReadObject();
//        cells.addElement(this);
//        if(isStart){setStart(true);}
//        if(isFinish){setFinish(true);}
//       
//        
//    }
//        
//        
//    
//}