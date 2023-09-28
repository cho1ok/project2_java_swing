package yogurt.pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BoardList extends Page{
	JTable table;
	JScrollPane scroll;
	JButton bt_regist; //글쓰기 버튼

	PublicBoard publicBoard;
	BoardModel model;
	
	
	public BoardList(YogurtMain yogurtMain, PublicBoard publicBoard) {
		super(yogurtMain);
		this.publicBoard=publicBoard;
		
		table=new JTable(model=new BoardModel());
		scroll=new JScrollPane(table);
		bt_regist=new JButton("글등록");
		
		
		//scroll.setPreferredSize(new Dimension(870,600));
		add(scroll);
		add(bt_regist);
		
		setLayout(null);
		scroll.setBounds(50, 50, 850, 300);
		bt_regist.setBounds(400, 400, 80, 30);
		bt_regist.setBackground(Color.white);
		
		
		getList();
		
		
		//글등록 버튼과 리스너 연결
		bt_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				publicBoard.showHide(PublicBoard.BOARDREGIST);
			}
		});		
		
		//테이블에 마우스 리스너 연결
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//제목을 클릭하면. 으로 제한을 주자
				if(table.getSelectedColumn()==1) {
					//클릭시 상세보기 페이지 보여주기
					publicBoard.showHide(PublicBoard.BOARDDETAIL);
					BoardDetail boardDetail=(BoardDetail)publicBoard.pages[PublicBoard.BOARDDETAIL];
					//사용자가 어디를 클릭하던, 0번째 컬럼의 값을 가져오자
					String value=(String)table.getValueAt( table.getSelectedRow() , 0); //내가 선택한 층의 0번째 호수
					System.out.println("선택한 board_id값은 "+value);
					
					boardDetail.getDetail(Integer.parseInt(value));
					//클릭시 상세페이지 보여주기
					//여기 필기 이상함 정리하자
					
					boardDetail.getCommentsList();
					
					
				}
			}
		});
	}
	
	//조회
	public void getList() {		
		
		model.boardList=publicBoard.boardDAO.selectAll();
		table.updateUI();
		
	}
}