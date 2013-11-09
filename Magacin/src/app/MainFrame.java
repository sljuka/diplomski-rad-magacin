package app;

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

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import controllers.AuthentificationController;
import controllers.FormController;

import db.DBConnection;


public class MainFrame extends JFrame {

	private JMenuBar menuBar;
	private JMenu menuAdministrator; 
	
	private static MainFrame mf;
	
	private JPanel loginPanel;
	private JPanel welcomePanel;
	
	private JTextField txtSifra;
	private JTextField txtPassword;
	private JLabel lblGreska;
	
	private JLabel lblWelcome;
	
	private MainFrame() {
		setSize(700, 500);
		setTitle("PI magacin");
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		initiateComponents();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				try {
					DBConnection.getConnection().close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
				super.windowClosing(e);
			}
		});
		menuBar.setVisible(false);
		loginPanel.setVisible(true);
		welcomePanel.setVisible(false);
	}
	
	public static MainFrame getInstance() {
		if (mf == null)
			mf = new MainFrame();
		return mf;
	}

	private void initiateComponents() {
		
		setLayout(new FlowLayout());
		
		loginPanel = new JPanel();
		welcomePanel = new JPanel();
		
		initiateLoginPage();
		initiateWelcomePage();
		initiateDialogs();
		initiateMenu();
		
		add(loginPanel);
		add(welcomePanel);
	}
	
	private void initiateWelcomePage() {
		lblWelcome = new JLabel();
		
		JButton btnLogout = new JButton("Izlogujte se");
				btnLogout.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						AuthentificationController.getAuthenticationInstance().logout();
						loginPanel.setVisible(true);
						welcomePanel.setVisible(false);
						menuBar.setVisible(false);
						menuAdministrator.setVisible(false);
					}
				});
		welcomePanel.setLayout(new MigLayout("", "[align l][align l, grow, fill]", ""));
		welcomePanel.add(lblWelcome, "wrap");
		welcomePanel.add(btnLogout);
	}
	
	private void initiateLoginPage() {
		JLabel lblLogin = new JLabel("Niste ulogovani");
		txtSifra = new JTextField(20);
		txtPassword = new JTextField(20);
		lblGreska = new JLabel("GRESKA");
		lblGreska.setVisible(false);
		JButton btnLogin = new JButton("Ulogujte se");
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					boolean successful = AuthentificationController.getAuthenticationInstance().authenticate(txtSifra.getText(), txtPassword.getText());
					if(successful) {
						String welcomeText = "Pozrav, " + AuthentificationController.getAuthenticationInstance().getImeUlogovanogKorisnika() + 
								(AuthentificationController.getAuthenticationInstance().isAdmin()? " care!" : "");
						lblWelcome.setText(welcomeText);
						
						menuBar.setVisible(true);
						if (AuthentificationController.getAuthenticationInstance().isAdmin())
							menuAdministrator.setVisible(true);
						else
							menuAdministrator.setVisible(false);
						lblGreska.setVisible(false);
						loginPanel.setVisible(false);
						welcomePanel.setVisible(true);
						txtSifra.setText("");
						txtPassword.setText("");
					}
					else
					{
						lblGreska.setVisible(true);	
						loginPanel.setVisible(true);
						welcomePanel.setVisible(false);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		loginPanel.setLayout(new MigLayout("", "[align l][align l, grow, fill]", ""));
		loginPanel.add(lblLogin, "wrap, align c");
		loginPanel.add(new JLabel("Korisnicko ime:"), "wrap");
		loginPanel.add(txtSifra, "wrap");
		loginPanel.add(new JLabel("Password: "), "wrap");
		loginPanel.add(txtPassword, "wrap");
		loginPanel.add(lblGreska, "wrap");
		loginPanel.add(btnLogin);
	}
	
	private void initiateDialogs() {
		// TODO Auto-generated method stub

	}

	private void initiateMenu() {
		menuBar = new JMenuBar();
		menuAdministrator = new JMenu("Administracija");
		/*
		 * DRZAVA
		 * NASELJENO MESTO
		 * PREDUZECE
		 * MERNA JEDINICA
		 * TIP OBJEKTA
		 */
		
		JMenu menuRacunovodsvo = new JMenu("Racunovodstvo");
		/*
		 * POSLOVNI PARTNERI
		 * POSLOVNI OBJEKTI
		 * POSLOVNA GODINA
		 * ZAPOSLENI
		 */
		
		JMenu menuMagacin = new JMenu("Magacin");
		/*
		 * ARTIKLI
		 * MAGACINSKE KARTICE I ANALITIKA
		 * PROMETNI DOKUMENTI I STAVKE
		 * POPISNI DOKUMENT
		 * STAVKE POPISA
		 * CLANOVI POPISNE KOMISIJE
		 * STAVKE PROMETA
		 */
		
		JMenuItem miZaposleni = new JMenuItem("Zaposleni");//
		JMenuItem miAnalitikaMagKartice = new JMenuItem("Analitika magacinske kartice");//
		JMenuItem miArtikli = new JMenuItem("Artikli");//
		JMenuItem miClanoviPopisneKomisije = new JMenuItem("Popisna komisija");//
		JMenuItem miDrzave = new JMenuItem("Drzave");//
		JMenuItem miMagacinkseKartice = new JMenuItem("Magacinske kartice");//
		JMenuItem miMernaJedinica = new JMenuItem("Merna jedinica");
		JMenuItem miNaseljenaMesta = new JMenuItem("Naseljena mesta");//
		JMenuItem miPopisniDokumenti = new JMenuItem("Popisni dokumenti");//
		JMenuItem miPoslovnaGodina = new JMenuItem("Poslovne godine");//
		JMenuItem miPoslovniObjekat = new JMenuItem("Poslovni objekti");//
		JMenuItem miPoslovniPartner = new JMenuItem("Poslovni partneri");//
		JMenuItem miPreduzece = new JMenuItem("Preduzeca");//
		JMenuItem miPrometniDokumenti = new JMenuItem("Prometni dokumenti");//
		JMenuItem miStavkaPopisa = new JMenuItem("Stavka popisa");//
		JMenuItem miTipoviObjekta = new JMenuItem("Tipovi objekta");
		JMenuItem miStavkePrometa = new JMenuItem("Stavke prometa");
		
		miTipoviObjekta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FormController fc = new FormController();
				DatabaseForma form = new TipObjektaForma(fc);
				fc.setForm(form);
				form.setVisible(true);
			}
		});
		
		miClanoviPopisneKomisije.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FormController fc = new FormController();
				ClanPopisneKomisijeForma form = new ClanPopisneKomisijeForma(fc);
				fc.setForm(form);
				form.setVisible(true);
				
			}
		});
		miPreduzece.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				FormController fc = new FormController();
				PreduzecaForma form = new PreduzecaForma(fc);
				fc.setForm(form);
				form.setVisible(true);
			}
		});
		
		miPoslovniObjekat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				FormController fc = new FormController();
				PoslovniObjektiForma form = new PoslovniObjektiForma(fc);
				fc.setForm(form);
				form.setVisible(true);
			}
		});
		miZaposleni.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FormController fc = new FormController();
				ZaposleniForma form = new ZaposleniForma(fc);
				fc.setForm(form);
				form.setVisible(true);
			}
		});
		miPoslovnaGodina.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FormController fc = new FormController();
				PoslovnaGodinaForma form = new PoslovnaGodinaForma(fc);
				fc.setForm(form);
				form.setVisible(true);
			}
		});
		miPoslovniPartner.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FormController fc = new FormController();
				PoslovniPartnerForma form = new PoslovniPartnerForma(fc);
				fc.setForm(form);
				form.setVisible(true);
			}
		});
		miArtikli.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FormController fc = new FormController();
				ArtikliForma form = new ArtikliForma(fc);
				fc.setForm(form);
				form.setVisible(true);
			}
		});
		miMagacinkseKartice.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FormController fc = new FormController();
				MagacinskeKarticeForma form = new MagacinskeKarticeForma(fc);
				fc.setForm(form);
				form.setVisible(true);
			}
		});
		miPrometniDokumenti.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FormController fc = new FormController();
				PrometniDokumentForma form = new PrometniDokumentForma(fc);
				fc.setForm(form);
				form.setVisible(true);
			}
		});
		
		miPopisniDokumenti.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FormController fc = new FormController();
				PopisniDokumentForma form = new PopisniDokumentForma(fc);
				fc.setForm(form);
				form.setVisible(true);
			}
		});
		
		miStavkaPopisa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FormController fc = new FormController();
				StavkePopisaForma form = new StavkePopisaForma(fc);
				fc.setForm(form);
				form.setVisible(true);
			}
		});
		
		miAnalitikaMagKartice.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FormController fc = new FormController();
				AnalitikaMagacinskeKarticeForma form = new AnalitikaMagacinskeKarticeForma(fc);
				fc.setForm(form);
				form.setVisible(true);
			}
		});
		
		miDrzave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FormController fc = new FormController();
				DatabaseForma form = new DrzaveForma(fc);
				fc.setForm(form);
				form.setVisible(true);
			}
		});
		
		miNaseljenaMesta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FormController fc = new FormController();
				MestaForma form = new MestaForma(fc);
				fc.setForm(form);
				form.setVisible(true);
			}
		});
		
		miMernaJedinica.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FormController fc = new FormController();
				MernaJedinicaForma form = new MernaJedinicaForma(fc);
				fc.setForm(form);
				form.setVisible(true);
			}
		});
		
		miStavkePrometa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FormController fc = new FormController();
				StavkePrometnogDokumentaForma form = new StavkePrometnogDokumentaForma(fc);
				fc.setForm(form);
				form.setVisible(true);
			}
		});
		
		menuAdministrator.add(miDrzave);
		menuAdministrator.add(miNaseljenaMesta);
		menuAdministrator.add(miPreduzece);
		menuAdministrator.add(miMernaJedinica);
		menuAdministrator.add(miTipoviObjekta);
		
		menuRacunovodsvo.add(miPoslovniObjekat);
		menuRacunovodsvo.add(miPoslovniPartner);
		menuRacunovodsvo.add(miPoslovnaGodina);
		menuRacunovodsvo.add(miZaposleni);
		
		menuMagacin.add(miArtikli);
		menuMagacin.add(miMagacinkseKartice);
		menuMagacin.add(miAnalitikaMagKartice);
		menuMagacin.add(miPrometniDokumenti);
		menuMagacin.add(miPopisniDokumenti);
		menuMagacin.add(miStavkaPopisa);
		menuMagacin.add(miClanoviPopisneKomisije);
		menuMagacin.add(miStavkePrometa);
		
		menuBar.add(menuAdministrator);
		
		menuBar.add(menuRacunovodsvo);
		menuBar.add(menuMagacin);
		setJMenuBar(menuBar);
	}

}
