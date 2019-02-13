package com.revature.hrms.service.impl;

import com.revature.hrms.data.exception.DataAccessException;
import com.revature.hrms.data.exception.DataServiceException;
import com.revature.hrms.data.impl.EmpDao;
import com.revature.hrms.models.Detail;
import com.revature.hrms.models.EmployeeEntry;
import com.revature.hrms.models.Employees;
import com.revature.hrms.models.JoinRecord;
import com.revature.hrms.models.Logs;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
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
      String id1 = null;
      String name1 =null;;
      String dept1 = null;
      String design1 =null;
      String shift1 =null;
// Collections.<Logs>sort( logs);
      int siz=logs.size();
      for (int q = 0; q <siz- 1; q++) {
        List<JoinRecord> record = new ArrayList<>();
        for (int r = q + 1; r < logs.size(); r++) {
          Logs logg = (Logs) logs.get(q);
          Logs logg1 = (Logs) logs.get(r);
          if (logg.getUser_id().equals(logg1.getUser_id())) { 
            record.add(
                new JoinRecord(logg1.getDate(), logg1.getIn(), logg1.getOut(), logg1.getDiff()));
           
          }
          else {
            id1 = logg.getUser_id();
            name1 = logg.getName();
            dept1 = logg.getDepart();
            design1 = logg.getDesign();
            shift1 = logg.getShift();
            record.add(
                new JoinRecord(logg.getDate(), logg.getIn(), logg.getOut(), logg.getDiff()));
            q = r-1;
            break;
          } 
        }
        List<JoinRecord> list = new ArrayList<JoinRecord>();
        list.addAll(record);
        detail.add(new Detail(id1, name1, dept1, design1, shift1, list));
        
      }
      
     
     
      return detail;
    } catch (DataAccessException e) {
      throw new DataServiceException(e);
    }
  }
}
