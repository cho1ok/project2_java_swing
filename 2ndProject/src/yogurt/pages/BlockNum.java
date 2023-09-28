package yogurt.pages;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class BlockNum extends JLabel {
	QnAPage qnaPage;
	String n;

	public BlockNum(String n, QnAPage qnaPage) {
		super(n);
		this.n=n;
		this.qnaPage=qnaPage;
				
		this.setFont(new Font("Arial black", Font.BOLD, 20));
		
	}
}
