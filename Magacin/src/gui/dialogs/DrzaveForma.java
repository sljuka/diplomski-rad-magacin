package gui.dialogs;
import gui.DocumentLimit;

import java.awt.Component;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controllers.FormController;

import model.DataBaseTableModel;
import model.DataBaseTableModel.tableNames;
import net.miginfocom.swing.MigLayout;
import actions.ActionAdd;
import actions.ActionCancelAction;
import actions.ActionCommit;
import actions.ActionHelp;
import actions.ActionJumoToPreviousForm;
import actions.ActionJumpToNextForm;
import actions.ActionRefresh;
import actions.ActionRemove;
import actions.ActionSearch;
import actions.ActionSelectFirst;
import actions.ActionSelectLast;
import actions.ActionSelectNext;
import actions.ActionSelectPrevious;


public class DrzaveForma extends DatabaseForma {
	
	private JTextField tfSifraDrzave;
	private JTextField tfNazivDrzave;
	
	public DrzaveForma() {
		// TODO Auto-generated constructor stub
		super();
		setupNextButton();
		ID = tableNames.DRZAVA;
		setTitle(ID.toString());
		setSizeAndMove(500, 300);
		initializeComponents();
		initializeStatusBar();
		setFieldsEditable(false);
		model.setPrimaryKeysNumbers(primaryKeysColumnNumber);
	}
	
	protected void setupNextButton() {
		
	}
	
	@Override
	protected void initializeComponents() {
		// TODO Auto-generated method stub
		setLayout(new MigLayout("", "[align r][align l, grow, fill]", ""));
		
		tfSifraDrzave = new JTextField(3);
		tfSifraDrzave.setDocument(new DocumentLimit(3));
		
		tfNazivDrzave = new JTextField(25);
		
		tfSifraDrzave.setEditable(false);
		tfNazivDrzave.setEditable(false);
		
		initializeTable();
		controller = new FormController(this);
		initializeToolbar();
		
		add(toolbar, "dock north");
		add(new JScrollPane(table), "dock north");
		
		JPanel tfPanel = new JPanel();
		tfPanel.setLayout(new MigLayout("center"));
		tfPanel.add(new JLabel("Sifra drzave"));
		tfPanel.add(tfSifraDrzave, "wrap");
		tfPanel.add(new JLabel("Naziv drzave"));
		tfPanel.add(tfNazivDrzave, "wrap");
		add(tfPanel);
		
		btnPanel = new JPanel();
		btnPanel.setLayout(new MigLayout("align right"));
		btnPanel.add(new JButton(new ActionCommit(controller)), "wrap");
		btnPanel.add(new JButton(new ActionCancelAction(controller)));
		add(btnPanel, "cell 3 0");
		
		populateFieldsArray();
		setFieldsEditable(false);
	}
	
	public void sync() {
		int index = table.getSelectedRow();
		if (index < 0) {
			for (Component c : editableFields) {
				if (c instanceof JTextField)
					((JTextField) c).setText("");
			}
			setFieldsEditable(false);
			return;
		}
		
		String sifra = (String)model.getValueAt(index, 0);
		String naziv = (String)model.getValueAt(index, 1);
		tfSifraDrzave.setText(sifra);
		tfNazivDrzave.setText(naziv);
		setFieldsEditable(true);
		childRetVals[0] = sifra;
		childRetVals[1] = naziv;
	}

	@Override
	public void populateFieldsArray() {
		// TODO Auto-generated method stub
		editableFields = new Component[2];
		editableFields[0] = tfSifraDrzave;
		editableFields[1] = tfNazivDrzave;
	}

	@Override
	public void childResponse(tableNames iD2, String[] childRetVals) {
		// TODO Auto-generated method stub
		// NO CHILDREN
	}

	@Override
	public void populateStatusBasedComponents() {
		// TODO Auto-generated method stub
		statusBasedButtons = new Component[0];
	}

	@Override
	public void initializePrimaryKeysNumbers() {
		// TODO Auto-generated method stub
		primaryKeysColumnNumber = new int[1];
		primaryKeysColumnNumber[0] = 0;
	}
	
	@Override
	public void setFieldsEditable(boolean b){
		tfNazivDrzave.setEditable(b);
		tfSifraDrzave.setEditable(b);
	}
}
