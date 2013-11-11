package gui.dialogs;

import gui.Input;
import gui.TextInput;
import gui.ZoomInput;
import model.DataBaseTableModel.tableNames;
import controllers.FormController;

public class PoslovniObjektiForma extends DatabaseForma {

	private ZoomInput zPib;
	private TextInput tfSifraObjekta;
	private ZoomInput zSifraDrzave;
	private ZoomInput zfSifraMesta;
	private ZoomInput zSifraTipa;
	private TextInput tfNazivObjekta;

	public PoslovniObjektiForma(FormController fc) {
		super(fc, tableNames.POSLOVNI_OBJEKAT, 750, 450, false);
	}
	
	@Override
	protected void sync() {
		super.sync();
		childRetVals[0] = zSifraDrzave.getText();
		childRetVals[1] = zfSifraMesta.getText();
		childRetVals[2] = zPib.getText();
		childRetVals[3] = tfSifraObjekta.getText();
	}

	@Override
	protected void initializeInputFields(FormController controller) {
		// TODO Auto-generated method stub
		zPib = new ZoomInput(this, tableNames.PREDUZECE, "Pib", 14, 30);
		tfSifraObjekta = new TextInput(14, "Sifra objekta", null);
		zSifraDrzave = new ZoomInput(this, tableNames.DRZAVA, "Drzava", 5, 30);
		zfSifraMesta = new ZoomInput(this, tableNames.NASELJENO_MESTO, "Naseljeno mesto", 4, 14);
		zSifraTipa = new ZoomInput(this, tableNames.TIP_OBJEKTA, "Tip objekta", 4, 30);
		tfNazivObjekta = new TextInput(30, "Naziv objekta", null);
	}

	@Override
	public void populateInputsAndRequiredArray() {
		// TODO Auto-generated method stub
		inputsArray = new Input[6];
		inputsArray[0] = zPib;
		inputsArray[1] = tfSifraObjekta;
		inputsArray[2] = zSifraDrzave;
		inputsArray[3] = zfSifraMesta;
		inputsArray[4] = zSifraTipa;
		inputsArray[5] = tfNazivObjekta;
		
		requiredFields = new int[6];
		requiredFields[0] = 0;
		requiredFields[1] = 1;
		requiredFields[2] = 2;
		requiredFields[3] = 3;
		requiredFields[4] = 4;
		requiredFields[5] = 5;
	}

	@Override
	public void childResponse(tableNames iD2, String[] childRetVals) {
		// TODO Auto-generated method stub
		switch (iD2) {
		case TIP_OBJEKTA:
			zSifraTipa.setText(childRetVals[0]);
			//tfNazivTipa.setText(childRetVals[1]);
			break;
		case NASELJENO_MESTO:
			zSifraDrzave.setText(childRetVals[0]);
			//tfSifraMesta.setText(childRetVals[1]);
			//tfNazivMesta.setText(childRetVals[2]);
			break;
		case PREDUZECE:
			zPib.setText(childRetVals[0]);
			//tfNazivPreduzeca.setText(childRetVals[1]);
			//break;
		default:
			break;
		}
	}

	@Override
	public void populatePrimaryInputsArray() {
		primaryKeysColumnNumber = new int[2];
		primaryKeysColumnNumber[0] = 0;
		primaryKeysColumnNumber[1] = 1;
	}

}
