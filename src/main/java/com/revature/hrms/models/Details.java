package com.revature.hrms.models;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class Details {

  Employees code;
  Employees name;
  Employees designation;
  Employees department;
  Employees shiftzone;
  LocalDateTime firstin;
  LocalDateTime lastout;
  LocalTime workedhrs;
  
  public Details()
  {
	  
  }
public Details(Employees code, Employees name, Employees designation, Employees department, Employees shiftzone,
		LocalDateTime firstin, LocalDateTime lastout, LocalTime workedhrs) {
	super();
	this.code = code;
	this.name = name;
	this.designation = designation;
	this.department = department;
	this.shiftzone = shiftzone;
	this.firstin = firstin;
	this.lastout = lastout;
	this.workedhrs = workedhrs;
}
public Employees getCode() {
	return code;
}
public void setCode(Employees code) {
	this.code = code;
}
public Employees getDesignation() {
	return designation;
}
public void setDesignation(Employees designation) {
	this.designation = designation;
}
public Employees getDepartment() {
	return department;
}
public void setDepartment(Employees department) {
	this.department = department;
}
  
  
  
  
  
  
}
