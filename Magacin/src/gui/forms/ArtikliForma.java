package gui.forms;

import gui.DocumentDecimal;

import gui.DocumentLimit;
import gui.DocumentNumericLimited;
import gui.Input;
import gui.TextInput;
import gui.ZoomInput;


import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controllers.AuthentificationController;
import controllers.FormController;

import model.DataBaseTableModel.tableNames;
import net.miginfocom.swing.MigLayout;

import actions.ActionCancelAction;
import actions.ActionCommit;

public class ArtikliForma extends DatabaseForma {

	private ZoomInput zPib;
	private TextInput tfSifraArtikla;
	private ZoomInput zSifraMerneJedinice;
	private TextInput tfNazivArtikla;
	private TextInput tfPakovanje;
	
	public ArtikliForma(FormController fc) {
		// TODO Auto-generated constructor stub
		super(fc, tableNames.ARTIKAL, 1000, 600, false);
	}
	
	@Override
	protected void sync() {
		// TODO Auto-generated method stub
		super.sync();
		childRetVals[0] = zPib.getText();
		childRetVals[1] = tfSifraArtikla.getText();
	}

	@Override
	protected void initializeInputFields(FormController controller) {
		// TODO Auto-generated method stub
		zPib = new ZoomInput(this, tableNames.PREDUZECE, "Pib", 15, 30);
		tfSifraArtikla = new TextInput(5, "Sifra artikla", null);
		zSifraMerneJedinice = new ZoomInput(this, tableNames.MERNA_JEDINICA, "Merna jedinica", 5, 30);
		tfNazivArtikla = new TextInput(30, "Naziv artikla", null);
		tfPakovanje = new TextInput(20, "Pakovanje", new DocumentNumericLimited(20));
	
		inputPanel = new JPanel();
		inputPanel.setLayout(new MigLayout("center"));

		inputPanel.add(zPib.getComponent(), "wrap");
		inputPanel.add(tfSifraArtikla.getComponent(), "wrap");
		inputPanel.add(zSifraMerneJedinice.getComponent(), "wrap");
		inputPanel.add(tfNazivArtikla.getComponent(), "wrap");
		inputPanel.add(tfPakovanje.getComponent(), "wrap");
	}

	@Override
	public void populateInputsAndRequiredArray() {
		inputsArray = new Input[5];
		inputsArray[0] = zPib;
		inputsArray[1] = tfSifraArtikla;
		inputsArray[2] = zSifraMerneJedinice;
		inputsArray[3] = tfNazivArtikla;
		inputsArray[4] = tfPakovanje;
		
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
		if (iD2 == tableNames.MERNA_JEDINICA) {
			zSifraMerneJedinice.setText(childRetVals[0]);
		}
		if (iD2 == tableNames.PREDUZECE) {
			zPib.setText(childRetVals[0]);
		}
	}

	@Override
	public void populatePrimaryInputsArray() {
		// TODO Auto-generated method stub
		primaryKeysColumnNumber = new int[1];
		primaryKeysColumnNumber[0] = 1;
	}
	
	@Override
	public void setFieldsEditable(boolean b) {
		// TODO Auto-generated method stub
		super.setFieldsEditable(b);
		zPib.setUserEditable(false);
	}
	
	@Override
	public void beforeAdd() {
		zPib.setText(AuthentificationController.getAuthenticationInstance().getPibPreduzecaUlogovanogKorisnika());
	}

}
