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

public class HamMenu3 extends HamMenu{
	YogurtMain yogurtMain;
	
	SideMenu[] sideMenu=new SideMenu[4];	

	JLabel main;
	JLabel mypage;
	
	public HamMenu3(YogurtMain yogurtMain) {
		super(yogurtMain);
		this.yogurtMain=yogurtMain;

		main=new JLabel("Home");
		main.setFont(new Font("Dotum", Font.BOLD, 30));
		add(main);
		main.setBounds(25, 130, 140, 45);
		main.setForeground(new Color(209, 190, 208));

		mypage=new JLabel("My Page");
		mypage.setFont(new Font("Dotum", Font.BOLD, 30));
		add(mypage);
		mypage.setBounds(25, 195, 140, 45);
		mypage.setForeground(new Color(209, 190, 208));
		
		createMenu();
		
		main.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				yogurtMain.showHide(YogurtMain.BRIDGEPAGE);
				yogurtMain.showHideHam(YogurtMain.BRIDGEPAGE);
			}
		});
		mypage.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				yogurtMain.showHide(YogurtMain.MYPAGE);
				yogurtMain.showHideHam(YogurtMain.MYPAGE);
			}
		});
		
	}
	
	public void createMenu() {		
		sideMenu[0]=new SideMenu("마이스케쥴", yogurtMain, yogurtMain.SCHEDULE);
		sideMenu[1]=new SideMenu("나의 ♥", yogurtMain, yogurtMain.HEART);
		sideMenu[2]=new SideMenu("나의 후기", yogurtMain, yogurtMain.EPILOGUE);
		sideMenu[3]=new SideMenu("open 게시판", yogurtMain, yogurtMain.PUBLICBOARD);

		for(int i=0; i<sideMenu.length; i++) {
			add(sideMenu[i]);
			sideMenu[i].setBounds(35, 260+50*i, 150, 30);
			sideMenu[i].setFont(new Font("Dotum", Font.BOLD, 20));
		}
		this.updateUI();		
	}
	
}
