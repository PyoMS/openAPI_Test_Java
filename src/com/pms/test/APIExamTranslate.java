package com.pms.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

// openAPI 사용 예제 (JAVA)
public class APIExamTranslate {
	final static int pSize = 100;
	
    public static void main(String[] args) {
    	
    	int list_total_count = 0;
    	int PageIndex = 1;
    	int DataCount=0;
    	
    	do{
    		if(list_total_count!=0){
        		++PageIndex;
        	}
    		System.out.println("*** Page "+PageIndex+" ***");
    		
    		JSONObject jsonObject = call_api(PageIndex+"");
        	Object RegionMnyFacltStus = jsonObject.get("RegionMnyFacltStus"); // 1. object로 받은 후
        	JSONArray jsonArray = (JSONArray) RegionMnyFacltStus; //2. array로 받고
//        	System.out.println("jsonArray.size : "+ jsonArray.size());
//        	for(int i =0;i<jsonArray.size();i++){
//        		JSONObject arr_jsonObject = (JSONObject)jsonArray.get(i);
//        		System.out.println(i+" : "+arr_jsonObject.toString());
//        	}
        	Object head = ((JSONObject)jsonArray.get(0)).get("head"); // 해당 array의 인자값을(JSONObject)로 받아서 다시 Object에 할당함. 
//        	System.out.println("head.toString() : "+head.toString());
        	
        	JSONArray jsonArray2 = (JSONArray)head;
        	list_total_count = Integer.parseInt(((JSONObject)jsonArray2.get(0)).get("list_total_count").toString());
        	System.out.println("list_total_count : " + list_total_count);
        	
        	
        	Object row = ((JSONObject)jsonArray.get(1)).get("row"); // 해당 array의 인자값을(JSONObject)로 받아서 다시 Object에 할당함. 
//        	System.out.println("row : "+row.toString());
        	DataCount = PageIndex*pSize;
    		System.out.println("DataCount : "+ DataCount);
    		
    	}while(DataCount<list_total_count);
    	
    	
    }
    
    public static JSONObject call_api(String startIndex){
    	JSONObject jsonObject = null;
    	try {
    		JSONParser parser = new JSONParser();
        	String Key = "";
        	String Type = "json"; //default = xml
//        	String pIndex = "1"; // 페이지 위치
//        	String pSize = "100"; // 페이지 당 요청 숫자.
            String apiURL = "https://openapi.gg.go.kr/RegionMnyFacltStus?Key="+Key+"&Type="+Type
            		+"&pIndex="+startIndex+"&pSize="+pSize;
            URL url = new URL(apiURL); //데이터 타입이 URL 임을 나타내주기 위해 URL 클래스를 사용한다.
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
//            con.setRequestMethod("GET");
            con.setRequestMethod("POST"); // 가능한한 post방식 사용.
            con.setDoOutput(true); //Server에서 온 데이털르 출력할 수 있는 상태인지 true/false
            
            
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode(); // Response 코드 반환.
            BufferedReader br;
            
            if(responseCode==200) { // 정상 호출
            	System.out.println("Success : " + responseCode);
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            } else {  // 에러 발생
            	System.out.println("Error : " + responseCode);
                br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
            }
            String inputLine;
            StringBuffer stringBuffer = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
            	stringBuffer.append(inputLine);
            }
            br.close();
            System.out.println(stringBuffer.toString()); // 여기서 해당 데이터 출력.
            Object obj = parser.parse(stringBuffer.toString());
            jsonObject = (JSONObject)obj;
        } catch (Exception e) {
            System.out.println(e);
        }
    	return jsonObject;
    	
    }
    
    public static void call_xml_api(){
    	try {
    		String Key = "";
        	String Type = "xml"; //default = xml
        	String pIndex = "1";
        	String pSize = "100";
            String apiURL = "https://openapi.gg.go.kr/RegionMnyFacltStus?Key="+Key+"&Type="+Type+"&pIndex="+pIndex+"&pSize="+pSize;
            URL url = new URL(apiURL); //데이터 타입이 URL 임을 나타내주기 위해 URL 클래스를 사용한다.
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
//            con.setRequestMethod("GET");
            con.setRequestMethod("POST"); // 가능한한 post방식 사용.
            con.setDoOutput(true); //Server에서 온 데이털르 출력할 수 있는 상태인지 true/false
            
            
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode(); // Response 코드 반환.
            BufferedReader br;
            
            if(responseCode==200) { // 정상 호출
            	System.out.println("Success : " + responseCode);
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            } else {  // 에러 발생
            	System.out.println("Error : " + responseCode);
                br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
            }
            String inputLine;
            StringBuffer stringBuffer = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
            	stringBuffer.append(inputLine);
            }
            br.close();
            System.out.println(stringBuffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    
}