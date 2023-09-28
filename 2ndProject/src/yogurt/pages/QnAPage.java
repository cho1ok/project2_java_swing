package yogurt.pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import yogurt.db.Board;
import yogurt.util.PagingManager;

public class QnAPage extends Page{
	JPanel p_list; //목록 패널들 (Row)이 불여질 영역
	JPanel p_num; //페이지 번호가 붙여질 영역 (각각을 라벨의 setBounds로?)
	JButton bt_regist;
	
	//Row[] rows; //컬렉션으로 교체하자
	ArrayList<Row> rows=new ArrayList<Row>(); //사이즈 0
	ArrayList<PageNum> pageNums=new ArrayList<PageNum>(); //사이즈 0
	
	/* 클래스 따로 빼줌. 재활용성 높
	//*수업주제*
	//페이징 처리란? 데이터를 분할하여 출력하는 기법 (산수계산에 의함)
	//페이징 처리 로직을 개발하는 과정에서는 db연동은 필수가 아니다
	int totalRecord=1126; //총 레코드 수. 일단 가라로
	int pageSize=10; //한 페이지당 보여질 레코드 수
	int totalPage; //총 페이지 수
	int blockSize=10; //블럭당 보여질 페이지 수
	int currentPage=1; //현재 유저가 보고있는 페이지
	
	//페이징 처리 계산 메서드
	public void init() {
		totalPage=(int)Math.ceil((float)totalRecord/pageSize);
		System.out.println(totalPage);
	}
	*/
	PagingManager pagingManager=new PagingManager(); //페이징 처리 객체 보유
	//ArrayList<ReBoard> boardList=new ArrayList<ReBoard>(); //reboard dto가 들어있는 게시물
	ArrayList<Board> boardList=new ArrayList<Board>(); 
	PublicBoard publicBoard;
	
	public QnAPage(YogurtMain yogurtMain, PublicBoard publicBoard) {
		super(yogurtMain);
		this.publicBoard=publicBoard;
		
		p_list=new JPanel();
		p_num=new JPanel();
		bt_regist=new JButton("글등록");
		
		p_list.setPreferredSize(new Dimension(770,455));
		p_num.setPreferredSize(new Dimension(770,50));
		p_list.setBackground(Color.WHITE);
		p_num.setBackground(Color.WHITE);
		
		add(p_list);
		add(p_num);
		add(bt_regist);
		
		/*
		pagingManager.init(boardList, 1); //db레코드를 담은 리스트, 현재페이지
		createRow();
		createPageNum();
		*/
		getList();
		
		//글쓰기버튼과 리스너연결
		bt_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				publicBoard.showHide(publicBoard.QNAREGIST);
			}
		});
	}
	
	//row 생성하기
	public void createRow() {
		//rows=new Row[10];
		/*
		for(int i=0; i<rows.length;i++) {
			//임시적으로 dto 넘기기 (추후 db 연동할거임)
			ReBoard dto=new ReBoard();
			dto.setTitle("48분에 식사하세요");
			dto.setWriter("zino");
			dto.setRegdate("2022-12-25");
			dto.setHit(45);
			
			rows[i]=new Row(i, dto);	
			add(rows[i]);
		}
		*/
		
		int num=pagingManager.getNum();
		
		for(int i=0; i<pagingManager.getPageSize();i++) {
			if(num<1)break;
			
			//임시적으로 dto 넘기기 (추후 db 연동할거임)
			Board dto=new Board();
			dto.setTitle("48분에 식사하세요");
			dto.setWriter("zino");
			dto.setRegdate("2022-12-25");
			dto.setHit(45);
			
			Row row=new Row(num--, dto, this);	
			rows.add(row); //리스트에 추가
			p_list.add(row); //패널에 부착
		}
		this.updateUI();
	}
	
	//페이지 번호 생성
	public void createPageNum() {
		//이전버튼
		BlockNum prev=new BlockNum("<", this);
		//PageNum prev=new PageNum("<", this); //.java 따로 뻄
		p_num.add(prev); // 패널에 부착
		prev.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//이전블럭으로 가기위한 currentPage값 계산식
				pagingManager.setCurrentPage(pagingManager.getFirstPage()-1); 
				getList();
			}
		});
		
		//페이지 번호
		for(int i=pagingManager.getFirstPage(); i<=pagingManager.getLastPage(); i++) { //블럭 사이즈로 한정 짓는다
			if(i>pagingManager.getTotalPage())break; //내가 가진 총 페이지 수를 넘어서면 반복문 빠져나오자
			
			PageNum pageNum=new PageNum(Integer.toString(i), this);
			p_num.add(pageNum); //하단 패널에 라벨 부착!
			pageNums.add(pageNum); //리스트에 담기
		}

		//다음버튼
		BlockNum next=new BlockNum(">", this);
		//PageNum next=new PageNum(">", this);
		p_num.add(next); // 패널에 부착
		next.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//다음 블럭으로 가기위한 currentPage값 계산식
				pagingManager.setCurrentPage(pagingManager.getLastPage()+1); 
				getList();
			}
		});
		
		p_num.updateUI(); //새고
	}
	
	public void getList() {
		//기존에 붙어있던 컴포넌트들을 모두 제거.하고 난 다음에 붙여야
		p_list.removeAll();
		p_num.removeAll();
		
		//쌓여있는 리스트의 요소들도 제거
		rows.removeAll(rows);
		pageNums.removeAll(pageNums);
		
		pagingManager.init(boardList, pagingManager.getCurrentPage()); //db레코드를 담은 리스트, 현재페이지
		createRow();
		createPageNum();
	}
	
	//row에 대한 하이라이트 효과내기
	public void showLight(int n) {
		//모든 row를 대상으로 하여
		for(int i=0; i<rows.size(); i++) {
			Row row=rows.get(i); //리스트에서 요소 하나 꺼내기
			if(i==n) {
				row.setBackground(Color.YELLOW); //배경색 주기
			}else {
				row.setBackground(Color.WHITE); //다시 돌려놓기
			}
		}
	}
	
	//선택한 페이지에 대한 이펙트
	public void activePage(String n) {
		for(int i=0; i<pageNums.size();i++) {
			PageNum pageNum=pageNums.get(i);
			
			if(pageNum.getText().equals(n)) { //넘겨받은 n과 같은 경우만 텍스트 색상 변경
				pageNum.setForeground(Color.BLUE);
				pageNum.setFont(new Font("Arial black",Font.BOLD, 25 ));
			}else {
				pageNum.setForeground(Color.BLACK);
				pageNum.setFont(new Font("Arial black",Font.BOLD, 20 ));
				
			}
		}
	}
	
}
