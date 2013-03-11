import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class ListeCellrendererRobot extends  DefaultTableCellRenderer {
	
	private ArrayList<JCheckBox> checkBoxList;
	private JTable table;
	public ListeCellrendererRobot(ArrayList<JCheckBox> check,JTable table){
		this.checkBoxList = check;
		this.table =table;
	}
	
	 public Component getTableCellRendererComponent (JTable table, Object obj, boolean isSelected, boolean hasFocus, int row, int column) {
		  if((this.table.getSelectedRow() == row) && (this.table.getSelectedColumn()==0)){
			   this.checkBoxList.get(row).setSelected(!this.checkBoxList.get(row).isSelected());
		   }
		   return this.checkBoxList.get(row);
	 }

}
