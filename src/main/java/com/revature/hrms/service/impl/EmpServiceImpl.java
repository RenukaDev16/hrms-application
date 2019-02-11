package com.revature.hrms.service.impl;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.hrms.data.exception.DataAccessException;
import com.revature.hrms.data.exception.DataServiceException;
import com.revature.hrms.data.impl.EmpDao;
import com.revature.hrms.models.Detail;
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
  
  public List<Detail>getDetails()throws DataServiceException{
	try
	{  
		List<Employees>emp=dao.getEmployees();
		List<Detail>detail=new ArrayList<>();
	    List<EmployeeEntry>entryList=dao.getLog();
	    LocalTime firstin;
	    LocalTime lastout;
	    LocalTime difference;
	    String name=" ";
	    String dept= "";
	   String   design="";
		String shift="";
	 
	   	
 for(int i=0;i<entryList.size()-2;i++) // i points record of next user or next date
{
		   EmployeeEntry entryObj=(EmployeeEntry)entryList.get(i);
		   for(int k=0;k<emp.size();k++)
		   {
			  Employees employe=(Employees)emp.get(k);
			  if(employe.getCode().equals(entryObj.getUser_id()))
			  {
				 name=employe.getName();
				 dept=employe.getDepartment();
				 design=employe.getDesignation();
				 shift=employe.getShiftzone();
			  }} // getting emp detail
			   
 if(entryObj.getRecord_type()==false)
 {
			   firstin=entryObj.getRecord_timestamp().toLocalTime();
			 
		for(int j=i+1;j<entryList.size()-1;j++)
	 {
				   
				 
				   EmployeeEntry entryObj1=(EmployeeEntry)entryList.get(j); 
  if(entryObj.getRecord_timestamp().toLocalDate().equals(entryObj1.getRecord_timestamp().toLocalDate()))// i & j compared 
{
					   EmployeeEntry entryObj2=(EmployeeEntry)entryList.get(j+1); 
	  if(entryObj1.getRecord_timestamp().toLocalDate().equals(entryObj2.getRecord_timestamp().toLocalDate()))// last table record
  {
					if(j==entryList.size()-2)
					{
                       if(entryObj2.getRecord_type()==true)
                       {
						   lastout=entryObj2.getRecord_timestamp().toLocalTime();
						difference= lastout.minusHours(firstin.getHour());
						detail.add(new Detail(name,dept,design,shift,entryObj2.getUser_id(),entryObj2.getRecord_timestamp().toLocalDate(),firstin,lastout,difference));						
                      }
                       else
                       {
                    	   detail.add(new Detail(name,dept,design,shift,entryObj2.getUser_id(),entryObj2.getRecord_timestamp().toLocalDate(),firstin,null,null));						
                       }
					}
  }  
					 
					   
	if(!(entryObj1.getRecord_timestamp().toLocalDate().equals(entryObj2.getRecord_timestamp().toLocalDate())))//last record of user or particular date 
 {
						   if(entryObj1.getRecord_type()==true)
						   {
							   lastout= entryObj1.getRecord_timestamp().toLocalTime();
							   difference=lastout.minusHours(firstin.getHour());
							   
							   for(int k=0;k<emp.size();k++)
							   {
								  Employees e=(Employees)emp.get(k);
								  if(e.getCode().equals(entryObj.getUser_id()))
								  {
									 name=e.getName();
									 dept=e.getDepartment();
									 design=e.getDesignation();
									 shift=e.getShiftzone();
								  }}
							   for(int k=0;k<emp.size();k++)
							   {
								  Employees e=(Employees)emp.get(k);
								  if(e.getCode().equals(entryObj.getUser_id()))
								  {
									 name=e.getName();
									 dept=e.getDepartment();
									 design=e.getDesignation();
									 shift=e.getShiftzone();
								  }}
								   detail.add(new Detail(name,dept,design,shift,entryObj1.getUser_id(),entryObj1.getRecord_timestamp().toLocalDate(),firstin,lastout,difference));
							                                                                                                                                                                                                                                     
							   i=j;
							   break;
						   }
						   else
						   {
							
							   detail.add(new Detail(name,dept,design,shift,entryObj1.getUser_id(),entryObj1.record_timestamp.toLocalDate(),firstin,null,null));
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
