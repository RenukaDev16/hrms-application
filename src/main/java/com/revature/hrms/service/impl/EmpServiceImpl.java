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
import com.revature.hrms.models.Detail;
import com.revature.hrms.models.EmployeeEntry;
import com.revature.hrms.models.Employees;

import ch.qos.logback.core.util.Duration;

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
  
  public List<Detail>getDetails()throws DataServiceException{
	try
	{
		List<Employees>emp=dao.getEmployees();
		List<Detail>detail=new ArrayList<>();
	   List<EmployeeEntry>entryList=dao.getLog();
	   LocalTime firstin;
	   LocalTime lastout;
	   LocalTime difference;
	 
	   	
	   for(int i=0;i<entryList.size()-2;i++)
	   {
		   EmployeeEntry entryObj=(EmployeeEntry)entryList.get(i);
		   if(entryObj.getRecord_type()==false)
		   {
			   firstin=entryObj.getRecord_timestamp().toLocalTime();
			   for(int j=i+1;j<entryList.size()-1;j++)
			   {
				   EmployeeEntry f=(EmployeeEntry)entryList.get(j);
				   if(entryObj.getRecord_timestamp().toLocalDate().equals(f.getRecord_timestamp().toLocalDate()))
				   {
					   
					   if(j==entryList.size()-1 && f.getRecord_type()==true)
					   {
						   lastout=f.getRecord_timestamp().toLocalTime();
						difference= lastout.minusHours(firstin.getHour());
						
							 detail.add(new Detail(f.getUser_id(),f.getRecord_timestamp().toLocalDate(),firstin,lastout,difference));
							 
					//	returning(f.getUser_id()+" "+f.getRecord_timestamp().toLocalDate()+" ");
						  break; 
					   }
					   EmployeeEntry g=(EmployeeEntry)entryList.get(j+1);
					   if(!(f.getRecord_timestamp().toLocalDate().equals(g.getRecord_timestamp().toLocalDate())))
					   {
						   if(f.getRecord_type()==true)
						   {
							   lastout= f.getRecord_timestamp().toLocalTime();
							   difference=lastout.minusHours(firstin.getHour());
							   
							 detail.add(new Detail(f.getUser_id(),f.getRecord_timestamp().toLocalDate(),firstin,lastout,difference));
									
						//   detail.add(new Detail(name,design,department,shift,f.getUser_id(),f.getRecord_timestamp().toLocalDate(),firstin,lastout,difference));
							   //returning all the values ..
							   i=j;
							   break;
						   }
						   else
						   {
							   i=j;
							   break;
						   }
					   }
				   }
			   }
		   }
	   }


	   return detail;
	}
	
	  catch (DataAccessException e) {
	      throw new DataServiceException(e);
	      }
	 
  }
  
  
}
