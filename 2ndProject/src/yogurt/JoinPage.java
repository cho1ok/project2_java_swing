package yogurt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JoinPage extends Page {
	JLabel la_join;
	
	JLabel la_name;
	JTextField t_name;
	JLabel la_id;
	JTextField t_id;
	JLabel la_pass;
	JTextField t_pass;
	
	
	
	
	JButton bt_login;
	JButton bt_join;
	
	JLabel[] label=new JLabel[3];
	JTextField[] textIn=new JTextField[3];
	
	public JoinPage(YogurtMain yogurtMain) {
		super(yogurtMain);
		
		la_join=new JLabel("회원가입", JLabel.CENTER);
		la_name=new JLabel("이름", JLabel.LEFT);
		t_name=new JTextField();
		la_id=new JLabel("아이디", JLabel.LEFT);
		t_id=new JTextField();
		la_pass=new JLabel("비밀번호", JLabel.LEFT);
		t_pass=new JTextField();
		
		
		
		
		

		bt_login=new JButton("로그인");
		bt_join=new JButton("회원가입 등록");

		setLayout(null);
		
		la_join.setFont(new Font("돋움", Font.BOLD, 30));
		add(la_join);
		la_join.setBounds(150, 10, 650, 50);		
		
		
		
//		la_name.setFont(new Font("굴림", Font.BOLD, 18));
//		add(la_name);
//		la_name.setBounds(150, 100, 50, 30);
//		add(t_name);
//		t_name.setBounds(250, 100, 500, 30);
//		
//		la_id.setFont(new Font("굴림", Font.BOLD, 18));
//		add(la_id);
//		la_id.setBounds(150, 140, 50, 30);
//		add(t_id);
//		t_id.setBounds(250, 140, 500, 30);
//		
		formSetting();
		
		
		
		
		
		this.setBackground(Color.WHITE);
		
		bt_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yogurtMain.showHide(yogurtMain.LOGINPAGE);
			}
		});
		bt_join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(JoinPage.this, "회원가입 완료!");
			}
		});
		
	}
	
	public void formSetting() {
		label[0]=la_name;
		label[1]=la_id;
		label[2]=la_pass;		
		
		for(int i=0; i<label.length; i++) {
			label[i].setFont(new Font("굴림", Font.BOLD, 16));
			add(label[i]);
			label[i].setBounds(150, 100+(i*40), 80, 30);
		}
		
		textIn[0]=t_name;
		textIn[1]=t_id;
		textIn[2]=t_pass;
		
		for(int i=0; i<label.length; i++) {
			add(textIn[i]);
			textIn[i].setBounds(250, 100+(i*40), 500, 30);
		}
	}
}
