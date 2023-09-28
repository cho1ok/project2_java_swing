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

import yogurt.db.Test2DAO;
import yogurt.db.Test2DTO;
import yogurt.db.TestDAO;
import yogurt.db.TestDTO;
import yogurt.pages.ConcertList;
import yogurt.pages.ExhibitList;

import java.io.BufferedReader;
import java.io.IOException;

public class Test2 {	
	String name;
	String place;
	String img;
	String book;
	String fee;
	String applydate;
	String sort;
	
	Test2DAO test2dao=new Test2DAO();
	Test2DTO test2dto;
	ExhibitList exhibitList;
	
	List list;
	
	public Test2(ExhibitList exhibitList) {
		this.exhibitList=exhibitList;
		
		getName();
		
	}
	
	public String getData() throws IOException {
		StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088/636a78684179656c36344d516a6443/json/culturalEventInfo/1/10/"); /*URL*/
		urlBuilder.append("/" +  URLEncoder.encode("636a78684179656c36344d516a6443","UTF-8") ); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
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
		test2dto=new Test2DTO();
		String data;			
		JSONParser jsonParser=new JSONParser();
		
		list=new ArrayList();
		try {
			data = getData();
			JSONObject json=(JSONObject)jsonParser.parse(data);			
			JSONObject cList = (JSONObject)json.get("culturalEventInfo");
			JSONArray row=(JSONArray)cList.get("row");					
			for(int i=0; i<row.size(); i++) {				
				JSONObject tmp=(JSONObject)row.get(i);							
				name=(String)tmp.get("TITLE");
				place=(String)tmp.get("PLACE");
				img=(String)tmp.get("MAIN_IMG");
				book=(String)tmp.get("ORG_LINK");
				applydate=(String)tmp.get("RGSTDATE");
				fee=(String)tmp.get("USE_FEE");
				sort=(String)tmp.get("CODENAME");

				int lastIndex=applydate.lastIndexOf("-");						
				String result=applydate.substring(lastIndex-7, lastIndex+3);		

								
//				System.out.println("\n----- "+(i+1)+"번째 전시 정보 -----");
//				System.out.println("제목 : "+name);
//				System.out.println("날짜 : "+applydate);
//				System.out.println("가격 : "+fee);
//				System.out.println("장소 : "+place);				
//				System.out.println(result);

				test2dto.setTitle(name);
				test2dto.setPoster(img);
				test2dto.setPlace(place);
				test2dto.setBook(book);
				test2dto.setApplydate(result);
				test2dto.setFee(fee);
				test2dto.setSort(sort);
				
				test2dao.insert(test2dto);	
				test2dao.select(i, test2dto);

				list.add(test2dto);
				//System.out.println("6: "+list);
			}		
		} catch (ParseException e) {
			e.printStackTrace();	
		} catch (IOException e1) {

			e1.printStackTrace();
		}	
		return list;
		

	}
	
//	public static void main(String[] args) throws IOException {
//		new Test2(null);
//	}
}
