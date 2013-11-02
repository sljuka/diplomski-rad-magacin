package actions;


import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import controllers.FormController;


public class ActionJumpToNextForm extends AbstractAction {

	private FormController controller;
	
	public ActionJumpToNextForm(FormController controller) {
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
