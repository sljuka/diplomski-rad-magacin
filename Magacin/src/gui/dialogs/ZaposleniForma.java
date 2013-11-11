package gui.dialogs;

import gui.CheckBoxInput;
import gui.DocumentLimit;
import gui.Input;
import gui.TextInput;
import gui.ZoomInput;
import model.DataBaseTableModel.tableNames;
import controllers.AuthentificationController;
import controllers.FormController;

public class ZaposleniForma extends DatabaseForma {

	private TextInput tfImeRadnika;
	private TextInput tfPrezimeRadnika;
	private ZoomInput zPib;
	private TextInput tfSifraRadnika;
	private TextInput tfPassword;
	private CheckBoxInput cbIsAdmin;
	
	public ZaposleniForma(FormController fc) {
		// TODO Auto-generated constructor stub
		super(fc, tableNames.ZAPOSLENI, 800, 500, false);
	}
	
	@Override
	protected void sync() {
		// TODO Auto-generated method stub
		super.sync();
		childRetVals[0] = zPib.getText();
		childRetVals[1] = tfSifraRadnika.getText();
	}
	
	@Override
	protected void initializeInputFields(FormController controller) {
		inputsArray = new Input[] {
				zPib = new ZoomInput(this, tableNames.PREDUZECE, "Pib", 14, 30),
				tfSifraRadnika = new TextInput(5, "Sifra radnika", new DocumentLimit(5)),
				tfPrezimeRadnika = new TextInput(30, "Prezime radnika", null),
				tfImeRadnika = new TextInput(30, "Ime radnika", null),
				tfPassword = new TextInput(30, "Password", new DocumentLimit(30)),
				cbIsAdmin = new CheckBoxInput("Is admin?")
		};
	}

	@Override
	public void populateInputsAndRequiredArray() {
		requiredFields = new int[5];
		for(int i = 0; i<5; i++)	//every field required
			requiredFields[i] = i;
	}

	@Override
	public void populatePrimaryInputsArray() {
		primaryKeysColumnNumber = new int[2];
		primaryKeysColumnNumber[0] = 0;
		primaryKeysColumnNumber[1] = 1;
	}

	@Override
	public void childResponse(tableNames iD2, String[] childRetVals) {
		// TODO Auto-generated method stub
		if (iD2 == tableNames.PREDUZECE) {
			zPib.setText(childRetVals[2]);
		}
	}

	@Override
	public void setFieldsEditable(boolean b) {
		// TODO Auto-generated method stub
		super.setFieldsEditable(b);
		zPib.setUserEditable(false);
		if(!AuthentificationController.getAuthenticationInstance().isAdmin())
			cbIsAdmin.setUserEditable(false);
	}

	@Override
	public void beforeAdd() {
		zPib.setText(AuthentificationController.getAuthenticationInstance().getPibPreduzecaUlogovanogKorisnika());
	}
	
}
