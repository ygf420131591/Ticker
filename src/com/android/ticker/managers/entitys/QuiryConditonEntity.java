package com.android.ticker.managers.entitys;

public class QuiryConditonEntity {

	private String trainData;
	private String fromStation;
	private String toStation;
	private String purposeCodes;
	
	public void setTrainData(String trainData) {
		this.trainData = trainData;
	}
	
	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}
	
	public void setToStation(String toStation) {
		this.toStation = toStation;
	}
	
	public void setPurposeCodes(String purposeCodes) {
		this.purposeCodes = purposeCodes;
	}
	
	public String getTrainData() {
		return this.trainData;
	}
	
	public String getFromStation() {
		return this.fromStation;
	}
	
	public String getToStation() {
		return this.toStation;
	}
	
	public String getPurposeCodes() {
		return this.purposeCodes;
	}
}
