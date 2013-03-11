import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;


public class ListeCellrendererRobot implements ListCellRenderer {
	JCheckBox check = new JCheckBox();
	@Override
	public Component getListCellRendererComponent(JList arg0, Object arg1,
			int arg2, boolean arg3, boolean arg4) {
		JPanel panel = new JPanel();
		JLabel label = new JLabel((String)arg1);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		panel.add(this.check,c);
		c.gridx = 1;
		panel.add(label,c);
		if (arg3){
			this.check.setSelected(!this.check.isSelected());
		}
		panel.updateUI();
		return panel;
	}

}
