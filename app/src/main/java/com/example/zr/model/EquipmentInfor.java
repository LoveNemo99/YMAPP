package com.example.zr.model;

public class EquipmentInfor {
	private Integer id;
	private Integer stateCodeId;
	private String factorTypeId;
	private String monitorPortName;
	private String devCode;
	private String mn;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStateCodeId() {
		return stateCodeId;
	}
	public void setStateCodeId(Integer stateCodeId) {
		this.stateCodeId = stateCodeId;
	}
	public String getMonitorPortName() {
		return monitorPortName;
	}
	public void setMonitorPortName(String monitorPortName) {
		this.monitorPortName = monitorPortName;
	}
	public String getDevCode() {
		return devCode;
	}
	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}
	public String getFactorTypeId() {
		return factorTypeId;
	}
	public void setFactorTypeId(String factorTypeId) {
		this.factorTypeId = factorTypeId;
	}
	public String getMn() {
		return mn;
	}
	public void setMn(String mn) {
		this.mn = mn;
	}

}
