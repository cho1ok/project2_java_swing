package yogurt;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class Page extends JPanel{
	YogurtMain yogurtMain;
	
	public Page(YogurtMain yogurtMain) {
		this.yogurtMain=yogurtMain;
		
		this.setPreferredSize(new Dimension(900,600));
	}
}
