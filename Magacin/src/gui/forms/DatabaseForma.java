package gui.forms;

import gui.DatePickerComponent;
import gui.IInput;
import gui.Input;
import gui.StatusBar;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.font.TextHitInfo;
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
import app.MainForm;

public abstract class DatabaseForma extends JDialog {
	
	/********************* ime tabele u bazi, koju forma predstavlja ****************/
	protected tableNames tableName;

	/********************* gui komponente u formi ****************/
	protected JToolBar toolbar;
	protected JTable table;
	protected JPanel inputPanel;
	protected JPanel executeCancelPanel;
	protected JPanel proceduresPanel;
	protected StatusBar statusBar;
	
	/********************* polja za unos (textfieldovi, combo, checkbox i zoom polja ***/
	protected Input[] inputsArray;
	
	/********************* procedure ***/
	protected JButton[] procedures;
	
	/********************* model za pristup bazi ujedno model gui tabele ***/
	protected DataBaseTableModel model;
		
	/********************* parent dialog prilikom aktiviranja zoom polja ***/
	protected DatabaseForma parentForm;

	/********************* povratne vrednosti koje vraca zoom forma parent formi ***/
	protected String[] childRetVals;
	
	/**
	*  	where filter koji se koristi prilikom prelaska u formu next akcijom
	*	koristi se u formatu [ polje, vrednost, polje, vrednost...]
	*/
	protected String[] keyFilter;

	/**
	 *	niz intova koji predstavljaju broj kolona koje ulaze u sastav primarnog kljuca
	 */
	protected int[] primaryKeysColumnNumber;

	/**
	 *	niz intova koji predstavljaju brojeve polja za unos koja su obavezna
	 *	prilikom dodavanja i editovanja
	 */
	protected int[] requiredFields;
	
	/**
	 * Ovaj flag oznacava nacin layouta input polja u formi
	 * ako je aktivan onda se inputi slazu u 2 kolone da zauzmu manje mesta,
	 * inace se slazu u jednom redu.
	 */
	private boolean hasTwoColsOfInputFields;
	
	public DatabaseForma(	FormController controller,
							tableNames tableName,
							int width,
							int height,
							boolean has2columnsOfInputs) {
		super();
		
		this.tableName = tableName;
		childRetVals = new String[10];
		
		setTitle(tableName.toString());
		
		setSizeAndMove(width, height);
		setModal(true);
		
		initializeComponents(controller);
		populateInputsAndRequiredArray();
		this.hasTwoColsOfInputFields = has2columnsOfInputs;
		layoutInputPan();
		layoutComponents();
		
		populatePrimaryInputsArray();
		model.setPrimaryKeysNumbers(primaryKeysColumnNumber);
		
		setFieldsEditable(false);
	}

	/** LAYOUTOVANJE INPUT POLJA */
	protected void layoutInputPan() {
		inputPanel = new JPanel();
		inputPanel.setLayout(new MigLayout("center"));

		if(!hasTwoColsOfInputFields) {
			for (IInput input : inputsArray) {
				inputPanel.add(input.getComponent(), "wrap");
			}
		}
		else {
			for(int i = 0; i<inputsArray.length; i++) {
				if(i % 2 == 0)
					inputPanel.add(inputsArray[i].getComponent());
				else
					inputPanel.add(inputsArray[i].getComponent(), "wrap");
			}
		}
	}
	
	/** PODESAVANJE VELICINE FORME I POMERANJE FORME NA SREDINU EKRANA */
	protected void setSizeAndMove(int width, int height) {
		setSize(width, height);
		setLocationRelativeTo(null);
	}
	
	/** POZIVA SE PRILIKOM PRELASKA U ADD STANJE
	 *  KORISTI SE PRILIKOM BLOKIRANJA ODREDJENIH INPUTA ZA UNOS
	 *  I PRESELEKTOVANJA COMBOBOXEVA PRE DODAVANJA
	 *  REDEFINISE SE PO POTREBI U KONKRETNIM FORMAMA
	 */
	public void beforeAdd() {
		
	}
	
	/**
	 * INICIJALIZACIJA GUI COMPONENTI, PROSLEDJUJE IM SE KONTROLER
	 * KOME DELEGIRAJU AKO TREBA NESTO DA SE MENJA U BAZI ILI AKO IMA VECIH
	 * PROMENA TOKOM IZVRSAVANJA AKCIJE
	 * @param controller
	 */
	protected void initializeComponents(FormController controller) {
		initializeStatusBar();
		initializeTable();
		initializeToolbar(controller);
		initializeInputFields(controller);
		initializeExecuteCancelPanel(controller);
		initializeProcedures(controller);
	}
	
	/**
	 * METODA ZA SLAGANJE GUI DELOVA U FORMU
	 * POZIVA SE POSLE INICIJALIZACIJE SVIH KOMPONENTI
	 */
	protected void layoutComponents() {
		setLayout(new MigLayout("", "[align r][align l, grow, fill]", ""));
		add(toolbar, "dock north");
		add(new JScrollPane(table), "dock north");
		add(inputPanel);
		add(executeCancelPanel, "cell 3 0");
		add(statusBar, "south, growx");
		add(proceduresPanel, "south, growx");
	}
	
	/**
	 * INICIJALIZACIJA PANELA NA KOJIMA SE NALAZI DUGME ZA IZVRSAVANJE I PONISTAVANJE
	 * @param controller
	 */
	protected void initializeExecuteCancelPanel(FormController controller) {
		executeCancelPanel = new JPanel();
		executeCancelPanel.setLayout(new MigLayout("align right"));
		executeCancelPanel.add(new JButton(new ActionCommit(controller)), "wrap");
		executeCancelPanel.add(new JButton(new ActionCancelAction(controller)));
	}
	
	/**
	 * INICIJALIZACIJA INPUT POLJA I
	 * INICIJALIZACIJA NIZA INPUT FIELDOVA
	 * POZIVA SE U KONKRETNIM FORMAMA
	 * @param controller
	 */
	protected abstract void initializeInputFields(FormController controller);
	
	/**
	 * INICIJALIZACIJA PROCEDURA
	 * @param controller
	 */
	protected void initializeProcedures(FormController controller) {
		if(procedures == null)
			procedures = new JButton[0];
		
		proceduresPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		for (JButton proc : procedures) {
			proceduresPanel.add(proc);
		}
	}
	
	/**
	 * INICIJALIZACIJA STATUS BARA
	 */
	protected void initializeStatusBar() {
		statusBar = new StatusBar();
	}
	
	/**
	 * INICIJALIZACIJA NIZA POLJA KOJI SU OBAVEZNI PRILIKOM DODAVANJA I MODIFIKACIJE
	 */
	public abstract void populateInputsAndRequiredArray();

	/**
	 * METODA SE POZIVA PRILIKOM VRACANJA IZ ZOOM POPUPA
	 * SLUZI DA NAMESTI VREDNOSTI IZ ZOOM POPUPA U PRETHODNI POPUP I DA ZATVORI ZOOM POPUP
	 * @param tableName
	 * @param childRetVals
	 */
	public abstract void childResponse(tableNames tableName, String[] childRetVals);

	/**
	 * POZIVA SE PRILIKOM SELEKTOVANJA VREDNOSTI TABELE
	 */
	protected void sync() {
		int index = table.getSelectedRow();
		if (index < 0) {
			for (Input i : inputsArray) {
				i.setText("");
			}
			return;
		}
		
		for (int i = 0; i < inputsArray.length; i++) {
			inputsArray[i].setText((String)model.getValueAt(index, i));
		}

		setFieldsEditable(true);
	}
	
	/**
	 * INICIJALIZACIJA TOOLBARA
	 * @param controller
	 */

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
	
	/**
	 * INICIJALIZACIJA TOOLBARA PRILIKOM ULASKA U ZOOM POPUP
	 * OVDE TOOLBAR NEMA OPCIJE ZA DODAVANJE ALI IMA OPCIJU ZA POVRATAK
	 * U PRETHODNI POPUP
	 * @param controller
	 */
	
	protected void initializeToolbarForChild(FormController controller) {
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

	/**
	 * INICIJALIZACIJA TABELE
	 */
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


	/**
	 * GETERI I SETERI
	 * @return
	 */
	
	public Input[] getInputs() {
		return inputsArray;
	}

	public DataBaseTableModel getModel() {
		return model;
	}

	public DatabaseForma getParentDialog() {
		return parentForm;
	}

	public void setParentDialog(DatabaseForma parentDialog, FormController fc) {
		this.parentForm = parentDialog;
		initializeToolbarForChild(fc);
		if(inputPanel != null)
			inputPanel.setVisible(false);
	}

	public String[] getChildRetVals() {
		return childRetVals;
	}

	public JTable getTable() {
		return table;
	}

	public String[] getKeyFilter() {
		return keyFilter;
	}

	public void setKeyFilter(String[] keyFilter) {
		this.keyFilter = keyFilter;
		model.setKeyFilter(keyFilter);
	}

	public abstract void populatePrimaryInputsArray();

	public int[] getPrimaryKeysColumnNumbers() {
		return primaryKeysColumnNumber;
	}

	public void setFieldsEditable(boolean b) {
		for (IInput c : inputsArray) {
			c.setUserEditable(b);
		}
	}

	public StatusBar getStatusBar() {
		return statusBar;
	}
	
	public void disableFields(){
		for (Input c : inputsArray){
			c.setUserEditable(false);
		}
	}
	
	public tableNames getTableName() {
		return tableName;
	}
	
	public boolean areRequiredFieldsEntered() {
		if(requiredFields == null)
			return true;
		for (int field : requiredFields) {
			if(inputsArray[field].isEmpty())
				return false;
		}
		return true;
	}
	
}
