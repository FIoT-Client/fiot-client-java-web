package br.ufrn.imd.app.exception;

/**
 * Exception that encapsulate the persistence errors that are not treatable by the user.
 *
 * <p>Disconnects, lost transaction etc.
 */
public class DaoException extends Exception {

  public DaoException(String message) {
    super(message);
  }
}
