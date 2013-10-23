package gui.dialogs;

import gui.DatePickerComponent;
import gui.DocumentLimit;
import gui.DocumentNumericLimited;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import controllers.DialogController;

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

import model.DataBaseTableModel.tableNames;

public class PrometniDokumentDialog extends DatabaseDialog {

	private JTextField tfBrojPrometnogDokumenta;
	private JTextField tfPib;
	private JTextField tfGodina;
	private JTextField tfSifraMagacina;
	private JTextField tfSiftaMagacinaMMPromet;
	private JTextField tfPibPoslovnogPartnera;
	private DatePickerComponent tfDatumNastanka;
	private DatePickerComponent tfDatumKnjizenja;
	private JComboBox cbStatus;
	private JComboBox cbVrstaDokumenta;

	private JTextField tfNazivMagacina;
	private JTextField tfNazivPoslovnogPartnera;

	private JButton btnZoomPoslovniObjekat;
	private JButton btnZoomPoslovniPartner;
	private JButton btnZoomPoslovnuGodinu;
	private JButton btnZoomMagacini;

	private JButton btnProknjizi;
	private JButton btnStorniraj;

	private JButton btnStavkePrometnogDokumenta;

	public PrometniDokumentDialog() {
		// TODO Auto-generated constructor stub
		super();
		ID = tableNames.PROMETNI_DOKUMENT;
		setTitle(ID.toString());
		setSizeAndMove(1050, 600);
		initializeComponents();
		populateFieldsArray();
		initializeStatusBar();
		setFieldsEditable(false);
	}

	@Override
	protected void initializeComponents() {
		// TODO Auto-generated method stub 
		setLayout(new MigLayout("", "[align r][align l, grow, fill]", ""));
		tfBrojPrometnogDokumenta = new JTextField(6);
		tfBrojPrometnogDokumenta.setDocument(new DocumentNumericLimited(5));

		tfPib = new JTextField(12);
		tfPib.setDocument(new DocumentLimit(12));

		tfGodina = new JTextField(12);
		tfGodina.setDocument(new DocumentNumericLimited(4));
		
		tfSifraMagacina = new JTextField(12);
		tfSifraMagacina.setDocument(new DocumentLimit(12));
		
		tfSiftaMagacinaMMPromet = new JTextField(12);
		tfSiftaMagacinaMMPromet.setDocument(new DocumentLimit(12));
		
		tfPibPoslovnogPartnera = new JTextField(12);
		tfPibPoslovnogPartnera.setDocument(new DocumentLimit(12));
		
		tfDatumNastanka = new DatePickerComponent();
		
		tfDatumKnjizenja = new DatePickerComponent();
		
		cbStatus = new JComboBox<String>();
		cbStatus.addItem("U fazi formiranja");
		cbStatus.addItem("Proknjizen");
		cbStatus.addItem("Storniran");
		
		cbVrstaDokumenta = new JComboBox<String>();
		cbVrstaDokumenta.addItem("primka");
		cbVrstaDokumenta.addItem("otpremnica");
		cbVrstaDokumenta.addItem("medjumagacinski promet");
		;

		tfNazivMagacina = new JTextField(20);
		tfNazivPoslovnogPartnera = new JTextField(20);

		initializeTable();
		controller = new DialogController(this);
		initializeToolbar();

		add(toolbar, "dock north");
		add(new JScrollPane(table), "dock north");

		JPanel tableBtnPanel = new JPanel();				//ZAPOSLENI, POSLOVNI OBJEKTI
		tableBtnPanel.setLayout(new MigLayout());
		btnStavkePrometnogDokumenta = new JButton("Stavke prometnog dokumenta");
		btnStavkePrometnogDokumenta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StavkePrometnogDokumentaDialog d = new StavkePrometnogDokumentaDialog();
				d.setParentDialog(PrometniDokumentDialog.this);
				d.setKeyFilter(new String[] {"PIB", tfPib.getText(), "GODINA", tfGodina.getText(), "BROJ_PROMETNOG_DOKUMENTA",
						tfBrojPrometnogDokumenta.getText()});
//				d.getTfBrojPrometnogDokumenta().setText(tfBrojPrometnogDokumenta.getText());
//				d.getTfPib().setText(tfPib.getText());
				d.setVisible(true);
			}
		});
		populateStatusBasedComponents();
		setEnabledStatusBasedComponents(false);
		tableBtnPanel.add(btnStavkePrometnogDokumenta, "width :130:");
		add(tableBtnPanel, "align left, wrap");

		btnZoomPoslovniObjekat = new JButton("...");
		btnZoomPoslovniObjekat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PoslovniObjektiDialog p = new PoslovniObjektiDialog();
				p.setParentDialog(PrometniDokumentDialog.this);
				p.setKeyFilter(new String[] {"PIB", tfPib.getText()});
				p.setVisible(true);
			}
		});

		btnZoomPoslovniPartner = new JButton("...");
		btnZoomPoslovniPartner.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PoslovniPartnerDialog p = new PoslovniPartnerDialog();
				p.setParentDialog(PrometniDokumentDialog.this);
				p.setKeyFilter(new String[] {"PIB", tfPib.getText()});
				p.setVisible(true);
			}
		});

		btnZoomPoslovnuGodinu = new JButton("...");
		btnZoomPoslovnuGodinu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				PoslovnaGodinaDialog p = new PoslovnaGodinaDialog();
				p.setParentDialog(PrometniDokumentDialog.this);
				p.setKeyFilter(new String[] {"ZAKLJUCENA", "False", "PIB", tfPib.getText()});
				p.setVisible(true);
			}
		});

		btnZoomMagacini = new JButton("...");
		btnZoomMagacini.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				PoslovniObjektiDialog p = new PoslovniObjektiDialog();
				p.setParentDialog(PrometniDokumentDialog.this);
				p.setKeyFilter(new String[] {"PIB", tfPib.getText()});
				p.setVisible(true);
				p.setTfToFill(tfSiftaMagacinaMMPromet);
			}
		});

		cbVrstaDokumenta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				switch (((String)cbVrstaDokumenta.getSelectedItem()).trim()) {
				case "primka":
					btnZoomPoslovniPartner.setEnabled(true);
					//tfNazivPoslovnogPartnera.setEditable(true);
					btnZoomMagacini.setEnabled(false);
					break;
				case "otpremnica":
					btnZoomPoslovniPartner.setEnabled(true);
					//tfNazivPoslovnogPartnera.setEditable(true);
					btnZoomMagacini.setEnabled(false);
					break;
				case "medjumagacinski promet":
					btnZoomPoslovniPartner.setEnabled(false);
					//tfNazivPoslovnogPartnera.setEditable(false);
					btnZoomMagacini.setEnabled(true);
					break;
				default:
					break;
				}
			}
		});
		
		cbStatus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				switch (((String)cbStatus.getSelectedItem()).trim()) {
				case "U fazi formiranja":
					setFieldsEditable(true);
					btnProknjizi.setEnabled(true);
					btnStorniraj.setEnabled(false);
					break;
				case "Proknjizen":
					setFieldsEditable(false);
					btnProknjizi.setEnabled(false);
					btnStorniraj.setEnabled(true);
					break;
				case "Storniran":
					setFieldsEditable(false);
					btnProknjizi.setEnabled(false);
					btnStorniraj.setEnabled(false);
					break;
				default:
					break;
				}
			}
		});

		JPanel tfPanel = new JPanel();
		tfPanel.setLayout(new MigLayout("center"));

		tfPanel.add(new JLabel("Vrsta dokumenta"));
		tfPanel.add(cbVrstaDokumenta);

		tfPanel.add(new JLabel("Pib"));
		tfPanel.add(tfPib, "wrap");

		tfPanel.add(new JLabel("Godina"));
		tfPanel.add(tfGodina, "grow 0, split 2");
		tfPanel.add(btnZoomPoslovnuGodinu, "grow 0");

		tfPanel.add(new JLabel("Sifra prometnog dokumenta"));
		tfPanel.add(tfBrojPrometnogDokumenta,"wrap");

		tfPanel.add(new JLabel("Sifra poslovnog objekta"));
		tfPanel.add(tfSifraMagacina, "grow 0, split 3");
		tfPanel.add(btnZoomPoslovniObjekat, "grow 0");
		tfPanel.add(tfNazivMagacina);

		tfPanel.add(new JLabel("Sifra magacina s kojim se posluje"));
		tfPanel.add(tfSiftaMagacinaMMPromet, "grow 0, split 3");
		tfPanel.add(btnZoomMagacini, "grow 0, wrap");

		tfPanel.add(new JLabel("Pib poslovnog partnera"));
		tfPanel.add(tfPibPoslovnogPartnera, "grow 0, split 3");
		tfPanel.add(btnZoomPoslovniPartner, "grow 0");
		tfPanel.add(tfNazivPoslovnogPartnera);

		tfPanel.add(new JLabel("Datum nastanka (yyyy-mm-dd)"));
		tfPanel.add(tfDatumNastanka, "wrap");

		tfPanel.add(new JLabel("Datum knjizenja (yyyy-mm-dd)"));
		tfPanel.add(tfDatumKnjizenja);

		tfPanel.add(new JLabel("Status dokumenta"));
		tfPanel.add(cbStatus, "wrap");

		add(tfPanel);

		btnPanel = new JPanel();
		btnPanel.setLayout(new MigLayout("align right"));
		btnPanel.add(new JButton(new ActionCommit(controller)), "wrap");
		btnPanel.add(new JButton(new ActionCancelAction(controller)));
		add(btnPanel, "wrap");

		btnProknjizi = new JButton("Proknjizi dokument");
		btnProknjizi.setIcon(new ImageIcon("images/finish.gif"));
		btnProknjizi.setEnabled(false);
		btnProknjizi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				controller.proknjiziDokument(new String[] {"PIB", tfPib.getText(), "GODINA", tfGodina.getText(),
						"BROJ_PROMETNOG_DOKUMENTA", tfBrojPrometnogDokumenta.getText()});
			}
		});
		add(btnProknjizi, "width :130:, align left, split 2");

		btnStorniraj = new JButton("Storniraj dokument");
		btnStorniraj.setIcon(new ImageIcon("images/quit.gif"));
		btnStorniraj.setEnabled(false);
		btnStorniraj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controller.stornirajDokument(new String[] {"PIB", tfPib.getText(), "GODINA", tfGodina.getText(),
						"BROJ_PROMETNOG_DOKUMENTA", tfBrojPrometnogDokumenta.getText()});
			}
		});
		add(btnStorniraj, "width :130:, align left, wrap");
	}

	@Override
	public void populateFieldsArray() {
		// TODO Auto-generated method stub
		editableFields = new Component[10];
		editableFields[0] = tfPib;
		editableFields[1] = tfGodina;
		editableFields[2] = tfBrojPrometnogDokumenta;
		editableFields[3] = tfSifraMagacina;
		editableFields[4] = tfSiftaMagacinaMMPromet;
		editableFields[5] = tfPibPoslovnogPartnera;
		editableFields[6] = cbVrstaDokumenta;
		editableFields[7] = tfDatumNastanka;
		editableFields[8] = tfDatumKnjizenja;
		editableFields[9] = cbStatus;
	}

	@Override
	public void childResponse(tableNames iD2, String[] childRetVals) {
		// TODO Auto-generated method stub
		if (iD2==tableNames.POSLOVNI_OBJEKAT) {
			tfSifraMagacina.setText(childRetVals[0]);
			tfNazivMagacina.setText(childRetVals[1]);
			tfPib.setText(childRetVals[2]);
		}
		if (iD2==tableNames.POSLOVNA_GODINA) {
			tfGodina.setText(childRetVals[0]);
			tfPib.setText(childRetVals[1]);
		}
		if (iD2==tableNames.POSLOVNI_PARTNER) {
			tfPibPoslovnogPartnera.setText(childRetVals[0]);
			tfNazivPoslovnogPartnera.setText(childRetVals[1]);
			tfPib.setText(childRetVals[2]);
		}
	}

	@Override
	protected void sync() {
		// TODO Auto-generated method stub
		int index = table.getSelectedRow();
		if (index < 0) {
			setFieldsEditable(false);
			tfNazivMagacina.setText("");
			tfNazivPoslovnogPartnera.setText("");
			setEnabledStatusBasedComponents(false);
			setFieldsEditable(false);
			return;
		}
		String pib = (String)model.getValueAt(index, 0);
		String godina = (String)model.getValueAt(index, 1);
		String brojPrometnogDokumenta = (String)model.getValueAt(index, 2);
		String sifraObjekta = (String)model.getValueAt(index, 3);
		String sifraObjektaMM = (String)model.getValueAt(index, 4);
		String pibPP = (String)model.getValueAt(index, 5);
		String vrstaDokumenta = (String)model.getValueAt(index, 6);
		String datumNastanka = (String)model.getValueAt(index, 7);
		String datumKnjizenja = (String)model.getValueAt(index, 8);
		String status = (String)model.getValueAt(index, 9);
		tfPib.setText(pib);
		tfBrojPrometnogDokumenta.setText(brojPrometnogDokumenta);
		tfGodina.setText(godina);
		tfSifraMagacina.setText(sifraObjekta);
		tfSiftaMagacinaMMPromet.setText(sifraObjektaMM);
		tfPibPoslovnogPartnera.setText(pibPP);
		tfDatumNastanka.setText(datumNastanka);
		tfDatumKnjizenja.setText(datumKnjizenja);
		
		switch (vrstaDokumenta.trim().toUpperCase()) {
		case "PR":
			cbVrstaDokumenta.setSelectedItem("primka");
			break;
		case "OT":
			cbVrstaDokumenta.setSelectedItem("otpremnica");
			break;
		case "MM":
			cbVrstaDokumenta.setSelectedItem("medjumagacinski promet");
			break;
		default:
			break;
		}
		
		switch (status.trim().toUpperCase()) {
		case "F":
			cbStatus.setSelectedItem("U fazi formiranja");
			break;
		case "P":
			cbStatus.setSelectedItem("Proknjizen");
			setFieldsEditable(false);
			break;
		case "S":
			cbStatus.setSelectedItem("Storniran");
			setFieldsEditable(false);
			break;
		default:
			break;
		}
		childRetVals[0] = brojPrometnogDokumenta;
		childRetVals[1] = pib;
		setEnabledStatusBasedComponents(true);
		setFieldsEditable(true);
	}

	@Override
	public void populateStatusBasedComponents() {
		// TODO Auto-generated method stub
		statusBasedButtons = new Component[1];
		statusBasedButtons[0] = btnStavkePrometnogDokumenta;
	}

	@Override
	public void initializePrimaryKeysNumbers() {
		// TODO Auto-generated method stub
		primaryKeysColumnNumber = new int[1];
		primaryKeysColumnNumber[0] = 2;
	}

	@Override
	public String comboBoxHandler(JComboBox cb) {
		// TODO Auto-generated method stub
		if (cb.getSelectedItem().equals("primka"))
			return "pr";
		else if (cb.getSelectedItem().equals("otpremnica"))
			return "ot";
		else if (cb.getSelectedItem().equals("medjumagacinski promet"))
			return "mm";
		else if (cb.getSelectedItem().equals("U fazi formiranja"))
			return "f";
		else if (cb.getSelectedItem().equals("Proknjizen"))
			return "p";
		else if (cb.getSelectedItem().equals("Storniran"))
			return "s";
		return null;
	}
	
	@Override
	public void setFieldsEditable(boolean b) {
		// TODO Auto-generated method stub
		super.setFieldsEditable(b);
		tfDatumKnjizenja.setEditable(false);
		tfPib.setEditable(false);
		tfGodina.setEditable(false);
		tfSifraMagacina.setEditable(false);
		tfPibPoslovnogPartnera.setEditable(false);
		tfSiftaMagacinaMMPromet.setEditable(false);
		tfNazivMagacina.setEditable(false);
		tfNazivPoslovnogPartnera.setEditable(false);
		
		btnZoomPoslovniObjekat.setEnabled(b);
		btnZoomPoslovniPartner.setEnabled(b);
		btnZoomPoslovnuGodinu.setEnabled(b);
		btnZoomMagacini.setEnabled(b);
		
		btnProknjizi.setEnabled(b);
		btnStorniraj.setEnabled(b);
		
		if (b) {
			cbVrstaDokumenta.setSelectedIndex(cbVrstaDokumenta.getSelectedIndex());	//refres combo boxova
			cbStatus.setSelectedIndex(cbStatus.getSelectedIndex());
		}
	}
}
