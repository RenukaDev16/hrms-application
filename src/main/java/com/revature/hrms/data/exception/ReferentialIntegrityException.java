package com.revature.hrms.data.exception;

public class ReferentialIntegrityException extends DataOperationFailedException {

  private static final long serialVersionUID = 1L;

  public ReferentialIntegrityException() {
    super();
  }

  public ReferentialIntegrityException(String message) {
    super(message);
  }

  public ReferentialIntegrityException(Throwable exception) {
    super(exception);
  }

  public ReferentialIntegrityException(String message, Throwable exception) {
    super(message, exception);
  }
}
