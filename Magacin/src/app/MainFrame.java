package app;

import gui.dialogs.AnalitikaMagacinskeKarticeForma;
import gui.dialogs.ArtikliForma;
import gui.dialogs.ClanPopisneKomisijeForma;
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
		
		miTipoviObjekta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TipObjektaForma t = new TipObjektaForma();
				t.setVisible(true);
			}
		});
		
		miClanoviPopisneKomisije.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ClanPopisneKomisijeForma cpd = new ClanPopisneKomisijeForma();
				cpd.setVisible(true);
				
			}
		});
		miPreduzece.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				PreduzecaForma p = new PreduzecaForma();
				p.setVisible(true);
			}
		});
		
		miPoslovniObjekat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				PoslovniObjektiForma p = new PoslovniObjektiForma();
				p.setVisible(true);
			}
		});
		miZaposleni.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ZaposleniForma z = new ZaposleniForma();
				z.setVisible(true);
			}
		});
		miPoslovnaGodina.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				PoslovnaGodinaForma p = new PoslovnaGodinaForma();
				p.setVisible(true);
			}
		});
		miPoslovniPartner.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				PoslovniPartnerForma p = new PoslovniPartnerForma();
				p.setVisible(true);
			}
		});
		miArtikli.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ArtikliForma d = new ArtikliForma();
				d.setVisible(true);
			}
		});
		miMagacinkseKartice.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MagacinskeKarticeForma m = new MagacinskeKarticeForma();
				m.setVisible(true);
			}
		});
		miPrometniDokumenti.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PrometniDokumentForma d = new PrometniDokumentForma();
				d.setVisible(true);
			}
		});
		
		miPopisniDokumenti.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				PopisniDokumentForma pdD = new PopisniDokumentForma();
				pdD.setVisible(true);
			}
		});
		
		miStavkaPopisa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				StavkePopisaForma stvDlg = new StavkePopisaForma();
				stvDlg.setVisible(true);
			}
		});
		
		miAnalitikaMagKartice.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AnalitikaMagacinskeKarticeForma anlDlg = new AnalitikaMagacinskeKarticeForma();
				anlDlg.setVisible(true);
			}
		});
		
		miDrzave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				DrzaveForma drzD = new DrzaveForma();
				drzD.setVisible(true);
			}
		});
		
		miNaseljenaMesta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MestaForma m = new MestaForma();
				m.setVisible(true);
			}
		});
		
		miMernaJedinica.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MernaJedinicaForma m = new MernaJedinicaForma();
				m.setVisible(true);
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
		
		
		menuBar.add(menuAdministrator);
		
		menuBar.add(menuRacunovodsvo);
		menuBar.add(menuMagacin);
		setJMenuBar(menuBar);
	}

}
