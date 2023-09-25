package yogurt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

//여기 테이블로 해야하지않을까..? 여기서부터 벌써 api 적용되어야하네 
public class ConcertList extends Page{
	JLabel la_name;
	JLabel la_pList;
	JLabel la_oList;
	JLabel la_1;
	
	public ConcertList(YogurtMain yogurtMain) {
		super(yogurtMain);
		
		la_name=new JLabel("Concert List", JLabel.CENTER);
		la_pList=new JLabel("예매가능한 공연", JLabel.CENTER);
		la_oList=new JLabel("오픈 예정 공연", JLabel.CENTER);
		la_1=new JLabel("- xxx어쩌구공연", JLabel.CENTER);
		
		la_name.setOpaque(true);
		la_pList.setOpaque(true);
		la_oList.setOpaque(true);
		la_1.setOpaque(true);
		la_name.setBackground(Color.YELLOW);
		la_name.setForeground(Color.BLUE);
//		la_pList.setBackground(Color.YELLOW);
//		la_oList.setBackground(Color.YELLOW);
		la_pList.setForeground(Color.BLACK);
		la_oList.setForeground(Color.BLUE);
		
		setLayout(null);
		
		la_name.setFont(new Font("Dotum", Font.BOLD, 30));
		la_pList.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		la_oList.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		la_1.setFont(new Font("맑은 고딕", Font.ROMAN_BASELINE, 18));
		
		add(la_name);
		add(la_pList);
		add(la_oList);
		add(la_1);
		
		la_name.setBounds(300, 80, 300, 50);
		la_pList.setBounds(300, 180, 300, 50);
		la_1.setBounds(300, 220, 300, 50);
		la_oList.setBounds(300, 280, 300, 50);
		
		la_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				yogurtMain.showHide(YogurtMain.DETAIL);
				yogurtMain.showHideHam(YogurtMain.DETAIL);
			}
		});
		
	}
	
}
