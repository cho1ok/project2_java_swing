package yogurt.pages;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LogoPage extends Page{
	JLabel logo;	
	
	
	public LogoPage(YogurtMain yogurtMain) {
		super(yogurtMain);
		
		try {
			URL url=new URL("https://imgur.com/1ibpX3p.png");
			logo=new JLabel(new ImageIcon(url));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		setLayout(null);
		add(logo);
		logo.setBounds(0, 0, 1000, 700);		
		
	}
}
