package gui.dialogs;

import gui.ComboBoxInput;
import gui.ComboListitem;
import gui.DateInput;
import gui.DocumentNumericLimited;
import gui.Input;
import gui.NumericTextInput;
import gui.TextInput;
import gui.ZoomInput;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import model.DataBaseTableModel.tableNames;
import net.miginfocom.swing.MigLayout;
import controllers.FormController;

public class AnalitikaMagacinskeKarticeForma extends DatabaseForma {

	private TextInput tfRbrAnalitike;
	private ZoomInput zSifraObjekta;
	private ZoomInput zPib;
	private ZoomInput zSifraArtikla;
	private ZoomInput zGodina;
	private ZoomInput zBrojPrometnogDokumenta;
	private ZoomInput zRedniBrojStavkePrometnogDokumenta;
	private DateInput diDatumPromijene;
	private ComboBoxInput cbVrstaDokumenta;
	private TextInput tfSifraDokumenta;
	private NumericTextInput tfKolicina;
	private NumericTextInput tfCena;
	private NumericTextInput tfVrednost;
	private ComboBoxInput cbSmer;

	public AnalitikaMagacinskeKarticeForma(FormController controller) {
		super(controller, tableNames.ANALITIKA_MAGACINSKE_KARTICE, 1200, 700, true);
	}
	
	@Override
	public void setFieldsEditable(boolean b) {
		super.setFieldsEditable(false);
	}

	@Override
	protected void initializeInputFields(FormController controller) {

		tfRbrAnalitike = new TextInput(5, "Redni broj analitike", new DocumentNumericLimited(5));
		zSifraObjekta = new ZoomInput(this, tableNames.POSLOVNI_OBJEKAT, "Sifra objekta", 12, 30);
		zPib = new ZoomInput(this, tableNames.PREDUZECE, "Preduzece", 12, 30);
		zSifraArtikla = new ZoomInput(this, tableNames.ARTIKAL, "Sifra artikla", 5, 30);
		zGodina = new ZoomInput(this, tableNames.POSLOVNA_GODINA, "Poslovna godina", 5, 5);
		zBrojPrometnogDokumenta = new ZoomInput(this, tableNames.PROMETNI_DOKUMENT, "Broj prometnog dokumenta", 12, 30);
		zRedniBrojStavkePrometnogDokumenta = new ZoomInput(this, tableNames.STAVKA_PROMETNOG_DOKUMENTA, "Broj stavke prometnog dokumenta", 5, 5);
		diDatumPromijene = new DateInput("Datum promene");
		
		List<ComboListitem> vrsteDokumenta = new ArrayList<ComboListitem>();
		vrsteDokumenta.add(new ComboListitem("primka", "pr"));
		vrsteDokumenta.add(new ComboListitem("otpremnica", "ot"));
		vrsteDokumenta.add(new ComboListitem("medjumagacinsko", "mm"));
		vrsteDokumenta.add(new ComboListitem("nivelacija", "ni"));
		vrsteDokumenta.add(new ComboListitem("korekcija", "kp"));
		vrsteDokumenta.add(new ComboListitem("pocetno", "ps"));
		cbVrstaDokumenta = new ComboBoxInput(vrsteDokumenta, "Vrste dokumenta");
		
		tfSifraDokumenta = new TextInput(20, "Sifra dokumenta", null);
		tfKolicina = new NumericTextInput("Kolicina", 20);
		tfCena = new NumericTextInput("Cena", 20);
		tfVrednost = new NumericTextInput("Vrednost", 20);
		
		List<ComboListitem> smerovi = new ArrayList<ComboListitem>();
		smerovi.add(new ComboListitem("Ulaz", "U"));
		smerovi.add(new ComboListitem("Izlaz", "I"));
		cbSmer = new ComboBoxInput(smerovi, "Smer");
		
	}

	@Override
	public void populateInputsAndRequiredArray() {
		
		inputsArray = new Input[14];
		inputsArray[0] = tfRbrAnalitike;
		inputsArray[1] = zSifraObjekta;
		inputsArray[2] = zPib;
		inputsArray[3] = zSifraArtikla;
		inputsArray[4] = zGodina;
		inputsArray[5] = zBrojPrometnogDokumenta;
		inputsArray[6] = zRedniBrojStavkePrometnogDokumenta;
		inputsArray[7] = diDatumPromijene;
		inputsArray[8] = cbVrstaDokumenta;
		inputsArray[9] = tfSifraDokumenta;
		inputsArray[10] = tfKolicina;
		inputsArray[11] = tfCena;
		inputsArray[12] = tfVrednost;
		inputsArray[13] = cbSmer;
		
	}

	@Override
	public void childResponse(tableNames iD2, String[] childRetVals) {
		// TODO Auto-generated method stub
		switch (iD2) {
		case MAGACINSAK_KARTICA:
			zSifraObjekta.setText(childRetVals[0]);
			zPib.setText(childRetVals[1]);
			zSifraArtikla.setText(childRetVals[2]);
			zGodina.setText(childRetVals[3]);
			break;
		case STAVKA_PROMETNOG_DOKUMENTA:
			zBrojPrometnogDokumenta.setText(childRetVals[3]);
			zPib.setText(childRetVals[0]);
			zGodina.setText(childRetVals[2]);
			break;
		}
	}

	@Override
	public void populateStatusBasedComponents() {
		// TODO Auto-generated method stub
		statusBasedButtons = new Component[0];
	}

	@Override
	public void initializePrimaryKeysNumbers() {
		// TODO Auto-generated method stub
		primaryKeysColumnNumber = new int[1];
		primaryKeysColumnNumber[0] = 0;
	}

	

}
