package com.casi.commons;


import java.util.Map;

public class Result {

	private int resultCode;
	private String message;
	private Map<String,String> resultMap;
	
	public final static int SUCCESS = 1;
	public final static int FAILURE = 0;

    public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getResultCode() {
		return resultCode;
	}
	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}
	
	public boolean isSuccess(){
        return this.resultCode == Result.SUCCESS;
	}

	public Map<String,String> getResultMap() {
		return resultMap;
	}
	public void setResultMap(Map<String,String> resultMap) {
		this.resultMap = resultMap;
	}
	
	public static Result successResult(){
		Result r = new Result();
		r.setResultCode(Result.SUCCESS);
		return r;
	}
    public static Result failureResult(){
        Result r = new Result();
        r.setResultCode(Result.FAILURE);
        return r;
    }
}
