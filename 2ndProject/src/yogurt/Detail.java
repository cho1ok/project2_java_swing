package yogurt;

import java.awt.Font;

import javax.swing.JLabel;

//모든 디테일정보창들의 최상위 객체
public class Detail extends Page{
	//들어가야하는거 : 제목 폰트사이즈 설정, 포스터 넣는 란, 기본정보 출력란, 버튼, 하트찍기, 게시판 이동, 상세정보란
	//
	
	
	JLabel la_name;
	
	public Detail(YogurtMain yogurtMain) {
		super(yogurtMain);
		
		la_name=new JLabel("xxx 리미티드 에디션", JLabel.CENTER);
		la_name.setOpaque(true);
		setLayout(null);
		la_name.setFont(new Font("궁서", Font.BOLD, 30));
		add(la_name);
		la_name.setBounds(300, 30, 300, 50);
		
	}
}
