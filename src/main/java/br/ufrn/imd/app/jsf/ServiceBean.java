package br.ufrn.imd.app.jsf;

import br.ufrn.imd.app.exception.BusinessException;
import br.ufrn.imd.app.model.Service;
import br.ufrn.imd.app.service.ServiceI;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ServiceBean {

  private static final String SERVICE_FORM_PAGE = "service/form";

  @EJB
  private ServiceI<Service> service;

  @Inject
  private MessageBean message;

  private String serviceName;
  private String servicePath;
  private String serviceApi;

  private String exemplo = "0";

  public String createPage() {
    return SERVICE_FORM_PAGE;
  }

  /**
   * Saves the Service unit into the database.
   *
   * @return a redirection page after operation finishes.
   */
  public String create() {
    try {
      Service newService = new Service(serviceName, servicePath, serviceApi);
      newService = service.save(newService);
      System.out.println("Salvou: \n" + newService);
    } catch (BusinessException e) {
      message.setError(e.getMessage());
      return SERVICE_FORM_PAGE;
    }

    // TODO: change for Service listing
    return "home";
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public String getServicePath() {
    return servicePath;
  }

  public void setServicePath(String servicePath) {
    this.servicePath = servicePath;
  }

  public String getServiceApi() {
    return serviceApi;
  }

  public void setServiceApi(String serviceApi) {
    this.serviceApi = serviceApi;
  }

  public String getExemplo() {
    return exemplo;
  }

  public void setExemplo(String exemplo) {
    this.exemplo = exemplo;
  }
}
