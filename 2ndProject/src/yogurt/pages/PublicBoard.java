package yogurt.pages;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import yogurt.db.Board;
import yogurt.db.BoardDAO;
import yogurt.db.ReplyDAO;
import yogurt.util.DBManager;

public class PublicBoard extends Page{	
	JLabel la_name;
	JPanel content;
	
	Page[] pages=new Page[5]; //프로그램에 사용될 페이지들을 담을 배열
	
	public static final int BOARDLIST=0;
	public static final int BOARDREGIST=1;
	public static final int BOARDDETAIL=2;
	public static final int QNAPAGE=3;
	public static final int QNAREGIST=4;
	
	DBManager dbManager=DBManager.getInstance();
	BoardDAO boardDAO=new BoardDAO();
	ReplyDAO replyDAO =new ReplyDAO();
	
	
	public PublicBoard(YogurtMain yogurtMain) {
		super(yogurtMain);
		
		
		la_name=new JLabel("게시판-못하겠다", JLabel.CENTER);
		la_name.setOpaque(true);
		setLayout(null);
		la_name.setFont(new Font("굴림", Font.BOLD, 30));
		add(la_name);
		la_name.setBounds(100, 30, 900, 50);
		
		content=new JPanel();
		add(content);
		
		//페이지 생성하기
		pages[0]=new BoardList(yogurtMain, this); //글목록
		pages[1]=new BoardRegist(yogurtMain,this); //글등록
		pages[2]=new BoardDetail(yogurtMain, this); //글보기
		pages[3]=new QnAPage(yogurtMain, this);
		pages[4]=new QnARegist(yogurtMain, this);
		
		//페이지 부착
		for(int i=0; i<pages.length;i++) {
			content.add(pages[i]);
		}
		
		
		content.setBounds(100, 100, 900, 600);
		
		
		
		setSize(900,700);
		setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		showHide(QNAPAGE);
		

		
		
		
	}
	
	public void showHide(int n) {
		
		for(int i=0; i<pages.length;i++) {
			if(n==i) {
				pages[i].setVisible(true);
			}else {
				pages[i].setVisible(false);
				
			}
		}
	}
}
