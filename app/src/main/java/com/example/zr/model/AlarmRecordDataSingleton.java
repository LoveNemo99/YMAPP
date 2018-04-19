package com.example.zr.model;

import java.util.List;

public class AlarmRecordDataSingleton {
	private List<AlarmRecordData> data;

	private static AlarmRecordDataSingleton instance=null;
	public static AlarmRecordDataSingleton getInstance(){
        if(instance==null){
            synchronized(AlarmRecordDataSingleton.class){
                if(instance==null){
                    instance=new AlarmRecordDataSingleton();
                }
            }
        }
        return instance;
    }
    private AlarmRecordDataSingleton(){}

    public List<AlarmRecordData> getData() {
        return data;
    }

    public void setData(List<AlarmRecordData> data) {
        this.data = data;
    }
}
