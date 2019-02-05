package com.revature.hrms.data.exception;

public class DataOperationFailedException extends DataAccessException {

  private static final long serialVersionUID = 1L;

  public DataOperationFailedException() {
    super();
  }

  public DataOperationFailedException(String message) {
    super(message);
  }

  public DataOperationFailedException(Throwable exception) {
    super(exception);
  }

  public DataOperationFailedException(String message, Throwable exception) {
    super(message, exception);
  }
}
