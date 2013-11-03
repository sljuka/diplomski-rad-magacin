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

	private DatabaseForma databaseDialog;

	public FormController(DatabaseForma databaseDialog) {
		// TODO Auto-generated constructor stub
		this.databaseDialog = databaseDialog;
		setCurrentState(new EditState());
		databaseDialog.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				currentState.mousePressed(e, FormController.this);
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
		databaseDialog.setKeyFilter(null);
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

	public DatabaseForma getDatabaseDialog() {
		return databaseDialog;
	}
	
	public void next() {
		
		if(databaseDialog.getTable().getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(	databaseDialog,
											"Morate imati selektovani red",
											"Obavestenje",
											JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		tableNames name = databaseDialog.getTableName();
		int mousePosX = MouseInfo.getPointerInfo().getLocation().x - databaseDialog.getLocation().x;
		int mousePosY = MouseInfo.getPointerInfo().getLocation().y - databaseDialog.getLocation().y;
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
			JOptionPane.showMessageDialog(	databaseDialog,
					"Nije implementirano",
					"Obavestenje",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		popup.show(databaseDialog, mousePosX, mousePosY);
		
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

	public void setupFilterAndVisibility(DatabaseForma d) {
		String[] keyPairs = databaseDialog.getModel().getSelectedKeyNameValuePair(databaseDialog.getTable().getSelectedRow());
		if(keyPairs == null) {
			JOptionPane.showMessageDialog(	databaseDialog,
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
