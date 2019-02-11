package com.revature.hrms.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Detail {

	
	
     public String user_id;
     public LocalDate date;
    public LocalTime firstin;
   public LocalTime lastout;
   public LocalTime difference;
   public Detail()
 {
	 
 }
   




public Detail(String user_id, LocalDate date, LocalTime firstin, LocalTime lastout, LocalTime difference) {
	super();
	this.user_id = user_id;
	this.date = date;
	this.firstin = firstin;
	this.lastout = lastout;
	this.difference = difference;
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
public LocalTime getFirstin() {
	return firstin;
}
public void setFirstin(LocalTime firstin) {
	this.firstin = firstin;
}
public LocalTime getLastout() {
	return lastout;
}
public void setLastout(LocalTime lastout) {
	this.lastout = lastout;
}
public LocalTime getDifference() {
	return difference;
}
public void setDifference(LocalTime difference) {
	this.difference = difference;
}

@Override
public String toString() {
	return "Detail [user_id=" + user_id + ", date=" + date + ", firstin=" + firstin + ", lastout=" + lastout
			+ ", difference=" + difference + "]";
}


 
 
 
 
}
