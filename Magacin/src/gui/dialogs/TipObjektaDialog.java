package gui.dialogs;

import java.awt.Component;
import java.sql.SQLException;

import gui.DocumentLimit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controllers.DialogController;

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

import net.miginfocom.swing.MigLayout;

import model.DataBaseTableModel;
import model.DataBaseTableModel.tableNames;

public class TipObjektaDialog extends DatabaseDialog {

	private JTextField tfsifraTipa;
	private JTextField tfnazivTipa;

	public TipObjektaDialog() {
		// TODO Auto-generated constructor stub
		super();
		ID = tableNames.TIP_OBJEKTA;
		setTitle(ID.toString());
		setSizeAndMove(500, 300);
		initializeComponents();
		initializeStatusBar();
		populateFieldsArray();
		setFieldsEditable(false);
	}

	
	@Override
	protected void initializeComponents() {
		// TODO Auto-generated method stub
		setLayout(new MigLayout("", "[align r][align l, grow, fill]", ""));
		tfsifraTipa = new JTextField(2);
		tfsifraTipa.setDocument(new DocumentLimit(2));
		tfnazivTipa = new JTextField(30);
		tfsifraTipa.setDocument(new DocumentLimit(40));

		initializeTable();
		controller = new DialogController(this);
		initializeToolbar();

		add(toolbar, "dock north");
		add(new JScrollPane(table), "dock north");

		JPanel tfPanel = new JPanel();
		tfPanel.setLayout(new MigLayout("center"));

		tfPanel.add(new JLabel("Naziv tipa objekta"));
		tfPanel.add(tfnazivTipa, "wrap");

		tfPanel.add(new JLabel("Sifra tipa objekta"));
		tfPanel.add(tfsifraTipa, "wrap");

		add(tfPanel);

		btnPanel = new JPanel();
		btnPanel.setLayout(new MigLayout("align right"));
		btnPanel.add(new JButton(new ActionCommit(controller)), "wrap");
		btnPanel.add(new JButton(new ActionCancelAction(controller)));
		add(btnPanel, "cell 3 0");
	}

	public void sync() {
		// TODO Auto-generated method stub
		int index = table.getSelectedRow();
		if (index < 0) {
			tfnazivTipa.setText("");
			tfsifraTipa.setText("");
			setFieldsEditable(false);
			return;
		}
		String sifraTipa = (String)model.getValueAt(index, 0);
		String nazivTipa = (String)model.getValueAt(index, 1);
		tfsifraTipa.setText(sifraTipa);
		tfnazivTipa.setText(nazivTipa);
		setFieldsEditable(true);
		
		childRetVals = new String[2];
		childRetVals[0] = sifraTipa;
		childRetVals[1] = nazivTipa;
	}

	@Override
	public void populateFieldsArray() {
		// TODO Auto-generated method stub
		editableFields = new Component[2];
		editableFields[0] = tfsifraTipa;
		editableFields[1] = tfnazivTipa;
	}

	@Override
	public void childResponse(tableNames iD2, String[] childRetVals) {
		// TODO Auto-generated method stub
		// FOREVER ALONE
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
		primaryKeysColumnNumber[0] = 0;
	}

}
