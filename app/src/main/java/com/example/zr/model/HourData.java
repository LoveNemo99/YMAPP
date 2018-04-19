package com.example.zr.model;

import java.math.BigInteger;
import java.util.Date;

public class HourData {
	private BigInteger id;
	private int statCodeId;
	private int devCodeId;
	private Date monitorTime;
	private int monitorFactorId;
	private Float min;
	private Float avg;
	private Float max;
	private Float cou;
	private String unit;
	private String factorName;
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getFactorName() {
		return factorName;
	}
	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}
	public int getStatCodeId() {
		return statCodeId;
	}
	public void setStatCodeId(int statCodeId) {
		this.statCodeId = statCodeId;
	}
	public int getDevCodeId() {
		return devCodeId;
	}
	public void setDevCodeId(int devCodeId) {
		this.devCodeId = devCodeId;
	}
	public Date getMonitorTime() {
		return monitorTime;
	}
	public void setMonitorTime(Date monitorTime) {
		this.monitorTime = monitorTime;
	}

	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public int getMonitorFactorId() {
		return monitorFactorId;
	}
	public void setMonitorFactorId(int monitorFactorId) {
		this.monitorFactorId = monitorFactorId;
	}
	public Float getMin() {
		return min;
	}
	public void setMin(Float min) {
		this.min = min;
	}
	public Float getAvg() {
		return avg;
	}
	public void setAvg(Float avg) {
		this.avg = avg;
	}
	public Float getMax() {
		return max;
	}
	public void setMax(Float max) {
		this.max = max;
	}
	
	public Float getCou() {
		return cou;
	}
	public void setCou(Float cou) {
		this.cou = cou;
	}
	@Override
	public String toString() {
		return "HourData [id=" + id + ", statCodeId=" + statCodeId
				+ ", devCodeId=" + devCodeId + ", monitorTime=" + monitorTime
				+ ", monitorFactorId=" + monitorFactorId + ", min=" + min
				+ ", avg=" + avg + ", max=" + max + ", cou=" + cou + ", unit="
				+ unit + ", factorName=" + factorName + "]";
	}
	
}
