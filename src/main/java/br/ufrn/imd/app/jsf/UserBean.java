package br.ufrn.imd.app.jsf;

import br.ufrn.imd.app.model.User;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Session Bean that keeps the logged user information.
 */
@Named
@SessionScoped
public class UserBean implements Serializable {

  private User user;

  public String logout() {
    user = null;
    return "login";
  }

  public boolean isLogged() {
    return user != null && user.getUsername() != null && !user.getUsername().isEmpty();
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void login(User user) {
    this.user = user;
  }

  public String getUsername() {
    return user != null ? user.getUsername() : "";
  }
}
