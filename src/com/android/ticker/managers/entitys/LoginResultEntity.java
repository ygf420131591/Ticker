package com.android.ticker.managers.entitys;

public class LoginResultEntity {

	private String validateMessagesShowId;
	private Boolean status;
	private int httpstatus;
	private RespData data;
	private String[] messages;
	private ValidateMessages validateMessages;
	
	private class RespData {
		private String otherMsg;
		private String loginCheck;
	}
	
	private class ValidateMessages {
		
	}
}
