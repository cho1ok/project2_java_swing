package yogurt;

import java.awt.Font;

import javax.swing.JLabel;

public class Schedule extends Page{

	JLabel la_name;
	
	public Schedule(YogurtMain yogurtMain) {
		super(yogurtMain);
		
		la_name=new JLabel("나의 공연 스케쥴표 한눈에 보기", JLabel.CENTER);
		la_name.setOpaque(true);
		setLayout(null);
		la_name.setFont(new Font("굴림", Font.BOLD, 30));
		add(la_name);
		la_name.setBounds(30, 30, 900, 50);
	}
}
