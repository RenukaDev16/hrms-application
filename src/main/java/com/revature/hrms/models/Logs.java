package com.revature.hrms.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Logs {

	public String user_id;
	  public LocalDate date;
	 public LocalTime  in;
	public LocalTime out;
	public LocalTime diff;
	public Logs()
	{
		
	}
	
	public Logs(String user_id, LocalDate date, LocalTime in, LocalTime out, LocalTime diff) {
		super();
		this.user_id = user_id;
		this.date = date;
		this.in = in;
		this.out = out;
		this.diff = diff;
	}

	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getIn() {
		return in;
	}
	public void setIn(LocalTime in) {
		this.in = in;
	}
	public LocalTime getOut() {
		return out;
	}
	public void setOut(LocalTime out) {
		this.out = out;
	}
	public LocalTime getDiff() {
		return diff;
	}
	public void setDiff(LocalTime diff) {
		this.diff = diff;
	}
		
	
	
	
	
}
