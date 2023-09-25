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

public class HamMenu1 extends HamMenu{
	YogurtMain yogurtMain;
	
	SideMenu[] sideMenu=new SideMenu[2];	
	
	public HamMenu1(YogurtMain yogurtMain) {
		super(yogurtMain);
		this.yogurtMain=yogurtMain;
		
		createMenu();
		
		
		
	}
	
	public void createMenu() {
		sideMenu[0]=new SideMenu("로그인", yogurtMain, yogurtMain.LOGINPAGE);
		sideMenu[1]=new SideMenu("회원가입", yogurtMain, yogurtMain.JOINPAGE);

		for(int i=0; i<sideMenu.length; i++) {
			add(sideMenu[i]);
			sideMenu[i].setBounds(20, 150+55*i, 140, 45);
		}
		this.updateUI();		
	}
	
}
