package com.revature.hrms.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Detail {

	
	
	  public String name;
	  public String designation;
	  public String department;
	  public String shiftzone;
     public String user_id;
     public LocalDate date;
    public LocalTime firstin;
   public LocalTime lastout;
   public LocalTime difference;
   public Detail()
 {
	 
 }
public Detail(String name, String designation, String department, String shiftzone, String user_id, LocalDate date,
		LocalTime firstin, LocalTime lastout, LocalTime difference) {
	super();
	this.name = name;
	this.designation = designation;
	this.department = department;
	this.shiftzone = shiftzone;
	this.user_id = user_id;
	this.date = date;
	this.firstin = firstin;
	this.lastout = lastout;
	this.difference = difference;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDesignation() {
	return designation;
}
public void setDesignation(String designation) {
	this.designation = designation;
}
public String getDepartment() {
	return department;
}
public void setDepartment(String department) {
	this.department = department;
}
public String getShiftzone() {
	return shiftzone;
}
public void setShiftzone(String shiftzone) {
	this.shiftzone = shiftzone;
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
	return "Detail [name=" + name + ", designation=" + designation + ", department=" + department + ", shiftzone="
			+ shiftzone + ", user_id=" + user_id + ", date=" + date + ", firstin=" + firstin + ", lastout=" + lastout
			+ ", difference=" + difference + "]";
}
   
   





 
 
 
}
