package yogurt.sche;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import yogurt.pages.ModalTest;
import yogurt.pages.Page;
import yogurt.pages.YogurtMain;
import yogurt.util.DBManager;

public class Schedule extends Page{
	DBManager dbManager=DBManager.getInstance();
	CalDAO calDAO=new CalDAO();
	Cal cal;

	JLabel la_name;

	JPanel p_west;
	JComboBox<String> box_yy; 
	JComboBox<String> box_mm; 
	JComboBox<String> box_dd; 
	JTextArea area;
	JScrollPane scroll;
	JButton bt_regist; 
	JButton bt_del;
	
	JPanel p_center; 
	JPanel p_title; 
	JPanel p_dayOfWeek;
	JPanel p_dayOfMonth; 
	JButton bt_prev;
	JLabel la_title; 
	JButton bt_next;

	DayCell[] dayCells=new DayCell[7];
	String[] dayTitle= {"Sun" , "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

	DateCell[][] dateCells=new DateCell[6][7];

	Calendar currentObj=Calendar.getInstance();
	
	YogurtMain yogurtMain;

	int cy;
	int cm;
	int cd;
	String cc;	
	Color ccol;
	
	public Schedule(YogurtMain yogurtMain) {
		super(yogurtMain);
		this.yogurtMain=yogurtMain;
		
		la_name=new JLabel("나의 관심 일정" , JLabel.CENTER);
		la_name.setOpaque(true);
		setLayout(null);
		la_name.setFont(new Font("굴림", Font.BOLD, 30));

		p_center=new JPanel();
		
		p_title=new JPanel();
		p_dayOfWeek=new JPanel();
		p_dayOfMonth=new JPanel();
		bt_prev=new JButton("◀");
		la_title=new JLabel("2022-12");
		bt_next=new JButton("▶");		
		
		la_title.setFont(new Font("Dotum", Font.BOLD|Font.ITALIC, 24));
		
		p_dayOfWeek.setLayout(new GridLayout(1,7));
		p_dayOfMonth.setLayout(new GridLayout(6,7));				
		
		p_center.setLayout(null);
		p_title.setLayout(null);
		
		p_center.add(la_name);
		p_title.add(bt_prev);
		p_title.add(la_title);
		p_title.add(bt_next);
		p_center.add(p_title);
		p_center.add(p_dayOfWeek);
		p_center.add(p_dayOfMonth);
		add(p_center);

		bt_prev.setBackground(Color.WHITE);
		bt_next.setBackground(Color.WHITE);
				
		la_name.setBounds(30, 30, 950, 50);
		p_title.setBounds(50, 90, 925, 50);
		la_title.setBounds(50, 1, 500, 40);
		bt_prev.setBounds(725, 5, 50, 30);
		bt_next.setBounds(815, 5, 50, 30);
		p_dayOfWeek.setBounds(50, 160, 920, 40);
		p_dayOfMonth.setBounds(50, 200, 920, 400);		
		p_center.setBounds(0, 0, 1000, 700);		
		
		createDayOfWeek(); 
		createDayOfMonth(); 
		calDAO.delete();		
		calculate();
		
		bt_next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int mm=currentObj.get(Calendar.MONTH);
				//System.out.println("당신이 보고있는 현재 월은 "+(mm+1));
				
				currentObj.set(Calendar.MONTH , mm+1); 

				calculate();				
			}
		});		

		bt_prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int mm=currentObj.get(Calendar.MONTH);				
				currentObj.set(Calendar.MONTH , mm-1); 				
				calculate();
			}
		});
			
	}

	public void createDayOfWeek() {
		
		for(int i=0;i<dayCells.length;i++) {
			dayCells[i]=new DayCell(dayTitle[i],"", 19, 70, 26);
			p_dayOfWeek.add(dayCells[i]); 
		}
	}

	public void createDayOfMonth() {
		
		for(int i=0;i<dateCells.length;i++) { 
			for(int a=0; a<dateCells[i].length; a++) { 
				dateCells[i][a]=new DateCell(yogurtMain, this, "", "", 13, 96, 26);
				p_dayOfMonth.add(dateCells[i][a]);
			}
		}
	}

	public void printTitle() {
		int yy=currentObj.get(Calendar.YEAR);
		int mm=currentObj.get(Calendar.MONTH);
		
		String str=yy+"년 "+(mm+1)+"월"; 
		
		la_title.setText(str);
	}

	public int getStartDayOfWeek() {
		Calendar calendar= Calendar.getInstance();
		
		int yy=currentObj.get(Calendar.YEAR);

		int mm=currentObj.get(Calendar.MONTH);
		
		calendar.set(yy, mm, 1); //1일로
		
		int day=calendar.get(Calendar.DAY_OF_WEEK);
		
		return day; //무슨 요일부터 시작하는지 반환해주는 메서드
	}
	
	public int getLastDayOfMonth() {
		Calendar calendar=Calendar.getInstance();
		int yy=currentObj.get(Calendar.YEAR);
		int mm=currentObj.get(Calendar.MONTH);
		
		calendar.set(yy, mm+1, 0); 
		int date=calendar.get(Calendar.DATE);
		
		return date;
	}
	
	public void printDate() {
		int n=0; 
		int d=0;
		
		for(int i=0; i<dateCells.length; i++) {
			for(int a=0; a<dateCells[i].length; a++) {
				DateCell dcell=dateCells[i][a]; 
				n++;
			
				if(n>=getStartDayOfWeek() && d<getLastDayOfMonth()) {
					d++;
					dcell.title=Integer.toString(d);
				}else {
					dcell.title="";
				}			
			}
			p_dayOfMonth.repaint();			
		}		
	}
	
	public void calculate() {
		printTitle(); 
		printDate();
		initCell();
		printLog();
	}
	
	public void printLog() {
		int yy=currentObj.get(Calendar.YEAR);
		int mm=currentObj.get(Calendar.MONTH);
		
		List<Cal> diaryList=calDAO.selectAll(yy, mm);

		for(int i=0; i<dateCells.length; i++) {
			for(int a=0; a<dateCells[i].length; a++) {
				
				if(dateCells[i][a].title.equals("")==false) { 

					int date=Integer.parseInt(dateCells[i][a].title); 

					for(int x=0; x<diaryList.size(); x++) {
						Cal obj=diaryList.get(x); 
							
							if(date == obj.getDd()){ 
								dateCells[i][a].color=new Color(215,181,216); 
								dateCells[i][a].content=obj.getContent(); 
								dateCells[i][a].cal=obj;
															
								p_dayOfMonth.updateUI();
								
								cy=obj.getYy();
								cm=obj.getMm();
								cd=obj.getDd();
								cc=dateCells[i][a].content;			
								ccol=dateCells[i][a].color;
								//모달
								dateCells[i][a].popUp(cc, cy, cm, cd);	
								
						}
					}					
				}				
			}
		}
		p_dayOfMonth.repaint();		
	}
	
	public void delCell(Cal cal) {
		if(cal.getDiary_idx()==0) {
		}else {
				int re=calDAO.delete(cal.getDiary_idx());
				if(re>0) {
					calculate();
			}
		}
		System.out.println(cal.getDiary_idx());
	}
	
	public void initCell() {
        for (int i = 0; i < dateCells.length; i++) {
            for (int a = 0; a < dateCells[i].length; a++) {

                dateCells[i][a].color = Color.WHITE; // 색깔넣고
                dateCells[i][a].content = ""; // 셀에 그 내용 뜨게 하겠음
            }
        }
        p_dayOfMonth.repaint();
    }
	
}
