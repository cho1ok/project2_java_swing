package yogurt;

import java.awt.Font;

import javax.swing.JLabel;

public class Heart extends Page{
	JLabel la_name;
	
	public Heart(YogurtMain yogurtMain) {
		super(yogurtMain);
		
		
		la_name=new JLabel("내 ♥ 목록", JLabel.CENTER);
		la_name.setOpaque(true);
		setLayout(null);
		la_name.setFont(new Font("굴림", Font.BOLD, 30));
		add(la_name);
		la_name.setBounds(10, 30, 900, 50);
	}
}
