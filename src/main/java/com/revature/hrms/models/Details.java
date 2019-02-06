package com.revature.hrms.models;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class Details {

  String code;
  String  name;
  String  designation;
  String  department;
  String shiftzone;
  LocalDateTime  record_timestamp;
  Boolean record_type;
  private String user_id;
  public static  int counter=0;
  public Details()
  {
	 
  }
  

public Details(String code, String name, String designation, String department, String shiftzone) {
	super();
	this.code = code;
	this.name = name;
	this.designation = designation;
	this.department = department;
	this.shiftzone = shiftzone;
	
}

public String getCode() {
	return code;
}

public void setCode(String code) {
	this.code = code;
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

public LocalDateTime getRecord_timestamp() {
	return record_timestamp;
}

public void setRecord_timestamp(LocalDateTime record_timestamp) {
	this.record_timestamp = record_timestamp;
}

public Boolean getRecord_type() {
	return record_type;
}

public void setRecord_type(Boolean record_type) {
	this.record_type = record_type;
}

@Override
public String toString() {
	return "Details [code=" + code + ", name=" + name + ", designation=" + designation + ", department=" + department
			+ ", shiftzone=" + shiftzone + ", record_timestamp=" + record_timestamp + ", record_type=" + record_type
			+ "]";
}
  
  
  
 


 


}
