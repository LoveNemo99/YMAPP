package com.example.zr.model;

public class MonitorFactor {
	private int id;
	private String factorCode;
	private String factorName;
	private String unit;
	private boolean isVisible;
	private boolean isMaxVisible;
	private boolean isMinVisible;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getFactorCode() {
		return factorCode;
	}
	public void setFactorCode(String factorCode) {
		this.factorCode = factorCode;
	}
	
	public String getFactorName() {
		return factorName;
	}
	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public boolean getIsVisible() {
		return isVisible;
	}
	public void setIsVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	
	public boolean getIsMaxVisible() {
		return isMaxVisible;
	}
	public void setIsMaxVisible(boolean isMaxVisible) {
		this.isMaxVisible = isMaxVisible;
	}
	
	public boolean getIsMinVisible() {
		return isMinVisible;
	}
	public void setIsMinVisible(boolean isMinVisible) {
		this.isMinVisible = isMinVisible;
	}
	
	
}
