package yogurt.pages;

import java.awt.Font;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MyPage extends Page{
	JLabel la_name;
	JLabel logo;	
	
	public MyPage(YogurtMain yogurtMain) {
		super(yogurtMain);		
		
//		la_name=new JLabel("My Page", JLabel.CENTER);
//		la_name.setOpaque(true);
		setLayout(null);
//		la_name.setFont(new Font("굴림", Font.BOLD, 30));
//		add(la_name);
//		la_name.setBounds(100, 30, 900, 50);
		
		try {
			URL url=new URL("https://imgur.com/aHMm0Uu.png");
			logo=new JLabel(new ImageIcon(url));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		add(logo);
		logo.setBounds(0, 0, 1000, 700);		
	}
}
