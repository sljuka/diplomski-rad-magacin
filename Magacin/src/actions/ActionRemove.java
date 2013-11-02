package actions;


import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import controllers.FormController;


public class ActionRemove extends AbstractAction {

	private FormController controller;

	public ActionRemove(FormController controller) {
		putValue(SMALL_ICON, new ImageIcon("images/remove.gif"));
		putValue(SHORT_DESCRIPTION, "Brisanje selektovanog reda");
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.deleteAction();
	}
}
