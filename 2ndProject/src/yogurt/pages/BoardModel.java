package yogurt.pages;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import yogurt.db.Board;

//뉴스게시물을 보여줄 JTable에 정보를 제공해주는 모델객체
public class BoardModel extends AbstractTableModel{
	List<Board> boardList=new ArrayList<Board>(); //0 nullpoint방지로 사이즈 0 미리 올려줌
	
	String[] columnName= {"board_id", "제목","작성자","등록일","조회수"};
	public int getRowCount() { //층수
		return boardList.size();
	}

	public int getColumnCount() { //호수
		return 5;
	}

	@Override
	public String getColumnName(int col) {
		return columnName[col];
	}
	
	public Object getValueAt(int row, int col) {
		Board board=boardList.get(row); //층수에서 배열이 아닌, 보다 객체지향적인 DTO를 꺼낸다
		String value=null;
		
		switch(col) {
			case 0: value=Integer.toString(board.getBoard_id());break;
			case 1: value=board.getTitle();break;
			case 2: value=board.getWriter();break;
			case 3: value=board.getRegdate();break;
			case 4: value=Integer.toString(board.getHit());break;
		}
		return value;
	}
	

}
