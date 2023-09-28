package yogurt.pages;

import javax.swing.table.AbstractTableModel;

import yogurt.db.HeartList;
import yogurt.db.HeartListDAO;

public class HeartListModel extends AbstractTableModel{
	Heart heart;
	HeartListDAO heartListDAO=new HeartListDAO();
	HeartList heartList;
	
	String[] column= {"NUM", "TITLE", "접수시작일"};	
	
	public HeartListModel(Heart heart) {
		this.heart=heart;
	}
	
	public int getRowCount() {
		return heart.hli.size();
	}

	public int getColumnCount() {
		return column.length;
	}

	public String getColumnName(int col) {
		return column[col];
	}
	
	public Object getValueAt(int row, int col) {
		heartList=heart.hli.get(row);
		
		String value="";
		
		switch(col) {
			case 0:value=Integer.toString(heartList.getHeart_idx());break;	
			case 1:value=heartList.getTitle();break;	
			case 2:value=heartList.getApplydate();break;	
		}		
		return value;

	}
	
	
	
}
