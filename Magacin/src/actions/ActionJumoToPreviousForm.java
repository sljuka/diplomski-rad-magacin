package actions;


import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import controllers.FormController;


public class ActionJumoToPreviousForm extends AbstractAction {

	FormController controller;
	
	public ActionJumoToPreviousForm(FormController cont) {
		// TODO Auto-generated constructor stub
		putValue(SMALL_ICON, new ImageIcon("images/zoom-pickup.gif"));
		controller = cont;
		putValue(SHORT_DESCRIPTION, "Povratak na prethodni prozor.");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		controller.jumpToPreviousForm();
	}
}
