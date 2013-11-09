package gui.dialogs;
import gui.DocumentLimit;
import gui.IInput;
import gui.TextInput;

import java.awt.Component;
import java.awt.TextField;
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
	
	private IInput tfSifraDrzave;
	private IInput tfNazivDrzave;
	
	//private JTextField tfSifraDrzave;
	//private JTextField tfNazivDrzave;
	
	public DrzaveForma(FormController controller) {
		// TODO Auto-generated constructor stub
		super(controller, tableNames.DRZAVA, 500, 300);
	}
	
	@Override
	protected void initializeInputFields(FormController controller) {
		// TODO Auto-generated method stub
		setLayout(new MigLayout("", "[align r][align l, grow, fill]", ""));

		inputPanel = new JPanel();
		inputPanel.setLayout(new MigLayout("center"));	
		
		tfSifraDrzave = new TextInput(3, "Sifra drzave", new DocumentLimit(3));
		tfNazivDrzave = new TextInput(25, "Naziv drzave", null);
			
		inputPanel.add(tfSifraDrzave.getComponent(), "wrap");
		inputPanel.add(tfNazivDrzave.getComponent(), "wrap");
	}
	
	public void sync() {
		int index = table.getSelectedRow();
		if (index < 0) {
			for (IInput c : inputsArray) {
				c.setText("");
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
	public void populateInputsAndRequiredArray() {
		// TODO Auto-generated method stub
		inputsArray = new IInput[2];
		inputsArray[0] = tfSifraDrzave;
		inputsArray[1] = tfNazivDrzave;
		
		requiredFields = new int[2];
		requiredFields[0] = 0;
		requiredFields[1] = 1;
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

}
