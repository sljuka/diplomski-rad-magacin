package gui.dialogs;

import gui.DocumentLimit;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.ZoneView;

import controllers.FormController;

import model.DataBaseTableModel.tableNames;
import net.miginfocom.swing.MigLayout;
import actions.ActionCancelAction;
import actions.ActionCommit;

public class PoslovnaGodinaForma extends DatabaseForma {

	private JTextField tfPib;
	private JTextField tfgodina;
	private JCheckBox cZakljucena;

	private JTextField tfNazivPreduzeca;

	private JButton btnZoomPreduzeca;

	private JButton btnZakljuciGodinu;

	public PoslovnaGodinaForma() {
		// TODO Auto-generated constructor stub
		super();
		ID = tableNames.POSLOVNA_GODINA;
		setTitle(ID.toString());
		setSizeAndMove(550, 300);
		setLocationRelativeTo(null);
		initializeComponents();
		populateStatusBasedComponents();
		populateFieldsArray();
		initializeStatusBar();
		setFieldsEditable(false);
		model.setPrimaryKeysNumbers(primaryKeysColumnNumber);
	}

	@Override
	protected void initializeComponents() {
		// TODO Auto-generated method stub
		setLayout(new MigLayout("", "[align r][align l, grow, fill]", ""));

		tfPib = new JTextField(12);
		tfPib.setDocument(new DocumentLimit(12));

		tfgodina = new JTextField(4);
		tfgodina.setDocument(new DocumentLimit(4));

		cZakljucena = new JCheckBox("zakljucena");

		tfNazivPreduzeca = new JTextField(20);

		initializeTable();
		controller = new FormController(this);
		initializeToolbar();

		add(toolbar, "dock north");
		add(new JScrollPane(table), "dock north");

		btnZoomPreduzeca = new JButton("...");
		btnZoomPreduzeca.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				PreduzecaForma p = new PreduzecaForma();
				p.setParentDialog(PoslovnaGodinaForma.this);
				p.setVisible(true);
			}
		});

		JPanel tfPanel = new JPanel();
		tfPanel.setLayout(new MigLayout("center"));

		tfPanel.add(new JLabel("Godina"));
		tfPanel.add(tfgodina, "wrap");

		tfPanel.add(new JLabel("Pib"));
		tfPanel.add(tfPib, "grow 0, split 3");
		tfPanel.add(btnZoomPreduzeca, "grow 0");
		tfPanel.add(tfNazivPreduzeca, "wrap 5");

		tfPanel.add(cZakljucena, "wrap");
		add(tfPanel);

		btnPanel = new JPanel();
		btnPanel.setLayout(new MigLayout("align right"));
		btnPanel.add(new JButton(new ActionCommit(controller)), "wrap");
		btnPanel.add(new JButton(new ActionCancelAction(controller)));
		add(btnPanel, "wrap");

		btnZakljuciGodinu = new JButton("Zakljuci godinu");
		btnZakljuciGodinu.setIcon(new ImageIcon("images/finish.gif"));
		btnZakljuciGodinu.setEnabled(false);
		btnZakljuciGodinu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				controller.zakljuciGodinu(new String[] { "PIB",
						tfPib.getText().trim(), "GODINA", tfgodina.getText() });
			}
		});
		add(btnZakljuciGodinu, "width :130:, align left");
	}

	@Override
	public void populateFieldsArray() {
		// TODO Auto-generated method stub
		editableFields = new Component[3];
		editableFields[0] = tfPib;
		editableFields[1] = tfgodina;
		editableFields[2] = cZakljucena;
	}

	@Override
	public void childResponse(tableNames iD2, String[] childRetVals) {
		// TODO Auto-generated method stub
		if (iD2 == tableNames.PREDUZECE) {
			tfPib.setText(childRetVals[0]);
			tfNazivPreduzeca.setText(childRetVals[1]);
		}
	}

	@Override
	public void sync() {
		// TODO Auto-generated method stub
		int index = table.getSelectedRow();
		if (index < 0) {
			tfPib.setText("");
			tfgodina.setText("");
			cZakljucena.setSelected(false);
			tfNazivPreduzeca.setText("");
			btnZakljuciGodinu.setEnabled(false);
			return;
		}
		String pib = (String) model.getValueAt(index, 0);
		String godina = (String) model.getValueAt(index, 1);
		String zakljucena = (String) model.getValueAt(index, 2);
		tfPib.setText(pib);
		tfgodina.setText(godina);
		cZakljucena.setSelected(zakljucena.equals("1"));
		btnZakljuciGodinu.setEnabled(!cZakljucena.isSelected());
		setFieldsEditable(!cZakljucena.isSelected());
		childRetVals[0] = godina;
		childRetVals[1] = pib;
	}

	@Override
	public void populateStatusBasedComponents() {
		// TODO Auto-generated method stub
		statusBasedButtons = new Component[1];
		statusBasedButtons[0] = btnZakljuciGodinu;
	}

	@Override
	public void initializePrimaryKeysNumbers() {
		// TODO Auto-generated method stub
		primaryKeysColumnNumber = new int[2];
		primaryKeysColumnNumber[0] = 0;
		primaryKeysColumnNumber[1] = 1;
	}

	@Override
	public void setFieldsEditable(boolean b) {
		super.setFieldsEditable(b);
		tfPib.setEditable(false);
		cZakljucena.setEnabled(false);
		btnZoomPreduzeca.setEnabled(b);
	}

}
