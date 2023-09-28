package yogurt.pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import yogurt.db.Board;

public class BoardRegist extends Page{
	JTextField t_title;
	JTextField t_writer;
	JTextArea t_detail;
	JButton bt_regist;
	JButton bt_list;
	
	PublicBoard publicBoard;
	
	public BoardRegist(YogurtMain yogurtMain, PublicBoard publicBoard) {
		super(yogurtMain);
		this.publicBoard=publicBoard;
		
		t_title=new JTextField();
		t_writer=new JTextField();
		t_detail=new JTextArea();
		
		bt_regist=new JButton("등록");
		bt_list=new JButton("목록");
		
		t_title.setPreferredSize(new Dimension(850,30));
		t_writer.setPreferredSize(new Dimension(850,30));
		t_detail.setPreferredSize(new Dimension(850,450));
		
		add(t_title);
		add(t_writer);
		add(t_detail);
		add(bt_regist);
		add(bt_list);
		
		//글목록 버튼과 리스너 연결
		bt_list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				publicBoard.showHide(publicBoard.BOARDLIST);
			}
		});		
		
		//글등록 버튼과 리스너 연결
		bt_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//empty DTO 생성
				Board board=new Board();
				
				//dto에 글 내용 채워넣기
				board.setTitle(t_title.getText()); //제목 채우기
				board.setWriter(t_writer.getText()); //작성자
				board.setContent(t_detail.getText()); //내용 채우기
				
				int result=publicBoard.boardDAO.insert(board);
				if(result>0) {
					JOptionPane.showMessageDialog(publicBoard, "등록성공");
					//목록 가져오기
					List daoList=publicBoard.boardDAO.selectAll();

					//모델이 현재 보유한 newsList의 주소값을 위의 newsList로 대체
					BoardList boardList=(BoardList)publicBoard.pages[publicBoard.BOARDLIST];
					boardList.model.boardList=daoList; //따끈따끈한 데이터로 교체!!
					boardList.table.updateUI(); //새고!
				}
			}
		});		

		
	}
}
