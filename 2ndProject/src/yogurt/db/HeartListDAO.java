package yogurt.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import yogurt.util.DBManager;

public class HeartListDAO {
	DBManager dbManager=DBManager.getInstance();
	
	HeartList heartList;
	
	
	
	public int insert(HeartList heartList) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		
		con=dbManager.getConnection(); 

		String sql="insert into heart(heart_idx, title, poster, place, book, applydate, fee, sort, tel)";
		sql+=" values(seq_heart.nextval,?,?,?,?,?,?,?,?)";
			
		try {
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, heartList.getTitle() );
			pstmt.setString(2, heartList.getPoster());
			pstmt.setString(3, heartList.getPlace());
			pstmt.setString(4, heartList.getBook());
			pstmt.setString(5, heartList.getApplydate());
			pstmt.setString(6, heartList.getFee());
			pstmt.setString(7, heartList.getSort());
			pstmt.setString(8, heartList.getTel());
			
			result=pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt);
		}
		return result;
	}
	
	public List selectAll() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		List list=new ArrayList<>();
		
		con=dbManager.getConnection();
		
		String sql="select * from heart";
		
		try {
			pstmt=con.prepareStatement(sql);

			rs=pstmt.executeQuery();
		
			while(rs.next()) {
				
				heartList=new HeartList();
				
				heartList.setHeart_idx(rs.getInt("heart_idx"));
				heartList.setTitle(rs.getString("title"));
				heartList.setPoster(rs.getString("poster"));
				heartList.setPlace(rs.getString("place"));
				heartList.setBook(rs.getString("book"));
				heartList.setApplydate(rs.getString("applydate"));
				heartList.setFee(rs.getString("fee"));
				heartList.setSort(rs.getString("sort"));
				heartList.setTel(rs.getString("tel"));

				list.add(heartList);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}		
		return list;
	}
	
	public HeartList select() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		con=dbManager.getConnection();
		
		String sql="select * from heart";
		
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();

			while(rs.next()) {				
				heartList=new HeartList();				
				heartList.setHeart_idx(rs.getInt("heart_idx"));
				heartList.setTitle(rs.getString("title"));
				heartList.setPoster(rs.getString("poster"));
				heartList.setPlace(rs.getString("place"));
				heartList.setBook(rs.getString("book"));
				heartList.setApplydate(rs.getString("applydate"));
				heartList.setFee(rs.getString("fee"));
				heartList.setSort(rs.getString("sort"));
				heartList.setTel(rs.getString("tel"));



				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}		
		return heartList;
	}
	
	public HeartList select(int idx, HeartList heartList) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		con=dbManager.getConnection();
		
		String sql="select * from heart where heart_idx=?";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, heartList.getHeart_idx());
			rs=pstmt.executeQuery();

			while(rs.next()) {				
				heartList=new HeartList();				
				heartList.setHeart_idx(rs.getInt("heart_idx"));
				heartList.setTitle(rs.getString("title"));	
				heartList.setPoster(rs.getString("poster"));
				heartList.setPlace(rs.getString("place"));
				heartList.setBook(rs.getString("book"));
				heartList.setApplydate(rs.getString("applydate"));
				heartList.setFee(rs.getString("fee"));
				heartList.setSort(rs.getString("sort"));
				heartList.setTel(rs.getString("tel"));



			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}		
		return heartList;
	}
	
	public void delete() {
		Connection con=null;
		PreparedStatement pstmt=null;
		con=dbManager.getConnection();
		
		String sql="delete from heart";
		
		try {
			pstmt=con.prepareStatement(sql);			
			pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt);
		}		
	}
		
}
