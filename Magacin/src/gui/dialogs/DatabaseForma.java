package gui.dialogs;

import gui.DatePickerComponent;
import gui.IInput;
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
import javax.swing.JScrollPane;
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
import actions.ActionCancelAction;
import actions.ActionCommit;
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

	// ime tabele u bazi koja je predstavljena u formi
	protected tableNames tableName;

	// gui komponente u formi
	protected JToolBar toolbar;
	protected JTable table;
	protected JPanel inputPanel;
	protected JPanel executeCancelPanel;
	protected StatusBar statusBar;
	
	// polja za unos (text fieldovi, checkboxovi, comboboxovi koji se nalaze na formi
	protected IInput[] inputsArray;
	
	protected JButton[] procedures;
	
	// controller, najverovatnije cu izbaciti
	protected FormController controller;
	
	// model za pristup bazi ujedno model gui tabele
	protected DataBaseTableModel model;
		
	// parent dialog prilikom aktiviranja zoom polja
	protected DatabaseForma parentDialog;

	// povratne vrednosti koje vraca zoom forma parent formi
	protected String[] childRetVals;
	
	/*
	*  	where filter koji se koristi prilikom prelaska u formu next akcijom
	*	koristi se u formatu [ polje, vrednost, polje, vrednost...]
	*/
	protected String[] keyFilter;

	/*
	 *	niz intova koji predstavljaju broj kolona koje ulaze u sastav primarnog kljuca
	 */
	protected int[] primaryKeysColumnNumber;

	protected Component[] statusBasedButtons;
	
	/*
	 *	niz intova koji predstavljaju brojeve polja za unos koja su obavezna
	 *	prilikom dodavanja i editovanja
	 */
	protected int[] requiredFields;
	
	/*
	 *	parent zoom polje koje se popunjava da prilikom povratka iz zoom polja na parent formu
	 */
	protected JTextField parrentsTextField;
	
	public DatabaseForma(FormController controller, tableNames tableName, int width, int height) {
		// TODO Auto-generated constructor stub
		super();
		
		this.tableName = tableName;
		setTitle(tableName.toString());
		
		setSizeAndMove(width, height);
		setModal(true);
		
		childRetVals = new String[4]; //Najvise 4 stringa child vraca parentu!
		initializeComponents(controller);
		populateInputsAndRequiredArray();
		layoutComponents();
		
		initializePrimaryKeysNumbers();
		model.setPrimaryKeysNumbers(primaryKeysColumnNumber);
		
		setFieldsEditable(false);
	}

	protected void setSizeAndMove(int width, int height) {
		setSize(width, height);
		setLocationRelativeTo(null);
	}
	
	protected void initializeComponents(FormController controller) {
		initializeStatusBar();
		initializeTable();
		initializeToolbar(controller);
		initializeInputFields(controller);
		initializeExecuteCancelPanel(controller);
		initializeProcedures(controller);
	}
	
	protected void layoutComponents() {
		add(toolbar, "dock north");
		add(new JScrollPane(table), "dock north");
		add(inputPanel);
		add(executeCancelPanel, "cell 3 0");
		add(statusBar, "south, growx");
	}
	
	protected void initializeExecuteCancelPanel(FormController controller) {
		executeCancelPanel = new JPanel();
		executeCancelPanel.setLayout(new MigLayout("align right"));
		executeCancelPanel.add(new JButton(new ActionCommit(controller)), "wrap");
		executeCancelPanel.add(new JButton(new ActionCancelAction(controller)));
	}
	
	protected abstract void initializeInputFields(FormController controller);
	
	protected void initializeProcedures(FormController controller) {
		procedures = new JButton[0];
	}
	
	
	protected void initializeStatusBar() {
		statusBar = new StatusBar();
	}
	
	public abstract void populateInputsAndRequiredArray();

	public Component[] getInputs() {
		return inputsArray;
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
		if(inputPanel != null)
			inputPanel.setVisible(false);
	}

	public String[] getChildRetVals() {
		return childRetVals;
	}

	public abstract void childResponse(tableNames iD2, String[] childRetVals);

	protected abstract void sync();

	public JTable getTable() {
		return table;
	}

	protected void initializeToolbar(FormController controller) {
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
		model = new DataBaseTableModel(tableName);
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
		for (IInput c : inputsArray) {
			c.setUserEditable(b);
		}
	}
	

	public StatusBar getStatusBar() {
		return statusBar;
	}
	
	public void setZoomButtons(Boolean b){
		
	}
	
	public void disableFields(){
		setZoomButtons(false);
		for (Component c : inputsArray){
			if (c instanceof JTextField)
				((JTextField) c).setEditable(false);
			if (c instanceof JComboBox)
				c.setEnabled(false);
			if (c instanceof DatePickerComponent)
				((DatePickerComponent) c).setEditable(false);
		}
	}
	
	public tableNames getTableName() {
		return tableName;
	}
	
	public boolean areRequiredFieldsEntered() {
		if(requiredFields == null)
			return true;
		for (int field : requiredFields) {
			JTextField tf = (JTextField)inputsArray[field];
			if(tf.getText().equals(""))
				return false;
		}
		return true;
	}
	
	public void zoomNotify(tableNames parentDialogName) {
		
	}

}
