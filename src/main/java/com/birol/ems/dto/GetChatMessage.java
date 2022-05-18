package com.birol.ems.dto;



public class GetChatMessage {

	private String message;
	private String color;

	public GetChatMessage(String message, String color) {
		super();
		this.message = message;
		this.color = color;
	}

	public GetChatMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}