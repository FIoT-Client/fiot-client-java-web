package br.ufrn.imd.app.jsf;

import br.ufrn.imd.app.model.Service;
import br.ufrn.imd.app.service.ServiceService;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Bean that representes the first screen after logging in.
 *
 * <p>Shoes the dashboard and menu to access the app services.
 */
@Named
@RequestScoped
public class HomeBean {

  private static final String HOME_PAGE = "home";

  @EJB
  private ServiceService service;

  private Integer serviceId;

  public String index() {
    return HOME_PAGE;
  }

  public Integer getServiceId() {
    return serviceId;
  }

  public void setServiceId(Integer serviceId) {
    this.serviceId = serviceId;
  }

  public List<Service> getAllServices() {
    return service.findAll();
  }
}

