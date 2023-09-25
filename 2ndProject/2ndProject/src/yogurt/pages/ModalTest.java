package yogurt.pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ModalTest extends JDialog {
	JLabel la;
	
	JButton bt = new JButton("보러 가기");
	JButton bt2 =new JButton("확인");
	
	YogurtMain yogurtMain;
	public ModalTest(YogurtMain yogurtMain, String content, int cy, int cm, int cd) {
		super(yogurtMain, "  나의 ♥ 스케쥴", true);
		
		la=new JLabel("<html><body style='text-align:center;'>"+cy+"."+cm+"."+cd+"<br/><br/>"+content+"</body></html>");

		la.setPreferredSize(new Dimension(180,220));
		la.setOpaque(true);
		la.setBackground(Color.WHITE);
		
		bt.setBackground(Color.WHITE);
		bt2.setBackground(Color.WHITE);

		setLayout(new FlowLayout());
		add(la);
		add(bt);
		add(bt2);
		setBounds(500, 500, 200, 300);


		bt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				yogurtMain.showHide(YogurtMain.HEART);
				yogurtMain.showHideHam(YogurtMain.HEART);
			}
		});
	}

}
