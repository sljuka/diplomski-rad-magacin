package gui.dialogs;

import gui.DocumentNumericLimited;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controllers.FormController;

import net.miginfocom.swing.MigLayout;
import numeric.textField.NumericTextField;
import actions.ActionCancelAction;
import actions.ActionCommit;

import model.DataBaseTableModel.tableNames;

public class StavkePrometnogDokumentaForma extends DatabaseForma {

	private JTextField tfPib;
	private JTextField tfGodina;
	private JTextField tfBrojPrometnogDokumenta;
	private JTextField tfSifraArtikla;
	private JTextField tfRbr;
	private NumericTextField tfKolicina;
	private NumericTextField tfCena;
	private NumericTextField tfVrednost;
	
	private JTextField tfNazivArtikla;
	
	private JButton btnZoomArtikli;
	private JButton btnZoomDokumenti;
	private JButton btnZoomGodine;
	
	public StavkePrometnogDokumentaForma() {
		// TODO Auto-generated constructor stub
		super();
		ID = tableNames.STAVKA_PROMETNOG_DOKUMENTA;
		setTitle(ID.toString());
		setSizeAndMove(1000, 600);
		initializeComponents();
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
		tfGodina = new JTextField(12);
		tfBrojPrometnogDokumenta = new JTextField(5);
		tfSifraArtikla = new JTextField(12);
		tfRbr = new JTextField(5);
		tfRbr.setDocument(new DocumentNumericLimited(5));
		tfKolicina = new NumericTextField(12, new DecimalFormat("0000.00"));
		tfKolicina.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (tfKolicina.getText().equals("") || tfCena.getText().equals(""))
					return;
				double cena = Double.parseDouble(tfCena.getText());
				double kolicina = Double.parseDouble(tfKolicina.getText());
				tfVrednost.setText(cena*kolicina+"");
			}
		});
		tfCena =  new NumericTextField(12, new DecimalFormat("##############.##"));
		tfCena.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (tfKolicina.getText().equals("") || tfCena.getText().equals(""))
					return;
				double cena = Double.parseDouble(tfCena.getText());
				DecimalFormat dfVrednost = new DecimalFormat("#.##");	
				double kolicina = Double.parseDouble(tfKolicina.getText());
				tfVrednost.setText(dfVrednost.format(cena*kolicina));
			}
		});
		tfVrednost =new NumericTextField(12, new DecimalFormat("0000.00"));
		tfVrednost.setEditable(false);
		
		tfNazivArtikla = new JTextField(20);
		
		initializeTable();
		controller = new FormController(this);
		initializeToolbar();
		
		add(toolbar, "dock north");
		add(new JScrollPane(table), "dock north");
		
		btnZoomArtikli = new JButton("...");
		btnZoomArtikli.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArtikliForma a = new ArtikliForma();
				a.setParentDialog(StavkePrometnogDokumentaForma.this);
				a.setKeyFilter(new String[] {"PIB", tfPib.getText()});
				a.setVisible(true);
			}
		});
		btnZoomDokumenti = new JButton("...");
		btnZoomDokumenti.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PrometniDokumentForma a = new PrometniDokumentForma();
				a.setParentDialog(StavkePrometnogDokumentaForma.this);
				a.setKeyFilter(new String[] {"PIB", tfPib.getText()});
				a.setVisible(true);
			}
		});
		btnZoomGodine = new JButton("...");
		btnZoomGodine.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PoslovnaGodinaForma p = new PoslovnaGodinaForma();
				p.setKeyFilter(new String[] {"PIB", tfPib.getText()});
				p.setParentDialog(StavkePrometnogDokumentaForma.this);
				p.setVisible(true);
			}
		});
		
		JPanel tfPanel = new JPanel();
		tfPanel.setLayout(new MigLayout("center"));
		
		tfPanel.add(new JLabel("Broj prometnog dokumenta"));
		tfPanel.add(tfBrojPrometnogDokumenta, "grow 0, split 3");
		tfPanel.add(btnZoomDokumenti, "grow 0, wrap");
		
		tfPanel.add(new JLabel("Godina"));
		tfPanel.add(tfGodina, "grow 0, split 3");
		tfPanel.add(btnZoomGodine, "grow 0, wrap");
		
		tfPanel.add(new JLabel("Artikal"));
		tfPanel.add(tfSifraArtikla, "grow 0, split 3");
		tfPanel.add(btnZoomArtikli, "grow 0");
		tfPanel.add(tfNazivArtikla, "wrap");
		
		tfPanel.add(new JLabel("Pib"));
		tfPanel.add(tfPib, "wrap");
		
		tfPanel.add(new JLabel("Redni broj stavke"));
		tfPanel.add(tfRbr, "wrap");
		
		tfPanel.add(new JLabel("Kolicina"));
		tfPanel.add(tfKolicina, "wrap");
		
		tfPanel.add(new JLabel("Cena"));
		tfPanel.add(tfCena, "wrap");
		
		tfPanel.add(new JLabel("Vrednost"));
		tfPanel.add(tfVrednost, "wrap");
		
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
		editableFields = new Component[8];
		editableFields[0] = tfPib;
		editableFields[1] = tfGodina;
		editableFields[2] = tfBrojPrometnogDokumenta;
		editableFields[3] = tfSifraArtikla;
		editableFields[4] = tfRbr;
		editableFields[5] = tfKolicina;
		editableFields[6] = tfCena;
		editableFields[7] = tfVrednost;
	}

	@Override
	public void childResponse(tableNames iD2, String[] childRetVals) {
		// TODO Auto-generated method stub
		if (iD2 == tableNames.ARTIKAL) {
			tfSifraArtikla.setText(childRetVals[0]);
			tfNazivArtikla.setText(childRetVals[1]);
			tfPib.setText(childRetVals[2]);
		}
		if (iD2 == tableNames.PROMETNI_DOKUMENT) {
			tfBrojPrometnogDokumenta.setText(childRetVals[0]);
			tfPib.setText(childRetVals[1]);
		}
		if (iD2==tableNames.POSLOVNA_GODINA) {
			tfGodina.setText(childRetVals[0]);
			tfPib.setText(childRetVals[1]);
		}
	}

	@Override
	protected void sync() {
		// TODO Auto-generated method stub
		int index = table.getSelectedRow();
		if (index < 0) {
			for (Component c : editableFields) {
				if (c instanceof JTextField)
					((JTextField)c).setText("");
			}
			tfNazivArtikla.setText("");
			return;
		}
		String pib = (String)model.getValueAt(index, 0);
		String godina = (String)model.getValueAt(index, 1);
		String brojPrometnogDokumenta = (String)model.getValueAt(index, 2);
		String sifraArtikla = (String)model.getValueAt(index, 3);
		String kolicina = (String)model.getValueAt(index, 3);
		String cena = (String)model.getValueAt(index, 3);
		String vrednost = (String)model.getValueAt(index, 3);
		
		tfPib.setText(pib);
		tfGodina.setText(godina);
		tfBrojPrometnogDokumenta.setText(brojPrometnogDokumenta);
		tfSifraArtikla.setText(sifraArtikla);
		tfKolicina.setText(kolicina);
		tfCena.setText(cena);
		tfVrednost.setText(vrednost);
		
		childRetVals[0] = pib;
		childRetVals[1] = godina;
		childRetVals[2] = brojPrometnogDokumenta;
	}

	@Override
	public void populateStatusBasedComponents() {
		// TODO Auto-generated method stub
		statusBasedButtons = new Component[0];
	}

	@Override
	public void initializePrimaryKeysNumbers() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setFieldsEditable(boolean b) {
		// TODO Auto-generated method stub
		super.setFieldsEditable(b);
		tfBrojPrometnogDokumenta.setEditable(false);
		tfSifraArtikla.setEditable(false);
		tfPib.setEditable(false);
		tfGodina.setEditable(false);
		tfNazivArtikla.setEditable(false);
		tfVrednost.setEditable(false);
		btnZoomArtikli.setEnabled(b);
		btnZoomDokumenti.setEnabled(b);
		btnZoomGodine.setEnabled(b);
	}

	public JTextField getTfPib() {
		return tfPib;
	}

	public JTextField getTfBrojPrometnogDokumenta() {
		return tfBrojPrometnogDokumenta;
	}
	
	

}
