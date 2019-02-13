package com.revature.hrms.controller;

import com.revature.hrms.data.exception.DataServiceException;
import com.revature.hrms.models.*;
import com.revature.hrms.service.impl.EmpService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpController {

  @Autowired EmpService empservice;

  @GetMapping(value = "/employees/")
  public ResponseEntity<List<Employees>> listemployees() {
    try {
      List<Employees> employees = empservice.getEmployees();
      return new ResponseEntity<List<Employees>>(employees, HttpStatus.OK);
    } catch (DataServiceException e) {
      System.out.println("Exception is" + e);
    }
    return null;
  }

  @GetMapping(value = "/entry/")
  public ResponseEntity<List<EmployeeEntry>> listentries() {
    try {
      List<EmployeeEntry> entry = empservice.getLog();
      return new ResponseEntity<List<EmployeeEntry>>(entry, HttpStatus.OK);

    } catch (DataServiceException e) {
      System.out.print("Exception is" + e);
    }
    return null;
  }

  @GetMapping(value = "/details")
  public ResponseEntity<List<Detail>> listdetails() {
    try {
      List<Detail> detail = empservice.getDetails();
      return new ResponseEntity<List<Detail>>(detail, HttpStatus.OK);
    } catch (DataServiceException e) {
      System.out.print("Exception is" + e);
    }
    return null;
  }
}
