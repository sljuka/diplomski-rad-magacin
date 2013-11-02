package gui.dialogs;

import gui.DocumentLimit;
import gui.DocumentNumericLimited;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controllers.FormController;

import actions.ActionCancelAction;
import actions.ActionCommit;

import net.miginfocom.swing.MigLayout;
import numeric.textField.NumericTextField;

import model.DataBaseTableModel.tableNames;

public class StavkePopisaForma extends DatabaseForma {

	private JTextField txtGodina;
	private JTextField txtSifraObjekta;
	private JTextField txtBrojPopisa;
	private JTextField txtPib;
	private JTextField txtSifraArtikla;
	private NumericTextField txtKolicinaPoPopisu;
	private NumericTextField txtCenaPoPopisu;
	private NumericTextField txtVrednostPoPopisu;
	private NumericTextField txtKolicinaUKartici;
	private NumericTextField txtCenaUKartici;
	private NumericTextField txtVrednostUKartici;

	private JButton btnZoomArtikal;
	private JButton btnZoomPopisniDokument;

	public StavkePopisaForma() {
		super();
		ID = tableNames.STAVKE_POPISA;
		setTitle(ID.toString());
		setSizeAndMove(750, 450);
		initializeComponents();
		initializeStatusBar();
		populateFieldsArray();
		setFieldsEditable(false);
		model.setPrimaryKeysNumbers(primaryKeysColumnNumber);
	}

	@Override
	protected void initializeComponents() {
		// TODO Auto-generated method stub
		setLayout(new MigLayout("", "[align r][align l, grow, fill]", ""));
		txtBrojPopisa = new JTextField(5);
		txtBrojPopisa.setDocument(new DocumentNumericLimited(4));
		txtGodina = new JTextField(10);
		txtGodina.setDocument(new DocumentNumericLimited(4));
		txtPib = new JTextField(13);
		txtPib.setDocument(new DocumentLimit(12));
		txtSifraObjekta = new JTextField(10);
		txtSifraObjekta.setDocument(new DocumentLimit(12));
		txtSifraArtikla = new JTextField(12);
		txtSifraArtikla.setDocument(new DocumentLimit(4));
		txtKolicinaPoPopisu = new NumericTextField(12, new DecimalFormat("0000.00"));
		
		txtCenaPoPopisu = new NumericTextField(5, new DecimalFormat("0000.00"));
		txtVrednostPoPopisu = new NumericTextField(5, new DecimalFormat("0000.00"));
		txtKolicinaUKartici = new NumericTextField(5, new DecimalFormat("0000.00"));
		txtCenaUKartici = new NumericTextField(5, new DecimalFormat("0000.00"));
		txtVrednostUKartici = new NumericTextField(5, new DecimalFormat("0000.00"));

		populateFieldsArray();
		for (Component c : editableFields) {
			if (c instanceof JTextField)
				((JTextField) c).setEditable(false);
		}

		initializeTable();
		controller = new FormController(this);
		initializeToolbar();

		add(toolbar, "dock north");
		add(new JScrollPane(table), "dock north");

		btnZoomArtikal = new JButton("...");
		btnZoomArtikal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ArtikliForma artDlg = new ArtikliForma();
				artDlg.setParentDialog(StavkePopisaForma.this);
				artDlg.setVisible(true);
			}
		});

		btnZoomPopisniDokument = new JButton("...");
		btnZoomPopisniDokument.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				PopisniDokumentForma pdDlg = new PopisniDokumentForma();
				pdDlg.setParentDialog(StavkePopisaForma.this);
				pdDlg.setVisible(true);
			}
		});

		JPanel tfPanel = new JPanel();
		tfPanel.setLayout(new MigLayout("center"));

		tfPanel.add(new JLabel("Godina"));
		tfPanel.add(txtGodina);

		tfPanel.add(new JLabel("PIB"));
		tfPanel.add(txtPib, "wrap");

		tfPanel.add(new JLabel("Sifra objekta"));
		tfPanel.add(txtSifraObjekta);

		tfPanel.add(new JLabel("Broj popisa"));
		tfPanel.add(txtBrojPopisa, "grow 0, split 2");
		tfPanel.add(btnZoomPopisniDokument, "grow 0, wrap");

		tfPanel.add(new JLabel("Sifra artikla"));
		tfPanel.add(txtSifraArtikla, "grow 0, split 2");
		tfPanel.add(btnZoomArtikal, "grow 0");

		tfPanel.add(new JLabel("Kolicina po popisu"));
		tfPanel.add(txtKolicinaPoPopisu, "wrap");

		tfPanel.add(new JLabel("Cena po popisu"));
		tfPanel.add(txtCenaPoPopisu);

		tfPanel.add(new JLabel("Vrednost po popisu"));
		tfPanel.add(txtVrednostPoPopisu, "wrap");

		tfPanel.add(new JLabel("Kolicina u kartici"));
		tfPanel.add(txtKolicinaUKartici);

		tfPanel.add(new JLabel("Cena u kartici"));
		tfPanel.add(txtCenaUKartici, "wrap");

		tfPanel.add(new JLabel("Vrednost u kartici"));
		tfPanel.add(txtVrednostUKartici);

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
		editableFields = new Component[11];
		editableFields[0] = txtGodina;
		editableFields[1] = txtPib;
		editableFields[2] = txtSifraObjekta;
		editableFields[3] = txtBrojPopisa;
		editableFields[4] = txtSifraArtikla;
		editableFields[5] = txtKolicinaPoPopisu;
		editableFields[6] = txtCenaPoPopisu;
		editableFields[7] = txtVrednostPoPopisu;
		editableFields[8] = txtKolicinaUKartici;
		editableFields[9] = txtCenaUKartici;
		editableFields[10] = txtVrednostUKartici;

	}

	@Override
	public void childResponse(tableNames iD2, String[] childRetVals) {
		// TODO Auto-generated method stub
		switch (iD2) {
		case POPISNI_DOKUMENT:
			txtGodina.setText(childRetVals[0]);
			txtBrojPopisa.setText(childRetVals[3]);
			txtPib.setText(childRetVals[1]);
			txtSifraObjekta.setText(childRetVals[2]);
			break;
		case ARTIKAL:
			txtSifraArtikla.setText(childRetVals[0]);
			break;

		}

	}

	@Override
	protected void sync() {
		// TODO Auto-generated method stub
		int index = table.getSelectedRow();
		if (index < 0) {
			txtBrojPopisa.setText("");
			txtCenaPoPopisu.setText("");
			txtCenaUKartici.setText("");
			txtGodina.setText("");
			txtKolicinaPoPopisu.setText("");
			txtKolicinaUKartici.setText("");
			txtPib.setText("");
			txtSifraArtikla.setText("");
			txtSifraObjekta.setText("");
			txtVrednostPoPopisu.setText("");
			txtVrednostUKartici.setText("");
			setFieldsEditable(false);
			return;
		}

		txtGodina.setText((String) model.getValueAt(index, 0));
		txtSifraObjekta.setText((String) model.getValueAt(index, 1));
		txtBrojPopisa.setText((String) model.getValueAt(index, 2));
		txtPib.setText((String) model.getValueAt(index, 3));
		txtSifraArtikla.setText((String) model.getValueAt(index, 4));
		txtKolicinaPoPopisu.setText((String) model.getValueAt(index, 5));
		txtCenaPoPopisu.setText((String) model.getValueAt(index, 6));
		txtVrednostPoPopisu.setText((String) model.getValueAt(index, 7));
		txtKolicinaUKartici.setText((String) model.getValueAt(index, 8));
		txtCenaUKartici.setText((String) model.getValueAt(index, 9));
		txtVrednostUKartici.setText((String) model.getValueAt(index, 10));
		setFieldsEditable(true);
	}

	@Override
	public void populateStatusBasedComponents() {
		// TODO Auto-generated method stub
		statusBasedButtons = new Component[0];

	}

	@Override
	public void initializePrimaryKeysNumbers() {
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
		btnZoomArtikal.setEnabled(b);
		btnZoomPopisniDokument.setEnabled(b);
	}

}
