package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JTable;

public class ActionSelectFirst extends AbstractAction {

	public JTable table;
	
	public ActionSelectFirst(JTable table) {
		// TODO Auto-generated constructor stub
		this.table = table;
		putValue(SMALL_ICON, new ImageIcon("images/first.gif"));
		putValue(SHORT_DESCRIPTION, "Selekcija prvog reda tabele.");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (table.getRowCount()>0)
			table.setRowSelectionInterval(0, 0);
	}

}
