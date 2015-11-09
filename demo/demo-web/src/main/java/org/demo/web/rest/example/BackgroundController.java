package org.demo.web.rest.example;

import org.apache.struts2.rest.HttpHeaders;
import org.apache.struts2.rest.DefaultHttpHeaders;

public class BackgroundController {
	
	public HttpHeaders index() {
		return new DefaultHttpHeaders("background");
	}
}
