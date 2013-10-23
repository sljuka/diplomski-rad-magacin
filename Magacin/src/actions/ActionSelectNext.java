package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JTable;

public class ActionSelectNext extends AbstractAction {

	private JTable table;

	public ActionSelectNext(JTable table) {
		// TODO Auto-generated constructor stub
		putValue(SMALL_ICON, new ImageIcon("images/next.gif"));
		putValue(SHORT_DESCRIPTION, "Selekcija narednog reda tabele.");
		this.table = table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int rowCount = table.getRowCount();
		if (rowCount > 0) {
			int selectedRow = table.getSelectedRow();
			if (selectedRow < rowCount -1) {
				table.setRowSelectionInterval(selectedRow + 1, selectedRow + 1);
			}

		}
	}

}
