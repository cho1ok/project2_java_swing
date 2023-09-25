package yogurt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginPage extends Page {
	JPanel p_drawer;
	
	JPanel p_north;
	
	JPanel p_center;
	JPanel p_id;
	JLabel la_id;
	JTextField t_id;
	JPanel p_pass;
	JLabel la_pass;
	JTextField t_pass;
	JPanel p_bt;
	JButton bt_login;
	JButton bt_join;
	
	
	HamMenu hamMenu;
	
	public LoginPage(YogurtMain yogurtMain) {
		super(yogurtMain);
		
		p_drawer=new JPanel();
		
		p_north=new JPanel();
	
		p_center=new JPanel();
		
		p_id=new JPanel();
		la_id=new JLabel("ID");
		t_id=new JTextField();
		p_pass=new JPanel();
		la_pass=new JLabel("Password");
		t_pass=new JTextField();
		
		p_bt=new JPanel();
		bt_login=new JButton("로그인");
		bt_join=new JButton("회원가입");
		
		p_north.setPreferredSize(new Dimension(600,150));
		p_center.setPreferredSize(new Dimension(600,600));
		p_id.setPreferredSize(new Dimension(800,30));
		p_pass.setPreferredSize(new Dimension(800,80));
		p_bt.setPreferredSize(new Dimension(800,30));
		la_id.setPreferredSize(new Dimension(70,30));
		la_pass.setPreferredSize(new Dimension(70,30));
		t_id.setPreferredSize(new Dimension(300,30));
		t_pass.setPreferredSize(new Dimension(300,30));
		
		//createHam();
		
		add(p_north, BorderLayout.NORTH);
		p_center.add(p_id);
		p_id.add(la_id);
		p_id.add(t_id);
		p_center.add(p_pass);
		p_pass.add(la_pass);
		p_pass.add(t_pass);
		p_center.add(p_bt);
		p_bt.add(bt_login);
		p_bt.add(bt_join);
		add(p_center);
		
		
		//this.setBackground(Color.black);

		
		bt_join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yogurtMain.showHide(yogurtMain.JOINPAGE);
			}
		});
	}
	
	public void createHam() {
		//p_drawer.setBackground(Color.YELLOW);
		//p_drawer.setPreferredSize(new Dimension(200,700));
		p_drawer.setLayout(null);
		p_drawer.setBounds(-160, 0, 200, 700);
		
		//hamMenu=new HamMenu(yogurtMain);
		p_drawer.add(new HamMenu(yogurtMain));
		add(p_drawer);
		
		p_drawer.updateUI();
	}
	
//	public void showHideHam(int n) {
//
//		if(n==yogurtMain.LOGINPAGE) {
//			hamMenu.setVisible(true);
//		}else {
//			hamMenu.setVisible(false);
//		}
//	
//	}
	
}
