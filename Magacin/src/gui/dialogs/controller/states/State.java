package gui.dialogs.controller.states;

import java.awt.event.MouseEvent;

import controllers.FormController;


public abstract class State {

	public abstract void handleState(FormController controller);
	
	public abstract  String getName();
	
	public abstract void mousePressed(MouseEvent e, FormController controller);
}
