package com.birol.ems.dto;



public class SendMessage {
	private String content;
	private String sender;
	private String senderid;
	private String rcvr;
	private String rcvrid;
	private String timeStamp;
	private String color;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSenderid() {
		return senderid;
	}

	public void setSenderid(String senderid) {
		this.senderid = senderid;
	}

	public String getRcvr() {
		return rcvr;
	}

	public void setRcvr(String rcvr) {
		this.rcvr = rcvr;
	}

	public String getRcvrid() {
		return rcvrid;
	}

	public void setRcvrid(String rcvrid) {
		this.rcvrid = rcvrid;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public SendMessage(String content, String sender, String senderid, String rcvr, String rcvrid, String timeStamp,
			String color) {
		super();
		this.content = content;
		this.sender = sender;
		this.senderid = senderid;
		this.rcvr = rcvr;
		this.rcvrid = rcvrid;
		this.timeStamp = timeStamp;
		this.color = color;
	}

	public SendMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

}