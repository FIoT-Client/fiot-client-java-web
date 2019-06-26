package br.ufrn.imd.app.jsf;

import br.ufrn.imd.app.model.User;
import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/** Bean that representes the state of login. */
@Named
@RequestScoped
public class LoginBean extends AbstractBean {

  private static final String LOGIN_PAGE = "/login";

  @Inject private UserBean userBean;
  @Inject private MessageBean messageBean;

  private String username;
  private String password;

  public static String loginPage() {
    String redirect = redirect(LOGIN_PAGE);
    try {
      FacesContext.getCurrentInstance().getExternalContext().redirect(redirect);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return redirect;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Try to login the user with current username and password.
   *
   * <p>TODO: implement validation and persistence
   *
   * @return redirection page on success or fail
   */
  public String login() {
    if (username == null || username.isEmpty()) {
      messageBean.setError("Login: campo obrigatório.");
      return loginPage();
    }
    User user = new User(username);
    userBean.login(user);

    messageBean.setSuccess("Login realizado com sucesso.");

    return HomeBean.HOME_PAGE;
  }
}
