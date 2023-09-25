package yogurt.pages;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import yogurt.apis.Test;
import yogurt.apis.Test2;
import yogurt.db.HeartList;
import yogurt.db.HeartListDAO;
import yogurt.db.Test2DAO;
import yogurt.db.Test2DTO;
import yogurt.db.TestDAO;
import yogurt.db.TestDTO;

public class Test2Model extends AbstractTableModel{
	ExhibitList exhibitList;
	Test2 test2;
	
	HeartList heartList;
	HeartListDAO heartListDAO=new HeartListDAO();
	
	Test2DAO test2dao=new Test2DAO();
	Test2DTO test2dto;

	String[] column= {"NUM", "TITLE", "접수시작일"};	
	
	public Test2Model(ExhibitList exhibitList) {
		this.exhibitList=exhibitList;		
	}
	
	public int getRowCount() {
		return exhibitList.teli.size();
	}

	public int getColumnCount() {
		return column.length;
	}

	public String getColumnName(int col) {
		return column[col];
	}
	
	public Object getValueAt(int row, int col) {
		test2dto=exhibitList.teli.get(row);
		
		String value="";
		
		switch(col) {
			case 0:value=Integer.toString(test2dto.getTest2_idx());break;	
			case 1:value=test2dto.getTitle();break;	
			case 2:value=test2dto.getApplydate();break;	
		}		
		return value;
	}

	
	
}
