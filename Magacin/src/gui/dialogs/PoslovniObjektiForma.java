package gui.dialogs;

import gui.DocumentLimit;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controllers.FormController;

import model.DataBaseTableModel.tableNames;
import net.miginfocom.swing.MigLayout;
import actions.ActionCancelAction;
import actions.ActionCommit;

public class PoslovniObjektiForma extends DatabaseForma {

	private JTextField tfPib;
	private JTextField tfSifraObjekta;
	private JTextField tfSifraDrzave;
	private JTextField tfSifraMesta;
	private JTextField tfSifraTipa;
	private JTextField tfNazivObjekta;

	private JTextField tfNazivPreduzeca;
	private JTextField tfNazivTipa;
	private JTextField tfNazivMesta;

	private JButton btnZoomMesta;
	private JButton btnZoomPreduzeca;
	private JButton btnZoomTipovi;

	public PoslovniObjektiForma() {
		// TODO Auto-generated constructor stub
		super();
		ID = tableNames.POSLOVNI_OBJEKAT;
		setTitle(ID.toString());
		setSizeAndMove(750, 450);
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

		tfPib = new JTextField(12);
		tfPib.setDocument(new DocumentLimit(12));

		tfSifraObjekta = new JTextField(12);
		tfSifraObjekta.setDocument(new DocumentLimit(12));

		tfSifraMesta = new JTextField(5);
		tfSifraMesta.setDocument(new DocumentLimit(5));

		tfSifraTipa = new JTextField(2);
		tfSifraTipa.setDocument(new DocumentLimit(2));

		tfNazivObjekta = new JTextField(20);
		tfNazivObjekta.setDocument(new DocumentLimit(40));

		tfSifraDrzave = new JTextField(3);
		tfSifraDrzave.setDocument(new DocumentLimit(3));

		tfNazivPreduzeca = new JTextField(30);

		tfNazivTipa = new JTextField(15);

		tfNazivMesta = new JTextField(30);

		initializeTable();
		controller = new FormController(this);
		initializeToolbar();

		add(toolbar, "dock north");
		add(new JScrollPane(table), "dock north");

		btnZoomMesta = new JButton("...");
		btnZoomMesta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MestaForma m = new MestaForma();
				m.setParentDialog(PoslovniObjektiForma.this);
				m.setVisible(true);
			}
		});

		btnZoomPreduzeca = new JButton("...");
		btnZoomPreduzeca.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PreduzecaForma p = new PreduzecaForma();
				p.setParentDialog(PoslovniObjektiForma.this);
				p.setVisible(true);
			}
		});

		btnZoomTipovi = new JButton("...");
		btnZoomTipovi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TipObjektaForma t = new TipObjektaForma();
				t.setParentDialog(PoslovniObjektiForma.this);
				t.setVisible(true);
			}
		});

		JPanel tfPanel = new JPanel();
		tfPanel.setLayout(new MigLayout("center"));

		tfPanel.add(new JLabel("Naziv objekta"));
		tfPanel.add(tfNazivObjekta, "wrap");

		tfPanel.add(new JLabel("Sifra objekta"));
		tfPanel.add(tfSifraObjekta, "wrap");

		tfPanel.add(new JLabel("Pib"));
		tfPanel.add(tfPib, "grow 0, split 3");
		tfPanel.add(btnZoomPreduzeca, "grow 0");
		tfPanel.add(tfNazivPreduzeca, "wrap 5");

		tfPanel.add(new JLabel("Sifra tipa"));
		tfPanel.add(tfSifraTipa, "grow 0, split 3");
		tfPanel.add(btnZoomTipovi, "grow 0");
		tfPanel.add(tfNazivTipa, "wrap 5");

		tfPanel.add(new JLabel("Sifra mesta"));
		tfPanel.add(tfSifraMesta, "grow 0, split 3");
		tfPanel.add(btnZoomMesta, "grow 0");
		tfPanel.add(tfNazivMesta, "wrap 5");

		tfPanel.add(new JLabel("Sifra drzave"));
		tfPanel.add(tfSifraDrzave, "wrap");

		add(tfPanel);

		btnPanel = new JPanel();
		btnPanel.setLayout(new MigLayout("align right"));
		btnPanel.add(new JButton(new ActionCommit(controller)), "wrap");
		btnPanel.add(new JButton(new ActionCancelAction(controller)));
		add(btnPanel);
	}

	public void sync() {
		// TODO Auto-generated method stub
		int index = table.getSelectedRow();
		if (index < 0) {
			tfPib.setText("");
			tfSifraObjekta.setText("");
			tfSifraDrzave.setText("");
			tfSifraMesta.setText("");
			tfSifraTipa.setText("");
			tfNazivObjekta.setText("");

			tfNazivMesta.setText("");
			tfNazivPreduzeca.setText("");
			tfNazivTipa.setText("");
			setFieldsEditable(false);
			return;
		}
		String pib = (String) model.getValueAt(index, 0);
		String sifraObjekta = (String) model.getValueAt(index, 1);
		String sifraDrzave = (String) model.getValueAt(index, 2);
		String sifraMesta = (String) model.getValueAt(index, 3);
		String sifraTipa = (String) model.getValueAt(index, 4);
		String nazivObjekta = (String) model.getValueAt(index, 5);
		tfPib.setText(pib);
		tfSifraObjekta.setText(sifraObjekta);
		tfSifraDrzave.setText(sifraDrzave);
		tfSifraMesta.setText(sifraMesta);
		tfSifraTipa.setText(sifraTipa);
		tfNazivObjekta.setText(nazivObjekta);
		setFieldsEditable(true);
		childRetVals[0] = sifraObjekta;
		childRetVals[1] = nazivObjekta;
		childRetVals[2] = pib;
	}

	@Override
	public void populateFieldsArray() {
		// TODO Auto-generated method stub
		editableFields = new Component[6];
		editableFields[0] = tfPib;
		editableFields[1] = tfSifraObjekta;
		editableFields[2] = tfSifraDrzave;
		editableFields[3] = tfSifraMesta;
		editableFields[4] = tfSifraTipa;
		editableFields[5] = tfNazivObjekta;
	}

	@Override
	public void childResponse(tableNames iD2, String[] childRetVals) {
		// TODO Auto-generated method stub
		switch (iD2) {
		case TIP_OBJEKTA:
			tfSifraTipa.setText(childRetVals[0]);
			tfNazivTipa.setText(childRetVals[1]);
			break;
		case NASELJENO_MESTO:
			tfSifraDrzave.setText(childRetVals[0]);
			tfSifraMesta.setText(childRetVals[1]);
			tfNazivMesta.setText(childRetVals[2]);
			break;
		case PREDUZECE:
			tfPib.setText(childRetVals[0]);
			tfNazivPreduzeca.setText(childRetVals[1]);
			break;
		default:
			break;
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

	public void setTfToFill(JTextField tfSiftaMagacinaMMPromet) {
		// TODO Auto-generated method stub
		parrentsTextField = tfSiftaMagacinaMMPromet;
	}

	@Override
	public void setFieldsEditable(boolean b) {
		super.setFieldsEditable(b);
		
		tfPib.setEditable(false);
		tfNazivMesta.setEditable(false);
		tfNazivPreduzeca.setEditable(false);
		tfNazivTipa.setEditable(false);
		
		btnZoomMesta.setEnabled(b);
		btnZoomPreduzeca.setEnabled(b);
		btnZoomTipovi.setEnabled(b);
	}

	@Override
	public void setZoomButtons(Boolean b) {
		// TODO Auto-generated method stub
		btnZoomMesta.setEnabled(b);
		btnZoomPreduzeca.setEnabled(b);
		btnZoomTipovi.setEnabled(b);
	}


}
