package gui.dialogs;

import java.awt.Component;

import gui.DocumentLimit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controllers.DialogController;

import actions.ActionCancelAction;
import actions.ActionCommit;

import net.miginfocom.swing.MigLayout;

import model.DataBaseTableModel.tableNames;

public class MernaJedinicaDialog extends DatabaseDialog {
	
	private JTextField tfSifraJedinice;
	private JTextField tfNazivJedinice;
	private JTextField tfOznakaJedinice;
	
	public MernaJedinicaDialog() {
		// TODO Auto-generated constructor stub
		super();
		ID = tableNames.MERNA_JEDINICA;
		setTitle(ID.toString());
		setSizeAndMove(500, 300);
		setLocationRelativeTo(null);
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
		
		tfSifraJedinice = new JTextField(5);
		tfSifraJedinice.setDocument(new DocumentLimit(5));
		
		tfNazivJedinice = new JTextField(12);
		tfNazivJedinice.setDocument(new DocumentLimit(12));
		tfOznakaJedinice = new JTextField(5);
		tfOznakaJedinice.setDocument(new DocumentLimit(5));
		
		
		initializeTable();
		controller = new DialogController(this);
		initializeToolbar();
		
		add(toolbar, "dock north");
		add(new JScrollPane(table), "dock north");
		
		JPanel tfPanel = new JPanel();
		tfPanel.setLayout(new MigLayout("center"));
		
		tfPanel.add(new JLabel("Naziv jedinice"));
		tfPanel.add(tfNazivJedinice, "wrap");
		
		tfPanel.add(new JLabel("Sifra jedinice"));
		tfPanel.add(tfSifraJedinice, "wrap");
		
		tfPanel.add(new JLabel("Oznaka jedinice"));
		tfPanel.add(tfOznakaJedinice, "wrap");
		
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
		editableFields = new Component[3];
		editableFields[0] = tfSifraJedinice;
		editableFields[1] = tfNazivJedinice;
		editableFields[2] = tfOznakaJedinice;
	}

	@Override
	public void childResponse(tableNames iD2, String[] childRetVals) {
		// TODO Auto-generated method stub
		//no children
	}

	@Override
	protected void sync() {
		// TODO Auto-generated method stub
		int index = table.getSelectedRow();
		if (index < 0) {
			tfNazivJedinice.setText("");
			tfOznakaJedinice.setText("");
			tfSifraJedinice.setText("");
			setFieldsEditable(false);
			return;
		}
		String sifraJ = (String)model.getValueAt(index, 0);
		String nazivJ = (String)model.getValueAt(index, 1);
		String oznakaJ = (String)model.getValueAt(index, 2);
		tfNazivJedinice.setText(nazivJ);
		tfOznakaJedinice.setText(oznakaJ);
		tfSifraJedinice.setText(sifraJ);
		setFieldsEditable(true);
		childRetVals[0] = sifraJ;
		childRetVals[1] = oznakaJ;
	}

	@Override
	public void populateStatusBasedComponents() {
		// TODO Auto-generated method stub
		// no have
	}

	@Override
	public void initializePrimaryKeysNumbers() {
		// TODO Auto-generated method stub
		primaryKeysColumnNumber = new int[1];
		primaryKeysColumnNumber[0] = 0;
	}
	
	@Override
	public void setFieldsEditable(boolean b){
		tfSifraJedinice.setEditable(b);
		tfOznakaJedinice.setEditable(b);
		tfNazivJedinice.setEditable(b);
	}

}
