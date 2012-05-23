package com.casi.commons;


import java.util.Map;

public class Result {

	private int resultCode;
	private String message;
	private Map resultMap;
	
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
		if(this.resultCode==Result.SUCCESS){
			return true;
		}else{
			return false;
		}
	}

	public Map getResultMap() {
		return resultMap;
	}
	public void setResultMap(Map resultMap) {
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
