package model;

import gui.SortUtils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import controllers.AuthentificationController;

import db.DBConnection;

public class DataBaseTableModel extends MyTableModel {

	public enum tableNames {
		DRZAVA, PREDUZECE, NASELJENO_MESTO, POSLOVNI_OBJEKAT, TIP_OBJEKTA, ZAPOSLENI,
		POSLOVNA_GODINA, POSLOVNI_PARTNER, ARTIKAL, MERNA_JEDINICA, MAGACINSAK_KARTICA,
		PROMETNI_DOKUMENT, POPISNI_DOKUMENT, STAVKA_PROMETNOG_DOKUMENTA, CLAN_POPISNE_KOMISIJE,
		STAVKE_POPISA, ANALITIKA_MAGACINSKE_KARTICE
	};

	// TABLE MODEL SE
	// POKLAPA SA BAZINiM TABELAMA

	//Dodate konstante za potrebe izvestavanja korisnika o greskama

	private static final int CUSTOM_ERROR_CODE = 50000;
	private static final String ERROR_RECORD_WAS_CHANGED = "Slog je promenjen od strane drugog korisnika. Molim vas, pogledajte njegovu trenutnu vrednost";
	private static final String ERROR_RECORD_WAS_DELETED = "Slog je obrisan od strane drugog korisnika";

	private String tableName;
	private String sqlStatement;

	private String basicQuery;

	private String[] keyFilter;
	
	private int[] primaryKeysColumnNumbers;

	public String[] getSelectedKeyNameValuePair(int selectedIndex) {
		if(primaryKeysColumnNumbers == null)
			return null;
		String[] returnVal = new String[primaryKeysColumnNumbers.length*2];
		int n = 0;
		int v = 0;
		
		for (int i = 0; i < primaryKeysColumnNumbers.length; i++) {
			
			n = i * 2;
			v = n++;
			returnVal[n] = (String)getValueAt(selectedIndex, primaryKeysColumnNumbers[i]);
			returnVal[v] = getColumnName(primaryKeysColumnNumbers[i]);
		}
		return returnVal;
	}
	
	public DataBaseTableModel(tableNames name) {
		super();
		this.tableName = name.toString();
		try {
	//		DBConnection.getConnection().setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			generateColumnNames();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void generateColumnNames() throws SQLException {
		DatabaseMetaData meta = DBConnection.getConnection().getMetaData();
		ResultSet rset = meta.getColumns(null, null, tableName, null);
		List<String> columns = new ArrayList<>();
		while (rset.next()) {
			columns.add(rset.getString("COLUMN_NAME"));
		}
		columnNames = new String[columns.size()];
		columns.toArray(columnNames);
	}

	private void generateTableContent(String[] keyFilter) throws SQLException {
		// TODO Auto-generated method stub
		ResultSet rset;
		Statement statement = DBConnection.getConnection().createStatement();
		sqlStatement = "SELECT * FROM " + tableName;
		
		if(!AuthentificationController.getAuthenticationInstance().isAdmin()) {
			for (String string : columnNames) {
				if(string.equals("PIB"))
					sqlStatement += " WHERE PIB = '" + AuthentificationController.getAuthenticationInstance().getPibPreduzecaUlogovanogKorisnika() + "'";
			}
		}
		
		if(keyFilter != null) {
			if (keyFilter[1] != null && !keyFilter[1].equals("")) {
				if(sqlStatement.contains("WHERE"))
					sqlStatement += " AND ";
				else
					sqlStatement += " WHERE ";
				
				for (int i = 0; i < keyFilter.length/2; i++) {
					if (keyFilter[i + 1].equals(""))
						break;
					sqlStatement += keyFilter[i*2] + "=" + "'" + keyFilter[i*2 + 1]
							+ "'";
					i++;
					if (i < keyFilter.length/2 - 1)
						sqlStatement += " AND ";
				}
			}
		}
		
		System.out.println(sqlStatement);
		
		rset = statement.executeQuery(sqlStatement);
		while (rset.next()) {
			String[] addRow = new String[columnNames.length];
			for (int i = 0; i < columnNames.length; i++) {
				if(tableName.equals(tableNames.ZAPOSLENI.toString()) && i == 4) {
					addRow[i] = "*******";
				}
				else
					addRow[i] = rset.getString(columnNames[i]);
			}
			sortedInsert(addRow);
		}
		statement.close();
		rset.close();
		fireTableDataChanged();
	}

	public int insertRow(String[] newRow) throws SQLException {
		int retVal = 0;

		if (tableName.equals(tableNames.POSLOVNA_GODINA.toString())) {
			int bnzg = Lookup.getBrojNeZakljucenihGodina(newRow[0]);
			if (bnzg >= 2) {
				JOptionPane.showMessageDialog(null,
						"Najvise 2 nezakljucene godine po preduzecu", "Error",
						JOptionPane.ERROR_MESSAGE);
				return -1;
			}
			System.out.println(bnzg);
		}

		String valuesPart = " VALUES (";
		String columnsPart = " (";
		for (int i = 0; i < columnNames.length; i++) {
			if (i != 0) {
				columnsPart += ", ";
				valuesPart += ", ";
			}
			columnsPart += columnNames[i];
			valuesPart += "?";
		}
		columnsPart += ")";
		valuesPart += ")";
		PreparedStatement stmt = DBConnection.getConnection().prepareStatement(
				"INSERT INTO " + tableName + columnsPart + valuesPart);
		for (int i = 0; i < columnNames.length; i++)
			stmt.setString(i + 1, newRow[i]);
		System.out.println("INSERT INTO " + tableName + columnsPart + valuesPart);
		int rowsAffected = stmt.executeUpdate();
		stmt.close();
		DBConnection.getConnection().commit();
		if (rowsAffected > 0) {
			retVal = sortedInsert(newRow);
			fireTableRowsInserted(retVal, retVal);
		}
		else
			System.out.println("insertion error");
		return retVal;
	}

	public void insertRow(int row, String[] newRow) {
		values.add(row, newRow);
	}

	public void refreshData() throws SQLException {
		values.clear();
		generateTableContent(keyFilter);
	}

	public void open(String[] keyFilter) throws SQLException {
		// TODO Auto-generated method stub
		this.keyFilter = keyFilter;
		generateTableContent(keyFilter);
	}

	public int sortedInsert(String[] row) {
		int left = 0;
		int right = getRowCount() - 1;
		int mid = (left + right) / 2;
		while (left <= right) {
			mid = (left + right) / 2;
			String aSifra = (String) getValueAt(mid, 0);
			if (SortUtils.getLatCyrCollator().compare(row[0], aSifra) > 0)
				left = mid + 1;
			else if (SortUtils.getLatCyrCollator().compare(row[0], aSifra) < 0)
				right = mid - 1;
			else
				// ako su jednaki: to ne moze da se desi ako tabela ima primarni
				// kljuc
				break;
		}
		insertRow(left, row);
		return left;
	}

	public List<String[]> getValues() {
		return values;
	}

	public void setKeyFilter(String[] keyFilter) {
		this.keyFilter = keyFilter;
		try {
			refreshData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteRow(int row, int[] keyColumnNumbers) throws SQLException {
	//	checkRow(row);
		String statement = "DELETE FROM " + tableName + " WHERE ";
		if (row < 0)
			return;
		for (int i = 0; i < keyColumnNumbers.length; i++) {
			if (i != 0)
				statement += " AND ";
			statement += getColumnName(keyColumnNumbers[i]) + " = '"
					+ getValueAt(row, keyColumnNumbers[i]) + "'";
		}
		if (tableName.equals(tableNames.POSLOVNA_GODINA.toString())) {
			if (Lookup.isZakljucena(
					(String) getValueAt(row, keyColumnNumbers[0]),
					(String) getValueAt(row, keyColumnNumbers[1]))) {
				JOptionPane.showMessageDialog(null,
						"Brisanje zakljucene godine nije dozvoljeno",
						"Brisanje godine", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		PreparedStatement stmt = DBConnection.getConnection().prepareStatement(
				statement);
		int rowsAffected = stmt.executeUpdate();
		stmt.close();
		DBConnection.getConnection().commit();
		if (rowsAffected > 0) {
			// i brisanje iz TableModel-a
			removeRow(row);
			fireTableRowsDeleted(row, row);
		}
	}

	public void zakljuciGodinu(int index, String[] godina) throws SQLException {
		// TODO Auto-generated method stub
		try {
			CallableStatement proc = DBConnection.getConnection().prepareCall("{call zakljuci_godinu(?,?)}");
			proc.setString(1, godina[1]);					
			proc.setString(2, godina[3]);
			System.out.println(godina[1]+" "+godina[3]);
			proc.execute();
			values.get(index)[2] = "1";
			fireTableCellUpdated(index, 2);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	public void removeRow(int row) {
		// TODO Auto-generated method stub
		values.remove(row);
	}

	public void proknjiziDokument(int index, String[] strings) {
		// TODO Auto-generated method stub
		try {
			CallableStatement proc = DBConnection.getConnection().prepareCall("{ call proknjizi_prometni_dokument(?, ?, ?)}");
			proc.setString(1, strings[1]);
			proc.setLong(2, Integer.parseInt(strings[3]));
			proc.setLong(3, Integer.parseInt(strings[5]));
			System.out.println(strings[1] +" "+Integer.parseInt(strings[3])+" "+Integer.parseInt(strings[5]));
			proc.execute();
			values.get(index)[9] = "p";
			refreshData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getTableName() {
		return this.tableName;

	}

	public void search(String[] componentStrings) {
		// TODO Auto-generated method stub
		sqlStatement = "SELECT * FROM " + tableName;
		sqlStatement += " WHERE ";
		for (int i = 0; i < componentStrings.length; i++) {
			if (componentStrings[i] == null)
				continue;
			sqlStatement += getColumnName(i) + "=" + "'" + componentStrings[i]
					+ "'";
			sqlStatement += " AND ";
		}

	}

	public void clear() {
		// TODO Auto-generated method stub
		values.clear();
		fireTableDataChanged();
	}

	public void setPrimaryKeysNumbers(int[] primaryKeysColumnNumber) {
		// TODO Auto-generated method stub
		this.primaryKeysColumnNumbers = primaryKeysColumnNumber;
	}

}
