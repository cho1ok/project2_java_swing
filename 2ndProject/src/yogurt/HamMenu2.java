package yogurt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import yogurt.SideMenu;

public class HamMenu2 extends HamMenu{
	YogurtMain yogurtMain;
	
	SideMenu[] sideMenu=new SideMenu[5];	
	
	public HamMenu2(YogurtMain yogurtMain) {
		super(yogurtMain);
		this.yogurtMain=yogurtMain;

		createMenu();
	}
	
	public void createMenu() {		
		sideMenu[0]=new SideMenu("콘서트", yogurtMain, yogurtMain.CONCERTLIST);
		sideMenu[1]=new SideMenu("영화", yogurtMain, yogurtMain.CONCERTLIST);
		sideMenu[2]=new SideMenu("전시", yogurtMain, yogurtMain.CONCERTLIST);
		sideMenu[3]=new SideMenu("관람", yogurtMain, yogurtMain.CONCERTLIST);
		sideMenu[4]=new SideMenu("마이페이지", yogurtMain, yogurtMain.SCHEDULE);

		for(int i=0; i<sideMenu.length; i++) {
			add(sideMenu[i]);
			sideMenu[i].setBounds(20, 150+55*i, 140, 45);
		}
		this.updateUI();		
	}
	
}
