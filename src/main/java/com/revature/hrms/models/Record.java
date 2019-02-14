package com.revature.hrms.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Record {
	@JsonSerialize(using=CustomDateSerialize.class)
  public LocalDate date;
	@JsonSerialize(using=CustomTimeSerialize.class)
  public LocalTime in;
	@JsonSerialize(using=CustomTimeSerialize.class)
  public LocalTime out;
	@JsonSerialize(using=CustomTimeSerialize.class)
  public LocalTime diff;
  

  public Record(
      LocalDate localDate, LocalTime localTime, LocalTime localTime2, LocalTime localTime3) {
    this.date = localDate;
    this.in = localTime;
    this.out = localTime2;
    this.diff = localTime3;
  }
	
  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) { 
    this.date = date;
  }

  public LocalTime getIn() {
    return in;
  }

  public void setIn(LocalTime in) {
    this.in = in;
  }

  public LocalTime getOut() {
    return out;
  }

  public void setOut(LocalTime out) {
    this.out = out;
  }

  public LocalTime getDiff() {
    return diff;
  }

  public void setDiff(LocalTime diff) {
    this.diff = diff;
  }
}
