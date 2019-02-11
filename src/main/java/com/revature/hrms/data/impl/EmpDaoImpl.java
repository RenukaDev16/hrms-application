package com.revature.hrms.data.impl;

import com.revature.hrms.data.exception.DataAccessException;
import com.revature.hrms.models.Employees;
import com.revature.hrms.models.EmployeeEntry;
import com.revature.hrms.utils.data.DataRetrieverUtilImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmpDaoImpl implements EmpDao {
  @Autowired private DataRetrieverUtilImpl dataRetriever;

  public List<Employees> getEmployees() throws DataAccessException {
    try {

      String query= "select  employees.code,employees.name,designations.name designation,departments.name department,shift_timings.zone shiftzone from employees inner join designations  on employees.designation_id=designations.id  inner join departments on employees.department_id=departments.id inner join shift_timings on employees.shift_id=shift_timings.id";

      return dataRetriever.retrieveBySQLResultTransformer(query, Employees.class);
    } catch (Exception e) {
      throw new DataAccessException(e);
    }
  }
  public List<EmployeeEntry>getLog()throws DataAccessException{
	try
	{
		String query="select user_id ,record_timestamp ,record_type from biometric_logs order by user_id,record_timestamp,record_type";
		return dataRetriever.retrieveBySQLResultTransformer(query, EmployeeEntry.class);
		
	}
	catch(Exception e)
	{
		throw new DataAccessException(e);
	}
	  
	  
  }
}
