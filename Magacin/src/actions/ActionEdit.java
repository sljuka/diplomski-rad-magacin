package actions;


import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import controllers.DialogController;

public class ActionEdit extends AbstractAction {

	private DialogController controller;
	
	public ActionEdit(DialogController controller) {
		// TODO Auto-generated constructor stub
		putValue(SMALL_ICON, new ImageIcon("images/edit.gif"));
		this.controller = controller;
		putValue(SHORT_DESCRIPTION, "Prelazak u rezim izmene.");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		controller.editAction();
	}

}
