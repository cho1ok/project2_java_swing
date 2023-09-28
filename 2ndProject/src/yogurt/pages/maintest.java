package yogurt.pages;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class maintest extends JFrame{

	JLabel la_name;
	
	public maintest() {

		la_name=new JLabel("나의 관람 후기", JLabel.CENTER);
		
		
		setSize(1000,700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	
	
	public static void main(String[] args) {
		new maintest();
	}
}
