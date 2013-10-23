package gui.dialogs;

import gui.DocumentDecimal;

import gui.DocumentLimit;
import gui.DocumentNumericLimited;


import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controllers.DialogController;

import model.DataBaseTableModel.tableNames;
import net.miginfocom.swing.MigLayout;

import actions.ActionCancelAction;
import actions.ActionCommit;

public class ArtikliDialog extends DatabaseDialog {

	private JTextField tfPib;
	private JTextField tfSifraArtikla;
	private JTextField tfSifraMerneJedinice;
	private JTextField tfNazivArtikla;
	private JTextField tfPakovanje;

	private JTextField tfOznakaMerneJedinice;
	private JTextField tfNazivPreduzeca;

	private JButton btnZoomMerneJedinice;
	private JButton btnZoomPreduzeca;

	public ArtikliDialog() {
		// TODO Auto-generated constructor stub
		super();
		ID = tableNames.ARTIKAL;
		setTitle(ID.toString());
		setSizeAndMove(1000, 600);
		setLocationRelativeTo(null);
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

		tfSifraArtikla = new JTextField(4);
		tfSifraArtikla.setDocument(new DocumentLimit(4));

		tfPib = new JTextField(12);
		tfPib.setEditable(false);
		tfPib.setDocument(new DocumentLimit(12));

		tfSifraMerneJedinice = new JTextField(4);
		tfSifraMerneJedinice.setDocument(new DocumentLimit(5));
		tfSifraMerneJedinice.setEditable(false);
		tfNazivArtikla = new JTextField(20);
		tfNazivArtikla.setDocument(new DocumentLimit(40));

		tfPakovanje = new JTextField(8);
		tfPakovanje.setDocument(new DocumentNumericLimited(10));

		tfOznakaMerneJedinice = new JTextField(5);
		tfOznakaMerneJedinice.setEditable(false);

		tfNazivPreduzeca = new JTextField(15);
		tfNazivPreduzeca.setEditable(false);

		btnZoomMerneJedinice = new JButton("...");
		btnZoomMerneJedinice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MernaJedinicaDialog m = new MernaJedinicaDialog();
				m.setParentDialog(ArtikliDialog.this);
				m.setVisible(true);
			}
		});

		btnZoomPreduzeca = new JButton("...");
		btnZoomPreduzeca.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PreduzecaDialog d = new PreduzecaDialog();
				d.setParentDialog(ArtikliDialog.this);
				d.setVisible(true);
			}
		});

		initializeTable();
		controller = new DialogController(this);
		initializeToolbar();

		add(toolbar, "dock north");
		add(new JScrollPane(table), "dock north");

		JPanel tfPanel = new JPanel();
		tfPanel.setLayout(new MigLayout("center"));

		tfPanel.add(new JLabel("Naziv artikla"));
		tfPanel.add(tfNazivArtikla, "wrap");

		tfPanel.add(new JLabel("Sifra artikla"));
		tfPanel.add(tfSifraArtikla, "wrap");

		tfPanel.add(new JLabel("Pib"));
		tfPanel.add(tfPib, "grow 0, split 3");
		tfPanel.add(btnZoomPreduzeca, "grow 0");
		tfPanel.add(tfNazivPreduzeca, "wrap 5");

		tfPanel.add(new JLabel("Sifra merne jedinice"));
		tfPanel.add(tfSifraMerneJedinice, "grow 0, split 3");
		tfPanel.add(btnZoomMerneJedinice, "grow 0");
		tfPanel.add(tfOznakaMerneJedinice, "wrap 5");

		tfPanel.add(new JLabel("Pakovanje"));
		tfPanel.add(tfPakovanje, "wrap");
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
		editableFields = new Component[5];
		editableFields[0] = tfPib;
		editableFields[1] = tfSifraArtikla;
		editableFields[2] = tfSifraMerneJedinice;
		editableFields[3] = tfNazivArtikla;
		editableFields[4] = tfPakovanje;

	}

	@Override
	public void childResponse(tableNames iD2, String[] childRetVals) {
		// TODO Auto-generated method stub
		if (iD2 == tableNames.MERNA_JEDINICA) {
			tfSifraMerneJedinice.setText(childRetVals[0]);
			tfOznakaMerneJedinice.setText(childRetVals[1]);
		}
		if (iD2 == tableNames.PREDUZECE) {
			tfPib.setText(childRetVals[0]);
			tfNazivPreduzeca.setText(childRetVals[1]);
		}
	}

	@Override
	protected void sync() {
		// TODO Auto-generated method stub
		int index = table.getSelectedRow();
		if (index < 0) {
			tfPib.setText("");
			tfSifraArtikla.setText("");
			tfSifraMerneJedinice.setText("");
			tfNazivArtikla.setText("");
			tfPakovanje.setText("");

			tfNazivPreduzeca.setText("");
			tfOznakaMerneJedinice.setText("");
			setFieldsEditable(false);
			return;
		}
		String pib = (String) model.getValueAt(index, 0);
		String sifraArtikla = (String) model.getValueAt(index, 1);
		String sifraMerneJedinice = (String) model.getValueAt(index, 2);
		String nazivArtikla = (String) model.getValueAt(index, 3);
		String nazivPakovanje = (String) model.getValueAt(index, 4);
		tfPib.setText(pib);
		tfSifraArtikla.setText(sifraArtikla);
		tfSifraMerneJedinice.setText(sifraMerneJedinice);
		tfNazivArtikla.setText(nazivArtikla);
		tfPakovanje.setText(nazivPakovanje);
		tfNazivPreduzeca.setText("");
		tfOznakaMerneJedinice.setText("");
		setFieldsEditable(true);

		childRetVals[0] = sifraArtikla;
		childRetVals[1] = nazivArtikla;
		childRetVals[2] = pib;

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
		primaryKeysColumnNumber[0] = 1;
	}

	@Override
	public void setFieldsEditable(boolean b) {
		super.setFieldsEditable(b);
		tfPib.setEditable(false);
		tfOznakaMerneJedinice.setEditable(false);
		tfNazivPreduzeca.setEditable(false);
		btnZoomMerneJedinice.setEnabled(b);
		btnZoomPreduzeca.setEnabled(b);
	}
	
	@Override
	public void setZoomButtons(Boolean b){
		btnZoomMerneJedinice.setEnabled(b);
		btnZoomPreduzeca.setEnabled(b);
	}
}
