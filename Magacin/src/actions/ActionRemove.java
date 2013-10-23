package actions;


import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import controllers.DialogController;


public class ActionRemove extends AbstractAction {

	private DialogController controller;

	public ActionRemove(DialogController controller) {
		putValue(SMALL_ICON, new ImageIcon("images/remove.gif"));
		putValue(SHORT_DESCRIPTION, "Brisanje selektovanog reda");
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.deleteAction();
	}
}
