package gui.dialogs;

import gui.DatePickerComponent;
import gui.StatusBar;

import java.awt.Component;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controllers.FormController;

import net.miginfocom.swing.MigLayout;

import model.DataBaseTableModel;
import model.DataBaseTableModel.tableNames;
import actions.ActionAdd;
import actions.ActionEdit;
import actions.ActionHelp;
import actions.ActionJumoToPreviousForm;
import actions.ActionNext;
import actions.ActionRefresh;
import actions.ActionRemove;
import actions.ActionSearch;
import actions.ActionSelectFirst;
import actions.ActionSelectLast;
import actions.ActionSelectNext;
import actions.ActionSelectPrevious;
import app.MainFrame;

public abstract class DatabaseForma extends JDialog {

	protected tableNames ID;

	protected JTable table;
	protected JToolBar toolbar;
	protected Component[] editableFields;
	protected FormController controller;
	protected DataBaseTableModel model;

	protected JPanel btnPanel;
	
	protected DatabaseForma parentDialog;

	protected String[] childRetVals;

	protected String[] keyFilter;

	protected int[] primaryKeysColumnNumber;

	protected Component[] statusBasedButtons;

	protected JTextField parrentsTextField;
	
	protected StatusBar statusBar;

	public DatabaseForma() {
		// TODO Auto-generated constructor stub
		super();
		setLocationRelativeTo(MainFrame.getInstance());
		setModal(true);
		setLayout(new MigLayout("", "[align r][align l, grow, fill]", ""));
		childRetVals = new String[4]; //Najvise 4 stringa child vraca parentu!
		initializePrimaryKeysNumbers();
		statusBar = new StatusBar();
	}

	protected void setSizeAndMove(int width, int height) {
		setSize(width, height);
		setLocationRelativeTo(null);
	}
	
	protected abstract void initializeComponents();
	
	public abstract void populateFieldsArray();

	public Component[] getFields() {
		populateFieldsArray();
		return editableFields;
	}

	public DataBaseTableModel getModel() {
		return model;
	}

	public DatabaseForma getParentDialog() {
		return parentDialog;
	}

	public void setParentDialog(DatabaseForma parentDialog) {
		this.parentDialog = parentDialog;
		initializeToolbarForChild();
		if(btnPanel != null)
			btnPanel.setVisible(false);
	}

	public String[] getChildRetVals() {
		return childRetVals;
	}

	public abstract void childResponse(tableNames iD2, String[] childRetVals);

	protected abstract void sync();

	public JTable getTable() {
		return table;
	}

	protected void initializeToolbar() {
		toolbar = new JToolBar();
		toolbar.add(new ActionSearch(controller));
		toolbar.add(new ActionRefresh(controller));
		toolbar.add(new ActionHelp());
		toolbar.addSeparator();
		toolbar.add(new ActionSelectFirst(table));
		toolbar.add(new ActionSelectPrevious(table));
		toolbar.add(new ActionSelectNext(table));
		toolbar.add(new ActionSelectLast(table));
		toolbar.addSeparator();
		toolbar.add(new ActionAdd(controller));
		toolbar.add(new ActionRemove(controller));
		toolbar.addSeparator();
		toolbar.add(new ActionNext(controller));
	}
	
	protected void initializeToolbarForChild() {
		toolbar.removeAll();
		toolbar.add(new ActionSearch(controller));
		toolbar.add(new ActionRefresh(controller));
		toolbar.add(new ActionHelp());
		toolbar.addSeparator();
		toolbar.add(new ActionSelectFirst(table));
		toolbar.add(new ActionSelectPrevious(table));
		toolbar.add(new ActionSelectNext(table));
		toolbar.add(new ActionSelectLast(table));
		toolbar.addSeparator();
		toolbar.add(new ActionJumoToPreviousForm(controller));
	}

	protected void initMagacinKarticaToolBar() {
		toolbar = new JToolBar();
		toolbar.add(new ActionSearch(controller));
		toolbar.add(new ActionRefresh(controller));
		//toolbar.add(new ActionJumoToPreviousForm(controller));
		toolbar.add(new ActionHelp());
		toolbar.addSeparator();
		toolbar.add(new ActionSelectFirst(table));
		toolbar.add(new ActionSelectPrevious(table));
		toolbar.add(new ActionSelectNext(table));
		toolbar.add(new ActionSelectLast(table));
		toolbar.addSeparator();
		toolbar.add(new ActionJumoToPreviousForm(controller));

	}

	protected void initializeTable() {
		// TODO Auto-generated method stub
		table = new JTable();
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		model = new DataBaseTableModel(ID);
		table.setModel(model);
		try {
			model.open(keyFilter);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						// TODO Auto-generated method stub
						if (e.getValueIsAdjusting())
							return;
						sync();
					}
				});
	}

	public String[] getKeyFilter() {
		return keyFilter;
	}

	public void setKeyFilter(String[] keyFilter) {
		this.keyFilter = keyFilter;
		model.setKeyFilter(keyFilter);
	}

	public abstract void populateStatusBasedComponents();

	public void setEnabledStatusBasedComponents(boolean b) {
		for (Component c : statusBasedButtons) {
			if (c != null) {
				if (c instanceof JButton)
					((JButton) c).setEnabled(b);
			}
		}
	}

	public abstract void initializePrimaryKeysNumbers();

	public tableNames getID() {
		return ID;
	}

	public int[] getPrimaryKeysColumnNumbers() {
		return primaryKeysColumnNumber;
	}

	public String comboBoxHandler(JComboBox c) {
		return null;
	}

	public JTextField getParrentsTextField() {
		return parrentsTextField;
	}

	public void setFieldsEditable(boolean b) {
		for (Component c : editableFields) {
			if (c instanceof JTextField)
				((JTextField) c).setEditable(b);
			if (c instanceof JComboBox)
				c.setEnabled(b);
			if (c instanceof DatePickerComponent)
				((DatePickerComponent) c).setEditable(b);
		}
	}
	
	public void initializeStatusBar() {
		add(statusBar, "south, growx");
	}

	public StatusBar getStatusBar() {
		return statusBar;
	}
	
	public void setZoomButtons(Boolean b){
		
	}
	
	public void disableFields(){
		setZoomButtons(false);
		for (Component c : editableFields){
			if (c instanceof JTextField)
				((JTextField) c).setEditable(false);
			if (c instanceof JComboBox)
				c.setEnabled(false);
			if (c instanceof DatePickerComponent)
				((DatePickerComponent) c).setEditable(false);
		}
	}
	
	public tableNames getTableName() {
		return ID;
	}

}
