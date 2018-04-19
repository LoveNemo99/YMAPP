package com.example.zr.ymapp;

import com.example.zr.model.AlarmRecordData;
import com.example.zr.model.EnterpriseInfo;
import com.example.zr.model.HourData;
import com.example.zr.model.RealtimeData;
import com.example.zr.model.SiteMonitorInfo;
import com.example.zr.model.User;
import com.example.zr.model.UserInfoSingleton;
import com.google.gson.Gson;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class ConnUtil {
	//private final static String BASEURL = "http://10.0.2.2:8080/SSHDemo/";
    //private final static String BASEURL = "http://192.168.1.121:8080/DustNoiseServer/";
	private final static String BASEURL = "http://223.113.12.221:6007/YMServer/";
//	private final static String BASEURL = "http://192.168.1.113:8080/YMServer/";
	static Gson gson = new Gson();
	
	public static String login(String username,String pass){
		List params = new ArrayList();  
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("pass", pass));
        String url = BASEURL + "login.action";
		System.out.println(url);
        String jsonStr = GsonUtil.getJson(url,params);
		if(jsonStr != null){//[]
			System.out.println("setstate:"+jsonStr);
			String s = jsonStr.substring(0, 1);
			if(s.equals("1")){
				java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<User>(){}.getType();
				String ustr = jsonStr.substring(1);
				if(ustr!=null&&ustr.length()>0){
					User ui = gson.fromJson(ustr, type);
					UserInfoSingleton uis = UserInfoSingleton.getInstance();
					uis.setUserInfo(ui);
				}
			}
			return s;
		}
		return null;
	}

	public static List<RealtimeData> getRealtimeData(String dev){
		List params = new ArrayList();
		params.add(new BasicNameValuePair("dev", ""+dev));
		String url = BASEURL + "getreal.action";
		//System.out.println(url);
		String jsonStr = GsonUtil.getJson(url,params);
		//String jsonStr = GsonUtil.getJson(url);
		System.out.println("jsonStr:"+jsonStr);
		if(jsonStr != null&&jsonStr.length()!=2){//[]
			java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<List<RealtimeData>>(){}.getType();
			return gson.fromJson(jsonStr, type);
		}
		return null;
	}

	public static List<EnterpriseInfo> getEnterpriseInfo(){
		String url = BASEURL + "getenterpriseinfo.action";
		//System.out.println(url);
		String jsonStr = GsonUtil.getJson(url);
		if(jsonStr != null&&jsonStr.length()!=2){//[]
			java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<List<EnterpriseInfo>>(){}.getType();
			return gson.fromJson(jsonStr, type);
		}
		return null;
	}

	public static List<SiteMonitorInfo> getSiteMonitorInfo(){
		String url = BASEURL + "getsitemonitor.action";
		//System.out.println(url);
		String jsonStr = GsonUtil.getJson(url);
		if(jsonStr != null&&jsonStr.length()!=2){//[]
			java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<List<SiteMonitorInfo>>(){}.getType();
			return gson.fromJson(jsonStr, type);
		}
		return null;
	}

	public static List<AlarmRecordData> getAlarmRecordData(int offset){
		List params = new ArrayList();
		params.add(new BasicNameValuePair("offset", ""+offset));
		String url = BASEURL + "getalarm.action";
		//System.out.println(url);
		String jsonStr = GsonUtil.getJson(url,params);
		if(jsonStr != null&&jsonStr.length()!=2){//[]
			java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<List<AlarmRecordData>>(){}.getType();
			return gson.fromJson(jsonStr, type);
		}
		return null;
	}

	public static List<HourData> getHourData(String dev,int factor){
		List params = new ArrayList();
		params.add(new BasicNameValuePair("dev", ""+dev));
		params.add(new BasicNameValuePair("factor", ""+factor));
		String url = BASEURL + "gethour.action";
		String jsonStr = GsonUtil.getJson(url,params);
		if(jsonStr != null&&jsonStr.length()!=2){//[]
			java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<List<HourData>>(){}.getType();
			return gson.fromJson(jsonStr, type);
		}
		return null;
	}
//	public static UserInfo getUserInfo(String username){
//		List params = new ArrayList();
//        params.add(new BasicNameValuePair("username", username));
//        String url = BASEURL + "getuserinfo.action";
//        java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<UserInfo>(){}.getType();
//        String jsonStr = GsonUtil.getJson(url,params);
//		if(jsonStr != null){//[]
//			System.out.println("setstate:"+jsonStr);
//			return gson.fromJson(jsonStr, type);
//		}
//		return null;
//	}
	

}
