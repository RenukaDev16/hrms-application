package com.revature.hrms.data.exception;

public class DataDuplicateException extends DataServiceException {

  private static final long serialVersionUID = 1L;

  public DataDuplicateException() {
    super();
  }

  public DataDuplicateException(String message) {
    super(message);
  }

  public DataDuplicateException(Throwable exception) {
    super(exception);
  }

  public DataDuplicateException(String message, Throwable exception) {
    super(message, exception);
  }
}
