package actions;


import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import controllers.DialogController;


public class ActionJumoToPreviousForm extends AbstractAction {

	DialogController controller;
	
	public ActionJumoToPreviousForm(DialogController cont) {
		// TODO Auto-generated constructor stub
		putValue(SMALL_ICON, new ImageIcon("images/nextform.gif"));
		controller = cont;
		putValue(SHORT_DESCRIPTION, "Povratak na prethodni prozor.");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		controller.jumpToPreviousForm();
	}
}
