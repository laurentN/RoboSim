import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class ListeCellrendererRobot extends  DefaultTableCellRenderer implements MouseListener{
	
	private ArrayList<JCheckBox> checkBoxList;
	private JTable table;
	public ListeCellrendererRobot(ArrayList<JCheckBox> check,JTable table){
		this.checkBoxList = check;
		this.table =table;
		this.table.addMouseListener(this);
	}
	
	 public Component getTableCellRendererComponent (JTable table, Object obj, boolean isSelected, boolean hasFocus, int row, int column) { 
		 return this.checkBoxList.get(row);
	 }

	@Override
	public void mouseClicked(MouseEvent e) {
		if(this.table.getSelectedColumn()==0){
			  this.checkBoxList.get(this.table.getSelectedRow()).setSelected(!this.checkBoxList.get(this.table.getSelectedRow()).isSelected());
			  this.table.updateUI();
		   }
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
