package gui.dialogs;

import gui.DocumentLimit;
import gui.DocumentNumericLimited;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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

import model.DataBaseTableModel;
import model.DataBaseTableModel.tableNames;

public class ZaposleniForma extends DatabaseForma {

	private JTextField tfImeRadnika;
	private JTextField tfPrezimeRadnika;
	private JTextField tfPib;
	private JTextField tfSifraRadnika;
	private JTextField tfPassword;
	
	private JTextField tfNazivPreduzeca;
	
	private JButton btnZoomPreduzeca;
	
	public ZaposleniForma() {
		// TODO Auto-generated constructor stub
		super();
		ID = tableNames.ZAPOSLENI;
		setTitle(ID.toString());
		setSizeAndMove(700, 420);
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
		tfImeRadnika = new JTextField(30);
		tfImeRadnika.setDocument(new DocumentLimit(30));
		tfPrezimeRadnika = new JTextField(30);
		tfPrezimeRadnika.setDocument(new DocumentLimit(30));
		tfPib = new JTextField(12);
		tfPib.setDocument(new DocumentLimit(12));
		tfPib.setEditable(false);
		tfSifraRadnika = new JTextField(5);
		tfSifraRadnika.setDocument(new DocumentNumericLimited(4));
		tfPassword = new JTextField(30);
		tfPassword.setDocument(new DocumentLimit(30));
		
		tfNazivPreduzeca = new JTextField(30);
		tfNazivPreduzeca.setEditable(false);
		
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
				PreduzecaForma d = new PreduzecaForma();
				d.setParentDialog(ZaposleniForma.this);
				d.setVisible(true);
			}
		});
		
		JPanel tfPanel = new JPanel();
		tfPanel.setLayout(new MigLayout("center"));
		
		tfPanel.add(new JLabel("Ime radnika"));
		tfPanel.add(tfImeRadnika, "wrap");
		
		tfPanel.add(new JLabel("Prezime radnika"));
		tfPanel.add(tfPrezimeRadnika, "wrap");
		
		tfPanel.add(new JLabel("Pib"));
		tfPanel.add(tfPib, "grow 0, split 3");
		tfPanel.add(btnZoomPreduzeca, "grow 0");
		tfPanel.add(tfNazivPreduzeca, "wrap 5");
		
		tfPanel.add(new JLabel("Sifra radnika"));
		tfPanel.add(tfSifraRadnika, "wrap");
		
		tfPanel.add(new JLabel("Password radnika"));
		tfPanel.add(tfPassword, "wrap");
		
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
		editableFields = new Component[6];
		editableFields[0] = tfPib;
		editableFields[1] = tfSifraRadnika;
		editableFields[2] = tfPrezimeRadnika;
		editableFields[3] = tfImeRadnika;
		editableFields[4] = tfPassword;
		JCheckBox isAdmin = new JCheckBox();
		isAdmin.setSelected(false);
		editableFields[5] = isAdmin;
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
			tfImeRadnika.setText("");
			tfPrezimeRadnika.setText("");
			tfSifraRadnika.setText("");
			tfNazivPreduzeca.setText("");
			setFieldsEditable(false);
			return;
		}
		String pib = (String)model.getValueAt(index, 0);
		String sifraRadnika = (String)model.getValueAt(index, 1);
		String prezime = (String)model.getValueAt(index, 2);
		String ime = (String)model.getValueAt(index, 3);
		tfPib.setText(pib);
		tfImeRadnika.setText(ime);
		tfPrezimeRadnika.setText(prezime);
		tfSifraRadnika.setText(sifraRadnika);
		setFieldsEditable(true);
		childRetVals[0] = sifraRadnika;
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
		// TODO Auto-generated method stub
		super.setFieldsEditable(b);
		tfPib.setEditable(false);
		tfNazivPreduzeca.setEditable(false);
		btnZoomPreduzeca.setEnabled(b);
	}
}
