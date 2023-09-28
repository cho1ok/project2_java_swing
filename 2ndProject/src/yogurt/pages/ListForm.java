package yogurt.pages;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ListForm extends Page{
	YogurtMain yogurtMain;
	
	JLabel la_name;
	JLabel la_pList;
	JLabel la_oList;
	JLabel la_1;

	public ListForm(YogurtMain yogurtMain) {
		super(yogurtMain);
		
		la_name=new JLabel("", JLabel.CENTER);
		la_pList=new JLabel("예매가능한 공연", JLabel.CENTER);
		la_oList=new JLabel("오픈 예정 공연", JLabel.CENTER);
		
		la_name.setOpaque(true);
		la_pList.setOpaque(true);
		la_oList.setOpaque(true);
		la_name.setBackground(new Color(215,181,216));
		la_name.setForeground(Color.WHITE);
		la_pList.setForeground(Color.BLACK);
		la_oList.setForeground(new Color(167, 161, 219));
		
		setLayout(null);
		
		la_name.setFont(new Font("Dotum", Font.BOLD, 30));
		la_pList.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		la_oList.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
		add(la_name);
		add(la_pList);
		add(la_oList);
		
		la_name.setBounds(370, 50, 300, 50);
		la_pList.setBounds(370, 120, 300, 50);
		la_oList.setBounds(370, 450, 300, 50);
	}
}
