package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

public class ActionRefresh extends AbstractAction {

	public ActionRefresh() {
		// TODO Auto-generated constructor stub
		putValue(SMALL_ICON, new ImageIcon("images/refresh.gif"));
		putValue(SHORT_DESCRIPTION, "Osvezi prikaz tabele.");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
