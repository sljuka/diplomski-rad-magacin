package actions;


import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import controllers.DialogController;


public class ActionCommit extends AbstractAction {

	private DialogController controller;
	
	public ActionCommit(DialogController controller) {
		// TODO Auto-generated constructor stub
		this.controller = controller;
		putValue(SMALL_ICON, new ImageIcon("images/commit.gif"));
		putValue(SHORT_DESCRIPTION, "Potvrdi");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		controller.commitAction();
	}

}
