package com.demian.SpringApiTest;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class jsonResponse {
	@Id
	private String answer;
	private String url;
	private String type;
	
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
