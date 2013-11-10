package gui.dialogs;

import gui.ComboBoxInput;
import gui.ComboListitem;
import gui.DateInput;
import gui.DatePickerComponent;
import gui.DocumentLimit;
import gui.DocumentNumericLimited;
import gui.Input;
import gui.TextInput;
import gui.ZoomInput;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controllers.FormController;

import actions.ActionCancelAction;
import actions.ActionCommit;

import net.miginfocom.swing.MigLayout;

import model.DataBaseTableModel.tableNames;

public class PopisniDokumentForma extends DatabaseForma {

	private TextInput tfBrojPopisa;
	private ZoomInput zGodina;
	private ZoomInput zPib;
	private ZoomInput zSifraObjekta;
	private DateInput dDatumPopisa;
	private ComboBoxInput cbStatus;

	public PopisniDokumentForma(FormController fc) {
		super(fc, tableNames.POPISNI_DOKUMENT, 750, 450, false);
	}
	
	@Override
	protected void initializeInputFields(FormController controller) {
		tfBrojPopisa = new TextInput(4, "Broj popisa", new DocumentNumericLimited(4));
		zGodina = new ZoomInput(this, tableNames.POSLOVNA_GODINA, "Poslovna godina", 5, 5);
		zPib = new ZoomInput(this, tableNames.PREDUZECE, "Pib", 14, 14);
		zSifraObjekta = new ZoomInput(this, tableNames.POSLOVNI_OBJEKAT, "Poslovni objekat", 14, 14);
		dDatumPopisa = new DateInput("Datum popisa");
		
		List<ComboListitem> items = new ArrayList<>();
		items.add(new ComboListitem("Faza formiranja", "f"));
		items.add(new ComboListitem("Proknjizen", "p"));
		items.add(new ComboListitem("Storniran", "s"));
		cbStatus = new ComboBoxInput(items, "Stanje dokumenta");
	}

	@Override
	public void populateInputsAndRequiredArray() {
		inputsArray = new Input[5];
		inputsArray[0] = tfBrojPopisa;
		inputsArray[1] = zGodina;
		inputsArray[2] = zPib;
		inputsArray[3] = zSifraObjekta;
		inputsArray[4] = dDatumPopisa;
		
		requiredFields = new int[5];
		requiredFields[0] = 0;
		requiredFields[1] = 1;
		requiredFields[2] = 2;
		requiredFields[3] = 3;
		requiredFields[4] = 4;
	}

	@Override
	public void childResponse(tableNames iD2, String[] childRetVals) {
		// TODO Auto-generated method stub
		switch (iD2) {
		case POSLOVNA_GODINA:
			zGodina.setText(childRetVals[0]);
			zPib.setText(childRetVals[1]);
			break;
		case POSLOVNI_OBJEKAT:
			zSifraObjekta.setText(childRetVals[0]);
			zPib.setText(childRetVals[2]);
			break;
		case PREDUZECE:
			zPib.setText(childRetVals[0]);
			break;
		}

	}

	@Override
	public void populateStatusBasedComponents() {

	}

	@Override
	public void initializePrimaryKeysNumbers() {
		// TODO Auto-generated method stub
		primaryKeysColumnNumber = new int[4];
		primaryKeysColumnNumber[0] = 0;
		primaryKeysColumnNumber[1] = 1;
		primaryKeysColumnNumber[2] = 2;
		primaryKeysColumnNumber[3] = 3;

	}
	
}
