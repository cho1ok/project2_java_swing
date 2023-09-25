package yogurt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import yogurt.util.DBManager;

//your' get 도경 아이디어
public class YogurtMain extends JFrame{	
	DBManager dbManager=DBManager.getInstance();
	
	JPanel p_container=new JPanel();
	Page[] page=new Page[10];
	public static final int LOGOPAGE=0;
	public static final int LOGINPAGE=1;
	public static final int JOINPAGE=2;
	public static final int BRIDGEPAGE=3;
	public static final int CONCERTLIST=4;
	public static final int DETAIL=5;
	public static final int SCHEDULE=6;
	public static final int HEART=7;
	public static final int EPILOGUE=8;
	public static final int PUBLICBOARD=9;
	
	HamMenu[] ham=new HamMenu[3];
	
	public YogurtMain() {
		
		createHam();
		add(p_container);
		
		createPage();
		showHide(JOINPAGE);
		showHideHam(JOINPAGE);
		
		setSize(1000,700);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dbManager.release(dbManager.getConnection());
				System.exit(0);
			}
		});

	}
	
	public void createPage() {
		page[0]=new LogoPage(this);
		page[1]=new LoginPage(this);
		page[2]=new JoinPage(this);
		page[3]=new BridgePage(this);
		page[4]=new ConcertList(this);
		page[5]=new Detail(this);
		page[6]=new Schedule(this);
		page[7]=new Heart(this);
		page[8]=new Epilogue(this);
		page[9]=new PublicBoard(this);
		
		for(int i=0; i<page.length; i++) {
			p_container.add(page[i]);
		}
	}
	
	public void showHide(int n) {
		for(int i=0; i<page.length; i++) {
			if(i==n) {
				page[i].setVisible(true);
			}else {
				page[i].setVisible(false);
			}
		}
	}	
	
	public void createHam() {	
		ham[0]=new HamMenu1(this);
		ham[1]=new HamMenu2(this);	
		ham[2]=new HamMenu3(this);	
		
		for(int i=0; i<ham.length; i++) {
			add(ham[i]);
		}	
	}
	
	public void showHideHam(int n) {
		for(int i=0; i<page.length; i++) {
			if(i==n) {
				if( n<=2 ) { 
					ham[0].setVisible(true);
					ham[1].setVisible(false);	
					ham[2].setVisible(false);	
				}else if(n>2 & n<=5 ){
					ham[0].setVisible(false);	
					ham[1].setVisible(true);				
					ham[2].setVisible(false);	
				}else if(n>5 & n<=9){ 
					ham[0].setVisible(false);	
					ham[1].setVisible(false);				
					ham[2].setVisible(true);	
				}				
			}
		}	
	}
	

	
	public static void main(String[] args) {
		new YogurtMain();
	}

}
