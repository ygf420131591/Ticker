package com.android.ticker.managers.entitys;

public class CheckLoginEntity {

	private String validateMessagesShowId;
	private Boolean status;
	private int httpstatus;
	private RespData data; 
	private String[] message;
	private ValidateMessages validateMessages;
	
	public Boolean getFlag() {
		return data.flag;
	}
	
	private class RespData {
		private Boolean flag;
	}
	
	private class ValidateMessages {
		
	}
	
}
