package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JTable;

public class ActionSelectLast extends AbstractAction {

	JTable table;
	
	public ActionSelectLast(JTable table) {
		putValue(SMALL_ICON, new ImageIcon("images/last.gif"));
		putValue(SHORT_DESCRIPTION, "Selekcija poslednjeg reda tabele.");
		this.table = table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int rowCount = table.getRowCount();
		if (rowCount > 0)
			table.setRowSelectionInterval(rowCount-1, rowCount-1);
	}

}
