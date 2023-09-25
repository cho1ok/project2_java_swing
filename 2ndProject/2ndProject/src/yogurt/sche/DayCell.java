package yogurt.sche;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class DayCell extends Cell{

	public DayCell(String title, String content, int fontSize, int x, int y) {
		super(title, content, fontSize, x, y);
	}

	protected void paintComponent(Graphics g) {
		Graphics2D g2=(Graphics2D)g;
		
		g2.setColor(new Color(121, 86, 149));
		g2.fillRect(0, 0, 150, 80);
		
		g2.setColor(Color.WHITE);
		Font font=new Font("verdana", Font.BOLD|Font.ITALIC, fontSize);
		g2.setFont(font);
		g2.drawString(title, x, y);		
	}
}
