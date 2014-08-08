package com.example.dugmici.model;

import android.R.string;

public class Reminder {

	private String task;
	private String details;
	
	
	
	public Reminder (String task, String details) {
		this.task = task;
		this.details = details;
		
	}



	public String getTask() {
		return task;
	}



	public void setTask(String task) {
		this.task = task;
	}



	public String getDetails() {
		return details;
	}



	public void setDetails(String details) {
		this.details = details;
	}



	@Override
	public String toString() {
		return "Reminder [task=" + task + ", details=" + details + "]";
	}
	
	
		
	
	
}
