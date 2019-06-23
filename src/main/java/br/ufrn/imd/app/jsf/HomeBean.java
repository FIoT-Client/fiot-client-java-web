package br.ufrn.imd.app.jsf;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Bean that representes the dashboard screen after logging in.
 *
 * <p>Shows the dashboard and menu to access the app services.
 */
@Named
@RequestScoped
public class HomeBean {

  static final String HOME_PAGE = "/home";

  public String index() {
    return HOME_PAGE;
  }
}
