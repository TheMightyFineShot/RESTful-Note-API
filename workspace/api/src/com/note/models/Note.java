package com.note.models;

import javax.xml.bind.annotation.XmlRootElement;

/*
 * Note Model
 */
@XmlRootElement
public class Note {
	
	private int id;
	
	private String body;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
