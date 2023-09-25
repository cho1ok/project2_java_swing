package yogurt.pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import yogurt.apis.Test;
import yogurt.apis.Test2;
import yogurt.db.HeartList;
import yogurt.db.HeartListDAO;
import yogurt.db.Test2DAO;
import yogurt.db.Test2DTO;
import yogurt.db.TestDAO;
import yogurt.db.TestDTO;

public class ExhibitList extends ListForm{
	JTable table2;
	Test2Model model;
	JScrollPane scroll;	
	
	
	HeartListDAO heartListDAO=new HeartListDAO();
	HeartList heartList;
	
	Test2 test2=new Test2(this);	
	Test2DAO test2dao=new Test2DAO();
	Test2DTO test2dto;
	
	List<Test2DTO> teli=new ArrayList<Test2DTO>();
	
	public ExhibitList(YogurtMain yogurtMain) {
		super(yogurtMain);

		la_name.setText("Concert List");
		
		test2dao.delete();
		showList();
		
		table2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				String value = (String) table2.getValueAt(table2.getSelectedRow(), 0); 	

				Detail detail = (Detail) yogurtMain.page[YogurtMain.DETAIL];

				test2dto = teli.get(table2.getSelectedRow());

				detail.getDetail(Integer.parseInt(value), test2dto);
				
				yogurtMain.showHide(YogurtMain.DETAIL);
				yogurtMain.showHideHam(YogurtMain.DETAIL);
			}
		});

	}
	
	public void showList() {		
		test2.getName();		
		teli=test2dao.selectAll();
		
		table2=new JTable(model=new Test2Model(this));
		scroll=new JScrollPane(table2);
		add(scroll);
		scroll.setBounds(80, 200, 900, 200);	
		
        table2.getColumnModel().getColumn(0).setMaxWidth(100);
        table2.getColumnModel().getColumn(0).setMinWidth(50);
        table2.getColumnModel().getColumn(0).setWidth(80);
		table2.setRowHeight(30);
		//table.setFont(getFont());
		table2.getColumnModel().getColumn(2).setMaxWidth(200);
		table2.getColumnModel().getColumn(2).setMinWidth(120);
		table2.getColumnModel().getColumn(2).setWidth(120);

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);

		for (int i = 0; i < table2.getColumnModel().getColumnCount(); i++) {
			table2.getColumnModel().getColumn(i).setCellRenderer(dtcr);
		}		
		//table.getColumnModel().getColumn(0).setCellRenderer(dtcr);				
	}
		
}
