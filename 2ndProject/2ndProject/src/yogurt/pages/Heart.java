package yogurt.pages;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import yogurt.apis.Test;
import yogurt.db.HeartList;
import yogurt.db.HeartListDAO;
import yogurt.db.TestDAO;
import yogurt.db.TestDTO;
import yogurt.sche.Cal;
import yogurt.sche.CalDAO;
import yogurt.sche.Schedule;

public class Heart extends Page {
	JLabel la_name;

	JTable table;
	HeartListModel model;
	JScrollPane scroll;

	TestDTO testDTO;
	HeartListDAO heartListDAO = new HeartListDAO();
	HeartList heartList;
	List<HeartList> hli = new ArrayList<HeartList>();
	ConcertList concertList;

	CalDAO calDAO = new CalDAO();

	public Heart(YogurtMain yogurtMain) {
		super(yogurtMain);

		la_name = new JLabel("나의 ♥ 목록", JLabel.CENTER);
		la_name.setOpaque(true);
		setLayout(null);
		la_name.setFont(new Font("굴림", Font.BOLD, 30));
		add(la_name);
		la_name.setBounds(40, 40, 950, 50);

		heartListDAO.delete();
		// calDAO.delete();

		if (scroll != null) {
			scroll.remove(table);
			hli = heartListDAO.selectAll();
		} else {
		}
		table = new JTable(model = new HeartListModel(this));
		scroll = new JScrollPane(table);
		add(scroll);
		scroll.setBounds(100, 150, 850, 400);
	}

	public void getHeart(int idx, Detail detail) {

		hli = heartListDAO.selectAll();
		table.updateUI();

		table.getColumnModel().getColumn(0).setMaxWidth(100);
		table.getColumnModel().getColumn(0).setMinWidth(50);
		table.getColumnModel().getColumn(0).setWidth(80);
		table.setRowHeight(30);
		table.getColumnModel().getColumn(2).setMaxWidth(200);
		table.getColumnModel().getColumn(2).setMinWidth(120);
		table.getColumnModel().getColumn(2).setWidth(120);

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);

		for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(dtcr);
		}

		addEvent();

	}

	public void addEvent() {

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					String[] buttons = { "일정 등록", "상세정보 보기", "취소" };
					URL url = new URL("https://cdn2.iconfinder.com/data/icons/circle-icons-1/64/compose-48.png");
					ImageIcon icon = new ImageIcon(url);

					int re = JOptionPane.showOptionDialog(Heart.this, "등록하시겠습니까?", "", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.INFORMATION_MESSAGE, icon, buttons, "등록하기");

					if (re == JOptionPane.YES_OPTION) {
						Cal cal = new Cal();

//						int row = table.getSelectedRow();
//						int col = table.getSelectedColumn();
//						Object value = table.getValueAt(row, col);
//						String date=(String)value;

						String cvalue = (String) table.getValueAt(table.getSelectedRow(), 2);
						String content = (String) table.getValueAt(table.getSelectedRow(), 1);

						int lastIndex = cvalue.lastIndexOf("-");
						String result = cvalue.substring(lastIndex - 7, lastIndex + 3);

						String yy = cvalue.substring(lastIndex - 7, lastIndex - 3);
						String mm = cvalue.substring(lastIndex - 2, lastIndex);
						String dd = cvalue.substring(lastIndex + 1, lastIndex + 3);

						cal.setYy(Integer.parseInt(yy));
						cal.setMm(Integer.parseInt(mm));
						cal.setDd(Integer.parseInt(dd));
						cal.setContent(content);

						System.out.println("접수시작일 : " + yy + mm + dd);

						int calre = calDAO.insert(cal);

						Schedule schedule = (Schedule) yogurtMain.page[YogurtMain.SCHEDULE];

						if (calre > 0) {
							JOptionPane.showMessageDialog(Heart.this, "등록성공");
							schedule.printLog();
//							schedule.updateUI();

//							yogurtMain.showHide(YogurtMain.SCHEDULE);
//							yogurtMain.showHideHam(YogurtMain.SCHEDULE);
						}

					} else if (re == JOptionPane.NO_OPTION) {

						String value = (String) table.getValueAt(table.getSelectedRow(), 0);

						Detail detail = (Detail) yogurtMain.page[YogurtMain.DETAIL];

						heartList = hli.get(table.getSelectedRow());		
						
						testDTO = new TestDTO();
						testDTO.setTitle(heartList.getTitle());
						testDTO.setPoster(heartList.getPoster());
						testDTO.setPlace(heartList.getPlace());
						testDTO.setBook(heartList.getBook());
						testDTO.setApplydate(heartList.getApplydate());

						detail.getDetail(Integer.parseInt(value), testDTO);

						yogurtMain.showHide(YogurtMain.DETAIL);
						yogurtMain.showHideHam(YogurtMain.DETAIL);

					}
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
			}
		});

	}

}
