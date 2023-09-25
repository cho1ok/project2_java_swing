package yogurt.pages;

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

import yogurt.db.JoinMember;
import yogurt.db.JoinMemberDAO;

public class JoinPage extends Page {
	JLabel la_join;
	
	JLabel la_name;
	JLabel la_id;
	JLabel la_pass;
	JLabel la_passCheck;
	JLabel la_mobile;
	JLabel la_birth;
	JLabel la_email;

	JTextField t_name;
	JTextField t_id;
	JTextField t_pass;
	JTextField t_passCheck;
	JTextField t_mobile;
	JTextField t_birth;
	JTextField t_email;
	
	JButton bt_idCheck;
	JButton bt_join;
	JButton bt_cancel;
	
	JLabel[] label=new JLabel[7];
	JTextField[] textIn=new JTextField[7];
	
	JoinMemberDAO joinMemberDAO;
	
	public JoinPage(YogurtMain yogurtMain) {
		super(yogurtMain);
		
		joinMemberDAO=new JoinMemberDAO();
		
		la_join=new JLabel("회원가입", JLabel.CENTER);
		la_name=new JLabel("이름", JLabel.LEFT);
		la_id=new JLabel("아이디", JLabel.LEFT);
		la_pass=new JLabel("비밀번호", JLabel.LEFT);
		la_passCheck=new JLabel("비밀번호 확인", JLabel.LEFT);
		la_mobile=new JLabel("휴대폰 번호", JLabel.LEFT);
		la_birth=new JLabel("생년월일", JLabel.LEFT);
		la_email=new JLabel("이메일", JLabel.LEFT);
		
		t_name=new JTextField();
		t_id=new JTextField();
		t_pass=new JTextField();
		t_passCheck=new JTextField();
		t_mobile=new JTextField();
		t_birth=new JTextField();
		t_email=new JTextField();
		
		bt_idCheck=new JButton("중복확인");
		bt_join=new JButton("저장");
		bt_cancel=new JButton("취소");

		setLayout(null);
		
		la_join.setFont(new Font("돋움", Font.BOLD, 30));
		add(la_join);
		la_join.setBounds(150, 60, 650, 50);		
		
		add(bt_idCheck);
		bt_idCheck.setBounds(750, 190, 90, 30);
		bt_idCheck.setBackground(Color.WHITE);
		add(bt_join);
		bt_join.setBounds(380, 450, 90, 30);
		bt_join.setBackground(Color.WHITE);
		add(bt_cancel);
		bt_cancel.setBounds(480, 450, 90, 30);
		bt_cancel.setBackground(Color.WHITE);
		
			
		formSetting();	
		
		this.setBackground(Color.WHITE);
		
		bt_idCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(JoinPage.this, "확인되었습니다.");
			}
		});
		bt_join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regist();
				yogurtMain.showHide(yogurtMain.LOGINPAGE); //회원가입이 완료되었습니다. 로그인하기. 로 페이지 따로 빼기?
			}
		});
		bt_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yogurtMain.showHide(yogurtMain.LOGOPAGE);
			}
		});
		
	}
	
	public void formSetting() {
		label[0]=la_name;
		label[1]=la_id;
		label[2]=la_pass;		
		label[3]=la_passCheck;		
		label[4]=la_mobile;		
		label[5]=la_birth;		
		label[6]=la_email;		
		
		for(int i=0; i<label.length; i++) {
			label[i].setFont(new Font("굴림", Font.BOLD, 16));
			add(label[i]);
			label[i].setBounds(180, 150+(i*40), 200, 30);
		}
		
		textIn[0]=t_name;
		textIn[1]=t_id;
		textIn[2]=t_pass;
		textIn[3]=t_passCheck;
		textIn[4]=t_mobile;
		textIn[5]=t_birth;
		textIn[6]=t_email;
		
		for(int i=0; i<label.length; i++) {
			add(textIn[i]);
			textIn[i].setBounds(320, 150+(i*40), 400, 30);
		}
	}
	
	public void regist() {
		JoinMember joinMember=new JoinMember();
		
		joinMember.setName(t_name.getText());
		joinMember.setId(t_id.getText());
		joinMember.setPass(t_pass.getText());
		joinMember.setMobile(Integer.parseInt(t_mobile.getText()));
		joinMember.setBirth(Integer.parseInt(t_birth.getText()));
		joinMember.setEmail(t_name.getText());
		
		int result=joinMemberDAO.insert(joinMember);
		if(result>0) {
			JOptionPane.showMessageDialog(this, "회원가입 완료!");
		}
	}
}
