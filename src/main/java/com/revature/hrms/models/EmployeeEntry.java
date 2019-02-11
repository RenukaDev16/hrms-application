package com.revature.hrms.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

public class  EmployeeEntry {
	

  public String user_id;
  public LocalDateTime record_timestamp;
  public Boolean record_type;

  public EmployeeEntry()
  
  {
	  
  }
  

public EmployeeEntry(String user_id, LocalDateTime record_timestamp, Boolean record_type) {
	super();
	this.user_id = user_id;
	this.record_timestamp = record_timestamp;
	this.record_type = record_type;
}


public String getUser_id() {
	return user_id;
}

public void setUser_id(String user_id) {
	this.user_id = user_id;
}

public LocalDateTime getRecord_timestamp() {
	return record_timestamp;
}

public void setRecord_timestamp(Object record_timestamp) {
	if(Objects.nonNull(record_timestamp) && record_timestamp instanceof Timestamp ){
		Timestamp timestamp = (Timestamp) record_timestamp;
		this.record_timestamp = timestamp.toLocalDateTime();
	} else if(Objects.nonNull(record_timestamp) && record_timestamp instanceof LocalDateTime ){
	this.record_timestamp = (LocalDateTime)record_timestamp;
	}
}

public Boolean getRecord_type() {
	return record_type;
}

public void setRecord_type(Boolean record_type) {
	this.record_type = record_type;
}


@Override
public String toString() {
	return "EmployeeEntry [user_id=" + user_id + ", record_timestamp=" + record_timestamp + ", record_type="
			+ record_type + "]";
}
  
  

  

}