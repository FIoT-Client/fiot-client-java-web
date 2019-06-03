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
    public String login() {
        return "index";
    }
}
