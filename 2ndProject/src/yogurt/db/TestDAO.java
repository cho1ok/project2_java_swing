package yogurt.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import yogurt.util.DBManager;

public class TestDAO {
	DBManager dbManager=DBManager.getInstance();
	
	TestDTO testDTO;
	
	
	
	public int insert(TestDTO testDTO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		
		con=dbManager.getConnection(); 

		String sql="insert into test(test_idx, title, poster, place, book, applydate, fee, sort, tel)";
		sql+=" values(seq_test.nextval,?,?,?,?,?,?,?,?)";
			
		try {
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, testDTO.getTitle() );
			pstmt.setString(2, testDTO.getPoster());
			pstmt.setString(3, testDTO.getPlace());
			pstmt.setString(4, testDTO.getBook());
			pstmt.setString(5, testDTO.getApplydate());
			pstmt.setString(6, testDTO.getFee());
			pstmt.setString(7, testDTO.getSort());
			pstmt.setString(8, testDTO.getTel());
			
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
		
		String sql="select * from test";
		
		try {
			pstmt=con.prepareStatement(sql);

			rs=pstmt.executeQuery();
		
			while(rs.next()) {
				
				testDTO=new TestDTO();
				
				testDTO.setTest_idx(rs.getInt("test_idx"));
				testDTO.setTitle(rs.getString("title"));
				testDTO.setPoster(rs.getString("poster"));
				testDTO.setPlace(rs.getString("place"));
				testDTO.setBook(rs.getString("book"));
				testDTO.setApplydate(rs.getString("applydate"));
				testDTO.setFee(rs.getString("fee"));
				testDTO.setSort(rs.getString("sort"));
				testDTO.setTel(rs.getString("tel"));
				
				list.add(testDTO);								
				
				//System.out.println("4 : "+list);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}		
		return list;
	}
	
	public TestDTO select() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		con=dbManager.getConnection();
		
		String sql="select * from test";
		
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();

			while(rs.next()) {				
				testDTO=new TestDTO();				
				testDTO.setTest_idx(rs.getInt("test_idx"));
				testDTO.setTitle(rs.getString("title"));
				testDTO.setPoster(rs.getString("poster"));
				testDTO.setPlace(rs.getString("place"));
				testDTO.setBook(rs.getString("book"));
				testDTO.setApplydate(rs.getString("applydate"));
				testDTO.setFee(rs.getString("fee"));
				testDTO.setSort(rs.getString("sort"));
				testDTO.setTel(rs.getString("tel"));

				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}		
		return testDTO;
	}
	
	public TestDTO select(int idx, TestDTO testDTO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		con=dbManager.getConnection();
		
		String sql="select * from test where test_idx=?";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, testDTO.getTest_idx());
			rs=pstmt.executeQuery();

			while(rs.next()) {				
				testDTO=new TestDTO();				
				testDTO.setTest_idx(rs.getInt("test_idx"));
				testDTO.setTitle(rs.getString("title"));
				testDTO.setPoster(rs.getString("poster"));
				testDTO.setPlace(rs.getString("place"));
				testDTO.setBook(rs.getString("book"));
				testDTO.setApplydate(rs.getString("applydate"));
				testDTO.setFee(rs.getString("fee"));
				testDTO.setSort(rs.getString("sort"));
				testDTO.setTel(rs.getString("tel"));

				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}		
		return testDTO;
	}
	
	
	
//	public List select(int idx) {
//		List list =new ArrayList();
//		Connection con=null;
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		TestDTO testDTO=null;	
//		
//		con=dbManager.getConnection();
//		
//		String sql="select * from test where test_idx=?";
//		
//		int result=0;
//		try {
//			pstmt=con.prepareStatement(sql);
//			pstmt.setInt(1, testDTO.getTest_idx());
//			rs=pstmt.executeQuery();
//
//			while(rs.next()) {				
//				testDTO=new TestDTO();				
//				testDTO.setTest_idx(rs.getInt("test_idx"));
//				testDTO.setTitle(rs.getString("title"));
//				testDTO.setPoster(rs.getString("poster"));
//				testDTO.setPlace(rs.getString("place"));
//				
//				list.add(testDTO);
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			dbManager.release(pstmt, rs);
//		}		
//		return list;
//	}
	
	
	public void delete() {
		Connection con=null;
		PreparedStatement pstmt=null;
		con=dbManager.getConnection();
		
		String sql="delete from test";
		
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
