package yogurt.sche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import yogurt.pages.ConcertList;
import yogurt.util.DBManager;

public class CalDAO {
	DBManager dbManager=DBManager.getInstance();
	
	//CRUD 중 insert 를 정의한다.
	public int insert(Cal cal) {
		//System.out.println("호출 후 d= " +cal);
		
		Connection con=null;
		PreparedStatement pstmt=null;
		
		con=dbManager.getConnection();
		
		String sql="insert into diary(diary_idx, yy, mm, dd, content)";
		sql+=" values(seq_diary.nextval, ?,?,?,?)";
		
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, cal.getYy());
			pstmt.setInt(2, cal.getMm());
			pstmt.setInt(3, cal.getDd());
			pstmt.setString(4, cal.getContent());
			
			result=pstmt.executeUpdate(); //쿼리 실행
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt);
		}	
		
		return result;
	}
	
	//해당 월에 등록된 다이어리 가져오기
	public List selectAll(int yy, int mm) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		ArrayList<Cal> list=new ArrayList<Cal>(); //rs 대신 DTO를 담을 어레이리스트
		
		
		String sql="select * from diary where yy=? and mm=?"; 
		try {
			pstmt=dbManager.getConnection().prepareStatement(sql);
			pstmt.setInt(1, yy);
			pstmt.setInt(2, mm);
			rs=pstmt.executeQuery(); 
			
			while(rs.next()) {
				Cal cal=new Cal();
				cal.setDiary_idx(rs.getInt("diary_idx"));
				cal.setYy(rs.getInt("yy"));
				cal.setMm(rs.getInt("mm"));
				cal.setDd(rs.getInt("dd"));
				cal.setContent(rs.getString("content"));

				list.add(cal);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}
		return list;
	}
	
	public void delete() {
		Connection con=null;
		PreparedStatement pstmt=null;
		con=dbManager.getConnection();
		
		String sql="delete from diary";
		
		try {
			pstmt=con.prepareStatement(sql);			
			pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt);
		}

	}
	
	public int delete(int idx) {
		Connection con=null;
		PreparedStatement pstmt=null;
		con=dbManager.getConnection();
		
		String sql="delete from diary";
		
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);			
			result=pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt);
		}
		
		return result;
	}
}
