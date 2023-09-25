package yogurt;

import java.awt.Font;

import javax.swing.JLabel;

public class Epilogue extends Page{

	JLabel la_name;
	
	public Epilogue(YogurtMain yogurtMain) {
		super(yogurtMain);
		
		
		la_name=new JLabel("나의 관람 후기", JLabel.CENTER);
		la_name.setOpaque(true);
		setLayout(null);
		la_name.setFont(new Font("굴림", Font.BOLD, 30));
		add(la_name);
		la_name.setBounds(100, 30, 900, 50);
	}
}
