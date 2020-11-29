package com.adesso.challenge.enums;

public enum PlanStatus {
	AC("AC"),
	DEL("DEL");

	private String status;
	
	PlanStatus(String status){
		this.status = status;
	}
	
	public String getText() {
		return status;
	}
	
}
