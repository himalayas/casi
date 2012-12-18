package com.casi.web.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements SessionAware{
	protected Logger webLogger = LoggerFactory.getLogger(this.getClass());
	private static final long serialVersionUID = 7832295150101890553L;
	protected Map session;

	public void setSession(Map session) {
		this.session = session;
	}
}