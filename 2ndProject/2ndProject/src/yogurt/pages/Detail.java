package yogurt.pages;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import yogurt.apis.Test2;
import yogurt.db.HeartList;
import yogurt.db.HeartListDAO;
import yogurt.db.Test2DAO;
import yogurt.db.Test2DTO;
import yogurt.db.TestDAO;
import yogurt.db.TestDTO;
import yogurt.util.StringUtil;

public class Detail extends Page{	
	JPanel bigP;
	JPanel p_name;
	JLabel la_name;
	JPanel poster;
	URL url;
	Image image;
	JPanel p_info;
	JLabel rinfo;
	JButton bt_reg;
	JLabel imgLabel;
	ImageIcon icon;
	JLabel xLabel;
	ImageIcon xicon;	
	JLabel review;
	
	JLabel lalalala;
	
	String dir="C:/Users/yello/Desktop/JAVA_study_20220927/java_workspace2/data/2ndProject/";
	String filename;
	long time;
	
	int idx;
	
	HeartListDAO heartListDAO=new HeartListDAO();
	HeartList heartList;
	
	TestDAO testDAO=new TestDAO();
	TestDTO testDTO;

	Test2DAO test2dao=new Test2DAO();
	Test2DTO test2dto;
	
	ConcertList concertList;
	Heart heart;	
	
	public Detail(YogurtMain yogurtMain) {
		super(yogurtMain);
	}
	
	public void getDetail(int idx, TestDTO dtoNum) {			
			
			testDTO=testDAO.select(idx, dtoNum);
			downLoad(testDTO.getPoster());			
			
			if(la_name!=null) {
				p_name.remove(la_name);
				la_name=new JLabel();
				p_name.add(la_name);
				la_name.setText(testDTO.getTitle());
				la_name.setFont(new Font("돋움", Font.BOLD, 28));
				p_name.setBounds(100, 25, 850, 30);		
				
				p_info.remove(rinfo);
				rinfo=new JLabel("", JLabel.CENTER);
				
				rinfo.setText("<html><body>구분 : "+testDTO.getSort()+
						"<br/><br/>예매 시작일 : "+testDTO.getApplydate()+
						"<br/><br/>장소 : "+testDTO.getPlace()+
						"<br/><br/>가격 : "+testDTO.getFee()+
						"<br/><br/>Tel : "+testDTO.getTel()+
						"</body></html>");
				
				p_info.add(rinfo);
				rinfo.setFont(new Font("돋움", Font.BOLD, 20));
				p_info.setBounds(550, 175, 400, 360);
				p_info.setLayout(new FlowLayout());
				
			}else {			
				p_name=new JPanel();
				la_name=new JLabel();	

				p_name.add(la_name);			
				la_name.setText(testDTO.getTitle());
				la_name.setFont(new Font("돋움", Font.BOLD, 28));
				p_name.setBounds(100, 25, 850, 30);		

				p_info=new JPanel();
				rinfo=new JLabel("", JLabel.CENTER);
				
				rinfo.setText("<html><body>구분 : "+testDTO.getSort()+
						"<br/><br/>예매 시작일 : "+testDTO.getApplydate()+
						"<br/><br/>장소 : "+testDTO.getPlace()+
						"<br/><br/>가격 : "+testDTO.getFee()+
						"<br/><br/>Tel : "+testDTO.getTel()+
						"</body></html>");

				p_info.add(rinfo);
				rinfo.setFont(new Font("돋움", Font.BOLD, 20));
				p_info.setBounds(550, 175, 400, 360);
				p_info.setLayout(new FlowLayout());

			}		
		createPage();		
		
		lalalala.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				goWeb();
			}		
		});
		
		imgLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				goHP();
			}
		});
		
		xLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				yogurtMain.showHide(YogurtMain.CONCERTLIST);
				yogurtMain.showHideHam(YogurtMain.CONCERTLIST);
			}
		});
	}
	
	public void getDetail(int idx, Test2DTO dto2Num ) {				

		test2dto=test2dao.select(idx, dto2Num);
		downLoad(test2dto.getPoster());

		if (la_name != null) {
			p_name.remove(la_name);
			la_name = new JLabel();
			p_name.add(la_name);
			la_name.setText(test2dto.getTitle());
			la_name.setFont(new Font("돋움", Font.BOLD, 28));
			p_name.setBounds(100, 25, 850, 30);

			p_info.remove(rinfo);
			rinfo = new JLabel(
					"<html><body style='text-align:center;'>구분 : "+test2dto.getSort()+
					"<br/><br/>예매 시작일 : "+test2dto.getApplydate()+
					"<br/><br/>장소 : "+test2dto.getPlace()+
					"<br/><br/>가격 : "+test2dto.getFee()+
					"</body></html>"
					, JLabel.CENTER
					);
			p_info.add(rinfo);
			rinfo.setFont(new Font("돋움", Font.BOLD, 18));
			p_info.setBounds(550, 190, 400, 360);
			p_info.setLayout(new FlowLayout());


		} else {
			p_name = new JPanel();
			la_name = new JLabel();

			p_name.add(la_name);
			la_name.setText(test2dto.getTitle());
			la_name.setFont(new Font("돋움", Font.BOLD, 28));
			p_name.setBounds(100, 25, 850, 30);

			p_info = new JPanel();
			rinfo = new JLabel(
					"<html><body style='text-align:center;'>구분 : "+test2dto.getSort()+
					"<br/><br/>예매 시작일 : "+test2dto.getApplydate()+
					"<br/><br/>장소 : "+test2dto.getPlace()+
					"<br/><br/>가격 : "+test2dto.getFee()+
					"</body></html>"
					, JLabel.CENTER
					);

			p_info.add(rinfo);
			rinfo.setFont(new Font("돋움", Font.BOLD, 18));
			p_info.setBounds(550, 190, 400, 360);
			p_info.setLayout(new FlowLayout());

		}
		createPage();
		
		lalalala.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				goWeb2();
			}		
		});
		
		imgLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				goHP2();
			}
		});
		
		xLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				yogurtMain.showHide(YogurtMain.EXHIBITLIST);
				yogurtMain.showHideHam(YogurtMain.EXHIBITLIST);
			}
		});
		
	}
	
	public void createPage() {

		poster=new JPanel();
		lalalala=new JLabel("예매사이트로 이동" , JLabel.CENTER);
		icon=new ImageIcon();
		xicon=new ImageIcon();
		review=new JLabel("reviews");				

		poster.setBackground(Color.BLUE);
		review.setFont(new Font("돋움", Font.PLAIN, 20));
		review.setOpaque(true);
		lalalala.setOpaque(true);
		lalalala.setFont(new Font("돋움", Font.BOLD, 26));
		lalalala.setBackground(new Color(215,181,216));
		
		poster=new JPanel() {
			protected void paintComponent(Graphics g) {
				Graphics2D g2=(Graphics2D)g;
				g2.clearRect(0, 0, 300, 400); 
				g2.drawImage(image, 0, 0, 340, 360, Detail.this);
			}
		};
			
		imgLabel = new JLabel();
		xLabel = new JLabel();
        
		icon = new ImageIcon(
				Detail.class.getResource("/yogurt/res/mew4.png")
        );	        
		xicon = new ImageIcon(
				Detail.class.getResource("/yogurt/res/punch3.png")
        );	    
        imgLabel.setIcon(icon);
        xLabel.setIcon(xicon);
        imgLabel.setBounds(200, 470, 70, 70);
        imgLabel.setHorizontalAlignment(JLabel.CENTER);
        xLabel.setBounds(860, 88, 50, 50);
		
        add(p_name);
		add(p_info);

        add(poster);
        add(imgLabel);
        add(xLabel);
        add(review);
        add(lalalala);
        
        setLayout(null);

		poster.setBounds(150, 90, 340, 360);
		review.setBounds(350, 470, 300, 70);
		lalalala.setBounds(125, 560, 800, 50);
		
		preview();	
		clicks();
		
			
	}
	
	public void downLoad(String imgurl) {
		InputStream is=null;
		FileOutputStream fos=null;		
		try {			
			URL url=new URL(imgurl);
			is=url.openStream(); 			

			time=System.currentTimeMillis();			
			fos=new FileOutputStream(dir+time+".jpg"); 
			int data=-1;
			while(true) {
				data=is.read();				
				if(data==-1)break;	
				fos.write(data); 
			}		
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fos!=null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}		
	
	public void preview() {
		File file=new File(dir+time+".jpg"); 
		try {
			image=ImageIO.read(file); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		poster.repaint();
	}	
	
	public void goHP() {
		int result=JOptionPane.showConfirmDialog(yogurtMain, "나의 ♥ 목록에 추가하시겠습니까?",null, JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
					
			if(result==JOptionPane.YES_OPTION ) {
				
				heartList=new HeartList();
				heartList.setTitle(testDTO.getTitle());
				heartList.setPoster(testDTO.getPoster());
				heartList.setPlace(testDTO.getPlace());
				heartList.setBook(testDTO.getBook());
				heartList.setApplydate(testDTO.getApplydate());
				
				heart=(Heart)yogurtMain.page[YogurtMain.HEART];
				heart.heartListDAO.insert(heartList);
				heart.getHeart(idx, Detail.this);			
				
//				yogurtMain.showHide(YogurtMain.HEART);
//				yogurtMain.showHideHam(YogurtMain.HEART);

			}else if(result==JOptionPane.YES_OPTION && heartList!=null) {				
								
			}			
	}
	
	public void goHP2() {
		int result=JOptionPane.showConfirmDialog(yogurtMain, "나의 ♥ 목록에 추가하시겠습니까?",null, JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
					
			if(result==JOptionPane.YES_OPTION ) {
				
				heartList=new HeartList();
				heartList.setTitle(test2dto.getTitle());
				heartList.setPoster(test2dto.getPoster());
				heartList.setPlace(test2dto.getPlace());
				heartList.setBook(test2dto.getBook());
				heartList.setApplydate(test2dto.getApplydate());
				//heartList.setTel("");
				
				heart=(Heart)yogurtMain.page[YogurtMain.HEART];
				heart.heartListDAO.insert(heartList);
				heart.getHeart(idx, Detail.this);			
				
//				yogurtMain.showHide(YogurtMain.HEART);
//				yogurtMain.showHideHam(YogurtMain.HEART);

			}else if(result==JOptionPane.YES_OPTION && heartList!=null) {				
								
			}			
	}
	
	public void goEP() {
		yogurtMain.showHide(YogurtMain.EPILOGUE);
		yogurtMain.showHideHam(YogurtMain.EPILOGUE);
	}
	
	public void goWeb() {
		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			try {
				URI uri = new URI(testDTO.getBook());
				desktop.browse(uri);
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (URISyntaxException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void goWeb2() {
		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			try {
				URI uri = new URI(test2dto.getBook());
				desktop.browse(uri);
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (URISyntaxException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void clicks() {		
		review.addMouseListener(new MouseAdapter() {			
			public void mouseClicked(MouseEvent e) {
				goEP();
			}
		});			
	}
	
}
