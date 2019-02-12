package com.revature.hrms.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Detail {

	
	
	  public String name;
	  public String designation;
	  public String department;
	  public String shiftzone;
      public String user_id;
      List<Logs>logs=new ArrayList<>();
      
     
 public Detail()
 {
	 
 }
 
public Detail(String name, String designation, String department, String shiftzone, String user_id, List<Logs> logs) {
	super();
	this.name = name;
	this.designation = designation;
	this.department = department;
	this.shiftzone = shiftzone;
	this.user_id = user_id;
	this.logs = logs;
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


public List<Logs> getLogs() {
	return logs;
}


public void setLogs(List<Logs> logs) {
	this.logs = logs;
}

}
