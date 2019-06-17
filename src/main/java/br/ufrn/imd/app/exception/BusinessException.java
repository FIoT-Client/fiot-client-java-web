package br.ufrn.imd.app.exception;

/** Exception that encapsulates business rules treatable by the user. */
public class BusinessException extends Exception {
  public BusinessException(String message) {
    super(message);
  }
}
