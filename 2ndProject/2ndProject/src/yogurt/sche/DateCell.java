package yogurt.sche;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import yogurt.pages.ModalTest;
import yogurt.pages.YogurtMain;

public class DateCell extends Cell {
	Color color = Color.WHITE;
	String title;
	
	Schedule schedule;
	YogurtMain yogurt;
	
	Cal cal;
	
	public DateCell(YogurtMain yogurt, Schedule schedule, String title, String content, int fontSize, int x, int y) {
		super(title, content, fontSize, x, y);
		
		this.yogurt=yogurt;
		this.schedule=schedule;
		this.title=title;

		Border border = new LineBorder(Color.DARK_GRAY);
		setBorder(border);		
	}

	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.clearRect(0, 0, 120, 120);
		// 배경색
		g2.setColor(color);
		g2.fillRect(0, 0, 150, 100);
		
		Font font=new Font("돋움", Font.PLAIN, fontSize);
		g2.setFont(font);
		g2.setColor(Color.DARK_GRAY);
		
		g2.drawString(title, x, y); 
		g2.drawString(content, x-80, y+20); 

	}
	
	public void popUp(String content, int cy, int cm, int cd) {
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//System.out.println("1: "+cal.getDiary_idx());
				if (color != Color.WHITE) {			

					try {
						String[] buttons= {"일정 삭제", "스케쥴 보기","취소"};
						URL url=new URL("https://cdn2.iconfinder.com/data/icons/circle-icons-1/64/compose-48.png");					
						ImageIcon icon = new ImageIcon(url);
						
						int re= JOptionPane.showOptionDialog(schedule, "삭제하시겠습니까?", "나의 스케쥴", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, icon, buttons , "스케쥴 보기");
						
						if(re==JOptionPane.YES_OPTION) {		
							//System.out.println("3: "+cal.getDiary_idx());
							int rere=JOptionPane.showConfirmDialog(schedule, "삭제하시겠습니까?", "", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, icon );
							
							if(rere==JOptionPane.YES_OPTION) {
								schedule.delCell(cal);								
							}							
							
						}else if(re==JOptionPane.NO_OPTION) {
							ModalTest modalTest=new ModalTest(yogurt , content, cy, cm, cd);
							modalTest.setVisible(true);	
						}					
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}					
				}else {

				}
				repaint();
			}
		});
	}
}
