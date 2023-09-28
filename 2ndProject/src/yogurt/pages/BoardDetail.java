package yogurt.pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import yogurt.db.Board;
import yogurt.db.Reply;

public class BoardDetail extends Page{
	JTextField t_title;
	JTextField t_writer;
	JTextArea t_detail;
	JButton bt_edit;
	JButton bt_del;
	JButton bt_list;
	
	PublicBoard publicBoard;
	Board board; //다른 메서드들에서, 상세보기 내용을 참고해야하므로 멤버변수로 빼둠
	
	JPanel p_form;
	JTextField c_title; //댓글 메시지
	JTextField c_writer; //
	JButton bt_regist;
	ReplyModel model;
	JTable ctable; //댓글테이블
	JScrollPane cscroll; //댓글 스크롤
	
	public BoardDetail(YogurtMain yogurtMain, PublicBoard publicBoard) {
		super(yogurtMain);
		this.publicBoard=publicBoard;
		
		t_title=new JTextField();
		t_writer=new JTextField();
		t_detail=new JTextArea();
		
		bt_edit=new JButton("수정");
		bt_del=new JButton("삭제");
		bt_list=new JButton("목록");
		
		//댓글입력폼
		p_form=new JPanel();
		c_title=new JTextField();
		c_writer=new JTextField();
		bt_regist=new JButton("등록");
		ctable=new JTable(model=new ReplyModel());
		cscroll=new JScrollPane(ctable);	
		//ctable.getColumnModel().getColumns(0)
		
		
		t_title.setPreferredSize(new Dimension(850,30));
		t_writer.setPreferredSize(new Dimension(850,30));
		t_detail.setPreferredSize(new Dimension(850,200));
		p_form.setPreferredSize(new Dimension(850,50));
		c_title.setPreferredSize(new Dimension(550,30));
		c_writer.setPreferredSize(new Dimension(150,30));
		cscroll.setPreferredSize(new Dimension(850,400));
		
		add(t_title);
		add(t_writer);
		add(t_detail);
		add(bt_edit);
		add(bt_del);
		add(bt_list);
		
		//댓글폼 부착
		p_form.add(c_title);
		p_form.add(c_writer);
		p_form.add(bt_regist);
		add(p_form);
		add(cscroll);
		
		
		//글목록 버튼과 리스너 연결
		bt_list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BoardList boardList=(BoardList)publicBoard.pages[publicBoard.BOARDLIST];
				boardList.getList();
				
				publicBoard.showHide(publicBoard.BOARDLIST);
			}
		});		
		
		//글수정 버튼과 리스너 연결
		bt_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(publicBoard, "수정하시겠습니까?")==JOptionPane.OK_OPTION) {
					
					//보고있던 news DTO 변경
					board.setTitle(t_title.getText()); //변경된 제목
					board.setWriter(t_writer.getText()); 
					board.setContent(t_detail.getText());
					
					
					int result=publicBoard.boardDAO.update(board); //상세보기 시 , 멤버변수로 빼놓았던 바로 그 객체
					if(result>0) {
						JOptionPane.showConfirmDialog(publicBoard, "수정완료");
						//목록 다시 갱신
						BoardList boardList=(BoardList)publicBoard.pages[publicBoard.BOARDLIST];
						boardList.getList();
					}
				}
			}
		});	
		//글삭제 버튼과 리스너 연결
		bt_del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(publicBoard, "삭제하시겠습니까?")==JOptionPane.OK_OPTION) {
					int result=publicBoard.boardDAO.delete(board.getBoard_id());
					if(result>0) { //삭제성공한다면
						//목록 다시 갱신
						BoardList boardList=(BoardList)publicBoard.pages[publicBoard.BOARDLIST];
						boardList.getList();
						
						//목록페이지 보여주기
						publicBoard.showHide(publicBoard.BOARDLIST);
					}
				}
			}
		});		

		//댓글 등록 버튼과 리스너 연결
		bt_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//등록 dao 호출
				
				//비어있는 dto를 생성하여, 댓글 1건 담아서 전달!
				Reply reply=new Reply(); //empty dto
				//foreign key 담기
				reply.setBoard(board); //현재 보고있는 글인 news 대입 //주의!!! 제일 중요!!!!
				//댓글메시지
				reply.setRtitle(c_title.getText());
				//댓글등록자명
				reply.setRwriter(c_writer.getText());
				
				int result=publicBoard.replyDAO.insert(reply);
				if(result>0) { //댓글 등록이 성공되면
					//댓글의 목록 갱신!
					//System.out.println("성공");
					getCommentsList();
					
					c_title.setText("");
					c_writer.setText("");
					
				}
			}
		});
	}
	
	//댓글 목록 가져오기
	public void getCommentsList() {
		List list=publicBoard.replyDAO.select(board); //딸려있는 댓글 목록 가져오기
		model.list=(ArrayList)list;
		ctable.updateUI();
	}
	
	//상세내용 가져오기
	public void getDetail(int board_id) {
		board=publicBoard.boardDAO.select(board_id);
		//내용 채우기
		t_title.setText(board.getTitle());
		t_writer.setText(board.getWriter());
		t_detail.setText(board.getContent());
	}
}
