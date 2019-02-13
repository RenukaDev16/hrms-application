package com.revature.hrms.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class JoinRecord {

  public LocalDate date;
  public LocalTime in;
  public LocalTime out;
  public LocalTime diff;

  public JoinRecord(
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
