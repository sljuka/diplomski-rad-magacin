package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.DataBaseTableModel.tableNames;

import db.DBConnection;

public class AuthentificationController {

	private String sifraUlogovanogKorisnika;
	private String pibPreduzecaUlogovanogKorisnika;
	private String imeUlogovanogKorisnika;
	private String prezimeUlogovanogKorisnika;
	private boolean isAdmin;
	private static AuthentificationController instance;
	
	private AuthentificationController() {
		
	}
	
	public static AuthentificationController getAuthenticationInstance() {
		if(instance==null)
			instance = new AuthentificationController();
		return instance;
	}
	
	public boolean authenticate(String sifra, String password) throws SQLException {
		ResultSet rset;
		Statement statement = DBConnection.getConnection().createStatement();
		
		String sqlStatement = "SELECT * FROM " + tableNames.ZAPOSLENI.toString() +
							" WHERE SIFRARDNK = " + sifra;
		
		
		rset = statement.executeQuery(sqlStatement);
		while (rset.next()) {
			String dbSifra = rset.getString("SIFRARDNK");
			String dbPrezime = rset.getString("PREZIME");
			String dbIme = rset.getString("IME");
			String pib = rset.getString("PIB");
			String dbPassword = rset.getString("PASSWORD");
			String dbIsAdmin = rset.getString("ISADMIN");
			if(password.equals(dbPassword)) {
				sifraUlogovanogKorisnika = dbSifra;
				imeUlogovanogKorisnika = dbIme;
				prezimeUlogovanogKorisnika = dbPrezime;
				pibPreduzecaUlogovanogKorisnika = pib;
				isAdmin = dbIsAdmin.equals("1");
				statement.close();
				rset.close();
				return true;
			}
		}
		return false;
	}
	
	public boolean logout() {
		if(sifraUlogovanogKorisnika!=null && sifraUlogovanogKorisnika != "") {
			sifraUlogovanogKorisnika = null;
			imeUlogovanogKorisnika = null;
			prezimeUlogovanogKorisnika = null;
			pibPreduzecaUlogovanogKorisnika = null;
			pibPreduzecaUlogovanogKorisnika = null;
			isAdmin = false;
			return true;
		}
		return false;
	}

	public String getSifraUlogovanogKorisnika() {
		return sifraUlogovanogKorisnika;
	}

	public String getPibPreduzecaUlogovanogKorisnika() {
		return pibPreduzecaUlogovanogKorisnika;
	}

	public String getImeUlogovanogKorisnika() {
		return imeUlogovanogKorisnika;
	}

	public String getPrezimeUlogovanogKorisnika() {
		return prezimeUlogovanogKorisnika;
	}
	
	public boolean isLoged() {
		return sifraUlogovanogKorisnika != null && !sifraUlogovanogKorisnika.equals("");
	}
	
	public boolean isAdmin() {
		return (isLoged() && isAdmin);
	}
	
}
