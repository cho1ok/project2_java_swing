package yogurt;

import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BridgePage extends Page{
	JLabel logo;	

	public BridgePage(YogurtMain yogurtMain) {
		super(yogurtMain);
		
		try {
			URL url=new URL("https://cdn0.iconfinder.com/data/icons/nasa-1/64/Planet-space-astronomy-galaxy-512.png");
			logo=new JLabel(new ImageIcon(url));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		add(logo);
	}
}
