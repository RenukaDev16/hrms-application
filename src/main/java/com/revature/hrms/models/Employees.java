package com.revature.hrms.models;

public class Employees {

  public String code;
  public String name;
  public String designation;
  public String department;
  public String shiftzone;
  public Employees(){
	  
  }
  
  public Employees(String code, String name,String designation) {
	super();
	this.code = code;
	this.name = name;
	this.designation=designation;
}

public String getShiftzone() {
    return shiftzone;
  }

  public void setShiftzone(String shiftzone) {
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

  @Override
  public String toString() {
    return "Employee [code=" + code + ", name=" + name + "]";
  }

}
