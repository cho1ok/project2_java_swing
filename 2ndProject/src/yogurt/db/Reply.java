package yogurt.db;

public class Reply {
	private int reply_idx;
	private int board_id;
	private String rtitle;
	private String rwriter;
	private String rregdate;
	
	private Board board;

	public int getReply_idx() {
		return reply_idx;
	}

	public void setReply_idx(int reply_idx) {
		this.reply_idx = reply_idx;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public String getRtitle() {
		return rtitle;
	}

	public void setRtitle(String rtitle) {
		this.rtitle = rtitle;
	}

	public String getRwriter() {
		return rwriter;
	}

	public void setRwriter(String rwriter) {
		this.rwriter = rwriter;
	}

	public String getRregdate() {
		return rregdate;
	}

	public void setRregdate(String rregdate) {
		this.rregdate = rregdate;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	

	
	
	
}
