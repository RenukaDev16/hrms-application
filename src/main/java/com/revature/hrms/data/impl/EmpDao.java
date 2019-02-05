package com.revature.hrms.data.impl;

import com.revature.hrms.data.exception.DataAccessException;
import com.revature.hrms.models.Employees;
import com.revature.hrms.models.EmployeeEntry;

import java.util.List;

public interface EmpDao {

  List<Employees> getEmployees() throws DataAccessException;
  List<EmployeeEntry>getLog()throws DataAccessException;
}
