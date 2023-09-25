package yogurt;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;

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

	public String getData() throws IOException {
		StringBuilder urlBuilder = new StringBuilder("http://openAPI.seoul.go.kr:8088/44507a756d79656c313237787a525264/json/ListPublicReservationCulture/1/210/"); /*URL*/
		urlBuilder.append("/" +  URLEncoder.encode("44507a756d79656c313237787a525264","UTF-8") ); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
		urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") ); /*요청파일타입 (xml,xmlf,xls,json) */
		urlBuilder.append("/" + URLEncoder.encode("ListPublicReservationCulture","UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
		urlBuilder.append("/" + URLEncoder.encode("1","UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
		urlBuilder.append("/" + URLEncoder.encode("210","UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
		// 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.
		
		// 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.
		//urlBuilder.append("/" + URLEncoder.encode("20220301","UTF-8")); /* 서비스별 추가 요청인자들*/
		
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
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
		System.out.println(sb.toString());
		
		return sb.toString();
	}
	
	public static void main(String[] args) throws IOException {
		Test tt=new Test();
		String data=tt.getData();
		
		JSONParser jsonParser=new JSONParser();
		try {
			JSONObject json=(JSONObject)jsonParser.parse(data);
			
			JSONObject cList = (JSONObject)json.get("ListPublicReservationCulture");
			JSONArray row=(JSONArray)cList.get("row");
						
			for(int i=0; i<row.size(); i++) {
				
				JSONObject tmp=(JSONObject)row.get(i);
								
				String name=(String)tmp.get("SVCNM");
				String place=(String)tmp.get("PLACENM");
				String img=(String)tmp.get("IMGURL");
				
				System.out.println("\n----- "+(i+1)+"번째 전시 정보 -----");
				System.out.println("제목 : "+name);
				System.out.println("장소 : "+place);
				System.out.println("이미지 : "+img);		
				
				//땡겨오는거 이제 되니까, 이거 이제 어떻게 테이블에 넣을지, 활용 어떻게 해야할지 고민 좀
			}
			
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
