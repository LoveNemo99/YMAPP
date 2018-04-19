package com.example.zr.model;

public class User {
	private int id;
	private String username;
	private String cusername;
	private String password;
	private int districtId;
	private String remark;
	private Integer enterpriseId;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCusername() {
		return cusername;
	}
	public void setCusername(String cusername) {
		this.cusername = cusername;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getDistrictId() {
		return districtId;
	}
	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	@Override
	public String toString() {
		return "User{" +
				"cusername='" + cusername + '\'' +
				", id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", districtId=" + districtId +
				", remark='" + remark + '\'' +
				", enterpriseId=" + enterpriseId +
				'}';
	}
}
