package gui.dialogs;

import gui.DocumentLimit;
import gui.IInput;
import gui.TextInput;
import gui.ZoomInput;

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

import controllers.FormController;

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

public class MestaForma extends DatabaseForma {

	private IInput zSifraDrzave;
	private IInput tfSifraMesta;
	private IInput tfNazivMesta;
	
	public MestaForma(FormController controller) {
		// TODO Auto-generated constructor stub
		super(controller, tableNames.NASELJENO_MESTO, 600, 300);
	}

	@Override
	protected void initializeInputFields(FormController controller) {
		
		setLayout(new MigLayout("", "[align r][align l, grow, fill]", ""));

		zSifraDrzave = new ZoomInput(this, tableNames.DRZAVA, "Sifra drzave", 3, 30);

		tfSifraMesta = new TextInput(3, "Sifra mesta", new DocumentLimit(5));
		tfNazivMesta = new TextInput(30, "Naziv mesta", null);
		
		inputPanel = new JPanel();
		inputPanel.setLayout(new MigLayout("center"));

		inputPanel.add(zSifraDrzave.getComponent(), "wrap");

		inputPanel.add(tfSifraMesta.getComponent(), "wrap");

		inputPanel.add(tfNazivMesta.getComponent(), "wrap");
		
	}
	
	public void sync() {
		int index = table.getSelectedRow();
		if (index < 0) {
			for (IInput input : inputsArray) {
				input.setText("");
			}
		}
		String sifraD = (String) model.getValueAt(index, 0);
		String sifraM = (String) model.getValueAt(index, 1);
		String nazivM = (String) model.getValueAt(index, 2);

		zSifraDrzave.setText(sifraD);
		tfSifraMesta.setText(sifraM);
		tfNazivMesta.setText(nazivM);
		setFieldsEditable(true);
		childRetVals[0] = sifraD;
		childRetVals[1] = sifraM;
		childRetVals[2] = nazivM;
	}

	@Override
	public void populateInputsAndRequiredArray() {
		// TODO Auto-generated method stub
		inputsArray = new IInput[3];
		inputsArray[0] = zSifraDrzave;
		inputsArray[1] = tfSifraMesta;
		inputsArray[2] = tfNazivMesta;
		
		requiredFields = new int[3];
		requiredFields[0] = 0;
		requiredFields[1] = 1;
		requiredFields[2] = 2;

	}

	@Override
	public void childResponse(tableNames iD2, String[] childRetVals) {
		// TODO Auto-generated method stub
		if (iD2 == tableNames.DRZAVA) {
			zSifraDrzave.setText(childRetVals[0]);
			((ZoomInput)zSifraDrzave).setNaziv(childRetVals[1]);
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

}
