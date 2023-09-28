package yogurt.pages;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import yogurt.db.Reply;

//댓글 JTable이 참고할 데이터를 제공하는 모델 클래스
public class ReplyModel extends AbstractTableModel{

	ArrayList<Reply> list=new ArrayList<Reply>();
	
	String[] columnName= {"댓글메시지","작성자","작성일"};
	
	public int getRowCount() {
		return list.size();
	}

	public int getColumnCount() {
		return columnName.length;
	}
	
	public String getColumnName(int col) {
		return columnName[col];
	}

	public Object getValueAt(int row, int col) {
		String value=null;
		Reply reply=list.get(row); //해당 층에 있는 dto에 먼저 접근
		
		switch(col) {
			case 0: value=reply.getRtitle();break;
			case 1: value=reply.getRwriter();break;
			case 2: value=reply.getRregdate();break;			
		}		
		return value;
	}
	
}
