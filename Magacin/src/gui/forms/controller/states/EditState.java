package gui.forms.controller.states;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import controllers.FormController;

import db.DBConnection;

import model.DataBaseTableModel;
import model.DataBaseTableModel.tableNames;
import model.Lookup;
import gui.forms.DatabaseForma;

public class EditState extends State {

	@Override
	public void handleState(FormController controller) {
		// TODO Auto-generated method stub		
		if(JOptionPane.showConfirmDialog(controller.getDatabaseDialog(), "Da li ste sigurni da zelite da izvrsite izmenu", 
				"Izmena podataka", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.CANCEL_OPTION)
			return;

		FormController ctrl = controller;
		String[] strings =ctrl.getComponentStrings();

		DataBaseTableModel model = ctrl.getDatabaseDialog().getModel();
		DatabaseForma dbdialog = ctrl.getDatabaseDialog();
		
		int[] keyColumnNumbers = dbdialog.getPrimaryKeysColumnNumbers();
		int index = dbdialog.getTable().getSelectedRow();
		
//		try {
//			model.checkRow(index);
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		int columnCount = model.getColumnCount();
		String[] columnNames=new String[columnCount];

		String tableName = model.getTableName();

		if (tableName.equals(tableNames.POSLOVNA_GODINA.toString())) {
			boolean isZak = true;
			try {
				isZak = Lookup.isZakljucena(strings[0], strings[1]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (isZak) {
				JOptionPane.showMessageDialog(null, "Izmena zakljucene godine nije dozvoljena", 
						"Izmena godine", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
								//UPDATE EMPLOYEE SET PHONENO=? WHERE EMPNO=?
		String stmt = "UPDATE "+tableName+" SET ";
		for(int i=0; i<columnCount; i++)
		{
//			columnNames[i]=model.getColumnName(i);
//			if(i!=(columnCount-1))
//				stmt+=columnNames[i]+"="+"'"+strings[i]+"'"+", ";
//			else
//				stmt+=columnNames[i]+"="+"'"+strings[i]+"'"+ " WHERE ";

			columnNames[i] = model.getColumnName(i);
			if(i!=(columnCount-1))
				stmt+=columnNames[i]+"="+"?, ";
			else
				stmt+=columnNames[i]+"="+"?"+ " WHERE ";
			
		}	
		for (int i = 0; i<keyColumnNumbers.length; i++) 
		{
			if (i != 0)
				stmt+=" AND ";
			stmt+=model.getColumnName(keyColumnNumbers[i])+" = '"+model.getValueAt(index, keyColumnNumbers[i])+"'";
		}
		//System.out.println(stmt);


		try {
			PreparedStatement preparedstmt = DBConnection.getConnection().prepareStatement(stmt);
			for(int i=0; i<columnCount; i++) {
				if (columnNames[i].startsWith("datum")) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date d = sdf.parse(strings[i]);
					preparedstmt.setObject(i+1, d);
				}
				preparedstmt.setString(i+1, strings[i]);
			}
			System.out.println(stmt);
			preparedstmt.executeUpdate();
			preparedstmt.close();
			DBConnection.getConnection().commit();
			model.refreshData();
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			if (e.getMessage().startsWith("The UPDATE statement conflicted with the REFERENCE constraint")) {
				JOptionPane.showMessageDialog(null, "podatak je koriscen kao kljuc u drugim stavkama, tako da se ne moze menjati", 
						"Dodavanje stavke", JOptionPane.ERROR_MESSAGE);
				return;
			}
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Edit state";
	}

	@Override
	public void mousePressed(MouseEvent e, FormController controller) {
		// TODO Auto-generated method stub
		
	}

}


