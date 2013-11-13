package controllers;

import gui.DatePickerComponent;
import gui.Input;
import gui.forms.ArtikliForma;
import gui.forms.DatabaseForma;
import gui.forms.MagacinskeKarticeForma;
import gui.forms.MestaForma;
import gui.forms.PopisniDokumentForma;
import gui.forms.PoslovnaGodinaForma;
import gui.forms.PoslovniObjektiForma;
import gui.forms.PoslovniPartnerForma;
import gui.forms.PreduzecaForma;
import gui.forms.PrometniDokumentForma;
import gui.forms.StavkePrometnogDokumentaForma;
import gui.forms.ZaposleniForma;
import gui.forms.controller.states.AddState;
import gui.forms.controller.states.EditState;
import gui.forms.controller.states.SearchState;
import gui.forms.controller.states.State;

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
		databaseForm.setFieldsEditable(true);
		for (Input comp : databaseForm.getInputs()) {
			comp.setText("");
		}
		databaseForm.beforeAdd();	
		setCurrentState(new AddState());
	}

	public void cancelAction() {
		// TODO Auto-generated method stub
		for (Input comp : databaseForm.getInputs()) {
			comp.setText("");
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
				return;
			}
		}
	}

	public void searchAction() {
		// TODO Auto-generated method stub
		databaseForm.setFieldsEditable(true);
		
		for (Input comp : databaseForm.getInputs()) {
			comp.setText("");
		}
		setCurrentState(new SearchState());		
		this.getDatabaseDialog().getModel().getValues().clear();   //isprazni tabelu
		this.getDatabaseDialog().getModel().fireTableDataChanged();
	}

	public String[] getComponentStrings() {
		String[] retVal = new String[databaseForm.getInputs().length];
		Input[] components = databaseForm.getInputs(); 
		for (int i = 0; i<components.length; i++) {
			retVal[i] = components[i].getText();
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
					FormController fc = new FormController();
					DatabaseForma d = new MestaForma(fc);
					fc.setForm(d);
					setupFilterAndVisibility(d);			
				}
			}));
			break;
			
		case NASELJENO_MESTO:
			popup.add(new JMenuItem(new AbstractAction("Preduzeca") {
				@Override
				public void actionPerformed(ActionEvent arg1) {
					// TODO Auto-generated method stub
					FormController fc = new FormController();
					DatabaseForma d = new PreduzecaForma(fc);
					fc.setForm(d);
					setupFilterAndVisibility(d);
				}
			}));
			break;
		
		case PREDUZECE:
			popup.add(new JMenuItem(new AbstractAction("Zaposleni") {
				@Override
				public void actionPerformed(ActionEvent arg1) {
					// TODO Auto-generated method stub
					FormController fc = new FormController();
					DatabaseForma d = new ZaposleniForma(fc);
					fc.setForm(d);
					setupFilterAndVisibility(d);
				}
			}));
			
			popup.add(new JMenuItem(new AbstractAction("Poslovni partneri") {
				@Override
				public void actionPerformed(ActionEvent arg1) {
					// TODO Auto-generated method stub
					FormController fc = new FormController();
					DatabaseForma d = new PoslovniPartnerForma(fc);
					fc.setForm(d);
					setupFilterAndVisibility(d);
				}
			}));
			
			popup.add(new JMenuItem(new AbstractAction("Poslovni objekat") {
				@Override
				public void actionPerformed(ActionEvent arg1) {
					// TODO Auto-generated method stub
					FormController fc = new FormController();
					DatabaseForma d = new PoslovniObjektiForma(fc);
					fc.setForm(d);
					setupFilterAndVisibility(d);
				}
			}));
			popup.add(new JMenuItem(new AbstractAction("Poslovne godine") {
				@Override
				public void actionPerformed(ActionEvent arg1) {
					// TODO Auto-generated method stub
					FormController fc = new FormController();
					DatabaseForma d = new PoslovnaGodinaForma(fc);
					fc.setForm(d);
					setupFilterAndVisibility(d);
				}
			}));
			popup.add(new JMenuItem(new AbstractAction("Artikli") {
				@Override
				public void actionPerformed(ActionEvent arg1) {
					// TODO Auto-generated method stub
					FormController fc = new FormController();
					DatabaseForma d = new ArtikliForma(fc);
					fc.setForm(d);
					setupFilterAndVisibility(d);
				}
			}));
			break;
	
		case POSLOVNI_OBJEKAT:
			popup.add(new JMenuItem(new AbstractAction("Popisni dokument") {
				@Override
				public void actionPerformed(ActionEvent arg1) {
					// TODO Auto-generated method stub
					FormController fc = new FormController();
					DatabaseForma d = new PopisniDokumentForma(fc);
					fc.setForm(d);
					setupFilterAndVisibility(d);
				}
			}));
			popup.add(new JMenuItem(new AbstractAction("Magacinske kartice") {
				@Override
				public void actionPerformed(ActionEvent arg1) {
					// TODO Auto-generated method stub
					FormController fc = new FormController();
					DatabaseForma d = new MagacinskeKarticeForma(fc);
					fc.setForm(d);
					setupFilterAndVisibility(d);
				}
			}));
			break;
		
		case POSLOVNA_GODINA:
			popup.add(new JMenuItem(new AbstractAction("Prometni dokumenti") {
				@Override
				public void actionPerformed(ActionEvent arg1) {
					// TODO Auto-generated method stub
					FormController fc = new FormController();
					DatabaseForma d = new PrometniDokumentForma(fc);
					fc.setForm(d);
					setupFilterAndVisibility(d);
				}
			}));
			break;
			
		case PROMETNI_DOKUMENT:
			popup.add(new JMenuItem(new AbstractAction("Stavke prometnog dokumenta") {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					FormController fc = new FormController();
					DatabaseForma d = new StavkePrometnogDokumentaForma(fc);
					fc.setForm(d);
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
		if(databaseForm != null)
			databaseForm.getStatusBar().setText(state.getName());
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
