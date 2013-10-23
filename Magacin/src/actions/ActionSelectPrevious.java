package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JTable;

public class ActionSelectPrevious extends AbstractAction {

	private JTable table;
	
	public ActionSelectPrevious(JTable table) {
		putValue(SMALL_ICON, new ImageIcon("images/prev.gif"));
		putValue(SHORT_DESCRIPTION, "Selekcija prethodnog reda tabele.");
		this.table = table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int rowCount = table.getRowCount();
		if (rowCount > 0) {
			int selectedRow = table.getSelectedRow();
			if (selectedRow > 0) {
				table.setRowSelectionInterval(selectedRow - 1, selectedRow - 1);
			}
		}
	}

}
