package com.revature.hrms.service.impl;

import com.revature.hrms.data.exception.DataServiceException;
import com.revature.hrms.models.*;
import java.util.List;

public interface EmpService {

  List<Employees> getEmployees() throws DataServiceException;
  List<EmployeeEntry>getLog() throws DataServiceException;
   List<Detail> getDetails() throws DataServiceException;
}
