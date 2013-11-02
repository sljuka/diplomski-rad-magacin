package actions;


import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import controllers.FormController;

public class ActionEdit extends AbstractAction {

	private FormController controller;
	
	public ActionEdit(FormController controller) {
		this.controller = controller;
		
		putValue(SMALL_ICON, new ImageIcon("images/edit.gif"));
		putValue(SHORT_DESCRIPTION, "Prelazak u rezim izmene.");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		controller.editAction();
	}

}
