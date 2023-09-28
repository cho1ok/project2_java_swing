package yogurt.pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import yogurt.db.Board;

//페이징 처리를 위해, JTable을 사용하지 않고 손수 행을 재정의하여 사용해보자
public class Row extends JPanel{
	int no; //게시물 순번
	Board board; //하나의 row마다 하나의 dto
	
	//클릭하기위해 그리기말고 라벨로 바꿈
	JLabel la_no;
	JLabel la_title;
	JLabel la_writer;
	JLabel la_regdate;
	JLabel la_hit;
	
	QnAPage qnaPage;
	
	public Row(int no, Board board, QnAPage qnaPage) {
		this.no=no;
		this.board=board;
		this.qnaPage=qnaPage;
		
		//테두리 : Border
		Border border=new LineBorder(Color.GRAY);
		this.setBorder(border); //보더적용
		this.setPreferredSize(new Dimension(750, 40));
		
		//라벨 생성
		la_no=new JLabel(Integer.toString(no)); //글번호 채우기 (pk 아님!! 순번임!)
		la_title=new JLabel(board.getTitle());
		la_writer=new JLabel(board.getWriter());
		la_regdate=new JLabel(board.getRegdate());
		la_hit=new JLabel(Integer.toString(board.getHit()));
		
		//부착하기 전에 라벨들의 간격조정을 위한 너비를 설정
		la_no.setPreferredSize(new Dimension(30,35));
		la_title.setPreferredSize(new Dimension(400,35));
		la_writer.setPreferredSize(new Dimension(120,35));
		la_regdate.setPreferredSize(new Dimension(100,35));
		la_hit.setPreferredSize(new Dimension(30,35));

		//부착
		add(la_no);
		add(la_title);
		add(la_writer);
		add(la_regdate);
		add(la_hit);		
		
		//제목라벨과 리스너연결
		la_title.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, Integer.toString(no));
			}
			public void mouseEntered(MouseEvent e) {
				//마우스가 올려진 애들만 배경색 주기. 나머지는 다시 배경색을 빼야한다. showHide랑 원리 동일하겠다
				//패널 바깥쪽에다가 전체 패널 조작하고 싶으니까, 큐엔에이 페이지에 메서드 ㄱㄱ

				//row인 내가 현재 배열의 몇번째에 들어있는지 먼저 역조사
				int index=qnaPage.rows.indexOf(Row.this);
				//하이라이트!
				qnaPage.showLight(index);
			}
		});
		
		
	}
	
	
	/*
	protected void paintComponent(Graphics g) {
		Graphics2D g2=(Graphics2D)g;		
		//순번
		g2.drawString(Integer.toString(no), 5, 10);
		//제목
		g2.drawString(reBoard.getTitle(), 100, 10);
		//작성자
		g2.drawString(reBoard.getWriter(), 450, 10);
		//등록일
		g2.drawString(reBoard.getRegdate(), 550, 10);
		//조회수
		g2.drawString(Integer.toString(reBoard.getHit()), 650, 10);		
	}
	*/

}
