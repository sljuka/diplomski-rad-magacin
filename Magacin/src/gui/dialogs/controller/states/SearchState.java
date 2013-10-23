package gui.dialogs.controller.states;

import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controllers.DialogController;

import db.DBConnection;
import model.DataBaseTableModel;

public class SearchState extends State {

	@Override
	public void handleState(DialogController controller) {
		// TODO Auto-generated method stub

		DialogController ctrl = controller;
		DataBaseTableModel model = ctrl.getDatabaseDialog().getModel();
		int columnCount = model.getColumnCount();
		String[] columnNames=new String[columnCount];
		String[] strings =ctrl.getComponentStrings();
		String tableName = model.getTableName();
		String stmt = "SELECT * FROM "+tableName+" WHERE ";
		
		for(int i=0; i<columnCount; i++)
		{
			if(strings[i]==null)
			{
				strings[i]="";
			}
			columnNames[i]=model.getColumnName(i);
			if(i!=(columnCount-1))
				stmt+=columnNames[i]+" LIKE "+"'"+strings[i]+"%'"+" AND ";
			else
				stmt+=columnNames[i]+" LIKE "+"'"+strings[i]+"%'";

		}	
		System.out.println(stmt);
		ResultSet rset;
		try {
			Statement statement = DBConnection.getConnection().createStatement();
		
		rset = statement.executeQuery(stmt);
		while (rset.next()) {
			String[] addRow = new String[columnNames.length];
			for (int i = 0; i < columnNames.length ; i++)
				addRow[i] = rset.getString(columnNames[i]);
			model.sortedInsert(addRow);
		}
		statement.close();
		rset.close();
		model.fireTableDataChanged();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Search state";
	}

	@Override
	public void mousePressed(MouseEvent e, DialogController controller) {
		// TODO Auto-generated method stub
		
	}

}