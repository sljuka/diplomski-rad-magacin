package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

public class ActionHelp extends AbstractAction {

	public ActionHelp() {
		// TODO Auto-generated constructor stub
		putValue(SMALL_ICON, new ImageIcon("images/help.gif"));
		putValue(SHORT_DESCRIPTION, "Pomoc?");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
