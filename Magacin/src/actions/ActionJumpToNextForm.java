package actions;


import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import controllers.DialogController;


public class ActionJumpToNextForm extends AbstractAction {

	private DialogController controller;
	
	public ActionJumpToNextForm(DialogController controller) {
		// TODO Auto-generated constructor stub
		putValue(SMALL_ICON, new ImageIcon("images/nextform.gif"));
		putValue(SHORT_DESCRIPTION, "Prelazak na sledeci prozor.");
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		controller.jumpToNextForm();
	}

}
