package gui.forms;
import gui.DocumentLimit;
import gui.IInput;
import gui.Input;
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
	
	private Input tfSifraDrzave;
	private Input tfNazivDrzave;
	
	public DrzaveForma(FormController controller) {
		// TODO Auto-generated constructor stub
		super(controller, tableNames.DRZAVA, 500, 300, false);
	}
	
	@Override
	protected void initializeInputFields(FormController controller) {
		
		inputsArray = new Input[] {
			tfSifraDrzave = new TextInput(3, "Sifra drzave", new DocumentLimit(3)),
			tfNazivDrzave = new TextInput(25, "Naziv drzave", null)
		};
			
	}

	@Override
	public void populateInputsAndRequiredArray() {

		requiredFields = new int[2];
		requiredFields[0] = 0;
		requiredFields[1] = 1;
	
	}
	
	@Override
	protected void sync() {
		// TODO Auto-generated method stub
		super.sync();
		childRetVals[0] = tfSifraDrzave.getText();
	}

	@Override
	public void childResponse(tableNames iD2, String[] childRetVals) {
		// TODO Auto-generated method stub
		// NO CHILDREN
	}

	@Override
	public void populatePrimaryInputsArray() {
		// TODO Auto-generated method stub
		primaryKeysColumnNumber = new int[1];
		primaryKeysColumnNumber[0] = 0;
	}

}
