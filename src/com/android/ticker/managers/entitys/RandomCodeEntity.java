package com.android.ticker.managers.entitys;


public class RandomCodeEntity {

	private String validateMessagesShowId;
	private Boolean status;
	private int httpstatus;
	private RespData data;
	private String[] messages;
	private ValidateMessages validateMessages;
	
	public String getResult() {
		return data.result;
	}
	
	private class RespData {
		private String result;
		private String msg;
	}
	
	private class ValidateMessages {
		
	}
}
