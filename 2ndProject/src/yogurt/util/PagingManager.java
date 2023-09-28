package yogurt.util;

import java.util.List;

//앞으로 모~~든 게시판에 중립적으로 적용할 수 있는 범용적 페이징 처리 계산 객체
public class PagingManager {
	//페이징 처리란? 데이터를 분할하여 출력하는 기법 (산수계산에 의함)
	//페이징 처리 로직을 개발하는 과정에서는 db연동은 필수가 아니다
	private int totalRecord; //총 레코드 수. 일단 가라로
	private int pageSize=10; //한 페이지당 보여질 레코드 수
	private int totalPage; //총 페이지 수
	private int blockSize=10; //블럭당 보여질 페이지 수
	private int currentPage=1; //현재 유저가 보고있는 페이지

	//이제 제일 어려운거
	//힌트: 위에 선언된 변수들을 조합만 하면 됨!
	private int firstPage; //블럭당 반복문의 시작값
	private int lastPage; //블럭당 반복문의 끝값
	
	private int num; //페이지당 시작 번호(1p:26, 2p:16, 3p:6부터) 힌트: 기존 변수 조합
	
	//페이징 처리 계산 메서드
	public void init(List list, int currentPage) {
		totalRecord=126;
		
		totalPage=(int)Math.ceil((float)totalRecord/pageSize);
		System.out.println(totalPage);
		
		this.currentPage=currentPage; //사용자가 선택한 페이지를 넘겨받아서 현재 페이지로 설정
		
		firstPage=currentPage-(currentPage-1)%blockSize;
		lastPage=firstPage+(blockSize-1);
		num=totalRecord-(currentPage-1)*pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	


}
