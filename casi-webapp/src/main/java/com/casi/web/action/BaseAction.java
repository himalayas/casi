package com.casi.web.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements SessionAware{
	protected Log webLogger = LogFactory.getLog(this.getClass());
	private static final long serialVersionUID = 7832295150101890553L;
	protected Map session;

	public void setSession(Map session) {
		this.session = session;
	}
}