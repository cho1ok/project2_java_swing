package yogurt.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import yogurt.util.DBManager;


public class BoardDAO {
	DBManager dbManager=DBManager.getInstance();
	
	//게시물 한건 넣기
	public int insert(Board board) { //여기에 매개변수로 일일이 하나하나 넣어주느냐, 배열로 가느냐, 등의 선택지가 있지만,
													//변수 양이 많아지거나 하면 코드 더러워지니까, dto로 가는게 깔끔
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0; //DML 성공여부를 반환해줄 변수
		
		con=dbManager.getConnection(); //여기에서 db접속을 얻어오게 되면, finally에서도 다시 끊어줘야하므로, 
		//db접속하는 시점을 프레임을 띄울때 하고, 프레임 닫을때 db닫겠다. 근데 싱글턴이므로, 프레임 닫을때 db닫아주면 된다 ->먼솔
		
		//oracle
		String sql="INSERT INTO board(board_id, title, writer, content)";
		sql+=" values(seq_board.nextval,?,?,?)";
		
		//mysql
		//String sql="insert into news(title, writer, detail) valus(?,?,?)";
		
		try {
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());			
			
			result=pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt);
		}
		return result; //dml 수행결과 반환
	}
	
	//모든 게시물 가져오기
	public List selectAll() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		ArrayList<Board> list=new ArrayList<Board>();
		
		con=dbManager.getConnection();
		
		//String sql="select * from news order by news_idx desc";
		
		//게시글테이블만이 아니라, 댓글 테이블과 조인하여 가져오기
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT b.board_id AS board_id, title, writer, regdate, hit, count(r.reply_idx) AS re");
		sb.append(" FROM board b LEFT OUTER join reply r");
		sb.append(" on b.board_id=r.board_id");
		sb.append(" GROUP BY b.board_id, title, writer, regdate, hit");
		
		
		try {
			pstmt=con.prepareStatement(sb.toString());
			rs=pstmt.executeQuery();
			
			//rs는 곧 닫힐 예정이므로, rs를 대체할 객체를 이용
			while(rs.next()) {
				Board board=new Board(); //empty dto
				board.setBoard_id(rs.getInt("board_id"));
				
				if(rs.getInt("re")>0) {
					board.setTitle(rs.getString("title")+"  ["+rs.getInt("re")+"]");
				}else {
					board.setTitle(rs.getString("title"));
				}
				//news.setCnt(rs.getInt("cnt")); //댓글 수
				
				board.setWriter(rs.getString("writer"));
				//news.setDetail(rs.getString("detail"));
				board.setRegdate(rs.getString("regdate"));
				board.setHit(rs.getInt("hit"));
				
				list.add(board); //리스트에 추가
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}		
		return list;
	}
	
	//뉴스기사 한건 가져오기
	public Board select(int board_id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Board board=null;
		
		con=dbManager.getConnection();
		
		String sql="select * from board where board_id=?";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			rs=pstmt.executeQuery();
		
			//해당되는 조건의 레코드가 있다면
			if(rs.next()) {
				board=new Board(); //empty 상태
				board.setBoard_id(rs.getInt("board_id"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getString("regdate"));
				board.setHit(rs.getInt("hit"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}	
		return board;
	}
	
	//한건 수정하기
	public int update(Board board) { //매개변수에 dto 받아오기
		Connection con=null;
		PreparedStatement pstmt=null;
		
		con=dbManager.getConnection();
		
		String sql="update board set title=?, writer=?, detail=? where board_id=?";
		
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, board.getBoard_id());
			
			//DML문이므로, executeUpdate() 메서드 호출. 이때 이 메서드 호출 후 반환되는 값은
			//이 쿼리 수행시 영향을 받은 레코드 수가 반환되므로, 반환값이 0인 경우 수정된 레코드가 없다는 뜻임
			
			//result를 반환값으로 사용해야하니, try문 밖으로 선언 빼줌
			result=pstmt.executeUpdate();			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt);
		}		
		return result;
	}
	
	//한건삭제
	public int delete(int board_id) {
		int result=0;

		Connection con=null;
		PreparedStatement pstmt=null;
		
		con=dbManager.getConnection();
		
		String sql="delete from board where board_id=?";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			result=pstmt.executeUpdate(); //DML 수행 후 반영된 레코드 수 반환
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt);
		}		
		return result;
		
	}
}
