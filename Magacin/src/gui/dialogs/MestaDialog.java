package gui.dialogs;

import gui.DocumentLimit;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;

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

import net.miginfocom.swing.MigLayout;

import actions.ActionAdd;
import actions.ActionCancelAction;
import actions.ActionCommit;
import actions.ActionHelp;
import actions.ActionJumoToPreviousForm;
import actions.ActionJumpToNextForm;
import actions.ActionRefresh;
import actions.ActionRemove;
import actions.ActionSearch;
import actions.ActionSelectFirst;
import actions.ActionSelectLast;
import actions.ActionSelectNext;
import actions.ActionSelectPrevious;

import model.DataBaseTableModel;
import model.Lookup;
import model.DataBaseTableModel.tableNames;

public class MestaDialog extends DatabaseDialog {

	private JTextField tfSifraDrzave;
	private JTextField tfSifraMesta;
	private JTextField tfNazivMesta;

	private JTextField tfNazivDrzave;

	private JButton btnZoomDrzave;

	public MestaDialog() {
		// TODO Auto-generated constructor stub
		super();
		ID = tableNames.NASELJENO_MESTO;
		setTitle(ID.toString());
		setSizeAndMove(500, 300);
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

		tfSifraDrzave = new JTextField(3);
		tfSifraDrzave.setDocument(new DocumentLimit(3));

		tfSifraMesta = new JTextField(3);
		tfSifraMesta.setDocument(new DocumentLimit(5));
		tfNazivMesta = new JTextField(30);
		tfNazivMesta.setDocument(new DocumentLimit(40));

		tfNazivDrzave = new JTextField(20);

		initializeTable();
		controller = new DialogController(this);
		initializeToolbar();

		add(toolbar, "dock north");
		add(new JScrollPane(table), "dock north");

		btnZoomDrzave = new JButton("...");
		btnZoomDrzave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				DrzaveDialog d = new DrzaveDialog();
				d.setParentDialog(MestaDialog.this);
				d.setVisible(true);
			}
		});

		tfSifraDrzave.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				String sifraDrzave = tfSifraDrzave.getText().trim();
				try {
					if (sifraDrzave != "") {
						String naziv = Lookup.getDrzava(sifraDrzave);
						tfNazivDrzave.setText(naziv);
					}
				} catch (SQLException e1) {

				}
			}
		});

		JPanel tfPanel = new JPanel();
		tfPanel.setLayout(new MigLayout("center"));

		tfPanel.add(new JLabel("Sifra drzave"));
		tfPanel.add(tfSifraDrzave, "grow 0, split 3");
		tfPanel.add(btnZoomDrzave, "grow 0");
		tfPanel.add(tfNazivDrzave, "wrap 5");

		tfPanel.add(new JLabel("Sifra mesta"));
		tfPanel.add(tfSifraMesta, "wrap");

		tfPanel.add(new JLabel("Naziv mesta"));
		tfPanel.add(tfNazivMesta, "wrap");

		add(tfPanel);

		btnPanel = new JPanel();
		btnPanel.setLayout(new MigLayout("align right"));
		btnPanel.add(new JButton(new ActionCommit(controller)), "wrap");
		btnPanel.add(new JButton(new ActionCancelAction(controller)));
		add(btnPanel, "cell 3 0");

		populateFieldsArray();
		setFieldsEditable(false);

	}

	public void sync() {
		int index = table.getSelectedRow();
		if (index < 0) {
			tfSifraDrzave.setText("");
			tfSifraMesta.setText("");
			tfNazivMesta.setText("");
			tfNazivDrzave.setText("");
			setFieldsEditable(false);
			return;
		}
		String sifraD = (String) model.getValueAt(index, 0);
		String sifraM = (String) model.getValueAt(index, 1);
		String nazivM = (String) model.getValueAt(index, 2);

		tfSifraDrzave.setText(sifraD);
		tfSifraMesta.setText(sifraM);
		tfNazivMesta.setText(nazivM);
		setFieldsEditable(true);
		childRetVals[0] = sifraD;
		childRetVals[1] = sifraM;
		childRetVals[2] = nazivM;
	}

	@Override
	public void populateFieldsArray() {
		// TODO Auto-generated method stub
		editableFields = new Component[4];
		editableFields[0] = tfSifraDrzave;
		editableFields[1] = tfSifraMesta;
		editableFields[2] = tfNazivMesta;

	}

	@Override
	public void childResponse(tableNames iD2, String[] childRetVals) {
		// TODO Auto-generated method stub
		if (iD2 == tableNames.DRZAVA) {
			tfSifraDrzave.setText(childRetVals[0]);
			tfNazivDrzave.setText(childRetVals[1]);
		}
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
		tfSifraDrzave.setEditable(!b);
		tfNazivMesta.setEditable(b);
		tfSifraMesta.setEditable(b);
		btnZoomDrzave.setEnabled(b);
		tfNazivDrzave.setEditable(false);

	}
	
	public void setZoomButtons(Boolean b){
		btnZoomDrzave.setEnabled(b);
	}
}
