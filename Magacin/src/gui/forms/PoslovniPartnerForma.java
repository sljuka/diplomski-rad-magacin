package gui.forms;

import gui.ComboBoxInput;
import gui.ComboListitem;
import gui.DocumentLimit;
import gui.Input;
import gui.TextInput;
import gui.ZoomInput;

import java.util.ArrayList;
import java.util.List;

import model.DataBaseTableModel.tableNames;
import controllers.AuthentificationController;
import controllers.FormController;

public class PoslovniPartnerForma extends DatabaseForma {

	private ZoomInput zPibPreduzeca;
	private TextInput tfPibPoslovnogPartnera;
	private ComboBoxInput cbVrsta;
	private TextInput tfNazivPartnera;

	public PoslovniPartnerForma(FormController fc) {
		// TODO Auto-generated constructor stub
		super(fc, tableNames.POSLOVNI_PARTNER, 800, 500, false);
	}
	
	@Override
	protected void sync() {
		// TODO Auto-generated method stub
		super.sync();
		childRetVals[0] = zPibPreduzeca.getText();
		childRetVals[1] = tfPibPoslovnogPartnera.getText();
	}

	@Override
	protected void initializeInputFields(FormController controller) {
		zPibPreduzeca = new ZoomInput(this, tableNames.PREDUZECE, "Pib preduzeca", 12, 30);
		tfPibPoslovnogPartnera = new TextInput(14, "Pib poslovnog partenra", new DocumentLimit(12));

		List<ComboListitem> items = new ArrayList<>();
		items.add(new ComboListitem("dobavlja", "d"));
		items.add(new ComboListitem("kupuje-dobavlja", "kd"));
		items.add(new ComboListitem("kupuje", "k"));
		cbVrsta = new ComboBoxInput(items, "Vrsta poslovnog partnera");
	
		tfNazivPartnera = new TextInput(30, "Naziv poslovnog partnera", null);
	}

	@Override
	public void populateInputsAndRequiredArray() {
		// TODO Auto-generated method stub
		inputsArray = new Input[4];
		inputsArray[0] = zPibPreduzeca;
		inputsArray[1] = tfPibPoslovnogPartnera;
		inputsArray[2] = cbVrsta;
		inputsArray[3] = tfNazivPartnera;
		
		requiredFields = new int[4];
		requiredFields[0] = 0;
		requiredFields[1] = 1;
		requiredFields[2] = 2;
		requiredFields[3] = 3;
	}

	@Override
	public void childResponse(tableNames iD2, String[] childRetVals) {
		// TODO Auto-generated method stub
		if (iD2==tableNames.PREDUZECE) {
			zPibPreduzeca.setText(childRetVals[2]);
		}
	}

	@Override
	public void populatePrimaryInputsArray() {
		primaryKeysColumnNumber = new int[2];
		primaryKeysColumnNumber[0] = 0;
		primaryKeysColumnNumber[1] = 1;
	}

	@Override
	public void setFieldsEditable(boolean b) {
		// TODO Auto-generated method stub
		super.setFieldsEditable(b);
		zPibPreduzeca.setUserEditable(false);
	}
	
	@Override
	public void beforeAdd() {
		zPibPreduzeca.setText(AuthentificationController.getAuthenticationInstance().getPibPreduzecaUlogovanogKorisnika());
	}
	
}
