package com.revature.hrms.models;
import java.util.List;

public class Detail {

  public String user_id;
  public String name;
  public String designation;
  public String department;
  public String shiftzone;
  List<Record> Record;
 

  public Detail()
  {
	  
  }

public Detail(String user_id, String name, String designation, String department, String shiftzone,
		List<Record> Record) {
	super();
	this.user_id = user_id;
	this.name = name;
	this.designation = designation;
	this.department = department;
	this.shiftzone = shiftzone;
	this.Record = Record;
}

public String getUser_id() {
	return user_id;
}

public void setUser_id(String user_id) {
	this.user_id = user_id;
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

public List<Record> getRecord() {
	return Record;
}

public void setRecord(List<Record> Record) {
	this.Record = Record;
}



  
  
  
  
}