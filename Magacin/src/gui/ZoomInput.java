package gui;

import gui.dialogs.AnalitikaMagacinskeKarticeForma;
import gui.dialogs.ArtikliForma;
import gui.dialogs.ClanPopisneKomisijeForma;
import gui.dialogs.DatabaseForma;
import gui.dialogs.DrzaveForma;
import gui.dialogs.MagacinskeKarticeForma;
import gui.dialogs.MernaJedinicaForma;
import gui.dialogs.MestaForma;
import gui.dialogs.PopisniDokumentForma;
import gui.dialogs.PoslovnaGodinaForma;
import gui.dialogs.PoslovniObjektiForma;
import gui.dialogs.PoslovniPartnerForma;
import gui.dialogs.PreduzecaForma;
import gui.dialogs.PrometniDokumentForma;
import gui.dialogs.StavkePopisaForma;
import gui.dialogs.StavkePrometnogDokumentaForma;
import gui.dialogs.TipObjektaForma;
import gui.dialogs.ZaposleniForma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.im.InputContext;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

import controllers.FormController;

import net.miginfocom.swing.MigLayout;

import model.Lookup;
import model.DataBaseTableModel.tableNames;

public class ZoomInput extends Input {
	
	private String label;
	private tableNames parentTableName;
	private DatabaseForma containerForm;
	
	private JTextField tfSifra;
	private JTextField tfNaziv;
	private JButton btnZoom;
	
	public ZoomInput(DatabaseForma containerForm, tableNames parentTableName, String label, int sifraLength, int nameLength) {
		this.label = label;
		this.parentTableName = parentTableName;
		this.containerForm = containerForm;
		
		tfSifra = new JTextField(sifraLength);
		tfNaziv = new JTextField(nameLength);
		btnZoom = new JButton("...");
		
		tfSifra.setEditable(false);
		tfNaziv.setEditable(false);
		
		btnZoom.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				FormController fc = new FormController();
				DatabaseForma m = resolveForm(fc, ZoomInput.this.parentTableName);
				fc.setForm(m);
				m.setParentDialog(ZoomInput.this.containerForm, fc);
				m.setVisible(true);
			}
		});
		
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return tfSifra.getText();
	}

	@Override
	public JComponent getComponent() {
		// TODO Auto-generated method stub
		JPanel zoomPanel = new JPanel();
		zoomPanel.setLayout(new MigLayout("center"));
		zoomPanel.add(new JLabel(label));
		zoomPanel.add(tfSifra, "grow 0, split 3");
		zoomPanel.add(btnZoom, "grow 0");
		zoomPanel.add(tfNaziv, "wrap 5");
		return zoomPanel;
	}
	
	private DatabaseForma resolveForm(FormController fc, tableNames tableName) {
		
		switch (tableName) {
		case ANALITIKA_MAGACINSKE_KARTICE:
			return new AnalitikaMagacinskeKarticeForma(fc);
		case ARTIKAL:
			return new ArtikliForma(fc);
		case CLAN_POPISNE_KOMISIJE:
			return new ClanPopisneKomisijeForma(fc);
		case DRZAVA:
			return new DrzaveForma(fc);
		case MAGACINSAK_KARTICA:
			return new MagacinskeKarticeForma(fc);
		case MERNA_JEDINICA:
			return new MernaJedinicaForma(fc);
		case NASELJENO_MESTO:
			return new MestaForma(fc);
		case POPISNI_DOKUMENT:
			return new PopisniDokumentForma(fc);
		case POSLOVNA_GODINA:
			return new PoslovnaGodinaForma(fc);
		case POSLOVNI_OBJEKAT:
			return new PoslovniObjektiForma(fc);
		case POSLOVNI_PARTNER:
			return new PoslovniPartnerForma(fc);
		case PREDUZECE:
			return new PreduzecaForma(fc);
		case PROMETNI_DOKUMENT:
			return new PrometniDokumentForma(fc);
		case STAVKA_PROMETNOG_DOKUMENTA:
			return new StavkePrometnogDokumentaForma(fc);
		case STAVKE_POPISA:
			return new StavkePopisaForma(fc);
		case TIP_OBJEKTA:
			return new TipObjektaForma(fc);
		case ZAPOSLENI:
			return new ZaposleniForma(fc);
		default:
			return null;
		}
	}

	@Override
	public void setText(String text) {
		tfSifra.setText(text);
		inputChanged(null);
	}
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return tfSifra.getText().equals("");
	}
	
	public void setNaziv(String naziv) {
		tfNaziv.setText(naziv);
	}

	@Override
	public void setUserEditable(boolean b) {
		// TODO Auto-generated method stub
		btnZoom.setEnabled(b);
	}

	@Override
	public void setDocument(PlainDocument p) {
		// TODO Auto-generated method stub
		
	}

}
