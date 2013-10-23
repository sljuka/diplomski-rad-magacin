package gui.dialogs.controller.states;

import java.awt.event.MouseEvent;

import controllers.DialogController;


public abstract class State {

	public abstract void handleState(DialogController controller);
	
	public abstract  String getName();
	
	public abstract void mousePressed(MouseEvent e, DialogController controller);
}
