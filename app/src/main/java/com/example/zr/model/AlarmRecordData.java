package com.example.zr.model;

import java.util.Date;

public class AlarmRecordData {
	private Integer id;
	private String siteName;
	private String monitorName;
	private Date datetime;
	private String content;
	private Boolean send;
	private String receiveMan;
	private String receiveNumber;
	private String soundAlarm;
	private String remark;
//	private String factor;
//	private Float alarmLimit;
//	private String alarmLimitType;
//	private String alarmLimitTimeType;
//	private String siteType;
//	private int count;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getMonitorName() {
		return monitorName;
	}
	public void setMonitorName(String monitorName) {
		this.monitorName = monitorName;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
//	public String getFactor() {
//		return factor;
//	}
//	public void setFactor(String factor) {
//		this.factor = factor;
//	}
//	public Float getAlarmLimit() {
//		return alarmLimit;
//	}
//	public void setAlarmLimit(Float alarmLimit) {
//		this.alarmLimit = alarmLimit;
//	}
//	public String getAlarmLimitType() {
//		return alarmLimitType;
//	}
//	public void setAlarmLimitType(String alarmLimitType) {
//		this.alarmLimitType = alarmLimitType;
//	}
//	public String getAlarmLimitTimeType() {
//		return alarmLimitTimeType;
//	}
//	public void setAlarmLimitTimeType(String alarmLimitTimeType) {
//		this.alarmLimitTimeType = alarmLimitTimeType;
//	}
//	public String getSiteType() {
//		return siteType;
//	}
//	public void setSiteType(String siteType) {
//		this.siteType = siteType;
//	}
//	public int getCount() {
//		return count;
//	}
//	public void setCount(int count) {
//		this.count = count;
//	}
	@Override
	public String toString() {
		return "AlarmRecordData [id=" + id + ", siteName=" + siteName
				+ ", monitorName=" + monitorName + ", datetime=" + datetime
				+ ", content=" + content + ", send=" + send + ", receiveMan="
				+ receiveMan + ", receiveNumber=" + receiveNumber
				+ ", soundAlarm=" + soundAlarm + ", remark=" + remark + "]";
	}
	
	public Boolean getSend() {
		return send;
	}
	public void setSend(Boolean send) {
		this.send = send;
	}
	public String getReceiveMan() {
		return receiveMan;
	}
	public void setReceiveMan(String receiveMan) {
		this.receiveMan = receiveMan;
	}
	public String getReceiveNumber() {
		return receiveNumber;
	}
	public void setReceiveNumber(String receiveNumber) {
		this.receiveNumber = receiveNumber;
	}
	public String getSoundAlarm() {
		return soundAlarm;
	}
	public void setSoundAlarm(String soundAlarm) {
		this.soundAlarm = soundAlarm;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}
