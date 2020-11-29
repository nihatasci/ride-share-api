package com.adesso.challenge.response;

import com.adesso.challenge.enums.ResponseStatus;

public class BaseResponse {
	private String description;
	private ResponseStatus status;

	public BaseResponse() {
		
	}
	
	public BaseResponse(String description, ResponseStatus status) {
		super();
		this.description = description;
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

}
