package com.revature.hrms.service.impl;
import java.time.LocalDate;
import java.time.LocalTime;
//import com.revature.hrms.models.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
		  
		 List<Employees>employees =dao.getEmployees();
		Iterator itr=employees.iterator();
		List<Details>detail=new ArrayList<>();
		while(itr.hasNext())
		{ 
			Employees emp= (Employees) itr.next();
		    detail.add(new  Details(emp.getCode(),emp.getName(),emp.getDepartment(),emp.getDesignation(),emp.getShiftzone()));
		} 
		 List<EmployeeEntry>logs=dao.getLog();
		Iterator itr1=logs.iterator();
		int count=0;
		while(itr1.hasNext())
		{
			 count++;
			EmployeeEntry entry=(EmployeeEntry)itr1.next();
			Details obj=detail.get(count);
			obj.setRecord_timestamp(entry.record_timestamp);
			obj.setRecord_type(entry.record_type);
		}	
			
		for(int i=0;i<detail.size();i++)
		{
			for(int j=i+1;j<detail.size();j++)
			{
			Details obj1=detail.get(i);
			Details obj2=detail.get(j);
			if(obj1.getCode()==obj2.getCode())
			{
		LocalDate d1=obj1.getRecord_timestamp().toLocalDate();
		LocalDate d2=obj2.getRecord_timestamp().toLocalDate();
		LocalTime t1=obj1.getRecord_timestamp().toLocalTime();
		LocalTime t2=obj2.getRecord_timestamp().toLocalTime();
		LocalTime firstin,lastout,flag = null;
				if(d1.isEqual(d2)&&t1.isBefore(t2))
				{
					if(obj1.getRecord_type()==false && obj2.getRecord_type()==true)
					{
						
						firstin=t1;
					 
					   if(flag.isBefore(t2))
					   {
						   flag=t2;
					   }
					}
				}
			}
		}
		}    
			
		
		
	
		
		return null; 
		  
	  }
	  catch (DataAccessException e) {
	      throw new DataServiceException(e);
	      }
	 
  }
  
  
}
