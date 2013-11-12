package gui.forms;

import gui.ComboBoxInput;
import gui.ComboListitem;
import gui.DateInput;
import gui.DocumentNumericLimited;
import gui.Input;
import gui.NumericTextInput;
import gui.TextInput;
import gui.ZoomInput;

import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.DataBaseTableModel;
import model.DataBaseTableModel.tableNames;
import net.miginfocom.swing.MigLayout;
import controllers.FormController;

public class MagacinskeKarticeForma extends DatabaseForma {

	private ZoomInput zPib;
	private ZoomInput zSifraObjekta;
	private ZoomInput zSifraArtikla;
	private ZoomInput zGodina;
	private NumericTextInput tfProsecnaCena;
	private NumericTextInput tfKolicinaPocetnogStanja;
	private NumericTextInput tfVrednostPocetnogStanja;
	private NumericTextInput tfKolicinaUlaza;
	private NumericTextInput tfVrednostUlaza;
	private NumericTextInput tfKolicinaIzlaza;
	private NumericTextInput tfVrednostIzlaza;
	private NumericTextInput tfKalkulisanaCena;
	
	private JTable tableAnalitike;
	private DataBaseTableModel modelAnalitika;
	private JPanel analitikeInputPanel;
	private Input[] inputsAnalitike;
	
	private TextInput tfRbrAnalitike;
	private ZoomInput zAnalitikeSifraObjekta;
	private ZoomInput zAnalitikePib;
	private ZoomInput zAnalitikeSifraArtikla;
	private ZoomInput zAnalitikeGodina;
	private ZoomInput zBrojPrometnogDokumenta;
	private ZoomInput zRedniBrojStavkePrometnogDokumenta;
	private DateInput diDatumPromijene;
	private ComboBoxInput cbVrstaDokumenta;
	private TextInput tfSifraDokumenta;
	private NumericTextInput tfKolicina;
	private NumericTextInput tfCena;
	private NumericTextInput tfVrednost;
	private ComboBoxInput cbSmer;
	
	public MagacinskeKarticeForma(FormController fc) {
		// TODO Auto-generated constructor stub
		super(	fc, tableNames.MAGACINSAK_KARTICA,
				Toolkit.getDefaultToolkit().getScreenSize().width,
				Toolkit.getDefaultToolkit().getScreenSize().height, true);
	}
	
	@Override
	protected void initializeComponents(FormController controller) {
		// TODO Auto-generated method stub
		super.initializeComponents(controller);
		initializeAnalitikeTable();
		initializeAnalitikeFields();
		layoutAnalitikeInputPan();
	}
	
	protected void layoutComponents() {
		setLayout(new MigLayout("", "[align r][align l, grow, fill]", ""));
		add(toolbar, "dock north");
		add(new JScrollPane(table), "dock north");
		add(inputPanel);
		add(analitikeInputPanel, "dock south");
		add(new JScrollPane(tableAnalitike), "dock SOUTH");
		
	};
	
	protected void layoutAnalitikeInputPan() {
		analitikeInputPanel = new JPanel();
		analitikeInputPanel.setLayout(new MigLayout("center"));
		
		for(int i = 0; i<inputsAnalitike.length; i++) {
			if(i%2 == 0)
				analitikeInputPanel.add(inputsAnalitike[i].getComponent());
			else
				analitikeInputPanel.add(inputsAnalitike[i].getComponent(), "wrap");
		}
		
	}

	@Override
	protected void initializeInputFields(FormController controller) {
		// TODO Auto-generated method stub
		inputsArray = new Input[] {
			zPib = new ZoomInput(this, tableNames.PREDUZECE, "Pib", 14, 14),
			zSifraObjekta = new ZoomInput(this, tableNames.POSLOVNI_OBJEKAT, "Sifra poslovnog objetkta", 15, 16),
			zSifraArtikla = new ZoomInput(this, tableNames.ARTIKAL, "Sifra artikla", 15, 15),
			zGodina = new ZoomInput(this, tableNames.POSLOVNA_GODINA, "Poslovna godina", 15, 15),
			tfProsecnaCena = new NumericTextInput("Prosecna cena", 14),
			tfKolicinaPocetnogStanja = new NumericTextInput("Kolicina pocetnog stanja", 14),
			tfVrednostPocetnogStanja = new NumericTextInput("Vrednost pocetnog stanja", 14),
			tfKolicinaUlaza = new NumericTextInput("Kolicina ulaza", 14),
			tfVrednostUlaza = new NumericTextInput("Vrednost ulaza", 14),
			tfKolicinaIzlaza = new NumericTextInput("Kolicina izlaza", 14),
			tfVrednostIzlaza = new NumericTextInput("Vrednost izlaza", 14),
			tfKalkulisanaCena = new NumericTextInput("Kalkulisana cena", 14)
		};
	}
	
	private void initializeAnalitikeFields() {
		// TODO Auto-generated method stub
		
		List<ComboListitem> vrsteDokumenta = new ArrayList<ComboListitem>();
		vrsteDokumenta.add(new ComboListitem("primka", "pr"));
		vrsteDokumenta.add(new ComboListitem("otpremnica", "ot"));
		vrsteDokumenta.add(new ComboListitem("medjumagacinsko", "mm"));
		vrsteDokumenta.add(new ComboListitem("nivelacija", "ni"));
		vrsteDokumenta.add(new ComboListitem("korekcija", "kp"));
		vrsteDokumenta.add(new ComboListitem("pocetno", "ps"));
		
		List<ComboListitem> smerovi = new ArrayList<ComboListitem>();
		smerovi.add(new ComboListitem("Ulaz", "U"));
		smerovi.add(new ComboListitem("Izlaz", "I"));
		
		inputsAnalitike = new Input[] {
			tfRbrAnalitike = new TextInput(5, "Redni broj analitike", new DocumentNumericLimited(5)),
			zAnalitikeSifraObjekta = new ZoomInput(this, tableNames.POSLOVNI_OBJEKAT, "Sifra objekta", 12, 30),
			zAnalitikePib = new ZoomInput(this, tableNames.PREDUZECE, "Preduzece", 12, 30),
			zAnalitikeSifraArtikla = new ZoomInput(this, tableNames.ARTIKAL, "Sifra artikla", 5, 30),
			zAnalitikeGodina = new ZoomInput(this, tableNames.POSLOVNA_GODINA, "Poslovna godina", 5, 5),
			zBrojPrometnogDokumenta = new ZoomInput(this, tableNames.PROMETNI_DOKUMENT, "Broj prometnog dokumenta", 12, 30),
			zRedniBrojStavkePrometnogDokumenta = new ZoomInput(this, tableNames.STAVKA_PROMETNOG_DOKUMENTA, "Broj stavke prometnog dokumenta", 5, 5),
			diDatumPromijene = new DateInput("Datum promene"),
			cbVrstaDokumenta = new ComboBoxInput(vrsteDokumenta, "Vrste dokumenta"),
			tfSifraDokumenta = new TextInput(20, "Sifra dokumenta", null),
			tfKolicina = new NumericTextInput("Kolicina", 20),
			tfCena = new NumericTextInput("Cena", 20),
			tfVrednost = new NumericTextInput("Vrednost", 20),
			cbSmer = new ComboBoxInput(smerovi, "Smer")
		};
	}

	@Override
	public void populateInputsAndRequiredArray() {
		// TODO Auto-generated method stub
		// no need couse this table is acessable for writing only for procedures
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
			for (Input i : inputsAnalitike) {
				i.setText("");
			}
			return;
		}
		
		for (int i = 0; i < inputsAnalitike.length; i++) {
			inputsAnalitike[i].setText((String)modelAnalitika.getValueAt(index, i));
		}
	}

	@Override
	public void childResponse(tableNames iD2, String[] childRetVals) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void sync() {
		// TODO Auto-generated method stub
		super.sync();
		try {
			modelAnalitika.clear();
			modelAnalitika.open(model.getSelectedKeyNameValuePair(table.getSelectedRow()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		childRetVals[0] = zSifraObjekta.getText();
		childRetVals[1] = zPib.getText();
		childRetVals[2] = zSifraArtikla.getText();
		childRetVals[3] = zGodina.getText();

	}

	@Override
	public void populatePrimaryInputsArray() {
		// TODO Auto-generated method stub
		primaryKeysColumnNumber = new int[4];
		primaryKeysColumnNumber[0] = 0;
		primaryKeysColumnNumber[1] = 1;
		primaryKeysColumnNumber[2] = 2;
		primaryKeysColumnNumber[3] = 3;
	}

	@Override
	public void setFieldsEditable(boolean b) {
		// TODO Auto-generated method stub
		for (Input i : inputsArray)
			i.setUserEditable(false);
		for (Input i : inputsAnalitike)
			i.setUserEditable(false);
	}

}
