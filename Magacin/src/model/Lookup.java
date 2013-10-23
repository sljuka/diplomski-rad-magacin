package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DBConnection;

public class Lookup { 
	public static String getDrzava (String sifraDrzave) throws SQLException {
		String naziv = "";  
		if (sifraDrzave.equals("")) return naziv;
		Statement stmt = DBConnection.getConnection().createStatement();
		String sql = "SELECT NAZIV_DRZAVE FROM DRZAVA WHERE SIFRA_DRZAVE = '" + sifraDrzave + "'";  
		ResultSet rset = stmt.executeQuery(sql);
		while (rset.next()) {      
			naziv = rset.getString("NAZIV_DRZAVE");      
		}
		rset.close();
		stmt.close();
		return naziv;
	}
	
	public static boolean isZakljucena (String pib, String godina) throws SQLException {
		boolean retVal = true;
		if (godina.equals("") || pib.equals(""))
			return true;
		Statement stmt = DBConnection.getConnection().createStatement();
		String sql = "SELECT ZAKLJUCENA FROM POSLOVNA_GODINA WHERE GODINA = '" + godina + "' AND PIB = '"+ pib +"'"; 
		ResultSet rset = stmt.executeQuery(sql);
		String zakljucena = "0";
		while (rset.next()) {      
			zakljucena = rset.getString("ZAKLJUCENA");      
		}
		retVal = zakljucena.equals("1")?true:false;
		rset.close();
		stmt.close();
		return retVal;
	}
	
	public static int getBrojNeZakljucenihGodina (String pib) throws SQLException {
		if (pib.equals(""))
			return -1;
		int numberOfRows = 0;
		Statement stmt = DBConnection.getConnection().createStatement();
		String sql = "SELECT COUNT(*) FROM POSLOVNA_GODINA WHERE PIB = '"+ pib +"'"+ " AND ZAKLJUCENA = '0'"; 
		ResultSet rset = stmt.executeQuery(sql);
		if (rset.next()) {
			numberOfRows = rset.getInt(1);
		}
		return numberOfRows;
	}
	
}
