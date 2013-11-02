package actions;



import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import controllers.FormController;


public class ActionAdd extends AbstractAction {

	FormController controller;

	public ActionAdd(FormController controller) {
		putValue(SMALL_ICON, new ImageIcon("images/add.gif"));
		putValue(SHORT_DESCRIPTION, "Prelazak u rezim dodavanja.");
		
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.addAction();
	}
}
