package gui.dialogs;

import gui.DocumentLimit;

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

public class PoslovniPartnerDialog extends DatabaseDialog {

	private JTextField tfPibPreduzeca;
	private JTextField tfPibPoslovnogPartnera;
	private JComboBox<String> cbVrsta;
	private JTextField tfNazivPartnera;

	private JTextField tfNazivPreduzeca;

	private JButton btnZoomPreduzeca;

	public PoslovniPartnerDialog() {
		// TODO Auto-generated constructor stub
		super();
		ID = tableNames.POSLOVNI_PARTNER;
		setTitle(ID.toString());
		setSizeAndMove(500, 300);
		initializeComponents();
		initializeStatusBar();
	}

	@Override
	protected void initializeComponents() {
		// TODO Auto-generated method stub
		setLayout(new MigLayout("", "[align r][align l, grow, fill]", ""));
		
		tfPibPreduzeca = new JTextField(12);
		tfPibPreduzeca.setDocument(new DocumentLimit(12));
		
		tfPibPoslovnogPartnera = new JTextField(12);
		tfPibPoslovnogPartnera.setDocument(new DocumentLimit(12));
		
		cbVrsta = new JComboBox<String>();
		cbVrsta.addItem("kupuje");
		cbVrsta.addItem("dobavlja");
		cbVrsta.addItem("kupuje-dobavlja");
		
		tfNazivPartnera = new JTextField(20);
		tfNazivPartnera.setDocument(new DocumentLimit(40));
		
		tfNazivPreduzeca = new JTextField(20);
		tfNazivPreduzeca.setEditable(false);

		initializeTable();
		controller = new DialogController(this);
		initializeToolbar();

		add(toolbar, "dock north");
		add(new JScrollPane(table), "dock north");

		btnZoomPreduzeca = new JButton("...");
		btnZoomPreduzeca.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PreduzecaDialog p = new PreduzecaDialog();
				p.setParentDialog(PoslovniPartnerDialog.this);
				p.setVisible(true);
			}
		});
		
		JPanel tfPanel = new JPanel();
		tfPanel.setLayout(new MigLayout("center"));
		
		tfPanel.add(new JLabel("Naziv partnera"));
		tfPanel.add(tfNazivPartnera, "wrap");
		
		tfPanel.add(new JLabel("Pib poslovnog partnera"));
		tfPanel.add(tfPibPoslovnogPartnera, "wrap");
		
		tfPanel.add(new JLabel("Vrsta poslovnog partnera"));
		tfPanel.add(cbVrsta, "wrap");
		
		tfPanel.add(new JLabel("Pib preduzeca"));
		tfPanel.add(tfPibPreduzeca, "grow 0, split 3");
		tfPanel.add(btnZoomPreduzeca, "grow 0");
		tfPanel.add(tfNazivPreduzeca, "wrap 5");
		
		add(tfPanel);
		
		btnPanel = new JPanel();
		btnPanel.setLayout(new MigLayout("align right"));
		btnPanel.add(new JButton(new ActionCommit(controller)), "wrap");
		btnPanel.add(new JButton(new ActionCancelAction(controller)));
		add(btnPanel, "cell 3 0");

	}

	@Override
	public void populateFieldsArray() {
		// TODO Auto-generated method stub
		editableFields = new Component[4];
		editableFields[0] = tfPibPreduzeca;
		editableFields[1] = tfPibPoslovnogPartnera;
		editableFields[2] = cbVrsta;
		editableFields[3] = tfNazivPartnera;
	}

	@Override
	public void childResponse(tableNames iD2, String[] childRetVals) {
		// TODO Auto-generated method stub
		if (iD2==tableNames.PREDUZECE) {
			tfPibPreduzeca.setText(childRetVals[0]);
			tfNazivPreduzeca.setText(childRetVals[1]);
		}
	}

	@Override
	protected void sync() {
		// TODO Auto-generated method stub
		int index = table.getSelectedRow();
		if (index < 0) {
			tfPibPoslovnogPartnera.setText("");
			tfPibPreduzeca.setText("");
			tfNazivPartnera.setText("");
			cbVrsta.setSelectedIndex(0);
			
			tfNazivPreduzeca.setText("");
			return;
		}
		String pibPreduzeca = (String)model.getValueAt(index, 0);
		String pibpp = (String)model.getValueAt(index, 1);
		String vrsta = (String)model.getValueAt(index, 2);
		String nazivPartnera = (String)model.getValueAt(index, 3);
		
		tfPibPoslovnogPartnera.setText(pibpp);
		tfPibPreduzeca.setText(pibPreduzeca);
		tfNazivPartnera.setText(nazivPartnera);
		if (vrsta.trim().equals("d"))
			cbVrsta.setSelectedItem("dobavlja");
		else if (vrsta.trim().equals("kd"))
			cbVrsta.setSelectedItem("kupuje-dobavlja");
		else
			cbVrsta.setSelectedItem("kupuje");
		childRetVals[0] = pibpp;
		childRetVals[1] = nazivPartnera;
		childRetVals[2] = pibPreduzeca;
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
	public String comboBoxHandler(JComboBox cb) {
		// TODO Auto-generated method stub
		if (cb.getSelectedItem().equals("dobavlja"))
			return "d";
		else if (cb.getSelectedItem().equals("kupuje-dobavlja"))
			return "kd";
		else if (cb.getSelectedItem().equals("kupuje"));
			return "k";
	}
	
	@Override
	public void setFieldsEditable(boolean b) {
		super.setFieldsEditable(b);
		tfPibPreduzeca.setEditable(false);
		tfNazivPreduzeca.setEditable(false);
		btnZoomPreduzeca.setEnabled(b);
	}

}
