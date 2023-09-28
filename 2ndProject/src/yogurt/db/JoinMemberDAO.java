package yogurt.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import yogurt.util.DBManager;

public class JoinMemberDAO {
	DBManager dbManager=DBManager.getInstance();
	
	public int insert(JoinMember joinMember) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		con=dbManager.getConnection();

		String sql="insert into joinyogurt(joinyogurt_idx, name, id, pass, mobile, birth, email)";
		sql+=" values(seq_joinyogurt.nextval,?,?,?,?,?,?)";
		
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, joinMember.getName());
			pstmt.setString(2, joinMember.getId());
			pstmt.setString(3, joinMember.getPass());
			pstmt.setInt(4, joinMember.getMobile());
			pstmt.setInt(5, joinMember.getBirth());
			pstmt.setString(6, joinMember.getEmail());

			result=pstmt.executeUpdate(); //쿼리실행
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return result;
	} 
	
	public int update(JoinMember joinMember) {
		
		return 0;
	} 
	
	public int delete(int joinyogurt_idx) {
		
		return 0;
	}
	
	public List selectAll() {
		
		return null;
	} 
	
	public JoinMember select(int joinyogurt_idx) {
		
		return null;
	}
	
	public JoinMember select(JoinMember joinMember) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		
		JoinMember joinDto=null; //로그인 성공 시 회원정보를 담아둘 DTO
		
		con=dbManager.getConnection();
		
		String sql="select * from joinyogurt where id=? and pass=?";
		
		try {
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, joinMember.getId());
			pstmt.setString(2, joinMember.getPass());
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) { 
				joinDto=new JoinMember(); 
				
				joinDto.setJoinyogurt_idx(rs.getInt("joinyogurt_idx"));
				joinDto.setId(rs.getString("id"));
				joinDto.setPass(rs.getString("pass"));

			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}
		
		return joinDto;
	}
}
