package gui.dialogs;

import gui.DatePickerComponent;
import gui.DocumentLimit;
import gui.DocumentNumericLimited;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controllers.DialogController;

import actions.ActionCancelAction;
import actions.ActionCommit;

import net.miginfocom.swing.MigLayout;

import model.DataBaseTableModel.tableNames;

public class PopisniDokumentDialog extends DatabaseDialog {

	private JTextField txtGodina;
	private JTextField txtPib;
	private JTextField txtSifraObjekta;
	private JTextField txtBrojPopisa;
	private DatePickerComponent txtDatumPopisa;
	private JComboBox<String> cbStatus;

	private JButton btnZoomGodina;
	private JButton btnZoomPoslovniObjekat;

	private JTextField txtNazivObjekta;
	private JTextField txtNazivPreduzeca;

	public PopisniDokumentDialog() {
		super();
		ID = tableNames.POPISNI_DOKUMENT;
		setTitle(ID.toString());
		setSizeAndMove(750, 450);
		setLocationRelativeTo(null);
		initializeComponents();
		initializeStatusBar();
		populateFieldsArray();
		setFieldsEditable(false);

	}

	@Override
	protected void initializeComponents() {
		// TODO Auto-generated method stub
		setLayout(new MigLayout("", "[align r][align l, grow, fill]", ""));

		txtBrojPopisa = new JTextField(5);
		txtBrojPopisa.setDocument(new DocumentNumericLimited(4));

		txtDatumPopisa = new DatePickerComponent();

		txtGodina = new JTextField(10);
		txtGodina.setDocument(new DocumentNumericLimited(4));

		txtNazivObjekta = new JTextField(15);

		txtNazivObjekta.setEditable(false);
		txtNazivPreduzeca = new JTextField(15);
		txtNazivPreduzeca.setEditable(false);

		txtPib = new JTextField(13);
		txtPib.setEditable(false);
		txtPib.setDocument(new DocumentLimit(12));

		txtSifraObjekta = new JTextField(10);
		txtSifraObjekta.setEditable(false);
		txtSifraObjekta.setDocument(new DocumentLimit(12));

		cbStatus = new JComboBox<String>();
		cbStatus.addItem("Faza formiranja");
		cbStatus.addItem("Proknjizen");
		cbStatus.addItem("Storniran");

		initializeTable();
		controller = new DialogController(this);
		initializeToolbar();

		add(toolbar, "dock north");
		add(new JScrollPane(table), "dock north");

		btnZoomGodina = new JButton("...");
		btnZoomGodina.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				PoslovnaGodinaDialog pgD = new PoslovnaGodinaDialog();
				pgD.setParentDialog(PopisniDokumentDialog.this);
				pgD.setKeyFilter(new String[] { "PIB", txtPib.getText() });
				pgD.setVisible(true);
			}
		});

		btnZoomPoslovniObjekat = new JButton("...");
		btnZoomPoslovniObjekat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				PoslovniObjektiDialog poD = new PoslovniObjektiDialog();
				poD.setKeyFilter(new String[] { "PIB", txtPib.getText() });
				poD.setParentDialog(PopisniDokumentDialog.this);
				poD.setVisible(true);
			}
		});

		JPanel tfPanel = new JPanel();
		tfPanel.setLayout(new MigLayout("center"));

		tfPanel.add(new JLabel("Godina"));
		tfPanel.add(txtGodina, "grow 0, split 3");
		tfPanel.add(btnZoomGodina, "grow 0, wrap");

		tfPanel.add(new JLabel("Pib"));
		tfPanel.add(txtPib, "grow 0, wrap");

		tfPanel.add(new JLabel("Sifra objekta"));
		tfPanel.add(txtSifraObjekta, "grow 0, split 3");
		tfPanel.add(btnZoomPoslovniObjekat, "grow 0");
		tfPanel.add(txtNazivObjekta, "wrap 5");

		tfPanel.add(new JLabel("Broj popisa"));
		tfPanel.add(txtBrojPopisa, "wrap");

		tfPanel.add(new JLabel("Datum popisa"));
		tfPanel.add(txtDatumPopisa, "wrap");

		tfPanel.add(new JLabel("Status"));
		tfPanel.add(cbStatus, "wrap");

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
		editableFields = new Component[6];
		editableFields[0] = txtGodina;
		editableFields[1] = txtPib;
		editableFields[2] = txtSifraObjekta;
		editableFields[3] = txtBrojPopisa;
		editableFields[4] = txtDatumPopisa;
		editableFields[5] = cbStatus;
	}

	@Override
	public void childResponse(tableNames iD2, String[] childRetVals) {
		// TODO Auto-generated method stub
		switch (iD2) {
		case POSLOVNA_GODINA:
			txtGodina.setText(childRetVals[0]);
			txtPib.setText(childRetVals[1]);
			break;
		case POSLOVNI_OBJEKAT:
			txtNazivObjekta.setText(childRetVals[1]);
			txtSifraObjekta.setText(childRetVals[0]);
			txtPib.setText(childRetVals[2]);
			break;
		case PREDUZECE:
			txtPib.setText(childRetVals[0]);
			txtNazivPreduzeca.setText(childRetVals[1]);
			break;
		}

	}

	@Override
	protected void sync() {
		// TODO Auto-generated method stub
		int index = table.getSelectedRow();
		if (index < 0) {
			txtBrojPopisa.setText("");
			txtDatumPopisa.setText("");
			txtGodina.setText("");
			txtNazivObjekta.setText("");
			txtNazivPreduzeca.setText("");
			txtPib.setText("");
			txtSifraObjekta.setText("");
			setFieldsEditable(false);
			return;
		}

		String brojPopisa = (String) model.getValueAt(index, 0);
		String godina = (String) model.getValueAt(index, 1);
		String pib = (String) model.getValueAt(index, 2);
		String sifraObjekta = (String) model.getValueAt(index, 3);
		String datumPopisa = (String) model.getValueAt(index, 4);
		String statusDokumenta = (String) model.getValueAt(index, 5);

		txtBrojPopisa.setText(brojPopisa);
		txtDatumPopisa.setText(datumPopisa);
		txtGodina.setText(godina);
		txtPib.setText(pib);
		txtSifraObjekta.setText(sifraObjekta);

		switch (statusDokumenta.trim()) {
		case "f":
			cbStatus.setSelectedItem("Faza formiranja");
			break;
		case "p":
			cbStatus.setSelectedItem("Proknjizen");
			break;
		case "s":
			cbStatus.setSelectedItem("Storniran");
			break;
		}
		setFieldsEditable(true);
		childRetVals[0] = godina;
		childRetVals[1] = pib;
		childRetVals[2] = sifraObjekta;
		childRetVals[3] = brojPopisa;
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

	@Override
	public void setFieldsEditable(boolean b) {
		super.setFieldsEditable(b);
		btnZoomGodina.setEnabled(b);
		btnZoomPoslovniObjekat.setEnabled(b);
		txtNazivObjekta.setEditable(false);
		txtNazivPreduzeca.setEditable(false);
	}

}
