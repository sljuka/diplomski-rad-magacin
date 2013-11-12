package gui.forms.controller.states;


import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import controllers.FormController;


public class AddState extends State {

	@Override
	public void handleState(FormController controller) {
		// TODO Auto-generated method stub
		if(!controller.getDatabaseDialog().areRequiredFieldsEntered()) {
			JOptionPane.showMessageDialog(	controller.getDatabaseDialog(),
					"Morate popuniti sva obavezna polja",
					"Obavestenje",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(JOptionPane.showConfirmDialog(controller.getDatabaseDialog(), "Da li ste sigurni da zelite da dodate novi podatak", 
				"Dodavanje podataka", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.CANCEL_OPTION)
			return;
		
		try {
			int index = controller.getDatabaseDialog().getModel().insertRow(controller.getComponentStrings());
			if (index >= 0) {
				controller.getDatabaseDialog().getTable().setRowSelectionInterval(index, index);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if (e.getMessage().startsWith("Violation of PRIMARY KEY constraint"))
				JOptionPane.showMessageDialog(null, "Stavka sa odabranim kljucem vec postoji u bazi", 
							"Dodavanje stavke", JOptionPane.ERROR_MESSAGE);
			else
				e.printStackTrace();
		}
		controller.setCurrentState(new EditState());
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Add state";
	}

	@Override
	public void mousePressed(MouseEvent e, FormController controller) {
		// TODO Auto-generated method stub
		controller.setCurrentState(new EditState());
	}

}
