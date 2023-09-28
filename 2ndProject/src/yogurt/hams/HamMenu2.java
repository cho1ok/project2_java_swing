package yogurt.hams;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import yogurt.hams.SideMenu;
import yogurt.pages.YogurtMain;

public class HamMenu2 extends HamMenu{
	YogurtMain yogurtMain;
	JLabel main;
	SideMenu[] sideMenu=new SideMenu[3];	
	
	public HamMenu2(YogurtMain yogurtMain) {
		super(yogurtMain);
		this.yogurtMain=yogurtMain;

		main=new JLabel("Home");
		main.setFont(new Font("Dotum", Font.BOLD, 30));
		add(main);
		main.setBounds(25, 190, 140, 45);
		main.setForeground(new Color(209, 190, 208));
		
		main.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				yogurtMain.showHide(YogurtMain.BRIDGEPAGE);
				yogurtMain.showHideHam(YogurtMain.BRIDGEPAGE);
			}
		});
		
		
		createMenu();
	}
	
	public void createMenu() {		
		sideMenu[0]=new SideMenu("전시 관람", yogurtMain, yogurtMain.CONCERTLIST);
		sideMenu[1]=new SideMenu("콘서트", yogurtMain, yogurtMain.EXHIBITLIST);
		sideMenu[2]=new SideMenu("마이페이지", yogurtMain, yogurtMain.MYPAGE);

		for(int i=0; i<sideMenu.length; i++) {
			add(sideMenu[i]);
			sideMenu[i].setBounds(35, 260+50*i, 150, 30);
			sideMenu[i].setFont(new Font("Dotum", Font.BOLD, 25));
		}
		this.updateUI();		
	}
	
}
