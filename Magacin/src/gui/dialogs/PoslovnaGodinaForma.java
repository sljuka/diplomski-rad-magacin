package gui.dialogs;

import gui.CheckBoxInput;
import gui.DocumentNumericLimited;
import gui.Input;
import gui.TextInput;
import gui.ZoomInput;
import model.DataBaseTableModel.tableNames;
import controllers.FormController;

public class PoslovnaGodinaForma extends DatabaseForma {

	private ZoomInput zPib;
	private TextInput tfgodina;
	private CheckBoxInput cZakljucena;

	public PoslovnaGodinaForma(FormController fc) {
		super(fc, tableNames.POSLOVNA_GODINA, 600, 300, false);
	}

	@Override
	protected void initializeInputFields(FormController controller) {
		zPib = new ZoomInput(this, tableNames.PREDUZECE, "Pib", 14, 14);
		tfgodina = new TextInput(5, "Godina", new DocumentNumericLimited(4));
		cZakljucena = new CheckBoxInput("Zakljucena");
	}

	@Override
	public void populateInputsAndRequiredArray() {
		inputsArray = new Input[3];
		inputsArray[0] = zPib;
		inputsArray[1] = tfgodina;
		inputsArray[2] = cZakljucena;
		
		requiredFields = new int[3];
		requiredFields[0] = 0;
		requiredFields[1] = 1;
		requiredFields[2] = 2;
	}
	
	@Override
	public void childResponse(tableNames iD2, String[] childRetVals) {
		// TODO Auto-generated method stub
		if (iD2 == tableNames.PREDUZECE) {
			zPib.setText(childRetVals[0]);
			((ZoomInput)zPib).setNaziv(childRetVals[1]);
		}
	}

	@Override
	public void populateStatusBasedComponents() {

	}

	@Override
	public void initializePrimaryKeysNumbers() {
		// TODO Auto-generated method stub
		primaryKeysColumnNumber = new int[2];
		primaryKeysColumnNumber[0] = 0;
		primaryKeysColumnNumber[1] = 1;
	}

}
