package controllers;

import gui.DatePickerComponent;
import gui.dialogs.ArtikliForma;
import gui.dialogs.DatabaseForma;
import gui.dialogs.MagacinskeKarticeForma;
import gui.dialogs.MestaForma;
import gui.dialogs.PopisniDokumentForma;
import gui.dialogs.PoslovnaGodinaForma;
import gui.dialogs.PoslovniObjektiForma;
import gui.dialogs.PoslovniPartnerForma;
import gui.dialogs.PreduzecaForma;
import gui.dialogs.PrometniDokumentForma;
import gui.dialogs.StavkePrometnogDokumentaForma;
import gui.dialogs.ZaposleniForma;
import gui.dialogs.controller.states.AddState;
import gui.dialogs.controller.states.EditState;
import gui.dialogs.controller.states.SearchState;
import gui.dialogs.controller.states.State;

import java.awt.Component;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.DataBaseTableModel.tableNames;


public class FormController {

	private State currentState;

	private DatabaseForma databaseForm;

	public FormController() {
		setCurrentState(new EditState());
	}
	
	public void setForm(DatabaseForma databaseForm) {
		this.databaseForm = databaseForm;
			databaseForm.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				currentState.mousePressed(e, FormController.this);
			}
		});
		databaseForm.getStatusBar().setText(currentState.getName());
	}

	public void addAction() {
		// TODO Auto-generated method stub
		try {
			this.getDatabaseDialog().getModel().refreshData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Component c = databaseForm.getInputs()[0];
		c.requestFocusInWindow();
		databaseForm.setFieldsEditable(true);
		databaseForm.setZoomButtons(true);
		for (Component comp : databaseForm.getInputs()) {
			if (comp instanceof JTextField) {
				JTextField tfcast = (JTextField) comp;
				tfcast.setText("");
			}
		}
		setCurrentState(new AddState());
	}

	public void cancelAction() {
		// TODO Auto-generated method stub
		for (Component comp : databaseForm.getInputs()) {
			if (comp instanceof JTextField) {
				JTextField tfcast = (JTextField) comp;
				tfcast.setText("");
			}
		}
		databaseForm.setKeyFilter(null);
		databaseForm.getTable().clearSelection();
		databaseForm.setFieldsEditable(false);
		setCurrentState(new EditState());
	}

	public void commitAction() {
		// TODO Auto-generated method stub
		currentState.handleState(this);
	}

	public void jumpToPreviousForm() {
		// TODO Auto-generated method stub
		if (databaseForm.getParentDialog() == null)
			return;
		if (databaseForm.getParrentsTextField() != null) {
			databaseForm.getParrentsTextField().setText(databaseForm.getChildRetVals()[0]);
			databaseForm.setVisible(false);
			return;
		}
		String[] childRetVals = databaseForm.getChildRetVals();
		if (childRetVals[0] == null || childRetVals[0].equals(""))		// Bar prvi mora da ima neku informaciju!!!
			return;
		databaseForm.getParentDialog().childResponse(databaseForm.getTableName(), childRetVals);
		databaseForm.dispose();
	}

	public void jumpToNextForm() {
		// TODO Auto-generated method stub

	}

	public void deleteAction() {
		// TODO Auto-generated method stub
		int selectedRow = databaseForm.getTable().getSelectedRow();
		if (selectedRow<0)
			return;
		if(JOptionPane.showConfirmDialog(databaseForm, "Delete selected row?", "Confirmation", JOptionPane.YES_NO_OPTION) == 0){
			try {
				databaseForm.getModel().deleteRow(selectedRow, databaseForm.getPrimaryKeysColumnNumbers());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				if (e.getMessage().startsWith("The DELETE statement conflicted with the REFERENCE constraint"))
					JOptionPane.showMessageDialog(databaseForm, "Stavka se ne moze obrisati posto je povezana s drugom stavkom u bazi",
							"Confirmation", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}

	public void searchAction() {
		// TODO Auto-generated method stub
		databaseForm.setFieldsEditable(true);
		Component c = databaseForm.getInputs()[0];  //omoguci unos u textfildove
		c.requestFocusInWindow();
		
		for (Component comp : databaseForm.getInputs()) {
			if (comp instanceof JTextField) {
				JTextField tfcast = (JTextField) comp;
				tfcast.setText("");
			}
		}
		setCurrentState(new SearchState());		
		this.getDatabaseDialog().getModel().getValues().clear();   //isprazni tabelu
		this.getDatabaseDialog().getModel().fireTableDataChanged();
		databaseForm.setFieldsEditable(true);
	}

	public String[] getComponentStrings() {
		String[] retVal = new String[databaseForm.getInputs().length];
		Component[] components = databaseForm.getInputs(); 
		for (int i = 0; i<components.length; i++) {
			if (components[i] instanceof JTextField)
				retVal[i] = ((JTextField)components[i]).getText();
			if (components[i] instanceof JCheckBox) {
				JCheckBox cb = (JCheckBox)components[i];
				retVal[i] = cb.isSelected()?"True":"False";
			}
			if (components[i] instanceof JComboBox) {
				retVal[i] = databaseForm.comboBoxHandler((JComboBox) components[i]);
			}
			if (components[i] instanceof DatePickerComponent) {
				retVal[i] = ((DatePickerComponent)components[i]).getText();
			}
			if (retVal[i].equals(""))
				retVal[i] = null;
		}

		return retVal;
	}

	public DatabaseForma getDatabaseDialog() {
		return databaseForm;
	}
	
	public void next() {
		
		if(databaseForm.getTable().getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(	databaseForm,
											"Morate imati selektovani red",
											"Obavestenje",
											JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		tableNames name = databaseForm.getTableName();
		int mousePosX = MouseInfo.getPointerInfo().getLocation().x - databaseForm.getLocation().x;
		int mousePosY = MouseInfo.getPointerInfo().getLocation().y - databaseForm.getLocation().y;
		final JPopupMenu popup = new JPopupMenu();
		
		switch (name) {
		case DRZAVA:
			popup.add(new JMenuItem(new AbstractAction("Naseljena mesta") {
				@Override
				public void actionPerformed(ActionEvent arg1) {
					// TODO Auto-generated method stub
					DatabaseForma d = new MestaForma();
					setupFilterAndVisibility(d);			
				}
			}));
			break;
			
		case NASELJENO_MESTO:
			popup.add(new JMenuItem(new AbstractAction("Preduzeca") {
				@Override
				public void actionPerformed(ActionEvent arg1) {
					// TODO Auto-generated method stub
					DatabaseForma d = new PreduzecaForma();
					setupFilterAndVisibility(d);
				}
			}));
			break;
		
		case PREDUZECE:
			popup.add(new JMenuItem(new AbstractAction("Zaposleni") {
				@Override
				public void actionPerformed(ActionEvent arg1) {
					// TODO Auto-generated method stub
					DatabaseForma d = new ZaposleniForma();
					setupFilterAndVisibility(d);
				}
			}));
			
			popup.add(new JMenuItem(new AbstractAction("Poslovni partneri") {
				@Override
				public void actionPerformed(ActionEvent arg1) {
					// TODO Auto-generated method stub
					DatabaseForma d = new PoslovniPartnerForma();
					setupFilterAndVisibility(d);
				}
			}));
			
			popup.add(new JMenuItem(new AbstractAction("Poslovni objekat") {
				@Override
				public void actionPerformed(ActionEvent arg1) {
					// TODO Auto-generated method stub
					DatabaseForma d = new PoslovniObjektiForma();
					setupFilterAndVisibility(d);
				}
			}));
			popup.add(new JMenuItem(new AbstractAction("Poslovne godine") {
				@Override
				public void actionPerformed(ActionEvent arg1) {
					// TODO Auto-generated method stub
					DatabaseForma d = new PoslovnaGodinaForma();
					setupFilterAndVisibility(d);
				}
			}));
			popup.add(new JMenuItem(new AbstractAction("Artikli") {
				@Override
				public void actionPerformed(ActionEvent arg1) {
					// TODO Auto-generated method stub
					DatabaseForma d = new ArtikliForma();
					setupFilterAndVisibility(d);
				}
			}));
			break;
	
		case POSLOVNI_OBJEKAT:
			popup.add(new JMenuItem(new AbstractAction("Popisni dokument") {
				@Override
				public void actionPerformed(ActionEvent arg1) {
					// TODO Auto-generated method stub
					DatabaseForma d = new PopisniDokumentForma();
					setupFilterAndVisibility(d);
				}
			}));
			popup.add(new JMenuItem(new AbstractAction("Magacinske kartice") {
				@Override
				public void actionPerformed(ActionEvent arg1) {
					// TODO Auto-generated method stub
					DatabaseForma d = new MagacinskeKarticeForma();
					setupFilterAndVisibility(d);
				}
			}));
			break;
		
		case POSLOVNA_GODINA:
			popup.add(new JMenuItem(new AbstractAction("Prometni dokumenti") {
				@Override
				public void actionPerformed(ActionEvent arg1) {
					// TODO Auto-generated method stub
					DatabaseForma d = new PrometniDokumentForma();
					setupFilterAndVisibility(d);
				}
			}));
			break;
			
		case PROMETNI_DOKUMENT:
			popup.add(new JMenuItem(new AbstractAction("Stavke prometnog dokumenta") {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					DatabaseForma d = new StavkePrometnogDokumentaForma();
					setupFilterAndVisibility(d);
				}
			}));
			break;
		default:
			JOptionPane.showMessageDialog(	databaseForm,
					"Nije implementirano",
					"Obavestenje",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		popup.show(databaseForm, mousePosX, mousePosY);
		
	}

	public void setCurrentState(State state) {
		if (currentState instanceof SearchState) {
			try {
				databaseForm.getModel().refreshData();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		currentState = state;
	}

	public void zakljuciGodinu(String[] godina) {
		// TODO Auto-generated method stub
		if(JOptionPane.showConfirmDialog(databaseForm, "Da li ste sigurni da zelite da zakljucite godinu?", 
				"Zakljucivanje godine", JOptionPane.OK_CANCEL_OPTION) == 0) {
			int index = databaseForm.getTable().getSelectedRow();
			try {
				databaseForm.getModel().zakljuciGodinu(index, godina);
				databaseForm.getTable().setRowSelectionInterval(index, index);
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
		if(JOptionPane.showConfirmDialog(databaseForm, "Da li ste sigurni da zelite da proknjizite prometni dokument?", 
				"Proknjizavanje", JOptionPane.OK_CANCEL_OPTION) == 0) {
			int index = databaseForm.getTable().getSelectedRow();
			databaseForm.getModel().proknjiziDokument(index, strings);
		}
	}


	public void editAction() {
		// TODO Auto-generated method stub
		setCurrentState(new EditState());
	}

	public void setupFilterAndVisibility(DatabaseForma d) {
		String[] keyPairs = databaseForm.getModel().getSelectedKeyNameValuePair(databaseForm.getTable().getSelectedRow());
		if(keyPairs == null) {
			JOptionPane.showMessageDialog(	databaseForm,
											"Nije implementiran menu item",
											"Obavestenje",
											JOptionPane.WARNING_MESSAGE);
			return;
		}
		d.setKeyFilter(keyPairs);
		d.setVisible(true);
		//databaseDialog.dispose();
	}
}
