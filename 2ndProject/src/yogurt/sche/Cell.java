package yogurt.sche;

import javax.swing.JPanel;

public class Cell extends JPanel{
	String title; //날짜
	String content; //메모 내용
	
	int fontSize;
	int x, y;	
	
	public Cell(String title, String content, int fontSize, int x, int y) {
		this.title=title;
		this.content=content;
		this.fontSize=fontSize;
		this.x=x;
		this.y=y;
	}
}