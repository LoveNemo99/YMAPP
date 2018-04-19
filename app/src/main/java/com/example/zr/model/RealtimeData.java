package com.example.zr.model;

import java.math.BigInteger;
import java.util.Date;

public class RealtimeData {
	private BigInteger id;
	private int statCodeId;
	private int devCodeId;
	private Date monitorTime;
	private int monitorFactorId;
	private Float rtd;
	private String unit;
	private String factorName;

	public RealtimeData() {
	}

	public RealtimeData(int devCodeId, String factorName, BigInteger id, int monitorFactorId, Date monitorTime, Float rtd, int statCodeId, String unit) {
		this.devCodeId = devCodeId;
		this.factorName = factorName;
		this.id = id;
		this.monitorFactorId = monitorFactorId;
		this.monitorTime = monitorTime;
		this.rtd = rtd;
		this.statCodeId = statCodeId;
		this.unit = unit;
	}

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
	public Float getRtd() {
		return rtd;
	}
	public void setRtd(Float rtd) {
		this.rtd = rtd;
	}
	@Override
	public String toString() {
		return "RealtimeData [id=" + id + ", statCodeId=" + statCodeId
				+ ", devCodeId=" + devCodeId + ", monitorTime=" + monitorTime
				+ ", monitorFactorId=" + monitorFactorId + ", rtd=" + rtd
				+ ", unit=" + unit + ", factorName=" + factorName + "]";
	}

	
}
