package com.example.zr.model;

public class UserInfo {
	private String username;
	private boolean administrator;
	private String cname;
	private String ename;
	private String mobile;
	private int psId;
	private String psAlias;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean getAdministrator() {
		return administrator;
	}
	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getPsId() {
		return psId;
	}
	public void setPsId(int psId) {
		this.psId = psId;
	}
	public String getPsAlias() {
		return psAlias;
	}
	public void setPsAlias(String psAlias) {
		this.psAlias = psAlias;
	}
	@Override
	public String toString() {
		return "UserInfo [username=" + username + ", administrator="
				+ administrator + ", cname=" + cname + ", ename=" + ename
				+ ", mobile=" + mobile + ", psId=" + psId + ", psAlias="
				+ psAlias + "]";
	}
	
}
