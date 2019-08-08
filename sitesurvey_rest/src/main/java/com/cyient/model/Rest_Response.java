package com.cyient.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Rest_Response implements Serializable {

	private static final long serialVersionUID = -3465813074586302847L;

	public Rest_Response()
	{
		System.out.println("Response Object created");
	}
	
	private String status;



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}
		
	
}