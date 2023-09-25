package yogurt.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import yogurt.util.DBManager;

public class Test2DAO {
	DBManager dbManager=DBManager.getInstance();
	
	Test2DTO test2DTO;
	
	
	
	public int insert(Test2DTO test2DTO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		
		con=dbManager.getConnection(); 

		String sql="insert into test2(test2_idx, title, poster, place, book, applydate, fee, sort)";
		sql+=" values(seq_test2.nextval,?,?,?,?,?,?,?)";
			
		try {
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, test2DTO.getTitle() );
			pstmt.setString(2, test2DTO.getPoster());
			pstmt.setString(3, test2DTO.getPlace());
			pstmt.setString(4, test2DTO.getBook());
			pstmt.setString(5, test2DTO.getApplydate());
			pstmt.setString(6, test2DTO.getFee());
			pstmt.setString(7, test2DTO.getSort());
			
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
		
		String sql="select * from test2";
		
		try {
			pstmt=con.prepareStatement(sql);

			rs=pstmt.executeQuery();
		
			while(rs.next()) {
				
				test2DTO=new Test2DTO();
				
				test2DTO.setTest2_idx(rs.getInt("test2_idx"));
				test2DTO.setTitle(rs.getString("title"));
				test2DTO.setPoster(rs.getString("poster"));
				test2DTO.setPlace(rs.getString("place"));
				test2DTO.setBook(rs.getString("book"));
				test2DTO.setApplydate(rs.getString("applydate"));
				test2DTO.setFee(rs.getString("fee"));
				test2DTO.setSort(rs.getString("sort"));
				
				list.add(test2DTO);								
				
				//System.out.println("4 : "+list);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}		
		return list;
	}
	
	public Test2DTO select() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		con=dbManager.getConnection();
		
		String sql="select * from test2";
		
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();

			while(rs.next()) {				
				test2DTO=new Test2DTO();
				
				test2DTO.setTest2_idx(rs.getInt("test2_idx"));
				test2DTO.setTitle(rs.getString("title"));
				test2DTO.setPoster(rs.getString("poster"));
				test2DTO.setPlace(rs.getString("place"));
				test2DTO.setBook(rs.getString("book"));
				test2DTO.setApplydate(rs.getString("applydate"));
				test2DTO.setFee(rs.getString("fee"));
				test2DTO.setSort(rs.getString("sort"));

				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}		
		return test2DTO;
	}
	
	public Test2DTO select(int idx, Test2DTO test2DTO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		con=dbManager.getConnection();
		
		String sql="select * from test2 where test2_idx=?";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, test2DTO.getTest2_idx());
			rs=pstmt.executeQuery();

			while(rs.next()) {				
				test2DTO=new Test2DTO();
				
				test2DTO.setTest2_idx(rs.getInt("test2_idx"));
				test2DTO.setTitle(rs.getString("title"));
				test2DTO.setPoster(rs.getString("poster"));
				test2DTO.setPlace(rs.getString("place"));
				test2DTO.setBook(rs.getString("book"));
				test2DTO.setApplydate(rs.getString("applydate"));
				test2DTO.setFee(rs.getString("fee"));
				test2DTO.setSort(rs.getString("sort"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}		
		return test2DTO;
	}	
	
	public void delete() {
		Connection con=null;
		PreparedStatement pstmt=null;
		con=dbManager.getConnection();
		
		String sql="delete from test2";
		
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
