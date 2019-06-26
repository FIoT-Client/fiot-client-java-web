package br.ufrn.imd.app.exception;

import javax.ejb.ApplicationException;

/** Exception that encapsulates business rules treatable by the user. */
@ApplicationException
public class BusinessException extends Exception {
  public BusinessException(String message) {
    super(message);
  }
}
