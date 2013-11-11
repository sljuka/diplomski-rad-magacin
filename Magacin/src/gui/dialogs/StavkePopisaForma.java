package gui.dialogs;

import gui.Input;
import gui.NumericTextInput;
import gui.ZoomInput;
import model.DataBaseTableModel.tableNames;
import controllers.FormController;

public class StavkePopisaForma extends DatabaseForma {

	private ZoomInput zGodina;
	private ZoomInput zSifraObjekta;
	private ZoomInput zBrojPopisa;
	private ZoomInput zPib;
	private ZoomInput zSifraArtikla;
	private NumericTextInput txtKolicinaPoPopisu;
	private NumericTextInput txtCenaPoPopisu;
	private NumericTextInput txtVrednostPoPopisu;
	private NumericTextInput txtKolicinaUKartici;
	private NumericTextInput txtCenaUKartici;
	private NumericTextInput txtVrednostUKartici;

	public StavkePopisaForma(FormController fc) {
		super(fc, tableNames.STAVKE_POPISA, 1100, 650, true);
	}
	
	@Override
	protected void initializeInputFields(FormController controller) {
		zGodina = new ZoomInput(this, tableNames.POSLOVNA_GODINA, "Poslovna godina", 5, 5);
		zSifraObjekta = new ZoomInput(this, tableNames.POSLOVNI_OBJEKAT, "Poslovni objekat", 14, 30);
		zBrojPopisa = new ZoomInput(this, tableNames.POPISNI_DOKUMENT, "Popisni dokument", 9, 20);
		zPib = new ZoomInput(this, tableNames.PREDUZECE, "Preduzece", 14, 30);
		zSifraArtikla = new ZoomInput(this, tableNames.POSLOVNI_OBJEKAT, "Magacin", 14, 30);

		txtKolicinaPoPopisu = new NumericTextInput("Kolicina po popisu", 14);
		txtCenaPoPopisu = new NumericTextInput("Cena po popisu", 14);
		txtVrednostPoPopisu = new NumericTextInput("Vrednost po popisu", 14);
		txtKolicinaUKartici = new NumericTextInput("Kolicina u kartici", 14);
		txtCenaUKartici = new NumericTextInput("Cena u kartici", 14);
		txtVrednostUKartici = new NumericTextInput("Vrednost u kartici", 14);
	}

	@Override
	public void populateInputsAndRequiredArray() {
		// TODO Auto-generated method stub
		inputsArray = new Input[] {
			zGodina,
			zSifraObjekta,
			zBrojPopisa,
			zPib,
			zSifraArtikla,
			txtKolicinaPoPopisu,
			txtCenaPoPopisu,
			txtVrednostPoPopisu,
			txtKolicinaUKartici,
			txtCenaUKartici,
			txtVrednostUKartici
		};
		
		requiredFields = new int[11];
		for(int i = 0; i<inputsArray.length; i++)	//every field required
			requiredFields[i] = i;
	}

	@Override
	public void populatePrimaryInputsArray() {
		// TODO Auto-generated method stub
		primaryKeysColumnNumber = new int[5];
		primaryKeysColumnNumber[0] = 0;
		primaryKeysColumnNumber[1] = 1;
		primaryKeysColumnNumber[2] = 2;
		primaryKeysColumnNumber[3] = 3;
		primaryKeysColumnNumber[4] = 4;
	}

	@Override
	public void childResponse(tableNames iD2, String[] childRetVals) {
		// TODO Auto-generated method stub
		switch (iD2) {
		case POPISNI_DOKUMENT:
			zGodina.setText(childRetVals[2]);
			zBrojPopisa.setText(childRetVals[3]);
			zPib.setText(childRetVals[0]);
			zSifraObjekta.setText(childRetVals[1]);
			break;
		case ARTIKAL:
			zSifraArtikla.setText(childRetVals[1]);
			break;

		}

	}
	
	@Override
	public void setFieldsEditable(boolean b) {
		// TODO Auto-generated method stub
		super.setFieldsEditable(b);
		zPib.setUserEditable(false);
	}
}
