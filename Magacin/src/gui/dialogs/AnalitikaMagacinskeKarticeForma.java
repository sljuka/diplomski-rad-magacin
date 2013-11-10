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
	private DateInput diDatumPromijene;
	private ComboBoxInput cbVrstaDokumenta;
	private TextInput tfSifraDokumenta;
	private NumericTextInput tfKolicina;
	private NumericTextInput tfCena;
	private NumericTextInput tfVrednost;
	private ComboBoxInput cbSmer;

	public AnalitikaMagacinskeKarticeForma(FormController controller) {
		super(controller, tableNames.ANALITIKA_MAGACINSKE_KARTICE, 1200, 700);
	}
	
	@Override
	public void setFieldsEditable(boolean b) {
		super.setFieldsEditable(false);
	}

	@Override
	protected void initializeInputFields(FormController controller) {
		
		// INICIJALIZACIJA
		
		tfRbrAnalitike = new TextInput(5, "Redni broj analitike", new DocumentNumericLimited(5));
		zSifraObjekta = new ZoomInput(this, tableNames.POSLOVNI_OBJEKAT, "Sifra objekta", 12, 30);
		zPib = new ZoomInput(this, tableNames.PREDUZECE, "Preduzece", 12, 30);
		zSifraArtikla = new ZoomInput(this, tableNames.ARTIKAL, "Sifra artikla", 5, 30);
		zGodina = new ZoomInput(this, tableNames.POSLOVNA_GODINA, "Poslovna godina", 5, 5);
		zBrojPrometnogDokumenta = new ZoomInput(this, tableNames.PROMETNI_DOKUMENT, "Broj prometnog dokumenta", 12, 30);
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
		
		// LAYOUT
		
		inputPanel = new JPanel();
		inputPanel.setLayout(new MigLayout("center"));

		inputPanel.add(tfRbrAnalitike.getComponent());
		inputPanel.add(zSifraObjekta.getComponent(), "wrap");
		
		inputPanel.add(zPib.getComponent());
		inputPanel.add(zSifraArtikla.getComponent(), "wrap");
		
		inputPanel.add(zGodina.getComponent());
		inputPanel.add(zBrojPrometnogDokumenta.getComponent(), "wrap");
		
		inputPanel.add(diDatumPromijene.getComponent());
		inputPanel.add(cbVrstaDokumenta.getComponent(), "wrap");
		
		inputPanel.add(tfSifraDokumenta.getComponent());
		inputPanel.add(tfKolicina.getComponent(), "wrap");
		
		inputPanel.add(tfCena.getComponent());
		inputPanel.add(tfVrednost.getComponent(), "wrap");
		
		inputPanel.add(cbSmer.getComponent());
		
	}

	@Override
	public void populateInputsAndRequiredArray() {
		
		inputsArray = new Input[13];
		inputsArray[0] = tfRbrAnalitike;
		inputsArray[1] = zSifraObjekta;
		inputsArray[2] = zPib;
		inputsArray[3] = zSifraArtikla;
		inputsArray[4] = zGodina;
		inputsArray[5] = zBrojPrometnogDokumenta;
		inputsArray[6] = diDatumPromijene;
		inputsArray[7] = cbVrstaDokumenta;
		inputsArray[8] = tfSifraDokumenta;
		inputsArray[9] = tfKolicina;
		inputsArray[10] = tfCena;
		inputsArray[11] = tfVrednost;
		inputsArray[12] = cbSmer;
		
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
	protected void sync() {
		// TODO Auto-generated method stub
//		int index = table.getSelectedRow();
//		if (index < 0) {
//			for (Component c : editableFields) {
//				if (c instanceof JTextField)
//					((JTextField) c).setText("");
//			}
//			return;
//		}
//
//		tfRbrAnalitike.setText((String) model.getValueAt(index, 0));
//		tfSifraObjekta.setText((String) model.getValueAt(index, 1));
//		tfPib.setText((String) model.getValueAt(index, 2));
//		tfSifraArtikla.setText((String) model.getValueAt(index, 3));
//		tfGodina.setText((String) model.getValueAt(index, 4));
//		tfBrojPrometnogDokumenta.setText((String) model.getValueAt(index, 5));
//		tfDatumPromijene.setText((String) model.getValueAt(index, 7));
//
//		switch (((String) model.getValueAt(index, 7)).trim().toLowerCase()) {
//		case "pr":
//			cbVrstaDokumenta.setSelectedItem("Primka");
//			break;
//		case "ot":
//			cbVrstaDokumenta.setSelectedItem("Otpremnica");
//			break;
//		case "mm":
//			cbVrstaDokumenta.setSelectedItem("Medjumagacinsko poslovanje");
//			break;
//		case "ni":
//			cbVrstaDokumenta.setSelectedItem("Nivelacija");
//			break;
//		case "kp":
//			cbVrstaDokumenta.setSelectedItem("Korekcija po popisu");
//			break;
//		case "ps":
//			cbVrstaDokumenta.setSelectedItem("Pocetno stanje");
//			break;
//		}
//
//		tfSifraDokumenta.setText((String) model.getValueAt(index, 8));
//		tfKolicina.setText((String) model.getValueAt(index, 9));
//		tfCena.setText((String) model.getValueAt(index, 10));
//		tfVrednost.setText((String) model.getValueAt(index, 11));
//
//		switch (((String) model.getValueAt(index, 12)).trim().toUpperCase()) {
//		case "U":
//			cbSmer.setSelectedItem("Ulaz");
//			break;
//		case "I":
//			cbSmer.setSelectedItem("Izlaz");
//			break;
//		}
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
