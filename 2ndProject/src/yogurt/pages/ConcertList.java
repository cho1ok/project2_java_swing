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
import yogurt.db.TestDAO;
import yogurt.db.TestDTO;

public class ConcertList extends ListForm{
	JTable table;
	TestModel model;
	JScrollPane scroll;	
	
	Test test=new Test(this);
	TestDAO testDAO=new TestDAO();
	TestDTO testDTO;	
	
	List<TestDTO> teli=new ArrayList<TestDTO>();
	
	public ConcertList(YogurtMain yogurtMain) {
		super(yogurtMain);
		
		la_name.setText("전시 관람 List");

		testDAO.delete();
		showList();
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				String value = (String) table.getValueAt(table.getSelectedRow(), 0); 

				Detail detail = (Detail) yogurtMain.page[YogurtMain.DETAIL];

				testDTO = teli.get(table.getSelectedRow());

				detail.getDetail(Integer.parseInt(value), testDTO);
				
				yogurtMain.showHide(YogurtMain.DETAIL);
				yogurtMain.showHideHam(YogurtMain.DETAIL);

			}
		});

	}
	
	public void showList() {		
		
		test.getName();
		
		teli=testDAO.selectAll(); 
		
		table=new JTable(model=new TestModel(this));
		scroll=new JScrollPane(table);
		add(scroll);
		scroll.setBounds(80, 200, 900, 200);	
		
        table.getColumnModel().getColumn(0).setMaxWidth(100);
        table.getColumnModel().getColumn(0).setMinWidth(50);
        table.getColumnModel().getColumn(0).setWidth(80);
		table.setRowHeight(30);
		//table.setFont(getFont());
		table.getColumnModel().getColumn(2).setMaxWidth(200);
		table.getColumnModel().getColumn(2).setMinWidth(120);
		table.getColumnModel().getColumn(2).setWidth(120);

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);

		for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(dtcr);
		}		
		//table.getColumnModel().getColumn(0).setCellRenderer(dtcr);
		
	}
	
		
}
