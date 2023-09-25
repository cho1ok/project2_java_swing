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
			URL url=new URL("https://cdn1.iconfinder.com/data/icons/lifestyle-entertainment-vol-3/512/movie_ticket_film_cinema-256.png");
			logo=new JLabel(new ImageIcon(url));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		setLayout(null);
		add(logo);
		logo.setBounds(80, 60, 800, 500);
		
		
	}
}
