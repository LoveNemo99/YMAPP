package com.example.zr.model;

public class EnterpriseInfo {
	private Integer id;
	private String name;
	private String code;
	private Integer districtId;
	private String display;
	private String remark;
	private String namePort;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getNamePort() {
		return namePort;
	}

	public void setNamePort(String namePort) {
		this.namePort = namePort;
	}
}
