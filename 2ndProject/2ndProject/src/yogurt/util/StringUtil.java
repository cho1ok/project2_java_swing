package yogurt.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil {
	//넘겨받은 숫자가 한 자리 수 이면, 앞에 0 붙이기 (5->05)
	//누군가가 이 메서드를 호출하면, 처리 결과를 반환받는
	public static String getNumString(int num) {
		String str=null;
		
		if(num<10) { //한자리수인경우
			str="0"+num; //05
		}else { //두자리수인경우
			str=Integer.toString(num); //Wrapper 클래스 적용 //5
		}
		return str;
	}
	
	//확장자 추출하여 반환받기
	public static String getExtend(String filename) {
		//String filename="a.aa.png";		
		int lastIndex=filename.lastIndexOf(".");		
		
		return filename.substring(lastIndex+1, filename.length());
	}
	
	//파일명 반환하기
	public static String createFileName(String url) {
		//파일명 만들기
		long time=System.currentTimeMillis();
		//확장자 구하기
		String ext=StringUtil.getExtend(url);		
		
		return time+"."+ext;
	}	
	
	//비밀번호 암호화하기 //비빌번호를 해시화하여 저장하기 (해시화=복호화는 못하는 암호화 기법) 해시코드: 해시 알고리즘에 의해 생성된 정수 값
	//자바의 보안과 관련된 기능을 지원하는 api가 모여있는 패키지가 바로
	//java.security 이다.
	public static String getConvertedPassword(String pass) {
		//암호화 객체
		
		//StringBuffer : A string buffer is like a String, but can be modified. 
		StringBuffer hexString=null;
		try {
			//MessageDigest: 메소드의 인수에 알고리즘 이름을 지정함으로써 해당 알고리즘에서 해시값을 계산하는 MessageDigest를 구할 수 있다.
			//클래스명을 직관적으로 생각해보자면, 입력한 string을 잘게 쪼개서 형체를 알아볼수없게 만들겠다 뭐 그런....
			MessageDigest digest=MessageDigest.getInstance("SHA-256");
			byte[] hash=digest.digest(pass.getBytes("UTF-8")); //암호화대상charset(문자열변환방식)이 들어가야 //UTF-8:한글도 소화되는 charset
			
			//String은 불변이다!!!! 따라서, (한번 메모리에 올라온 )스트링은 그 값이 변경될 수 없다.!! //(그래서 스트링은 누적시키는 식, += 이거 쓰면 안된다.)
			//따라서 String객체는 반복문 횟수가 클때는 절대 누적식을 사용해서는 안된다.!!!!
			//해결책) 변경 가능한 문자열 객체를 지원하는 StringBuffer 나 StringBuilder 등을 활용하자
			//String hexString=""; 
			hexString=new StringBuffer(); //일반 클래스니까 new로  
			
			for(int i=0; i<hash.length; i++) {
				String hex=Integer.toHexString(0xff& hash[i]); //그냥 받아들이세요 //16진수를 해시로 바꾸는..? 바이트배열을 16진수 문자열로 바꾸는
				//(0xff& hash[i]): byte가 (32비트의?16진수의?아무튼) int형으로 강제 형변환이 됨. 그 int를 다시 HexString으로 변환시켜서 String에 담는거
				
				//System.out.println(0xff& hash[i]);
				//System.out.println(hex);
				if(hex.length()==1) {
					hexString.append("0");
					//hex="0"+hex;
					//hexString+="0";
				}
				//hexString+=hex;
				hexString.append(hex);
			}
			//System.out.println(hexString);
			//System.out.println(hexString.toString());
			//System.out.println(hexString.length());
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		System.out.println(hexString); //64자로 암호화된 코드 나옴
		return hexString.toString();
	}

	
	
	
	//어떻게 나오는지 확인용
	public static void main(String[] args) {
		String result=getConvertedPassword("minzino");
		System.out.println(result.length());
	}
	/*
	
	public static void main(String[] args) {
		System.out.println(getExtend("aaa.a.a.aa.png"));
	}
	
	 * public static void main(String[] args) { 
	 * String result=getNumString(12);
	 * System.out.println(result); 
	 * }
	 */
}
