package com.example.zr.model;

public class UserInfoSingleton {
	private User userInfo;
	
	private static UserInfoSingleton instance=null;
	public static UserInfoSingleton getInstance(){
        if(instance==null){
            synchronized(UserInfoSingleton.class){
                if(instance==null){
                    instance=new UserInfoSingleton();
                }
            }
        }
        return instance;
    }
    private UserInfoSingleton(){}
	public User getUser() {
		return userInfo;
	}
	public void setUserInfo(User userInfo) {
		this.userInfo = userInfo;
	}
}
