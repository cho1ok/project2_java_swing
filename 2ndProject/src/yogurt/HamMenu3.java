package yogurt;

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

import yogurt.SideMenu;

public class HamMenu3 extends HamMenu{
	YogurtMain yogurtMain;
	
	SideMenu[] sideMenu=new SideMenu[4];	
	
	JLabel mypage;
	
	public HamMenu3(YogurtMain yogurtMain) {
		super(yogurtMain);
		this.yogurtMain=yogurtMain;

		mypage=new JLabel("My Page");
		mypage.setFont(new Font("Dotum", Font.BOLD, 30));
		add(mypage);
		mypage.setBounds(25, 100, 140, 45);
		
		createMenu();
	}
	
	public void createMenu() {		
		sideMenu[0]=new SideMenu("마이스케쥴", yogurtMain, yogurtMain.SCHEDULE);
		sideMenu[1]=new SideMenu("나의 ♥", yogurtMain, yogurtMain.HEART);
		sideMenu[2]=new SideMenu("나의 후기", yogurtMain, yogurtMain.EPILOGUE);
		sideMenu[3]=new SideMenu("open 게시판", yogurtMain, yogurtMain.PUBLICBOARD);

		for(int i=0; i<sideMenu.length; i++) {
			add(sideMenu[i]);
			sideMenu[i].setBounds(20, 200+55*i, 150, 45);
		}
		this.updateUI();		
	}
	
}
