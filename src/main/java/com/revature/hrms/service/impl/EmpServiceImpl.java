package com.revature.hrms.service.impl;

import com.revature.hrms.data.exception.DataAccessException;

import com.revature.hrms.data.exception.DataServiceException;
import com.revature.hrms.data.impl.EmpDao;
import com.revature.hrms.models.Detail;
import com.revature.hrms.models.EmployeeEntry;
import com.revature.hrms.models.Employees;
import com.revature.hrms.models.Record;
import com.revature.hrms.models.Logs;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

  public List<EmployeeEntry> getLog() throws DataServiceException {
    try {

      return dao.getLog();

    } catch (DataAccessException e) {
      throw new DataServiceException(e);
    }
  }
  //final detail method
  public List<Detail> getDetails() throws DataServiceException {
    try {
      List<Employees> emp = dao.getEmployees();
      List<EmployeeEntry> entryList = dao.getLog();
      List<Logs> logs = new ArrayList<>();
      List<Detail> detail = new ArrayList<>();
      LocalTime firstin;
      LocalTime lastout;
      LocalTime difference;
      String userid = "";
      String name = "";
      String dept = "";
      String design = "";
      String shift = "";
      // i points record of next user or next date
      for (int i = 0; i < entryList.size() - 2; i++) {
        EmployeeEntry entryObj = (EmployeeEntry) entryList.get(i);
        for (int k = 0; k < emp.size(); k++) {
          Employees employe = (Employees) emp.get(k);
          if (employe.getCode().equals(entryObj.getUser_id())) {
            userid = employe.getCode();
            name = employe.getName();
            dept = employe.getDepartment();
            design = employe.getDesignation();
            shift = employe.getShiftzone();
          }
        } // getting emp detail

        if (entryObj.getRecord_type() == false) {
          firstin = entryObj.getRecord_timestamp().toLocalTime();

          for (int j = i + 1; j < entryList.size() - 1; j++) {

            EmployeeEntry entryObj1 = (EmployeeEntry) entryList.get(j);
            if (entryObj1.getUser_id().equals(entryObj.getUser_id())) {
              // i & j compared
              if (entryObj
                      .getRecord_timestamp()
                      .toLocalDate()
                      .equals(entryObj1.getRecord_timestamp().toLocalDate())
                  && entryObj1.getUser_id().equals(entryObj.getUser_id())) {
                EmployeeEntry entryObj2 = (EmployeeEntry) entryList.get(j + 1);
                // last table record
                if (entryObj2.getUser_id().equals(entryObj1.getUser_id())
                    && entryObj2
                        .getRecord_timestamp()
                        .toLocalDate()
                        .equals(entryObj1.getRecord_timestamp().toLocalDate())) {
                  if (j == entryList.size() - 2) {
                    if (entryObj2.getRecord_type() == true
                        && entryObj1.getUser_id().equals(entryObj2.getUser_id())) {
                      lastout = entryObj2.getRecord_timestamp().toLocalTime();
                      difference = lastout.minusHours(firstin.getHour());
                      logs.add(
                          new Logs(
                              name,
                              dept,
                              design,
                              shift,
                              entryObj2.getUser_id(),
                              entryObj2.getRecord_timestamp().toLocalDate(),
                              firstin,
                              lastout,
                              difference));
                    } else {
                      logs.add(
                          new Logs(
                              name,
                              dept,
                              design,
                              shift,
                              entryObj2.getUser_id(),
                              entryObj2.getRecord_timestamp().toLocalDate(),
                              firstin,
                              null,
                              null));
                    }
                  }
                }
                //last record of user or particular date
                if (!(entryObj1
                    .getRecord_timestamp()
                    .toLocalDate()
                    .equals(
                        entryObj2
                            .getRecord_timestamp()
                            .toLocalDate()))) { //split to two same user r diff user of diff date
                  if (entryObj1.getUser_id().equals(entryObj2.getUser_id())) {

                    if (entryObj1.getRecord_type() == true) {

                      lastout = entryObj1.getRecord_timestamp().toLocalTime();
                      difference = lastout.minusHours(firstin.getHour());
                      logs.add(
                          new Logs(
                              name,
                              dept,
                              design,
                              shift,
                              entryObj1.getUser_id(),
                              entryObj1.getRecord_timestamp().toLocalDate(),
                              firstin,
                              lastout,
                              difference));
                      i = j;
                      break;

                    } else {

                      logs.add(
                          new Logs(
                              name,
                              dept,
                              design,
                              shift,
                              entryObj1.getUser_id(),
                              entryObj1.getRecord_timestamp().toLocalDate(),
                              firstin,
                              null,
                              null));
                      i = j;
                      break;
                    }
                  }
                  if (!entryObj1.getUser_id().equals(entryObj2.getUser_id())) {

                    if (entryObj1.getRecord_type() == true) {

                      lastout = entryObj1.getRecord_timestamp().toLocalTime();
                      difference = lastout.minusHours(firstin.getHour());
                      logs.add(
                          new Logs(
                              name,
                              dept,
                              design,
                              shift,
                              entryObj1.getUser_id(),
                              entryObj1.getRecord_timestamp().toLocalDate(),
                              firstin,
                              lastout,
                              difference));
                      i = j;
                      break;
                    } else {

                      logs.add(
                          new Logs(
                              name,
                              dept,
                              design,
                              shift,
                              entryObj1.getUser_id(),
                              entryObj1.getRecord_timestamp().toLocalDate(),
                              firstin,
                              null,
                              null));

                      i = j;
                      break;
                    }
                  }
                }
              }
            }
          }
        }
      }
  
      List<Detail>  userDetails = new ArrayList<>();
      for(Logs log: logs){
   	  Detail foundDetail = null;
   	  for(Detail tempDetail: userDetails){
    		  if(tempDetail.getUser_id().equals(log.getUser_id())){
    			  foundDetail = tempDetail;
    			  break;    		  }
    	  }
    	  if(Objects.isNull(foundDetail)){
    		  foundDetail = new Detail();
    	  } 
   	  foundDetail.setUser_id(log.getUser_id());
		  foundDetail.setName(log.getName());
		  foundDetail.setDepartment(log.getDepart());
		  foundDetail.setDesignation(log.getDesign());
		  foundDetail.setShiftzone(log.getShift());
		  		  if(Objects.isNull(foundDetail.getRecord())){
			  //found detail object's list is empty ,creating new list
			  foundDetail.setRecord(new ArrayList<>());
	  }
		  
		Record record = new Record(log.getDate(), log.getIn(), log.getOut(), log.getDiff());
		//getting rec list using obj fd and adding list
		foundDetail.getRecord().add(record); 
      if(!userDetails.contains(foundDetail)){
			  userDetails.add(foundDetail);
		  }
     }
      
     
     
      return userDetails;
    } catch (DataAccessException e) {
      throw new DataServiceException(e);
    }
  }
}
