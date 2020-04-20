package com.pms.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// openAPI ��� ���� (JAVA)
public class APIExamTranslate {
    public static void main(String[] args) {
        try {
        	String Key = "�ش� Ű ���� �Է�.";
        	String Type = "json"; //default = xml
        	String pIndex = "1";
        	String pSize = "100";
            String apiURL = "https://openapi.gg.go.kr/RegionMnyFacltStus?Key="+Key+"&Type="+Type+"&pIndex="+pIndex+"&pSize="+pSize;
            URL url = new URL(apiURL); //������ Ÿ���� URL ���� ��Ÿ���ֱ� ���� URL Ŭ������ ����Ѵ�.
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
//            con.setRequestMethod("GET");
            con.setRequestMethod("POST"); // �������� post��� ���.
            con.setDoOutput(true); //Server���� �� �����и� ����� �� �ִ� �������� true/false
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode(); // Response �ڵ� ��ȯ.
            BufferedReader br;
            
            if(responseCode==200) { // ���� ȣ��
            	System.out.println("Success : " + responseCode);
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            } else {  // ���� �߻�
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
            System.out.println(e);
        }
    }
}