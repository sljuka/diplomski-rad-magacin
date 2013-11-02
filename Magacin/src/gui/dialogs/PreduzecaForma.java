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


public class PreduzecaForma extends DatabaseForma {

	private JTextField tfPib;
	private JTextField tfSifraDrzave;
	private JTextField tfSifraMesta;
	private JTextField tfNazivPreduzeca;
	
	private JTextField tfNazivMesta;	//ovi nisu u bazi, vazne su samo sifre.
	private JTextField tfNazivDrzave;
	
	private JButton btnZoomMesta;
	
	private JButton btnZaposleni;
	private JButton btnPoslovniObjekti;
	private JButton btnPoslovniPartneri;
	private JButton btnArtikli;
	
	public PreduzecaForma() {
		// TODO Auto-generated constructor stub
		super();
		ID = tableNames.PREDUZECE;
		setTitle(ID.toString());
		setSizeAndMove(1000, 600);
		initializeComponents();
		populateFieldsArray();
		setFieldsEditable(false);
		initializeStatusBar();
		initializePrimaryKeysNumbers();
		model.setPrimaryKeysNumbers(primaryKeysColumnNumber);
	}

	
	@Override
	protected void initializeComponents() {
		// TODO Auto-generated method stub
		setLayout(new MigLayout("", "[align r][align l, grow, fill]", ""));
		
		tfPib = new JTextField(12);
		tfPib.setDocument(new DocumentLimit(12));
		
		tfSifraDrzave = new JTextField(3);
		tfSifraDrzave.setDocument(new DocumentLimit(3));
		
		tfNazivDrzave = new JTextField(30);
		
		tfSifraMesta = new JTextField(5);
		tfSifraMesta.setDocument(new DocumentLimit(5));
		
		tfNazivMesta = new JTextField(30);
		
		tfNazivPreduzeca = new JTextField(40);
		tfNazivPreduzeca.setDocument(new DocumentLimit(40));
		
		initializeTable();
		controller = new FormController(this);
		initializeToolbar();
		
		add(toolbar, "dock north");
		add(new JScrollPane(table), "dock north");
		
		initializeStatusBasedComponents();
		setEnabledStatusBasedComponents(false);
		
		JPanel tableBtnPanel = new JPanel();				//ZAPOSLENI, POSLOVNI OBJEKTI
		tableBtnPanel.setLayout(new MigLayout());
		tableBtnPanel.add(btnZaposleni, "width :130:");
		tableBtnPanel.add(btnPoslovniObjekti, "width :130:");
		tableBtnPanel.add(btnArtikli, "width :130:");
		tableBtnPanel.add(btnPoslovniPartneri, "width :130:");
		add(tableBtnPanel, "align left, wrap");
		
		
		btnZoomMesta = new JButton("...");
		btnZoomMesta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MestaForma m = new MestaForma();
				m.setParentDialog(PreduzecaForma.this);
				m.setVisible(true);
			}
		});
		
		JPanel tfPanel = new JPanel();
		tfPanel.setLayout(new MigLayout("center"));
		
		tfPanel.add(new JLabel("Naziv preduzeca"));
		tfPanel.add(tfNazivPreduzeca, "wrap");
		
		tfPanel.add(new JLabel("Pib"));
		tfPanel.add(tfPib, "wrap");
		
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
	
	private void initializeStatusBasedComponents() {
		// TODO Auto-generated method stub
		btnZaposleni = new JButton("Zaposleni");
		btnZaposleni.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ZaposleniForma z = new ZaposleniForma();
				z.setKeyFilter(new String[] {"PIB", tfPib.getText()});
				z.setVisible(true);
			}
		});
		btnPoslovniObjekti = new JButton("Poslovni objekti");
		btnPoslovniObjekti.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				PoslovniObjektiForma p = new PoslovniObjektiForma();
				p.setKeyFilter(new String[] {"PIB", tfPib.getText()});
				p.setVisible(true);
			}
		});
		btnArtikli = new JButton("Artikli");
		btnArtikli.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ArtikliForma a = new ArtikliForma();
				a.setKeyFilter(new String[] {"PIB", tfPib.getText()}); //OVA DVA STRINGA CE CINITI WHERE CLAUSE.
				a.setVisible(true);
			}
		});
		btnPoslovniPartneri = new JButton("Poslovni partneri");
		btnPoslovniPartneri.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PoslovniPartnerForma p = new PoslovniPartnerForma();
				p.setKeyFilter(new String[] {"PIB", tfPib.getText()});
				p.setVisible(true);
			}
		});
		populateStatusBasedComponents();
	}

	public void sync() {
		// TODO Auto-generated method stub
		int index = table.getSelectedRow();
		if (index < 0) {
			tfPib.setText("");
			tfSifraDrzave.setText("");
			tfSifraMesta.setText("");
			tfNazivPreduzeca.setText("");
			
			tfNazivMesta.setText("");
			setEnabledStatusBasedComponents(false);
			setFieldsEditable(false);
			return;
		}
	
		String pib = (String)model.getValueAt(index, 0);
		String sifraDrzave = (String)model.getValueAt(index, 1);
		String sifraMesta = (String)model.getValueAt(index, 2);
		String nazivPreduzeca = (String)model.getValueAt(index, 3);
		tfPib.setText(pib);
		tfSifraDrzave.setText(sifraDrzave);
		tfSifraMesta.setText(sifraMesta);
		tfNazivPreduzeca.setText(nazivPreduzeca);
		tfNazivMesta.setText("");
		childRetVals[0] = pib;
		childRetVals[1] = nazivPreduzeca;
		
		setEnabledStatusBasedComponents(true);
		setFieldsEditable(true);
	}

	@Override
	public void populateFieldsArray() {
		// TODO Auto-generated method stub
		editableFields = new Component[4];
		editableFields[0] = tfPib;
		editableFields[1] = tfSifraDrzave;
		editableFields[2] = tfSifraMesta;
		editableFields[3] = tfNazivPreduzeca;
	}

	@Override
	public void childResponse(tableNames iD2, String[] childRetVals) {
		// TODO Auto-generated method stub
		if (iD2 == tableNames.NASELJENO_MESTO) {
			tfSifraDrzave.setText(childRetVals[0]);
			tfSifraMesta.setText(childRetVals[1]);
			tfNazivMesta.setText(childRetVals[2]);
		}
	}

	@Override
	public void populateStatusBasedComponents() {
		// TODO Auto-generated method stub
		statusBasedButtons = new Component[4];
		statusBasedButtons[0] = btnZaposleni;
		statusBasedButtons[1] = btnPoslovniObjekti;
		statusBasedButtons[2] = btnPoslovniPartneri;
		statusBasedButtons[3] = btnArtikli;
	}

	@Override
	public void initializePrimaryKeysNumbers() {
		// TODO Auto-generated method stub
		primaryKeysColumnNumber = new int[1];
		primaryKeysColumnNumber[0] = 0;
	}
	
	@Override
	public void setFieldsEditable(boolean b) {
		// TODO Auto-generated method stub
		super.setFieldsEditable(b);
		tfNazivMesta.setEditable(false);
		tfNazivDrzave.setEditable(false);
		tfSifraMesta.setEditable(false);
		btnZoomMesta.setEnabled(b);
	}
}
