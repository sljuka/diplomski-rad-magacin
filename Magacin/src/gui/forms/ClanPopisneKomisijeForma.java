package gui.forms;

import gui.ComboBoxInput;
import gui.ComboListitem;
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

import model.DataBaseTableModel.tableNames;
import net.miginfocom.swing.MigLayout;
import actions.ActionCancelAction;
import actions.ActionCommit;

public class ClanPopisneKomisijeForma extends DatabaseForma {

	private ZoomInput zGodina;
	private ZoomInput zPib;
	private ZoomInput zSifraObjekta;
	private ZoomInput zBrojPopisa;
	private ZoomInput zSifraRdnk;
	private ComboBoxInput cbFunkcijaClana;

	public ClanPopisneKomisijeForma(FormController fc) {
		super(fc, tableNames.CLAN_POPISNE_KOMISIJE, 1000, 600, false);
	}

	@Override
	protected void initializeInputFields(FormController controller) {
		zGodina = new ZoomInput(this, tableNames.POSLOVNA_GODINA, "Poslovna godina", 5, 5);
		zPib = new ZoomInput(this, tableNames.PREDUZECE, "Pib", 15, 30);
		zSifraObjekta = new ZoomInput(this, tableNames.POSLOVNI_OBJEKAT, "Sifra objekta", 10, 30);
		zBrojPopisa = new ZoomInput(this, tableNames.POPISNI_DOKUMENT, "Broj popusa", 5, 5);
		zSifraRdnk = new ZoomInput(this, tableNames.ZAPOSLENI, "Sifra radnika", 4, 4);
		
		List<ComboListitem> items = new ArrayList<>();
		items.add(new ComboListitem("Predsednik", "P"));
		items.add(new ComboListitem("Clan", "C"));
		cbFunkcijaClana = new ComboBoxInput(items, "Funkcija clana");
	}

	@Override
	public void populateInputsAndRequiredArray() {

		inputsArray = new Input[6];
		inputsArray[0] = zGodina;
		inputsArray[1] = zPib;
		inputsArray[2] = zSifraObjekta;
		inputsArray[3] = zBrojPopisa;
		inputsArray[4] = zSifraRdnk;
		inputsArray[5] = cbFunkcijaClana;
		
		requiredFields = new int[6];
		requiredFields[0] = 0;
		requiredFields[1] = 1;
		requiredFields[2] = 2;
		requiredFields[3] = 3;
		requiredFields[4] = 4;
		requiredFields[5] = 5;
		
		inputPanel = new JPanel();
		inputPanel.setLayout(new MigLayout("center"));

		inputPanel.add(zGodina.getComponent(), "wrap");
		inputPanel.add(zPib.getComponent(), "wrap");
		inputPanel.add(zSifraObjekta.getComponent(), "wrap");
		inputPanel.add(zBrojPopisa.getComponent(), "wrap");
		inputPanel.add(zSifraRdnk.getComponent(), "wrap");
		inputPanel.add(cbFunkcijaClana.getComponent(), "wrap");
	}

	@Override
	public void childResponse(tableNames iD2, String[] childRetVals) {
		// TODO Auto-generated method stub
		switch (iD2) {
		case POPISNI_DOKUMENT:
			zGodina.setText(childRetVals[2]);
			zPib.setText(childRetVals[0]);
			zBrojPopisa.setText(childRetVals[3]);
			zSifraObjekta.setText(childRetVals[1]);
			break;
		case ZAPOSLENI:
			zSifraRdnk.setText(childRetVals[0]);
			break;
		}
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
	public void setFieldsEditable(boolean b) {
		// TODO Auto-generated method stub
		super.setFieldsEditable(b);
		zPib.setUserEditable(false);
		zBrojPopisa.setUserEditable(false);
		zSifraObjekta.setUserEditable(false);
	}

}