package gui.dialogs;

import gui.DocumentNumericLimited;
import gui.Input;
import gui.NumericTextInput;
import gui.TextInput;
import gui.ZoomInput;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controllers.FormController;

import net.miginfocom.swing.MigLayout;
import numeric.textField.NumericTextField;
import actions.ActionCancelAction;
import actions.ActionCommit;

import model.DataBaseTableModel.tableNames;

public class StavkePrometnogDokumentaForma extends DatabaseForma {

	private ZoomInput zPib;
	private ZoomInput zGodina;
	private ZoomInput zBrojPrometnogDokumenta;
	private ZoomInput zSifraArtikla;
	private TextInput tfRbr;
	private NumericTextInput tfKolicina;
	private NumericTextInput tfCena;
	private NumericTextInput tfVrednost;
	
	public StavkePrometnogDokumentaForma(FormController fc) {
		// TODO Auto-generated constructor stub
		super(fc, tableNames.STAVKA_PROMETNOG_DOKUMENTA, 1000, 600, true);
	}
	
	@Override
	protected void initializeInputFields(FormController controller) {
		// TODO Auto-generated method stub
		inputsArray = new Input[] {
			zPib = new ZoomInput(this, tableNames.PREDUZECE, "Preduzece", 14, 30),
			zGodina = new ZoomInput(this, tableNames.POSLOVNA_GODINA, "Poslovna godina", 5, 5),
			zBrojPrometnogDokumenta = new ZoomInput(this, tableNames.PROMETNI_DOKUMENT, "Broj prometnog dokumenta", 4, 5),
			tfRbr = new TextInput(3, "Redni broj stavke", new DocumentNumericLimited(3)),
			zSifraArtikla = new ZoomInput(this, tableNames.ARTIKAL, "Artikl", 14, 30),
			tfKolicina = new NumericTextInput("Kolicina", 14),
			tfCena = new NumericTextInput("Cena", 14),
			tfVrednost = new NumericTextInput("Vrednost", 14)
		};
	}

	@Override
	public void populateInputsAndRequiredArray() {
		requiredFields = new int[8];
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
	}

	@Override
	public void childResponse(tableNames iD2, String[] childRetVals) {
		// TODO Auto-generated method stub
		if (iD2 == tableNames.ARTIKAL) {
			zSifraArtikla.setText(childRetVals[1]);
			zPib.setText(childRetVals[0]);
		}
		if (iD2 == tableNames.PROMETNI_DOKUMENT) {
			zBrojPrometnogDokumenta.setText(childRetVals[2]);
			zPib.setText(childRetVals[0]);
			zGodina.setText(childRetVals[1]);
		}
	}
}
