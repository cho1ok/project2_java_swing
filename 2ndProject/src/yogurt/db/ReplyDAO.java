package yogurt.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import yogurt.util.DBManager;

//Comments 테이블에 대한 CRUD를 처리하는 객체
public class ReplyDAO {
	DBManager dbManager=DBManager.getInstance();
	
	//댓글 등록(부모 테이블의 pk를 함께 넣어줘야함)
	public int insert(Reply reply) {
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
	
		con=dbManager.getConnection();
		
		//oracle
		String sql="insert into reply(reply_idx, board_id, rtitle, rwriter)";
		sql+=" values(seq_reply.nextval,?,?,?)";
		
		//mysql
		//String sql="insert into comments(news_idx, ctitle, cwriter) values(?,?,?)";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, reply.getBoard().getBoard_id());
			pstmt.setString(2, reply.getRtitle());
			pstmt.setString(3, reply.getRwriter());
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt);
		}		
		return result;
	}
	
	//댓글 목록 가져오기 (특정 뉴스 기사에 딸린)
	public List select(Board board) {
		List list =new ArrayList();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		con=dbManager.getConnection();
		
		String sql="select * from reply where board_id=?"; //보고있는 뉴스기사
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, board.getBoard_id()); //상세보기에서 살려놓았던 바로 그 news DTO 넘겨받음
			
			rs=pstmt.executeQuery();
			
			//rs에 들어있는 여러 레코드들을 자바의 객체로 변환
			while(rs.next()) {
				Reply reply=new Reply();
				reply.setReply_idx(rs.getInt("reply_idx"));
				reply.setRtitle(rs.getString("rtitle"));
				reply.setRwriter(rs.getString("rwriter"));
				reply.setRregdate(rs.getString("rregdate"));
				//주의!
				reply.setBoard(board); //상세보기에서 넘겨받은 news 다시 대입
				
				list.add(reply);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}		
		return list;
	}
		
}
