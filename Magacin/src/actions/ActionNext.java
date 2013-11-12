package actions;

import gui.forms.DrzaveForma;
import gui.forms.MestaForma;

import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import app.MainFrame;

import model.DataBaseTableModel.tableNames;

import controllers.FormController;

public class ActionNext extends AbstractAction {

	private FormController controller;
	
	public ActionNext(FormController controller) {
		// TODO Auto-generated constructor stub
		putValue(SMALL_ICON, new ImageIcon("images/nextform.gif"));
		putValue(SHORT_DESCRIPTION, "Next.");
		this.controller = controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		controller.next();
	}

}
