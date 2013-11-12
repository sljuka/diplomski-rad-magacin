package gui.forms;

import gui.DocumentLimit;
import gui.Input;
import gui.TextInput;
import gui.ZoomInput;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controllers.FormController;

import model.DataBaseTableModel.tableNames;
import net.miginfocom.swing.MigLayout;
import actions.ActionCancelAction;
import actions.ActionCommit;


public class PreduzecaForma extends DatabaseForma {

	private TextInput tfPib;
	private ZoomInput zSifraDrzave;
	private ZoomInput zSifraMesta;
	private TextInput tfNazivPreduzeca;
	
	public PreduzecaForma(FormController fc) {
		// TODO Auto-generated constructor stub
		super(fc, tableNames.PREDUZECE, 1000, 600,false);
	}
	
	@Override
	protected void initializeInputFields(FormController controller) {
		// TODO Auto-generated method stub
		tfPib = new TextInput(12, "Pib", new DocumentLimit(12));
		zSifraDrzave = new ZoomInput(this, tableNames.DRZAVA, "Drzava", 5, 30);
		zSifraMesta = new ZoomInput(this, tableNames.NASELJENO_MESTO, "Mesto", 5, 30);
		tfNazivPreduzeca = new TextInput(30, "Naziv preduzeca", null);
	}


	@Override
	public void populateInputsAndRequiredArray() {
		// TODO Auto-generated method stub
		inputsArray = new Input[4];
		inputsArray[0] = tfPib;
		inputsArray[1] = zSifraDrzave;
		inputsArray[2] = zSifraMesta;
		inputsArray[3] = tfNazivPreduzeca;
		
		requiredFields = new int[4];
		requiredFields[0] = 0;
		requiredFields[1] = 1;
		requiredFields[2] = 2;
		requiredFields[3] = 3;
	}

	@Override
	public void childResponse(tableNames iD2, String[] childRetVals) {
		// TODO Auto-generated method stub
		if (iD2 == tableNames.NASELJENO_MESTO) {
			zSifraDrzave.setText(childRetVals[0]);
			zSifraMesta.setText(childRetVals[1]);
		}
	}
	
	@Override
	protected void sync() {
		// TODO Auto-generated method stub
		super.sync();
		childRetVals[0] = zSifraDrzave.getText();
		childRetVals[1] = zSifraMesta.getText();
		childRetVals[2] = tfPib.getText();
	}

	@Override
	public void populatePrimaryInputsArray() {
		// TODO Auto-generated method stub
		primaryKeysColumnNumber = new int[3];
		primaryKeysColumnNumber[0] = 0;
		primaryKeysColumnNumber[1] = 1;
		primaryKeysColumnNumber[2] = 2;
	}
	
	@Override
	public void setFieldsEditable(boolean b) {
		// TODO Auto-generated method stub
		super.setFieldsEditable(b);
		zSifraDrzave.setUserEditable(false);
	}
	
}
