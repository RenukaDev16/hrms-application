package com.revature.hrms.data.exception;

public class DataUpdateFailedException extends DataServiceException {

  private static final long serialVersionUID = 1L;

  public DataUpdateFailedException() {
    super();
  }

  public DataUpdateFailedException(String message) {
    super(message);
  }

  public DataUpdateFailedException(Throwable exception) {
    super(exception);
  }

  public DataUpdateFailedException(String message, Throwable exception) {
    super(message, exception);
  }
}
