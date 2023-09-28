package yogurt.apis;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import yogurt.db.TestDAO;
import yogurt.db.TestDTO;
import yogurt.pages.ConcertList;
import yogurt.sche.Cal;

import java.io.BufferedReader;
import java.io.IOException;

//차트 참고
//https://docs.oracle.com/javase/tutorial/uiswing/components/editorpane.html
//보더 참고
//https://docs.oracle.com/javase/tutorial/uiswing/components/border.html
//
//https://techsignal.tistory.com/9
//https://www.habonyphp.com/2021/01/java-10.html
//
//http://json.parser.online.fr/
//https://m.blog.naver.com/occidere/220799351272
//
//서울시 문화행사 정보
//http://data.seoul.go.kr/dataList/OA-15486/S/1/datasetView.do
//서울시 문화행사 공공서비스예약 정보
//http://data.seoul.go.kr/dataList/OA-2269/S/1/datasetView.do
//서울시 세종문화회관 공연 및 전시 정보
//http://data.seoul.go.kr/dataList/OA-2708/S/1/datasetView.do


public class Test {
	
	String name;
	String place;
	String img;
	String book;
	String applydate;
	String fee;
	String sort;
	String tel;
	
	TestDAO testDAO=new TestDAO();
	TestDTO testDTO;
	ConcertList concertList;
	
	List list;
	
	public Test(ConcertList concertList) {
		this.concertList=concertList;
		
	}
	
	public String getData() throws IOException {
		StringBuilder urlBuilder = new StringBuilder("http://openAPI.seoul.go.kr:8088/44507a756d79656c313237787a525264/json/ListPublicReservationCulture/1/10/"); /*URL*/
		urlBuilder.append("/" +  URLEncoder.encode("44507a756d79656c313237787a525264","UTF-8") ); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
		urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") ); /*요청파일타입 (xml,xmlf,xls,json) */
		urlBuilder.append("/" + URLEncoder.encode("ListPublicReservationCulture","UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
		urlBuilder.append("/" + URLEncoder.encode("1","UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
		urlBuilder.append("/" + URLEncoder.encode("10","UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
		// 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.		
		// 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.
		//urlBuilder.append("/" + URLEncoder.encode("20220301","UTF-8")); /* 서비스별 추가 요청인자들*/		
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		//System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
		BufferedReader rd;	
		// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		//System.out.println(sb.toString());
		
		return sb.toString();
	}
	
	public List getName() {
		testDTO=new TestDTO();
		String data;			
		JSONParser jsonParser=new JSONParser();
		
		list=new ArrayList();
		try {
			data = getData();
			JSONObject json=(JSONObject)jsonParser.parse(data);			
			JSONObject cList = (JSONObject)json.get("ListPublicReservationCulture");
			JSONArray row=(JSONArray)cList.get("row");					
			for(int i=0; i<row.size(); i++) {				
				JSONObject tmp=(JSONObject)row.get(i);							
				name=(String)tmp.get("SVCNM");
				place=(String)tmp.get("PLACENM");
				img=(String)tmp.get("IMGURL");
				book=(String)tmp.get("SVCURL");
				applydate=(String)tmp.get("RCPTBGNDT");
				fee=(String)tmp.get("PAYATNM");
				sort=(String)tmp.get("MINCLASSNM");
				tel=(String)tmp.get("TELNO");
				
//				System.out.println("\n----- "+(i+1)+"번째 전시 정보 -----");
//				System.out.println("제목 : "+name);
//				System.out.println("장소 : "+place);

				int lastIndex=applydate.lastIndexOf("-");						
				String result=applydate.substring(lastIndex-7, lastIndex+3);				
				//System.out.println("접수시작일 : "+result);			
//				String yy=date.substring(lastIndex-7, lastIndex-3);		
//				String mm=date.substring(lastIndex-2, lastIndex);
//				String dd=date.substring(lastIndex+1, lastIndex+3);				
//				//System.out.println("접수시작일 : "+yy+mm+dd);						
				
				testDTO.setTitle(name);
				testDTO.setPoster(img);
				testDTO.setPlace(place);
				testDTO.setBook(book);
				testDTO.setApplydate(result);				
				testDTO.setFee(fee);
				testDTO.setSort(sort);
				testDTO.setTel(tel);
				
				testDAO.insert(testDTO);	
				testDAO.select(i, testDTO);

				list.add(testDTO);
//				//System.out.println("6: "+list);
			}		
		} catch (ParseException e) {
			e.printStackTrace();	
		} catch (IOException e1) {

			e1.printStackTrace();
		}	
		return list;
	}
//	
//	public static void main(String[] args) throws IOException {
//		new Test(null);
//	}
}
