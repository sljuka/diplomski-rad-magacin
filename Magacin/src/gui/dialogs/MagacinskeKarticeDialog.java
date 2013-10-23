package gui.dialogs;

import gui.DatePickerComponent;
import gui.DocumentLimit;
import gui.DocumentNumericLimited;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controllers.DialogController;

import model.DataBaseTableModel;
import model.DataBaseTableModel.tableNames;
import net.miginfocom.swing.MigLayout;
import numeric.textField.NumericTextField;

public class MagacinskeKarticeDialog extends DatabaseDialog {

	private JTextField tfPib; // editable false
	private JTextField tfSifraObjekta; // isto
	private JTextField tfSifraArtikla; // isto
	private JTextField tfGodina; // isto
	private JTextField tfProsecnaCena;
	private JTextField tfKolicinaPocetnogStanja;
	private JTextField tfVrednostPocetnogStanja;
	private JTextField tfKolicinaUlaza;
	private JTextField tfVrednostUlaza;
	private JTextField tfKolicinaIzlaza;
	private JTextField tfVrednostIzlaza;
	private JTextField tfKalkulisanaCena;

	private JTextField tfNazivPreduzeca;
	private JTextField tfNazivObjekta;
	private JTextField tfNazivArtikla;

	private JTable tableAnalitike;
	private DataBaseTableModel modelAnalitika;

	private JTextField tfARbrAnalitike;
	private JTextField tfASifraObjekta;
	private JTextField tfAPib;
	private JTextField tfASifraArtikla;
	private JTextField tfAGodina;
	private JTextField tfABrojPrometnogDokumenta;
	private DatePickerComponent tfADatumPromijene;
	private JComboBox<String> cbAVrstaDokumenta;
	private JTextField tfASifraDokumenta;
	private NumericTextField tfAKolicina;
	private NumericTextField tfACena;
	private NumericTextField tfAVrednost;
	private JComboBox<String> cbASmer;

	public MagacinskeKarticeDialog() {
		// TODO Auto-generated constructor stub
		super();
		ID = tableNames.MAGACINSAK_KARTICA;
		setTitle(ID.toString());
		Toolkit toolkit =  Toolkit.getDefaultToolkit ();
		Dimension dim = toolkit.getScreenSize();
		setSizeAndMove(dim.width,dim.height);
		initializeComponents();
		populateFieldsArray();
		setFieldsEditable(false);
		populateStatusBasedComponents();
	}

	private void initializeAnalitikeTable() {
		tableAnalitike = new JTable();
		tableAnalitike.setRowSelectionAllowed(true);
		tableAnalitike.setColumnSelectionAllowed(false);
		tableAnalitike.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modelAnalitika = new DataBaseTableModel(tableNames.ANALITIKA_MAGACINSKE_KARTICE);
		tableAnalitike.setModel(modelAnalitika);
		tableAnalitike.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						// TODO Auto-generated method stub
						if (e.getValueIsAdjusting())
							return;
						syncAnalitike();
					}
				});
	}
	
	private void syncAnalitike() {
		// TODO Auto-generated method stub
		int index = tableAnalitike.getSelectedRow();
		if (index < 0) {
			return;
		}

		tfARbrAnalitike.setText((String) modelAnalitika.getValueAt(index, 0));
		tfASifraObjekta.setText((String) modelAnalitika.getValueAt(index, 1));
		tfAPib.setText((String) modelAnalitika.getValueAt(index, 2));
		tfASifraArtikla.setText((String) modelAnalitika.getValueAt(index, 3));
		tfAGodina.setText((String) modelAnalitika.getValueAt(index, 4));
		tfABrojPrometnogDokumenta.setText((String) modelAnalitika.getValueAt(index, 5));
		tfADatumPromijene.setText((String) modelAnalitika.getValueAt(index, 6));

		switch (((String) modelAnalitika.getValueAt(index, 7)).trim().toLowerCase()) {
		case "pr":
			cbAVrstaDokumenta.setSelectedItem("Primka");
			break;
		case "ot":
			cbAVrstaDokumenta.setSelectedItem("Otpremnica");
			break;
		case "mm":
			cbAVrstaDokumenta.setSelectedItem("Medjumagacinsko poslovanje");
			break;
		case "ni":
			cbAVrstaDokumenta.setSelectedItem("Nivelacija");
			break;
		case "kp":
			cbAVrstaDokumenta.setSelectedItem("Korekcija po popisu");
			break;
		case "ps":
			cbAVrstaDokumenta.setSelectedItem("Pocetno stanje");
			break;
		}

		tfASifraDokumenta.setText((String) modelAnalitika.getValueAt(index, 8));
		tfAKolicina.setText((String) modelAnalitika.getValueAt(index, 9));
		tfACena.setText((String) modelAnalitika.getValueAt(index, 10));
		tfAVrednost.setText((String) modelAnalitika.getValueAt(index, 11));

		switch (((String) modelAnalitika.getValueAt(index, 12)).trim().toUpperCase()) {
		case "U":
			cbASmer.setSelectedItem("Ulaz");
			break;
		case "I":
			cbASmer.setSelectedItem("Izlaz");
			break;
		}
	}

	@Override
	protected void initializeComponents() {
		// TODO Auto-generated method stub
		setLayout(new MigLayout("", "[align r][align l, grow, fill]", ""));

		tfPib = new JTextField(12);
		tfPib.setDocument(new DocumentLimit(12));
		tfPib.setEditable(false);

		tfSifraObjekta = new JTextField(12);
		tfSifraObjekta.setDocument(new DocumentLimit(12));
		tfSifraObjekta.setEditable(false);

		tfSifraArtikla = new JTextField(4);
		tfSifraArtikla.setDocument(new DocumentLimit(4));
		tfSifraArtikla.setEditable(false);

		tfGodina = new JTextField(4);
		tfGodina.setDocument(new DocumentNumericLimited(4));
		tfGodina.setEditable(false);

		tfProsecnaCena = new NumericTextField(14, new DecimalFormat("0000.00"));

		tfKolicinaPocetnogStanja = new NumericTextField(12, new DecimalFormat("0000.00"));

		tfVrednostPocetnogStanja = new NumericTextField(14, new DecimalFormat("0000.00"));

		tfKolicinaUlaza = new NumericTextField(12, new DecimalFormat("0000.00"));
		tfVrednostUlaza =new NumericTextField(14, new DecimalFormat("0000.00"));
		tfKolicinaIzlaza = new NumericTextField(12, new DecimalFormat("0000.00"));
		tfVrednostIzlaza = new NumericTextField(14, new DecimalFormat("0000.00"));
		tfKalkulisanaCena = new NumericTextField(14, new DecimalFormat("0000.00"));

		tfNazivPreduzeca = new JTextField(12);
		tfNazivObjekta = new JTextField(12);
		tfNazivArtikla = new JTextField(12);
		tfNazivPreduzeca.setEditable(false);
		tfNazivObjekta.setEditable(false);
		tfNazivArtikla.setEditable(false);

		initializeTable();
		initializeAnalitikeTable();
		controller = new DialogController(this);
		initMagacinKarticaToolBar();

		JLabel labelMagacini = new JLabel("MAGACINSKE KARTICE");
		labelMagacini.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		labelMagacini.setSize(20, 5);

		add(toolbar, "dock north");
		add(labelMagacini, "dock north");
		add(new JScrollPane(table), "dock north");

		JPanel tfPanel = new JPanel();
		tfPanel.setLayout(new MigLayout("center"));

		tfPanel.add(new JLabel("Sifra artikla"));
		tfPanel.add(tfSifraArtikla);

		tfPanel.add(new JLabel("Naziv artikla"), "gapleft 80");
		tfPanel.add(tfNazivArtikla, "wrap");

		tfPanel.add(new JLabel("Pib"));
		tfPanel.add(tfPib);

		tfPanel.add(new JLabel("Poslovni objekat"), "gapleft 80");
		tfPanel.add(tfNazivObjekta, "wrap");

		tfPanel.add(new JLabel("Godina"));
		tfPanel.add(tfGodina);

		tfPanel.add(new JLabel("Prosecna cena"), "gapleft 80");
		tfPanel.add(tfProsecnaCena, "wrap");

		tfPanel.add(new JLabel("Kolicina pocetnog stanja"));
		tfPanel.add(tfKolicinaPocetnogStanja);

		tfPanel.add(new JLabel("Vrednost pocetnog stanja"), "gapleft 80");
		tfPanel.add(tfVrednostPocetnogStanja, "wrap");

		tfPanel.add(new JLabel("Kolicina ulaza"));
		tfPanel.add(tfKolicinaUlaza);

		tfPanel.add(new JLabel("Vrednost ulaza"), "gapleft 80");
		tfPanel.add(tfVrednostUlaza, "wrap");

		tfPanel.add(new JLabel("Kolicina izlaza"));
		tfPanel.add(tfKolicinaIzlaza);

		tfPanel.add(new JLabel("Vrednost izlaza"), "gapleft 80");
		tfPanel.add(tfVrednostIzlaza, "wrap");

		tfPanel.add(new JLabel("Kalkulisana cena"));
		tfPanel.add(tfKalkulisanaCena, "wrap");

		add(tfPanel, "wrap");

		JLabel labelAnalitike = new JLabel("ANALITIKE MAGACINSKE KARTICE");
		labelAnalitike.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		add(labelAnalitike, "align left");

		JPanel tfAnalalitikePanel = new JPanel();
		initializeAnalitikeFields();
		tfAnalalitikePanel.setLayout(new MigLayout("center"));

		tfAnalalitikePanel.add(new JLabel("Rbr. Analitike"));
		tfAnalalitikePanel.add(tfARbrAnalitike);

		tfAnalalitikePanel.add(new JLabel("Sifra objekta"));
		tfAnalalitikePanel.add(tfASifraObjekta, "wrap");

		tfAnalalitikePanel.add(new JLabel("PIB"));
		tfAnalalitikePanel.add(tfAPib);

		tfAnalalitikePanel.add(new JLabel("Godina"));
		tfAnalalitikePanel.add(tfAGodina, "wrap");

		tfAnalalitikePanel.add(new JLabel("Sifra artikla"));
		tfAnalalitikePanel.add(tfASifraArtikla);

		tfAnalalitikePanel.add(new JLabel("Broj prometnog dokumenta"));
		tfAnalalitikePanel.add(tfABrojPrometnogDokumenta, "wrap");

		tfAnalalitikePanel.add(new JLabel("Datum promene"));
		tfAnalalitikePanel.add(tfADatumPromijene);

		tfAnalalitikePanel.add(new JLabel("Vrsta dokumenta"));
		tfAnalalitikePanel.add(cbAVrstaDokumenta, "wrap");

		tfAnalalitikePanel.add(new JLabel("Sifra dokumenta"));
		tfAnalalitikePanel.add(tfASifraDokumenta);

		tfAnalalitikePanel.add(new JLabel("Kolicina"));
		tfAnalalitikePanel.add(tfAKolicina, "wrap");

		tfAnalalitikePanel.add(new JLabel("Cena"));
		tfAnalalitikePanel.add(tfACena);

		tfAnalalitikePanel.add(new JLabel("Vrednost"));
		tfAnalalitikePanel.add(tfAVrednost, "wrap");

		tfAnalalitikePanel.add(new JLabel("Smer"));
		tfAnalalitikePanel.add(cbASmer);

		add(tfAnalalitikePanel, "dock south, align left");
		add(new JScrollPane(tableAnalitike), "dock south");
	}

	private void initializeAnalitikeFields() {
		// TODO Auto-generated method stub
		tfABrojPrometnogDokumenta = new JTextField(5);
		tfABrojPrometnogDokumenta.setDocument(new DocumentNumericLimited(5));
		tfABrojPrometnogDokumenta.setEditable(false);

		tfACena = new NumericTextField(14, new DecimalFormat("0000.00"));
		tfACena.setEditable(false);

		tfADatumPromijene = new DatePickerComponent();
		tfADatumPromijene.setEditable(false);

		tfAGodina = new JTextField(7);
		tfAGodina.setDocument(new DocumentNumericLimited(4));
		tfAGodina.setEditable(false);

		tfAKolicina = new NumericTextField(14, new DecimalFormat("0000.00"));
		tfAKolicina.setEditable(false);

		tfAPib = new JTextField(13);
		tfAPib.setDocument(new DocumentLimit(12));
		tfAPib.setEditable(false);

		tfARbrAnalitike = new JTextField(5);
		tfARbrAnalitike.setDocument(new DocumentNumericLimited(5));
		tfARbrAnalitike.setEditable(false);

		tfASifraArtikla = new JTextField(5);	
		tfASifraArtikla.setDocument(new DocumentLimit(4));
		tfASifraArtikla.setEditable(false);

		tfASifraDokumenta = new JTextField(13);
		tfASifraDokumenta.setDocument(new DocumentLimit(12));
		tfASifraDokumenta.setEditable(false);

		tfASifraObjekta = new JTextField(13);
		tfASifraObjekta.setDocument(new DocumentLimit(12));
		tfASifraObjekta.setEditable(false);

		tfAVrednost = new NumericTextField(5, new DecimalFormat("0000.00"));
		tfAVrednost.setEditable(false);

		cbAVrstaDokumenta = new JComboBox<String>();
		cbAVrstaDokumenta.addItem("Primka");
		cbAVrstaDokumenta.addItem("Otpremnica");
		cbAVrstaDokumenta.addItem("Medjumagacinski promet");
		cbAVrstaDokumenta.addItem("Nivelacija");
		cbAVrstaDokumenta.addItem("Korekcija po popisu");
		cbAVrstaDokumenta.addItem("Pocetno stanje");
		cbAVrstaDokumenta.setEnabled(false);

		cbASmer = new JComboBox<String>();
		cbASmer.addItem("Izlaz");
		cbASmer.addItem("Ulaz");
		cbASmer.setEnabled(false);
	}

	@Override
	public void populateFieldsArray() {
		// TODO Auto-generated method stub
		editableFields = new Component[12];
		editableFields[0] = tfPib;
		editableFields[1] = tfSifraObjekta;
		editableFields[2] = tfSifraArtikla;
		editableFields[3] = tfGodina;
		editableFields[4] = tfProsecnaCena;
		editableFields[5] = tfKolicinaPocetnogStanja;
		editableFields[6] = tfVrednostPocetnogStanja;
		editableFields[7] = tfKolicinaUlaza;
		editableFields[8] = tfVrednostUlaza;
		editableFields[9] = tfKolicinaIzlaza;
		editableFields[10] = tfVrednostIzlaza;
		editableFields[11] = tfKalkulisanaCena;
	}

	@Override
	public void childResponse(tableNames iD2, String[] childRetVals) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void sync() {
		// TODO Auto-generated method stub
		int index = table.getSelectedRow();
		if (index < 0) {
			for (Component comp : editableFields) {
				((JTextField) comp).setText("");
			}
			tfNazivArtikla.setText("");
			tfNazivObjekta.setText("");
			tfNazivPreduzeca.setText("");
			return;
		}
		String pib = (String) model.getValueAt(index, 0);
		String sifraObjekta = (String) model.getValueAt(index, 1);
		String sifraArtikla = (String) model.getValueAt(index, 2);
		String godina = (String) model.getValueAt(index, 3);
		String prosecnaCena = (String) model.getValueAt(index, 4);
		String kolicinaPocetnogStanja = (String) model.getValueAt(index, 5);
		String vrednostPocetnogStanja = (String) model.getValueAt(index, 6);
		String kolicinaUlaza = (String) model.getValueAt(index, 7);
		String vrednostUlaza = (String) model.getValueAt(index, 8);
		String kolicinaIzlaza = (String) model.getValueAt(index, 9);
		String vrednostIzlaza = (String) model.getValueAt(index, 10);
		String kalkulisanaCena = (String) model.getValueAt(index, 11);
		tfPib.setText(pib);
		tfSifraObjekta.setText(sifraObjekta);
		tfSifraArtikla.setText(sifraArtikla);
		tfGodina.setText(godina);
		tfProsecnaCena.setText(prosecnaCena);
		tfKolicinaPocetnogStanja.setText(kolicinaPocetnogStanja);
		tfVrednostPocetnogStanja.setText(vrednostPocetnogStanja);
		tfKolicinaUlaza.setText(kolicinaUlaza);
		tfVrednostUlaza.setText(vrednostUlaza);
		tfKolicinaIzlaza.setText(kolicinaIzlaza);
		tfVrednostIzlaza.setText(vrednostIzlaza);
		tfKalkulisanaCena.setText(kalkulisanaCena);

		try {
			modelAnalitika.clear();
			modelAnalitika.open(new String[] {"PIB", tfPib.getText(), "SIFRA_OBJEKTA", tfSifraObjekta.getText(),
					"SIFRA_ARTIKLA", tfSifraArtikla.getText(), "GODINA", tfGodina.getText()});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		childRetVals[0] = sifraObjekta;
		childRetVals[1] = pib;
		childRetVals[2] = sifraArtikla;
		childRetVals[3] = godina;

	}

	@Override
	public void populateStatusBasedComponents() {
		// TODO Auto-generated method stub
		statusBasedButtons = new Component[0];
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
