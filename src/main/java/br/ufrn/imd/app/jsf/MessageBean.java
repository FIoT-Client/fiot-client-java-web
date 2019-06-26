package br.ufrn.imd.app.jsf;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named(value = "message")
@RequestScoped
public class MessageBean {

  private String success;
  private String error;

  public String getSuccess() {
    return success;
  }

  public void setSuccess(String success) {
    this.success = success;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public boolean hasSuccess() {
    return success != null && !success.isEmpty();
  }

  public boolean hasError() {
    return error != null && !error.isEmpty();
  }
}
