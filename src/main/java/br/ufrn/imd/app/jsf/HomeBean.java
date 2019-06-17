package br.ufrn.imd.app.jsf;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Bean that representes the first screen after logging in.
 * <p>
 * Shoes the dashboard and menu to access the app services.
 */
@Named
@RequestScoped
public class HomeBean {

    private static final String HOME_PAGE = "home";

    public String index() {
        return HOME_PAGE;
    }

}
