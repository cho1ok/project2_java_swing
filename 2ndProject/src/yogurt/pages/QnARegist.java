package yogurt.pages;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//글 등록폼
public class QnARegist extends Page{
	JTextField t_title;
	JTextField t_writer;
	JTextArea t_detail;
	JButton bt_regist;
	JButton bt_list;
	
	PublicBoard publicBoard;
	
	public QnARegist(YogurtMain yogurtMain, PublicBoard publicBoard) {
		super(yogurtMain);
		this.publicBoard=publicBoard;
		
		t_title=new JTextField();
		t_writer=new JTextField();
		t_detail=new JTextArea();
		
		bt_regist=new JButton("등록");
		bt_list=new JButton("목록");
		
		t_title.setPreferredSize(new Dimension(740,30));
		t_writer.setPreferredSize(new Dimension(740,30));
		t_detail.setPreferredSize(new Dimension(740,450));
		
		add(t_title);
		add(t_writer);
		add(t_detail);
		add(bt_regist);
		add(bt_list);
		
		//등록버튼과 리스너 연결
		bt_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regist();
			}
		});
	}
	
	public void regist() {
		
	}
}
