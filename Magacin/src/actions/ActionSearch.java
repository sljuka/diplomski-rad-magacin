package actions;

import gui.dialogs.DatabaseDialog;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import controllers.DialogController;


public class ActionSearch extends AbstractAction {

	private DialogController controller;

	public ActionSearch(DialogController controller) {
		// TODO Auto-generated constructor stub
		putValue(SMALL_ICON, new ImageIcon("images/search.gif"));
		putValue(SHORT_DESCRIPTION, "Prelazak u rezim pretrage.");
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		controller.searchAction();
		
		
	}

}
