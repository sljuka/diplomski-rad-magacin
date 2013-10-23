package gui.dialogs;

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

import model.DataBaseTableModel.tableNames;
import net.miginfocom.swing.MigLayout;
import actions.ActionCancelAction;
import actions.ActionCommit;

public class ClanPopisneKomisijeDialog extends DatabaseDialog {

	private JTextField tfGodina;
	private JTextField tfPib;
	private JTextField tfSifraObjekta;
	private JTextField tfBrojPopisa;
	private JTextField tfSifraRdnk;
	private JComboBox<String> cbFunkcijaClana;

	private JButton btnZoomPopisniDokument;
	private JButton btnZoomZaposleni;

	public ClanPopisneKomisijeDialog() {
		super();
		ID = tableNames.CLAN_POPISNE_KOMISIJE;
		setTitle(ID.toString());
		setSizeAndMove(1000, 600);
		initializeComponents();
		populateFieldsArray();
		initializeStatusBar();
		setFieldsEditable(false);
		model.setPrimaryKeysNumbers(primaryKeysColumnNumber);
	}

	@Override
	protected void initializeComponents() {
		setLayout(new MigLayout("", "[align r][align l, grow, fill]", ""));
		tfGodina = new JTextField(4);
		tfGodina.setDocument(new DocumentNumericLimited(4));
		tfGodina.setEditable(false);
		tfPib = new JTextField(12);
		tfPib.setDocument(new DocumentLimit(12));
		tfPib.setEditable(false);
		tfSifraObjekta = new JTextField(12);
		tfSifraObjekta.setDocument(new DocumentLimit(12));
		tfSifraObjekta.setEditable(false);
		tfBrojPopisa = new JTextField(4);
		tfBrojPopisa.setEditable(false);
		tfBrojPopisa.setDocument(new DocumentNumericLimited(4));
		tfSifraRdnk = new JTextField(4);
		tfSifraRdnk.setEditable(false);
		tfSifraRdnk.setDocument(new DocumentNumericLimited(4));

		cbFunkcijaClana = new JComboBox<String>();
		cbFunkcijaClana.addItem("Predsednik");
		cbFunkcijaClana.addItem("Clan");

		initializeTable();
		controller = new DialogController(this);
		initializeToolbar();

		btnZoomPopisniDokument = new JButton("...");
		btnZoomPopisniDokument.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				PopisniDokumentDialog pdDlg = new PopisniDokumentDialog();
				pdDlg.setParentDialog(ClanPopisneKomisijeDialog.this);
				pdDlg.setVisible(true);
			}
		});

		btnZoomZaposleni = new JButton("...");
		btnZoomZaposleni.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ZaposleniDialog zapDlg = new ZaposleniDialog();
				zapDlg.setParentDialog(ClanPopisneKomisijeDialog.this);
				zapDlg.setVisible(true);
			}
		});

		initializeTable();
		controller = new DialogController(this);
		initializeToolbar();

		add(toolbar, "dock north");
		add(new JScrollPane(table), "dock north");

		JPanel tfPanel = new JPanel();
		tfPanel.setLayout(new MigLayout("center"));

		tfPanel.add(new JLabel("Godina:"));
		tfPanel.add(tfGodina, "wrap");

		tfPanel.add(new JLabel("PIB:"));
		tfPanel.add(tfPib, "wrap");

		tfPanel.add(new JLabel("Sifra objekta:"));
		tfPanel.add(tfSifraObjekta, "wrap");

		tfPanel.add(new JLabel("Broj popisa"));
		tfPanel.add(tfBrojPopisa, "grow 0, split 3");
		tfPanel.add(btnZoomPopisniDokument, "grow 0, wrap");

		tfPanel.add(new JLabel("Sifra radnika:"));
		tfPanel.add(tfSifraRdnk, "grow 0, split 3");
		tfPanel.add(btnZoomZaposleni, "grow 0, wrap");

		tfPanel.add(new JLabel("Funkcija clana u komisiji:"));
		tfPanel.add(cbFunkcijaClana, "wrap");
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
		editableFields[0] = tfGodina;
		editableFields[1] = tfPib;
		editableFields[2] = tfSifraObjekta;
		editableFields[3] = tfBrojPopisa;
		editableFields[4] = tfSifraRdnk;
		editableFields[5] = cbFunkcijaClana;

	}

	@Override
	public void childResponse(tableNames iD2, String[] childRetVals) {
		// TODO Auto-generated method stub
		switch (iD2) {
		case POPISNI_DOKUMENT:
			tfGodina.setText(childRetVals[0]);
			tfPib.setText(childRetVals[1]);
			tfBrojPopisa.setText(childRetVals[3]);
			tfSifraObjekta.setText(childRetVals[2]);
			break;
		case ZAPOSLENI:
			tfSifraRdnk.setText(childRetVals[0]);
			break;

		}

	}

	@Override
	protected void sync() {
		// TODO Auto-generated method stub
		int index = table.getSelectedRow();
		if (index < 0) {
			tfBrojPopisa.setText("");
			tfGodina.setText("");
			tfPib.setText("");
			tfSifraObjekta.setText("");
			tfSifraRdnk.setText("");
			cbFunkcijaClana.setSelectedIndex(0);
			setFieldsEditable(false);
			return;
		}

		tfBrojPopisa.setText((String) model.getValueAt(index, 3));
		tfGodina.setText((String) model.getValueAt(index, 0));
		tfPib.setText((String) model.getValueAt(index, 1));
		tfSifraObjekta.setText((String) model.getValueAt(index, 2));
		tfSifraRdnk.setText((String) model.getValueAt(index, 4));

		switch (((String) model.getValueAt(index, 5))) {
		case "P":
			cbFunkcijaClana.setSelectedItem("Predsednik");
			break;
		case "C":
			cbFunkcijaClana.setSelectedItem("Clan");
			break;

		}
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
		super.setFieldsEditable(b);
		btnZoomPopisniDokument.setEnabled(b);
		btnZoomZaposleni.setEnabled(b);
	}
}