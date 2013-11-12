package gui.forms;

import java.awt.Component;

import gui.DocumentLimit;
import gui.Input;
import gui.TextInput;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controllers.FormController;

import actions.ActionCancelAction;
import actions.ActionCommit;

import net.miginfocom.swing.MigLayout;

import model.DataBaseTableModel.tableNames;

public class MernaJedinicaForma extends DatabaseForma {
	
	private TextInput tfSifraJedinice;
	private TextInput tfNazivJedinice;
	private TextInput tfOznakaJedinice;
	
	public MernaJedinicaForma(FormController fc) {
		super(fc, tableNames.MERNA_JEDINICA, 700, 400, false);
	}
	
	@Override
	protected void sync() {
		// TODO Auto-generated method stub
		super.sync();
		childRetVals[0] = tfSifraJedinice.getText();
	}
	
	@Override
	protected void initializeInputFields(FormController controller) {
		tfSifraJedinice = new TextInput(30, "Sifra jedinice", null);
		tfNazivJedinice = new TextInput(30, "Naziv jedinice", null);
		tfOznakaJedinice = new TextInput(10, "Oznaka merne jedinice", null);
	}

	@Override
	public void populateInputsAndRequiredArray() {
		inputsArray = new Input[3];
		inputsArray[0] = tfSifraJedinice;
		inputsArray[1] = tfNazivJedinice;
		inputsArray[2] = tfOznakaJedinice;
		
		requiredFields = new int[3];
		requiredFields[0] = 0;
		requiredFields[1] = 1;
		requiredFields[2] = 2;
	}
	
	@Override
	public void childResponse(tableNames iD2, String[] childRetVals) {
		// TODO Auto-generated method stub
		//no children
	}

	@Override
	public void populatePrimaryInputsArray() {
		// TODO Auto-generated method stub
		primaryKeysColumnNumber = new int[1];
		primaryKeysColumnNumber[0] = 0;
	}

}
