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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import controllers.FormController;

import net.miginfocom.swing.MigLayout;
import actions.ActionAdd;
import actions.ActionCancelAction;
import actions.ActionCommit;
import actions.ActionHelp;
import actions.ActionJumoToPreviousForm;
import actions.ActionRefresh;
import actions.ActionRemove;
import actions.ActionSearch;
import actions.ActionSelectFirst;
import actions.ActionSelectLast;
import actions.ActionSelectNext;
import actions.ActionSelectPrevious;

import model.DataBaseTableModel.tableNames;

public class PrometniDokumentForma extends DatabaseForma {

	private ZoomInput zPib;
	private ZoomInput zGodina;
	private TextInput tfBrojPrometnogDokumenta;
	private ZoomInput zSifraMagacina;
	private ZoomInput zSiftaMagacinaMMPromet;
	private ZoomInput zPibPoslovnogPartnera;
	private DateInput dDatumNastanka;
	private DateInput dDatumKnjizenja;
	private ComboBoxInput cbStatus;
	private ComboBoxInput cbVrstaDokumenta;

	public PrometniDokumentForma(FormController fc) {
		super(fc, tableNames.PROMETNI_DOKUMENT, 1050, 600, true);
	}
	
	@Override
	protected void initializeInputFields(FormController controller) {
		zPib = new ZoomInput(this, tableNames.PREDUZECE, "Pib preduzeca", 15, 30);
		zGodina = new ZoomInput(this, tableNames.POSLOVNA_GODINA, "Poslovna godina", 5, 5);
		tfBrojPrometnogDokumenta = new TextInput(5, "Broj prometnog dokumenta", new DocumentNumericLimited(5));
		zSifraMagacina = new ZoomInput(this, tableNames.POSLOVNI_OBJEKAT, "Magacin", 15, 30);
		zSiftaMagacinaMMPromet = new ZoomInput(this, tableNames.POSLOVNI_OBJEKAT, "Magacin ciljni", 15, 30);
		zPibPoslovnogPartnera = new ZoomInput(this, tableNames.POSLOVNI_PARTNER, "Poslovni partner", 15, 30);
		dDatumNastanka = new DateInput("Datum nastanka");
		dDatumKnjizenja = new DateInput("Datum knizenja");
		
		List<ComboListitem> statusi = new ArrayList<>();
		statusi.add(new ComboListitem("U fazi formiranja", "F"));
		statusi.add(new ComboListitem("Proknjizen", "P"));
		statusi.add(new ComboListitem("Storniran", "S"));
		cbStatus = new ComboBoxInput(statusi, "Statusi dokumenta");
		
		List<ComboListitem> vrste = new ArrayList<>();
		vrste.add(new ComboListitem("Primka", "PR"));
		vrste.add(new ComboListitem("Otpremnica", "OT"));
		vrste.add(new ComboListitem("Medjumagacinski transfer", "MM"));
		cbVrstaDokumenta = new ComboBoxInput(vrste, "Vrste dokumenta");
		
	}

	@Override
	public void populateInputsAndRequiredArray() {
		inputsArray = new Input[10];
		
		inputsArray[0] = zPib;
		inputsArray[1] = zGodina;
		inputsArray[2] = tfBrojPrometnogDokumenta;
		inputsArray[3] = zSifraMagacina;
		inputsArray[4] = zSiftaMagacinaMMPromet;
		inputsArray[5] = zPibPoslovnogPartnera;
		inputsArray[6] = cbVrstaDokumenta;
		inputsArray[7] = dDatumNastanka;
		inputsArray[8] = dDatumKnjizenja;
		inputsArray[9] = cbStatus;
		
		requiredFields = new int[6];
		requiredFields[0] = 0;
		requiredFields[1] = 1;
		requiredFields[2] = 2;
		requiredFields[3] = 3;
		requiredFields[4] = 6;
		requiredFields[5] = 7;
	}
	
	@Override
	public void populatePrimaryInputsArray() {
		// TODO Auto-generated method stub
		primaryKeysColumnNumber = new int[3];
		primaryKeysColumnNumber[0] = 1;
		primaryKeysColumnNumber[0] = 2;
		primaryKeysColumnNumber[0] = 3;
	}

	@Override
	public void childResponse(tableNames iD2, String[] childRetVals) {
		// TODO Auto-generated method stub
		if (iD2==tableNames.POSLOVNI_OBJEKAT) {
			zSifraMagacina.setText(childRetVals[0]);
			((ZoomInput)zSifraMagacina).setNaziv(childRetVals[1]);
			zPib.setText(childRetVals[2]);
		}
		if (iD2==tableNames.POSLOVNA_GODINA) {
			zGodina.setText(childRetVals[0]);
			zPib.setText(childRetVals[1]);
		}
		if (iD2==tableNames.POSLOVNI_PARTNER) {
			zPibPoslovnogPartnera.setText(childRetVals[0]);
			((ZoomInput)zPibPoslovnogPartnera).setNaziv(childRetVals[1]);
			zPib.setText(childRetVals[2]);
		}
	}

}
