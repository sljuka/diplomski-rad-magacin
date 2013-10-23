package actions;


import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import controllers.DialogController;


public class ActionCancelAction extends AbstractAction {

	DialogController controller;
	
	public ActionCancelAction(DialogController controller) {
		// TODO Auto-generated constructor stub
		putValue(SMALL_ICON, new ImageIcon("images/remove.gif"));
		putValue(SHORT_DESCRIPTION, "Odustani");
		this.controller = controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		controller.cancelAction();
	}
}
