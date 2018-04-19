package com.example.zr.ymapp;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class GsonUtil {  
    public static String getJson(String url){
    	HttpClient client = new DefaultHttpClient();  
        HttpPost request;
        try {
        	request = new HttpPost(new URI(url));
        	HttpResponse  response  =  client.execute(request);
        	//判断请求是否成功
        	if (response.getStatusLine().getStatusCode() == 200) { //200表示请求成功
        		HttpEntity  entity = response.getEntity();  
        		if(entity!=null){  
        			String beanListToJson = EntityUtils.toString(entity,"utf-8");  
        			return beanListToJson;  
        		}  
        	}
        	else{
        		System.out.println("请求失败！");
        	}
        } catch (Exception e) {  
         // TODO: handle exception  
        }  
        return  null;  
    }  

    
    public static String getJson(String url,String vname,String value){
    	HttpClient client = new DefaultHttpClient();  
        HttpPost request;
        try {
        	request = new HttpPost(new URI(url));
        	List params = new ArrayList();  
            params.add(new BasicNameValuePair(vname, value));
            request.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8)); 
            
        	HttpResponse  response  =  client.execute(request);
        	// �ж������Ƿ�ɹ�      
        	if (response.getStatusLine().getStatusCode() == 200) { //200��ʾ����ɹ�
        		HttpEntity  entity = response.getEntity();  
        		if(entity!=null){  
        			String beanListToJson = EntityUtils.toString(entity,"utf-8");  
        			return beanListToJson;  
        		}  
        	}
        	else{
        		System.out.println("请求失败！");
        	}
        } catch (Exception e) {  
         // TODO: handle exception  
        }  
        return  null;  
    }
    
    public static String getJson(String url,List params){
    	HttpClient client = new DefaultHttpClient();  
        HttpPost request;
        try {
        	request = new HttpPost(new URI(url));
            request.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8)); 
        	HttpResponse  response  =  client.execute(request);
        	// �ж������Ƿ�ɹ�      
        	if (response.getStatusLine().getStatusCode() == 200) { //200��ʾ����ɹ�
        		HttpEntity  entity = response.getEntity();  
        		if(entity!=null){  
        			String beanListToJson = EntityUtils.toString(entity,"utf-8");  
        			return beanListToJson;  
        		}  
        	}
        	else{
        		System.out.println("请求失败！");
        	}
        } catch (Exception e) {  
         // TODO: handle exception  
        }  
        return  null;  
    }
}
