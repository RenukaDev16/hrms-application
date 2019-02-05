package com.revature.hrms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.hrms.data.exception.DataAccessException;
import com.revature.hrms.data.exception.DataServiceException;
import com.revature.hrms.data.impl.EmpDao;
import com.revature.hrms.models.Details;
import com.revature.hrms.models.EmployeeEntry;
import com.revature.hrms.models.Employees;

@Service
public class EmpServiceImpl implements EmpService {
  @Autowired EmpDao dao;

  public List<Employees> getEmployees() throws DataServiceException {
    try {

      return dao.getEmployees();
    } catch (DataAccessException e) {
      throw new DataServiceException(e);
      }
  }
  
  public List<EmployeeEntry>getLog() throws DataServiceException{
  try{
	  
	  return dao.getLog();
	  
  }
  catch (DataAccessException e) {
      throw new DataServiceException(e);
      }
  
}
  
  public List<Details>getDetails()throws DataServiceException{
	  try{
		  dao.getEmployees();
		  dao.getLog();
		  
		  
		  return null;	//return result	  
		  
	  }
	  catch (DataAccessException e) {
	      throw new DataServiceException(e);
	      }
	 
  }
  
  
}
