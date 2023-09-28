package yogurt.pages;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import yogurt.apis.Test;
import yogurt.db.TestDAO;
import yogurt.db.TestDTO;

public class TestModel extends AbstractTableModel{
	ConcertList concertList;
	Test test;
	
	TestDAO testDAO=new TestDAO();
	TestDTO testDTO;
	
//	List<TestDTO> teli=new ArrayList<TestDTO>();
	
	//List lili=new ArrayList();
	
	String[] column= {"NUM", "TITLE", "접수시작일"};	
	
	public TestModel(ConcertList concertList) {
		this.concertList=concertList;
		
//		test.getName();
		//teli=test.getName();
//		teli=testDAO.selectAll();
		//System.out.println("1 :"+teli);
		
//		for(int i=0; i<5;i++) {
//			lili=teli;
//			
//			//testDAO.select();
//		}
		
		
	}
	
	public int getRowCount() {
		return concertList.teli.size();
	}

	public int getColumnCount() {
		return column.length;
	}

	public String getColumnName(int col) {
		return column[col];
	}
	
	public Object getValueAt(int row, int col) {
		testDTO=concertList.teli.get(row);
		
		//testDAO.select(0, testDTO);
		//System.out.println("2 :"+concertList.teli);
		
		String value="";
		
		switch(col) {
			case 0:value=Integer.toString(testDTO.getTest_idx());break;	
			case 1:value=testDTO.getTitle();break;	
			case 2:value=testDTO.getApplydate();break;	
		}		
		return value;
		
		//return "바나나";
	}

	
	
}
