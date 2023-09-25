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

public class HamMenu extends JPanel{
	YogurtMain yogurtMain;
	
	JPanel p_drawer; 
	JLabel la_ham; 

	double a=0.08;
	double targetX=-160;
	double x=-200;	
	boolean fold=true;	
	
	Thread loopThread;
	
	public HamMenu(YogurtMain yogurtMain) {
		this.yogurtMain=yogurtMain;
		
		p_drawer=new JPanel();
		
		try {
			URL url=new URL("https://cdn0.iconfinder.com/data/icons/user-interface-2063/24/UI_Essential_icon_expanded-01-48.png");
			la_ham=new JLabel(new ImageIcon(url));
			
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} 
		
		loopThread=new Thread() {
			public void run() {
				while(true) {
					tick();
					render();
					try {
						Thread.sleep(5); //fps
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		loopThread.start();
	
		this.setBackground(new Color(100, 0, 100, 1));
		this.setBounds((int)x, 0, 200, 700);
		this.setLayout(null);
		
		la_ham.setBounds(160, 0, 40, 40);
		add(la_ham);

		la_ham.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(fold) {
					targetX=0;
				}else {
					targetX=-160;
				}
				fold=!fold;
			}
		});
	}
	
	public void tick() {
		x=x+a*(targetX-x);
	}	
	public void render() {
		this.setBounds((int)x, 0, 200, 700);
		this.updateUI();
	}
	
}
