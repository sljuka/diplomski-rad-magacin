package gui.forms;

import gui.DocumentLimit;
import gui.Input;
import gui.TextInput;
import model.DataBaseTableModel.tableNames;
import controllers.FormController;

public class TipObjektaForma extends DatabaseForma {

	private TextInput tfsifraTipa;
	private TextInput tfnazivTipa;

	public TipObjektaForma(FormController fc) {
		// TODO Auto-generated constructor stub
		super(fc, tableNames.TIP_OBJEKTA, 700, 400, false);
	}
	
	@Override
	protected void sync() {
		// TODO Auto-generated method stub
		super.sync();
		childRetVals[0] = tfsifraTipa.getText();
	}
	
	@Override
	protected void initializeInputFields(FormController controller) {
		inputsArray = new Input[] {
			tfsifraTipa = new TextInput(14, "Sifra tipa", new DocumentLimit(14)),
			tfnazivTipa = new TextInput(14, "Naziv tipa", null)
		};
	}


	@Override
	public void populateInputsAndRequiredArray() {
		requiredFields = new int[2];
		for(int i = 0; i<inputsArray.length; i++)	//every field required
			requiredFields[i] = i;
	}


	@Override
	public void populatePrimaryInputsArray() {
		primaryKeysColumnNumber = new int[1];
		primaryKeysColumnNumber[0] = 0;
	}

	@Override
	public void childResponse(tableNames iD2, String[] childRetVals) {
		// TODO Auto-generated method stub
		// NO CHILDREN, SMRC
	}

}
