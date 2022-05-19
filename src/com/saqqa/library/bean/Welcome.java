package com.saqqa.library.bean;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("welcome")
@ManagedBean
@SessionScoped
public class Welcome implements Serializable {

	public Welcome() {
		// TODO Auto-generated constructor stub
	}

	private String message = "Hello World!";

	
	public java.lang.String getMessage() {
		return message;
	}


	
	public void setMessage(java.lang.String message) {
		this.message = message;
	}



}
