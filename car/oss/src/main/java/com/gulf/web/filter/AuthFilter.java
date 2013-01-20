/*
 * Copyright 2013 5idea gulf team. All rights reserved.
 */
package com.gulf.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;

/**
 * AuthFilter.java
 * 认证、授权
 * @author ryenlea
 */
public class AuthFilter implements ActionFilter {
	private static final Log log = Logs.getLog(AuthFilter.class);
	
	@Override
	public View match(ActionContext ac) {
		HttpServletRequest req = ac.getRequest();
		HttpSession session = req.getSession();
		if(accessNotAllow(req)){
			//TODO
		}
		return null;
	}
	
	private boolean accessNotAllow(HttpServletRequest req){
		HttpSession session = req.getSession();
		return false;
	}
}
