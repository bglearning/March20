package com.fusemachines.march20.thought;

import java.time.Clock;

import org.springframework.data.annotation.Id;

public class Thought {

	@Id
	private String id;

	private String content;

	private String feeling;

	private long timeStamp;

	public Thought() {
	}

	public Thought(String content, String feeling) {
		this(content, feeling, Clock.systemUTC().millis());
	}

	public Thought(String content, String feeling, long timeStamp) {
		this.content = content;
		this.feeling = feeling;
		this.timeStamp = timeStamp;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFeeling() {
		return feeling;
	}

	public void setFeeling(String feeling) {
		this.feeling = feeling;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

}