package yogurt.hams;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import yogurt.pages.YogurtMain;

public class SideMenu extends JLabel{
	YogurtMain yogurtMain;
	int targetPage;
	
	public SideMenu(String label, YogurtMain yogurtMain, int targetPage) {
		super(label);
		this.yogurtMain=yogurtMain;
		this.targetPage=targetPage;
		
		
		setFont(new Font("Dotum", Font.BOLD, 25));
		setForeground(Color.WHITE);
		
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				yogurtMain.showHide(targetPage);
				yogurtMain.showHideHam(targetPage);
			}
		});
	}
}
