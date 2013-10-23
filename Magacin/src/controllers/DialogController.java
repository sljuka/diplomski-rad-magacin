package controllers;

import gui.DatePickerComponent;
import gui.dialogs.DatabaseDialog;
import gui.dialogs.controller.states.AddState;
import gui.dialogs.controller.states.EditState;
import gui.dialogs.controller.states.SearchState;
import gui.dialogs.controller.states.State;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class DialogController {

	private State currentState;

	private DatabaseDialog databaseDialog;

	public DialogController(DatabaseDialog databaseDialog) {
		// TODO Auto-generated constructor stub
		this.databaseDialog = databaseDialog;
		setCurrentState(new EditState());
		databaseDialog.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				currentState.mousePressed(e, DialogController.this);
		//		setCurrentState(new EditState());
			}
		});
	}


	public void addAction() {
		// TODO Auto-generated method stub
		try {
			this.getDatabaseDialog().getModel().refreshData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Component c = databaseDialog.getFields()[0];
		c.requestFocusInWindow();
		databaseDialog.setFieldsEditable(true);
		databaseDialog.setZoomButtons(true);
		for (Component comp : databaseDialog.getFields()) {
			if (comp instanceof JTextField) {
				JTextField tfcast = (JTextField) comp;
				tfcast.setText("");
			}
		}
		setCurrentState(new AddState());
	}

	public void cancelAction() {
		// TODO Auto-generated method stub
		for (Component comp : databaseDialog.getFields()) {
			if (comp instanceof JTextField) {
				JTextField tfcast = (JTextField) comp;
				tfcast.setText("");
			}
		}
		databaseDialog.getTable().clearSelection();
		databaseDialog.setFieldsEditable(false);
		setCurrentState(new EditState());
	}

	public void commitAction() {
		// TODO Auto-generated method stub
		currentState.handleState(this);
	}

	public void jumpToPreviousForm() {
		// TODO Auto-generated method stub
		if (databaseDialog.getParentDialog() == null)
			return;
		if (databaseDialog.getParrentsTextField() != null) {
			databaseDialog.getParrentsTextField().setText(databaseDialog.getChildRetVals()[0]);
			databaseDialog.setVisible(false);
			return;
		}
		String[] childRetVals = databaseDialog.getChildRetVals();
		if (childRetVals[0] == null || childRetVals[0].equals(""))		// Bar prvi mora da ima neku informaciju!!!
			return;
		databaseDialog.getParentDialog().childResponse(databaseDialog.getID(), childRetVals);
		databaseDialog.dispose();
	}

	public void jumpToNextForm() {
		// TODO Auto-generated method stub

	}

	public void deleteAction() {
		// TODO Auto-generated method stub
		int selectedRow = databaseDialog.getTable().getSelectedRow();
		if (selectedRow<0)
			return;
		if(JOptionPane.showConfirmDialog(databaseDialog, "Delete selected row?", "Confirmation", JOptionPane.YES_NO_OPTION) == 0){
			try {
				databaseDialog.getModel().deleteRow(selectedRow, databaseDialog.getPrimaryKeysColumnNumbers());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				if (e.getMessage().startsWith("The DELETE statement conflicted with the REFERENCE constraint"))
					JOptionPane.showMessageDialog(databaseDialog, "Stavka se ne moze obrisati posto je povezana s drugom stavkom u bazi",
							"Confirmation", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}

	public void searchAction() {
		// TODO Auto-generated method stub
		databaseDialog.setFieldsEditable(true);
		Component c = databaseDialog.getFields()[0];  //omoguci unos u textfildove
		c.requestFocusInWindow();
		
		for (Component comp : databaseDialog.getFields()) {
			if (comp instanceof JTextField) {
				JTextField tfcast = (JTextField) comp;
				tfcast.setText("");
			}
		}
		setCurrentState(new SearchState());		
		this.getDatabaseDialog().getModel().getValues().clear();   //isprazni tabelu
		this.getDatabaseDialog().getModel().fireTableDataChanged();
		databaseDialog.setFieldsEditable(true);
	}

	public String[] getComponentStrings() {
		String[] retVal = new String[databaseDialog.getFields().length];
		Component[] components = databaseDialog.getFields(); 
		for (int i = 0; i<components.length; i++) {
			if (components[i] instanceof JTextField)
				retVal[i] = ((JTextField)components[i]).getText();
			if (components[i] instanceof JCheckBox) {
				JCheckBox cb = (JCheckBox)components[i];
				retVal[i] = cb.isSelected()?"True":"False";
			}
			if (components[i] instanceof JComboBox) {
				retVal[i] = databaseDialog.comboBoxHandler((JComboBox) components[i]);
			}
			if (components[i] instanceof DatePickerComponent) {
				retVal[i] = ((DatePickerComponent)components[i]).getText();
			}
			
				
			if (retVal[i].equals(""))
				retVal[i] = null;
		}

		return retVal;
	}

	public DatabaseDialog getDatabaseDialog() {
		return databaseDialog;
	}

	public void setCurrentState(State state) {
		if (currentState instanceof SearchState) {
			try {
				databaseDialog.getModel().refreshData();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		currentState = state;
		databaseDialog.getStatusBar().setText(state.getName());
	}

	public void zakljuciGodinu(String[] godina) {
		// TODO Auto-generated method stub
		if(JOptionPane.showConfirmDialog(databaseDialog, "Da li ste sigurni da zelite da zakljucite godinu?", 
				"Zakljucivanje godine", JOptionPane.OK_CANCEL_OPTION) == 0) {
			int index = databaseDialog.getTable().getSelectedRow();
			try {
				databaseDialog.getModel().zakljuciGodinu(index, godina);
				databaseDialog.getTable().setRowSelectionInterval(index, index);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void stornirajDokument(String[] strings) {
		// TODO Auto-generated method stub

	}


	public void proknjiziDokument(String[] strings) {
		// TODO Auto-generated method stub
		if(JOptionPane.showConfirmDialog(databaseDialog, "Da li ste sigurni da zelite da proknjizite prometni dokument?", 
				"Proknjizavanje", JOptionPane.OK_CANCEL_OPTION) == 0) {
			int index = databaseDialog.getTable().getSelectedRow();
			databaseDialog.getModel().proknjiziDokument(index, strings);
		}
	}


	public void editAction() {
		// TODO Auto-generated method stub
		setCurrentState(new EditState());
	}

}
