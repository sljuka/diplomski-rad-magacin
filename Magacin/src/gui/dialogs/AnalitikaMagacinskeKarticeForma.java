package gui.dialogs;

import gui.DatePickerComponent;
import gui.DocumentLimit;
import gui.DocumentNumericLimited;

import java.awt.Component;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controllers.FormController;

import model.DataBaseTableModel.tableNames;
import net.miginfocom.swing.MigLayout;
import numeric.textField.NumericTextField;
import actions.ActionCancelAction;
import actions.ActionCommit;

public class AnalitikaMagacinskeKarticeForma extends DatabaseForma {

	private JTextField tfRbrAnalitike;
	private JTextField tfSifraObjekta;
	private JTextField tfPib;
	private JTextField tfSifraArtikla;
	private JTextField tfGodina;
	private JTextField tfBrojPrometnogDokumenta;
	private DatePickerComponent tfDatumPromijene;
	private JComboBox<String> cbVrstaDokumenta;
	private JTextField tfSifraDokumenta;
	private NumericTextField tfKolicina;
	private NumericTextField tfCena;
	private NumericTextField tfVrednost;
	private JComboBox<String> cbSmer;

	public AnalitikaMagacinskeKarticeForma() {
		super();
		ID = tableNames.ANALITIKA_MAGACINSKE_KARTICE;
		setTitle(ID.toString());
		setSizeAndMove(750, 450);
		initializeComponents();
		populateFieldsArray();
		initializeStatusBar();
		setFieldsEditable(false);
		model.setPrimaryKeysNumbers(primaryKeysColumnNumber);
	}

	@Override
	protected void initializeComponents() {
		// TODO Auto-generated method stub
		setLayout(new MigLayout("", "[align r][align l, grow, fill]", ""));
		
		tfBrojPrometnogDokumenta = new JTextField(5);
		tfBrojPrometnogDokumenta.setDocument(new DocumentNumericLimited(5));
		
		tfCena = new NumericTextField(14, new DecimalFormat("0000.00"));
		
		tfDatumPromijene = new DatePickerComponent();
		
		tfGodina = new JTextField(7);
		tfGodina.setDocument(new DocumentNumericLimited(4));
		
		tfKolicina = new NumericTextField(14, new DecimalFormat("0000.00"));
		
		tfPib = new JTextField(13);
		tfPib.setDocument(new DocumentLimit(12));
		
		tfRbrAnalitike = new JTextField(5);
		tfRbrAnalitike.setDocument(new DocumentNumericLimited(5));
		
		tfSifraArtikla = new JTextField(5);	
		tfSifraArtikla.setDocument(new DocumentLimit(4));
		
		tfSifraDokumenta = new JTextField(13);
		tfSifraDokumenta.setDocument(new DocumentLimit(12));
		
		tfSifraObjekta = new JTextField(13);
		tfSifraObjekta.setDocument(new DocumentLimit(12));
		
		tfVrednost = new NumericTextField(5, new DecimalFormat("0000.00"));

		cbVrstaDokumenta = new JComboBox<String>();
		cbVrstaDokumenta.addItem("Primka");
		cbVrstaDokumenta.addItem("Otpremnica");
		cbVrstaDokumenta.addItem("Medjumagacinski promet");
		cbVrstaDokumenta.addItem("Nivelacija");
		cbVrstaDokumenta.addItem("Korekcija po popisu");
		cbVrstaDokumenta.addItem("Pocetno stanje");

		cbSmer = new JComboBox<String>();
		cbSmer.addItem("Izlaz");
		cbSmer.addItem("Ulaz");

		initializeTable();
		controller = new FormController(this);
		initializeToolbar();

		add(toolbar, "dock north");
		add(new JScrollPane(table), "dock north");

		JPanel tfPanel = new JPanel();
		tfPanel.setLayout(new MigLayout("center"));

		tfPanel.add(new JLabel("Rbr. Analitike"));
		tfPanel.add(tfRbrAnalitike);

		tfPanel.add(new JLabel("Sifra objekta"));
		tfPanel.add(tfSifraObjekta, "wrap");

		tfPanel.add(new JLabel("PIB"));
		tfPanel.add(tfPib);

		tfPanel.add(new JLabel("Godina"));
		tfPanel.add(tfGodina, "wrap");

		tfPanel.add(new JLabel("Sifra artikla"));
		tfPanel.add(tfSifraArtikla);

		tfPanel.add(new JLabel("Broj prometnog dokumenta"));
		tfPanel.add(tfBrojPrometnogDokumenta, "wrap");

		tfPanel.add(new JLabel("Datum promene"));
		tfPanel.add(tfDatumPromijene);

		tfPanel.add(new JLabel("Vrsta dokumenta"));
		tfPanel.add(cbVrstaDokumenta, "wrap");

		tfPanel.add(new JLabel("Sifra dokumenta"));
		tfPanel.add(tfSifraDokumenta);

		tfPanel.add(new JLabel("Kolicina"));
		tfPanel.add(tfKolicina, "wrap");

		tfPanel.add(new JLabel("Cena"));
		tfPanel.add(tfCena);

		tfPanel.add(new JLabel("Vrednost"));
		tfPanel.add(tfVrednost, "wrap");

		tfPanel.add(new JLabel("Smer"));
		tfPanel.add(cbSmer);

		add(tfPanel);

		btnPanel = new JPanel();
		btnPanel.setLayout(new MigLayout("align right"));
		btnPanel.add(new JButton(new ActionCommit(controller)), "wrap");
		btnPanel.add(new JButton(new ActionCancelAction(controller)));
		add(btnPanel);
	}

	@Override
	public void populateFieldsArray() {
		// TODO Auto-generated method stub

		editableFields = new Component[13];
		editableFields[0] = tfRbrAnalitike;
		editableFields[1] = tfSifraObjekta;
		editableFields[2] = tfPib;
		editableFields[4] = tfGodina;
		editableFields[3] = tfSifraArtikla;
		editableFields[5] = tfBrojPrometnogDokumenta;
		editableFields[6] = tfDatumPromijene;
		editableFields[7] = cbVrstaDokumenta;
		editableFields[8] = tfSifraDokumenta;
		editableFields[9] = tfKolicina;
		editableFields[10] = tfCena;
		editableFields[11] = tfVrednost;
		editableFields[12] = cbSmer;

	}

	@Override
	public void childResponse(tableNames iD2, String[] childRetVals) {
		// TODO Auto-generated method stub
		switch (iD2) {
		case MAGACINSAK_KARTICA:
			tfSifraObjekta.setText(childRetVals[0]);
			tfPib.setText(childRetVals[1]);
			tfSifraArtikla.setText(childRetVals[2]);
			tfGodina.setText(childRetVals[3]);
			break;
		case STAVKA_PROMETNOG_DOKUMENTA:
			tfBrojPrometnogDokumenta.setText(childRetVals[3]);
			tfPib.setText(childRetVals[0]);
			tfGodina.setText(childRetVals[2]);
			break;
		}
	}

	@Override
	protected void sync() {
		// TODO Auto-generated method stub
		int index = table.getSelectedRow();
		if (index < 0) {
			for (Component c : editableFields) {
				if (c instanceof JTextField)
					((JTextField) c).setText("");
			}
			return;
		}

		tfRbrAnalitike.setText((String) model.getValueAt(index, 0));
		tfSifraObjekta.setText((String) model.getValueAt(index, 1));
		tfPib.setText((String) model.getValueAt(index, 2));
		tfSifraArtikla.setText((String) model.getValueAt(index, 3));
		tfGodina.setText((String) model.getValueAt(index, 4));
		tfBrojPrometnogDokumenta.setText((String) model.getValueAt(index, 5));
		tfDatumPromijene.setText((String) model.getValueAt(index, 6));

		switch (((String) model.getValueAt(index, 7)).trim().toLowerCase()) {
		case "pr":
			cbVrstaDokumenta.setSelectedItem("Primka");
			break;
		case "ot":
			cbVrstaDokumenta.setSelectedItem("Otpremnica");
			break;
		case "mm":
			cbVrstaDokumenta.setSelectedItem("Medjumagacinsko poslovanje");
			break;
		case "ni":
			cbVrstaDokumenta.setSelectedItem("Nivelacija");
			break;
		case "kp":
			cbVrstaDokumenta.setSelectedItem("Korekcija po popisu");
			break;
		case "ps":
			cbVrstaDokumenta.setSelectedItem("Pocetno stanje");
			break;
		}

		tfSifraDokumenta.setText((String) model.getValueAt(index, 8));
		tfKolicina.setText((String) model.getValueAt(index, 9));
		tfCena.setText((String) model.getValueAt(index, 10));
		tfVrednost.setText((String) model.getValueAt(index, 11));

		switch (((String) model.getValueAt(index, 12)).trim().toUpperCase()) {
		case "U":
			cbSmer.setSelectedItem("Ulaz");
			break;
		case "I":
			cbSmer.setSelectedItem("Izlaz");
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

	@Override
	public void setFieldsEditable(boolean b) {
		super.setFieldsEditable(false);
	}

}
